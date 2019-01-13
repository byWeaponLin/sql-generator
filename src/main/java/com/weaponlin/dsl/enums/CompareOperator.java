package com.weaponlin.dsl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * compare operator
 * GE: >=
 * GT: >
 * EQ: =
 * LE: <=
 * LT: <
 */
@AllArgsConstructor
public enum CompareOperator {
    LT("<"),
    LE("<="),
    EQ("="),
    GT(">"),
    GE(">="),
    NEQ("!="),
    LIKE("like"),
    NOT_LIKE("not like"),
    IS_NULL("is null"),
    IS_NOT_NULL("is not null"),
    IN("in"),
    NOT_IN("not in"),
    // TODO between...and...
    BETWEEN_AND("between_and");

    @Getter
    private String comparator;
}
