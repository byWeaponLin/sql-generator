package com.weaponlin.dsl.executor;

import com.weaponlin.dsl.SQLParameter;

import java.util.List;
import java.util.Map;

public interface Executors {

    <T> T selectOne(SQLParameter sqlParameter, T type);

    <E> List<E> selectList(SQLParameter sqlParameter, E type);

    <K, V> Map<K, V> selectMap(SQLParameter sqlParameter, Map<K, V> type);

    int insert(SQLParameter sqlParameter);

    int update(SQLParameter sqlParameter);

    int delete(SQLParameter sqlParameter);
}
