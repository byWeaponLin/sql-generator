package com.weaponlin.dsl.operand.transform;


import com.weaponlin.dsl.operand.expression.ExpressionOperand;
import lombok.NonNull;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

/**
 * TODO
 */
public class VariableOperand extends TransformOperand {
    private static final long serialVersionUID = -3585238394996936166L;

    private VariableOperand(Object... values) {
        super("");
        super.parameters = Arrays.asList(values);
    }

    public static VariableOperand values(@NonNull Object... values) {
        return new VariableOperand(values);
    }

    public static VariableOperand value(Object value) {
        return new VariableOperand(value);
    }

    /**
     * useless
     *
     * @param hasAlias
     * @return
     */
    @Override
    public String toString(boolean hasAlias) {
        return Optional.ofNullable(parameters)
                .filter(CollectionUtils::isNotEmpty)
                .map(list ->
                        Collections.nCopies(list.size(), "?")
                                .stream()
                                .collect(joining(", "))
                ).orElse("");
    }

    @Override
    public String toString() {
        return toString(false);
    }

    @Override
    public ExpressionOperand toExpression() {
        throw new UnsupportedOperationException("can not convert VariableOperand to ExpressionOperand");
    }
}
