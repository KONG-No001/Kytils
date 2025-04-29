package com.kongyu.kytils.numerical.basedMapping.mapper;

import com.kongyu.kytils.map.MapUtils;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/28
 */
public class NumericalDecimalMapper implements INumericalDecimalMapper {
    private final static Map<Integer, Map<String, Integer>> NCS = new ConcurrentHashMap<>(MapUtils.getMapInstance(
            2, Collections.unmodifiableMap(MapUtils.getMapInstance(
                    "0", 0,
                    "1", 1
            )),
            8, Collections.unmodifiableMap(MapUtils.getMapInstance(
                    "0", 0,
                    "1", 1,
                    "2", 2,
                    "3", 3,
                    "4", 4,
                    "5", 5,
                    "6", 6,
                    "7", 7
            )),
            16, Collections.unmodifiableMap(MapUtils.getMapInstance(
                    "0", 0,
                    "1", 1,
                    "2", 2,
                    "3", 3,
                    "4", 4,
                    "5", 5,
                    "6", 6,
                    "7", 7,
                    "8", 8,
                    "9", 9,
                    "A", 10,
                    "B", 11,
                    "C", 12,
                    "D", 13,
                    "E", 14,
                    "F", 15
            )),
            26, Collections.unmodifiableMap(MapUtils.getMapInstance(
                    "A", 1,
                    "B", 2,
                    "C", 3,
                    "D", 4,
                    "E", 5,
                    "F", 6,
                    "G", 7,
                    "H", 8,
                    "I", 9,
                    "J", 10,
                    "K", 11,
                    "L", 12,
                    "M", 13,
                    "N", 14,
                    "O", 15,
                    "P", 16,
                    "Q", 17,
                    "R", 18,
                    "S", 19,
                    "T", 20,
                    "U", 21,
                    "V", 22,
                    "W", 23,
                    "X", 24,
                    "Y", 25,
                    "Z", 26
            )),
            36, Collections.unmodifiableMap(MapUtils.getMapInstance(
                    "0", 0,
                    "1", 1,
                    "2", 2,
                    "3", 3,
                    "4", 4,
                    "5", 5,
                    "6", 6,
                    "7", 7,
                    "8", 8,
                    "9", 9,
                    "A", 10,
                    "B", 11,
                    "C", 12,
                    "D", 13,
                    "E", 14,
                    "F", 15,
                    "G", 16,
                    "H", 17,
                    "I", 18,
                    "J", 19,
                    "K", 20,
                    "L", 21,
                    "M", 22,
                    "N", 23,
                    "O", 24,
                    "P", 25,
                    "Q", 26,
                    "R", 27,
                    "S", 28,
                    "T", 29,
                    "U", 30,
                    "V", 31,
                    "W", 32,
                    "X", 33,
                    "Y", 34,
                    "Z", 35
            ))
    ));

    @Override
    public Map<String, Integer> getDecimalMapper(int cardinality) {
        return NCS.get(cardinality);
    }
}
