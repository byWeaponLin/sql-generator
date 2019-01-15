package com.weaponlin.dsl.operand.expression;

import com.weaponlin.dsl.operand.Operand;
import lombok.Getter;

import java.util.List;

public abstract class ExpressionOperand extends Operand {
    private static final long serialVersionUID = -6216349853355952178L;

    @Getter
    protected List<Object> parameters;

    ExpressionOperand(String name) {
        super(name);
    }

    /**
     * TODO
     */
//    abstract void parameters();

    /**
     * TODO
     */
//    abstract String as();
}
