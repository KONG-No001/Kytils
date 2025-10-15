package link.kongyu.kytils.sql;

import link.kongyu.kytils.sql.query.QuerySqlEntity;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/23
 */
public class FromEntity implements SqlEntity {
    private final String table;
    private final String sql;
    private final QuerySqlEntity entity;

    public FromEntity(String table) {
        this.table = table;
        this.sql = null;
        this.entity = null;
    }

    public FromEntity(String table, QuerySqlEntity entity) {
        this.table = table;
        this.sql = null;
        this.entity = entity;
    }

    public FromEntity(String table, String sql) {
        this.table = table;
        this.sql = sql;
        this.entity = null;
    }

    @Override
    public String sql() {
        return sql(new StringBuilder()).toString();
    }

    @Override
    public StringBuilder sql(StringBuilder sb) {
        if (entity != null) {
            return sb.append("( ").append(entity.sql()).append(" ) ").append(table);
        }
        if (sql != null) {
            return sb.append("( ").append(sql).append(" ) ").append(table);
        }
        else {
            return sb.append(table);
        }

    }
}
