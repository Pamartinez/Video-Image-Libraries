package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements VideoFrameProcessingTaskExecutor.Task {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1007a;
    public final /* synthetic */ long b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f1008c;

    public /* synthetic */ h(Object obj, long j2, int i2) {
        this.f1007a = i2;
        this.f1008c = obj;
        this.b = j2;
    }

    public final void run() {
        switch (this.f1007a) {
            case 0:
                ((DefaultVideoCompositor) this.f1008c).lambda$releaseOutputTexture$0(this.b);
                return;
            case 1:
                ((DefaultVideoFrameProcessor) this.f1008c).lambda$renderOutputFrame$3(this.b);
                return;
            default:
                ((FinalShaderProgramWrapper) this.f1008c).lambda$releaseOutputTexture$0(this.b);
                return;
        }
    }
}
