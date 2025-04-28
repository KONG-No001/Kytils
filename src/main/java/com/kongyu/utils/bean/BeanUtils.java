package com.kongyu.utils.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/28
 */
public class BeanUtils implements IBeanUtils {
    @Override
    public PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) {
        return new PropertyDescriptor[0]; // TODO: 需要硬编码
    }

    @Override
    public PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName) {
        return null; // TODO: 需要硬编码
    }

    @Override
    public void setProperty(Object bean, String property, Object value) {
        PropertyDescriptor propertyDescriptor = getPropertyDescriptor(bean.getClass(), property);
        if (propertyDescriptor != null) {
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                writeMethod.setAccessible(true);
            }
            try {
                writeMethod.invoke(bean, value);
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new BeanException("无法设置属性'" + property + "'", e);
            }
        }
    }

    @Override
    public Object getProperty(Object bean, String property) {
        PropertyDescriptor propertyDescriptor = getPropertyDescriptor(bean.getClass(), property);
        Method readMethod = propertyDescriptor.getReadMethod();
        if (readMethod != null) {
            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                readMethod.setAccessible(true);
            }
            try {
                return readMethod.invoke(bean);
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new BeanException("无法获取属性'" + property + "'", e);
            }
        }
        return null;
    }

    @Override
    public <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T bean = newInstance(clazz);

        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                try {
                    setProperty(bean, key, value);
                }
                catch (BeanException e) {
                    throw new BeanException("Map转换到'" + clazz.getName() + "'失败", e);
                }
            }
        }
        return bean;
    }

    @Override
    public Map<String, Object> beanToMap(Object bean) {
        Class<?> clazz = bean.getClass();
        Map<String, Object> map = new HashMap<>();

        for (Field declaredField : clazz.getDeclaredFields()) {
            String name = declaredField.getName();
            if ("class".equals(name)) {
                continue;
            }
            try {
                map.put(name, getProperty(bean, name));
            }
            catch (BeanException e) {
                throw new BeanException("转换Map失败", e);
            }
        }
        return map;
    }

    private <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new BeanException("对象'" + clazz.getName() + "'无法实例化", e);
        }
    }
}
