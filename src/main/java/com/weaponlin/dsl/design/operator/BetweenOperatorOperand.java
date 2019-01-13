package com.weaponlin.dsl.design.operator;

public class BetweenOperatorOperand extends OperatorOperand {
    private static final long serialVersionUID = -5917770564059569617L;

    private Object start;

    private Object end;

    /**
     * TODO how to define the name?
     * @param name
     * @param start
     */
    BetweenOperatorOperand(String name, Object start) {
        super(name);
        this.start = start;
    }

    public static BetweenOperatorOperand between(Object start) {
        return new BetweenOperatorOperand("", start);
    }

    public BetweenOperatorOperand and(Object end) {
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
