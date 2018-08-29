package com.github.jvanheesch.builder;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface Builder<T> {
    <C> Builder<T> with(BiConsumer<? super T, ? super C> setter, C value);

    T build();

    static <T> Builder<T> of(Supplier<? extends T> supplier) {
        return new BuilderImpl<>(supplier);
    }
}
