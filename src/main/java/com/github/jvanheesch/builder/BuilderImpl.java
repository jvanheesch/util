package com.github.jvanheesch.builder;

import com.github.jvanheesch.functions.Consumers;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

class BuilderImpl<T> implements Builder<T> {
    private final Supplier<? extends T> supplier;
    private Consumer<T> config = Consumers.noop();

    BuilderImpl(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public <C> Builder<T> with(BiConsumer<? super T, ? super C> setter, C value) {
        this.config = this.config.andThen(t -> setter.accept(t, value));
        return this;
    }

    @Override
    public T build() {
        T newObject = this.supplier.get();
        this.config.accept(newObject);
        return newObject;
    }
}
