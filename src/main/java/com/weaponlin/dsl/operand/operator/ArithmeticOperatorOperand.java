package com.weaponlin.dsl.operand.operator;

import com.weaponlin.dsl.enums.ArithmeticOperator;
import com.weaponlin.dsl.operand.transform.ColumnOperand;
import com.weaponlin.dsl.operand.transform.VariableOperand;

/**
 * TODO 算术运算
 */
public class ArithmeticOperatorOperand extends OperatorOperand {
    private static final long serialVersionUID = 2321512695610163641L;

    private ColumnOperand column;
    private ArithmeticOperator operator;
    private VariableOperand variable;

    public ArithmeticOperatorOperand(ColumnOperand column, ArithmeticOperator operator, VariableOperand variable) {
        super(operator.getOperator());
        this.column = column;
        this.operator = operator;
        this.variable = variable;
    }

    @Override
    public String toString(boolean hasAlias) {
        return column.toString(false) + operator.getOperator() + " ?";
    }

    @Override
    public String toString() {
        return toString(false);
    }
}
