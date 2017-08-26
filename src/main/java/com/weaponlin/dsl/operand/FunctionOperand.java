package com.weaponlin.dsl.operand;


import com.weaponlin.dsl.operator.FunctionOperator;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class FunctionOperand extends TransformOperand {
    private static final long serialVersionUID = 3889728733609769621L;

    ColumnOperand column;

    private FunctionOperand(ColumnOperand column, FunctionOperator function) {
        super(function.getOperator());
        this.column = column;
    }

    public static FunctionOperand min(ColumnOperand operand) {
        return new FunctionOperand(operand, FunctionOperator.MINIMUM);
    }

    public static FunctionOperand max(ColumnOperand operand) {
        return new FunctionOperand(operand, FunctionOperator.MAXIMUM);
    }

    public static FunctionOperand sum(ColumnOperand operand) {
        return new FunctionOperand(operand, FunctionOperator.SUM);
    }

    public static FunctionOperand avg(ColumnOperand operand) {
        return new FunctionOperand(operand, FunctionOperator.AVG);
    }

    public static FunctionOperand count(ColumnOperand operand) {
        return new FunctionOperand(operand, FunctionOperator.COUNT);
    }

    @Override
    public String toString(boolean hasAlias) {
        return super.name + "(" + column.name + ")" + getAlias(hasAlias);
    }

    private String getAlias(boolean hasAlias) {
        if (hasAlias) {
            String alias = isNotBlank(column.getAliasName()) ? column.getAliasName() : column.name;
            return " AS " + (super.name + "_" + alias).toLowerCase();
        } else {
            return "";
        }
    }

}
