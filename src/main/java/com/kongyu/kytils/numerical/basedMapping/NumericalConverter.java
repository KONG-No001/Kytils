package com.kongyu.kytils.numerical.basedMapping;

import com.kongyu.kytils.numerical.INumericalConverter;
import com.kongyu.kytils.numerical.NumericalConverterException;
import com.kongyu.kytils.numerical.basedMapping.mapper.INumericalDecimalMapper;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/28
 */
public class NumericalConverter implements INumericalConverter {

    private INumericalDecimalMapper numericalDecimalMapper;

    private Map<Integer, Integer> cardinalityOffset;

    public void setNumericalDecimalMapper(INumericalDecimalMapper numericalDecimalMapper) {
        this.numericalDecimalMapper = numericalDecimalMapper;
    }

    public void setCardinalityOffset(Map<Integer, Integer> cardinalityOffset) {
        this.cardinalityOffset = cardinalityOffset;
    }

    @Override
    public int toDecimal(int cardinality, String code) {
        int decimal = 0;
        int weighting = 1; // 位权
        Map<String, Integer> mapper = numericalDecimalMapper.getDecimalMapper(cardinality);
        for (int i = code.length() - 1; i >= 0; i--) {
            decimal += mapper.get(code.substring(i, i + 1).toUpperCase(Locale.ROOT)) * weighting;
            weighting *= cardinality;
        }
        return decimal;
    }

    @Override
    public String toBaseCode(int cardinality, long decimal, int size) {
        if (decimal < 0) {
            throw new NumericalConverterException("decimal 必须大于 -1");
        }

        Map<String, Integer> mapper = numericalDecimalMapper.getDecimalMapper(cardinality);
        Map<Long, String> mapper2 = new HashMap<>();
        mapper.forEach((k, v) -> mapper2.put(new Long(v), k));
        if (mapper.size() != mapper2.size()) {
            throw new NumericalConverterException("存在重复值，无法生成唯一反向映射");
        }

        int offset = cardinalityOffset.getOrDefault(cardinality, 0); // 偏移

        StringBuilder sb = new StringBuilder();

        while (decimal > 0) {
            long remainder = decimal % cardinality;

            if (remainder == 0 && offset > 0) {
                remainder = cardinality + offset;
            }
            String key = mapper2.get(remainder);
            if (key != null) {
                sb.append(key);
            }

            decimal /= cardinality;
        }

        String string = sb.reverse().toString();

        if (size > 0) {
            int length = string.length();
            if (length < size) {
                StringBuilder sb2 = new StringBuilder();
                for (int j = 0; j < size - length; j++) {
                    String key = mapper2.get(0L);
                    if (key != null) {
                        sb2.append(key);
                    }
                }
                sb2.append(string);
                string = sb2.toString();
            }
        }

        return string;
    }
}
