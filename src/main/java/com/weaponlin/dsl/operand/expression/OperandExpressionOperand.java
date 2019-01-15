package com.weaponlin.dsl.operand.expression;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.operand.transform.TransformOperand;
import lombok.NonNull;

import java.util.Optional;

public class OperandExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = 4122770030630205650L;

    private TransformOperand operand;

    public OperandExpressionOperand(@NonNull TransformOperand operand) {
        super(operand.getName());
        this.operand = operand;
        super.parameters = Optional.ofNullable(operand.getParameters()).orElse(Lists.newArrayList());
    }

    @Override
    public String toString(boolean hasAlias) {
        return operand.toString();
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
