package link.kongyu.kytils.sql.query;

import link.kongyu.kytils.sql.*;

import java.util.LinkedList;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/23
 */
public class SimpleQuerySqlEntity implements SqlEntity {
    private final LinkedList<SelectEntity> selects = new LinkedList<>();
    private final LinkedList<FromEntity> froms = new LinkedList<>();
    private final LinkedList<JoinEntity> joins = new LinkedList<>();
    private final LinkedList<ConditionEntity> wheres = new LinkedList<>();
    private final LinkedList<GroupByEntity> groupbys = new LinkedList<>();
    private final LinkedList<ConditionEntity> havings = new LinkedList<>();
    private final LinkedList<OrderByEntity> orderbys = new LinkedList<>();
    private boolean distinct;
    private String limit;
    private String union;

    public LinkedList<SelectEntity> getSelects() {
        return selects;
    }

    public LinkedList<FromEntity> getFroms() {
        return froms;
    }

    public LinkedList<JoinEntity> getJoins() {
        return joins;
    }

    public LinkedList<ConditionEntity> getWheres() {
        return wheres;
    }

    public LinkedList<GroupByEntity> getGroupbys() {
        return groupbys;
    }

    public LinkedList<ConditionEntity> getHavings() {
        return havings;
    }

    public LinkedList<OrderByEntity> getOrderbys() {
        return orderbys;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setUnion(String union) {
        this.union = union;
    }

    @Override
    public String sql() {
        StringBuilder sb = new StringBuilder();

        // SELECT clause
        if (!selects.isEmpty()) {
            sb.append("SELECT ");
            if (distinct) sb.append("DISTINCT ");
            for (SelectEntity select : selects) {
                sb.append(select.sql());
                if (selects.indexOf(select) < selects.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ");
        }

        // FROM clause
        if (!froms.isEmpty()) {
            sb.append("FROM ");
            for (FromEntity from : froms) {
                sb.append(from.sql());
                if (froms.indexOf(from) < froms.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ");
        }

        // JOIN clause
        if (!joins.isEmpty()) {
            for (JoinEntity join : joins) {
                sb.append(join.sql()).append(" ");
            }
        }

        // WHERE clause
        if (!wheres.isEmpty()) {
            String s = ConditionEntity.buildConditionClause(wheres);
            if (!s.isEmpty()) {
                sb.append("WHERE ").append(s);
            }
            sb.append(" ");
        }

        // GROUP BY clause
        if (!groupbys.isEmpty()) {
            sb.append("GROUP BY ");
            for (GroupByEntity groupby : groupbys) {
                sb.append(groupby.sql());
                if (groupbys.indexOf(groupby) < groupbys.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ");
        }

        // HAVING clause
        if (!havings.isEmpty()) {
            String s = ConditionEntity.buildConditionClause(havings);
            if (!s.isEmpty()) {
                sb.append("HAVING ").append(s).append(" ");
            }
        }

        // ORDER BY clause
        if (!orderbys.isEmpty()) {
            sb.append("ORDER BY ");
            for (OrderByEntity orderby : orderbys) {
                sb.append(orderby.sql());
                if (orderbys.indexOf(orderby) < orderbys.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ");
        }

        // LIMIT clause
        if (limit != null) {
            sb.append("LIMIT ").append(limit).append(" ");
        }

        // UNION clause
        if (union != null) {
            sb.append(union).append(" ");
        }

        return sb.toString();
    }
}
