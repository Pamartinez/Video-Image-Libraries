package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MFGraph e;

    public /* synthetic */ q(MFGraph mFGraph, int i2) {
        this.d = i2;
        this.e = mFGraph;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MFGraph mFGraph = this.e;
        MediaBuffer mediaBuffer = (MediaBuffer) obj;
        switch (i2) {
            case 0:
                mFGraph.onReceiveOutputBuffer(mediaBuffer);
                return;
            default:
                mFGraph.lambda$run$2(mediaBuffer);
                return;
        }
    }
}
