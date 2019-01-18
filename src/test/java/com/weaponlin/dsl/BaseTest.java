package com.weaponlin.dsl;

import com.weaponlin.dsl.builder.SelectBuilder;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BaseTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    protected void equals(List<Object> parameters, Object[] objects) {
        assertEquals(parameters.size(), objects.length);
        for (int i = 0; i < objects.length; i++) {
            assertEquals(parameters.get(i), objects[i]);
        }
    }

    protected void assertEquals2(List<Object> expectedParameters, List<Object> actualParameters) {
        assertEquals(expectedParameters.size(), actualParameters.size());
        for (int i = 0; i < actualParameters.size(); i++) {
            assertEquals(expectedParameters.get(i), actualParameters.get(i));
        }
    }

    protected List<Object> getProperties(SQLParameter sqlParameter) {
        assertNotNull(sqlParameter);
        return sqlParameter.getRowMaps().stream()
                .map(SelectBuilder.RowMap::getProperty)
                .collect(Collectors.toList());
    }
}
