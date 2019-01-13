package com.weaponlin.dsl.operand.transform;

import com.weaponlin.dsl.enums.ArithmeticOperator;

public class ArithmeticOperand extends TransformOperand {
    private static final long serialVersionUID = -3912432990109932328L;

    private ArithmeticOperator operator;

    protected ArithmeticOperand(String name) {
        super(name);
    }

    @Override
    public String toString(boolean hasAlias) {
        return null;
    }
}
