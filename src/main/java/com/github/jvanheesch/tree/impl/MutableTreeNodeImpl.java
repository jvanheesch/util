package com.github.jvanheesch.tree.impl;

import com.github.jvanheesch.tree.iface.MutableTreeNode;

import java.util.ArrayList;
import java.util.List;

public abstract class MutableTreeNodeImpl<N extends MutableTreeNode<N>> implements MutableTreeNode<N> {
    private N parent;
    private final List<N> children = new ArrayList<>();

    @Override
    public final N getParent() {
        return this.parent;
    }

    @Override
    public final List<N> getChildren() {
        return new ArrayList<>(this.children);
    }

    @Override
    public final N addChild(N node) {
        this.children.add(node);
        node.setParent(this.self());
        return this.self();
    }

    @Override
    public void setParent(N parent) {
        this.parent = parent;
    }

    @SuppressWarnings("unchecked")
    protected final N self() {
        return (N) this;
    }
}
