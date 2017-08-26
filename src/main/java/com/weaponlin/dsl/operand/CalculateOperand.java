package com.weaponlin.dsl.operand;


import com.weaponlin.dsl.operator.CalculateOperator;

public class CalculateOperand extends TransformOperand {
    private static final long serialVersionUID = 6234213290902959634L;

    private final TransformOperand left;
    private final TransformOperand right;

    CalculateOperand(TransformOperand left, CalculateOperator name, TransformOperand right) {
        super(name.getOperator());
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString(boolean hasAlias) {
        return String.format("(%s %s %s)", left.toString(false), super.name, right.toString(false));
    }
}
