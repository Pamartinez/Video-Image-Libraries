package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.MultipleInputVideoGraph;
import androidx.media3.effect.SingleInputVideoGraph;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ float e;
    public final /* synthetic */ VideoFrameProcessor.Listener f;

    public /* synthetic */ t(VideoFrameProcessor.Listener listener, float f5, int i2) {
        this.d = i2;
        this.f = listener;
        this.e = f5;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MultipleInputVideoGraph.AnonymousClass1) this.f).lambda$onOutputFrameRateChanged$1(this.e);
                return;
            default:
                ((SingleInputVideoGraph.AnonymousClass1) this.f).lambda$onOutputFrameRateChanged$1(this.e);
                return;
        }
    }
}
