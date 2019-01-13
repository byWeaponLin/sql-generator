package com.weaponlin.dsl;

import org.junit.Test;

import static org.junit.Assert.*;

public class DSLTest {

    @Test
    public void test() {
        String sql = DSL.select().column("id").column("name").column("age")
                .from("student")
                .where().build();

        System.out.println(sql);
    }
}