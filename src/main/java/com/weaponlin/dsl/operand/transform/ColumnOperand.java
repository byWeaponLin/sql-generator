package com.weaponlin.dsl.operand.transform;

import com.weaponlin.dsl.operand.expression.ExpressionOperand;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.checkArgument;

public class ColumnOperand extends TransformOperand {
    private static final long serialVersionUID = 7564148803426063816L;

    @Getter
    private String alias;

    ColumnOperand(String name) {
        super(name);
    }

    public static ColumnOperand name(String column) {
        checkArgument(StringUtils.isNotBlank(column), "name name can not be empty");
        return new ColumnOperand(column);
    }

    public ColumnOperand as(String alias) {
        this.alias = alias;
        return this;
    }

//    public ArithmeticOperatorOperand add(VariableOperand variable) {
//        return new ArithmeticOperatorOperand(this, ArithmeticOperator.ADD, variable);
//    }
//
//    public ArithmeticOperatorOperand minus(VariableOperand variable) {
//        return new ArithmeticOperatorOperand(this, ArithmeticOperator.MINUS, variable);
//    }
//
//    public ArithmeticOperatorOperand multiply(VariableOperand variable) {
//        return new ArithmeticOperatorOperand(this, ArithmeticOperator.MULTIPLY, variable);
//    }
//
//    public ArithmeticOperatorOperand divide(VariableOperand variable) {
//        return new ArithmeticOperatorOperand(this, ArithmeticOperator.DIVIDE, variable);
//    }
//
//    public ArithmeticOperatorOperand and(VariableOperand variable) {
//        return new ArithmeticOperatorOperand(this, ArithmeticOperator.AND, variable);
//    }
//
//    public ArithmeticOperatorOperand or(VariableOperand variable) {
//        return new ArithmeticOperatorOperand(this, ArithmeticOperator.OR, variable);
//    }

    @Override
    public String toString(boolean hasAlias) {
        return hasAlias && StringUtils.isNotBlank(alias) ? (name + " as " + alias) : name;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    @Override
    public ExpressionOperand toExpression() {
        return null;
    }
}
