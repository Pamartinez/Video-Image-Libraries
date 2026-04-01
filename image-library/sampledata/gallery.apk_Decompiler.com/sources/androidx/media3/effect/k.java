package androidx.media3.effect;

import androidx.media3.effect.DefaultVideoFrameProcessor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DefaultVideoFrameProcessor e;
    public final /* synthetic */ DefaultVideoFrameProcessor.InputStreamInfo f;

    public /* synthetic */ k(DefaultVideoFrameProcessor defaultVideoFrameProcessor, DefaultVideoFrameProcessor.InputStreamInfo inputStreamInfo, int i2) {
        this.d = i2;
        this.e = defaultVideoFrameProcessor;
        this.f = inputStreamInfo;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$configure$5(this.f);
                return;
            default:
                this.e.lambda$configure$6(this.f);
                return;
        }
    }
}
