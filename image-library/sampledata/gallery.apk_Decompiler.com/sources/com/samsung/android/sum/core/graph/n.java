package com.samsung.android.sum.core.graph;

import android.os.Parcelable;
import android.util.Pair;
import com.samsung.android.sum.core.graph.MFDescriptorGraph;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4116a;

    public /* synthetic */ n(int i2) {
        this.f4116a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4116a) {
            case 0:
                return Integer.valueOf(((MFDescriptorGraph.DescriptorNode) obj).getId());
            case 1:
                return GraphBuilderBase.lambda$addNode$0((GraphNode) obj);
            case 2:
                return GraphEdge.lambda$getBeginNode$0((Pair) obj);
            case 3:
                return GraphEdge.lambda$getEndNode$1((Pair) obj);
            case 4:
                return GraphNodeBase.lambda$configInputChannels$2((GraphEdge) obj);
            default:
                return MFDescriptorGraph.lambda$new$0((Parcelable) obj);
        }
    }
}
