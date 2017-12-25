package com.github.jvanheesch.formatters;

import java.text.Format;

public abstract class FormatterBuilder<T, F extends Format, B extends FormatterBuilder<T, F, B>> {
    @SuppressWarnings("unchecked")
    protected final B self() {
        return (B) this;
    }

    public abstract Formatter<T, F> build();
}
