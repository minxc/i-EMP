
/**  

* @Title: IdServiceFactoryBean.java 

* @Package com.minxc.id.service.factory 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午10:47:04 

* @version V1.0  

*/

package com.minxc.id.service.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;

import com.minxc.id.itf.IdService;
import com.minxc.id.service.impl.IdServiceImpl;
import com.minxc.id.service.impl.provider.DataBaseMachineIdProvider;
import com.minxc.id.service.impl.provider.IpConfigurableMachineIdProvider;
import com.minxc.id.service.impl.provider.PropertyMachineIdProvider;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 项目名称：emp-id-generator-impl 类名称：IdServiceFactoryBean 类描述： 创建人：Xianchang.min
 * 创建时间：2018年7月29日 下午11:52:50 修改人：Xianchang.min 修改时间：2018年7月29日 下午11:52:50 修改备注：
 * 
 * @version 1.0
 *
 */

@Slf4j
public class IdServiceFactoryBean implements FactoryBean<IdService> {

    public enum Type {
        PROPERTY, IP_CONFIGURABLE, DB
    }

    private Type providerType;

    private long machineId;

    private String ips;

    private String dbUrl;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    private long genMethod = -1;
    private long type = -1;
    private long version = -1;

    private IdService idService;

    public void init() {
        if (providerType == null) {
            log.error("The type of Id service is mandatory.");
            throw new IllegalArgumentException("The type of Id service is mandatory.");
        }

        switch (providerType) {
        case PROPERTY:
            idService = constructPropertyIdService(machineId);
            break;
        case IP_CONFIGURABLE:
            idService = constructIpConfigurableIdService(ips);
            break;
        case DB:
            idService = constructDbIdService(dbUrl, dbName, dbUser, dbPassword);
            break;
        }
    }

    public IdService getObject() throws Exception {
        return idService;
    }

    private IdService constructPropertyIdService(long machineId) {
        log.info("Construct Property IdService machineId {}", machineId);

        PropertyMachineIdProvider propertyMachineIdProvider = new PropertyMachineIdProvider();
        propertyMachineIdProvider.setMachineId(machineId);

        IdServiceImpl idServiceImpl;
        if (type != -1)
            idServiceImpl = new IdServiceImpl(type);
        else
            idServiceImpl = new IdServiceImpl();

        idServiceImpl.setMachineIdProvider(propertyMachineIdProvider);
        if (genMethod != -1)
            idServiceImpl.setGenMethod(genMethod);
        if (version != -1)
            idServiceImpl.setVersion(version);
        idServiceImpl.init();

        return idServiceImpl;
    }

    private IdService constructIpConfigurableIdService(String ips) {
        log.info("Construct Ip Configurable IdService ips {}", ips);

        IpConfigurableMachineIdProvider ipConfigurableMachineIdProvider = new IpConfigurableMachineIdProvider(ips);

        IdServiceImpl idServiceImpl;
        if (type != -1)
            idServiceImpl = new IdServiceImpl(type);
        else
            idServiceImpl = new IdServiceImpl();

        idServiceImpl.setMachineIdProvider(ipConfigurableMachineIdProvider);
        if (genMethod != -1)
            idServiceImpl.setGenMethod(genMethod);
        if (version != -1)
            idServiceImpl.setVersion(version);
        idServiceImpl.init();

        return idServiceImpl;
    }

    private IdService constructDbIdService(String dbUrl, String dbName, String dbUser, String dbPassword) {
        log.info("Construct Db IdService dbUrl {} dbName {} dbUser {} dbPassword {}", dbUrl, dbName, dbUser, dbPassword);

        HikariDataSource dataSource = new HikariDataSource();

        String jdbcDriver = "com.mysql.jdbc.Driver"; // 需修改 默认数据库连接池配置方式
                                                     // 现在暂时指定为针对MySQL
//        try {
        dataSource.setDriverClassName(jdbcDriver);
//        } catch (PropertyVetoException e) {
//            log.error("Wrong JDBC driver {}", jdbcDriver);
//            log.error("Wrong JDBC driver error: ", e);
//            throw new IllegalStateException("Wrong JDBC driver ", e);
//        }

        dataSource.setMaximumPoolSize(30);
        dataSource.setValidationTimeout(20);
        dataSource.setMaxLifetime(25);

        String url = String.format("jdbc:mysql://%s/%s?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true", dbUrl, dbName);

        dataSource.setJdbcUrl(url);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setLazyInit(false);
        jdbcTemplate.setDataSource(dataSource);

        DataBaseMachineIdProvider dbMachineIdProvider = new DataBaseMachineIdProvider();
        dbMachineIdProvider.setJdbcTemplate(jdbcTemplate);
        dbMachineIdProvider.init();

        IdServiceImpl idServiceImpl;
        if (type != -1)
            idServiceImpl = new IdServiceImpl(type);
        else
            idServiceImpl = new IdServiceImpl();

        idServiceImpl.setMachineIdProvider(dbMachineIdProvider);
        if (genMethod != -1)
            idServiceImpl.setGenMethod(genMethod);
        if (version != -1)
            idServiceImpl.setVersion(version);
        idServiceImpl.init();

        return idServiceImpl;
    }

    public Class< ? > getObjectType() {
        return IdService.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public Type getProviderType() {
        return providerType;
    }

    public void setProviderType(Type providerType) {
        this.providerType = providerType;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public long getGenMethod() {
        return genMethod;
    }

    public void setGenMethod(long genMethod) {
        this.genMethod = genMethod;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}
