package com.kongyu.utils.numerical;


import com.kongyu.utils.map.MapUtils;
import com.kongyu.utils.numerical.basedMapping.NumericalConverter;
import com.kongyu.utils.numerical.basedMapping.mapper.NumericalDecimalMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;


public class INumericalConverterTest {

    private INumericalConverter numericalConverter;

    @Before
    public void before() {
        NumericalDecimalMapper mapper = new NumericalDecimalMapper();
        NumericalConverter numericalConverter = new NumericalConverter();
        numericalConverter.setNumericalDecimalMapper(mapper);
        numericalConverter.setCardinalityOffset(new ConcurrentHashMap<>(MapUtils.getMapInstance(26, 1)));
        this.numericalConverter = numericalConverter;
    }

    @Test
    public void test() {
        int decimal = numericalConverter.toDecimal(26, "ABCDEF");
        System.out.println("n26 code 'ABCDEF' to decimal: " + decimal);
        String code = numericalConverter.toBaseCode(36, 232323, 5);
        System.out.println("decimal '232323' to n32 code: " + code);
    }

}