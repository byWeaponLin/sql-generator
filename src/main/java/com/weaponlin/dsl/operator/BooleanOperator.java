package com.weaponlin.dsl.operator;

import com.weaponlin.dsl.builder.WhereBuilder;
import com.weaponlin.dsl.operand.BitwiseOperand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BiFunction;

@AllArgsConstructor
public enum BooleanOperator {
    AND(" AND ", WhereBuilder::and),
    OR(" OR ", WhereBuilder::or);

    @Getter
    private String operator;

    private BiFunction<WhereBuilder, BitwiseOperand, WhereBuilder> function;

    public static BiFunction<WhereBuilder, BitwiseOperand, WhereBuilder> getFunction(boolean isBoth) {
        return isBoth ? AND.function : OR.function;
    }
}
