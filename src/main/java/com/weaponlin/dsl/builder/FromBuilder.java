package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.SQLParameter;
import com.weaponlin.dsl.operand.table.TableOperand;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class FromBuilder implements Builder {
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
    public SQLParameter build() {
        return new SQLParameter(toString(), getParameters());
    }

    @Override
    public List<Object> getParameters() {
        checkNotNull(previousBuilder, "PreviousBuilder can not be null");
        return Optional.ofNullable(previousBuilder.getParameters()).orElse(Lists.newArrayList());
    }
}
