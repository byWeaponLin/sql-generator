package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.operand.BitwiseOperand;
import com.weaponlin.dsl.operator.BooleanOperator;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.joining;

public class WhereBuilder implements Serializable {
    private static final long serialVersionUID = 1826524948263871298L;

    private final SelectBuilder selectBuilder;
    private final FromBuilder fromBuilder;
    private final List<BitwiseOperand> operands = Lists.newArrayList();
    private volatile BooleanOperator booleanIdentifier;

    WhereBuilder(SelectBuilder selectBuilder, FromBuilder fromBuilder) {
        this.selectBuilder = checkNotNull(selectBuilder, "select builder shouldn't be null");
        this.fromBuilder = checkNotNull(fromBuilder, "from builder shouldn't be null");
    }

    public WhereBuilder and(BitwiseOperand operand) {
        checkIdentificationStatus(BooleanOperator.AND);
        checkNotNull(operand, "BitwiseOperand shouldn't be null");
        operands.add(operand);
        return this;
    }

    public WhereBuilder or(BitwiseOperand operand) {
        checkIdentificationStatus(BooleanOperator.OR);
        checkNotNull(operand, "BitwiseOperand shouldn't be null");
        operands.add(operand);
        return this;
    }

    private void checkIdentificationStatus(BooleanOperator identifier) {
        if (booleanIdentifier == null) {
            booleanIdentifier = identifier;
        } else if (booleanIdentifier != identifier) {
            throw new IllegalStateException("AND and OR shouldn't be used in the same time");
        }
    }

    @Override
    public String toString() {
        if (CollectionUtils.isEmpty(operands)) {
            return "";
        }
        return operands.stream().map(bit -> bit.toString(false)).collect(joining(booleanIdentifier.getOperator(), "WHERE ", ""));
    }

    public String build() {
        return selectBuilder  + " " + fromBuilder + " " + this.toString();
    }
}
