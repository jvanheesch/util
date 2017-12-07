package com.mycompany.builder;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface Builder<T> {
    <C> Builder<T> with(BiConsumer<T, C> consumer, C value);

    T build();

    static <T> Builder<T> of(Supplier<T> supplier) {
        return new BuilderImpl<>(supplier);
    }
}
