package com.weaponlin.dsl.operand;

import com.weaponlin.dsl.operator.CompareOperator;
import lombok.Getter;

public class BitwiseOperand extends NameOperand {
    private static final long serialVersionUID = 639839003517283701L;

    @Getter
    private TransformOperand left;
    private TransformOperand right;

    BitwiseOperand(TransformOperand left, CompareOperator operator, TransformOperand right) {
        super(operator.getOperator());
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString(boolean hasAlias) {
        return left.toString(hasAlias) + name + right.toString(hasAlias);
    }
}
