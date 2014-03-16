/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package cn.dayne.gz.platform.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 容器的帮助类.
 * 
 * @author 
 * @version 1.0, 2010-7-2
 */
@SuppressWarnings("rawtypes")
public class AppContextUtil implements ApplicationContextAware {
    /**
     * 获取容器.
     */
    private static ApplicationContext appContext;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.context.ApplicationContextAware
     * #setApplicationContext
     * (org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        appContext = applicationContext;
    }

    /**
     * 获取指定class类型的实现以及子类的实现.
     * 
     * @author liyin 2010-7-2
     * @param type class类型
     * @return the Map of matching bean instances, or an
     *         empty Map if none
     */
    public static Map<?, ?> beansOfTypeIncludingAncestors(Class<?> type) {
        return BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, type, true, false);
    }

	@SuppressWarnings("unchecked")
	public static Map getBeansOfType(Class type) {
        return appContext.getBeansOfType(type);
    }
	@SuppressWarnings("unchecked")
    public static Object getBean(String name, Class requiredType) throws BeansException {
        return appContext.getBean(name, requiredType);
    }

    public static Object getBean(String name) {
        return appContext.getBean(name);
    }

    public static Class getType(String name) throws NoSuchBeanDefinitionException {
        return appContext.getType(name);
    }

    public static boolean containsBean(String name) {
        return appContext.containsBean(name);
    }

}
