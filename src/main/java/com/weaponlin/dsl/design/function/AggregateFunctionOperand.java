package com.weaponlin.dsl.design.function;

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

    AggregateFunctionOperand(String name, Aggregate aggregate) {
        super(name);
        this.aggregate = aggregate;
    }

    public static AggregateFunctionOperand max(String column) {
        return new AggregateFunctionOperand(column, Aggregate.MAX);
    }

    public static AggregateFunctionOperand min(String column) {
        return new AggregateFunctionOperand(column, Aggregate.MIN);
    }

    public static AggregateFunctionOperand avg(String column) {
        return new AggregateFunctionOperand(column, Aggregate.AVG);
    }

    public static AggregateFunctionOperand sum(String column) {
        return new AggregateFunctionOperand(column, Aggregate.SUM);
    }

    public static AggregateFunctionOperand count(String column) {
        return new AggregateFunctionOperand(column, Aggregate.COUNT);
    }

    public AggregateFunctionOperand as(String alias) {
        this.alias = alias;
        return this;
    }

    private String fullFunction() {
        return aggregate.name() + "(" + super.name + ")";
    }

    @Override
    public String toString(boolean hasAlias) {
        return fullFunction() + (hasAlias && StringUtils.isNotBlank(alias) ? " as " + alias : "");
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
