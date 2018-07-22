package com.minxc.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName DynamicDataSource
 * @Description 动态数据源绑定
 * @Author Xianchang.min
 * @Date 2018/7/22 15:02
 * @Version 1.0
 **/

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

}
