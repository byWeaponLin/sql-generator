package com.weaponlin.dsl.operand;

import lombok.Getter;

import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.isNotBlank;


public class ColumnOperand extends NameOperand implements Serializable {
    private static final long serialVersionUID = 4994278345418764027L;

    @Getter
    private String aliasName;

    private ColumnOperand(String name, String aliasName) {
        super(name);
        this.aliasName = aliasName;
    }

    public static ColumnOperand name(String columnName) {
        return new ColumnOperand(columnName, "");
    }

    public ColumnOperand alias(String aliasName) {
        this.aliasName = aliasName;
        return this;
    }

    @Override
    public String toString(boolean hasAlias) {
        return name + (hasAlias && isNotBlank(aliasName) ? " AS " + aliasName : "");
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
