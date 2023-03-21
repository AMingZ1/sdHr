package com.sd.sdhr.constant.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Title: SpringApplicationUtils
 * @Author dems
 * @Package com.sd.sdhr.constant.utils
 * @Date 2023/3/21 14:25
 * @description: ${description}
 */
@Component
public class SpringApplicationUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {

        return applicationContext;

    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        /*if (SpringApplicationUtils.applicationContext == null) {
            SpringApplicationUtils.applicationContext = applicationContext;
        }*/

        applicationContext=context;
    }

    /**
     * 通过name获取 Bean.
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param
     * @return
     */
    public static <T>T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param
     * @return
     */
    public static <T>T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取指定类型的所有bean实例
     * @param clazz
     * @param
     * @return
     */
    public static Map getBeansOfType(Class clazz) {
        Map instances = getApplicationContext().getBeansOfType(clazz);
        return instances;
    }
}
