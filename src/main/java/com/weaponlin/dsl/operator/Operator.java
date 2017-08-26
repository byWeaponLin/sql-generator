package com.weaponlin.dsl.operator;


import com.weaponlin.dsl.operand.TransformOperand;

import java.util.function.BiFunction;

public interface Operator<T> {

    String getOperator();

    BiFunction<TransformOperand, TransformOperand, T> getFunction();
}
