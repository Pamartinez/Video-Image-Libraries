package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import androidx.media3.effect.SingleInputVideoGraph;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ p(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((FinalShaderProgramWrapper) this.e).lambda$setOutputSurfaceInfo$4((InterruptedException) this.f);
                return;
            case 1:
                ((FinalShaderProgramWrapper) this.e).lambda$destroyOutputEglSurface$5((GlUtil.GlException) this.f);
                return;
            case 2:
                ((FinalShaderProgramWrapper) this.e).lambda$ensureConfigured$7((Size) this.f);
                return;
            case 3:
                ((SingleInputVideoGraph.AnonymousClass1) this.e).lambda$onError$3((VideoFrameProcessingException) this.f);
                return;
            default:
                ((VideoFrameProcessingTaskExecutor) this.e).lambda$invoke$0((VideoFrameProcessingTaskExecutor.Task) this.f);
                return;
        }
    }
}
