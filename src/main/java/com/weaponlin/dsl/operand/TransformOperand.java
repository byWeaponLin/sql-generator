package com.weaponlin.dsl.operand;


import static com.weaponlin.dsl.operator.CalculateOperator.*;
import static com.weaponlin.dsl.operator.CompareOperator.*;

public abstract class TransformOperand extends NameOperand {
    private static final long serialVersionUID = -1653169984269618636L;

    TransformOperand(String name) {
        super(name);
    }

    public BitwiseOperand ge(TransformOperand operand) {
        return new BitwiseOperand(this, GE, operand);
    }

    public BitwiseOperand gt(TransformOperand operand) {
        return new BitwiseOperand(this, GT, operand);
    }

    public BitwiseOperand equal(TransformOperand operand) {
        return new BitwiseOperand(this, EQ, operand);
    }

    public BitwiseOperand le(TransformOperand operand) {
        return new BitwiseOperand(this, LE, operand);
    }

    public BitwiseOperand lt(TransformOperand operand) {
        return new BitwiseOperand(this, LT, operand);
    }

    public CalculateOperand add(TransformOperand operand) {
        return new CalculateOperand(this, ADD, operand);
    }

    public CalculateOperand minus(TransformOperand operand) {
        return new CalculateOperand(this, MINUS, operand);
    }

    public CalculateOperand multiply(TransformOperand operand) {
        return new CalculateOperand(this, MULTIPLY, operand);
    }

    public CalculateOperand divide(TransformOperand operand) {
        return new CalculateOperand(this, DIVIDE, operand);
    }
}
