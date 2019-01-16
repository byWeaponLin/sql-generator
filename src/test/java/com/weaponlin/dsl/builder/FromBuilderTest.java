package com.weaponlin.dsl.builder;

import com.weaponlin.dsl.BaseTest;
import com.weaponlin.dsl.DSL;
import com.weaponlin.dsl.operand.transform.PlaceholderOperand;
import org.junit.Test;

import static com.weaponlin.dsl.operand.table.TableOperand.table;
import static com.weaponlin.dsl.operand.transform.AggregateFunctionOperand.max;
import static com.weaponlin.dsl.operand.transform.ColumnOperand.name;
import static com.weaponlin.dsl.operand.transform.ValueOperand.value;
import static org.junit.Assert.assertEquals;

public class FromBuilderTest extends BaseTest {
    @Test
    public void test_from_table_success() {
        String sql = DSL.select()
                .column("id")
                .column("name")
                .column("score")
                .from(table("students"))
                .build();
        assertEquals("SELECT id, name, score FROM students", sql);

        sql = DSL.select().column("id", "name", "score")
                .from(table("students").as("stu"))
                .build();
        assertEquals("SELECT id, name, score FROM students AS stu", sql);
    }

    @Test
    public void test_transform_operand_from_table_success() {
        String sql = DSL.select()
                .column("id")
                .column(name("name").as("nm"))
                .column(max("score").as("maxScore"))
                .column(name("score").add(value(10)).as("newScore"))
                .column(name("name").like_(PlaceholderOperand.value("林")))
                .column(name("age").gt(value(15)))
                .column(name("name").like_(value("林")))
                .from(table("students"))
                .build();
        assertEquals("SELECT id, name AS nm, MAX(score) AS maxScore, score + 10 AS newScore, name LIKE ?, age > 15, name LIKE '林%' FROM students", sql);
    }
}