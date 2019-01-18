package com.weaponlin.dsl;

import lombok.Getter;

import java.util.List;

import static com.weaponlin.dsl.builder.SelectBuilder.RowMap;

/**
 * TODO
 */
@Getter
public class SQLParameter {

    private String sql;
    private List<Object> parameters;
    private List<RowMap> rowMaps;

    /**
     * TODO NEED MORE ATTRIBUTES
     */
    public SQLParameter(String sql, List<Object> parameters) {
        this.sql = sql;
        this.parameters = parameters;
    }

    public SQLParameter(String sql, List<Object> parameters, List<RowMap> rowMaps) {
        this.sql = sql;
        this.parameters = parameters;
        this.rowMaps = rowMaps;
    }
}
