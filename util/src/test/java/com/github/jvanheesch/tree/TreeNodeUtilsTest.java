package com.github.jvanheesch.tree;

import com.github.jvanheesch.tree.iface.DataTreeNode;
import com.github.jvanheesch.tree.iface.SimpleNamedNode;
import com.github.jvanheesch.tree.impl.SimpleNamedNodeImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TreeNodeUtilsTest {

    @Test
    public void flattenTreeDepthFirst() {
        SimpleNamedNode root =
                new SimpleNamedNodeImpl("root")
                        .addChild(new SimpleNamedNodeImpl("1")
                                .addChild(new SimpleNamedNodeImpl("11")
                                        .addChild(new SimpleNamedNodeImpl("111")))
                                .addChild(new SimpleNamedNodeImpl("12")
                                        .addChild(new SimpleNamedNodeImpl("121"))
                                        .addChild(new SimpleNamedNodeImpl("122"))))
                        .addChild(new SimpleNamedNodeImpl("2")
                                .addChild(new SimpleNamedNodeImpl("21")
                                        .addChild(new SimpleNamedNodeImpl("211")
                                                .addChild(new SimpleNamedNodeImpl("2111"))))
                                .addChild(new SimpleNamedNodeImpl("22")
                                        .addChild(new SimpleNamedNodeImpl("221"))
                                        .addChild(new SimpleNamedNodeImpl("222"))));

        List<SimpleNamedNode> flattenedTree = TreeNodeUtils.flattenTreeDepthFirst(root)
                .collect(Collectors.toList());

        Assertions.assertThat(flattenedTree)
                .extracting(DataTreeNode::getData)
                .containsExactly(
                        "root",
                        "1",
                        "11",
                        "111",
                        "12",
                        "121",
                        "122",
                        "2",
                        "21",
                        "211",
                        "2111",
                        "22",
                        "221",
                        "222"
                );
    }

    @Test
    public void getLeafs() {
        SimpleNamedNode root =
                new SimpleNamedNodeImpl("root")
                        .addChild(new SimpleNamedNodeImpl("1")
                                .addChild(new SimpleNamedNodeImpl("11")
                                        .addChild(new SimpleNamedNodeImpl("111")))
                                .addChild(new SimpleNamedNodeImpl("12")
                                        .addChild(new SimpleNamedNodeImpl("121"))
                                        .addChild(new SimpleNamedNodeImpl("122"))))
                        .addChild(new SimpleNamedNodeImpl("2")
                                .addChild(new SimpleNamedNodeImpl("21")
                                        .addChild(new SimpleNamedNodeImpl("211")
                                                .addChild(new SimpleNamedNodeImpl("2111"))))
                                .addChild(new SimpleNamedNodeImpl("22")
                                        .addChild(new SimpleNamedNodeImpl("221"))
                                        .addChild(new SimpleNamedNodeImpl("222"))));

        List<SimpleNamedNode> flattenedTree = TreeNodeUtils.getLeafs(root)
                .collect(Collectors.toList());

        Assertions.assertThat(flattenedTree)
                .extracting(DataTreeNode::getData)
                .containsExactly(
                        "111",
                        "121",
                        "122",
                        "2111",
                        "221",
                        "222"
                );
    }
}
