package com.mycompany.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBiFunction;

import java.util.List;

public final class FunctionalMarkupRepeater<T> extends MarkupRepeater<T> {
    private static final long serialVersionUID = 1L;

    private final SerializableBiFunction<String, IModel<T>, ? extends Component> componentConstructor;

    public FunctionalMarkupRepeater(String id, IModel<? extends List<T>> model, SerializableBiFunction<String, IModel<T>, ? extends Component> componentConstructor) {
        super(id, model);
        this.componentConstructor = componentConstructor;
    }

    @Override
    protected Component constructComponent(String id, IModel<T> model) {
        return this.componentConstructor.apply(id, model);
    }
}
