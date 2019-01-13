package com.weaponlin.dsl.operand.operator;

import static com.google.common.base.Preconditions.checkNotNull;

public class LikeOperatorOperand extends OperatorOperand {
    private static final long serialVersionUID = -8483762890141108278L;

    private Object value;

    private boolean like;

    LikeOperatorOperand(String name, boolean like, Object value) {
        super(name);
        this.like = like;
        this.value = value;
    }

    public static LikeOperatorOperand like(Object value) {
        checkNotNull(value, "like value can not be null");
        return new LikeOperatorOperand("", true, value);
    }

    public static LikeOperatorOperand _like(Object value) {
        checkNotNull(value, "_like value can not be null");
        return new LikeOperatorOperand("", true, "%" + value);
    }

    public static LikeOperatorOperand like_(Object value) {
        checkNotNull(value, "like_ value can not be null");
        return new LikeOperatorOperand("", true, value + "%");
    }

    public static LikeOperatorOperand _like_(Object value) {
        checkNotNull(value, "_like_ value can not be null");
        return new LikeOperatorOperand("", true, "%" + value + "%");
    }

    public static LikeOperatorOperand notLike(Object value) {
        checkNotNull(value, "like value can not be null");
        return new LikeOperatorOperand("", false, value);
    }

    public static LikeOperatorOperand _notLike(Object value) {
        checkNotNull(value, "_notLike value can not be null");
        return new LikeOperatorOperand("", false, "%" + value);
    }

    public static LikeOperatorOperand notLike_(Object value) {
        checkNotNull(value, "notLike_ value can not be null");
        return new LikeOperatorOperand("", false, value + "%");
    }

    public static LikeOperatorOperand _notLike_(Object value) {
        checkNotNull(value, "_notLike_ value can not be null");
        return new LikeOperatorOperand("", false, "%" + value + "%");
    }

    @Override
    public String toString(boolean hasAlias) {
        return (like ? "like" : "not like") + " ?";
    }

    @Override
    public String toString() {
        return toString(false);
    }
}
