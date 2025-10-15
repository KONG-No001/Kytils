package link.kongyu.kytils.sql;

import link.kongyu.kytils.sql.query.QuerySqlEntity;

import java.util.List;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/10/15
 */
public class WithEntity implements SqlEntity {
    String table;
    String sql;
    QuerySqlEntity entity;

    public WithEntity(String table, String sql) {
        this.table = table;
        this.sql = sql;
    }

    public WithEntity(String table, QuerySqlEntity entity) {
        this.table = table;
        this.entity = entity;
    }

    public static StringBuilder buildClause(List<WithEntity> withs, StringBuilder sb) {
        if (withs == null || withs.isEmpty()) { return sb; }
        for (WithEntity with : withs) {
            with.sql(sb);
        }
        return sb;
    }

    @Override
    public String sql() {
        return sql(new StringBuilder()).toString();
    }

    @Override
    public StringBuilder sql(StringBuilder sb) {
        sb.append("WITH ").append(table).append(" AS ");
        if (entity != null) {
            sb.append("( ").append(entity.sql()).append(" ) ");
        }
        else {
            sb.append("( ").append(sql).append(" ) ");
        }
        return sb;
    }
}
