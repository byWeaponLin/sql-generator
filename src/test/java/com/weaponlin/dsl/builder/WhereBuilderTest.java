package com.weaponlin.dsl.builder;

import com.weaponlin.dsl.BaseTest;
import com.weaponlin.dsl.DSL;
import org.junit.Test;

import static com.weaponlin.dsl.operand.table.TableOperand.table;
import static com.weaponlin.dsl.operand.transform.AggregateFunctionOperand.count;
import static com.weaponlin.dsl.operand.transform.ColumnOperand.name;
import static com.weaponlin.dsl.operand.transform.PlaceholderOperand.value;
import static com.weaponlin.dsl.operand.transform.PlaceholderOperand.values;
import static org.junit.Assert.assertEquals;

public class WhereBuilderTest extends BaseTest {

    @Test
    public void test_where_common_comparator_success() {
        String sql = DSL.select()
                .column("id", "name", "score")
                .from(table("students"))
                .where().and(name("score").ge(value(90)))
                .and(name("name").like_(value("林")))
                .build();
        assertEquals("SELECT id, name, score FROM students WHERE score >= ? AND name LIKE ?", sql);
    }

    @Test
    public void test_where_like_success() {
        String sql = DSL.select()
                .column("name")
                .from(table("students"))
                .where()
                .and(name("name").like_(value("林")))
                .and(name("age").notLike_(value(2)))
                .and(name("class_id").like(value(1)))
                .build();
        assertEquals("SELECT name FROM students WHERE name LIKE ? AND age NOT LIKE ? AND class_id LIKE ?", sql);
    }

    @Test
    public void test_where_in_success() {
        String sql = DSL.select()
                .column("name")
                .from(table("students"))
                .where()
                .or(name("age").notIn(values(15, 16, 17)))
                .or(name("score").in(values(80, 90)))
                .or(name("class_id").in(values(1, 2, 3)))
                .build();
        assertEquals("SELECT name FROM students WHERE age NOT IN (?, ?, ?) OR score IN (?, ?) OR class_id IN (?, ?, ?)", sql);
    }

    @Test
    public void test_where_between_success() {
        String sql = DSL.select()
                .column("name")
                .from(table("students"))
                .where()
                .and(name("age").betweenAnd(value(13), value(15)))
                .build();
        assertEquals("SELECT name FROM students WHERE age BETWEEN ? AND ?", sql);
    }

    @Test
    public void test_where_group_by_success() {
        String sql = DSL.select()
                .column("class_id")
                .column(count("class_id").as("classPerson"))
                .from(table("students"))
                .where()
                .groupBy("class_id")
                .build();
        assertEquals("SELECT class_id, COUNT(class_id) AS classPerson FROM students GROUP BY class_id", sql);
    }

    @Test
    public void test_where_limit_success() {
        String sql = DSL.select()
                .column("class_id")
                .from(table("students"))
                .where()
                .limit(10)
                .build();
        assertEquals("SELECT class_id FROM students LIMIT ?", sql);

        sql = DSL.select()
                .column("class_id")
                .from(table("students"))
                .where()
                .limit(10, 20)
                .build();
        assertEquals("SELECT class_id FROM students LIMIT ?, ?", sql);
    }

    @Test
    public void test_where_group_by_limit_success() {
        String sql = DSL.select()
                .column("score")
                .from(table("students"))
                .where()
                .groupBy("score")
                .limit(10)
                .build();
        assertEquals("SELECT score FROM students GROUP BY score LIMIT ?", sql);

        sql = DSL.select()
                .column("score")
                .from(table("students"))
                .where()
                .groupBy("score")
                .limit(10, 10)
                .build();
        assertEquals("SELECT score FROM students GROUP BY score LIMIT ?, ?", sql);
    }
}