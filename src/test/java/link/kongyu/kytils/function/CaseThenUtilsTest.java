package link.kongyu.kytils.function;

import link.kongyu.kytils.vo.People;
import link.kongyu.kytils.vo.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaseThenUtilsTest {
    static Logger logger = LoggerFactory.getLogger(CaseThenUtilsTest.class);

    @Before
    public void before() {
        logger.info("测试开始");
    }

    @After
    public void after() {
        logger.info("测试结束");
    }

    @Test
    public void testNotNull() {
        People people = new People();
        WhenUtils.notNull(people, t -> {
            logger.info("People: {}", t.toString());
        });

        Student student = WhenUtils.notNull(people, t -> {
            logger.info("初始化 student");
            return new Student();
        }).orElse(null);

        logger.info("Student: {}", student);
    }

}
