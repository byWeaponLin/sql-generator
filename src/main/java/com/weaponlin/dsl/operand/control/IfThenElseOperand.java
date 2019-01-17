package com.weaponlin.dsl.operand.control;

import com.weaponlin.dsl.operand.Operand;

/**
 * TODO interesting
 */
public class IfThenElseOperand extends Operand {
    private static final long serialVersionUID = -8254982278446889442L;

    private boolean condition;

    private Operand operand;

    IfThenElseOperand(boolean condition) {
        super("");
        this.condition = condition;
        this.operand = null;
    }

    public static IfThenElseOperand _if(boolean condition) {
        return new IfThenElseOperand(condition);
    }

    public IfThenElseOperand then(Operand operand) {
        if (condition) {
            this.operand = operand;
        }
        return this;
    }

    public IfThenElseOperand _else(Operand operand) {
        if (!condition) {
            this.operand = operand;
        }
        return this;
    }

    @Override
    public String toString(boolean hasAlias) {
        // TODO
        return null;
    }

    @Override
    public Operand as(String alias) {
        // TODO
        return null;
    }

    @Override
    protected String getDecoratedAlias(boolean hasAlias) {
        return "";
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
