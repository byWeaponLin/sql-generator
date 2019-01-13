package com.weaponlin.dsl.design.operator;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.joining;

public class InOperatorOperand extends OperatorOperand {
    private static final long serialVersionUID = 7632160740408852158L;

    private Object[] values;

    /**
     * TODO consider to remove this attribute
     */
    private String alias;

    private boolean in;

    InOperatorOperand(String name, boolean in, Object... values) {
        super(name);
        this.in = in;
        this.values = values;
    }

    public static InOperatorOperand in(Object... values) {
        checkNotNull(values, "in values can not be null");
        return new InOperatorOperand("", true, values);
    }

    public static InOperatorOperand notIn(Object... values) {
        checkNotNull(values, "not in values can not be null");
        return new InOperatorOperand("", false, values);
    }

    public InOperatorOperand as(String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public String toString(boolean hasAlias) {
        return (in ? "in" : "not in")
                + Collections.nCopies(values.length, "?").stream().collect(joining(",", "(", ")"))
                + (hasAlias && StringUtils.isNotBlank(alias) ? " as " + alias : "");
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
