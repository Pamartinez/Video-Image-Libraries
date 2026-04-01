package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.cache.DiskCache;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ k(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                GraphNodeBase.lambda$configOutputChannels$5((HashMap) obj2, (GraphEdge) obj);
                return;
            default:
                MFGraph.lambda$run$3((DiskCache) obj2, (MediaBuffer) obj);
                return;
        }
    }
}
