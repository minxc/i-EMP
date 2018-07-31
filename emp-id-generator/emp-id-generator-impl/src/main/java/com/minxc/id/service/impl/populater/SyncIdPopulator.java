
/**  

* @Title: SyncIdPopulator.java 

* @Package com.minxc.id.service.impl.populater 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:24:40 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.populater;

import com.minxc.id.bean.Id;
import com.minxc.id.service.impl.bean.IdMetaData;
import com.minxc.id.service.impl.timer.Timer;

/**   
*    
* 项目名称：emp-id-generator-impl   
* 类名称：SyncIdPopulator   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:24:40   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:24:40   
* 修改备注：   
* @version  1.0  
*    
*/

public class SyncIdPopulator extends BasePopulator {

    public SyncIdPopulator() {
        super();
    }

    @Override
    public synchronized void populateId(Timer timer, Id id, IdMetaData idMeta) {
        super.populateId(timer, id, idMeta);
    }

}

