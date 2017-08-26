package com.weaponlin.dsl.operand;

import org.springframework.util.ClassUtils;

public class VariableOperand extends TransformOperand {
    private static final long serialVersionUID = -3546080273040663434L;

    private final String placeholder;

    private VariableOperand(String placeholder) {
        super(ClassUtils.getShortName(VariableOperand.class));
        this.placeholder = placeholder;
    }

    public static VariableOperand variable(String placeholder) {
        return new VariableOperand(placeholder);
    }

    @Override
    public String toString(boolean hasAlias) {
        return "?" + placeholder;
    }
}
