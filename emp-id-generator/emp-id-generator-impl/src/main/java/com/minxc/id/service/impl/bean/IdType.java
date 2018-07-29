
/**  

* @Title: IdType.java 

* @Package com.minxc.id.service.impl.bean 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午10:59:49 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.bean;



/**   
*    
* 项目名称：emp-id-impl   
* 类名称：IdType   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午10:59:49   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午10:59:49   
* 修改备注：   
* @version  1.0  
*    
*/

public enum IdType {
    SECONDS("seconds"), MILLISECONDS("milliseconds");

    private String name;

    private IdType(String name) {
        this.name = name;
    }

    public long value() {
        switch (this) {
            case SECONDS:
                return 0;
            case MILLISECONDS:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static IdType parse(String name) {
        if ("seconds".equals(name))
            return SECONDS;
        else if ("milliseconds".equals(name))
            return MILLISECONDS;

        throw new IllegalArgumentException("Illegal IdType name <[" + name + "]>, available names are seconds and milliseconds");
    }

    public static IdType parse(long type) {
        if (type == 1)
            return MILLISECONDS;
        else if (type == 0)
            return SECONDS;

        throw new IllegalArgumentException("Illegal IdType value <[" + type + "]>, available values are 0 (for seconds) and 1 (for milliseconds)");
    }
}
