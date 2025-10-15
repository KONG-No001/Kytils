package link.kongyu.kytils.sql;

import java.util.List;

/**
 * Sql实体顶层框架
 *
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/22
 */
public interface SqlEntity {
    String sql();

    StringBuilder sql(StringBuilder sb);
}
