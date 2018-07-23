package com.minxc;

import com.minxc.datasource.DynamicDataSourceRegister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
//@JdbcTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DemoApplicationTests {

    @Resource
    private DataSource dataSource;
    @Resource
    private StudentService service;
    @Test
    public void contextLoads() {
       System.err.print("Hello...闵现畅。。。");
       System.out.println(dataSource.toString());
    }

}
