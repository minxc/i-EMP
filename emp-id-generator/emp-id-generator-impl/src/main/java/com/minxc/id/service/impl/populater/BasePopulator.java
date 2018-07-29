
/**  

* @Title: BasePopulator.java 

* @Package com.minxc.id.service.impl.populater 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:05:31 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.populater;

import com.minxc.id.bean.Id;
import com.minxc.id.service.impl.bean.IdMeta;
import com.minxc.id.service.impl.timer.Timer;

/**   
*    
* 项目名称：emp-id-impl   
* 类名称：BasePopulator   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:05:31   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:05:31   
* 修改备注：   
* @version  1.0  
*    
*/

public abstract class BasePopulator implements IdPopulator, ResetPopulator {

    protected long sequence = 0;
    protected long lastTimestamp = -1;

    public BasePopulator() {
        super();
    }
    @Override
    public void populateId(Timer timer, Id id, IdMeta idMeta) {
        long timestamp = timer.genTime();
        timer.validateTimestamp(lastTimestamp, timestamp);

        if (timestamp == lastTimestamp) {
            sequence++;
            sequence &= idMeta.getSeqBitsMask();
            if (sequence == 0) {
                timestamp = timer.tillNextTimeUnit(lastTimestamp);
            }
        } else {
            lastTimestamp = timestamp;
            sequence = 0;
        }

        id.setSeq(sequence);
        id.setTime(timestamp);
    }

    public void reset() {
        this.sequence = 0;
        this.lastTimestamp = -1;
    }

}
