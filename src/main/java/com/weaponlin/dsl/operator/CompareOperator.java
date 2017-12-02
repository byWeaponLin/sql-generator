package com.weaponlin.dsl.operator;

import com.weaponlin.dsl.operand.BitwiseOperand;
import com.weaponlin.dsl.operand.TransformOperand;
import com.weaponlin.enums.Comparator;
import lombok.AllArgsConstructor;

import java.util.function.BiFunction;

@AllArgsConstructor
public enum CompareOperator implements Operator<BitwiseOperand> {
    GT(Comparator.GT, TransformOperand::gt),
    GE(Comparator.GE, TransformOperand::ge),
    EQ(Comparator.EQ, TransformOperand::equal),
    LT(Comparator.LT, TransformOperand::lt),
    LE(Comparator.LE, TransformOperand::le);

    private Comparator operator;

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
        if (operator == Comparator.GT) {
            return CalculateOperator.valueOf("ADD");
        } else if (operator == Comparator.LT) {
            return CalculateOperator.valueOf("MINUS");
        }

        return null;
    }
}
