package com.weaponlin.dsl.builder;

import lombok.Getter;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Getter
public class FromBuilder implements Serializable {
    private static final long serialVersionUID = -7640575610110436283L;

    private final String tableName;
    private final SelectBuilder selectBuilder;

    FromBuilder(String tableName, SelectBuilder selectBuilder) {
        checkState(isNotBlank(tableName), "Table name shouldn't be null");
        this.tableName = tableName;
        this.selectBuilder = checkNotNull(selectBuilder, "Select part shouldn't be null");
    }

    public WhereBuilder where() {
        return new WhereBuilder(selectBuilder, this);
    }

    @Override
    public String toString() {
        return "FROM " + tableName;
    }
}
