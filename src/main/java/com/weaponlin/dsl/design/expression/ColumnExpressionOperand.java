package com.weaponlin.dsl.design.expression;

import com.weaponlin.dsl.design.transform.TransformOperand;

public class ColumnExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = -226655892689191795L;

    private TransformOperand operand;

    public ColumnExpressionOperand(TransformOperand operand) {
        // TODO get name
        super("");
    }


    @Override
    public String toString(boolean hasAlias) {
        //  TODO
        return null;
    }
}
