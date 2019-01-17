package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.SQLParameter;
import com.weaponlin.dsl.enums.CompareOperator;
import com.weaponlin.dsl.operand.expression.CompareExpressionOperand;
import com.weaponlin.dsl.operand.expression.ExpressionOperand;
import com.weaponlin.dsl.operand.table.TableOperand;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class UpdateBuilder implements Builder {
    private static final long serialVersionUID = 4651243308668163270L;

    private TableOperand table;

    private List<CompareExpressionOperand> assignments;

    public UpdateBuilder() {
        assignments = Lists.newArrayList();
    }

    public UpdateBuilder table(String table) {
        checkArgument(StringUtils.isNotBlank(table), "table name can not be empty");
        return table(TableOperand.table(table));
    }

    public UpdateBuilder table(TableOperand table) {
        checkNotNull(table, "table can not be null");
        this.table = table;
        return this;
    }

    public UpdateBuilder set(CompareExpressionOperand assignment) {
        checkNotNull(assignment, "assignment can not be null");
        checkNotNull(assignment.getOperator() == CompareOperator.EQ, "assignment only support EQ-Operator(=)");
        assignments.add(assignment);
        return this;
    }

    public WhereBuilder where() {
        return new WhereBuilder(this);
    }

    @Override
    public String toString() {
        checkState(isNotEmpty(assignments), "no assignments, please check sql");
        checkNotNull(table, "table can not be null");
        return "UPDATE "
                + table.toString(false)
                + assignments.stream().map(e -> e.toString(false)).collect(joining(", ", " SET ", ""));
    }

    @Override
    public SQLParameter build() {
        return new SQLParameter(toString(), getParameters());
    }

    @Override
    public List<Object> getParameters() {
        return assignments.stream()
                .map(ExpressionOperand::getParameters)
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream)
                .collect(toList());
    }
}
