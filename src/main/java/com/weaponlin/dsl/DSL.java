package com.weaponlin.dsl;

import com.weaponlin.dsl.builder.SelectBuilder;

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


    public static void update() {

    }

    public static void delete() {

    }

    public static void insert() {

    }
}
