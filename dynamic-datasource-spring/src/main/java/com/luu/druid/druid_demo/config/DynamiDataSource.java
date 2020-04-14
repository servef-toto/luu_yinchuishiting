package com.luu.druid.druid_demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class DynamiDataSource extends AbstractRoutingDataSource {

    /**
     * 配置DataSource, defaultTargetDataSource为主数据库
     */
    public DynamiDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        //设置默认数据源
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        //设置数据源列表
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getRouteKey();
    }
}
