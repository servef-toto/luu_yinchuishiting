package com.luu.druid.druid_demo;

import com.luu.druid.druid_demo.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
class DruidDemoApplicationTests {

    @Autowired
    TestMapper testMapper;

    @Test
    void contextLoads() {
        int i = testMapper.test();

        String id = testMapper.mallNoAnno();

        String id2 = testMapper.mallExitAnno();

        String name = testMapper.userNoAnno();

        String name2 = testMapper.userExitAnno();
    }
}
