package com.kongyu.utils;

import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Luojun
 * @version v1.0.0
 * @since 2024/6/22
 */
public class LogUtils {

    public static ProgressLogger getProgress(Logger logger, int total, long cd) {
        return new ProgressLogger(logger, total, cd);
    }

    /**
     * 进度条日志
     */
    public static class ProgressLogger {
        Logger logger;
        long total;
        long cd;
        AtomicLong count = new AtomicLong(0);
        AtomicLong millis = new AtomicLong(0);

        public ProgressLogger(Logger logger, long total, long cd) {
            this.logger = logger;
            this.total = total;
            this.cd = cd;
        }

        public void increment() {
            increment(null, (Object[]) null);
        }

        public void increment(String format, Object... obj) {
            count.incrementAndGet();

            long previousMillis = millis.get();
            if (cd > 0) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - previousMillis > cd) {
                    if (millis.compareAndSet(previousMillis, currentTimeMillis)) {
                        info(format, obj);
                    }
                }
            } else {
                info(format, obj);
            }
        }

        public void debug(String format, Object... arguments) {
            logger.debug(getFormat(format), getObjects(arguments));
        }

        public void info(String format, Object... arguments) {
            logger.info(getFormat(format), getObjects(arguments));
        }

        public void warn(String format, Object... arguments) {
            logger.warn(getFormat(format), getObjects(arguments));
        }

        public void error(String format, Object... arguments) {
            logger.error(getFormat(format), getObjects(arguments));
        }

        private String getFormat(String format) {
            return "{}/{}" + (format == null ? "" : "-" + format);
        }

        private Object[] getObjects(Object[] arguments) {
            Object[] defaultObjects = new Object[]{count.get(), total};
            if (arguments == null || arguments.length == 0) {
                return defaultObjects;
            }

            Object[] objects = new Object[arguments.length + 2];
            objects[0] = defaultObjects[0];
            objects[1] = defaultObjects[1];
            System.arraycopy(arguments, 0, objects, 2, arguments.length);
            return objects;
        }

    }
}


