package com.weaponlin.dsl.operand.expression;

import com.weaponlin.dsl.operand.transform.TransformOperand;
import com.weaponlin.dsl.enums.CompareOperator;

public class CompareExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = 2554618149615907450L;

    private TransformOperand left;
    private TransformOperand right;

    private CompareOperator operator;


    public CompareExpressionOperand(TransformOperand left, CompareOperator operator, TransformOperand right) {
        super(operator.name());
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String toString(boolean hasAlias) {
        // TODO add alias
        return left.toString(false) + operator.getComparator() + right.toString(false);
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
