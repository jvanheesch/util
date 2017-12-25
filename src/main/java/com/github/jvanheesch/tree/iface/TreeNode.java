package com.github.jvanheesch.tree.iface;

/**
 * Implementations of this interface should guarantee consistent parent-child relationships, i.e.:
 * x.getParent() = y <=> y.getChildren().contains(x);
 *
 * @param <N> Ideally this should be an type (interface) rather than an implementation (class)
 */
public interface TreeNode<N extends TreeNode<N>> extends DownwardsTreeNode<N>, UpwardsTreeNode<N> {
}
