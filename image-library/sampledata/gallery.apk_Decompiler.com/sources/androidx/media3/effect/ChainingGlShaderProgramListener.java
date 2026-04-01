package androidx.media3.effect;

import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.util.Assertions;
import androidx.media3.effect.GlShaderProgram;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ChainingGlShaderProgramListener implements GlShaderProgram.InputListener, GlShaderProgram.OutputListener {
    private final FrameConsumptionManager frameConsumptionManager;
    private final GlShaderProgram producingGlShaderProgram;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    public ChainingGlShaderProgramListener(GlObjectsProvider glObjectsProvider, GlShaderProgram glShaderProgram, GlShaderProgram glShaderProgram2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2) {
        boolean z;
        if (glShaderProgram != glShaderProgram2) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "Creating a self loop in the chain: " + glShaderProgram);
        this.producingGlShaderProgram = glShaderProgram;
        this.frameConsumptionManager = new FrameConsumptionManager(glObjectsProvider, glShaderProgram2, videoFrameProcessingTaskExecutor2);
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onInputFrameProcessed$0(GlTextureInfo glTextureInfo) {
        this.producingGlShaderProgram.releaseOutputFrame(glTextureInfo);
    }

    public synchronized void onCurrentOutputStreamEnded() {
        this.frameConsumptionManager.signalEndOfCurrentStream();
    }

    public void onInputFrameProcessed(GlTextureInfo glTextureInfo) {
        this.videoFrameProcessingTaskExecutor.submit(new c(0, this, glTextureInfo));
    }

    public synchronized void onOutputFrameAvailable(GlTextureInfo glTextureInfo, long j2) {
        this.frameConsumptionManager.queueInputFrame(glTextureInfo, j2);
    }

    public synchronized void onReadyToAcceptInputFrame() {
        this.frameConsumptionManager.onReadyToAcceptInputFrame();
    }
}
