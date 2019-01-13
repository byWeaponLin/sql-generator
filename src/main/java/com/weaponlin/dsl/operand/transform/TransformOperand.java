package com.weaponlin.dsl.operand.transform;

import com.weaponlin.dsl.operand.Operand;
import com.weaponlin.dsl.operand.expression.*;
import com.weaponlin.dsl.enums.Aggregate;
import com.weaponlin.dsl.enums.ArithmeticOperator;

import static com.weaponlin.dsl.enums.CompareOperator.*;

public abstract class TransformOperand extends Operand {
    private static final long serialVersionUID = 8510012843009307113L;

    protected TransformOperand(String name) {
        super(name);
    }

    public ExpressionOperand ge(TransformOperand operand) {
        return new CompareExpressionOperand(this, GE, operand);
    }

    public ExpressionOperand gt(TransformOperand operand) {
        return new CompareExpressionOperand(this, GT, operand);
    }

    public ExpressionOperand eq(TransformOperand operand) {
        return new CompareExpressionOperand(this, EQ, operand);
    }

    public ExpressionOperand neq(TransformOperand operand) {
        return new CompareExpressionOperand(this, NEQ, operand);
    }

    public ExpressionOperand le(TransformOperand operand) {
        return new CompareExpressionOperand(this, LE, operand);
    }

    public ExpressionOperand lt(TransformOperand operand) {
        return new CompareExpressionOperand(this, LT, operand);
    }

    public ExpressionOperand like(TransformOperand operand) {
        return new CompareExpressionOperand(this, LIKE, operand);
    }

    public ExpressionOperand notLike(TransformOperand operand) {
        return new CompareExpressionOperand(this, NOT_LIKE, operand);
    }

    public ExpressionOperand isNull(TransformOperand operand) {
        return new CompareExpressionOperand(this, IS_NULL, operand);
    }

    public ExpressionOperand isNotNull(TransformOperand operand) {
        return new CompareExpressionOperand(this, IS_NOT_NULL, operand);
    }

    public ExpressionOperand in(TransformOperand values) {
        return new CompareExpressionOperand(this, IN, values);
    }

    public ExpressionOperand notIn(TransformOperand values) {
        return new CompareExpressionOperand(this, NOT_IN, values);
    }

    public ExpressionOperand betweenAnd(TransformOperand value1, TransformOperand value2) {
        return new BetweenExpressionOperand(this, value1, value2);
    }

    public ExpressionOperand add(TransformOperand operand) {
        return new ArithmeticExpressionOperand(this, ArithmeticOperator.ADD, operand);
    }

    public ExpressionOperand minus(TransformOperand operand) {
        return new ArithmeticExpressionOperand(this, ArithmeticOperator.MINUS, operand);
    }

    public ExpressionOperand multiply(TransformOperand operand) {
        return new ArithmeticExpressionOperand(this, ArithmeticOperator.MULTIPLY, operand);
    }

    public ExpressionOperand divide(TransformOperand operand) {
        return new ArithmeticExpressionOperand(this, ArithmeticOperator.DIVIDE, operand);
    }

    public ExpressionOperand and(TransformOperand operand) {
        return new ArithmeticExpressionOperand(this, ArithmeticOperator.AND, operand);
    }

    public ExpressionOperand or(TransformOperand operand) {
        return new ArithmeticExpressionOperand(this, ArithmeticOperator.OR, operand);
    }

    /**
     * TODO change method name
     * @param operand
     * @return
     */
    @Deprecated
    public ExpressionOperand column(TransformOperand operand) {
        return new ColumnExpressionOperand(operand);
    }

    public ExpressionOperand expression() {
        return new ChildExpressionOperand();
    }

    /**
     * TODO consider how to operand aggregate function
     *
     * @return
     */
    public ExpressionOperand max() {
        return new FunctionExpressionOperand(Aggregate.MAX, this);
    }

    public ExpressionOperand min() {
        return new FunctionExpressionOperand(Aggregate.MIN, this);
    }

    public ExpressionOperand count() {
        return new FunctionExpressionOperand(Aggregate.COUNT, this);
    }

    public ExpressionOperand sum() {
        return new FunctionExpressionOperand(Aggregate.SUM, this);
    }

    public ExpressionOperand avg() {
        return new FunctionExpressionOperand(Aggregate.AVG, this);
    }


}
