package com.weaponlin.dsl.operand.operator;

import com.weaponlin.dsl.operand.transform.VariableOperand;

public class BetweenOperatorOperand extends OperatorOperand {
    private static final long serialVersionUID = -5917770564059569617L;

    private VariableOperand start;

    private VariableOperand end;

    /**
     * TODO how to define the name?
     *
     * @param start
     * @param end
     */
    BetweenOperatorOperand(VariableOperand start, VariableOperand end) {
        super("");
        this.start = start;
        this.end = end;
    }

    public static BetweenOperatorOperand between(VariableOperand start) {
        return new BetweenOperatorOperand(start, null);
    }

    public static BetweenOperatorOperand between(VariableOperand start, VariableOperand end) {
        return new BetweenOperatorOperand(start, end);
    }

    public BetweenOperatorOperand and(VariableOperand end) {
        this.end = end;
        return this;
    }

    @Override
    public String toString(boolean hasAlias) {
        return "between ? and ?";
    }

    @Override
    public String toString() {
        return toString(false);
    }
}
