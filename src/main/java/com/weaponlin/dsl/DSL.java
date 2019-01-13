package com.weaponlin.dsl;

import com.weaponlin.dsl.builder.SelectBuilder;
import com.weaponlin.dsl.operand.NameOperand;

/**
 * TODO
 */
public class DSL {

    public static SelectBuilder select() {
        return new SelectBuilder();
    }

    public static SelectBuilder select(String... columns) {
        return new SelectBuilder(columns);
    }

    public static SelectBuilder select(NameOperand... operands) {
        return new SelectBuilder(operands);
    }

    public static void update() {

    }

    public static void delete() {

    }

    public static void insert() {

    }
}
