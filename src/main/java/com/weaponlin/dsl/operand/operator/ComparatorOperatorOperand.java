package com.weaponlin.dsl.operand.operator;

import com.weaponlin.dsl.enums.CompareOperator;
import com.weaponlin.dsl.operand.transform.VariableOperand;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO 比较符
 */
public class ComparatorOperatorOperand extends OperatorOperand {
    private static final long serialVersionUID = 638583525725789675L;

    private VariableOperand value;

    private CompareOperator compareOperator;

    private String alias;

    ComparatorOperatorOperand(String name, CompareOperator compareOperator, VariableOperand value) {
        super(name);
        this.compareOperator = compareOperator;
        this.value = value;
    }

    public static ComparatorOperatorOperand gt(VariableOperand value) {
        return new ComparatorOperatorOperand("", CompareOperator.GT, value);
    }

    public static ComparatorOperatorOperand ge(VariableOperand value) {
        return new ComparatorOperatorOperand("", CompareOperator.GE, value);
    }

    public static ComparatorOperatorOperand eq(VariableOperand value) {
        return new ComparatorOperatorOperand("", CompareOperator.EQ, value);
    }

    public static ComparatorOperatorOperand neq(VariableOperand value) {
        return new ComparatorOperatorOperand("", CompareOperator.NEQ, value);
    }

    public static ComparatorOperatorOperand lt(VariableOperand value) {
        return new ComparatorOperatorOperand("", CompareOperator.LT, value);
    }

    public static ComparatorOperatorOperand le(VariableOperand value) {
        return new ComparatorOperatorOperand("", CompareOperator.LE, value);
    }

    public ComparatorOperatorOperand as(String alias) {
        this.alias = alias;
        return this;
    }


    @Override
    public String toString(boolean hasAlias) {
        return compareOperator.getComparator() + " ? " + (hasAlias && StringUtils.isNotBlank(alias) ? " as " + alias : "");
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
