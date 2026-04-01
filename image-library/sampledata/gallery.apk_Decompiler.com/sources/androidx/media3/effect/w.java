package androidx.media3.effect;

import androidx.media3.common.FrameInfo;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements VideoFrameProcessingTaskExecutor.Task {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TexIdTextureManager f1018a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ FrameInfo f1019c;
    public final /* synthetic */ long d;

    public /* synthetic */ w(TexIdTextureManager texIdTextureManager, int i2, FrameInfo frameInfo, long j2) {
        this.f1018a = texIdTextureManager;
        this.b = i2;
        this.f1019c = frameInfo;
        this.d = j2;
    }

    public final void run() {
        this.f1018a.lambda$queueInputTexture$1(this.b, this.f1019c, this.d);
    }
}
