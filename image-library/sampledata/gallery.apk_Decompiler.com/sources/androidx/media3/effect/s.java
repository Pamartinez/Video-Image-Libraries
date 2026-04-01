package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements VideoFrameProcessingTaskExecutor.Task {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1017a;
    public final /* synthetic */ Object b;

    public /* synthetic */ s(int i2, Object obj) {
        this.f1017a = i2;
        this.b = obj;
    }

    public final void run() {
        int i2 = this.f1017a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                ((GlShaderProgram) obj).signalEndOfCurrentInputStream();
                return;
            case 1:
                ((TexIdTextureManager) obj).lambda$signalEndOfCurrentInputStream$2();
                return;
            default:
                ((FrameConsumptionManager) obj).onReadyToAcceptInputFrame();
                return;
        }
    }
}
