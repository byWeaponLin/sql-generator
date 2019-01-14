package com.weaponlin.dsl;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BaseTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    protected void equals(List<Object> parameters, Object[] objects) {
        assertEquals(parameters.size(), objects.length);
        for (int i = 0; i < objects.length; i++) {
            assertEquals(parameters.get(i), objects[i]);
        }
    }
}
