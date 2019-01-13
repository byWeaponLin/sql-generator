package com.weaponlin.dsl.builder;

import org.junit.Test;

import static com.weaponlin.dsl.operand.ColumnOperand.name;
import static com.weaponlin.dsl.operand.FunctionOperand.avg;
import static com.weaponlin.dsl.operand.VariableOperand.variable;

public class SelectBuilderTest {

    @Test
    public void test() {
        String sql = SelectBuilder.select("name", "age", "gender", "score").from("student")
                .where()
                .and(avg(name("age")).gt(variable("16")))
                .build();
        System.out.println(sql);
    }
}