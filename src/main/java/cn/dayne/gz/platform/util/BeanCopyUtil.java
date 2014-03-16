package cn.dayne.gz.platform.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import net.sf.cglib.beans.BeanCopier;

/**
 * @author yeqiuming
 * @date Sept 13, 2012
 * @Description 用于实体属性复制
 */
public class BeanCopyUtil {
	
    /**
     * 复制对象属性
     * 
     * @param <T>
     * @param source 源对象
     * @param target 目标对象类型
     * @return
     */
    public static <T> T copy(Object source, Class<T> target) {
    	Logger logger = Logger.getLogger(target);
        T entityObject = null;
        try {
            BeanCopier copier = BeanCopier.create(source.getClass(), target, false);
            entityObject = target.getConstructor().newInstance();
            copier.copy(source, entityObject, null);
        } catch (IllegalArgumentException e) {
        	logger.error(e);
        } catch (SecurityException e) {
        	logger.error(e);
        } catch (InstantiationException e) {
        	logger.error(e);
        } catch (IllegalAccessException e) {
        	logger.error(e);
        } catch (InvocationTargetException e) {
        	logger.error(e);
        } catch (NoSuchMethodException e) {
        	logger.error(e);
        }
        return entityObject;
    }
}
