package com.weaponlin.dsl.operand.expression;

import com.weaponlin.dsl.operand.transform.TransformOperand;
import com.weaponlin.dsl.enums.CompareOperator;

public class BetweenExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = -5917770564059569617L;

    private TransformOperand left;
    private TransformOperand value1;
    private TransformOperand value2;

    public BetweenExpressionOperand(TransformOperand left, TransformOperand value1, TransformOperand value2) {
        super(CompareOperator.BETWEEN_AND.name());
        this.left = left;
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString(boolean hasAlias) {
        // TODO add alias
        return left.toString(false) + " between " + value1.toString(false) + " and " + value2.toString(false);
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
