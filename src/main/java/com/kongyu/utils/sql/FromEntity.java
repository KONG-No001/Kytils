package com.kongyu.utils.sql;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/23
 */
public class FromEntity implements SqlEntity {
    private final String table;
    private final String sql;
    private final QuerySqlEntity entity;

    FromEntity(String table) {
        this.table = table;
        this.sql = null;
        this.entity = null;
    }

     FromEntity(String table, QuerySqlEntity entity) {
        this.table = table;
        this.sql = null;
        this.entity = entity;
    }

     FromEntity(String table, String sql) {
        this.table = table;
        this.sql = sql;
        this.entity = null;
    }

    @Override
    public String sql() {
        if (entity != null) return "( " + entity.sql() + " ) " + table;
        if (sql != null) {
            return "( " + sql + " ) " + table;
        } else {
            return table;
        }
    }
}
