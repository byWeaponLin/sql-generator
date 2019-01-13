package com.weaponlin.dsl.operand.transform;

import com.weaponlin.dsl.enums.Aggregate;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO 聚合函数
 * sum、max、min、avg、count
 */
public class AggregateFunctionOperand extends FunctionOperand {
    private static final long serialVersionUID = 161277410730688617L;

    private String alias;

    private Aggregate aggregate;

    private ColumnOperand column;

    AggregateFunctionOperand(ColumnOperand column, Aggregate aggregate) {
        super(column.getName());
        this.column = column;
        this.aggregate = aggregate;
    }

    public static AggregateFunctionOperand max(String column) {
        return max(ColumnOperand.column(column));
    }

    public static AggregateFunctionOperand max(ColumnOperand column) {
        return new AggregateFunctionOperand(column, Aggregate.MAX);
    }

    public static AggregateFunctionOperand min(String column) {
        return min(ColumnOperand.column(column));
    }

    public static AggregateFunctionOperand min(ColumnOperand column) {
        return new AggregateFunctionOperand(column, Aggregate.MIN);
    }

    public static AggregateFunctionOperand avg(String column) {
        return avg(ColumnOperand.column(column));
    }

    public static AggregateFunctionOperand avg(ColumnOperand column) {
        return new AggregateFunctionOperand(column, Aggregate.AVG);
    }

    public static AggregateFunctionOperand sum(String column) {
        return sum(ColumnOperand.column(column));
    }

    public static AggregateFunctionOperand sum(ColumnOperand column) {
        return new AggregateFunctionOperand(column, Aggregate.SUM);
    }

    public static AggregateFunctionOperand count(String column) {
        return count(ColumnOperand.column(column));
    }

    public static AggregateFunctionOperand count(ColumnOperand column) {
        return new AggregateFunctionOperand(column, Aggregate.COUNT);
    }


    public AggregateFunctionOperand as(String alias) {
        this.alias = alias;
        return this;
    }

    private String fullFunction() {
        return aggregate.getFunctionName() + "(" + column.toString(false) + ")";
    }

    @Override
    public String toString(boolean hasAlias) {
        return fullFunction() + (hasAlias && StringUtils.isNotBlank(alias) ? " AS " + alias : "");
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
