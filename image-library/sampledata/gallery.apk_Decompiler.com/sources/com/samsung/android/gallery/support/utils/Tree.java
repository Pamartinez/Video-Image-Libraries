package com.samsung.android.gallery.support.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Tree<T> {
    private HashMap<T, Node<T>> index = new HashMap<>();
    private Node<T> root;

    public Tree(T t) {
        this.root = addNode(t);
    }

    private Node<T> addNode(T t) {
        Node<T> node = new Node<>();
        node.data = t;
        node.children = new ArrayList();
        this.index.put(t, node);
        return node;
    }

    public Node<T> addChild(T t, T t3) {
        Node<T> node = new Node<>(t3);
        Node<T> node2 = this.index.get(t);
        node.parent = node2;
        if (node2 == null) {
            node.parent = addNode(t);
        }
        node.parent.children.add(node);
        this.index.put(t3, node);
        return node;
    }

    public int getMaxDepth() {
        return this.root.getMaxDepth();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Node<T> {
        List<Node<T>> children = new ArrayList();
        T data;
        Node<T> parent;

        public Node() {
        }

        /* access modifiers changed from: private */
        public int getMaxDepth() {
            int i2 = 1;
            for (Node<T> maxDepth : this.children) {
                i2 = Math.max(i2, maxDepth.getMaxDepth() + i2);
            }
            return i2;
        }

        public Node(T t) {
            this.data = t;
        }
    }
}
