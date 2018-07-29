
/**  

* @Title: LockIdPopulator.java 

* @Package com.minxc.id.service.impl.populater 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:20:08 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.populater;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.minxc.id.bean.Id;
import com.minxc.id.service.impl.bean.IdMeta;
import com.minxc.id.service.impl.timer.Timer;

/**   
*    
* 项目名称：emp-id-generator-impl   
* 类名称：LockIdPopulator   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:20:08   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:20:08   
* 修改备注：   
* @version  1.0  
*    
*/

public class LockIdPopulator extends BasePopulator {
    
    private Lock lock = new ReentrantLock();

    public LockIdPopulator() {
        super();
    }

    @Override
    public void populateId(Timer timer, Id id, IdMeta idMeta) {
        lock.lock();
        try {
            super.populateId(timer, id, idMeta);
        } finally {
            lock.unlock();
        }
    }

    
}
