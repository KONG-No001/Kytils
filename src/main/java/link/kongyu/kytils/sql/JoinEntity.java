package link.kongyu.kytils.sql;

import link.kongyu.kytils.sql.query.QuerySqlEntity;

import java.util.Collections;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/23
 */
public class JoinEntity implements SqlEntity {
    private final String type;
    private final String table;
    private final QuerySqlEntity entity;
    private final ConditionEntity where;

    public JoinEntity(String type, String table, ConditionEntity where) {
        this.type = type;
        this.table = table;
        this.entity = null;
        this.where = where;
    }

    public JoinEntity(String type, String table, QuerySqlEntity entity, ConditionEntity where) {
        this.type = type;
        this.table = table;
        this.entity = entity;
        this.where = where;
    }

    @Override
    public String sql() {
        return sql(new StringBuilder()).toString();
    }

    @Override
    public StringBuilder sql(StringBuilder sb) {
        String onSql = buildOnClause();
        if (entity == null) {
            return sb.append(type).append(" ").append(table).append(" ").append(onSql).append(" ");
        }
        else {
            return sb.append(type).append(" ( ").append(entity.sql()).append(" ) ").append(table).append(" ").append(onSql).append(" ");
        }
    }

    private String buildOnClause() {
        if (where == null) {
            return "";
        }

        String s = ConditionEntity.buildConditionClause(Collections.singletonList(where));
        if (!s.isEmpty()) {
            return "ON " + s;
        }

        return "";
    }
}
