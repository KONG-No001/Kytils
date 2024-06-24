package com.kongyu.utils.log;

import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <h2>进度条日志</h2>
 * <p>一个日志对象，输出日志时将附带进度指示。并且，如果设置了冷却时间，increment将会间隔的输出日志。</p>
 * <p>该日志对象是线程安全的。</p>
 * <br/>
 * <h3>用途：</h3>
 * <p>用于输出附带进度指示且输出频率受控的日志。</p>
 * <br/>
 * <h3>应用场景：</h3>
 * <ul>
 *     <li><b>显示进度：</b>需要显示当前进度和总进度时</li>
 *     <li><b>显示位置：</b>当需要特别提醒时，可以根据进度位置快速找到对象</li>
 * </ul>
 * <br/>
 *
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/24
 */
public class ProgressLogger {

    private final Logger logger;
    private final long total;
    private final long cd;
    private final AtomicLong count = new AtomicLong(0);
    private final AtomicLong millis = new AtomicLong(0);

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
