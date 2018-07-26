package com.minxc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用的入口层函数
 */
@SpringBootApplication(scanBasePackages = {"com.minxc.**.rest.*"})
//public class EmpUIApplication  extends SpringBootServletInitializer{    war部署方式
//    public static void main(String[] args) {
//        SpringApplication.run(EmpUIApplication.class, args);
//    }
//}


public class EmpUIApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpUIApplication.class, args);
    }
}

