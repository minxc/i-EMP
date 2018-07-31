
/**  

* @Title: IdPopulator.java 

* @Package com.minxc.id.service.impl.populater 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:03:26 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.populater;

import com.minxc.id.bean.Id;
import com.minxc.id.service.impl.bean.IdMetaData;
import com.minxc.id.service.impl.timer.Timer;

/**   
*    
* 项目名称：emp-id-impl   
* 类名称：IdPopulator   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:03:26   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:03:26   
* 修改备注：   
* @version  1.0  
*    
*/

public interface IdPopulator {
    
    
    void populateId(Timer timer, Id id, IdMetaData idMeta);
}
