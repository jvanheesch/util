package com.github.jvanheesch.tree.impl;

import com.github.jvanheesch.tree.iface.SimpleNamedNode;

public final class SimpleNamedNodeImpl extends DataTreeNodeImpl<String, SimpleNamedNode> implements SimpleNamedNode {
    public SimpleNamedNodeImpl(String data) {
        super(data);
    }

    @Override
    public String toString() {
        return this.getData();
    }
}
