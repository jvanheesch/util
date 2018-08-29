package com.github.jvanheesch.formatters;

import java.text.Format;

public interface Formatter<T, F extends Format> {
    String format(T obj);

    T parseObject(String source);

    F toFormat();

    default T parseObjectNullsafe(String source) {
        return source == null ? null : this.parseObject(source);
    }

    default String formatNullsafe(T obj) {
        return obj == null ? null : this.format(obj);
    }
}
