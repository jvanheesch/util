package com.mycompany.tree.impl;

import com.mycompany.tree.iface.SimpleNamedNode;

public final class SimpleNamedNodeImpl extends DataTreeNodeImpl<String, SimpleNamedNode> implements SimpleNamedNode {
    public SimpleNamedNodeImpl(String data) {
        super(data);
    }

    @Override
    public String toString() {
        return this.getData();
    }
}
