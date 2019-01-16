package com.weaponlin.dsl.operand.table;

import com.weaponlin.dsl.operand.Operand;
import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.checkArgument;

public class TableOperand extends Operand {
    private static final long serialVersionUID = 827621260033371902L;

    private String tableName;

    private String alias;

    private TableOperand(String tableName) {
        super(tableName);
        this.tableName = tableName;
    }

    public static TableOperand table(String tableName) {
        checkArgument(StringUtils.isNotBlank(tableName), "table name can not be empty");
        return new TableOperand(tableName);
    }

    public TableOperand as(String alias) {
        checkArgument(StringUtils.isNotBlank(alias), "alias can not be empty");
        this.alias = alias;
        return this;
    }

    @Override
    public String toString(boolean hasAlias) {
        return tableName + (hasAlias && StringUtils.isNotBlank(alias) ? " AS " + alias : "");
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
