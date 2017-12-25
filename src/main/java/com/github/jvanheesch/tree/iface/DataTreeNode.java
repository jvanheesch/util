package com.github.jvanheesch.tree.iface;

public interface DataTreeNode<T, N extends DataTreeNode<T, N>> extends MutableTreeNode<N> {
    T getData();
}
