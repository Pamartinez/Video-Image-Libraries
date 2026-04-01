package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements VideoFrameProcessingTaskExecutor.Task {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1009a;
    public final /* synthetic */ DefaultVideoFrameProcessor b;

    public /* synthetic */ j(DefaultVideoFrameProcessor defaultVideoFrameProcessor, int i2) {
        this.f1009a = i2;
        this.b = defaultVideoFrameProcessor;
    }

    public final void run() {
        int i2 = this.f1009a;
        DefaultVideoFrameProcessor defaultVideoFrameProcessor = this.b;
        switch (i2) {
            case 0:
                defaultVideoFrameProcessor.releaseGlObjects();
                return;
            default:
                defaultVideoFrameProcessor.configurePendingInputStream();
                return;
        }
    }
}
