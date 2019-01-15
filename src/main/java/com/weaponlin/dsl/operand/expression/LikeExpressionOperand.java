package com.weaponlin.dsl.operand.expression;

import com.weaponlin.dsl.enums.CompareOperator;
import com.weaponlin.dsl.operand.transform.TransformOperand;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class LikeExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = -1175028327903814931L;

    private TransformOperand left;
    private CompareOperator operator;
    private TransformOperand right;

    public LikeExpressionOperand(TransformOperand left, CompareOperator operator, TransformOperand right) {
        super(operator.name());
        checkNotNull(operator, "operator can not be null");
        checkArgument(operator == CompareOperator.LIKE || operator == CompareOperator.NOT_LIKE,
                "LikeExpressionOperand only support LIKE and NOT LIKE");
        this.left = left;
        this.operator = operator;
        this.right = right;
        // TODO set parameters

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
