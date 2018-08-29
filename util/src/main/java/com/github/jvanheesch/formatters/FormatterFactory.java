package com.github.jvanheesch.formatters;

public final class FormatterFactory {
    public static BigDecimalFormatterBuilder bigDecimal() {
        return new BigDecimalFormatterBuilder();
    }
}
