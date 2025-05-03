package link.kongyu.kytils.bean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;

public class BeanUtilsTest {

    static Logger logger = LoggerFactory.getLogger(BeanUtilsTest.class);

    private static IBeanUtils myBeanUtils = null;

    @Before
    public void before() {
        logger.info("测试开始");
        logger.info("初始化 BeanUtils");

        myBeanUtils = new MyBeanUtils() {
            @Override
            public PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) {
                return BeanUtils.getPropertyDescriptors(clazz);
            }

            @Override
            public PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName) {
                return BeanUtils.getPropertyDescriptor(clazz, propertyName);
            }
        };


    }

    @After
    public void after() {
        logger.info("测试结束");
    }

    @Test
    public void test() {

    }

    public static class People {
        private String name;
        private Integer age;
        private Integer sex;
    }

    public static class Student extends People {

    }
}
