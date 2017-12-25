package com.mycompany.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// TODO_JORIS: kind of a crappy name, since the essence of any repeater is to repeat markup...
public abstract class MarkupRepeater<T> extends RepeatingView {
    private static final long serialVersionUID = 1L;

    protected MarkupRepeater(String id, IModel<? extends List<T>> model) {
        super(id, model);
    }

    @Override
    protected final void onPopulate() {
        this.removeAll();
        this.addChildComponents();
    }

    private void addChildComponents() {
        this.getModels().forEachOrdered(model -> this.add(this.constructComponent(this.newChildId(), model)));
    }

    private Stream<IModel<T>> getModels() {
        IModel<? extends List<T>> listModel = this.getModel();

        return IntStream.range(0, listModel.getObject().size())
                .mapToObj(i -> new ListElementModel<>(listModel, i));
    }

    protected abstract Component constructComponent(String id, IModel<T> model);

    @SuppressWarnings("unchecked")
    public final IModel<? extends List<T>> getModel() {
        return (IModel<? extends List<T>>) this.getDefaultModel();
    }
}
