package link.kongyu.kytils.function;

@FunctionalInterface
public interface ThrowingFunction<T> {
    T apply() throws Exception;
}
