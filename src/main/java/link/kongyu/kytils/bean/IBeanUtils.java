package link.kongyu.kytils.bean;

import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/28
 */
public interface IBeanUtils {

    PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz);

    PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName);

    void setProperty(Object bean, String property, Object value);

    Object getProperty(Object bean, String property);

    <T> T mapToBean(Map<String, Object> map, Class<T> clazz);

    Map<String, Object> beanToMap(Object bean);
}
