package com.weaponlin;

import com.weaponlin.dsl.operand.BitwiseOperand;
import com.weaponlin.dsl.operator.CompareOperator;
import com.weaponlin.dsl.operator.FunctionOperator;
import com.weaponlin.dsl.operator.Operator;
import com.weaponlin.enums.AlertAlgorithm;
import com.weaponlin.enums.Comparator;
import com.weaponlin.enums.DynamicTrend;
import com.weaponlin.enums.Threshold;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.*;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * 告警条件实体类，用来生成sql语句
 */
@Data
@Accessors(chain = true)
public class AlertBasicSeverityLevel implements Serializable{

    private String severity; // WARNING/CRITICAL
    private int lastTime;
    private boolean both;
    private Threshold threshold;
    private List<Condition> conditions;

    private transient String ruleId;

    @Data
    @Accessors(chain = true)
    public static class Condition implements Serializable{

        private Threshold.SubThreshold subThreshold;
        private String metric;
        private int frequencyTime;
        private AlertAlgorithm algorithm;
        private Comparator operator;
        private DynamicTrend trend;
        private double count;


        public FunctionOperator functionOperator() {
            return FunctionOperator.valueOf(algorithm.toString().toUpperCase());
        }

        public Operator<BitwiseOperand> comparatorType() {
            return CompareOperator.valueOf(operator.toString().toUpperCase());
        }

        // 为了生成每个变量唯一的代表串，写了这个一个恶心的东西，后期有可能再改
        private String createUniqueFlag() {
            StringBuilder sb = new StringBuilder();
            sb.append(metric).append("_");
            sb.append(Optional.ofNullable(subThreshold).map(Enum::toString).orElse("dynamic")).append("_");
            sb.append(algorithm.toString()).append("_");
            sb.append(Optional.ofNullable(trend).map(Enum::toString).orElse("trend")).append("_");
            sb.append(operator.toString()).append("_");
            return sb.toString().replaceAll("\\.", "_").toLowerCase();
        }

        /**
         * placeholder for sql
         * @return
         */
        public String uniqueCount() {
            return createUniqueFlag() + "count";
        }

        /**
         * placeholder for sql
         * @return
         */
        public String uniqueTimes() {
            return createUniqueFlag() + "times";
        }
    }

    public List<Condition> mergedCondition() {
        checkArgument(!isEmpty(conditions), "conditions shouldn't be null or empty");
        return conditions.stream()
            .collect(groupingBy(Condition::getMetric))
            .entrySet()
            .stream()
            .collect(toMap(Map.Entry::getKey, entry -> doGetMergedCondition(entry.getValue())))
            .entrySet().stream().map(Map.Entry::getValue).flatMap(List::stream).collect(toList());
    }

    // TODO merge conditions
    private List<Condition> doGetMergedCondition(List<Condition> conditions) {

        return conditions;
    }
}
