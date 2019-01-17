package com.weaponlin.dsl.builder;

import com.weaponlin.dsl.SQLParameter;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 */
public interface Builder extends Serializable {

    /**
     * TODO 1. generate sql string
     * TODO 2. generate SQLParameter(include sql-string, parameters, rowMapper and other info)
     * @return
     */
    SQLParameter build();

    List<Object> getParameters();
}
