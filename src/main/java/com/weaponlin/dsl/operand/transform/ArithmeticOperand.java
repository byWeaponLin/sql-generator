package com.weaponlin.dsl.operand.transform;

import com.weaponlin.dsl.enums.ArithmeticOperator;
import com.weaponlin.dsl.operand.expression.ExpressionOperand;

/**
 * TODO
 */
public class ArithmeticOperand extends TransformOperand {
    private static final long serialVersionUID = -3912432990109932328L;

    private TransformOperand left;
    private ArithmeticOperator operator;
    private TransformOperand right;


    ArithmeticOperand(TransformOperand left, ArithmeticOperator operator, TransformOperand right) {
        super(operator.name());
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public ExpressionOperand toExpression() {
        return null;
    }

    @Override
    public String toString(boolean hasAlias) {
        return null;
    }
}
