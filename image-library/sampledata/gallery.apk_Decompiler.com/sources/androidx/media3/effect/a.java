package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements VideoFrameProcessingTaskExecutor.Task {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f998a;
    public final /* synthetic */ BitmapTextureManager b;

    public /* synthetic */ a(BitmapTextureManager bitmapTextureManager, int i2) {
        this.f998a = i2;
        this.b = bitmapTextureManager;
    }

    public final void run() {
        int i2 = this.f998a;
        BitmapTextureManager bitmapTextureManager = this.b;
        switch (i2) {
            case 0:
                bitmapTextureManager.lambda$signalEndOfCurrentInputStream$2();
                return;
            case 1:
                bitmapTextureManager.lambda$release$3();
                return;
            default:
                bitmapTextureManager.lambda$onReadyToAcceptInputFrame$0();
                return;
        }
    }
}
