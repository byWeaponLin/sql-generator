package com.weaponlin;

import com.weaponlin.dsl.builder.SelectBuilder;

import static com.weaponlin.dsl.operand.ColumnOperand.name;
import static com.weaponlin.dsl.operand.FunctionOperand.avg;
import static com.weaponlin.dsl.operand.NumberOperand.num;

public class RuleSQLGenerator {

    public static void main(String[] args) {
        String sql = SelectBuilder.select()
                .column("name, age, gender")
                .from("user")
                .where().and(avg(name("age")).gt(num(20)))
                .forEvents(10).build();

        System.out.println(sql);
    }
}
