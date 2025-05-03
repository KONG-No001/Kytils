package link.kongyu.kytils.function;

import link.kongyu.kytils.object.ObjectUtils;

import java.util.function.Consumer;
import java.util.function.Function;

public class CaseThenUtils {

    public static <T> void asTrue(T t, boolean b, Consumer<T> consumer) {
        if (b) { consumer.accept(t); }
    }

    public static <T, R> R asTrue(T t, boolean b, Function<T, R> function) {
        if (b) { return function.apply(t); }
        return null;
    }

    public static <T> void notNull(T t, Consumer<T> consumer) {
        asTrue(t, t != null, consumer);
    }

    public static <T, R> R notNull(T t, Function<T, R> function) {
        return asTrue(t, t != null, function);
    }

    public static <T> void isNotEmpty(T t, Consumer<T> consumer) {
        asTrue(t, !ObjectUtils.isEmpty(t), consumer);
    }

    public static <T, R> R isNotEmpty(T t, Function<T, R> function) {
        return asTrue(t, !ObjectUtils.isEmpty(t), function);
    }

    public static <T> void calculateTrue(T t, Function<T, Boolean> calculate, Consumer<T> consumer) {
        asTrue(t, calculate.apply(t), consumer);
    }

    public static <T, R> R calculateTrue(T t, Function<T, Boolean> calculate, Function<T, R> function) {
        return asTrue(t, calculate.apply(t), function);
    }
}
