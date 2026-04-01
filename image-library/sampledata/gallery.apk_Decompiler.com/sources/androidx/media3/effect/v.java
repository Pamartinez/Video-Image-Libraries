package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.MultipleInputVideoGraph;
import androidx.media3.effect.SingleInputVideoGraph;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ VideoFrameProcessor.Listener g;

    public /* synthetic */ v(VideoFrameProcessor.Listener listener, long j2, boolean z, int i2) {
        this.d = i2;
        this.g = listener;
        this.e = j2;
        this.f = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MultipleInputVideoGraph.AnonymousClass1) this.g).lambda$onOutputFrameAvailableForRendering$2(this.e, this.f);
                return;
            default:
                ((SingleInputVideoGraph.AnonymousClass1) this.g).lambda$onOutputFrameAvailableForRendering$2(this.e, this.f);
                return;
        }
    }
}
