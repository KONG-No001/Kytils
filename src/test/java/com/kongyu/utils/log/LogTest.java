package com.kongyu.utils.log;

import com.kongyu.utils.SegmentedUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LogTest {
    Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void loggerTest() {
        ProgressLogger progress = new ProgressLogger(logger, 1000, 0);
        for (int i = 0; i < 1000; i++) {
            progress.increment();
        }
    }

    @Test
    public void loggerTest2() {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            data.add(i);
        }
        ProgressLogger progress = new ProgressLogger(logger, data.size(), 500);
        for (Integer datum : data) {
            progress.increment();
            if (datum % 100 == 0) {
                try {
                    progress.warn("warn: {}", datum);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void loggerTest3() {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            data.add(i);
        }
        ProgressLogger progress = new ProgressLogger(logger, data.size(), 500);

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        SegmentedUtils.longitudinalSegmented(10, data).forEach(sublist -> futures.add(CompletableFuture.runAsync(() -> {
            for (Integer integer : sublist) {
                progress.increment("increment: {}", integer);
                try {
                    if (integer % 100 == 0) {
                        progress.warn("warn: {}", integer);
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    progress.error("error: {}", e.getMessage());
                }
            }
        })));
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
}