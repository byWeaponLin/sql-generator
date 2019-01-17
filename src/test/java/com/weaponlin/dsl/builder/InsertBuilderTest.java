package com.weaponlin.dsl.builder;

import com.weaponlin.dsl.BaseTest;
import com.weaponlin.dsl.DSL;
import com.weaponlin.dsl.operand.table.TableOperand;
import org.junit.Test;

import static com.weaponlin.dsl.operand.table.TableOperand.*;
import static org.junit.Assert.*;

public class InsertBuilderTest extends BaseTest {
    @Test
    public void test_insert_success() {
        String sql = DSL.insert()
                .into(table("students"))
                .columns("id", "name", "score")
                .values(1, "weapon lin", 100)
                .build();
        assertEquals("INSERT INTO students(id, name, score) VALUES(?, ?, ?)", sql);
    }

    @Test
    public void test_insert_multiple_columns_and_values_success() {
        String sql = DSL.insert()
                .into(table("students"))
                .columns("id", "name")
                .columns("score")
                .values(1)
                .values( "weapon lin", 100)
                .build();
        assertEquals("INSERT INTO students(id, name, score) VALUES(?, ?, ?)", sql);
    }

    @Test
    public void test_insert_throw_exception_if_no_columns() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("columns's size must be greater than zero");

        DSL.insert()
                .into(table("students"))
                .values(1, "weapon lin", 100)
                .build();
    }

    @Test
    public void test_insert_throw_exception_if_no_values() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("values's size must be greater than zero");

        DSL.insert()
                .into(table("students"))
                .columns("id", "name", "score")
                .build();
    }

    @Test
    public void test_insert_throw_exception_if_column_size_not_equal_value_size() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Illegal SQL, columns's size is not equal to values'size, column size = 3, value size = 2");
        DSL.insert()
                .into(table("students"))
                .columns("id", "name", "score")
                .values(1, "weapon lin")
                .build();
    }
}