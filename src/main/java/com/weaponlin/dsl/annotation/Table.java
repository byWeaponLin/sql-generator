package com.weaponlin.dsl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表注解，设置数据库名、表名以及分库分表配置
 * TODO 是否需要添加分片策略?
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    /**
     * database name
     */
    String database();

    /**
     * table name
     */
    String table();

    /**
     * TODO 分片id
     * @return
     */
    int hashId() default 0;

    /**
     * TODO 分片的字段
     * @return
     */
    String hashColumn() default "";

    /**
     * 若不分库，值为0；若分库，值为分库的个数。<br>
     * 默认为0。
     */
    int databaseCount() default 0;

    /**
     * 若不分表，值为0；若分表，值为分表的个数。<br>
     * 默认为0。
     */
    int tableCount() default 0;
}

