package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((GraphNode) obj).release();
                return;
            case 1:
                GraphNodeBase.lambda$release$15((GraphEdge) obj);
                return;
            case 2:
                ((GraphNode) obj).pause();
                return;
            case 3:
                ((GraphNode) obj).resume();
                return;
            default:
                MFGraph.lambda$configOutputChannel$1((MediaBuffer) obj);
                return;
        }
    }
}
