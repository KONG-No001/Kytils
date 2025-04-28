package com.kongyu.utils.numerical.basedMapping.mapper;

import java.util.Map;

/**
 * 进制与十进制映射器
 *
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/28
 */
public interface INumericalDecimalMapper {
    Map<String, Integer> getDecimalMapper(int cardinality);
}
