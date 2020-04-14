package com.luu.druid.druid_demo.aop;

import com.luu.druid.druid_demo.common.ChangeDataSource;
import com.luu.druid.druid_demo.config.DynamicDataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect implements Ordered {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 切点: 所有配置 ChangeDataSource 注解的方法
     */
    @Pointcut("@annotation(com.luu.druid.druid_demo.common.ChangeDataSource)")
    public void dataSourcePointCut() {}

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        ChangeDataSource ds = method.getAnnotation(ChangeDataSource.class);
        // 通过判断 @ChangeDataSource注解 中的值来判断当前方法应用哪个数据源
        DynamicDataSourceHolder.setRouteKey(ds.value());
        System.out.println("当前数据源: " + ds.value());
        logger.debug("set datasource is " + ds.value());
        try {
            return point.proceed();
        } finally {
            DynamicDataSourceHolder.removeRouteKey();
            logger.debug("clean datasource");
        }
    }
    @Override
    public int getOrder() {
        return 1;
    }
}
