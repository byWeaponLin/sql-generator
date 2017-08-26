package com.weaponlin.dsl.operand;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class NameOperand implements Serializable {
    private static final long serialVersionUID = 3553715314841699144L;

    protected String name;

    NameOperand(String name) {
        checkNotNull(name, "Operand should have a name attribute");
        this.name = name;
    }

    /**
     * Whether the str should have alias part
     */
    public abstract String toString(boolean hasAlias);
}
