package com.luu.druid.druid_demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.luu.druid.druid_demo.common.DataSourceFlag;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    /**
     * 创建 orders 数据源
     */
    @Bean(name = "mallDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mall") // 读取 spring.datasource.orders 配置到 HikariDataSource 对象
    public DataSource ordersDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 创建 users 数据源
     */
    @Bean(name = "usersDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.users")
    public DataSource usersDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamiDataSource dataSource(DataSource mallDataSource, DataSource usersDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceFlag.DATA_SOURCE_FLAG_MALL, mallDataSource);
        targetDataSources.put(DataSourceFlag.DATA_SOURCE_FLAG_USER, usersDataSource);
        // 还有数据源,在targetDataSources中继续添加
        System.out.println("DataSources:" + targetDataSources);
        //默认的数据源是oneDataSource
        return new DynamiDataSource(mallDataSource, targetDataSources);
    }

}
