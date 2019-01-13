package com.weaponlin.dsl.design.operator;

import com.weaponlin.dsl.enums.CompareOperator;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO 比较符
 */
public class ComparatorOperatorOperand extends OperatorOperand {
    private static final long serialVersionUID = 638583525725789675L;

    private Object value;

    private CompareOperator compareOperator;

    private String alias;

    ComparatorOperatorOperand(String name, CompareOperator compareOperator, Object value) {
        super(name);
        this.compareOperator = compareOperator;
        this.value = value;
    }

    public static ComparatorOperatorOperand gt(Object value) {
        return new ComparatorOperatorOperand("", CompareOperator.GT, value);
    }

    public static ComparatorOperatorOperand ge(Object value) {
        return new ComparatorOperatorOperand("", CompareOperator.GE, value);
    }

    public static ComparatorOperatorOperand eq(Object value) {
        return new ComparatorOperatorOperand("", CompareOperator.EQ, value);
    }

    public static ComparatorOperatorOperand neq(Object value) {
        return new ComparatorOperatorOperand("", CompareOperator.NEQ, value);
    }

    public static ComparatorOperatorOperand lt(Object value) {
        return new ComparatorOperatorOperand("", CompareOperator.LT, value);
    }

    public static ComparatorOperatorOperand le(Object value) {
        return new ComparatorOperatorOperand("", CompareOperator.LE, value);
    }

    public ComparatorOperatorOperand as(String alias) {
        this.alias = alias;
        return this;
    }


    @Override
    public String toString(boolean hasAlias) {
        return compareOperator.name() + " ? " + (hasAlias && StringUtils.isNotBlank(alias) ? " as " + alias : "");
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
