package androidx.media3.effect;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import i.C0212a;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ExternalTextureManager extends TextureManager {
    private static final int[] ADDITIONAL_CANDIDATE_BUFFER_SIZE_GUESSES = {1920, 1088};
    private static final long SURFACE_TEXTURE_TIMEOUT_MS;
    private static final int[] TRANSFORMATION_MATRIX_EXPECTED_ZERO_INDICES = {2, 3, 6, 7, 8, 9, 11, 14};
    private boolean automaticReregistration;
    private int availableFrameCount;
    private FrameInfo currentFrame;
    private boolean currentInputStreamEnded;
    private final boolean experimentalAdjustSurfaceTextureTransformationMatrix;
    private ExternalShaderProgram externalShaderProgram;
    private int externalShaderProgramInputCapacity;
    private final int externalTexId;
    private Future<?> forceSignalEndOfStreamFuture;
    private final GlObjectsProvider glObjectsProvider;
    private FrameInfo lastRegisteredFrame;
    private final Queue<FrameInfo> pendingFrames;
    private CountDownLatch releaseAllFramesLatch;
    private final ScheduledExecutorService scheduledExecutorService;
    private volatile boolean shouldRejectIncomingFrames;
    private final Surface surface;
    private final SurfaceTexture surfaceTexture;
    private final float[] textureTransformMatrix;

    static {
        long j2;
        if (Util.isRunningOnEmulator()) {
            j2 = 20000;
        } else {
            j2 = 500;
        }
        SURFACE_TEXTURE_TIMEOUT_MS = j2;
    }

    public ExternalTextureManager(GlObjectsProvider glObjectsProvider2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, boolean z, boolean z3) {
        super(videoFrameProcessingTaskExecutor);
        this.glObjectsProvider = glObjectsProvider2;
        this.automaticReregistration = z;
        this.experimentalAdjustSurfaceTextureTransformationMatrix = z3;
        try {
            int createExternalTexture = GlUtil.createExternalTexture();
            this.externalTexId = createExternalTexture;
            SurfaceTexture surfaceTexture2 = new SurfaceTexture(createExternalTexture);
            this.surfaceTexture = surfaceTexture2;
            this.textureTransformMatrix = new float[16];
            this.pendingFrames = new ConcurrentLinkedQueue();
            this.scheduledExecutorService = Util.newSingleThreadScheduledExecutor("ExtTexMgr:Timer");
            surfaceTexture2.setOnFrameAvailableListener(new n(this, videoFrameProcessingTaskExecutor));
            this.surface = new Surface(surfaceTexture2);
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    private void cancelForceSignalEndOfStreamTimer() {
        Future<?> future = this.forceSignalEndOfStreamFuture;
        if (future != null) {
            future.cancel(false);
        }
        this.forceSignalEndOfStreamFuture = null;
    }

    /* access modifiers changed from: private */
    public void forceSignalEndOfStream() {
        if (this.availableFrameCount != this.pendingFrames.size()) {
            Log.w("ExtTexMgr", Util.formatInvariant("Forcing EOS after missing %d frames for %d ms, with available frame count: %d", Integer.valueOf(this.pendingFrames.size()), Long.valueOf(SURFACE_TEXTURE_TIMEOUT_MS), Integer.valueOf(this.availableFrameCount)));
            this.currentInputStreamEnded = false;
            this.currentFrame = null;
            this.shouldRejectIncomingFrames = true;
            removeAllSurfaceTextureFrames();
            this.pendingFrames.clear();
            signalEndOfCurrentInputStream();
        }
    }

    private static float guessScaleWithoutSurfaceTextureTrim(float f, int i2) {
        int i7 = i2;
        for (int i8 = 2; i8 <= 256; i8 *= 2) {
            int i10 = (((i2 + i8) - 1) / i8) * i8;
            if (scoreForCandidateBufferSize(i10, f, i2) < scoreForCandidateBufferSize(i7, f, i2)) {
                i7 = i10;
            }
        }
        for (int i11 : ADDITIONAL_CANDIDATE_BUFFER_SIZE_GUESSES) {
            if (i11 >= i2 && scoreForCandidateBufferSize(i11, f, i2) < scoreForCandidateBufferSize(i7, f, i2)) {
                i7 = i11;
            }
        }
        if (scoreForCandidateBufferSize(i7, f, i2) > 1.0E-9f) {
            return f;
        }
        return ((float) i2) / ((float) i7);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        DebugTraceUtil.logEvent("VideoFrameProcessor", "SurfaceTextureInput", -9223372036854775807L);
        if (this.automaticReregistration) {
            this.pendingFrames.add((FrameInfo) Assertions.checkNotNull(this.lastRegisteredFrame));
        }
        if (this.shouldRejectIncomingFrames) {
            this.surfaceTexture.updateTexImage();
            this.pendingFrames.poll();
            if (this.releaseAllFramesLatch != null && this.pendingFrames.isEmpty()) {
                this.releaseAllFramesLatch.countDown();
                return;
            }
            return;
        }
        if (this.currentInputStreamEnded) {
            restartForceSignalEndOfStreamTimer();
        }
        this.availableFrameCount++;
        maybeQueueFrameToExternalShaderProgram();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, SurfaceTexture surfaceTexture2) {
        videoFrameProcessingTaskExecutor.submit(new m(this, 2), false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onInputFrameProcessed$4() {
        this.currentFrame = null;
        if (!this.currentInputStreamEnded || !this.pendingFrames.isEmpty()) {
            maybeQueueFrameToExternalShaderProgram();
            return;
        }
        this.currentInputStreamEnded = false;
        ((ExternalShaderProgram) Assertions.checkNotNull(this.externalShaderProgram)).signalEndOfCurrentInputStream();
        DebugTraceUtil.logEvent("ExternalTextureManager", "SignalEOS", Long.MIN_VALUE);
        cancelForceSignalEndOfStreamTimer();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onReadyToAcceptInputFrame$3() {
        this.externalShaderProgramInputCapacity++;
        maybeQueueFrameToExternalShaderProgram();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerInputFrame$5() {
        this.shouldRejectIncomingFrames = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restartForceSignalEndOfStreamTimer$8() {
        this.videoFrameProcessingTaskExecutor.submit(new m(this, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSamplingGlShaderProgram$2(GlShaderProgram glShaderProgram) {
        this.externalShaderProgramInputCapacity = 0;
        this.externalShaderProgram = (ExternalShaderProgram) glShaderProgram;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signalEndOfCurrentInputStream$6() {
        if (this.automaticReregistration) {
            this.shouldRejectIncomingFrames = true;
        }
        if (!this.pendingFrames.isEmpty() || this.currentFrame != null) {
            this.currentInputStreamEnded = true;
            restartForceSignalEndOfStreamTimer();
            return;
        }
        ((ExternalShaderProgram) Assertions.checkNotNull(this.externalShaderProgram)).signalEndOfCurrentInputStream();
        DebugTraceUtil.logEvent("ExternalTextureManager", "SignalEOS", Long.MIN_VALUE);
        cancelForceSignalEndOfStreamTimer();
    }

    private void maybeQueueFrameToExternalShaderProgram() {
        if (this.externalShaderProgramInputCapacity != 0 && this.availableFrameCount != 0 && this.currentFrame == null) {
            this.surfaceTexture.updateTexImage();
            this.availableFrameCount--;
            FrameInfo element = this.pendingFrames.element();
            this.currentFrame = element;
            this.externalShaderProgramInputCapacity--;
            this.surfaceTexture.getTransformMatrix(this.textureTransformMatrix);
            long timestamp = (this.surfaceTexture.getTimestamp() / 1000) + element.offsetToAddUs;
            if (this.experimentalAdjustSurfaceTextureTransformationMatrix) {
                float[] fArr = this.textureTransformMatrix;
                Format format = element.format;
                removeSurfaceTextureScaleFromTransformMatrix(fArr, timestamp, format.width, format.height);
            }
            ((ExternalShaderProgram) Assertions.checkNotNull(this.externalShaderProgram)).setTextureTransformMatrix(this.textureTransformMatrix);
            GlObjectsProvider glObjectsProvider2 = this.glObjectsProvider;
            int i2 = this.externalTexId;
            Format format2 = element.format;
            ((ExternalShaderProgram) Assertions.checkNotNull(this.externalShaderProgram)).queueInputFrame(glObjectsProvider2, new GlTextureInfo(i2, -1, -1, format2.width, format2.height), timestamp);
            Assertions.checkStateNotNull(this.pendingFrames.remove());
            DebugTraceUtil.logEvent("VideoFrameProcessor", "QueueFrame", timestamp);
        }
    }

    private void removeAllSurfaceTextureFrames() {
        while (true) {
            int i2 = this.availableFrameCount;
            if (i2 <= 0) {
                break;
            }
            this.availableFrameCount = i2 - 1;
            this.surfaceTexture.updateTexImage();
            this.pendingFrames.remove();
        }
        if (this.releaseAllFramesLatch != null && this.pendingFrames.isEmpty()) {
            this.releaseAllFramesLatch.countDown();
        }
    }

    private static void removeSurfaceTextureScaleFromTransformMatrix(float[] fArr, long j2, int i2, int i7) {
        boolean z;
        boolean z3;
        boolean z7;
        char c5;
        char c6;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        float[] fArr2 = fArr;
        boolean z13 = true;
        if (fArr2.length != 16) {
            z = true;
        } else {
            z = false;
        }
        for (int i8 : TRANSFORMATION_MATRIX_EXPECTED_ZERO_INDICES) {
            if (Math.abs(fArr2[i8]) > 1.0E-9f) {
                z12 = true;
            } else {
                z12 = false;
            }
            z |= z12;
        }
        if (Math.abs(fArr2[10] - 1.0f) > 1.0E-9f) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z14 = z | z3;
        if (Math.abs(fArr2[15] - 1.0f) > 1.0E-9f) {
            z7 = true;
        } else {
            z7 = false;
        }
        boolean z15 = z14 | z7;
        char c8 = 12;
        char c10 = 4;
        if (Math.abs(fArr2[0]) > 1.0E-9f && Math.abs(fArr2[5]) > 1.0E-9f) {
            if (Math.abs(fArr2[1]) > 1.0E-9f) {
                z11 = true;
            } else {
                z11 = false;
            }
            boolean z16 = z15 | z11;
            if (Math.abs(fArr2[4]) <= 1.0E-9f) {
                z13 = false;
            }
            z13 |= z16;
            c6 = 13;
            c10 = 5;
            c5 = 0;
        } else if (Math.abs(fArr2[1]) <= 1.0E-9f || Math.abs(fArr2[4]) <= 1.0E-9f) {
            c5 = 65535;
            c6 = 65535;
            c8 = 65535;
            c10 = 65535;
        } else {
            if (Math.abs(fArr2[0]) > 1.0E-9f) {
                z9 = true;
            } else {
                z9 = false;
            }
            boolean z17 = z15 | z9;
            if (Math.abs(fArr2[5]) > 1.0E-9f) {
                z10 = true;
            } else {
                z10 = false;
            }
            z13 = z17 | z10;
            c6 = 12;
            c8 = 13;
            c5 = 1;
        }
        if (z13) {
            DebugTraceUtil.logEvent("ExternalTextureManager", "SurfaceTextureTransformFix", j2, "Unable to apply SurfaceTexture fix", new Object[0]);
            return;
        }
        float f = fArr2[c5];
        float f5 = fArr2[c8];
        if (Math.abs(f) + 1.0E-9f < 1.0f) {
            float copySign = Math.copySign(guessScaleWithoutSurfaceTextureTrim(Math.abs(f), i2), f);
            float a7 = C0212a.a(f, copySign, 0.5f, f5);
            DebugTraceUtil.logEvent("ExternalTextureManager", "SurfaceTextureTransformFix", j2, "Width scale adjusted.", new Object[0]);
            fArr2[c5] = copySign;
            fArr2[c8] = a7;
        }
        float f8 = fArr2[c10];
        float f10 = fArr2[c6];
        if (Math.abs(f8) + 1.0E-9f < 1.0f) {
            float copySign2 = Math.copySign(guessScaleWithoutSurfaceTextureTrim(Math.abs(f8), i7), f8);
            float a10 = C0212a.a(f8, copySign2, 0.5f, f10);
            DebugTraceUtil.logEvent("ExternalTextureManager", "SurfaceTextureTransformFix", j2, "Height scale adjusted.", new Object[0]);
            fArr2[c10] = copySign2;
            fArr2[c6] = a10;
        }
    }

    private void restartForceSignalEndOfStreamTimer() {
        cancelForceSignalEndOfStreamTimer();
        this.forceSignalEndOfStreamFuture = this.scheduledExecutorService.schedule(new o(0, this), SURFACE_TEXTURE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
    }

    private static float scoreForCandidateBufferSize(int i2, float f, int i7) {
        float f5 = 1.0f;
        for (int i8 = 0; i8 <= 2; i8++) {
            float f8 = ((((float) i7) - ((float) i8)) / ((float) i2)) - f;
            if (Math.abs(f8) < f5) {
                f5 = Math.abs(f8);
            }
        }
        return f5;
    }

    public Surface getInputSurface() {
        return this.surface;
    }

    public int getPendingFrameCount() {
        return this.pendingFrames.size();
    }

    public void onInputFrameProcessed(GlTextureInfo glTextureInfo) {
        this.videoFrameProcessingTaskExecutor.submit(new m(this, 3));
    }

    public void onReadyToAcceptInputFrame() {
        this.videoFrameProcessingTaskExecutor.submit(new m(this, 5));
    }

    public void registerInputFrame(FrameInfo frameInfo) {
        this.lastRegisteredFrame = frameInfo;
        if (!this.automaticReregistration) {
            this.pendingFrames.add(frameInfo);
        }
        this.videoFrameProcessingTaskExecutor.submit(new m(this, 1));
    }

    public void release() {
        this.surfaceTexture.release();
        this.surface.release();
        this.scheduledExecutorService.shutdownNow();
    }

    public void setInputFrameInfo(FrameInfo frameInfo, boolean z) {
        this.automaticReregistration = z;
        if (z) {
            this.lastRegisteredFrame = frameInfo;
            SurfaceTexture surfaceTexture2 = this.surfaceTexture;
            Format format = frameInfo.format;
            surfaceTexture2.setDefaultBufferSize(format.width, format.height);
        }
    }

    public void setSamplingGlShaderProgram(GlShaderProgram glShaderProgram) {
        Assertions.checkState(glShaderProgram instanceof ExternalShaderProgram);
        this.videoFrameProcessingTaskExecutor.submit(new c(2, this, glShaderProgram));
    }

    public void signalEndOfCurrentInputStream() {
        this.videoFrameProcessingTaskExecutor.submit(new m(this, 0));
    }
}
