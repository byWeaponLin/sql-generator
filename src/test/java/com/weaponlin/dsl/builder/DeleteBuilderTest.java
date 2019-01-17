package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.BaseTest;
import com.weaponlin.dsl.DSL;
import com.weaponlin.dsl.SQLParameter;
import org.junit.Test;

import static com.weaponlin.dsl.operand.table.TableOperand.table;
import static com.weaponlin.dsl.operand.transform.ColumnOperand.name;
import static com.weaponlin.dsl.operand.transform.PlaceholderOperand.value;
import static com.weaponlin.dsl.operand.transform.PlaceholderOperand.values;
import static org.junit.Assert.assertEquals;

public class DeleteBuilderTest extends BaseTest {

    @Test
    public void test_delete_success() {
        SQLParameter sqlParameter = DSL.delete().from(table("students"))
                .where().and(name("class_id").eq(value(1)))
                .and(name("name").like_(value("林")))
                .build();
        assertEquals("DELETE FROM students WHERE class_id = ? AND name LIKE ?", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(1, "林%"), sqlParameter.getParameters());
    }

    @Test
    public void test_delete_with_between_in_condition_success() {
        SQLParameter sqlParameter = DSL.delete().from(table("students"))
                .where().and(name("class_id").ge(value(1)))
                .and(name("name").like_(value("林")))
                .and(name("age").in(values(11, 12, 13)))
                .and(name("score").betweenAnd(value(80), value(100)))
                .build();
        assertEquals("DELETE FROM students WHERE class_id >= ? AND name LIKE ? AND age IN (?, ?, ?) AND score BETWEEN ? AND ?", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(1, "林%", 11, 12, 13, 80, 100), sqlParameter.getParameters());
    }

    @Test
    public void test_delete_throw_exception_if_no_from() {
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("DeleteBuilder not support this operation");
        DSL.delete().build();
    }
}