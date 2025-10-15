package link.kongyu.kytils.sql;

import java.util.List;

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

    @Override
    public StringBuilder sql(StringBuilder sb) {
        return sb.append(express);
    }
}
