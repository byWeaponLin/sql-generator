package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.operand.expression.ExpressionOperand;
import com.weaponlin.dsl.operand.table.TableOperand;
import com.weaponlin.dsl.operand.transform.ColumnOperand;
import com.weaponlin.dsl.operand.transform.TransformOperand;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.weaponlin.dsl.operand.table.TableOperand.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class SelectBuilder implements Serializable, Builder {
    private static final long serialVersionUID = 5394178704845865940L;

    private List<ExpressionOperand> columns;

    /**
     * TODO save select filed name(column name, alias, full name and so on)
     */
    private List<String> fields;

    /**
     * TODO save parameters
     */
    private List<Object> parameters;

    public SelectBuilder() {
        this.columns = Lists.newArrayList();
    }

    public SelectBuilder column(String... columns) {
        List<ExpressionOperand> columnOperands = Arrays.stream(columns)
                .map(ColumnOperand::name)
                .map(ColumnOperand::toExpression)
                .collect(toList());
        this.columns.addAll(columnOperands);
        return this;
    }

    public SelectBuilder column(ExpressionOperand... operands) {
        checkNotNull(operands, "Operands shouldn't be null");
        columns.addAll(Arrays.asList(operands));
        return this;
    }

    public SelectBuilder column(TransformOperand... operands) {
        checkNotNull(operands, "Operands shouldn't be null");
        columns.addAll(Arrays.stream(operands).map(TransformOperand::toExpression).collect(toList()));
        return this;
    }

    @Override
    public String toString() {
        // TODO if column is empty then select *
        checkState(isNotEmpty(columns), "Wrong usage, no name selected");
        return columns.stream().map(selectColumn -> selectColumn.toString(true))
                .filter(StringUtils::isNotBlank).collect(joining(", ", "SELECT ", ""));
    }

    public FromBuilder from(TableOperand operand) {
        return new FromBuilder(operand, this);
    }

    public FromBuilder from(String table) {
        checkArgument(StringUtils.isNotBlank(table), "table name can not be empty");
        return from(table(table));
    }

    @Override
    public String build() {
        return this.toString();
    }
}
