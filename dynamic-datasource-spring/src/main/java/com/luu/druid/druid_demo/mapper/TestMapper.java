package com.luu.druid.druid_demo.mapper;

import com.luu.druid.druid_demo.common.ChangeDataSource;
import com.luu.druid.druid_demo.common.DataSourceFlag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    int test();

    String mallNoAnno();

    @ChangeDataSource(DataSourceFlag.DATA_SOURCE_FLAG_MALL)
    String mallExitAnno();

    @ChangeDataSource(DataSourceFlag.DATA_SOURCE_FLAG_USER)
    String userNoAnno();

    @ChangeDataSource(DataSourceFlag.DATA_SOURCE_FLAG_USER)
    String userExitAnno();

}
