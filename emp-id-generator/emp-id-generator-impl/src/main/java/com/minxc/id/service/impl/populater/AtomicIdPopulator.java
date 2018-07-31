
/**  

* @Title: AtomicIdPopulator.java 

* @Package com.minxc.id.service.impl.populater 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:04:29 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.populater;

import java.util.concurrent.atomic.AtomicReference;

import com.minxc.id.bean.Id;
import com.minxc.id.service.impl.bean.IdMetaData;
import com.minxc.id.service.impl.timer.Timer;

/**   
*    
* 项目名称：emp-id-impl   
* 类名称：AtomicIdPopulator   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:04:29   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:04:29   
* 修改备注：   
* @version  1.0  
*    
*/

public class AtomicIdPopulator implements IdPopulator, ResetPopulator {

    class Variant {

        private long sequence = 0;
        private long lastTimestamp = -1;

    }

    private AtomicReference<Variant> variant = new AtomicReference<Variant>(new Variant());

    public AtomicIdPopulator() {
        super();
    }

    
    @Override
    public void populateId(Timer timer, Id id, IdMetaData idMeta) {
        Variant varOld, varNew;
        long timestamp, sequence;

        while (true) {

            // Save the old variant
            varOld = variant.get();

            // populate the current variant
            timestamp = timer.genTime();
            timer.validateTimestamp(varOld.lastTimestamp, timestamp);

            sequence = varOld.sequence;

            if (timestamp == varOld.lastTimestamp) {
                sequence++;
                sequence &= idMeta.getSeqBitsMask();
                if (sequence == 0) {
                    timestamp = timer.tillNextTimeUnit(varOld.lastTimestamp);
                }
            } else {
                sequence = 0;
            }

            // Assign the current variant by the atomic tools
            varNew = new Variant();
            varNew.sequence = sequence;
            varNew.lastTimestamp = timestamp;

            if (variant.compareAndSet(varOld, varNew)) {
                id.setSeq(sequence);
                id.setTime(timestamp);

                break;
            }

        }
    }
    
    @Override
    public void reset() {
        variant = new AtomicReference<Variant>(new Variant());
    }

}
