package com.kongyu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/29
 */
public class App {
    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Class<?> aClass = Zhang3.class;

        Class<? extends People> aClass1 = Stream.of(People.class, Student.class).filter(t -> t.isAssignableFrom(aClass)).min((o1, o2) -> {
            if (o1 == o2) { return 0; }
            return o1.isAssignableFrom(o2) ? 1 : -1;
        }).orElse(null);

        System.out.println(aClass1);
    }

    public static class People {
    }

    public static class Student extends People {
    }

    public static class Zhang3 extends Student {

    }

}
