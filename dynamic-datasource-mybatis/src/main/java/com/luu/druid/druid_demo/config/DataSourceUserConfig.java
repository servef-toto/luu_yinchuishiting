package com.luu.druid.druid_demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.luu.druid.druid_demo.common.DBConstants;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.luu.druid.druid_demo.mapper.user", sqlSessionTemplateRef = "userSqlSessionTemplate")
public class DataSourceUserConfig {

    /**
     * 创建 user 数据源
     */
    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.users")
    public DataSource userDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 创建 MyBatis SqlSessionFactory
     */
    @Bean(name = "userSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource userDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // <2.1> 设置 orders 数据源
        bean.setDataSource(userDataSource);
        // <2.2> 设置 entity 所在包
        bean.setTypeAliasesPackage("com.luu.druid.druid_demo.entity.*");
        // <2.3> 设置 config 路径
//        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        // <2.4> 设置 mapper 路径
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/user/*.xml"));
        return bean.getObject();
    }

    /**
     * 创建 MyBatis SqlSessionTemplate
     */
    @Bean(name = "userSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(DataSource userDataSource) throws Exception {
        return new SqlSessionTemplate(this.sqlSessionFactory(userDataSource));
    }

    /**
     * 创建 user 数据源的 TransactionManager 事务管理器
     */
    @Bean(name = DBConstants.TX_MANAGER_SER)
    public PlatformTransactionManager transactionManager(DataSource userDataSource) {
        return new DataSourceTransactionManager(userDataSource);
    }

}
