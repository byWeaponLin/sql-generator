package com.weaponlin.dsl.design.column;

import com.weaponlin.dsl.design.Operand;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class ColumnOperand extends Operand {
    private static final long serialVersionUID = 7564148803426063816L;

    @Getter
    private String alias;

    ColumnOperand(String name) {
        super(name);
    }

    public static ColumnOperand column(String column) {
        return new ColumnOperand(column);
    }

    public ColumnOperand as(String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public String toString(boolean hasAlias) {
        return hasAlias && StringUtils.isNotBlank(alias) ? (name + " as " + alias) : name;
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
