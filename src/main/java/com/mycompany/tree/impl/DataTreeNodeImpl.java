package com.mycompany.tree.impl;

import com.mycompany.tree.iface.DataTreeNode;

public abstract class DataTreeNodeImpl<T, N extends DataTreeNode<T, N>> extends MutableTreeNodeImpl<N> implements DataTreeNode<T, N> {
    private final T data;

    protected DataTreeNodeImpl(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return this.data;
    }
}
