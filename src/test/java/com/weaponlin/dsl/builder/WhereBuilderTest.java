package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.BaseTest;
import com.weaponlin.dsl.DSL;
import com.weaponlin.dsl.SQLParameter;
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
        SQLParameter sqlParameter = DSL.select()
                .column("id", "name", "score")
                .from(table("students"))
                .where().and(name("score").ge(value(90)))
                .and(name("name").like_(value("林")))
                .build();
        assertEquals("SELECT id, name, score FROM students WHERE score >= ? AND name LIKE ?", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(90, "林%"), sqlParameter.getParameters());
    }

    @Test
    public void test_where_like_success() {
        SQLParameter sqlParameter = DSL.select()
                .column("name")
                .from(table("students"))
                .where()
                .and(name("name").like_(value("林")))
                .and(name("age").notLike_(value(2)))
                .and(name("class_id").like(value(1)))
                .build();
        assertEquals("SELECT name FROM students WHERE name LIKE ? AND age NOT LIKE ? AND class_id LIKE ?", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList("林%", "2%", "1"), sqlParameter.getParameters());
    }

    @Test
    public void test_where_in_success() {
        SQLParameter sqlParameter = DSL.select()
                .column("name")
                .from(table("students"))
                .where()
                .or(name("age").notIn(values(15, 16, 17)))
                .or(name("score").in(values(80, 90)))
                .or(name("class_id").in(values(1, 2, 3)))
                .build();
        assertEquals("SELECT name FROM students WHERE age NOT IN (?, ?, ?) OR score IN (?, ?) OR class_id IN (?, ?, ?)", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(15, 16, 17, 80, 90, 1, 2, 3), sqlParameter.getParameters());
    }

    @Test
    public void test_where_between_success() {
        SQLParameter sqlParameter = DSL.select()
                .column("name")
                .from(table("students"))
                .where()
                .and(name("age").betweenAnd(value(13), value(15)))
                .build();
        assertEquals("SELECT name FROM students WHERE age BETWEEN ? AND ?", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(13, 15), sqlParameter.getParameters());
    }

    @Test
    public void test_where_group_by_success() {
        SQLParameter sqlParameter = DSL.select()
                .column("class_id")
                .column(count("class_id").as("classPerson"))
                .from(table("students"))
                .where()
                .groupBy("class_id")
                .build();
        assertEquals("SELECT class_id, COUNT(class_id) AS classPerson FROM students GROUP BY class_id", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(), sqlParameter.getParameters());
    }

    @Test
    public void test_where_limit_success() {
        SQLParameter sqlParameter = DSL.select()
                .column("class_id")
                .from(table("students"))
                .where()
                .limit(10)
                .build();
        assertEquals("SELECT class_id FROM students LIMIT ?", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(10), sqlParameter.getParameters());

        sqlParameter = DSL.select()
                .column("class_id")
                .from(table("students"))
                .where()
                .limit(10, 20)
                .build();
        assertEquals("SELECT class_id FROM students LIMIT ?, ?", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(10, 20), sqlParameter.getParameters());
    }

    @Test
    public void test_where_group_by_limit_success() {
        SQLParameter sqlParameter = DSL.select()
                .column("score")
                .from(table("students"))
                .where()
                .groupBy("score")
                .limit(10)
                .build();
        assertEquals("SELECT score FROM students GROUP BY score LIMIT ?", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(10), sqlParameter.getParameters());

        sqlParameter = DSL.select()
                .column("score")
                .from(table("students"))
                .where()
                .groupBy("score")
                .limit(10, 10)
                .build();
        assertEquals("SELECT score FROM students GROUP BY score LIMIT ?, ?", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(10, 10), sqlParameter.getParameters());
    }
}