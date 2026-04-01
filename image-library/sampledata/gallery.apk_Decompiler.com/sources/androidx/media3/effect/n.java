package androidx.media3.effect;

import android.graphics.SurfaceTexture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ExternalTextureManager f1016a;
    public final /* synthetic */ VideoFrameProcessingTaskExecutor b;

    public /* synthetic */ n(ExternalTextureManager externalTextureManager, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor) {
        this.f1016a = externalTextureManager;
        this.b = videoFrameProcessingTaskExecutor;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.f1016a.lambda$new$1(this.b, surfaceTexture);
    }
}
