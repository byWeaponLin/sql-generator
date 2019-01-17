package com.weaponlin.dsl.builder;

import com.weaponlin.dsl.SQLParameter;
import com.weaponlin.dsl.operand.table.TableOperand;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.weaponlin.dsl.operand.table.TableOperand.table;

/**
 * TODO
 */
public class DeleteBuilder implements Builder {
    private static final long serialVersionUID = 3648144059870595764L;

    public DeleteBuilder() {
    }

    public FromBuilder from(TableOperand table) {
        checkNotNull(table, "table can not be null");
        return new FromBuilder(table, this);
    }

    public FromBuilder from(String table) {
        checkArgument(StringUtils.isNotBlank(table), "table name can not be empty");
        return from(table(table));
    }

    @Override
    public String toString() {
        return "DELETE";
    }

    @Override
    public SQLParameter build() {
        throw new UnsupportedOperationException("DeleteBuilder not support this operation");
    }

    @Override
    public List<Object> getParameters() {
        return null;
    }
}
