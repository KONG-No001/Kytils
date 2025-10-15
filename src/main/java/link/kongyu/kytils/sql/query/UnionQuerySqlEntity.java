package link.kongyu.kytils.sql.query;

import link.kongyu.kytils.sql.SqlEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/23
 */
public class UnionQuerySqlEntity implements SqlEntity {
    List<SqlEntity> entities = new ArrayList<>();

    @Override
    public String sql() {
        return sql(new StringBuilder()).toString();
    }

    @Override
    public StringBuilder sql(StringBuilder sb) {
        for (SqlEntity entity : entities) {
            sb.append(entity.sql());
        }
        return sb;
    }
}
