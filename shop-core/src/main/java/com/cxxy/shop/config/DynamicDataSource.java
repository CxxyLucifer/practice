package com.cxxy.shop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Author:liuhui
 * Description:
 * Date: 下午2:26 2017/12/21
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        //可以做一个简单的负载均衡策略
        String lookupKey = DynamicDataSourceHolder.getDataSource();
        logger.info("------------lookupKey---------:" + lookupKey);
        return lookupKey;
    }
}
