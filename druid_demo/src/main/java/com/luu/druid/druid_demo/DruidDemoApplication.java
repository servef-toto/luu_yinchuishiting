package com.luu.druid.druid_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;


@SpringBootApplication
public class DruidDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DruidDemoApplication.class, args);
    }

    private Logger logger = LoggerFactory.getLogger(DruidDemoApplication.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) {
        logger.info("[run][获得数据源：{}]", dataSource.getClass());
    }
}
