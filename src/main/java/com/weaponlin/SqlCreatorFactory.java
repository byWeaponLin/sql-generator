package com.weaponlin;

import com.google.common.collect.ImmutableMap;
import com.weaponlin.enums.Threshold;
import com.weaponlin.sqlbuilder.DynamicSqlCreator;
import com.weaponlin.sqlbuilder.NoDataSqlCreator;
import com.weaponlin.sqlbuilder.SqlCreator;
import com.weaponlin.sqlbuilder.StaticSqlCreator;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.Optional;

/**
 * handle severity condition info to generator rule sql
 */
@AllArgsConstructor
public class SqlCreatorFactory {

    private static final Map<Threshold, SqlCreator> CREATORS =
            ImmutableMap.<Threshold, SqlCreator>builder()
                    .put(Threshold.STATIC, new StaticSqlCreator())
                    .put(Threshold.DYNAMIC, new DynamicSqlCreator())
                    .put(Threshold.NODATA, new NoDataSqlCreator()).build();

    @SneakyThrows
    public static String generateSql(final AlertBasicSeverityLevel severityLevel) {
        SqlCreator creator = CREATORS.getOrDefault(severityLevel.getThreshold(), null);
        return Optional.ofNullable(creator)
                .map(e -> e.ruleSql(severityLevel))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("invalid threshold type. error type: " + severityLevel.getThreshold().toString());
                });
    }
}
