
/**  

* @Title: Timer.java 

* @Package com.minxc.id.service.impl.timer 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午10:56:13 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.timer;

import java.util.Date;

import com.minxc.id.service.impl.bean.IdMeta;
import com.minxc.id.service.impl.bean.IdType;

/**   
*    
* 项目名称：emp-id-impl   
* 类名称：Timer   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午10:56:13   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午10:56:13   
* 修改备注：   
* @version  1.0  
*    
*/

public interface Timer {
    
    long EPOCH = 1420041600000L;

    
    void init(IdMeta idMeta, IdType idType);

    Date transTime(long time);

    void validateTimestamp(long lastTimestamp, long timestamp);

    long tillNextTimeUnit(long lastTimestamp);

    long genTime();
}
