package com.weaponlin.dsl.design.expression;

import com.weaponlin.dsl.design.Operand;
import com.weaponlin.dsl.design.transform.TransformOperand;
import com.weaponlin.dsl.operator.CompareOperator;

public abstract class ExpressionOperand extends Operand {
    private static final long serialVersionUID = -6216349853355952178L;

    public ExpressionOperand(String name) {
        super(name);
    }


}
