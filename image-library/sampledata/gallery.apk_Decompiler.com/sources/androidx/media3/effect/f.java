package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements VideoFrameProcessingTaskExecutor.Task {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1005a;
    public final /* synthetic */ DefaultVideoCompositor b;

    public /* synthetic */ f(DefaultVideoCompositor defaultVideoCompositor, int i2) {
        this.f1005a = i2;
        this.b = defaultVideoCompositor;
    }

    public final void run() {
        int i2 = this.f1005a;
        DefaultVideoCompositor defaultVideoCompositor = this.b;
        switch (i2) {
            case 0:
                defaultVideoCompositor.releaseGlObjects();
                return;
            case 1:
                defaultVideoCompositor.setupGlObjects();
                return;
            default:
                defaultVideoCompositor.maybeComposite();
                return;
        }
    }
}
