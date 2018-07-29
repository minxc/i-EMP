
/**  

* @Title: CommonUtils.java 

* @Package com.minxc.id.util 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:31:57 

* @version V1.0  

*/ 

package com.minxc.id.util;

import java.util.Arrays;

/**   
*    
* 项目名称：emp-id-generator-impl   
* 类名称：CommonUtils   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:31:57   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:31:57   
* 修改备注：   
* @version  1.0  
*    
*/

public class CommonUtils {
    
    public static String[] SWITCH_ON_EXP = new String[]{"ON", "TRUE", "on", "true"};

    public static String[] SWITCH_OFF_EXP = new String[]{"OFF", "FALSE", "off", "false"};

    public static boolean isOn(String swtch) {

        if (Arrays.asList(SWITCH_ON_EXP).contains(swtch)) {
            return true;
        }
            return false;
    }

    public static boolean isPropKeyOn(String key) {

        String prop = System.getProperty(key);

        if (Arrays.asList(SWITCH_ON_EXP).contains(prop)) {
            return true;
        }

        return false;
    }
}
