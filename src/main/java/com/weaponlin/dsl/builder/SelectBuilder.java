package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.operand.transform.ColumnOperand;
import com.weaponlin.dsl.operand.transform.TransformOperand;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * TODO add aggregate function
 */
public class SelectBuilder implements Serializable {
    private static final long serialVersionUID = 5394178704845865940L;

    private List<TransformOperand> columns;

    public SelectBuilder() {
        this.columns = Lists.newArrayList();
    }

    public SelectBuilder(String... columns) {
        checkNotNull(columns, "Wrong arguments");
        this.columns = Arrays.stream(columns).map(ColumnOperand::column).collect(toList());
    }

    public SelectBuilder(ColumnOperand... operands) {
        checkNotNull(operands, "Wrong arguments");
        columns = Lists.newArrayList(operands);
    }

    public SelectBuilder column(String... columns) {
        List<ColumnOperand> columnOperands = Arrays.stream(columns).map(ColumnOperand::column).collect(toList());
        this.columns.addAll(columnOperands);
        return this;
    }

    public SelectBuilder column(ColumnOperand... operands) {
        columns.addAll(Arrays.asList(checkNotNull(operands, "Operands shouldn't be null")));
        return this;
    }

    @Override
    public String toString() {
        checkState(isNotEmpty(columns), "Wrong usage, no column selected");
        return columns.stream().map(selectColumn -> selectColumn.toString(true))
            .filter(StringUtils::isNotBlank).collect(joining(", ", "SELECT ", ""));
    }

    public FromBuilder from(String tableName) {
        return new FromBuilder(tableName, this);
    }

    @Deprecated
    public static SelectBuilder select() {
        return new SelectBuilder();
    }

    @Deprecated
    public static SelectBuilder select(String... columns) {
        return new SelectBuilder(columns);
    }

    @Deprecated
    public static SelectBuilder select(ColumnOperand... operands) {
        return new SelectBuilder(operands);
    }
}
