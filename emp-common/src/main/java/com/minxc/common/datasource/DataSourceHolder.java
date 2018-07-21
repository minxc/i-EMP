package com.minxc.common.datasource;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DataSourceHolder {

    //线程本地环境
    private static final ThreadLocal<String> contextHolders = new ThreadLocal<String>();

    //数据源列表
    public static List<String> dataSourceIds = new ArrayList<>();

    //设置数据源
    public static void setDataSource(String customerType) {
        contextHolders.set(customerType);
    }

    //获取数据源
    public static String getDataSource() {
        return (String) contextHolders.get();
    }

    //清除数据源
    public static void clearDataSource() {
        contextHolders.remove();
    }


    
    /*
     * @method containsDataSource
     * @Description 判断指定DataSrouce当前是否存在
     * @Author Xianchang.min
     * @Date 23:45 2018/7/21
     * @Param [dataSourceId] 
     * @return boolean
     **/
    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}
