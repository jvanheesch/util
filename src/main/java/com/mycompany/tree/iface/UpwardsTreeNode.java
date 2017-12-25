package com.mycompany.tree.iface;

/**
 * In general we can only traverse branches, not whole trees.
 */
public interface UpwardsTreeNode<N extends UpwardsTreeNode<N>> {
    N getParent();

    default boolean isRoot() {
        return this.getParent() == null;
    }

    /**
     * http://typeocaml.com/2014/11/26/height-depth-and-level-of-a-tree/
     */
    default int getDepth() {
        return this.isRoot() ? 0 : 1 + this.getParent().getDepth();
    }
}
