package com.weaponlin.dsl.operand.transform;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.enums.ArithmeticOperator;
import com.weaponlin.dsl.operand.expression.ExpressionOperand;
import com.weaponlin.dsl.operand.expression.OperandExpressionOperand;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        List<Object> objects = Optional.ofNullable(left.getParameters()).orElse(Lists.newArrayList());
        objects.addAll(Optional.ofNullable(right.getParameters()).orElse(Collections.emptyList()));
        super.parameters = objects;
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
