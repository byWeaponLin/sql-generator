package com.weaponlin.dsl.builder;

import com.weaponlin.dsl.operand.table.TableOperand;
import lombok.Getter;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Getter
public class FromBuilder implements Serializable, Builder {
    private static final long serialVersionUID = -7640575610110436283L;

    private final TableOperand operand;
    private final SelectBuilder selectBuilder;

    FromBuilder(TableOperand operand, SelectBuilder selectBuilder) {
        this.operand = checkNotNull(operand, "Table name shouldn't be null");
        this.selectBuilder = checkNotNull(selectBuilder, "Select part shouldn't be null");
    }

    public WhereBuilder where() {
        return new WhereBuilder(selectBuilder, this);
    }

    @Override
    public String toString() {
        return " FROM " + operand;
    }

    @Override
    public String build() {
        return selectBuilder.toString() + this.toString();
    }
}
