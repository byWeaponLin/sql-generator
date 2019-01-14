package com.weaponlin.dsl.operand.operator;

import com.weaponlin.dsl.enums.ArithmeticOperator;
import com.weaponlin.dsl.operand.transform.TransformOperand;

/**
 * TODO 算术运算表达式
 */
@Deprecated
public class ArithmeticOperatorOperand extends OperatorOperand {
    private static final long serialVersionUID = 2321512695610163641L;

    private TransformOperand left;
    private ArithmeticOperator operator;
    private TransformOperand right;

    public ArithmeticOperatorOperand(TransformOperand left, ArithmeticOperator operator, TransformOperand right) {
        super(operator.getOperator());
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String toString(boolean hasAlias) {
        return left.toString(false) + operator.getOperator() + right.toString(false);
    }

    @Override
    public String toString() {
        return toString(false);
    }
}
