package com.luu.druid.druid_demo;

import com.luu.druid.druid_demo.mapper.mall.TestMapper;
import com.luu.druid.druid_demo.mapper.user.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DruidDemoApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TestMapper testMapper;

    @Test
    void contextLoads() {
        String id = testMapper.mallNoAnno();

        String id2 = testMapper.mallExitAnno();

        String name = userMapper.userNoAnno();

        String name2 = userMapper.userExitAnno();
    }
}
