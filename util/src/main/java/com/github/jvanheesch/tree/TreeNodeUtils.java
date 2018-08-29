package com.github.jvanheesch.tree;

import com.github.jvanheesch.tree.iface.DownwardsTreeNode;

import java.util.Collection;
import java.util.stream.Stream;

public final class TreeNodeUtils {
    private TreeNodeUtils() {
    }

    public static <N extends DownwardsTreeNode<N>> Stream<N> flattenTreeDepthFirst(N root) {
        return Stream.concat(
                Stream.of(root),
                root.getChildren().stream()
                        .flatMap(TreeNodeUtils::flattenTreeDepthFirst)
        );
    }

    public static <N extends DownwardsTreeNode<N>> Stream<N> flattenTreeDepthFirst(Collection<N> roots) {
        return roots.stream()
                .flatMap(TreeNodeUtils::flattenTreeDepthFirst);
    }

    public static <N extends DownwardsTreeNode<N>> Stream<N> getLeafs(N root) {
        return flattenTreeDepthFirst(root)
                .filter(DownwardsTreeNode::isLeaf);
    }

    public static <N extends DownwardsTreeNode<N>> Stream<N> getLeafs(Collection<N> roots) {
        return flattenTreeDepthFirst(roots)
                .filter(DownwardsTreeNode::isLeaf);
    }
}
