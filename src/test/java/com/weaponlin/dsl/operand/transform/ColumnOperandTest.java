package com.weaponlin.dsl.operand.transform;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ColumnOperandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void column_success() {
        ColumnOperand column = ColumnOperand.column("id");
        assertEquals("id", column.toString());
        column.as("_id");
        assertEquals("id as _id", column.toString());
    }

    @Test
    public void throw_exception_if_column_name_is_null() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("column name can not be empty");
        ColumnOperand.column((String) null);
    }

    @Test
    public void throw_exception_if_column_name_is_empty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("column name can not be empty");
        ColumnOperand.column("");
    }
}