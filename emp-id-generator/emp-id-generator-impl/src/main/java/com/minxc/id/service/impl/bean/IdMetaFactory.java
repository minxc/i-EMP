
/**  

* @Title: IdMetaFactory.java 

* @Package com.minxc.id.service.impl.bean 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午10:59:24 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.bean;



/**   
*    
* 项目名称：emp-id-impl   
* 类名称：IdMetaFactory   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午10:59:24   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午10:59:24   
* 修改备注：   
* @version  1.0  
*    
*/

public class IdMetaFactory {
    
    private static IdMeta maxPeak = new IdMeta((byte) 10, (byte) 20, (byte) 30, (byte) 2, (byte) 1, (byte) 1);

    private static IdMeta minGranularity = new IdMeta((byte) 10, (byte) 10, (byte) 40, (byte) 2, (byte) 1, (byte) 1);

    public static IdMeta getIdMeta(IdType type) {
        if (IdType.SECONDS.equals(type)) {
            return maxPeak;
        } else if (IdType.MILLISECONDS.equals(type)) {
            return minGranularity;
        }
        return null;
    }
}
