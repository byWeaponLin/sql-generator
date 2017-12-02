package com.weaponlin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * compare operator
 * GE: >=
 * GT: >
 * EQ: =
 * LE: <=
 * LT: <
 * INCREASE: 增长
 * DECREASE: 下降
 */
@AllArgsConstructor
public enum Comparator {
    LT(" < "),
    LE(" <= "),
    EQ(" = "),
    GT(" > "),
    GE(" >= "),
    //no reason to exist,definitely not use it
    INCREASE(" + "),
    DECREASE(" - ");
    @Getter
    private String comparator;
}
