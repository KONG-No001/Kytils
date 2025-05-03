package link.kongyu.kytils.map;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/28
 */
public class MapException extends RuntimeException {
    public MapException(String message) {
        super(message);
    }

    public MapException(String message, Throwable cause) {
        super(message, cause);
    }
}
