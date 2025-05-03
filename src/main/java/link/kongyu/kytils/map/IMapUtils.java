package link.kongyu.kytils.map;

import java.util.Map;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/28
 */
public interface IMapUtils {

    <T> T mapToBean(Map<String, Object> map, Class<T> clazz);

    Map<String, Object> beanToMap(Object bean);

}
