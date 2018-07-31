
/**  

* @Title: PropertyMachineIdProvider.java 

* @Package com.minxc.id.service.impl.provider 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:29:37 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.provider;

/**   
*    
* 项目名称：emp-id-generator-impl   
* 类名称：PropertyMachineIdProvider   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:29:37   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:29:37   
* 修改备注：   
* @version  1.0  
*    
*/

public class PropertyMachineIdProvider implements MachineIdProvider {

    private long machineId;
    
    
    @Override
    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

}
