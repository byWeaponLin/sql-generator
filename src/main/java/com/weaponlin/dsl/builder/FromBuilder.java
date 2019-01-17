package com.weaponlin.dsl.builder;

import com.weaponlin.dsl.operand.table.TableOperand;
import lombok.Getter;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class FromBuilder implements Serializable, Builder {
    private static final long serialVersionUID = -7640575610110436283L;

    private TableOperand operand;

    private Builder previousBuilder;

    FromBuilder(TableOperand operand, SelectBuilder selectBuilder) {
        this.operand = checkNotNull(operand, "Table name shouldn't be null");
        this.previousBuilder = checkNotNull(selectBuilder, "Select part shouldn't be null");
    }

    FromBuilder(TableOperand operand, DeleteBuilder deleteBuilder) {
        this.operand = checkNotNull(operand, "Table name shouldn't be null");
        this.previousBuilder = checkNotNull(deleteBuilder, "Delete part shouldn't be null");
    }

    public WhereBuilder where() {
        return new WhereBuilder(this);
    }

    @Override
    public String toString() {
        return previousBuilder.toString() + " FROM " + operand;
    }

    @Override
    public String build() {
        return toString();
    }
}
