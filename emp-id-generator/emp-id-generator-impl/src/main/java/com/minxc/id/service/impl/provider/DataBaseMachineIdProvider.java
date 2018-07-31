
/**  

* @Title: DataBaseMachineIdProvider.java 

* @Package com.minxc.id.service.impl.provider 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:27:15 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.provider;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.minxc.id.util.IpAddressUtils;

import lombok.extern.slf4j.Slf4j;

/**   
*    
* 项目名称：emp-id-generator-impl   
* 类名称：DataBaseMachineIdProvider   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:27:15   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:27:15   
* 修改备注：   
* @version  1.0  
*    
*/
@Slf4j
public class DataBaseMachineIdProvider implements MachineIdProvider {

            private long machineId;

            private JdbcTemplate jdbcTemplate;

            public DataBaseMachineIdProvider() {
                log.debug("IpConfigurableMachineIdProvider constructed.");
            }

            public void init() {
                String ip = IpAddressUtils.getHostIp();

                if (StringUtils.isEmpty(ip)) {
                    String msg = "Fail to get host IP address. Stop to initialize the DbMachineIdProvider provider.";

                    log.error(msg);
                    throw new IllegalStateException(msg);
                }

                Long id = null;
                try {
                    id = jdbcTemplate.queryForObject(
                            "select ID from DB_MACHINE_ID_PROVIDER where IP = ?",
                            new Object[]{ip}, Long.class);

                } catch (EmptyResultDataAccessException e) {
                    // Ignore the exception
                    log.error("No allocation before for ip {}.", ip);
                }

                if (id != null) {
                    machineId = id;
                    return;
                }

                log.info(
                        "Fail to get ID from DB for host IP address {}. Next step try to allocate one.",
                        ip);

                int count = jdbcTemplate
                        .update("update DB_MACHINE_ID_PROVIDER set IP = ? where IP is null limit 1",
                                ip);

                if (count <= 0 || count > 1) {
                    String msg = String
                            .format("Fail to allocte ID for host IP address {}. The {} records are updated. Stop to initialize the DbMachineIdProvider provider.",
                                    ip, count);

                    log.error(msg);
                    throw new IllegalStateException(msg);
                }

                try {
                    id = jdbcTemplate.queryForObject(
                            "select ID from DB_MACHINE_ID_PROVIDER where IP = ?",
                            new Object[]{ip}, Long.class);

                } catch (EmptyResultDataAccessException e) {
                    // Ignore the exception
                    log.error("Fail to do allocation for ip {}.", ip);
                }

                if (id == null) {
                    String msg = String
                            .format("Fail to get ID from DB for host IP address {} after allocation. Stop to initialize the DbMachineIdProvider provider.",
                                    ip);

                    log.error(msg);
                    throw new IllegalStateException(msg);
                }

                machineId = id;
            }

            public long getMachineId() {
                return machineId;
            }

            public void setMachineId(long machineId) {
                this.machineId = machineId;
            }

            public JdbcTemplate getJdbcTemplate() {
                return jdbcTemplate;
            }

            public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;
            }

}
