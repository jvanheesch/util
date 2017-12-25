package com.mycompany.tree.iface;

import java.util.List;

/**
 * A tree with single root (which is, by definition, always the case) can be fully traversed iff we start from the root.
 */
public interface DownwardsTreeNode<N extends DownwardsTreeNode<N>> {
    List<N> getChildren();

    default boolean isLeaf() {
        return this.getChildren().isEmpty();
    }
}
