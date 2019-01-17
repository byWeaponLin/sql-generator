package com.weaponlin.dsl.operand;

import lombok.Getter;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Operand implements Serializable {
    private static final long serialVersionUID = -3309665656541573178L;

    @Getter
    protected String name;

    protected Operand(String name) {
        checkNotNull(name, "Operand should have a name attribute");
        this.name = name;
    }

    /**
     * TODO getParameters
     */

    public abstract String toString(boolean hasAlias);

    public abstract Operand as(String alias);

    protected abstract String getDecoratedAlias(boolean hasAlias);
}
