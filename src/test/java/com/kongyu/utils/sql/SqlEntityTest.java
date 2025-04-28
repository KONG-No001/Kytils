package com.kongyu.utils.sql;

import com.kongyu.utils.sql.query.QuerySqlEntity;
import org.junit.Test;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/24
 */
public class SqlEntityTest {

    @Test
    public void test() {
        QuerySqlEntity querySqlEntity = new QuerySqlEntity();
        querySqlEntity.distinct()
                .select("id")
                .from("table")
                .join("LEFT JOIN", "table2", w -> w.where("table.id = table2.id").where("OR", "table.id = 1"))
                .join("LEFT JOIN", "table3", w -> w.where("table.id = table3.id").where("OR", "table3.id = 1"))
                .where("AND", w -> w.where("table.id = 1").where("OR", "table2.id = 2"))
                .where("AND", w -> w.where("table.id = 3").where("OR", "table2.id = 4"))
                .groupBy("table.id")
                .having("AND", w -> w.where("table.id = 1").where("OR", "table2.id = 2"))
                .orderBy("table.id", "DESC")
                .limit("10")
        ;

        System.out.println(querySqlEntity.sql());
    }

}
