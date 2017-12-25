package com.github.jvanheesch.formatters;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.BiConsumer;

public abstract class NumberFormatterBuilder<T extends Number, F extends NumberFormat, B extends NumberFormatterBuilder<T, F, B>> extends FormatterBuilder<T, F, B> {
    private Locale locale;

    private Integer maximumIntegerDigits;
    private Integer minimumIntegerDigits;
    private Integer maximumFractionDigits;
    private Integer minimumFractionDigits;

    private RoundingMode roundingMode;

    private Boolean groupingUsed;

    public B locale(Locale locale) {
        this.locale = locale;
        return this.self();
    }

    public B useGrouping() {
        return this.groupingUsed(true);
    }

    public B noGrouping() {
        return this.groupingUsed(false);
    }

    B groupingUsed(boolean groupingUsed) {
        this.groupingUsed = groupingUsed;
        return this.self();
    }

    public B maximumIntegerDigits(int maximumIntegerDigits) {
        this.maximumIntegerDigits = maximumIntegerDigits;
        return this.self();
    }

    public B minimumIntegerDigits(int minimumIntegerDigits) {
        this.minimumIntegerDigits = minimumIntegerDigits;
        return this.self();
    }

    public B maximumFractionDigits(int maximumFractionDigits) {
        this.maximumFractionDigits = maximumFractionDigits;
        return this.self();
    }

    public B minimumFractionDigits(int minimumFractionDigits) {
        this.minimumFractionDigits = minimumFractionDigits;
        return this.self();
    }

    public B fixedNbOfFractionDigits(int nbFractionDigits) {
        return this
                .minimumFractionDigits(nbFractionDigits)
                .maximumFractionDigits(nbFractionDigits);
    }

    public B roundingMode(RoundingMode roundingMode) {
        this.roundingMode = roundingMode;
        return this.self();
    }

    @Override
    public final Formatter<T, F> build() {
        return new FormatterImpl<>(this.buildFormat());
    }

    private F buildFormat() {
        F numberFormat = this.createNumberFormat();
        this.configureFormat(numberFormat);
        return numberFormat;
    }

    protected abstract F createNumberFormat();

    protected void configureFormat(F format) {
        applyIfValuePresent(NumberFormat::setMinimumIntegerDigits, format, this.minimumIntegerDigits);
        applyIfValuePresent(NumberFormat::setMaximumIntegerDigits, format, this.maximumIntegerDigits);
        applyIfValuePresent(NumberFormat::setMinimumFractionDigits, format, this.minimumFractionDigits);
        applyIfValuePresent(NumberFormat::setMaximumFractionDigits, format, this.maximumFractionDigits);
        applyIfValuePresent(NumberFormat::setRoundingMode, format, this.roundingMode);
        applyIfValuePresent(NumberFormat::setGroupingUsed, format, this.groupingUsed);
    }

    static <F extends NumberFormat, V> void applyIfValuePresent(BiConsumer<F, V> setter, F format, V value) {
        if (value != null) {
            setter.accept(format, value);
        }
    }

    Locale getLocale() {
        return this.locale;
    }
}
