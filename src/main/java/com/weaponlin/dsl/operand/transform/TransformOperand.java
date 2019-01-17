package com.weaponlin.dsl.operand.transform;

import com.weaponlin.dsl.enums.ArithmeticOperator;
import com.weaponlin.dsl.enums.CompareOperator;
import com.weaponlin.dsl.enums.LikeOption;
import com.weaponlin.dsl.operand.Operand;
import com.weaponlin.dsl.operand.expression.*;
import lombok.Getter;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.weaponlin.dsl.enums.CompareOperator.*;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public abstract class TransformOperand extends Operand {
    private static final long serialVersionUID = 8510012843009307113L;

    /**
     * TODO IMPORTANT
     */
    protected List<Object> parameters;

    @Getter
    protected String alias;

    protected TransformOperand(String name) {
        super(name);
    }

    public CompareExpressionOperand ge(TransformOperand operand) {
        return new CompareExpressionOperand(this, GE, operand);
    }

    public CompareExpressionOperand gt(TransformOperand operand) {
        return new CompareExpressionOperand(this, GT, operand);
    }

    public CompareExpressionOperand eq(TransformOperand operand) {
        return new CompareExpressionOperand(this, EQ, operand);
    }

    public CompareExpressionOperand neq(TransformOperand operand) {
        return new CompareExpressionOperand(this, NEQ, operand);
    }

    public CompareExpressionOperand le(TransformOperand operand) {
        return new CompareExpressionOperand(this, LE, operand);
    }

    public CompareExpressionOperand lt(TransformOperand operand) {
        return new CompareExpressionOperand(this, LT, operand);
    }

    /**
     * {column} like '{parameter}'
     * @param operand
     * @return
     */
    public LikeExpressionOperand like(VariableOperand operand) {
        decorateParameter(operand, LIKE, LikeOption.NONE);
        return new LikeExpressionOperand(this, LIKE, operand);
    }

    /**
     * {column} like '%{parameter}'
     * @param operand
     * @return
     */
    public LikeExpressionOperand _like(VariableOperand operand) {
        decorateParameter(operand, LIKE, LikeOption.LEFT);
        return new LikeExpressionOperand(this, LIKE, operand);
    }

    /**
     * {column} like '{parameter}%'
     * @param operand
     * @return
     */
    public LikeExpressionOperand like_(VariableOperand operand) {
        decorateParameter(operand, LIKE, LikeOption.RIGHT);
        return new LikeExpressionOperand(this, LIKE, operand);
    }

    /**
     * {column} like '%{parameter}%'
     * @param operand
     * @return
     */
    public LikeExpressionOperand _like_(VariableOperand operand) {
        decorateParameter(operand, LIKE, LikeOption.ALL);
        return new LikeExpressionOperand(this, LIKE, operand);
    }

    /**
     * {column} not like '{parameter}'
     * @param operand
     * @return
     */
    public LikeExpressionOperand notLike(VariableOperand operand) {
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
    public LikeExpressionOperand notLike_(VariableOperand operand) {
        decorateParameter(operand, NOT_LIKE, LikeOption.RIGHT);
        return new LikeExpressionOperand(this, NOT_LIKE, operand);
    }

    /**
     * {column} not like '%{parameter}%'
     * @param operand
     * @return
     */
    public LikeExpressionOperand _notLike_(VariableOperand operand) {
        decorateParameter(operand, NOT_LIKE, LikeOption.ALL);
        return new LikeExpressionOperand(this, NOT_LIKE, operand);
    }

    public CompareExpressionOperand isNull() {
        return new CompareExpressionOperand(this, IS_NULL, null);
    }

    public CompareExpressionOperand isNotNull() {
        return new CompareExpressionOperand(this, IS_NOT_NULL, null);
    }

    public InExpressionOperand in(TransformOperand operand) {
        return new InExpressionOperand(this, IN, operand);
    }

    public InExpressionOperand notIn(TransformOperand operand) {
        return new InExpressionOperand(this, NOT_IN, operand);
    }

    public BetweenExpressionOperand betweenAnd(TransformOperand value1, TransformOperand value2) {
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

    @Override
    public TransformOperand as(String alias) {
        checkArgument(isNotBlank(alias), "alias can not be empty.");
        this.alias = alias;
        return this;
    }

    @Override
    protected String getDecoratedAlias(boolean hasAlias) {
        return  (hasAlias && isNotBlank(alias) ? " AS " + alias : "");
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
