package com.samsung.android.sum.core.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GraphBuilderBase<T> implements GraphBuilder<T> {
    protected List<GraphNode<T>> graphNodes = new ArrayList();

    public GraphBuilder<T> addNode(GraphNode<? extends T>... graphNodeArr) {
        this.graphNodes.addAll((Collection) Arrays.stream(graphNodeArr).map(new n(1)).collect(Collectors.toList()));
        return this;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ GraphNode lambda$addNode$0(GraphNode graphNode) {
        return graphNode;
    }
}
