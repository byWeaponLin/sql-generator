package com.weaponlin.dsl.operand.transform;

import com.weaponlin.dsl.enums.Aggregate;
import com.weaponlin.dsl.operand.expression.ExpressionOperand;
import com.weaponlin.dsl.operand.expression.FunctionExpressionOperand;
import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.weaponlin.dsl.operand.transform.ColumnOperand.*;

/**
 * TODO 聚合函数
 * sum、max、min、avg、count
 */
public class AggregateFunctionOperand extends FunctionOperand {
    private static final long serialVersionUID = 161277410730688617L;

    private String alias;

    private Aggregate aggregate;

    private TransformOperand operand;

    AggregateFunctionOperand(TransformOperand operand, Aggregate aggregate) {
        super(operand.getName());
        this.operand = operand;
        this.aggregate = aggregate;
    }

    public static AggregateFunctionOperand max(String column) {
        checkArgument(StringUtils.isNotBlank(column), "column can not be blank");
        return max(name(column));
    }

    public static AggregateFunctionOperand max(TransformOperand operand) {
        checkNotNull(operand, "operand can not be null");
        return new AggregateFunctionOperand(operand, Aggregate.MAX);
    }

    public static AggregateFunctionOperand min(String column) {
        checkArgument(StringUtils.isNotBlank(column), "column can not be blank");
        return min(name(column));
    }

    public static AggregateFunctionOperand min(TransformOperand operand) {
        checkNotNull(operand, "operand can not be null");
        return new AggregateFunctionOperand(operand, Aggregate.MIN);
    }

    public static AggregateFunctionOperand avg(String column) {
        checkArgument(StringUtils.isNotBlank(column), "column can not be blank");
        return avg(name(column));
    }

    public static AggregateFunctionOperand avg(TransformOperand operand) {
        checkNotNull(operand, "operand can not be null");
        return new AggregateFunctionOperand(operand, Aggregate.AVG);
    }

    public static AggregateFunctionOperand sum(String column) {
        checkArgument(StringUtils.isNotBlank(column), "column can not be blank");
        return sum(name(column));
    }

    public static AggregateFunctionOperand sum(TransformOperand operand) {
        checkNotNull(operand, "operand can not be null");
        return new AggregateFunctionOperand(operand, Aggregate.SUM);
    }

    public static AggregateFunctionOperand count(String column) {
        checkArgument(StringUtils.isNotBlank(column), "column can not be blank");
        return count(name(column));
    }

    public static AggregateFunctionOperand count(TransformOperand operand) {
        checkNotNull(operand, "operand can not be null");
        return new AggregateFunctionOperand(operand, Aggregate.COUNT);
    }

    public AggregateFunctionOperand as(String alias) {
        this.alias = alias;
        return this;
    }

    private String fullFunction() {
        return aggregate.getFunctionName() + "(" + operand.toString(false) + ")";
    }

    @Override
    public String toString(boolean hasAlias) {
        return fullFunction() + (hasAlias && StringUtils.isNotBlank(alias) ? " AS " + alias : "");
    }

    @Override
    public String toString() {
        return toString(true);
    }

    @Override
    public ExpressionOperand toExpression() {
        return new FunctionExpressionOperand(aggregate, this);
    }
}
