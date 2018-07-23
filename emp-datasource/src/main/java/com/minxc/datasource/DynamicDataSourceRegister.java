package com.minxc.datasource;

import com.google.common.collect.Maps;
import com.sun.javafx.collections.MappingChange;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;
import sun.security.ssl.HandshakeInStream;

import javax.sql.DataSource;
import java.util.*;

/**
 * @ClassName MultiDataSourceRegister
 * @Description TODO
 * @Author Xianchang.min
 * @Date 2018/7/22 19:26
 * @Version 1.0
 **/
@Slf4j
@Configuration
public class DynamicDataSourceRegister implements EnvironmentAware {


    private static final Object DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource"; //系统默认支持的数据库连接池


    private static final String DEFAULT_DATASOURCE_TYPE_NAME = "hikari";
    // 数据源
    private DataSource defaultDataSource;
    private Map<String, DataSource> secondaryDataSource = Maps.newHashMap();

    private final static ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases(); //别名


    static {
        //由于部分数据源配置不同，所以在此处添加别名，避免切换数据源出现某些参数无法注入的情况
        aliases.addAliases("url", new String[]{"jdbc-url"});
        aliases.addAliases("username", new String[]{"user"});
    }

    private Environment evn; //配置上下文（也可以理解为配置文件的获取工具）

    private Map<String, DataSource> sourceMap;  //数据源列表

    private Binder binder; //参数绑定工具

    /**
     * ImportBeanDefinitionRegistrar接口的实现方法，通过该方法可以按照自己的方式注册bean
     *
     * @param annotationMetadata
     * @param beanDefinitionRegistry
     */
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
//        log.info("Dynamic DataSource Registry begin...");
//        GenericBeanDefinition define = new GenericBeanDefinition(); //bean定义类
//        define.setBeanClass(DynamicDataSource.class); //设置bean的类型，此处MultiDataSource是继承AbstractRoutingDataSource的实现类
//        MutablePropertyValues mpv = define.getPropertyValues(); //需要注入的参数，类似spring配置文件中的<property/>
//        mpv.add("defaultTargetDataSource", defaultDataSource); //添加默认数据源，避免key不存在的情况没有数据源可用
//        mpv.add("targetDataSources", sourceMap); //添加其他数据源targetDataSources
//        beanDefinitionRegistry.registerBeanDefinition("dataSource", define); //将该bean注册为datasource，不使用springboot自动生成的datasource
//        log.info("Dynamic DataSource Registry end...");
//    }

    /**
     * 通过字符串获取数据源class对象
     *
     * @param typeStr
     * @return
     */
    private Class<? extends DataSource> getDataSourceType(String typeStr) {
        Class<? extends DataSource> type;
        try {
            if (StringUtils.hasLength(typeStr)) { //字符串不为空则通过反射获取class对象
                type = (Class<? extends DataSource>) Class.forName(typeStr);
            } else {
                type = HikariDataSource.class;  //默认为hikariCP数据源，与springboot默认数据源保持一致
            }
            return type;
        } catch (Exception e) {
            throw new IllegalArgumentException("can not resolve class with type: " + typeStr); //无法通过反射获取class对象的情况则抛出异常，该情况一般是写错了，所以此次抛出一个runtimeexception
        }
    }


    /**
     * 绑定参数，以下三个方法都是参考DataSourceBuilder的bind方法实现的，目的是尽量保证我们自己添加的数据源构造过程与springboot保持一致
     *
     * @param result
     * @param properties
     */
    private void bind(DataSource result, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(result));  //将参数绑定到对象
    }

    private <T extends DataSource> T bind(Class<T> clazz, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(clazz)).get(); //通过类型绑定参数并获得实例对象
    }

    /**
     * @param clazz
     * @param sourcePath 参数路径，对应配置文件中的值，如: spring.datasource
     * @param <T>
     * @return
     */
    private <T extends DataSource> T bind(Class<T> clazz, String sourcePath) {
        Map properties = binder.bind(sourcePath, Map.class).get();
        return bind(clazz, properties);
    }


    /**
     * EnvironmentAware接口的实现方法，通过aware的方式注入，此处是environment对象
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        log.info("Initilize system datasources....");
        this.binder = Binder.get(environment); //绑定的变量获取获取数据源所有配置信息
        Map configs = binder.bind("spring.datasource", Map.class).get();
        initDefaultDataSource(configs);
        initSecondaryDataSources(configs);
    }

    /*
     * @method
     * @Description 获取系统默认数据源
     * @Author Xianchang.min
     * @Date 22:56 2018/7/23
     * @Param
     * @return
     **/

    private void initDefaultDataSource(Map configs){
        DataSource ds = null;
        Map defaultDsConfig = (Map)configs.get("default");  //默认数据源
        Set<String> keys = defaultDsConfig.keySet();
        if(keys.size() == 1){
            String dsType = keys.toArray(new String[1])[0];

            if(DEFAULT_DATASOURCE_TYPE_NAME.equals(dsType)){   //系统默认采用HirakiCP数据库连接池
                Map<String, String> hirakiDsCongfig = (Map<String, String>)defaultDsConfig.get(dsType);
                this.defaultDataSource =  this.bind(HikariDataSource.class, hirakiDsCongfig);
            }
        }
    }


    /*
     * @method
     * @Description 获取系统配置多个备选数据源
     * @Author Xianchang.min
     * @Date 22:56 2018/7/23
     * @Param  
     * @return 
     **/

    private void initSecondaryDataSources(Map configs){
        String secondaryDsNames = configs.get("secondary").toString();
        if(secondaryDsNames.contains(",")){   //备选数据源多余两个
            String[] dsNames = secondaryDsNames.split(",");
            for(String ds : dsNames){
                Map  aDsConfig = (Map)configs.get(ds);
                Set<String> keys = aDsConfig.keySet();
                String aDsConfigType  = keys.toArray(new String[1])[0];
                if(DEFAULT_DATASOURCE_TYPE_NAME.equals(aDsConfigType)){   //系统默认采用HirakiCP数据库连接池
                    Map<String, String> hirakiDsCongfig = (Map<String, String>)aDsConfig.get(aDsConfigType);
                    DataSource dataSource =  this.bind(HikariDataSource.class, hirakiDsCongfig);
                    this.secondaryDataSource.put(ds, dataSource);
                }
            }
        }
    }


    @Bean(name = "dataSource")
    public DynamicDataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("defaultDataSource");
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("defaultDataSource", defaultDataSource);
        dsMap.putAll(secondaryDataSource);

        for (String key : secondaryDataSource.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }


        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }
}
