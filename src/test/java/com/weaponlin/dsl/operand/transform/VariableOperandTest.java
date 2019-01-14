package com.weaponlin.dsl.operand.transform;

import org.junit.Test;

import static org.junit.Assert.*;

public class VariableOperandTest {

    @Test
    public void test_success() {
        VariableOperand values = VariableOperand.values(1, 2, 3);
        assertEquals("?, ?, ?", values.toString());
    }
}