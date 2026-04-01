package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements VideoFrameProcessingTaskExecutor.Task {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1015a;
    public final /* synthetic */ ExternalTextureManager b;

    public /* synthetic */ m(ExternalTextureManager externalTextureManager, int i2) {
        this.f1015a = i2;
        this.b = externalTextureManager;
    }

    public final void run() {
        int i2 = this.f1015a;
        ExternalTextureManager externalTextureManager = this.b;
        switch (i2) {
            case 0:
                externalTextureManager.lambda$signalEndOfCurrentInputStream$6();
                return;
            case 1:
                externalTextureManager.lambda$registerInputFrame$5();
                return;
            case 2:
                externalTextureManager.lambda$new$0();
                return;
            case 3:
                externalTextureManager.lambda$onInputFrameProcessed$4();
                return;
            case 4:
                externalTextureManager.forceSignalEndOfStream();
                return;
            default:
                externalTextureManager.lambda$onReadyToAcceptInputFrame$3();
                return;
        }
    }
}
