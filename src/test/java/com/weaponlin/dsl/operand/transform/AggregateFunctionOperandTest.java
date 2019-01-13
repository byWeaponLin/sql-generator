package com.weaponlin.dsl.operand.transform;

import org.junit.Test;

import static com.weaponlin.dsl.operand.transform.ColumnOperand.*;
import static org.junit.Assert.assertEquals;

public class AggregateFunctionOperandTest {


    @Test
    public void test_max_success() {
        AggregateFunctionOperand max = AggregateFunctionOperand.max("score");
        assertEquals("MAX(score)", max.toString());
        max.as("max_score");
        assertEquals("MAX(score) AS max_score", max.toString());
    }

    @Test
    public void test_min_success() {
        AggregateFunctionOperand min = AggregateFunctionOperand.min("score");
        assertEquals("MIN(score)", min.toString());
        min.as("min_score");
        assertEquals("MIN(score) AS min_score", min.toString());
    }

    @Test
    public void test_avg_success() {
        AggregateFunctionOperand avg = AggregateFunctionOperand.avg(column("score"));
        assertEquals("AVG(score)", avg.toString());
        avg.as("avg_score");
        assertEquals("AVG(score) AS avg_score", avg.toString());
    }
}