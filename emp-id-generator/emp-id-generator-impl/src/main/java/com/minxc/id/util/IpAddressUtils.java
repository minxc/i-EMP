
/**  

* @Title: IpAddressUtils.java 

* @Package com.minxc.id.util 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:32:43 

* @version V1.0  

*/ 

package com.minxc.id.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import lombok.extern.slf4j.Slf4j;

/**   
*    
* 项目名称：emp-id-generator-impl   
* 类名称：IpAddressUtils   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:32:43   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:32:43   
* 修改备注：   
* @version  1.0  
*    
*/
@Slf4j
public class IpAddressUtils {
    public static String getHostIp() {
        String ip = null;
        try {
            Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr
                            .nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()) {
                        ip = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            log.error("Fail to get IP address.", e);
        }
        return ip;
    }

    public static String getHostName() {
        String hostName = null;
        try {
            Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr
                            .nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()) {
                        hostName = inetAddress.getHostName();
                    }
                }
            }
        } catch (SocketException e) {
            log.error("Fail to get host name.", e);
        }
        return hostName;
    }
}
