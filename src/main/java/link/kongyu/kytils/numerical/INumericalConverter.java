package link.kongyu.kytils.numerical;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/28
 */
public interface INumericalConverter {

    int toDecimal(int cardinality, String code);

    String toBaseCode(int cardinality, long decimal, int size);
}
