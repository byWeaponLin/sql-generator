package com.weaponlin.dsl.operand.expression;

import com.weaponlin.dsl.enums.CompareOperator;
import com.weaponlin.dsl.operand.transform.TransformOperand;

public class InExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = 3683824947894772826L;

    private TransformOperand left;
    private TransformOperand right;

    private CompareOperator operator;

    public InExpressionOperand(TransformOperand left, CompareOperator operator, TransformOperand right) {
        super(operator.getComparator());
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String toString(boolean hasAlias) {
        return left.toString(false) + operator.getComparator() + "(" + right.toString(false) + ")";
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
