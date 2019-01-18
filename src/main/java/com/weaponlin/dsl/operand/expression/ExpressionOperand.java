package com.weaponlin.dsl.operand.expression;

import com.weaponlin.dsl.operand.Operand;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.*;

public abstract class ExpressionOperand extends Operand {
    private static final long serialVersionUID = -6216349853355952178L;

    ExpressionOperand(String name) {
        super(name);
    }

    /**
     * TODO add if...else
     */

    @Override
    public ExpressionOperand as(String alias) {
        checkArgument(isNotBlank(alias), "alias can not be empty");
        this.alias = alias;
        return this;
    }

    @Override
    protected String getDecoratedAlias(boolean hasAlias) {
        return hasAlias && StringUtils.isNotBlank(alias) ? " AS " + alias : "";
    }
}
