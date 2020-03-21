package com.github.jvanheesch;

import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * Based on {@link org.apache.wicket.markup.html.list.ListItemModel}.
 */
public class ListElementModel<T> implements IModel<T> {
    private static final long serialVersionUID = 1L;

    private final IModel<? extends List<T>> listModel;
    private final int index;

    public ListElementModel(IModel<? extends List<T>> listModel, int index) {
        this.listModel = listModel;
        this.index = index;
    }

    @Override
    public T getObject() {
        return this.listModel.getObject().get(this.index);
    }

    @Override
    public final void setObject(T object) {
        this.listModel.getObject().set(this.index, object);
    }

    @Override
    public void detach() {
        this.listModel.detach();
    }
}
