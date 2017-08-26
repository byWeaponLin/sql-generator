package com.weaponlin.dsl.operand;

import org.springframework.util.ClassUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberOperand extends TransformOperand {
    private static final long serialVersionUID = 7452209161777430277L;

    private static final ThreadLocal<DecimalFormat> decimalFormatThreadLocal = ThreadLocal.withInitial(() -> {
        DecimalFormat decimalFormat = new DecimalFormat("#.######", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat;
    });

    private double value;

    private NumberOperand(String name, double value) {
        super(name);
        this.value = value;
    }

    public static NumberOperand num(double value) {
        return new NumberOperand(ClassUtils.getShortName(NumberOperand.class), value);
    }

    @Override
    public String toString(boolean hasAlias) {
        return decimalFormatThreadLocal.get().format(value);
    }
}
