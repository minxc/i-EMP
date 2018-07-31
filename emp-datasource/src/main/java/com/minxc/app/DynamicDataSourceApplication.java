package com.minxc.app;

import com.minxc.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @ClassName DynamicDataSourceApplication
 * @Description TODO
 * @Author Xianchang.min
 * @Date 2018/7/22 22:36
 * @Version 1.0
 **/
@SpringBootApplication
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
public class DynamicDataSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicDataSourceApplication.class, args);

    }
}
