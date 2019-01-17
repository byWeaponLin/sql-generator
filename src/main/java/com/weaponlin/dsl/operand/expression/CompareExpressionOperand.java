package com.weaponlin.dsl.operand.expression;

import com.weaponlin.dsl.operand.transform.TransformOperand;
import com.weaponlin.dsl.enums.CompareOperator;
import lombok.Getter;

public class CompareExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = 2554618149615907450L;

    private TransformOperand left;
    private TransformOperand right;

    @Getter
    private CompareOperator operator;


    public CompareExpressionOperand(TransformOperand left, CompareOperator operator, TransformOperand right) {
        super(operator.name());
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String toString(boolean hasAlias) {
        return left.toString(false)
                + operator.getComparator()
                + right.toString(false)
                + getDecoratedAlias(hasAlias);
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
