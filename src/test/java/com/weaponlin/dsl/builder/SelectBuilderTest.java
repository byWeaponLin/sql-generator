package com.weaponlin.dsl.builder;

import com.weaponlin.dsl.BaseTest;
import com.weaponlin.dsl.DSL;
import com.weaponlin.dsl.operand.transform.PlaceholderOperand;
import org.junit.Test;

import static com.weaponlin.dsl.operand.transform.AggregateFunctionOperand.max;
import static com.weaponlin.dsl.operand.transform.ColumnOperand.name;
import static com.weaponlin.dsl.operand.transform.ValueOperand.value;
import static org.junit.Assert.assertEquals;

public class SelectBuilderTest extends BaseTest {

    @Test
    public void test_string_columns_success() {
        String sql = DSL.select().column("id").column("name").column("score").build();
        assertEquals("SELECT id, name, score", sql);

        sql = DSL.select().column("id", "name", "score").build();
        assertEquals("SELECT id, name, score", sql);
    }

    @Test
    public void test_column_operand_success() {
        String sql = DSL.select().column(name("id")).column(name("name").as("nm")).column(name("score")).build();
        assertEquals("SELECT id, name AS nm, score", sql);

        sql = DSL.select().column(name("id"), name("name").as("nm"), name("score")).build();
        assertEquals("SELECT id, name AS nm, score", sql);
    }

    @Test
    public void test_mix_string_and_operand_columns_success() {
        String sql = DSL.select().column("id").column(name("name").as("nm")).column("score").build();
        assertEquals("SELECT id, name AS nm, score", sql);
    }

    @Test
    public void test_transform_operand_success() {
        String sql = DSL.select()
                .column("id")
                .column(name("name").as("nm"))
                .column(max("score").as("maxScore"))
                .column(name("score").add(value(10)).as("newScore"))
                .column(name("name").like_(PlaceholderOperand.value("林")))
                .column(name("age").gt(value(15)))
                .column(name("name").like_(value("林")))
                .build();
        assertEquals("SELECT id, name AS nm, MAX(score) AS maxScore, score + 10 AS newScore, name LIKE ?, age > 15, name LIKE '林%'", sql);
    }
}