package com.weaponlin.dsl.design.function;

import org.apache.commons.lang3.StringUtils;

/**
 * TODO 系统内置函数
 */
public class InnerFunctionOperand extends FunctionOperand {
    private static final long serialVersionUID = 3141827431185669384L;

    private String alias;

    InnerFunctionOperand(String name) {
        super(name);
    }

    /**
     *
     * @return
     */
    public static InnerFunctionOperand now() {
        return new InnerFunctionOperand("now()");
    }

    /**
     * TODO Consider that if the inner function has parameters?
     * @return
     */
    public static InnerFunctionOperand rand() {
        return new InnerFunctionOperand("rand()");
    }

    public InnerFunctionOperand as (String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public String toString(boolean hasAlias) {
        return hasAlias && StringUtils.isNotBlank(alias) ? name + " as " + alias : name;
    }

    @Override
    public String toString() {
        return toString(true);
    }
}
