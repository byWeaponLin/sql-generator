package com.weaponlin.dsl.operand.expression;

/**
 * TODO interesting
 */
public class IfThenElseExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = -8254982278446889442L;

    private boolean condition;

    private ExpressionOperand operand;

    IfThenElseExpressionOperand(boolean condition) {
        super("");
        this.condition = condition;
        this.operand = null;
    }

    public static IfThenElseExpressionOperand _if(boolean condition) {
        return new IfThenElseExpressionOperand(condition);
    }

    public IfThenElseExpressionOperand then(ExpressionOperand operand) {
        if (condition) {
            this.operand = operand;
        }
        return this;
    }

    public IfThenElseExpressionOperand _else(ExpressionOperand operand) {
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
    public String toString() {
        return toString(true);
    }
}
