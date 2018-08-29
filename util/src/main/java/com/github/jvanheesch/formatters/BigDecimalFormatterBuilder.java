package com.github.jvanheesch.formatters;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public final class BigDecimalFormatterBuilder extends NumberFormatterBuilder<BigDecimal, DecimalFormat, BigDecimalFormatterBuilder> {
    private String positivePrefix;

    public BigDecimalFormatterBuilder positivePrefixPlus() {
        return this.positivePrefix("+");
    }

    private BigDecimalFormatterBuilder positivePrefix(@SuppressWarnings("SameParameterValue") String positivePrefix) {
        this.positivePrefix = positivePrefix;
        return this.self();
    }

    @Override
    protected DecimalFormat createNumberFormat() {
        try {
            return (DecimalFormat) NumberFormat.getInstance(this.getLocale());
        } catch (ClassCastException e) {
            throw new RuntimeException("Failed to cast the result of NumberFormat.getInstance(Locale) to DecimalFormat for the given Locale.");
        }
    }

    @Override
    protected void configureFormat(DecimalFormat format) {
        super.configureFormat(format);

        applyIfValuePresent(DecimalFormat::setPositivePrefix, format, this.positivePrefix);

        format.setParseBigDecimal(true);
    }
}
