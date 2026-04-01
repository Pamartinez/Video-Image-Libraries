package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.channel.BufferChannelDescriptor;
import com.samsung.android.sum.core.evaluate.Evaluator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface GraphBuilder<T> {
    GraphBuilder<T> addNode(GraphNode<? extends T> graphNode, GraphNode<? extends T> graphNode2);

    GraphBuilder<T> addNode(GraphNode<? extends T> graphNode, GraphNode<? extends T> graphNode2, BufferChannelDescriptor bufferChannelDescriptor);

    GraphBuilder<T> addNode(GraphNode<? extends T> graphNode, GraphNode<? extends T> graphNode2, Evaluator evaluator);

    GraphBuilder<T> addNode(GraphNode<? extends T> graphNode, GraphNode<? extends T> graphNode2, Evaluator evaluator, BufferChannelDescriptor bufferChannelDescriptor);

    GraphBuilder<T> addNode(GraphNode<? extends T>... graphNodeArr);

    Graph<T> build();
}
