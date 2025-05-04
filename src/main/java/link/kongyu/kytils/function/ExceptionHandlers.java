package link.kongyu.kytils.function;

import link.kongyu.kytils.log.ILogger;

import java.util.Optional;
import java.util.function.Function;

public class ExceptionHandlers {

    public static <T> T runSafely(ThrowingFunction<T> throwingFunction) {
        return runSafely(throwingFunction, RuntimeException::new);
    }

    public static <T> T runSafely(ThrowingFunction<T> throwingFunction, String message) {
        return runSafely(throwingFunction, cause -> new RuntimeException(message, cause));
    }

    public static <T> T runSafely(ThrowingFunction<T> throwingFunction, Function<Exception, RuntimeException> exceptionWrapper) {
        try {
            return throwingFunction.apply();
        }
        catch (Exception cause) {
            RuntimeException ex = exceptionWrapper.apply(cause);
            if (ex == null) {
                ex = new RuntimeException("Wrapped exception is null", cause);
            }
            throw ex;
        }
    }

    public static <T> Optional<T> runSilently(ThrowingFunction<T> throwingFunction) {
        return runSilently(throwingFunction, null);
    }

    public static <T> Optional<T> runSilently(ThrowingFunction<T> throwingFunction, ILogger logger) {
        try {
            return Optional.ofNullable(throwingFunction.apply());
        }
        catch (Exception cause) {
            if (logger != null) {
                logger.warn("静默执行失败 - 异常：{} - 消息：{}", cause.getClass().getSimpleName(), cause.getMessage());
            }
            return Optional.empty();
        }
    }
}
