package link.kongyu.kytils.sql.query;

import link.kongyu.kytils.sql.*;

import java.util.function.Function;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/23
 */
public class QuerySqlEntity implements SqlEntity {

    private final UnionQuerySqlEntity sqlEntity = new UnionQuerySqlEntity();
    private SimpleQuerySqlEntity currentEntity = new SimpleQuerySqlEntity();

    public QuerySqlEntity() {
        sqlEntity.entities.add(currentEntity);
    }


    // ==================  with  ==================

    public QuerySqlEntity with(String table, Function<QuerySqlEntity, QuerySqlEntity> entityFun) {
        this.currentEntity.getWiths().add(new WithEntity(table, entityFun.apply(new QuerySqlEntity())));
        return this;
    }

    // ==================  select  ==================

    public QuerySqlEntity distinct() {
        return this.distinct(true);
    }

    public QuerySqlEntity distinct(boolean distinct) {
        this.currentEntity.setDistinct(distinct);
        return this;
    }


    public QuerySqlEntity select(String express) {
        this.currentEntity.getSelects().add(new SelectEntity(express));
        return this;
    }

    // ==================  from  ==================

    public QuerySqlEntity from(String table) {
        this.currentEntity.getFroms().add(new FromEntity(table));
        return this;
    }

    public QuerySqlEntity from(String table, String sql) {
        this.currentEntity.getFroms().add(new FromEntity(table, sql));
        return this;
    }

    public QuerySqlEntity from(String table, QuerySqlEntity entity) {
        this.currentEntity.getFroms().add(new FromEntity(table, entity));
        return this;
    }

    public QuerySqlEntity from(String table, Function<QuerySqlEntity, QuerySqlEntity> entityFun) {
        this.currentEntity.getFroms().add(new FromEntity(table, entityFun.apply(new QuerySqlEntity())));
        return this;
    }

    // ==================  join  ==================

    public QuerySqlEntity join(String type, String table, String on) {
        this.currentEntity.getJoins().add(new JoinEntity(type, table, new ConditionEntity("AND", on)));
        return this;
    }

    public QuerySqlEntity join(String type, String table, ConditionEntity entity) {
        this.currentEntity.getJoins().add(new JoinEntity(type, table, entity));
        return this;
    }

    public QuerySqlEntity join(String type, String table, Function<ConditionEntity, ConditionEntity> entityFun) {
        this.currentEntity.getJoins().add(new JoinEntity(type, table, entityFun.apply(new ConditionEntity("AND", ""))));
        return this;
    }

    public QuerySqlEntity join(String type, String table, String on, Function<QuerySqlEntity, QuerySqlEntity> entityFun) {
        this.currentEntity.getJoins().add(new JoinEntity(type, table, entityFun.apply(new QuerySqlEntity()), new ConditionEntity("AND", on)));
        return this;
    }

    // ==================  innerJoin  ==================

    public QuerySqlEntity innerJoin(String table, String on) {
        return join("INNER JOIN", table, on);
    }

    public QuerySqlEntity innerJoin(String table, ConditionEntity entity) {
        return join("INNER JOIN", table, entity);
    }

    public QuerySqlEntity innerJoin(String table, Function<ConditionEntity, ConditionEntity> entityFun) {
        return join("INNER JOIN", table, entityFun);
    }

    public QuerySqlEntity innerJoin(String table, String on, Function<QuerySqlEntity, QuerySqlEntity> entityFun) {
        return join("INNER JOIN", table, on, entityFun);
    }

    // ==================  straightJoin  ==================

    public QuerySqlEntity straightJoin(String table, String on) {
        return join("STRAIGHT_JOIN", table, on);
    }

    public QuerySqlEntity straightJoin(String table, ConditionEntity entity) {
        return join("STRAIGHT_JOIN", table, entity);
    }

    public QuerySqlEntity straightJoin(String table, Function<ConditionEntity, ConditionEntity> entityFun) {
        return join("STRAIGHT_JOIN", table, entityFun);
    }

    public QuerySqlEntity straightJoin(String table, String on, Function<QuerySqlEntity, QuerySqlEntity> entityFun) {
        return join("STRAIGHT_JOIN", table, on, entityFun);
    }

    // ==================  rightJoin  ==================

    public QuerySqlEntity rightJoin(String table, String on) {
        return join("RIGHT JOIN", table, on);
    }

    public QuerySqlEntity rightJoin(String table, ConditionEntity entity) {
        return join("RIGHT JOIN", table, entity);
    }

    public QuerySqlEntity rightJoin(String table, Function<ConditionEntity, ConditionEntity> entityFun) {
        return join("RIGHT JOIN", table, entityFun);
    }

    public QuerySqlEntity rightJoin(String table, String on, Function<QuerySqlEntity, QuerySqlEntity> entityFun) {
        return join("RIGHT JOIN", table, on, entityFun);
    }

    // ==================  where  ==================

    public QuerySqlEntity where(String express) {
        this.currentEntity.getWheres().add(new ConditionEntity("AND", express));
        return this;
    }

    public QuerySqlEntity where(String LogicalOperator, String express) {
        this.currentEntity.getWheres().add(new ConditionEntity(LogicalOperator, express));
        return this;
    }

    public QuerySqlEntity where(ConditionEntity entity) {
        this.currentEntity.getWheres().add(entity);
        return this;
    }

    public QuerySqlEntity where(String logicalOperator, Function<ConditionEntity, ConditionEntity> entityFun) {
        return where(entityFun.apply(new ConditionEntity(logicalOperator, "")));
    }

    // ==================  groupBy  ==================

    public QuerySqlEntity groupBy(String express) {
        this.currentEntity.getGroupbys().add(new GroupByEntity(express));
        return this;
    }

    // ==================  having  ==================

    public QuerySqlEntity having(String express) {
        this.currentEntity.getHavings().add(new ConditionEntity("AND", express));
        return this;
    }

    public QuerySqlEntity having(String logicalOperator, String express) {
        this.currentEntity.getHavings().add(new ConditionEntity(logicalOperator, express));
        return this;
    }

    public QuerySqlEntity having(ConditionEntity entity) {
        this.currentEntity.getHavings().add(entity);
        return this;
    }

    public QuerySqlEntity having(String logicalOperator, Function<ConditionEntity, ConditionEntity> entityFun) {
        return having(entityFun.apply(new ConditionEntity(logicalOperator, "")));
    }

    // ==================  orderBy  ==================

    public QuerySqlEntity orderBy(String express) {
        this.currentEntity.getOrderbys().add(new OrderByEntity(express));
        return this;
    }

    public QuerySqlEntity orderBy(String express, String direction) {
        this.currentEntity.getOrderbys().add(new OrderByEntity(express, direction));
        return this;
    }

    public QuerySqlEntity limit(String limit) {
        this.currentEntity.setLimit(limit);
        return this;
    }

    public QuerySqlEntity limit(int offset, int limit) {
        this.currentEntity.setLimit(offset + ", " + limit);
        return this;
    }

    // ==================  union  ==================

    public QuerySqlEntity union(String union) {
        this.currentEntity.setUnion(union.trim());
        this.currentEntity = new SimpleQuerySqlEntity();
        this.sqlEntity.entities.add(currentEntity);
        return this;
    }

    public QuerySqlEntity union(String union, QuerySqlEntity entity) {
        this.currentEntity.setUnion(union.trim());
        this.currentEntity = new SimpleQuerySqlEntity();
        this.sqlEntity.entities.addAll(entity.sqlEntity.entities);
        return this;
    }

    @Override
    public String sql() {
        return sql(new StringBuilder()).toString();
    }

    @Override
    public StringBuilder sql(StringBuilder sb) {
        return sqlEntity.sql(sb);
    }
}
