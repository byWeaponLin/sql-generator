package com.weaponlin.dsl.operand.expression;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.enums.BooleanOperator;

import java.util.List;

/**
 * TODO 子表达式
 */
public class ChildExpressionOperand extends ExpressionOperand {
    private static final long serialVersionUID = 6206390500895753029L;

    private List<ExpressionOperand> expressions;

    private BooleanOperator operator;

    public ChildExpressionOperand() {
        super("");
        expressions = Lists.newArrayList();
    }

    public ChildExpressionOperand and(ExpressionOperand expression) {
        check(BooleanOperator.AND);
        expressions.add(expression);
        return this;
    }

    public ChildExpressionOperand or(ExpressionOperand expression) {
        check(BooleanOperator.OR);
        expressions.add(expression);
        return this;
    }

    private void check(BooleanOperator operator) {
        if (this.operator == null) {
            this.operator = operator;
        } else if (this.operator != operator) {
            throw new IllegalStateException("AND and OR shouldn't be used in the same time");
        }
    }

    @Override
    public String toString(boolean hasAlias) {
        // TODO
        return null;
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
