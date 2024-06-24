package com.kongyu.utils.sql;

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
        StringBuilder sb = new StringBuilder();
        for (SqlEntity entity : entities) {
            sb.append(entity.sql());
        }
        return sb.toString();
    }
}
