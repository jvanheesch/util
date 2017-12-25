package com.mycompany.tree.iface;

public interface MutableTreeNode<N extends MutableTreeNode<N>> extends TreeNode<N> {
    /**
     * returns this node to allow for method chaining
     */
    N addChild(N child);

    void setParent(N parent);
}
