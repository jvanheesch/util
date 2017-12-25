package com.mycompany.tree.iface;

public interface DataTreeNode<T, N extends DataTreeNode<T, N>> extends MutableTreeNode<N> {
    T getData();
}
