package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.effect.DefaultVideoFrameProcessor;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements VideoFrameProcessingTaskExecutor.Task {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1001a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f1002c;

    public /* synthetic */ c(int i2, Object obj, Object obj2) {
        this.f1001a = i2;
        this.b = obj;
        this.f1002c = obj2;
    }

    public final void run() {
        switch (this.f1001a) {
            case 0:
                ((ChainingGlShaderProgramListener) this.b).lambda$onInputFrameProcessed$0((GlTextureInfo) this.f1002c);
                return;
            case 1:
                ((DefaultVideoFrameProcessor) this.b).lambda$registerInputStream$2((DefaultVideoFrameProcessor.InputStreamInfo) this.f1002c);
                return;
            case 2:
                ((ExternalTextureManager) this.b).lambda$setSamplingGlShaderProgram$2((GlShaderProgram) this.f1002c);
                return;
            case 3:
                ((FinalShaderProgramWrapper) this.b).lambda$setOutputSurfaceInfo$3((SurfaceInfo) this.f1002c);
                return;
            case 4:
                ((FrameConsumptionManager) this.b).lambda$onReadyToAcceptInputFrame$0((TimedGlTextureInfo) this.f1002c);
                return;
            default:
                ((TexIdTextureManager) this.b).lambda$onInputFrameProcessed$0((GlTextureInfo) this.f1002c);
                return;
        }
    }
}
