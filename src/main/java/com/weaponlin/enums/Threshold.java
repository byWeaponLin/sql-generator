package com.weaponlin.enums;

/**
 * 门限类型
 *
 * STATIC: 静态门限
 * DYNAMIC: 动态门限
 * NODATA: 无数据门限
 */
public enum Threshold {
    STATIC,
    DYNAMIC,
    NODATA;


    /**
     * 静态门限类型的子类型
     *
     * NUMERICAL: 数值门限
     * FREQUENCY: 频率门限
     */
    public static enum SubThreshold {
        NUMERICAL,
        FREQUENCY
    }
}

