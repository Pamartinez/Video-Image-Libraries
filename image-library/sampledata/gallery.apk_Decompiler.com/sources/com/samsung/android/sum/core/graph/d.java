package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ GraphBase e;

    public /* synthetic */ d(GraphBase graphBase, int i2) {
        this.d = i2;
        this.e = graphBase;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        GraphBase graphBase = this.e;
        MediaBuffer mediaBuffer = (MediaBuffer) obj;
        switch (i2) {
            case 0:
                graphBase.lambda$runBatch$1(mediaBuffer);
                return;
            default:
                graphBase.lambda$runOneWay$0(mediaBuffer);
                return;
        }
    }
}
