package link.kongyu.kytils.sql;

import link.kongyu.kytils.sql.query.QuerySqlEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/10/15
 */
public class SqlEntityTest {

    static Logger logger = LoggerFactory.getLogger(SqlEntityTest.class);

    @Before
    public void before() {
        logger.info("测试开始");
    }

    @After
    public void after() {
        logger.info("测试结束");
    }

    @Test
    public void test() {
        String sql = new QuerySqlEntity()
                .with("original_sku", os -> os
                        .select("COALESCE(NULLIF(p2.original_sku, ''), NULLIF(p.original_sku, ''), p.parent_sku) original_sku")
                        .select("IFNULL(NULLIF(p.initial_sku, ''), p.parent_sku) initial_sku")
                        .from("erp_product p")
                        .leftJoin("erp_product p2", "p2.parent_sku = p.initial_sku")
                        .where("p.parent_sku = :sku")
                        .limit("1")
                )
                .select("p.*")
                .from("erp_product p")
                .from("original_sku os")
                .where("p.parent_sku IN (os.original_sku, os.initial_sku)")
                .union("UNION")
                .select("p.*")
                .from("erp_product p")
                .from("original_sku os")
                .where("p.original_sku IN (os.original_sku, os.initial_sku)")
                .union("UNION")
                .select("p.*")
                .from("erp_product p")
                .from("original_sku os")
                .where("p.initial_sku IN (os.original_sku, os.initial_sku)")
                .sql();

        System.out.println(sql);
    }

}
