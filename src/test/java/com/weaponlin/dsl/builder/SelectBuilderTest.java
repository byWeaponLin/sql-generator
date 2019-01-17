package com.weaponlin.dsl.builder;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.BaseTest;
import com.weaponlin.dsl.DSL;
import com.weaponlin.dsl.SQLParameter;
import com.weaponlin.dsl.operand.transform.PlaceholderOperand;
import org.junit.Test;

import static com.weaponlin.dsl.operand.transform.AggregateFunctionOperand.max;
import static com.weaponlin.dsl.operand.transform.ColumnOperand.name;
import static com.weaponlin.dsl.operand.transform.ValueOperand.value;
import static com.weaponlin.dsl.operand.transform.ValueOperand.values;
import static org.junit.Assert.assertEquals;

public class SelectBuilderTest extends BaseTest {

    @Test
    public void test_string_columns_success() {
        SQLParameter sqlParameter = DSL.select().column("id").column("name").column("score").build();
        assertEquals("SELECT id, name, score", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(), sqlParameter.getParameters());

        sqlParameter = DSL.select().column("id", "name", "score").build();
        assertEquals("SELECT id, name, score", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(), sqlParameter.getParameters());
    }

    @Test
    public void test_column_operand_success() {
        SQLParameter sqlParameter = DSL.select().column(name("id")).column(name("name").as("nm")).column(name("score")).build();
        assertEquals("SELECT id, name AS nm, score", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(), sqlParameter.getParameters());

        sqlParameter = DSL.select().column(name("id"), name("name").as("nm"), name("score")).build();
        assertEquals("SELECT id, name AS nm, score", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(), sqlParameter.getParameters());
    }

    @Test
    public void test_mix_string_and_operand_columns_success() {
        SQLParameter sqlParameter = DSL.select().column("id").column(name("name").as("nm")).column("score").build();
        assertEquals("SELECT id, name AS nm, score", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(), sqlParameter.getParameters());
    }

    @Test
    public void test_transform_operand_success() {
        SQLParameter sqlParameter = DSL.select()
                .column("id")
                .column(name("name").as("nm"))
                .column(max("score").as("maxScore"))
                .column(name("score").add(value(10)).as("newScore"))
                .column(name("name").like_(PlaceholderOperand.value("林")))
                .column(name("age").gt(value(15)))
                .column(name("name").like_(value("林")))
                .build();
        assertEquals("SELECT id, name AS nm, MAX(score) AS maxScore, score + 10 AS newScore, name LIKE ?, age > 15, name LIKE '林%'", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList("林%"), sqlParameter.getParameters());
    }

    @Test
    public void test_select_with_expression_alias_success() {
        SQLParameter sqlParameter = DSL.select().column(max("score").as("max").ge(values(90)).as("maxScore")).build();
        assertEquals("SELECT MAX(score) >= 90 AS maxScore", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(), sqlParameter.getParameters());

        sqlParameter = DSL.select().column(max("score").as("max")).build();
        assertEquals("SELECT MAX(score) AS max", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(), sqlParameter.getParameters());
    }

    @Test
    public void test_select_like_alias_success() {
        SQLParameter sqlParameter = DSL.select().column(name("name").like_(PlaceholderOperand.value("林")).as("TheSameName")).build();
        assertEquals("SELECT name LIKE ? AS TheSameName", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList("林%"), sqlParameter.getParameters());
    }

    @Test
    public void test_select_in_alias_success() {
        SQLParameter sqlParameter = DSL.select().column(name("age").in(PlaceholderOperand.values(11, 12, 13)).as("TheSameAge")).build();
        assertEquals("SELECT age IN (?, ?, ?) AS TheSameAge", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(11, 12, 13), sqlParameter.getParameters());

        sqlParameter = DSL.select().column(name("age").in(values(11, 12, 13)).as("TheSameAge")).build();
        assertEquals("SELECT age IN (11, 12, 13) AS TheSameAge", sqlParameter.getSql());
        assertEquals2(Lists.newArrayList(), sqlParameter.getParameters());
    }
}