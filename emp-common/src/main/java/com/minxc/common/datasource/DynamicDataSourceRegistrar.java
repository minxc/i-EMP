package com.minxc.common.datasource;

import com.google.common.collect.Maps;
import com.zaxxer.hikari.HikariDataSource;
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
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DynamicDataSourceRegistrar
 * @Description 动态数据源注册类
 * @Author Xianchang.min
 * @Date 2018/7/21 23:48
 * @Version 1.0
 **/

public class DynamicDataSourceRegistrar implements EnvironmentAware, ImportBeanDefinitionRegistrar {

    //数据源的别名
    private final static ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases(); //别名

    static {
        //由于部分数据源配置不同，所以在此处添加别名，避免切换数据源出现某些参数无法注入的情况
        aliases.addAliases("url", new String[]{"jdbc-url"});
        aliases.addAliases("username", new String[]{"user"});
    }


    private Environment evn; //配置上下文（也可以理解为配置文件的获取工具）

    private Map<String, DataSource> sourceMap = Maps.newHashMap();  //数据源列表

    private Binder binder; //参数绑定工具


    /*
     * @method getDataSourceType
     * @Description 获取数据源类型
     * @Author Xianchang.min
     * @Date 23:56 2018/7/21
     * @Param [dataSourceClazzName]
     * @return java.lang.Class<? extends javax.sql.DataSource>
     **/
    private Class<? extends DataSource> getDataSourceType(String dataSourceClazzName) {
        Class<? extends DataSource> type;
        try {
            if (StringUtils.hasLength(dataSourceClazzName)) { //字符串不为空则通过反射获取class对象
                type = (Class<? extends DataSource>) Class.forName(dataSourceClazzName);
            } else {
                type = HikariDataSource.class;  //默认为hikariCP数据源，与spring boot默认数据源保持一致
            }
            return type;
        } catch (Exception e) {
            throw new IllegalArgumentException("can not resolve class with type: " + dataSourceClazzName); //无法通过反射获取class对象的情况则抛出异常，该情况一般是写错了，所以此次抛出一个runtimeexception
        }
    }


    /*
     * @method bind
     * @Description 绑定参数，
     *     以下三个方法都是参考DataSourceBuilder的bind方法实现的，目的是尽量保证我们自己添加的数据源构造过程与springboot保持一致
     * @Author Xianchang.min
     * @Date 23:58 2018/7/21
     * @Param [result, properties]
     * @return void
     **/
    private void bind(DataSource result, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(result));  //将参数绑定到对象
    }

    /*
     * @method bind
     * @Description
     * @Author Xianchang.min
     * @Date 23:58 2018/7/21
     * @Param [clazz, properties]
     * @return T
     **/
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

    /*
     * @method setEnvironment
     * @Description EnvironmentAware接口的实现方法，通过aware的方式注入，此处是environment对象
     * @Author Xianchang.min
     * @Date 23:51 2018/7/21
     * @Param [environment]
     * @return void
     **/
    @Override
    public void setEnvironment(Environment environment) {
        this.evn = environment;
        this.binder = Binder.get(environment);
    }



    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map config, properties, defaultConfig = binder.bind("spring.datasource", Map.class).get(); //获取所有数据源配置
        sourceMap = new HashMap<>(); //默认配置
        properties = defaultConfig;
        String typeStr = evn.getProperty("spring.datasource.type"); //默认数据源类型
        Class<? extends DataSource> clazz = getDataSourceType(typeStr); //获取数据源类型
        DataSource consumerDatasource, defaultDatasource = bind(clazz, properties); //绑定默认数据源参数
        List<Map> configs = binder.bind("spring.datasource.multi", Bindable.listOf(Map.class)).get(); //获取其他数据源配置
        for (int i = 0; i < configs.size(); i++) { //遍历生成其他数据源
            config = configs.get(i);
            clazz = getDataSourceType((String) config.get("type"));
            if ((boolean) config.getOrDefault("extend", Boolean.TRUE)) { //获取extend字段，未定义或为true则为继承状态
                properties = new HashMap(defaultConfig); //继承默认数据源配置
                properties.putAll(config); //添加数据源参数
            } else {
                properties = config; //不继承默认配置
            }
            consumerDatasource = bind(clazz, properties); //绑定参数
            sourceMap.put(config.get("key").toString(), consumerDatasource); //获取数据源的key，以便通过该key可以定位到数据源
        }
        GenericBeanDefinition define = new GenericBeanDefinition(); //bean定义类
        define.setBeanClass(DynamicDataSource.class); //设置bean的类型，此处MultiDataSource是继承AbstractRoutingDataSource的实现类
        MutablePropertyValues mpv = define.getPropertyValues(); //需要注入的参数，类似spring配置文件中的<property/>
        mpv.add("defaultTargetDataSource", defaultDatasource); //添加默认数据源，避免key不存在的情况没有数据源可用
        mpv.add("targetDataSources", sourceMap); //添加其他数据源
        beanDefinitionRegistry.registerBeanDefinition("datasource", define); //将该bean注册为datasource，不使用springboot自动生成的datasource
    }
}
