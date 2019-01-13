package com.weaponlin.dsl.operand.transform;

import com.google.common.base.Preconditions;
import com.weaponlin.dsl.enums.ArithmeticOperator;
import com.weaponlin.dsl.operand.operator.ArithmeticOperatorOperand;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.*;

public class ColumnOperand extends TransformOperand {
    private static final long serialVersionUID = 7564148803426063816L;

    @Getter
    private String alias;

    ColumnOperand(String name) {
        super(name);
    }

    public static ColumnOperand column(String column) {
        checkArgument(StringUtils.isNotBlank(column), "column name can not be empty");
        return new ColumnOperand(column);
    }

    public ColumnOperand as(String alias) {
        this.alias = alias;
        return this;
    }

    public ArithmeticOperatorOperand or(VariableOperand variable) {
        return new ArithmeticOperatorOperand(this, ArithmeticOperator.OR, variable);
    }

    @Override
    public String toString(boolean hasAlias) {
        return hasAlias && StringUtils.isNotBlank(alias) ? (name + " as " + alias) : name;
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
