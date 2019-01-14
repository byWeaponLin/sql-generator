package com.weaponlin.dsl.operand.transform;

import com.weaponlin.dsl.BaseTest;
import lombok.NonNull;
import org.junit.Test;

import java.util.List;

import static com.weaponlin.dsl.operand.transform.ValueOperand.*;
import static org.junit.Assert.*;

public class ArithmeticOperandTest extends BaseTest {

    @Test
    public void test_arithmetic_with_placeholder_success() {
        ArithmeticOperand operand = ColumnOperand.name("score").add(PlaceholderOperand.value(5));
        assertEquals("score + ?", operand.toString());
        equals(operand, new Object[]{5});

        operand = ColumnOperand.name("score").minus(PlaceholderOperand.value(5));
        assertEquals("score - ?", operand.toString());
        equals(operand, new Object[]{5});

        operand = ColumnOperand.name("score").multiply(PlaceholderOperand.value(5));
        assertEquals("score * ?", operand.toString());
        equals(operand, new Object[]{5});

        operand = ColumnOperand.name("score").divide(PlaceholderOperand.value(5));
        assertEquals("score / ?", operand.toString());
        equals(operand, new Object[]{5});

        operand = ColumnOperand.name("score").and(PlaceholderOperand.value(5));
        assertEquals("score & ?", operand.toString());
        equals(operand, new Object[]{5});

        operand = ColumnOperand.name("score").or(PlaceholderOperand.value(5));
        assertEquals("score | ?", operand.toString());
        equals(operand, new Object[]{5});
    }

    @Test
    public void test_arithmetic_with_value_success() {
        ArithmeticOperand operand = ColumnOperand.name("score").add(value(5));
        assertEquals("score + 5", operand.toString());

        operand = ColumnOperand.name("score").minus(value(5));
        assertEquals("score - 5", operand.toString());

        operand = ColumnOperand.name("score").multiply(value(5));
        assertEquals("score * 5", operand.toString());

        operand = ColumnOperand.name("score").divide(value(5));
        assertEquals("score / 5", operand.toString());

        operand = ColumnOperand.name("score").and(value(5));
        assertEquals("score & 5", operand.toString());

        operand = ColumnOperand.name("score").or(value(5));
        assertEquals("score | 5", operand.toString());
    }

    @Test
    public void test_multiple_arithmetic_success() {
        ArithmeticOperand operand = ColumnOperand.name("score").add(PlaceholderOperand.value(5)).minus(PlaceholderOperand.value(10));
        assertEquals("score + ? - ?", operand.toString());
        List<Object> parameters = operand.getParameters();
        equals(parameters, new Object[]{5, 10});
    }

    @Test
    public void test_and_with_function_success() {
        ArithmeticOperand operand = AggregateFunctionOperand.max("score")
                .add(PlaceholderOperand.value(10))
                .minus(ColumnOperand.name("age"))
                .as("what");
        assertEquals("MAX(score) + ? - age AS what", operand.toString());
        List<Object> parameters = operand.getParameters();
        equals(parameters, new Object[]{10});
    }

    @Test
    public void throw_exception_if_alias_is_empty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("alias can not be empty");
        ColumnOperand.name("name").add(value(1)).as("");

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("alias can not be empty");
        ColumnOperand.name("name").add(value(1)).as(null);
    }

    private void equals(@NonNull ArithmeticOperand operand, Object[] objects) {
        equals(operand.getParameters(), objects);
    }
}