package link.kongyu.kytils.function;

@FunctionalInterface
public interface ThrowFunction<T> {
    T accept() throws Exception;
}
