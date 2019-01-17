package com.weaponlin.dsl.operand.transform;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.enums.ArithmeticOperator;
import com.weaponlin.dsl.operand.expression.ExpressionOperand;
import com.weaponlin.dsl.operand.expression.OperandExpressionOperand;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;

public class ArithmeticOperand extends TransformOperand {
    private static final long serialVersionUID = -3912432990109932328L;

    private TransformOperand left;
    private ArithmeticOperator operator;
    private TransformOperand right;


    ArithmeticOperand(TransformOperand left, ArithmeticOperator operator, TransformOperand right) {
        super(operator.name());
        this.left = left;
        this.right = right;
        this.operator = operator;
        super.parameters = Stream.of(left.getParameters(), right.getParameters())
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public ArithmeticOperand as(String alias) {
        checkArgument(StringUtils.isNotBlank(alias), "alias can not be empty.");
        this.alias = alias;
        return this;
    }

    @Override
    public ExpressionOperand toExpression() {
        return new OperandExpressionOperand(this);
    }

    @Override
    public String toString(boolean hasAlias) {
        return left
                + operator.getOperator()
                + right
                + getDecoratedAlias(hasAlias);
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
