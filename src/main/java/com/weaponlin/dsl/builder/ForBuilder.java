package com.weaponlin.dsl.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class ForBuilder implements Serializable {
    private static final long serialVersionUID = -8085252699936853764L;

    private Integer val;
    private ForUnit forUnit;
    private SelectBuilder selectBuilder;
    private FromBuilder fromBuilder;
    private WhereBuilder whereBuilder;

    ForBuilder(Integer val, ForUnit forUnit, SelectBuilder selectBuilder, FromBuilder fromBuilder, WhereBuilder whereBuilder) {
        checkState(val > 0, "The val should not less than zero");
        this.val = val;
        this.forUnit = forUnit;
        this.selectBuilder = checkNotNull(selectBuilder, "select builder shouldn't be null");
        this.fromBuilder = checkNotNull(fromBuilder, "from builder shouldn't be null");
        this.whereBuilder = checkNotNull(whereBuilder, "where builder shouldn't be null");
    }

    @Override
    public String toString() {
        return "for last " + val + forUnit.getUnit();
    }

    public String build() {
        return selectBuilder + " " + fromBuilder + " " + whereBuilder + " " + this.toString();
    }

    @AllArgsConstructor
    public enum ForUnit {
        MIN(" min"), EVENTS(" events");

        @Getter
        private String unit;
    }
}
