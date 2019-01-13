package com.weaponlin.dsl.operand.transform;


import lombok.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 */
public class VariableOperand extends TransformOperand {
    private static final long serialVersionUID = -3585238394996936166L;
    private List<Object> values;

    private VariableOperand(Object... values) {
        super("");
        this.values = Arrays.asList(values);
    }

    public static VariableOperand values(@NonNull Object... values) {
        return new VariableOperand(values);
    }

    public static VariableOperand value(Object value) {
        return new VariableOperand(value);
    }

    /**
     * useless
     *
     * @param hasAlias
     * @return
     */
    @Override
    public String toString(boolean hasAlias) {
        return null;
    }
}
