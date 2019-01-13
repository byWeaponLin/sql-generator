package com.weaponlin.dsl.operand.expression;

import com.weaponlin.dsl.operand.transform.TransformOperand;
import com.weaponlin.dsl.enums.Aggregate;

public class FunctionExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = -7608252715501917810L;

    private TransformOperand operand;

    private Aggregate aggregate;

    public FunctionExpressionOperand(Aggregate aggregate, TransformOperand operand) {
        super(aggregate.name());
        this.operand = operand;
    }

    @Override
    public String toString(boolean hasAlias) {
        return null;
    }
}
