package link.kongyu.kytils.function;

import link.kongyu.kytils.object.ObjectUtils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class WhenUtils {

    public static <T> void ifTrue(T input, boolean b, Consumer<T> action) {
        if (b) {
            action.accept(input);
        }
    }

    public static <T, R> Optional<R> ifTrue(T input, boolean b, Function<T, R> action) {
        if (b) {
            return Optional.ofNullable(action.apply(input));
        }
        return Optional.empty();
    }

    public static <T> void ifTrue(T input, Function<T, Boolean> predicate, Consumer<T> action) {
        ifTrue(input, Boolean.TRUE.equals(predicate.apply(input)), action);
    }

    public static <T, R> Optional<R> ifTrue(T input, Function<T, Boolean> predicate, Function<T, R> action) {
        return ifTrue(input, Boolean.TRUE.equals(predicate.apply(input)), action);
    }

    public static <T> void notNull(T input, Consumer<T> action) {
        ifTrue(input, input != null, action);
    }

    public static <T, R> Optional<R> notNull(T input, Function<T, R> action) {
        return ifTrue(input, input != null, action);
    }

    public static <T> void isNotEmpty(T input, Consumer<T> action) {
        ifTrue(input, !ObjectUtils.isNullOrEmpty(input), action);
    }

    public static <T, R> Optional<R> isNotEmpty(T input, Function<T, R> action) {
        return ifTrue(input, !ObjectUtils.isNullOrEmpty(input), action);
    }
}
