package com.weaponlin.dsl.operator;

import com.weaponlin.dsl.operand.BitwiseOperand;
import com.weaponlin.dsl.operand.TransformOperand;
import lombok.AllArgsConstructor;

import java.util.function.BiFunction;

@AllArgsConstructor
public enum CompareOperator implements Operator<BitwiseOperand> {
    GT(com.weaponlin.dsl.enums.CompareOperator.GT, TransformOperand::gt),
    GE(com.weaponlin.dsl.enums.CompareOperator.GE, TransformOperand::ge),
    EQ(com.weaponlin.dsl.enums.CompareOperator.EQ, TransformOperand::equal),
    LT(com.weaponlin.dsl.enums.CompareOperator.LT, TransformOperand::lt),
    LE(com.weaponlin.dsl.enums.CompareOperator.LE, TransformOperand::le);

    private com.weaponlin.dsl.enums.CompareOperator operator;

    private BiFunction<TransformOperand, TransformOperand, BitwiseOperand> function;

    @Override
    public String getOperator() {
        return operator.getComparator();
    }

    @Override
    public BiFunction<TransformOperand, TransformOperand, BitwiseOperand> getFunction() {
        return function;
    }

    /**
     * Warning,this method only for the dynamic threshold,because the comparator attribute has two meanings.
     */
    public CalculateOperator getCalculateOperator() {
        if (operator == com.weaponlin.dsl.enums.CompareOperator.GT) {
            return CalculateOperator.valueOf("ADD");
        } else if (operator == com.weaponlin.dsl.enums.CompareOperator.LT) {
            return CalculateOperator.valueOf("MINUS");
        }

        return null;
    }
}
