package com.weaponlin;

import com.google.common.collect.Lists;
import com.weaponlin.AlertBasicSeverityLevel.Condition;
import com.weaponlin.enums.Comparator;
import com.weaponlin.enums.Threshold;

import java.util.List;

import static com.weaponlin.enums.AlertAlgorithm.AVG;
import static com.weaponlin.enums.AlertAlgorithm.SUM;
import static com.weaponlin.enums.Threshold.STATIC;
import static com.weaponlin.enums.Threshold.SubThreshold.*;

public class RuleSQLGenerator {

    public static void main(String[] args) {
        AlertBasicSeverityLevel severityLevel = new AlertBasicSeverityLevel()
                .setRuleId("rule_id")
                .setBoth(true)
                .setSeverity("warning")
                .setLastTime(10)
                .setThreshold(STATIC)
                .setConditions(conditions());
        String sql = SqlCreatorFactory.generateSql(severityLevel);
        System.out.println(sql);
    }

    private static List<Condition> conditions() {
        return Lists.newArrayList(
                new Condition()
                        .setSubThreshold(FREQUENCY)
                        .setMetric("CpuUsage")
                        .setAlgorithm(SUM)
                        .setOperator(Comparator.GT)
                        .setCount(0.80),
                new Condition()
                        .setSubThreshold(Threshold.SubThreshold.NUMERICAL)
                        .setMetric("MemoryUsage")
                        .setAlgorithm(AVG)
                        .setOperator(Comparator.GT)
                        .setCount(2048.0)
                );
    }
}
