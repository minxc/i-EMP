
/**  

* @Title: IdConverter.java 

* @Package com.minxc.id.service.impl.converter 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:01:02 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.converter;

import com.minxc.id.bean.Id;
import com.minxc.id.service.impl.bean.IdMetaData;

/**   
*    
* 项目名称：emp-id-impl   
* 类名称：IdConverter   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:01:02   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:01:02   
* 修改备注：   
* @version  1.0  
*    
*/

public interface IdConverter {
    long convert(Id id, IdMetaData idMeta);

    Id convert(long id, IdMetaData idMeta);
}
