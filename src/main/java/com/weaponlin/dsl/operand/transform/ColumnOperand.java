package com.weaponlin.dsl.operand.transform;

import com.google.common.collect.Lists;
import com.weaponlin.dsl.operand.expression.ExpressionOperand;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class ColumnOperand extends TransformOperand {
    private static final long serialVersionUID = 7564148803426063816L;

    @Getter
    private String alias;

    ColumnOperand(String name) {
        super(name);
    }

    public static ColumnOperand name(String column) {
        checkArgument(StringUtils.isNotBlank(column), "column name can not be empty");
        return new ColumnOperand(column);
    }

    public ColumnOperand as(String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public String toString(boolean hasAlias) {
        return hasAlias && StringUtils.isNotBlank(alias) ? (name + " AS " + alias) : name;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    @Override
    public ExpressionOperand toExpression() {
        return null;
    }

    @Override
    public List<Object> getParameters() {
        return Lists.newArrayList();
    }
}
