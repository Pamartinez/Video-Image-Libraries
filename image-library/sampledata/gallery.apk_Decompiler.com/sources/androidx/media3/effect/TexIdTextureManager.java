package androidx.media3.effect;

import H.c;
import androidx.media3.common.Format;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TexIdTextureManager extends TextureManager {
    private FrameConsumptionManager frameConsumptionManager;
    private OnInputFrameProcessedListener frameProcessedListener;
    private final GlObjectsProvider glObjectsProvider;
    private FrameInfo inputFrameInfo;

    public TexIdTextureManager(GlObjectsProvider glObjectsProvider2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor) {
        super(videoFrameProcessingTaskExecutor);
        this.glObjectsProvider = glObjectsProvider2;
    }

    /* access modifiers changed from: private */
    public void lambda$onInputFrameProcessed$0(GlTextureInfo glTextureInfo) {
        ((c) ((OnInputFrameProcessedListener) Assertions.checkNotNull(this.frameProcessedListener))).f295a.onCompositionVideoFrameProcessorInputFrameProcessed(glTextureInfo.texId, GlUtil.createGlSyncFence());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$queueInputTexture$1(int i2, FrameInfo frameInfo, long j2) {
        Format format = frameInfo.format;
        ((FrameConsumptionManager) Assertions.checkNotNull(this.frameConsumptionManager)).queueInputFrame(new GlTextureInfo(i2, -1, -1, format.width, format.height), j2);
        DebugTraceUtil.logEvent("VideoFrameProcessor", "QueueTexture", j2, "%dx%d", Integer.valueOf(frameInfo.format.width), Integer.valueOf(frameInfo.format.height));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signalEndOfCurrentInputStream$2() {
        ((FrameConsumptionManager) Assertions.checkNotNull(this.frameConsumptionManager)).signalEndOfCurrentStream();
        DebugTraceUtil.logEvent("TexIdTextureManager", "SignalEOS", Long.MIN_VALUE);
    }

    public int getPendingFrameCount() {
        return ((FrameConsumptionManager) Assertions.checkNotNull(this.frameConsumptionManager)).getPendingFrameCount();
    }

    public void onInputFrameProcessed(GlTextureInfo glTextureInfo) {
        this.videoFrameProcessingTaskExecutor.submit(new c(5, this, glTextureInfo));
    }

    public void onReadyToAcceptInputFrame() {
        Assertions.checkNotNull(this.frameConsumptionManager);
        VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor = this.videoFrameProcessingTaskExecutor;
        FrameConsumptionManager frameConsumptionManager2 = this.frameConsumptionManager;
        Objects.requireNonNull(frameConsumptionManager2);
        videoFrameProcessingTaskExecutor.submit(new s(2, frameConsumptionManager2));
    }

    public void queueInputTexture(int i2, long j2) {
        Assertions.checkNotNull(this.frameProcessedListener);
        this.videoFrameProcessingTaskExecutor.submit(new w(this, i2, (FrameInfo) Assertions.checkNotNull(this.inputFrameInfo), j2));
    }

    public void setInputFrameInfo(FrameInfo frameInfo, boolean z) {
        this.inputFrameInfo = frameInfo;
    }

    public void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
        this.frameProcessedListener = onInputFrameProcessedListener;
    }

    public void setSamplingGlShaderProgram(GlShaderProgram glShaderProgram) {
        this.frameConsumptionManager = new FrameConsumptionManager(this.glObjectsProvider, glShaderProgram, this.videoFrameProcessingTaskExecutor);
    }

    public void signalEndOfCurrentInputStream() {
        this.videoFrameProcessingTaskExecutor.submit(new s(1, this));
    }

    public void release() {
    }
}
