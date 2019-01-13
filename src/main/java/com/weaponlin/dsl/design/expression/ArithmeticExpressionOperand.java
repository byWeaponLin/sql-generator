package com.weaponlin.dsl.design.expression;

import com.weaponlin.dsl.design.transform.TransformOperand;
import com.weaponlin.dsl.enums.ArithmeticOperator;

public class ArithmeticExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = 5023260839181426657L;

    private TransformOperand left;
    private TransformOperand right;
    private ArithmeticOperator operator;

    public ArithmeticExpressionOperand(TransformOperand left, ArithmeticOperator operator, TransformOperand right) {
        super(operator.name());
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String toString(boolean hasAlias) {
        // TODO
        return null;
    }
}
