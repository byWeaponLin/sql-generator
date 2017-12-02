package com.weaponlin.sqlbuilder;


import com.weaponlin.AlertBasicSeverityLevel;

/**
 * 无数据告警Sql生成
 */
public class NoDataSqlCreator implements SqlCreator {

    @Override
    public String ruleSql(AlertBasicSeverityLevel condition) {
        return "";
    }
}
