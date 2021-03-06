package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.operand.ColumnOperand;
import com.weaponlin.dsl.operand.NameOperand;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class SelectBuilder implements Serializable {
    private static final long serialVersionUID = 5394178704845865940L;

    private List<NameOperand> selectColumns;

    private SelectBuilder() {
        this.selectColumns = Lists.newArrayList();
    }

    private SelectBuilder(String... columns) {
        checkNotNull(columns, "Wrong arguments");
        selectColumns = Arrays.stream(columns).map(ColumnOperand::name).collect(toList());
    }

    private SelectBuilder(NameOperand... operands) {
        checkNotNull(operands, "Wrong arguments");
        selectColumns = Lists.newArrayList(operands);
    }

    public SelectBuilder column(String... columns) {
        List<ColumnOperand> columnOperands = Arrays.stream(columns).map(ColumnOperand::name).collect(toList());
//        if (log.isDebugEnabled()) {
//            log.debug("Add column {}", columnOperands);
//        }
        selectColumns.addAll(columnOperands);
        return this;
    }

    public SelectBuilder column(NameOperand... operands) {
        selectColumns.addAll(Arrays.asList(checkNotNull(operands, "Operands shouldn't be null")));
        return this;
    }

    @Override
    public String toString() {
        checkState(isNotEmpty(selectColumns), "Wrong usage, no column selected");
        return selectColumns.stream().map(selectColumn -> selectColumn.toString(true))
            .filter(StringUtils::isNotBlank).collect(joining(", ", "SELECT ", ""));
    }

    public FromBuilder from(String tableName) {
        return new FromBuilder(tableName, this);
    }

    public static SelectBuilder select() {
        return new SelectBuilder();
    }

    public static SelectBuilder select(String... columns) {
        return new SelectBuilder(columns);
    }

    public static SelectBuilder select(NameOperand... operands) {
        return new SelectBuilder(operands);
    }
}
