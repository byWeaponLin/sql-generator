package com.weaponlin.dsl.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Aggregate {

    MAX("max"),
    MIN("min"),
    COUNT("count"),
    AVG("avg"),
    SUM("sum")
    ;

    private String functionName;
}
