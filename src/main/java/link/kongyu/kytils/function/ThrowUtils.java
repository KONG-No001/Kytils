package link.kongyu.kytils.function;

public class ThrowUtils {

    public static <T> T apply(ThrowFunction<T> throwFunction) {
        return apply(throwFunction, new RuntimeException());
    }

    public static <T> T apply(ThrowFunction<T> throwFunction, String message) {
        return apply(throwFunction, new RuntimeException(message));
    }

    public static <T> T apply(ThrowFunction<T> throwFunction, RuntimeException runtimeException) {
        try {
            return throwFunction.accept();
        }
        catch (Exception cause) {
            runtimeException.initCause(cause);
            throw runtimeException;
        }
    }

    public static <T> T ignored(ThrowFunction<T> throwFunction) {
        try {
            return throwFunction.accept();
        }
        catch (Exception ignored) {
            return null;
        }
    }
}
