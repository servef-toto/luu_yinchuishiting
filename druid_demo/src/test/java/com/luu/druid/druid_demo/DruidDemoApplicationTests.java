package com.luu.druid.druid_demo;

import com.luu.druid.druid_demo.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
class DruidDemoApplicationTests {

    @Autowired
    TestMapper testMapper;

    @Test
    void contextLoads() {
        int a = testMapper.test();
        System.out.println(a);
    }

    private Logger logger = LoggerFactory.getLogger(DruidDemoApplicationTests.class);

    @Resource(name = "mallDataSource")
    private DataSource ordersDataSource;

    @Resource(name = "usersDataSource")
    private DataSource usersDataSource;

    @Test
    public void test() {
        // orders 数据源
        logger.info("[run][获得数据源：{}]", ordersDataSource.getClass());

        // users 数据源
        logger.info("[run][获得数据源：{}]", usersDataSource.getClass());

        int a = testMapper.test();
        System.out.println(a);
    }

}
