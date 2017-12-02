package com.weaponlin.sqlbuilder;


import com.weaponlin.AlertBasicSeverityLevel;

/**
 * 根据条件信息，获取要生成的告警Sql
 */
public interface SqlCreator {

    String ruleSql(AlertBasicSeverityLevel condition);
}
