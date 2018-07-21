package com.minxc.common.datasource;

import com.minxc.common.datasource.annotation.DynamicDS;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(-1000)   // 确保数据源的切换在Transactional之前
public class DynamicDataSourceAspect {


    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, DynamicDS ds) throws Throwable {
        String dsId = ds.name();
        if (!DataSourceHolder.containsDataSource(dsId)) {
            log.error("数据源[{}]不存在，使用默认数据源 > {}", ds.name(), point.getSignature());
        } else {
            log.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
            DataSourceHolder.setDataSource(ds.name());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, DynamicDS ds) {
        log.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DataSourceHolder.clearDataSource();
    }
}
