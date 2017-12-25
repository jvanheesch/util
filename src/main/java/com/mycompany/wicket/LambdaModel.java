package com.mycompany.wicket;

import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBiConsumer;
import org.danekja.java.util.function.serializable.SerializableFunction;

public class LambdaModel<T, R> implements IModel<R> {
    private static final long serialVersionUID = 2605657772223162404L;

    private final IModel<T> wrappedModel;
    private final SerializableFunction<T, R> read;
    private final SerializableBiConsumer<T, R> write;

    public LambdaModel(IModel<T> wrappedModel, SerializableFunction<T, R> read, SerializableBiConsumer<T, R> write) {
        this.wrappedModel = wrappedModel;
        this.read = read;
        this.write = write;
    }

    public LambdaModel(IModel<T> wrappedModel, SerializableFunction<T, R> read) {
        this(wrappedModel, read, (modelObject, value) -> {
            throw new UnsupportedOperationException();
        });
    }

    @Override
    public R getObject() {
        return this.read.apply(this.wrappedModel.getObject());
    }

    @Override
    public void setObject(R object) {
        this.write.accept(this.wrappedModel.getObject(), object);
    }

    @Override
    public void detach() {
        this.wrappedModel.detach();
    }
}
