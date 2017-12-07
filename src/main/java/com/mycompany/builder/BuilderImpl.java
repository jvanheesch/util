package com.mycompany.builder;

import com.mycompany.functions.Consumers;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

class BuilderImpl<T> implements Builder<T> {
    private final Supplier<T> supplier;
    private Consumer<T> config = Consumers.noop();

    BuilderImpl(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public <C> Builder<T> with(BiConsumer<T, C> setter, C value) {
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
