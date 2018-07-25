package com.minxc.core.repository.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


/**********************************************************
 *
 * @author min.xianchang  xianchangmin@126.com
 * @date 2018/7/9
 *
 *********************************************************/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "customerTransactionManager")
public class JpaRepositopryTest {
}
