package com.weaponlin.dsl.operand.transform;

import com.weaponlin.dsl.BaseTest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlaceholderOperandTest extends BaseTest {

    @Test
    public void test_values_success() {
        Object[] objects = {1, 2.1, 3.1f, 'a', true};
        VariableOperand values = PlaceholderOperand.values(objects);
        assertEquals("?, ?, ?, ?, ?", values.toString());
        List<Object> parameters = values.getParameters();
        equals(parameters, objects);
    }

    @Test
    public void test_values_throw_exception_if_no_parameters() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("values's size can not be zero.");
        VariableOperand values = PlaceholderOperand.values();
    }

    @Test
    public void test_values_throw_exception_if_parameter_is_null() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("values can not be null, If only and have a null value, please use new Object[]{null}.");
        VariableOperand values = PlaceholderOperand.values(null);
    }

    @Test
    public void test_values_success_if_has_a_null_value() {
        Object[] objects = {null};
        VariableOperand values = PlaceholderOperand.values(objects);
        assertEquals("?", values.toString());
        List<Object> parameters = values.getParameters();
        equals(parameters, objects);
    }

    @Test
    public void test_value_success() {
        Object object = 1;
        VariableOperand value = PlaceholderOperand.value(object);
        assertEquals("?", value.toString());
        List<Object> parameters = value.getParameters();
        equals(parameters, new Object[]{object});
    }
}