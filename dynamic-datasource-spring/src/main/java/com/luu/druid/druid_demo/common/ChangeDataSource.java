package com.luu.druid.druid_demo.common;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeDataSource {
    String value() default DataSourceFlag.DATA_SOURCE_FLAG_MALL;
}
