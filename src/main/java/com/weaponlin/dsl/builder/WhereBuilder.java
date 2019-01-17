package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.SQLParameter;
import com.weaponlin.dsl.enums.BooleanOperator;
import com.weaponlin.dsl.operand.Operand;
import com.weaponlin.dsl.operand.expression.ExpressionOperand;
import com.weaponlin.dsl.operand.transform.ColumnOperand;
import com.weaponlin.dsl.operand.transform.TransformOperand;
import com.weaponlin.dsl.operand.transform.VariableOperand;
import lombok.NonNull;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.weaponlin.dsl.operand.transform.PlaceholderOperand.value;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * TODO having count
 */
public class WhereBuilder implements Builder {
    private static final long serialVersionUID = 1826524948263871298L;

    private Builder previousBuilder;
    private final List<ExpressionOperand> operands = Lists.newArrayList();
    private volatile BooleanOperator booleanIdentifier;

    private List<ColumnOperand> groupByColumns = Lists.newArrayList();

    private List<VariableOperand> limitOperands;

    WhereBuilder(Builder previousBuilder) {
        this.previousBuilder = checkNotNull(previousBuilder, "previousBuilder can not be null");
    }

    public WhereBuilder and(ExpressionOperand operand) {
        checkIdentificationStatus(BooleanOperator.AND);
        checkNotNull(operand, "BitwiseOperand shouldn't be null");
        operands.add(operand);
        return this;
    }

    public WhereBuilder or(ExpressionOperand operand) {
        checkIdentificationStatus(BooleanOperator.OR);
        checkNotNull(operand, "BitwiseOperand shouldn't be null");
        operands.add(operand);
        return this;
    }

    public WhereBuilder groupBy(String... columns) {
        checkNotNull(columns, "group by columns can not be null");
        groupByColumns.addAll(
                Arrays.stream(columns)
                        .filter(StringUtils::isNotBlank)
                        .map(ColumnOperand::name)
                        .collect(toList())
        );
        return this;
    }

    public WhereBuilder groupBy(ColumnOperand... columns) {
        checkNotNull(columns, "group by columns can not be null");
        groupByColumns.addAll(Arrays.asList(columns));
        return this;
    }

    public WhereBuilder limit(@NonNull Integer offset, @NonNull Integer limit) {
        this.limitOperands = Lists.newArrayList(value(offset), value(limit));
        return this;
    }

    public WhereBuilder limit(@NonNull Integer limit) {
        this.limitOperands = Lists.newArrayList(value(limit));
        return this;
    }

    /**
     * TODO NOT SUPPORT NOW
     */
    public WhereBuilder having(TransformOperand operand) {

        return this;
    }

    private void checkIdentificationStatus(BooleanOperator identifier) {
        if (booleanIdentifier == null) {
            booleanIdentifier = identifier;
        } else if (booleanIdentifier != identifier) {
            throw new IllegalStateException("AND and OR shouldn't be used in the same time");
        }
    }

    private String conditionString() {
        if (CollectionUtils.isEmpty(operands)) {
            return "";
        }
        return operands.stream()
                .map(bit -> bit.toString(false))
                .collect(joining(booleanIdentifier.getOperator(), " WHERE ", ""));
    }

    private String groupByString() {
        return Optional.ofNullable(groupByColumns)
                .filter(CollectionUtils::isNotEmpty)
                .map(list -> list.stream()
                        .map(e -> e.toString(false))
                        .collect(joining(", ", " GROUP BY ", "")))
                .orElse("");
    }

    private String limitString() {
        return Optional.ofNullable(limitOperands)
                .filter(CollectionUtils::isNotEmpty)
                .map(list -> list.stream()
                        .map(e -> e.toString(false))
                        .collect(joining(", ", " LIMIT ", ""))
                ).orElse("");
    }

    @Override
    public String toString() {
        return previousBuilder.toString() + conditionString() + groupByString() + limitString();
    }

    @Override
    public SQLParameter build() {
        return new SQLParameter(toString(), getParameters());
    }

    @Override
    public List<Object> getParameters() {
        List<Object> conditionParameters = Optional.ofNullable(operands).map(ops -> ops.stream()
                .map(ExpressionOperand::getParameters)
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream)
                .collect(toList())
        ).orElse(Lists.newArrayList());
        List<Object> limitParameters = Optional.ofNullable(limitOperands).map(ops -> ops.stream()
                .map(Operand::getParameters)
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream)
                .collect(toList())
        ).orElse(Lists.newArrayList());
        return Stream.of(previousBuilder.getParameters(), conditionParameters, limitParameters)
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream)
                .collect(toList());
    }
}
