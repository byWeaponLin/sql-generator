package com.weaponlin.dsl.builder;

import com.weaponlin.dsl.BaseTest;
import com.weaponlin.dsl.DSL;
import org.junit.Test;

import static com.weaponlin.dsl.operand.transform.ColumnOperand.name;
import static com.weaponlin.dsl.operand.transform.PlaceholderOperand.value;
import static org.junit.Assert.assertEquals;

public class UpdateBuilderTest extends BaseTest {

    @Test
    public void test_update_success() {
        String sql = DSL.update().table("students")
                .set(name("score").eq(value(100)))
                .where()
                .and(name("class_id").eq(value(1)))
                .and(name("name").eq(value("Weapon Lin")))
                .build();
        assertEquals("UPDATE students SET score = ? WHERE class_id = ? AND name = ?", sql);
    }

    @Test
    public void test_update_without_condition_success() {
        String sql = DSL.update().table("students")
                .set(name("score").eq(value(100)))
                .build();
        assertEquals("UPDATE students SET score = ?", sql);
    }

    @Test
    public void test_update_throw_exception_if_table_is_null() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("table can not be null");
        DSL.update().set(name("class_id").eq(value(1))).build();
    }

    @Test
    public void test_update_throw_exception_if_no_assignments() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("no assignments, please check sql");
        DSL.update().table("students").build();
    }
}