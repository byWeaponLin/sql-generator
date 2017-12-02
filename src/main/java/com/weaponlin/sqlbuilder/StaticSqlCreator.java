package com.weaponlin.sqlbuilder;


import com.weaponlin.AlertBasicSeverityLevel;
import com.weaponlin.AlertBasicSeverityLevel.Condition;
import com.weaponlin.dsl.builder.FromBuilder;
import com.weaponlin.dsl.builder.SelectBuilder;
import com.weaponlin.dsl.builder.WhereBuilder;
import com.weaponlin.dsl.operand.BitwiseOperand;
import com.weaponlin.dsl.operand.FunctionOperand;
import com.weaponlin.dsl.operand.TransformOperand;
import com.weaponlin.dsl.operator.BooleanOperator;
import com.weaponlin.dsl.operator.FunctionOperator;
import com.weaponlin.dsl.operator.Operator;

import java.util.List;

import static com.weaponlin.dsl.builder.SelectBuilder.select;
import static com.weaponlin.dsl.operand.ColumnOperand.name;
import static com.weaponlin.dsl.operand.VariableOperand.variable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class StaticSqlCreator implements SqlCreator {

    @Override
    //TODO 同一个指标的同一条件要取最值
    public String ruleSql(AlertBasicSeverityLevel condition) {
        SelectBuilder select = select();
        List<BitwiseOperand> bitwiseOperands = condition.getConditions().stream()
            .collect(toMap(cond -> cond, cond -> convertToTransform(cond)))
            .entrySet().stream().map(kv -> {
                TransformOperand value = kv.getValue();
                select.column(value);

                return convertToBitwise(kv.getKey(), kv.getValue());
            }).collect(toList());
        FromBuilder from = select.from(condition.getRuleId());
        WhereBuilder where = bitwiseOperands.stream().reduce(from.where(), BooleanOperator.getFunction(condition.isBoth()), (w1, w2) -> w2);

        return where.forMinutes(condition.getLastTime()).build();
    }

    private TransformOperand convertToTransform(Condition condition) {
        String columnName = condition.getMetric();
        FunctionOperator function = condition.functionOperator();

        return function.getFunction().apply(name(columnName));
    }

    private BitwiseOperand convertToBitwise(Condition condition, TransformOperand operand) {
        Operator<BitwiseOperand> compare = condition.comparatorType();
        if (operand instanceof FunctionOperand) {
            return compare.getFunction().apply(operand, variable(condition.uniqueCount()));
        }

        throw new IllegalArgumentException("wrong args");
    }
}
