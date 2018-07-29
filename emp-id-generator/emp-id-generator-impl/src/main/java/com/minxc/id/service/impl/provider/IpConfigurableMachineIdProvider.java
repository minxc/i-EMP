
/**  

* @Title: IpConfigurableMachineIdProvider.java 

* @Package com.minxc.id.service.impl.provider 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:28:26 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.minxc.id.util.IpAddressUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**   
*    
* 项目名称：emp-id-generator-impl   
* 类名称：IpConfigurableMachineIdProvider   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:28:26   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:28:26   
* 修改备注：   
* @version  1.0  
*    
*/


@Slf4j
@Setter
@Getter
public class IpConfigurableMachineIdProvider implements MachineIdProvider {

    private long machineId;

    private Map<String, Long> ipsMap = new HashMap<String, Long>();

    public IpConfigurableMachineIdProvider() {
        log.debug("IpConfigurableMachineIdProvider constructed.");
    }

    public IpConfigurableMachineIdProvider(String ips) {
        setIps(ips);
        init();
    }

    public void init() {
        String ip = IpAddressUtils.getHostIp();
        if (StringUtils.isEmpty(ip)) {
            String msg = "Fail to get host IP address. Stop to initialize the IpConfigurableMachineIdProvider provider.";
            log.error(msg);
            throw new IllegalStateException(msg);
        }

        if (!ipsMap.containsKey(ip)) {
            String msg = String.format("Fail to configure ID for host IP address %s. Stop to initialize the IpConfigurableMachineIdProvider provider.", ip);

            log.error(msg);
            throw new IllegalStateException(msg);
        }

        machineId = ipsMap.get(ip);

        log.info("IpConfigurableMachineIdProvider.init ip {} id {}", ip, machineId);
    }

    public void setIps(String ips) {
        log.debug("IpConfigurableMachineIdProvider ips {}", ips);
        if (!StringUtils.isEmpty(ips)) {
            String[] ipArray = ips.split(",");

            for (int i = 0; i < ipArray.length; i++) {
                ipsMap.put(ipArray[i], (long) i);
            }
        }
    }


}
