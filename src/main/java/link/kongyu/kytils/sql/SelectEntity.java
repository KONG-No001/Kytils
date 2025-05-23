package link.kongyu.kytils.sql;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/23
 */
public class SelectEntity implements SqlEntity {
    private final String express;

    public SelectEntity(String express) {
        this.express = express;
    }

    @Override
    public String sql() {
        return express;
    }
}
