package link.kongyu.kytils.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * WhereEntity
 *
 * @author Kongyu
 * @version v1.0
 * @since 2023-10-05
 */
public class ConditionEntity implements SqlEntity {
    private final String logicalOperator;
    private final String expression;
    private final List<ConditionEntity> conditions;

    public ConditionEntity(String logicalOperator, String expression) {
        this.logicalOperator = logicalOperator;
        this.expression = expression;
        this.conditions = new ArrayList<>();
    }

    public ConditionEntity(String logicalOperator, List<ConditionEntity> conditions) {
        this.logicalOperator = logicalOperator;
        this.expression = null;
        this.conditions = conditions;
    }

    public static String buildConditionClause(List<ConditionEntity> conditions) {
        if (conditions == null || conditions.isEmpty()) { return ""; }

        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (ConditionEntity condition : conditions) {
            String s = condition.sql();
            if (s != null && !s.isEmpty()) {
                if (first) {
                    // 删除第一个逻辑运算符
                    String logicalOperator = condition.getLogicalOperator();
                    if (logicalOperator != null && !logicalOperator.isEmpty()) {
                        s = s.substring(logicalOperator.length() + 1).trim();
                    }
                    first = false;
                }
                sb.append(s);
                if (conditions.indexOf(condition) < conditions.size() - 1) {
                    sb.append(" ");
                }
            }
        }

        // 仅当有内容时才添加括号
        if (sb.length() > 0 && conditions.size() > 1) {
            sb.insert(0, "(").append(")");
        }

        return sb.toString();
    }

    public ConditionEntity and(String express) {
        return this.addCondition("AND", express);
    }

    public ConditionEntity or(String express) {
        return this.addCondition("OR", express);
    }

    public ConditionEntity addCondition(String logicalOperator, String express) {
        conditions.add(new ConditionEntity(logicalOperator, express));
        return this;
    }

    public ConditionEntity addCondition(ConditionEntity entity) {
        conditions.add(entity);
        return this;
    }

    public ConditionEntity or(Function<ConditionEntity, ConditionEntity> entityFun) {
        return this.addCondition("OR", entityFun);
    }

    public ConditionEntity not(Function<ConditionEntity, ConditionEntity> entityFun) {
        return this.addCondition("NOT", entityFun);
    }

    public ConditionEntity addCondition(String logicalOperator, Function<ConditionEntity, ConditionEntity> entityFun) {
        return this.addCondition(entityFun.apply(new ConditionEntity(logicalOperator, "")));
    }

    public String getLogicalOperator() {
        return logicalOperator;
    }

    public String getExpression() {
        return expression;
    }

    public List<ConditionEntity> getConditions() {
        return conditions;
    }

    /**
     * WHERE clause
     */
    @Override
    public String sql() {
        String logicalOperator = this.logicalOperator == null ? "AND" : this.logicalOperator;
        String express = this.expression;

        // 如果条件列表不为空，则构建条件子句
        if (!conditions.isEmpty()) {
            express = buildConditionClause(conditions);
        }

        // 如果 express 为 null 或空，则返回 null
        if (express == null || express.isEmpty()) { return null; }

        // 生成并返回最终的 SQL 字符串
        return logicalOperator + " ( " + express + " )";
    }
}
