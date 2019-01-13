package com.weaponlin.dsl.design;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Operand implements Serializable {
    private static final long serialVersionUID = -3309665656541573178L;

    protected String name;

    protected Operand(String name) {
        checkNotNull(name, "Operand should have a name attribute");
        this.name = name;
    }

    public abstract String toString(boolean hasAlias);
}
