package com.cxxy.shop.aop;

import com.cxxy.shop.config.DynamicDataSourceHolder;
import com.cxxy.shop.config.annotation.ReadDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Author:liuhui
 * Description:
 * Date: 下午2:28 2017/12/21
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Around("execution(public * com.cxxy.shop.service..*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();

        if (targetMethod.isAnnotationPresent(ReadDataSource.class)) {
            String targetDataSource = targetMethod.getAnnotation(ReadDataSource.class).dataSource();
            logger.info("----------数据源是:" + targetDataSource + "------");
            DynamicDataSourceHolder.setDataSource(targetDataSource);
        }

        Object result = proceedingJoinPoint.proceed();//执行方法
        DynamicDataSourceHolder.clearDataSource();

        return result;
    }
}
