package com.cxxy.practice.util;

import org.springframework.context.ApplicationContext;

/**
 * 上下文工具,需要ApplicationContextRegister类支持,获取当前系统的应用上下文
 */
public class ApplicationContextUtil {
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     * 根据bean名称从应用上下文中获取bean <br>
     * 如果没有获取到上下文，则返回null
     *
     * @param name
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Object getBean(String name) {
        Object bean = null;

        if (checkNotNull()) {
            bean = applicationContext.getBean(name);
        }

        return bean;
    }

    /**
     * 根据类型从应用上下文中获取bean <br>
     * 如果没有获取到上下文，则返回null
     *
     * @param requiredType
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> T getBean(Class<T> requiredType) {
        T t = null;

        if (checkNotNull()) {
            t = applicationContext.getBean(requiredType);
        }

        return t;
    }

    /**
     * 检查上下文是否为空<br>
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkNotNull() {
        if (applicationContext == null) {
            return false;
        } else {
            return true;
        }
    }
}
