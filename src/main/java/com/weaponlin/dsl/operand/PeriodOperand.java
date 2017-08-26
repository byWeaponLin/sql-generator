package com.weaponlin.dsl.operand;

import lombok.Getter;

public class PeriodOperand extends TransformOperand {
    private static final long serialVersionUID = -3077568516964030175L;

    @Getter
    private FunctionOperand func;
    private int min;
    private int day;

    private PeriodOperand(FunctionOperand func, int min, int day) {
        super("period");
        this.func = func;
        this.min = min;
        this.day = day;
    }

    public static PeriodOperand period(FunctionOperand func, int min, int day) {
        return new PeriodOperand(func, min, day);
    }

    @Override
    public String toString(boolean hasAlias) {
        return String.format("%s(%s, %d min, %d day)", name, func.column.name, min, day)
            + (hasAlias ? " AS period_" + func.name + "_" + func.column.name.toLowerCase() : "");
    }
}
