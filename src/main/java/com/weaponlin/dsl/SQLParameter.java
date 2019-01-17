package com.weaponlin.dsl;

import lombok.Getter;

import java.util.List;

/**
 * TODO
 */
@Getter
public class SQLParameter {

    private String sql;
    private List<Object> parameters;

    /**
     * TODO NEED MORE ATTRIBUTES
     */
    public SQLParameter(String sql, List<Object> parameters) {
        this.sql = sql;
        this.parameters = parameters;
    }
}
