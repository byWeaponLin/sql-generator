package com.weaponlin.dsl.operator;

import com.weaponlin.dsl.operand.ColumnOperand;
import com.weaponlin.dsl.operand.FunctionOperand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum FunctionOperator {
    MINIMUM("minimum", FunctionOperand::min),
    MAXIMUM("maximum", FunctionOperand::max),
    AVG("avg", FunctionOperand::avg),
    SUM("sum", FunctionOperand::sum),
    COUNT("count", FunctionOperand::count);

    private String operator;

    private Function<ColumnOperand, FunctionOperand> function;
}
