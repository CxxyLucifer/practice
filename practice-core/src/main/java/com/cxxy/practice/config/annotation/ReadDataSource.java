package com.cxxy.practice.config.annotation;


import java.lang.annotation.*;

/**
 * Author:liuhui
 * Description: 读数据源注解
 * Date: 下午2:29 2017/12/21
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReadDataSource {
    String dataSource() default "readDruidDataSource";//数据源
}
