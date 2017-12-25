package com.github.jvanheesch.formatters;

import java.text.Format;
import java.text.ParseException;

public final class FormatterImpl<T, F extends Format> implements Formatter<T, F> {
    private final F format;

    FormatterImpl(F format) {
        this.format = format;
    }

    @Override
    public final String format(T obj) {
        return this.format.format(obj);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T parseObject(String source) {
        try {
            return (T) this.format.parseObject(source);
        } catch (ParseException e) {
            throw new RuntimeException();
        } catch (ClassCastException e) {
            throw new RuntimeException("Failed to cast the result of Format.parseObject(String source) to type F.");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public F toFormat() {
        return (F) this.format.clone();
    }
}
