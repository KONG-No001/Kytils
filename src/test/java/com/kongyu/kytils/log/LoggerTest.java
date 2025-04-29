package com.kongyu.kytils.log;

import com.kongyu.kytils.SegmentedUtils;
import com.kongyu.kytils.log.loggerFactory.MyLoggerFactory;
import com.kongyu.kytils.log.progress.IProgressLogger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/29
 */
public class LoggerTest {
    static Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Before
    public void before() {
        logger.info("测试开始");
        logger.info("初始化 MyLoggerFactory");
        MyLoggerFactory.putLoggerFactory(Logger.class, l -> new ILogger() {
            @Override
            public void debug(String format, Object... arguments) {
                l.debug(format, arguments);
            }

            @Override
            public void info(String format, Object... arguments) {
                l.info(format, arguments);
            }

            @Override
            public void warn(String format, Object... arguments) {
                l.warn(format, arguments);
            }

            @Override
            public void error(String format, Object... arguments) {
                l.error(format, arguments);
            }
        });
    }

    @After
    public void after() {
        logger.info("测试结束");
    }

    @Test
    public void loggerTest1() {
        logger.info("loggerTest1 测试开始");
        int length = 10;
        int cd = 0;
        IProgressLogger progressLogger = MyLoggerFactory.getProgressLogger(logger, length, cd);
        for (int i = 0; i < length; i++) {
            progressLogger.increment();
        }
    }

    @Test
    public void loggerTest2() {
        logger.info("loggerTest2 测试开始");
        int length = 100;
        int cd = 0;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            data.add(i);
        }
        IProgressLogger progressLogger = MyLoggerFactory.getProgressLogger(logger, length, cd);
        for (Integer datum : data) {
            try {
                if (datum != 0 && datum % 10 == 0) {
                    progressLogger.warn("warn: {} 是10的倍数", datum);
                }
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            progressLogger.increment();
        }
    }

    @Test
    public void loggerTest3() {
        logger.info("loggerTest3 测试开始");
        int length = 100;
        int cd = 500;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            data.add(i);
        }

        IProgressLogger progressLogger = MyLoggerFactory.getProgressLogger(logger, length, cd);
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        SegmentedUtils.longitudinalSegmented(10, data).forEach(sublist -> futures.add(CompletableFuture.runAsync(() -> {
            for (Integer integer : sublist) {
                try {
                    if (integer != 0 && integer % 10 == 0) {
                        progressLogger.warn("warn: {} 是10的倍数", integer);
                    }
                    Thread.sleep(100);
                    if (integer % 9 == 0) {
                        System.out.println(0 / 0);
                    }
                }
                catch (Exception e) {
                    progressLogger.error("error: {}", e.getMessage());
                }
                progressLogger.increment("窗口: {}", integer);
            }
        })));
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
}
