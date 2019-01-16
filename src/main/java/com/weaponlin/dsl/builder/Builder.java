package com.weaponlin.dsl.builder;

/**
 * TODO
 */
public interface Builder {

    /**
     * TODO 1. generate sql string
     * TODO 2. generate SQL(include sql-string, parameters, rowMapper and other info)
     * @return
     */
    String build();
}
