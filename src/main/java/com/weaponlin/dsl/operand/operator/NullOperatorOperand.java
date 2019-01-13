package com.weaponlin.dsl.operand.operator;

public class NullOperatorOperand extends OperatorOperand {
    private static final long serialVersionUID = -305410045738774571L;

    private boolean empty;

    NullOperatorOperand(String name, boolean empty) {
        super(name);
        this.empty = empty;
    }

    public static NullOperatorOperand isNull() {
        return new NullOperatorOperand("", true);
    }

    public static NullOperatorOperand isNotNull() {
        return new NullOperatorOperand("", false);
    }

    @Override
    public String toString(boolean hasAlias) {
        return empty ? "is null" : "is not null";
    }

    @Override
    public String toString() {
        return toString(false);
    }
}
