package link.kongyu.kytils.log.loggerFactory;

import link.kongyu.kytils.log.ILogger;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/29
 */
public interface ILoggerFactory<T> {
    ILogger getLogger(T logger);
}
