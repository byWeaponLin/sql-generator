package com.weaponlin.sqlbuilder;

import com.weaponlin.AlertBasicSeverityLevel;
import com.weaponlin.AlertBasicSeverityLevel.Condition;
import com.weaponlin.dsl.builder.FromBuilder;
import com.weaponlin.dsl.builder.SelectBuilder;
import com.weaponlin.dsl.builder.WhereBuilder;
import com.weaponlin.dsl.operand.*;
import com.weaponlin.dsl.operator.BooleanOperator;
import com.weaponlin.dsl.operator.CompareOperator;
import com.weaponlin.dsl.operator.FunctionOperator;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import static com.weaponlin.dsl.builder.SelectBuilder.select;
import static com.weaponlin.dsl.operand.ColumnOperand.name;
import static com.weaponlin.dsl.operand.NumberOperand.num;
import static com.weaponlin.dsl.operand.PeriodOperand.period;
import static com.weaponlin.dsl.operand.VariableOperand.variable;
import static java.util.stream.Collectors.toList;

/**
 * 动态sql
 */
public class DynamicSqlCreator implements SqlCreator {

    @Override
    public String ruleSql(AlertBasicSeverityLevel condition) {
        SelectBuilder select = select();
        List<BitwiseOperand> bitwiseOperands = condition.getConditions().stream()
                // map is unordered, so it will get something wrong when we test.
//            .collect(toMap(cond -> cond, cond -> convertToTransform(cond)))
//            .entrySet().stream().map(kv -> {
              .map(cond -> Pair.of(cond,convertToTransform(cond)))
               .map(kv -> {
                TransformOperand value = kv.getValue();
                if (value instanceof PeriodOperand) {
                    select.column(((PeriodOperand) value).getFunc());
                }
                select.column(value);

                return convertToBitwise(kv.getKey(), kv.getValue());
            }).collect(toList());
        FromBuilder from = select.from(condition.getRuleId());
        WhereBuilder where = bitwiseOperands.stream().reduce(from.where(), BooleanOperator.getFunction(condition.isBoth()), (w1, w2) -> w2);

        return where.forMinutes(condition.getLastTime()).build();
    }

    /**
     * only period operand
     * @param condition
     * @return
     */
    private TransformOperand convertToTransform(Condition condition) {
        String columnName = condition.getMetric();
        FunctionOperator function = condition.functionOperator();

        //forever value for min(60) and day(15)
        return period(function.getFunction().apply(name(columnName)),60,15);
    }

    private BitwiseOperand convertToBitwise(Condition condition, TransformOperand operand) {
        CompareOperator compare = (CompareOperator)condition.comparatorType();
        CalculateOperand calculateOperand = compare.getCalculateOperator().getFunction().apply(num(1), variable(condition.uniqueCount())).multiply(operand);

        return condition.comparatorType().getFunction().apply(convertToFunction(condition), calculateOperand);
    }

    private FunctionOperand convertToFunction(Condition condition) {
        String columnName = condition.getMetric();
        FunctionOperator function = condition.functionOperator();

        return function.getFunction().apply(name(columnName));
    }
}
