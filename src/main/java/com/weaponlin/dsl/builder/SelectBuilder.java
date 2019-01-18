package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.SQLParameter;
import com.weaponlin.dsl.operand.Operand;
import com.weaponlin.dsl.operand.expression.ExpressionOperand;
import com.weaponlin.dsl.operand.table.TableOperand;
import com.weaponlin.dsl.operand.transform.ColumnOperand;
import com.weaponlin.dsl.operand.transform.TransformOperand;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;
import static com.weaponlin.dsl.operand.table.TableOperand.table;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class SelectBuilder implements Builder {
    private static final long serialVersionUID = 5394178704845865940L;

    private List<ExpressionOperand> columns;

    /**
     * TODO save select filed name(column name, alias, full name and so on)
     */
    @Getter
    private List<RowMap> rowMaps;

    public SelectBuilder() {
        this.columns = Lists.newArrayList();
        this.rowMaps = Lists.newArrayList();
    }

    public SelectBuilder column(String... columns) {
        checkNotNull(columns, "Operands shouldn't be null");
        List<ExpressionOperand> columnOperands = Arrays.stream(columns)
                .map(ColumnOperand::name)
                .map(ColumnOperand::toExpression)
                .collect(toList());
        this.columns.addAll(columnOperands);
        setRowMap(columns);
        return this;
    }

    public SelectBuilder column(ExpressionOperand... operands) {
        checkNotNull(operands, "Operands shouldn't be null");
        columns.addAll(Arrays.asList(operands));
        setRowMap(operands);
        return this;
    }

    public SelectBuilder column(TransformOperand... operands) {
        checkNotNull(operands, "Operands shouldn't be null");
        columns.addAll(Arrays.stream(operands).map(TransformOperand::toExpression).collect(toList()));
        setRowMap(operands);
        return this;
    }

    @Override
    public String toString() {
        // TODO if column is empty then select ${all columns}
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
    public SQLParameter build() {
        return new SQLParameter(toString(), getParameters(), rowMaps);
    }

    @Override
    public List<Object> getParameters() {
        checkState(isNotEmpty(columns), "Wrong usage, no name selected");
        return columns.stream()
                .map(ExpressionOperand::getParameters)
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    private void setRowMap(String... columns) {
        // TODO get current method's result type, and then analysise result type's properties mapping to fields
        rowMaps.addAll(Arrays.stream(columns).map(e -> new RowMap().setProperty(e)).collect(toList()));
    }

    private void setRowMap(Operand... operands) {
        // TODO get current method's result type, and then analysise result type's properties mapping to fields
        List<RowMap> maps = Arrays.stream(operands)
                .map(operand -> Optional.ofNullable(operand.getAlias())
                        .filter(StringUtils::isNotBlank)
                        .orElse(operand.toString(false)))
                .map(p -> new RowMap().setProperty(p))
                .collect(Collectors.toList());
        rowMaps.addAll(maps);
    }

    @Data
    @Accessors(chain = true)
    public static class RowMap {
        private String property;
        private Class javaType;

        private String column;
        private Class jdbcType;

        private Function typeHandler;
    }
}
