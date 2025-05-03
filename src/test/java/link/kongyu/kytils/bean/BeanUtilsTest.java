package link.kongyu.kytils.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.util.Map;

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
        People people = new People();

        myBeanUtils.setProperty(people, "name", "zhang3");
        myBeanUtils.setProperty(people, "age", 18);
        myBeanUtils.setProperty(people, "sex", 1);

        logger.info("People: {}", people);

        Map<String, Object> toMap = myBeanUtils.beanToMap(people);

        logger.info("toMap: {}", toMap);

        Student student = myBeanUtils.mapToBean(toMap, Student.class);
        student.setClsNo("C10086");

        logger.info("toBean: {}", student);
    }

    @Data
    public static class People {
        protected String name;
        protected Integer age;
        protected Integer sex;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Student extends People {
        private String clsNo;
    }
}
