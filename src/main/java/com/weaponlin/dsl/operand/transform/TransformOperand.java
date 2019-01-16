package com.weaponlin.dsl.operand.transform;

import com.weaponlin.dsl.enums.CompareOperator;
import com.weaponlin.dsl.enums.LikeOption;
import com.weaponlin.dsl.operand.Operand;
import com.weaponlin.dsl.operand.expression.*;
import com.weaponlin.dsl.enums.ArithmeticOperator;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.weaponlin.dsl.enums.CompareOperator.*;

public abstract class TransformOperand extends Operand {
    private static final long serialVersionUID = 8510012843009307113L;

    /**
     * TODO IMPORTANT
     */
    protected List<Object> parameters;

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

    /**
     * {column} like '{parameter}'
     * @param operand
     * @return
     */
    public ExpressionOperand like(VariableOperand operand) {
        decorateParameter(operand, LIKE, LikeOption.NONE);
        return new LikeExpressionOperand(this, LIKE, operand);
    }

    /**
     * {column} like '%{parameter}'
     * @param operand
     * @return
     */
    public ExpressionOperand _like(VariableOperand operand) {
        decorateParameter(operand, LIKE, LikeOption.LEFT);
        return new LikeExpressionOperand(this, LIKE, operand);
    }

    /**
     * {column} like '{parameter}%'
     * @param operand
     * @return
     */
    public ExpressionOperand like_(VariableOperand operand) {
        decorateParameter(operand, LIKE, LikeOption.RIGHT);
        return new LikeExpressionOperand(this, LIKE, operand);
    }

    /**
     * {column} like '%{parameter}%'
     * @param operand
     * @return
     */
    public ExpressionOperand _like_(VariableOperand operand) {
        decorateParameter(operand, LIKE, LikeOption.ALL);
        return new LikeExpressionOperand(this, LIKE, operand);
    }

    /**
     * {column} not like '{parameter}'
     * @param operand
     * @return
     */
    public ExpressionOperand notLike(VariableOperand operand) {
        decorateParameter(operand, NOT_LIKE, LikeOption.NONE);
        return new LikeExpressionOperand(this, NOT_LIKE, operand);
    }

    /**
     * {column} not like '%{parameter}'
     * @param operand
     * @return
     */
    public ExpressionOperand _notLike(VariableOperand operand) {
        decorateParameter(operand, NOT_LIKE, LikeOption.LEFT);
        return new LikeExpressionOperand(this, NOT_LIKE, operand);
    }

    /**
     * {column} not like '{parameter}%'
     * @param operand
     * @return
     */
    public ExpressionOperand notLike_(VariableOperand operand) {
        decorateParameter(operand, NOT_LIKE, LikeOption.RIGHT);
        return new LikeExpressionOperand(this, NOT_LIKE, operand);
    }

    /**
     * {column} not like '%{parameter}%'
     * @param operand
     * @return
     */
    public ExpressionOperand _notLike_(VariableOperand operand) {
        decorateParameter(operand, NOT_LIKE, LikeOption.ALL);
        return new LikeExpressionOperand(this, NOT_LIKE, operand);
    }

    public ExpressionOperand isNull() {
        return new CompareExpressionOperand(this, IS_NULL, null);
    }

    public ExpressionOperand isNotNull() {
        return new CompareExpressionOperand(this, IS_NOT_NULL, null);
    }

    public ExpressionOperand in(TransformOperand operand) {
        return new InExpressionOperand(this, IN, operand);
    }

    public ExpressionOperand notIn(TransformOperand operand) {
        return new InExpressionOperand(this, NOT_IN, operand);
    }

    public ExpressionOperand betweenAnd(TransformOperand value1, TransformOperand value2) {
        return new BetweenExpressionOperand(this, value1, value2);
    }

    public ArithmeticOperand add(TransformOperand operand) {
        return new ArithmeticOperand(this, ArithmeticOperator.ADD, operand);
    }

    public ArithmeticOperand minus(TransformOperand operand) {
        return new ArithmeticOperand(this, ArithmeticOperator.MINUS, operand);
    }

    public ArithmeticOperand multiply(TransformOperand operand) {
        return new ArithmeticOperand(this, ArithmeticOperator.MULTIPLY, operand);
    }

    public ArithmeticOperand divide(TransformOperand operand) {
        return new ArithmeticOperand(this, ArithmeticOperator.DIVIDE, operand);
    }

    public ArithmeticOperand and(TransformOperand operand) {
        return new ArithmeticOperand(this, ArithmeticOperator.AND, operand);
    }

    public ArithmeticOperand or(TransformOperand operand) {
        return new ArithmeticOperand(this, ArithmeticOperator.OR, operand);
    }

    /**
     * TODO insert child expression
     *
     * @return
     */
    public ExpressionOperand nestedExpression() {
        return new ChildExpressionOperand();
    }

    public abstract ExpressionOperand toExpression();

    public List<Object> getParameters() {
        return parameters;
    }

    /**
     * only for like operation
     * @param operand
     */
    private void decorateParameter(VariableOperand operand, CompareOperator operator, LikeOption likeOption) {
        checkArgument(operator == CompareOperator.LIKE || operator == CompareOperator.NOT_LIKE,
                "decorateParameter only support LIKE and NOT LIKE");
        checkNotNull(operand, "VariableOperand can not be null");
        List<Object> parameters = operand.getRealParameters();
        checkNotNull(parameters, "VariableOperand's parameters can not be null");
        parameters.set(0, likeOption.format(parameters.get(0)));
    }
}
