package com.kongyu.utils.sql;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/23
 */
public class OrderByEntity implements SqlEntity {
    private final String express;
    private final String direction;

    OrderByEntity(String express) {
        this.express = express;
        this.direction = null;
    }

    OrderByEntity(String express, String direction) {
        this.express = express;
        this.direction = direction;
    }

    @Override
    public String sql() {
        if (direction == null) {
            return express;
        } else {
            return express + " " + direction;
        }
    }
}
