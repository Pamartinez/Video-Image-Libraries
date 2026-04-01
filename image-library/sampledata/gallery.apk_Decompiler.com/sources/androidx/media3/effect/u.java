package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.MultipleInputVideoGraph;
import androidx.media3.effect.SingleInputVideoGraph;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ VideoFrameProcessor.Listener g;

    public /* synthetic */ u(VideoFrameProcessor.Listener listener, int i2, int i7, int i8) {
        this.d = i8;
        this.g = listener;
        this.e = i2;
        this.f = i7;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MultipleInputVideoGraph.AnonymousClass1) this.g).lambda$onOutputSizeChanged$0(this.e, this.f);
                return;
            default:
                ((SingleInputVideoGraph.AnonymousClass1) this.g).lambda$onOutputSizeChanged$0(this.e, this.f);
                return;
        }
    }
}
