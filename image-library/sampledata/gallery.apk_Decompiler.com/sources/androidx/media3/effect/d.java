package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements VideoFrameProcessingTaskExecutor.Task {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1003a;
    public final /* synthetic */ GlTextureInfo b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f1004c;
    public final /* synthetic */ Object d;

    public /* synthetic */ d(Object obj, GlTextureInfo glTextureInfo, long j2, int i2) {
        this.f1003a = i2;
        this.d = obj;
        this.b = glTextureInfo;
        this.f1004c = j2;
    }

    public final void run() {
        switch (this.f1003a) {
            case 0:
                ((DefaultShaderProgram) this.d).drawFrame(this.b.texId, this.f1004c);
                return;
            default:
                ((FrameConsumptionManager) this.d).lambda$queueInputFrame$1(this.b, this.f1004c);
                return;
        }
    }
}
