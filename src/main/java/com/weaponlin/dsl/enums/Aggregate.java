package com.weaponlin.dsl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Aggregate {

    MAX("MAX"),
    MIN("MIN"),
    COUNT("COUNT"),
    AVG("AVG"),
    SUM("SUM")
    ;

    @Getter
    private String functionName;
}
