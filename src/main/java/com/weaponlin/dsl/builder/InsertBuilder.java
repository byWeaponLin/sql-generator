package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.SQLParameter;
import com.weaponlin.dsl.operand.Operand;
import com.weaponlin.dsl.operand.table.TableOperand;
import com.weaponlin.dsl.operand.transform.ColumnOperand;
import com.weaponlin.dsl.operand.transform.PlaceholderOperand;
import com.weaponlin.dsl.operand.transform.VariableOperand;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.util.stream.Collectors.joining;

public class InsertBuilder implements Builder {
    private static final long serialVersionUID = -6682031211557475666L;

    private TableOperand table;

    private List<ColumnOperand> columns;

    private List<VariableOperand> values;

    public InsertBuilder() {
        this.columns = Lists.newArrayList();
        this.values = Lists.newArrayList();
    }

    public InsertBuilder into(TableOperand table) {
        checkNotNull(table, "table can not be null");
        this.table = table;
        return this;
    }

    public InsertBuilder columns(String... columns) {
        checkNotNull(columns, "columns can not be null");
        this.columns.addAll(Arrays.stream(columns)
                .filter(StringUtils::isNotBlank)
                .map(ColumnOperand::name)
                .collect(Collectors.toList())
        );
        return this;
    }

    public InsertBuilder values(Object... values) {
        checkNotNull(values, "values can not be null");
        this.values.addAll(Arrays.stream(values)
                .map(PlaceholderOperand::value)
                .collect(Collectors.toList())
        );
        return this;
    }

    @Override
    public String toString() {
        checkState(columns.size() > 0, "columns's size must be greater than zero");
        checkState(values.size() > 0, "values's size must be greater than zero");
        checkState(columns.size() == values.size(),
                "Illegal SQLParameter, columns's size is not equal to values'size, column size = %s, value size = %s",
                columns.size(),
                values.size());

        return "INSERT INTO "
                + table.toString(false)
                + columns.stream().map(e -> e.toString(false)).collect(joining(", ", "(", ")"))
                + " VALUES"
                + Collections.nCopies(values.size(), "?").stream().collect(joining(", ", "(", ")"));
    }

    @Override
    public SQLParameter build() {
        return new SQLParameter(toString(), getParameters());
    }

    @Override
    public List<Object> getParameters() {
        return values.stream()
                .map(Operand::getParameters)
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
