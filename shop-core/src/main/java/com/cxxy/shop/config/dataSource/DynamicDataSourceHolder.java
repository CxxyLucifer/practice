package com.cxxy.shop.config.dataSource;

/**
 * Author:liuhui
 * Description:
 * Date: 下午2:27 2017/12/21
 */
public class DynamicDataSourceHolder {

    //使用ThreadLocal把数据源与当前线程绑定
    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

    public static void setDataSource(String dataSourceName) {
        dataSources.set(dataSourceName);
    }

    public static String getDataSource() {
        return (String) dataSources.get();
    }

    public static void clearDataSource() {
        dataSources.remove();
    }
}
