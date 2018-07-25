package com.minxc.app.core.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**********************************************************
 * EmpUICoreConfig
 * @author min.xianchang  xianchangmin@126.com
 * @date 2018/7/9
 *
 *********************************************************/
@EnableJpaRepositories(entityManagerFactoryRef ="empCoreEntityManagerFactory", transactionManagerRef = "customerTransactionManager")
public class EmpUICoreConfig {


    @Bean
    PlatformTransactionManager customerTransactionManager() {
        return new JpaTransactionManager (empCoreEntityManagerFactory().getObject());
    }




    @Bean
    LocalContainerEntityManagerFactoryBean empCoreEntityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(EmpUICoreConfig.class.getPackage().getName());
        return factoryBean;
    }


    /**
     * 配置系统的数据源连接池，使用HikariCP作为系统高性能连接池
     * @return
     */
    @Bean
    DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource (  );
        dataSource.setJdbcUrl ("jdbc:mysql://192.168.249.235:3306/iemp?useUnicode=true&amp;characterEncoding=UTF-8");
        dataSource.setUsername ( "root" );
        dataSource.setPassword ( "Mym7Root!" );
        dataSource.setDriverClassName ("com.mysql.cj.jdbc.Driver");
        return new HikariDataSource();
    }

}
