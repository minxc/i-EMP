package com.minxc.app;

import com.minxc.datasource.DynamicDataSourceRegister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
