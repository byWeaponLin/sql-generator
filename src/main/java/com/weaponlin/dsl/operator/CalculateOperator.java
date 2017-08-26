package com.weaponlin.dsl.operator;

import com.weaponlin.dsl.operand.CalculateOperand;
import com.weaponlin.dsl.operand.TransformOperand;
import lombok.AllArgsConstructor;

import java.util.function.BiFunction;

@AllArgsConstructor
public enum CalculateOperator implements Operator<CalculateOperand> {
    ADD("+", TransformOperand::add),
    MINUS("-", TransformOperand::minus),
    MULTIPLY("*", TransformOperand::multiply),
    DIVIDE("/", TransformOperand::divide);

    private String operator;

    private BiFunction<TransformOperand, TransformOperand, CalculateOperand> function;

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public BiFunction<TransformOperand, TransformOperand, CalculateOperand> getFunction() {
        return function;
    }
}
