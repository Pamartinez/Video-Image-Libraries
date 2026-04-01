package androidx.media3.effect;

import F2.N;
import android.content.Context;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.Size;
import androidx.media3.effect.GlShaderProgram;
import androidx.media3.effect.GlTextureProducer;
import androidx.media3.effect.ScaleAndRotateTransformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class FinalShaderProgramWrapper implements GlShaderProgram, GlTextureProducer {
    private final Queue<TimedGlTextureInfo> availableFrames;
    private final Context context;
    private DefaultShaderProgram defaultShaderProgram;
    private final EGLContext eglContext;
    private final EGLDisplay eglDisplay;
    private int inputHeight;
    private GlShaderProgram.InputListener inputListener;
    private int inputWidth;
    private boolean isInputStreamEndedWithPendingAvailableFrames;
    private Listener listener;
    private final List<GlMatrixTransformation> matrixTransformations = new ArrayList();
    private boolean matrixTransformationsChanged;
    private final ColorInfo outputColorInfo;
    private EGLSurface outputEglSurface;
    private Size outputSizeBeforeSurfaceTransformation;
    private SurfaceInfo outputSurfaceInfo;
    private boolean outputSurfaceInfoChanged;
    private final TexturePool outputTexturePool;
    private final LongArrayQueue outputTextureTimestamps;
    private final EGLSurface placeholderSurface;
    private long redrawFramePresentationTimeUs;
    private final boolean renderFramesAutomatically;
    private final List<Object> rgbMatrices = new ArrayList();
    private final int sdrWorkingColorSpace;
    private final LongArrayQueue syncObjects;
    private final GlTextureProducer.Listener textureOutputListener;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;
    private final VideoFrameProcessor.Listener videoFrameProcessorListener;
    private final Executor videoFrameProcessorListenerExecutor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onFrameRendered(long j2);

        void onInputStreamProcessed();
    }

    public FinalShaderProgramWrapper(Context context2, EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, ColorInfo colorInfo, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2, Executor executor, VideoFrameProcessor.Listener listener2, GlTextureProducer.Listener listener3, int i2, int i7, boolean z) {
        this.context = context2;
        this.eglDisplay = eGLDisplay;
        this.eglContext = eGLContext;
        this.placeholderSurface = eGLSurface;
        this.outputColorInfo = colorInfo;
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
        this.videoFrameProcessorListenerExecutor = executor;
        this.videoFrameProcessorListener = listener2;
        this.textureOutputListener = listener3;
        this.sdrWorkingColorSpace = i7;
        this.renderFramesAutomatically = z;
        this.inputListener = new GlShaderProgram.InputListener() {
        };
        this.availableFrames = new ConcurrentLinkedQueue();
        this.outputTexturePool = new TexturePool(ColorInfo.isTransferHdr(colorInfo), i2);
        this.outputTextureTimestamps = new LongArrayQueue(i2);
        this.syncObjects = new LongArrayQueue(i2);
        this.redrawFramePresentationTimeUs = -9223372036854775807L;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [F2.N, F2.Q] */
    private DefaultShaderProgram createDefaultShaderProgram(int i2, int i7, int i8) {
        boolean z;
        ? n = new N(4);
        n.c(this.matrixTransformations);
        if (i2 != 0) {
            n.a(new ScaleAndRotateTransformation.Builder().setRotationDegrees((float) i2).build());
        }
        boolean z3 = false;
        n.a(Presentation.createForWidthAndHeight(i7, i8, 0));
        DefaultShaderProgram createApplyingOetf = DefaultShaderProgram.createApplyingOetf(this.context, n.f(), this.rgbMatrices, this.outputColorInfo, this.sdrWorkingColorSpace);
        Size configure = createApplyingOetf.configure(this.inputWidth, this.inputHeight);
        SurfaceInfo surfaceInfo = this.outputSurfaceInfo;
        if (surfaceInfo != null) {
            SurfaceInfo surfaceInfo2 = (SurfaceInfo) Assertions.checkNotNull(surfaceInfo);
            if (configure.getWidth() == surfaceInfo2.width) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            if (configure.getHeight() == surfaceInfo2.height) {
                z3 = true;
            }
            Assertions.checkState(z3);
        }
        return createApplyingOetf;
    }

    private void destroyOutputEglSurface() {
        if (this.outputEglSurface != null) {
            try {
                GlUtil.focusEglSurface(this.eglDisplay, this.eglContext, this.placeholderSurface, 1, 1);
                GlUtil.destroyEglSurface(this.eglDisplay, this.outputEglSurface);
            } catch (GlUtil.GlException e) {
                this.videoFrameProcessorListenerExecutor.execute(new p(1, this, e));
            } finally {
                this.outputEglSurface = null;
            }
        }
    }

    private boolean ensureConfigured(GlObjectsProvider glObjectsProvider, int i2, int i7) {
        boolean z;
        int i8;
        int i10;
        int i11;
        boolean z3 = true;
        if (this.inputWidth == i2 && this.inputHeight == i7 && this.outputSizeBeforeSurfaceTransformation != null) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            this.inputWidth = i2;
            this.inputHeight = i7;
            Size configureAndGetOutputSize = MatrixUtils.configureAndGetOutputSize(i2, i7, this.matrixTransformations);
            if (!Objects.equals(this.outputSizeBeforeSurfaceTransformation, configureAndGetOutputSize)) {
                this.outputSizeBeforeSurfaceTransformation = configureAndGetOutputSize;
                this.videoFrameProcessorListenerExecutor.execute(new p(2, this, configureAndGetOutputSize));
            }
        }
        Assertions.checkNotNull(this.outputSizeBeforeSurfaceTransformation);
        SurfaceInfo surfaceInfo = this.outputSurfaceInfo;
        if (surfaceInfo == null && this.textureOutputListener == null) {
            if (this.outputEglSurface != null) {
                z3 = false;
            }
            Assertions.checkState(z3);
            DefaultShaderProgram defaultShaderProgram2 = this.defaultShaderProgram;
            if (defaultShaderProgram2 != null) {
                defaultShaderProgram2.release();
                this.defaultShaderProgram = null;
            }
            Log.w("FinalShaderWrapper", "Output surface and size not set, dropping frame.");
            return false;
        }
        if (surfaceInfo == null) {
            i8 = this.outputSizeBeforeSurfaceTransformation.getWidth();
        } else {
            i8 = surfaceInfo.width;
        }
        SurfaceInfo surfaceInfo2 = this.outputSurfaceInfo;
        if (surfaceInfo2 == null) {
            i10 = this.outputSizeBeforeSurfaceTransformation.getHeight();
        } else {
            i10 = surfaceInfo2.height;
        }
        SurfaceInfo surfaceInfo3 = this.outputSurfaceInfo;
        if (surfaceInfo3 != null && this.outputEglSurface == null) {
            this.outputEglSurface = glObjectsProvider.createEglSurface(this.eglDisplay, surfaceInfo3.surface, this.outputColorInfo.colorTransfer, surfaceInfo3.isEncoderInputSurface);
        }
        if (this.textureOutputListener != null) {
            this.outputTexturePool.ensureConfigured(glObjectsProvider, i8, i10);
        }
        DefaultShaderProgram defaultShaderProgram3 = this.defaultShaderProgram;
        if (defaultShaderProgram3 != null && (this.outputSurfaceInfoChanged || z || this.matrixTransformationsChanged)) {
            defaultShaderProgram3.release();
            this.defaultShaderProgram = null;
            this.outputSurfaceInfoChanged = false;
            this.matrixTransformationsChanged = false;
        }
        if (this.defaultShaderProgram == null) {
            SurfaceInfo surfaceInfo4 = this.outputSurfaceInfo;
            if (surfaceInfo4 == null) {
                i11 = 0;
            } else {
                i11 = surfaceInfo4.orientationDegrees;
            }
            this.defaultShaderProgram = createDefaultShaderProgram(i11, i8, i10);
            this.outputSurfaceInfoChanged = false;
        }
        return true;
    }

    private int getInputCapacity() {
        if (this.textureOutputListener == null) {
            return 1;
        }
        return this.outputTexturePool.freeTextureCount();
    }

    private boolean isWaitingForRedrawFrame() {
        if (this.redrawFramePresentationTimeUs != -9223372036854775807L) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$destroyOutputEglSurface$5(GlUtil.GlException glException) {
        this.videoFrameProcessorListener.onError(VideoFrameProcessingException.from(glException));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$ensureConfigured$7(Size size) {
        this.videoFrameProcessorListener.onOutputSizeChanged(size.getWidth(), size.getHeight());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$queueInputFrame$1(long j2) {
        this.videoFrameProcessorListener.onOutputFrameAvailableForRendering(j2, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$queueInputFrame$2(long j2) {
        this.videoFrameProcessorListener.onOutputFrameAvailableForRendering(j2, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$renderFrame$6(Exception exc, long j2) {
        this.videoFrameProcessorListener.onError(VideoFrameProcessingException.from(exc, j2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOutputSurfaceInfo$4(InterruptedException interruptedException) {
        this.videoFrameProcessorListener.onError(VideoFrameProcessingException.from(interruptedException));
    }

    /* access modifiers changed from: private */
    /* renamed from: releaseOutputTextureInternal */
    public void lambda$releaseOutputTexture$0(long j2) {
        boolean z;
        if (this.textureOutputListener != null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        while (this.outputTexturePool.freeTextureCount() < this.outputTexturePool.capacity() && this.outputTextureTimestamps.element() <= j2) {
            this.outputTexturePool.freeTexture();
            this.outputTextureTimestamps.remove();
            GlUtil.deleteSyncObject(this.syncObjects.remove());
            this.inputListener.onReadyToAcceptInputFrame();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        if (r10 != r7.redrawFramePresentationTimeUs) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void renderFrame(androidx.media3.common.GlObjectsProvider r8, androidx.media3.common.GlTextureInfo r9, long r10, long r12) {
        /*
            r7 = this;
            r0 = -2
            int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x001c
            int r1 = r9.width     // Catch:{ VideoFrameProcessingException | GlException -> 0x0040 }
            int r2 = r9.height     // Catch:{ VideoFrameProcessingException | GlException -> 0x0040 }
            boolean r8 = r7.ensureConfigured(r8, r1, r2)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0040 }
            if (r8 == 0) goto L_0x001c
            boolean r8 = r7.isWaitingForRedrawFrame()     // Catch:{ VideoFrameProcessingException | GlException -> 0x0040 }
            if (r8 == 0) goto L_0x0026
            long r1 = r7.redrawFramePresentationTimeUs     // Catch:{ VideoFrameProcessingException | GlException -> 0x0020 }
            int r8 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r8 == 0) goto L_0x0026
        L_0x001c:
            r1 = r7
            r2 = r9
            r3 = r10
            goto L_0x0045
        L_0x0020:
            r0 = move-exception
            r8 = r0
            r1 = r7
            r2 = r9
            r3 = r10
            goto L_0x0058
        L_0x0026:
            androidx.media3.common.SurfaceInfo r8 = r7.outputSurfaceInfo     // Catch:{ VideoFrameProcessingException | GlException -> 0x0040 }
            if (r8 == 0) goto L_0x0035
            r1 = r7
            r2 = r9
            r3 = r10
            r5 = r12
            r1.renderFrameToOutputSurface(r2, r3, r5)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0032 }
            goto L_0x0062
        L_0x0032:
            r0 = move-exception
        L_0x0033:
            r8 = r0
            goto L_0x0058
        L_0x0035:
            r1 = r7
            r2 = r9
            r3 = r10
            androidx.media3.effect.GlTextureProducer$Listener r7 = r1.textureOutputListener     // Catch:{ VideoFrameProcessingException | GlException -> 0x0032 }
            if (r7 == 0) goto L_0x0062
            r1.renderFrameToOutputTexture(r2, r3)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0032 }
            goto L_0x0062
        L_0x0040:
            r0 = move-exception
            r1 = r7
            r2 = r9
            r3 = r10
            goto L_0x0033
        L_0x0045:
            androidx.media3.effect.GlShaderProgram$InputListener r7 = r1.inputListener     // Catch:{ VideoFrameProcessingException | GlException -> 0x0032 }
            r7.onInputFrameProcessed(r2)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0032 }
            if (r0 != 0) goto L_0x0057
            androidx.media3.effect.FinalShaderProgramWrapper$Listener r7 = r1.listener     // Catch:{ VideoFrameProcessingException | GlException -> 0x0032 }
            java.lang.Object r7 = androidx.media3.common.util.Assertions.checkNotNull(r7)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0032 }
            androidx.media3.effect.FinalShaderProgramWrapper$Listener r7 = (androidx.media3.effect.FinalShaderProgramWrapper.Listener) r7     // Catch:{ VideoFrameProcessingException | GlException -> 0x0032 }
            r7.onFrameRendered(r3)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0032 }
        L_0x0057:
            return
        L_0x0058:
            java.util.concurrent.Executor r7 = r1.videoFrameProcessorListenerExecutor
            androidx.media3.effect.q r9 = new androidx.media3.effect.q
            r9.<init>(r1, r8, r3)
            r7.execute(r9)
        L_0x0062:
            androidx.media3.effect.GlShaderProgram$InputListener r7 = r1.inputListener
            r7.onInputFrameProcessed(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.FinalShaderProgramWrapper.renderFrame(androidx.media3.common.GlObjectsProvider, androidx.media3.common.GlTextureInfo, long, long):void");
    }

    private void renderFrameToOutputSurface(GlTextureInfo glTextureInfo, long j2, long j3) {
        boolean z;
        EGLSurface eGLSurface = (EGLSurface) Assertions.checkNotNull(this.outputEglSurface);
        SurfaceInfo surfaceInfo = (SurfaceInfo) Assertions.checkNotNull(this.outputSurfaceInfo);
        GlUtil.focusEglSurface(this.eglDisplay, this.eglContext, eGLSurface, surfaceInfo.width, surfaceInfo.height);
        GlUtil.clearFocusedBuffers();
        ((DefaultShaderProgram) Assertions.checkNotNull(this.defaultShaderProgram)).drawFrame(glTextureInfo.texId, j2);
        if (j3 == -3) {
            if (j2 != -9223372036854775807L) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            j3 = 1000 * j2;
        }
        EGLExt.eglPresentationTimeANDROID(this.eglDisplay, eGLSurface, j3);
        EGL14.eglSwapBuffers(this.eglDisplay, eGLSurface);
        ((Listener) Assertions.checkNotNull(this.listener)).onFrameRendered(j2);
        DebugTraceUtil.logEvent("VideoFrameProcessor", "RenderedToOutputSurface", j2);
    }

    private void renderFrameToOutputTexture(GlTextureInfo glTextureInfo, long j2) {
        GlTextureInfo useTexture = this.outputTexturePool.useTexture();
        this.outputTextureTimestamps.add(j2);
        GlUtil.focusFramebufferUsingCurrentContext(useTexture.fboId, useTexture.width, useTexture.height);
        GlUtil.clearFocusedBuffers();
        ((DefaultShaderProgram) Assertions.checkNotNull(this.defaultShaderProgram)).drawFrame(glTextureInfo.texId, j2);
        long createGlSyncFence = GlUtil.createGlSyncFence();
        this.syncObjects.add(createGlSyncFence);
        ((GlTextureProducer.Listener) Assertions.checkNotNull(this.textureOutputListener)).onTextureRendered(this, useTexture, j2, createGlSyncFence);
    }

    /* access modifiers changed from: private */
    /* renamed from: setOutputSurfaceInfoInternal */
    public void lambda$setOutputSurfaceInfo$3(SurfaceInfo surfaceInfo) {
        boolean z;
        if (this.textureOutputListener == null && !Objects.equals(this.outputSurfaceInfo, surfaceInfo)) {
            SurfaceInfo surfaceInfo2 = this.outputSurfaceInfo;
            if (surfaceInfo2 != null && (surfaceInfo == null || !surfaceInfo2.surface.equals(surfaceInfo.surface))) {
                destroyOutputEglSurface();
            }
            SurfaceInfo surfaceInfo3 = this.outputSurfaceInfo;
            if (surfaceInfo3 != null && surfaceInfo != null && surfaceInfo3.width == surfaceInfo.width && surfaceInfo3.height == surfaceInfo.height && surfaceInfo3.orientationDegrees == surfaceInfo.orientationDegrees) {
                z = false;
            } else {
                z = true;
            }
            this.outputSurfaceInfoChanged = z;
            this.outputSurfaceInfo = surfaceInfo;
        }
    }

    public void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j2) {
        boolean z;
        FinalShaderProgramWrapper finalShaderProgramWrapper;
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        if (!isWaitingForRedrawFrame()) {
            this.videoFrameProcessorListenerExecutor.execute(new r(this, j2, 0));
        }
        if (this.textureOutputListener == null) {
            if (this.renderFramesAutomatically) {
                renderFrame(glObjectsProvider, glTextureInfo, j2, j2 * 1000);
                finalShaderProgramWrapper = this;
            } else {
                finalShaderProgramWrapper = this;
                GlObjectsProvider glObjectsProvider2 = glObjectsProvider;
                GlTextureInfo glTextureInfo2 = glTextureInfo;
                long j3 = j2;
                finalShaderProgramWrapper.availableFrames.add(new TimedGlTextureInfo(glTextureInfo2, j3));
                if (finalShaderProgramWrapper.isWaitingForRedrawFrame()) {
                    if (j3 == finalShaderProgramWrapper.redrawFramePresentationTimeUs) {
                        finalShaderProgramWrapper.redrawFramePresentationTimeUs = -9223372036854775807L;
                        finalShaderProgramWrapper.videoFrameProcessorListenerExecutor.execute(new r(finalShaderProgramWrapper, j3, 1));
                        finalShaderProgramWrapper.renderFrame(glObjectsProvider2, glTextureInfo2, j3, Clock.DEFAULT.nanoTime());
                        finalShaderProgramWrapper.availableFrames.clear();
                    } else {
                        finalShaderProgramWrapper.inputListener.onInputFrameProcessed(glTextureInfo2);
                    }
                }
            }
            finalShaderProgramWrapper.inputListener.onReadyToAcceptInputFrame();
            return;
        }
        GlObjectsProvider glObjectsProvider3 = glObjectsProvider;
        GlTextureInfo glTextureInfo3 = glTextureInfo;
        long j8 = j2;
        if (this.outputTexturePool.freeTextureCount() > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        renderFrame(glObjectsProvider3, glTextureInfo3, j8, j8 * 1000);
    }

    public void release() {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        DefaultShaderProgram defaultShaderProgram2 = this.defaultShaderProgram;
        if (defaultShaderProgram2 != null) {
            defaultShaderProgram2.release();
        }
        try {
            this.outputTexturePool.deleteAllTextures();
            GlUtil.destroyEglSurface(this.eglDisplay, this.outputEglSurface);
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    public void releaseOutputFrame(GlTextureInfo glTextureInfo) {
        throw new UnsupportedOperationException();
    }

    public void releaseOutputTexture(long j2) {
        this.videoFrameProcessingTaskExecutor.submit(new h(this, j2, 2));
    }

    public void renderOutputFrame(GlObjectsProvider glObjectsProvider, long j2) {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        if (this.textureOutputListener == null) {
            Assertions.checkState(!this.renderFramesAutomatically);
            if (!this.availableFrames.isEmpty()) {
                TimedGlTextureInfo remove = this.availableFrames.remove();
                renderFrame(glObjectsProvider, remove.glTextureInfo, remove.presentationTimeUs, j2);
                if (this.availableFrames.isEmpty() && this.isInputStreamEndedWithPendingAvailableFrames) {
                    ((Listener) Assertions.checkNotNull(this.listener)).onInputStreamProcessed();
                    this.isInputStreamEndedWithPendingAvailableFrames = false;
                }
            }
        }
    }

    public void setErrorListener(Executor executor, GlShaderProgram.ErrorListener errorListener) {
        throw new UnsupportedOperationException();
    }

    public void setInputListener(GlShaderProgram.InputListener inputListener2) {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        this.inputListener = inputListener2;
        for (int i2 = 0; i2 < getInputCapacity(); i2++) {
            inputListener2.onReadyToAcceptInputFrame();
        }
    }

    public void setListener(Listener listener2) {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        this.listener = listener2;
    }

    public void setMatrixTransformations(List<GlMatrixTransformation> list, List<Object> list2) {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        this.matrixTransformations.clear();
        this.matrixTransformations.addAll(list);
        this.rgbMatrices.clear();
        this.rgbMatrices.addAll(list2);
        this.matrixTransformationsChanged = true;
    }

    public void setOutputListener(GlShaderProgram.OutputListener outputListener) {
        throw new UnsupportedOperationException();
    }

    public void setOutputSurfaceInfo(SurfaceInfo surfaceInfo) {
        try {
            this.videoFrameProcessingTaskExecutor.invoke(new c(3, this, surfaceInfo));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.videoFrameProcessorListenerExecutor.execute(new p(0, this, e));
        }
    }

    public void signalEndOfCurrentInputStream() {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        if (this.availableFrames.isEmpty()) {
            ((Listener) Assertions.checkNotNull(this.listener)).onInputStreamProcessed();
            this.isInputStreamEndedWithPendingAvailableFrames = false;
            return;
        }
        Assertions.checkState(!this.renderFramesAutomatically);
        this.isInputStreamEndedWithPendingAvailableFrames = true;
    }
}
