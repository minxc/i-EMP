package com.minxc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 应用的入口层函数
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// 继承实现基于单独的WAR部署方式
//public class EmpUIApplication  extends SpringBootServletInitializer{    war部署方式
//public static void main(String[] args) {
//  SpringApplication.run(EmpUIApplication.class, args);
//}
//}
