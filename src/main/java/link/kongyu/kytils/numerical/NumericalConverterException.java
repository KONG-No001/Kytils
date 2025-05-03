package link.kongyu.kytils.numerical;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/28
 */
public class NumericalConverterException extends RuntimeException {
    public NumericalConverterException(String message) {
        super(message);
    }

    public NumericalConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
