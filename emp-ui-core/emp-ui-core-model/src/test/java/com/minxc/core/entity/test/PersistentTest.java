package com.minxc.core.entity.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

/**********************************************************
 * PersistentTest
 * @author min.xianchang  xianchangmin@126.com
 * @date 2018/7/9
 *
 *********************************************************/


public class PersistentTest {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory ( "corePersistenceUnit" );
    }
    @Test
    public void testBasicUsage() {
        // create a couple of events...
        EntityManager entityManager = Persistence.createEntityManagerFactory ( "corePersistenceUnit" ).createEntityManager();
//        entityManager.getTransaction().begin();
//
//        entityManager.getTransaction().commit();
//        entityManager.close();
//
//        // now lets pull events from the database and list them
//        entityManager = entityManagerFactory.createEntityManager();
//
//        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @After
    public  void destroy(){
        entityManagerFactory.close ();
    }
}
