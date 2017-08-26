package com.weaponlin.dsl.operator;

import com.weaponlin.dsl.operand.BitwiseOperand;
import com.weaponlin.dsl.operand.TransformOperand;
import lombok.AllArgsConstructor;

import java.util.function.BiFunction;

@AllArgsConstructor
public enum CompareOperator implements Operator<BitwiseOperand> {
    GT(" > ", TransformOperand::gt),
    GE(" >= ", TransformOperand::ge),
    EQ(" == ", TransformOperand::equal),
    LT(" < ", TransformOperand::lt),
    LE(" <= ", TransformOperand::le);

    private String operator;

    private BiFunction<TransformOperand, TransformOperand, BitwiseOperand> function;

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public BiFunction<TransformOperand, TransformOperand, BitwiseOperand> getFunction() {
        return function;
    }
}
