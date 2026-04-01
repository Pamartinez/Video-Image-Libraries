package androidx.media3.effect;

import D7.g;
import F2.C0040v;
import F2.N;
import Fb.h;
import H.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.os.Build;
import android.util.Pair;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.common.util.Util;
import androidx.media3.effect.FinalShaderProgramWrapper;
import androidx.media3.effect.GlTextureProducer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultVideoFrameProcessor implements VideoFrameProcessor {
    private final List<Effect> activeEffects = new ArrayList();
    private final Context context;
    private InputStreamInfo currentInputStreamInfo;
    private final DebugViewProvider debugViewProvider;
    private final EGLDisplay eglDisplay;
    private final FinalShaderProgramWrapper finalShaderProgramWrapper;
    private final ReplayableFrameCacheGlShaderProgram frameCache;
    private final GlObjectsProvider glObjectsProvider;
    /* access modifiers changed from: private */
    public volatile boolean inputStreamEnded;
    private final ConditionVariable inputStreamRegisteredCondition;
    private final InputSwitcher inputSwitcher;
    private final List<GlShaderProgram> intermediateGlShaderPrograms;
    private final VideoFrameProcessor.Listener listener;
    private final Executor listenerExecutor;
    private final Object lock = new Object();
    private volatile FrameInfo nextInputFrameInfo;
    private Runnable onInputSurfaceReadyListener;
    private final ColorInfo outputColorInfo;
    private InputStreamInfo pendingInputStreamInfo;
    private boolean registeredFirstInputStream;
    private volatile boolean released;
    private final boolean renderFramesAutomatically;
    private final boolean shouldReleaseGlObjectsProvider;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements VideoFrameProcessor.Factory {
        /* access modifiers changed from: private */
        public final boolean enableReplayableCache;
        /* access modifiers changed from: private */
        public final ExecutorService executorService;
        /* access modifiers changed from: private */
        public final boolean experimentalAdjustSurfaceTextureTransformationMatrix;
        /* access modifiers changed from: private */
        public final boolean experimentalRepeatInputBitmapWithoutResampling;
        /* access modifiers changed from: private */
        public final GlObjectsProvider glObjectsProvider;
        /* access modifiers changed from: private */
        public final boolean repeatLastRegisteredFrame;
        /* access modifiers changed from: private */
        public final int sdrWorkingColorSpace;
        /* access modifiers changed from: private */
        public final int textureOutputCapacity;
        /* access modifiers changed from: private */
        public final GlTextureProducer.Listener textureOutputListener;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder {
            private boolean enableReplayableCache;
            private ExecutorService executorService;
            private boolean experimentalAdjustSurfaceTextureTransformationMatrix;
            private boolean experimentalRepeatInputBitmapWithoutResampling;
            private GlObjectsProvider glObjectsProvider;
            private boolean requireRegisteringAllInputFrames;
            private int sdrWorkingColorSpace;
            private int textureOutputCapacity;
            private GlTextureProducer.Listener textureOutputListener;

            public Factory build() {
                return new Factory(this.sdrWorkingColorSpace, !this.requireRegisteringAllInputFrames, this.glObjectsProvider, this.executorService, this.textureOutputListener, this.textureOutputCapacity, this.enableReplayableCache, this.experimentalAdjustSurfaceTextureTransformationMatrix, this.experimentalRepeatInputBitmapWithoutResampling);
            }

            public Builder setEnableReplayableCache(boolean z) {
                this.enableReplayableCache = z;
                return this;
            }

            public Builder setExecutorService(ExecutorService executorService2) {
                this.executorService = executorService2;
                return this;
            }

            public Builder setGlObjectsProvider(GlObjectsProvider glObjectsProvider2) {
                this.glObjectsProvider = glObjectsProvider2;
                return this;
            }

            public Builder setTextureOutput(GlTextureProducer.Listener listener, int i2) {
                this.textureOutputListener = listener;
                boolean z = true;
                if (i2 < 1) {
                    z = false;
                }
                Assertions.checkArgument(z);
                this.textureOutputCapacity = i2;
                return this;
            }

            public Builder() {
                this.sdrWorkingColorSpace = 0;
                this.requireRegisteringAllInputFrames = true;
                this.experimentalAdjustSurfaceTextureTransformationMatrix = true;
                this.experimentalRepeatInputBitmapWithoutResampling = true;
            }

            private Builder(Factory factory) {
                this.sdrWorkingColorSpace = factory.sdrWorkingColorSpace;
                this.executorService = factory.executorService;
                this.glObjectsProvider = factory.glObjectsProvider;
                this.textureOutputListener = factory.textureOutputListener;
                this.textureOutputCapacity = factory.textureOutputCapacity;
                this.enableReplayableCache = factory.enableReplayableCache;
                this.requireRegisteringAllInputFrames = !factory.repeatLastRegisteredFrame;
                this.experimentalAdjustSurfaceTextureTransformationMatrix = factory.experimentalAdjustSurfaceTextureTransformationMatrix;
                this.experimentalRepeatInputBitmapWithoutResampling = factory.experimentalRepeatInputBitmapWithoutResampling;
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ DefaultVideoFrameProcessor lambda$create$0(Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, Executor executor, VideoFrameProcessor.Listener listener, GlObjectsProvider glObjectsProvider2, boolean z3) {
            return DefaultVideoFrameProcessor.createOpenGlObjectsAndFrameProcessor(context, debugViewProvider, colorInfo, this.sdrWorkingColorSpace, z, videoFrameProcessingTaskExecutor, executor, listener, glObjectsProvider2, z3, this.enableReplayableCache, this.textureOutputListener, this.textureOutputCapacity, this.repeatLastRegisteredFrame, this.experimentalAdjustSurfaceTextureTransformationMatrix, this.experimentalRepeatInputBitmapWithoutResampling);
        }

        public Builder buildUpon() {
            return new Builder();
        }

        private Factory(int i2, boolean z, GlObjectsProvider glObjectsProvider2, ExecutorService executorService2, GlTextureProducer.Listener listener, int i7, boolean z3, boolean z7, boolean z9) {
            this.sdrWorkingColorSpace = i2;
            this.repeatLastRegisteredFrame = z;
            this.glObjectsProvider = glObjectsProvider2;
            this.executorService = executorService2;
            this.textureOutputListener = listener;
            this.textureOutputCapacity = i7;
            this.enableReplayableCache = z3;
            this.experimentalAdjustSurfaceTextureTransformationMatrix = z7;
            this.experimentalRepeatInputBitmapWithoutResampling = z9;
        }

        public DefaultVideoFrameProcessor create(Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, Executor executor, VideoFrameProcessor.Listener listener) {
            ExecutorService executorService2 = this.executorService;
            if (executorService2 == null) {
                executorService2 = Util.newSingleThreadExecutor("Effect:DefaultVideoFrameProcessor:GlThread");
            }
            ExecutorService executorService3 = executorService2;
            boolean z3 = this.executorService == null;
            Objects.requireNonNull(listener);
            VideoFrameProcessor.Listener listener2 = listener;
            VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor = new VideoFrameProcessingTaskExecutor(executorService3, z3, new g(1, listener2));
            GlObjectsProvider glObjectsProvider2 = this.glObjectsProvider;
            boolean z7 = glObjectsProvider2 == null || this.executorService == null;
            if (glObjectsProvider2 == null) {
                glObjectsProvider2 = new DefaultGlObjectsProvider();
            }
            try {
                return (DefaultVideoFrameProcessor) executorService3.submit(new l(this, context, debugViewProvider, colorInfo, z, videoFrameProcessingTaskExecutor, executor, listener2, glObjectsProvider2, z7)).get();
            } catch (ExecutionException e) {
                throw new VideoFrameProcessingException((Throwable) e);
            } catch (InterruptedException e7) {
                Thread.currentThread().interrupt();
                throw new VideoFrameProcessingException((Throwable) e7);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class InputStreamInfo {
        public final List<Effect> effects;
        public final Format format;
        public final int inputType;
        public final long offsetToAddUs;

        public InputStreamInfo(int i2, Format format2, List<Effect> list, long j2) {
            this.inputType = i2;
            this.format = format2;
            this.effects = list;
            this.offsetToAddUs = j2;
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.effect");
    }

    private DefaultVideoFrameProcessor(Context context2, GlObjectsProvider glObjectsProvider2, boolean z, EGLDisplay eGLDisplay, InputSwitcher inputSwitcher2, final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2, VideoFrameProcessor.Listener listener2, Executor executor, FinalShaderProgramWrapper finalShaderProgramWrapper2, boolean z3, ColorInfo colorInfo, DebugViewProvider debugViewProvider2, ReplayableFrameCacheGlShaderProgram replayableFrameCacheGlShaderProgram) {
        this.context = context2;
        this.glObjectsProvider = glObjectsProvider2;
        this.shouldReleaseGlObjectsProvider = z;
        this.eglDisplay = eGLDisplay;
        this.inputSwitcher = inputSwitcher2;
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
        this.listener = listener2;
        this.listenerExecutor = executor;
        this.renderFramesAutomatically = z3;
        this.outputColorInfo = colorInfo;
        this.frameCache = replayableFrameCacheGlShaderProgram;
        this.debugViewProvider = debugViewProvider2;
        this.finalShaderProgramWrapper = finalShaderProgramWrapper2;
        this.intermediateGlShaderPrograms = new ArrayList();
        ConditionVariable conditionVariable = new ConditionVariable();
        this.inputStreamRegisteredCondition = conditionVariable;
        conditionVariable.open();
        final VideoFrameProcessor.Listener listener3 = listener2;
        final Executor executor2 = executor;
        final ReplayableFrameCacheGlShaderProgram replayableFrameCacheGlShaderProgram2 = replayableFrameCacheGlShaderProgram;
        finalShaderProgramWrapper2.setListener(new FinalShaderProgramWrapper.Listener() {
            public void onFrameRendered(long j2) {
                ReplayableFrameCacheGlShaderProgram replayableFrameCacheGlShaderProgram = replayableFrameCacheGlShaderProgram2;
                if (replayableFrameCacheGlShaderProgram != null) {
                    replayableFrameCacheGlShaderProgram.onFrameRendered(j2);
                }
            }

            public void onInputStreamProcessed() {
                if (DefaultVideoFrameProcessor.this.inputStreamEnded) {
                    Executor executor = executor2;
                    VideoFrameProcessor.Listener listener = listener3;
                    Objects.requireNonNull(listener);
                    executor.execute(new g(24, listener));
                    DebugTraceUtil.logEvent("VideoFrameProcessor", "SignalEnded", Long.MIN_VALUE);
                    return;
                }
                videoFrameProcessingTaskExecutor2.submit(new j(DefaultVideoFrameProcessor.this, 1));
            }
        });
    }

    private Format adjustForPixelWidthHeightRatio(Format format) {
        float f = format.pixelWidthHeightRatio;
        if (f > 1.0f) {
            return format.buildUpon().setWidth((int) (((float) format.width) * format.pixelWidthHeightRatio)).setPixelWidthHeightRatio(1.0f).build();
        }
        if (f < 1.0f) {
            return format.buildUpon().setHeight((int) (((float) format.height) / format.pixelWidthHeightRatio)).setPixelWidthHeightRatio(1.0f).build();
        }
        return format;
    }

    private static void chainShaderProgramsWithListeners(GlObjectsProvider glObjectsProvider2, List<GlShaderProgram> list, FinalShaderProgramWrapper finalShaderProgramWrapper2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2, VideoFrameProcessor.Listener listener2, Executor executor) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.add(finalShaderProgramWrapper2);
        int i2 = 0;
        while (i2 < arrayList.size() - 1) {
            GlShaderProgram glShaderProgram = (GlShaderProgram) arrayList.get(i2);
            i2++;
            GlShaderProgram glShaderProgram2 = (GlShaderProgram) arrayList.get(i2);
            ChainingGlShaderProgramListener chainingGlShaderProgramListener = new ChainingGlShaderProgramListener(glObjectsProvider2, glShaderProgram, glShaderProgram2, videoFrameProcessingTaskExecutor2);
            glShaderProgram.setOutputListener(chainingGlShaderProgramListener);
            Objects.requireNonNull(listener2);
            glShaderProgram.setErrorListener(executor, new h(2, listener2));
            glShaderProgram2.setInputListener(chainingGlShaderProgramListener);
        }
    }

    private static void checkColors(ColorInfo colorInfo, ColorInfo colorInfo2) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9 = false;
        if (ColorInfo.isTransferHdr(colorInfo)) {
            if (colorInfo.colorSpace == 6) {
                z7 = true;
            } else {
                z7 = false;
            }
            Assertions.checkArgument(z7);
        }
        if (ColorInfo.isTransferHdr(colorInfo) || ColorInfo.isTransferHdr(colorInfo2)) {
            try {
                if (GlUtil.getContextMajorVersion() != 3) {
                    throw new VideoFrameProcessingException("OpenGL ES 3.0 context support is required for HDR input or output.");
                }
            } catch (GlUtil.GlException e) {
                throw VideoFrameProcessingException.from(e);
            }
        }
        Assertions.checkArgument(colorInfo.isDataSpaceValid());
        if (colorInfo.colorTransfer != 1) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        Assertions.checkArgument(colorInfo2.isDataSpaceValid());
        if (colorInfo2.colorTransfer != 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument(z3);
        if (ColorInfo.isTransferHdr(colorInfo) != ColorInfo.isTransferHdr(colorInfo2)) {
            if (isSupportedToneMapping(colorInfo, colorInfo2) || isUltraHdr(colorInfo, colorInfo2)) {
                z9 = true;
            }
            Assertions.checkArgument(z9);
        }
    }

    /* JADX WARNING: type inference failed for: r9v13, types: [F2.N, F2.Q] */
    private void configure(InputStreamInfo inputStreamInfo, boolean z) {
        int i2;
        checkColors((ColorInfo) Assertions.checkNotNull(inputStreamInfo.format.colorInfo), this.outputColorInfo);
        if (z || !this.activeEffects.equals(inputStreamInfo.effects)) {
            if (!this.intermediateGlShaderPrograms.isEmpty()) {
                if (this.frameCache == null) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                while (i2 < this.intermediateGlShaderPrograms.size()) {
                    this.intermediateGlShaderPrograms.get(i2).release();
                    i2++;
                }
                this.intermediateGlShaderPrograms.clear();
            }
            ReplayableFrameCacheGlShaderProgram replayableFrameCacheGlShaderProgram = this.frameCache;
            if (replayableFrameCacheGlShaderProgram != null) {
                this.intermediateGlShaderPrograms.add(replayableFrameCacheGlShaderProgram);
            }
            ? n = new N(4);
            n.c(inputStreamInfo.effects);
            DebugViewProvider debugViewProvider2 = this.debugViewProvider;
            if (debugViewProvider2 != DebugViewProvider.NONE) {
                n.a(new DebugViewEffect(debugViewProvider2, this.outputColorInfo));
            }
            this.intermediateGlShaderPrograms.addAll(createGlShaderPrograms(this.context, n.f(), this.outputColorInfo, this.finalShaderProgramWrapper));
            this.inputSwitcher.setDownstreamShaderProgram((GlShaderProgram) C0040v.h(this.intermediateGlShaderPrograms, this.finalShaderProgramWrapper));
            chainShaderProgramsWithListeners(this.glObjectsProvider, this.intermediateGlShaderPrograms, this.finalShaderProgramWrapper, this.videoFrameProcessingTaskExecutor, this.listener, this.listenerExecutor);
            this.activeEffects.clear();
            this.activeEffects.addAll(inputStreamInfo.effects);
        }
        this.inputSwitcher.switchToInput(inputStreamInfo.inputType, new FrameInfo(inputStreamInfo.format, inputStreamInfo.offsetToAddUs));
        this.inputStreamRegisteredCondition.open();
        synchronized (this.lock) {
            try {
                Runnable runnable = this.onInputSurfaceReadyListener;
                if (runnable != null) {
                    runnable.run();
                    this.onInputSurfaceReadyListener = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.listenerExecutor.execute(new k(this, inputStreamInfo, 0));
        InputStreamInfo inputStreamInfo2 = this.currentInputStreamInfo;
        if (inputStreamInfo2 == null || inputStreamInfo.format.frameRate != inputStreamInfo2.format.frameRate) {
            this.listenerExecutor.execute(new k(this, inputStreamInfo, 1));
        }
        this.currentInputStreamInfo = inputStreamInfo;
    }

    /* access modifiers changed from: private */
    public void configurePendingInputStream() {
        InputStreamInfo inputStreamInfo;
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        synchronized (this.lock) {
            try {
                inputStreamInfo = this.pendingInputStreamInfo;
                if (inputStreamInfo != null) {
                    this.pendingInputStreamInfo = null;
                } else {
                    inputStreamInfo = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (inputStreamInfo != null) {
            configure(inputStreamInfo, false);
        }
    }

    private static Pair<EGLContext, EGLSurface> createFocusedEglContext(GlObjectsProvider glObjectsProvider2, EGLDisplay eGLDisplay, int i2, int[] iArr) {
        EGLContext createEglContext = glObjectsProvider2.createEglContext(eGLDisplay, i2, iArr);
        return Pair.create(createEglContext, glObjectsProvider2.createFocusedPlaceholderEglSurface(createEglContext, eGLDisplay));
    }

    private static Pair<EGLContext, EGLSurface> createFocusedEglContextWithFallback(GlObjectsProvider glObjectsProvider2, EGLDisplay eGLDisplay, int[] iArr) {
        try {
            return createFocusedEglContext(glObjectsProvider2, eGLDisplay, 3, iArr);
        } catch (GlUtil.GlException unused) {
            return createFocusedEglContext(glObjectsProvider2, eGLDisplay, 2, iArr);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: F2.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: F2.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: F2.Q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: F2.Q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: F2.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: F2.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: F2.Q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: F2.Q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: F2.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: F2.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: F2.Q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: F2.Q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: F2.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: F2.N} */
    /* JADX WARNING: type inference failed for: r0v0, types: [F2.N, F2.Q] */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0046, code lost:
        if (r8.isEmpty() == false) goto L_0x0048;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static F2.U createGlShaderPrograms(android.content.Context r10, java.util.List<androidx.media3.common.Effect> r11, androidx.media3.common.ColorInfo r12, androidx.media3.effect.FinalShaderProgramWrapper r13) {
        /*
            F2.Q r0 = new F2.Q
            r1 = 4
            r0.<init>(r1)
            F2.Q r2 = new F2.Q
            r2.<init>(r1)
            F2.Q r3 = new F2.Q
            r3.<init>(r1)
            r4 = 0
        L_0x0011:
            int r5 = r11.size()
            if (r4 >= r5) goto L_0x0063
            java.lang.Object r5 = r11.get(r4)
            androidx.media3.common.Effect r5 = (androidx.media3.common.Effect) r5
            boolean r6 = r5 instanceof androidx.media3.effect.GlEffect
            java.lang.String r7 = "DefaultVideoFrameProcessor only supports GlEffects"
            androidx.media3.common.util.Assertions.checkArgument(r6, r7)
            androidx.media3.effect.GlEffect r5 = (androidx.media3.effect.GlEffect) r5
            boolean r6 = r5 instanceof androidx.media3.effect.GlMatrixTransformation
            if (r6 == 0) goto L_0x0030
            androidx.media3.effect.GlMatrixTransformation r5 = (androidx.media3.effect.GlMatrixTransformation) r5
            r2.a(r5)
            goto L_0x0060
        L_0x0030:
            boolean r6 = androidx.media3.common.ColorInfo.isTransferHdr(r12)
            F2.y0 r7 = r2.f()
            F2.y0 r8 = r3.f()
            boolean r9 = r7.isEmpty()
            if (r9 == 0) goto L_0x0048
            boolean r9 = r8.isEmpty()
            if (r9 != 0) goto L_0x0059
        L_0x0048:
            androidx.media3.effect.DefaultShaderProgram r2 = androidx.media3.effect.DefaultShaderProgram.create(r10, r7, r8, r6)
            r0.a(r2)
            F2.Q r2 = new F2.Q
            r2.<init>(r1)
            F2.Q r3 = new F2.Q
            r3.<init>(r1)
        L_0x0059:
            androidx.media3.effect.GlShaderProgram r5 = r5.toGlShaderProgram(r10, r6)
            r0.a(r5)
        L_0x0060:
            int r4 = r4 + 1
            goto L_0x0011
        L_0x0063:
            F2.y0 r10 = r2.f()
            F2.y0 r11 = r3.f()
            r13.setMatrixTransformations(r10, r11)
            F2.y0 r10 = r0.f()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.DefaultVideoFrameProcessor.createGlShaderPrograms(android.content.Context, java.util.List, androidx.media3.common.ColorInfo, androidx.media3.effect.FinalShaderProgramWrapper):F2.U");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.effect.DefaultVideoFrameProcessor createOpenGlObjectsAndFrameProcessor(android.content.Context r25, androidx.media3.common.DebugViewProvider r26, androidx.media3.common.ColorInfo r27, int r28, boolean r29, androidx.media3.effect.VideoFrameProcessingTaskExecutor r30, java.util.concurrent.Executor r31, androidx.media3.common.VideoFrameProcessor.Listener r32, androidx.media3.common.GlObjectsProvider r33, boolean r34, boolean r35, androidx.media3.effect.GlTextureProducer.Listener r36, int r37, boolean r38, boolean r39, boolean r40) {
        /*
            android.opengl.EGLDisplay r2 = androidx.media3.common.util.GlUtil.getDefaultEglDisplay()
            boolean r13 = androidx.media3.common.ColorInfo.isTransferHdr(r27)
            if (r13 == 0) goto L_0x000f
            int[] r0 = androidx.media3.common.util.GlUtil.EGL_CONFIG_ATTRIBUTES_RGBA_1010102
        L_0x000c:
            r1 = r33
            goto L_0x0012
        L_0x000f:
            int[] r0 = androidx.media3.common.util.GlUtil.EGL_CONFIG_ATTRIBUTES_RGBA_8888
            goto L_0x000c
        L_0x0012:
            android.util.Pair r0 = createFocusedEglContextWithFallback(r1, r2, r0)
            androidx.media3.common.ColorInfo$Builder r3 = r27.buildUpon()
            r4 = 1
            androidx.media3.common.ColorInfo$Builder r3 = r3.setColorTransfer(r4)
            r4 = 0
            androidx.media3.common.ColorInfo$Builder r3 = r3.setHdrStaticInfo(r4)
            androidx.media3.common.ColorInfo r3 = r3.build()
            if (r13 == 0) goto L_0x002d
            r11 = r28
            goto L_0x0032
        L_0x002d:
            r5 = 2
            r11 = r28
            if (r11 != r5) goto L_0x0035
        L_0x0032:
            r16 = r3
            goto L_0x0037
        L_0x0035:
            r16 = r27
        L_0x0037:
            androidx.media3.effect.InputSwitcher r5 = new androidx.media3.effect.InputSwitcher
            java.util.Objects.requireNonNull(r32)
            Fb.h r3 = new Fb.h
            r6 = 2
            r7 = r32
            r3.<init>(r6, r7)
            r15 = r25
            r18 = r30
            r19 = r31
            r22 = r38
            r23 = r39
            r24 = r40
            r17 = r1
            r20 = r3
            r14 = r5
            r21 = r11
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            androidx.media3.effect.FinalShaderProgramWrapper r9 = new androidx.media3.effect.FinalShaderProgramWrapper
            java.lang.Object r1 = r0.first
            r3 = r1
            android.opengl.EGLContext r3 = (android.opengl.EGLContext) r3
            java.lang.Object r0 = r0.second
            android.opengl.EGLSurface r0 = (android.opengl.EGLSurface) r0
            r1 = r25
            r5 = r27
            r11 = r28
            r12 = r29
            r6 = r30
            r10 = r37
            r15 = r4
            r8 = r7
            r7 = r31
            r4 = r0
            r0 = r9
            r9 = r36
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            androidx.media3.effect.DefaultVideoFrameProcessor r1 = new androidx.media3.effect.DefaultVideoFrameProcessor
            if (r35 == 0) goto L_0x009e
            androidx.media3.effect.ReplayableFrameCacheGlShaderProgram r4 = new androidx.media3.effect.ReplayableFrameCacheGlShaderProgram
            r3 = r25
            r4.<init>(r3, r13)
            r9 = r0
            r0 = r1
            r1 = r3
            r13 = r4
            r12 = r26
            r11 = r27
            r10 = r29
            r6 = r30
            r8 = r31
            r7 = r32
            r5 = r14
            r3 = r34
            r4 = r2
        L_0x009b:
            r2 = r33
            goto L_0x00b4
        L_0x009e:
            r9 = r0
            r0 = r1
            r13 = r15
            r1 = r25
            r12 = r26
            r11 = r27
            r10 = r29
            r6 = r30
            r8 = r31
            r7 = r32
            r3 = r34
            r4 = r2
            r5 = r14
            goto L_0x009b
        L_0x00b4:
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.DefaultVideoFrameProcessor.createOpenGlObjectsAndFrameProcessor(android.content.Context, androidx.media3.common.DebugViewProvider, androidx.media3.common.ColorInfo, int, boolean, androidx.media3.effect.VideoFrameProcessingTaskExecutor, java.util.concurrent.Executor, androidx.media3.common.VideoFrameProcessor$Listener, androidx.media3.common.GlObjectsProvider, boolean, boolean, androidx.media3.effect.GlTextureProducer$Listener, int, boolean, boolean, boolean):androidx.media3.effect.DefaultVideoFrameProcessor");
    }

    private static String getInputTypeString(int i2) {
        if (i2 == 1) {
            return "Surface";
        }
        if (i2 == 2) {
            return "Bitmap";
        }
        if (i2 == 3) {
            return "Texture ID";
        }
        if (i2 == 4) {
            return "Surface with automatic frame registration";
        }
        throw new IllegalArgumentException(String.valueOf(i2));
    }

    private static boolean isSupportedToneMapping(ColorInfo colorInfo, ColorInfo colorInfo2) {
        if (colorInfo.colorSpace != 6 || colorInfo2.colorSpace == 6 || !ColorInfo.isTransferHdr(colorInfo)) {
            return false;
        }
        int i2 = colorInfo2.colorTransfer;
        if (i2 == 10 || i2 == 3) {
            return true;
        }
        return false;
    }

    private static boolean isUltraHdr(ColorInfo colorInfo, ColorInfo colorInfo2) {
        if (!colorInfo.equals(ColorInfo.SRGB_BT709_FULL) || colorInfo2.colorSpace != 6 || !ColorInfo.isTransferHdr(colorInfo2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$configure$5(InputStreamInfo inputStreamInfo) {
        this.listener.onInputStreamRegistered(inputStreamInfo.inputType, inputStreamInfo.format, inputStreamInfo.effects);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$configure$6(InputStreamInfo inputStreamInfo) {
        this.listener.onOutputFrameRateChanged(inputStreamInfo.format.frameRate);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerInputStream$1(InterruptedException interruptedException) {
        this.listener.onError(VideoFrameProcessingException.from(interruptedException));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerInputStream$2(InputStreamInfo inputStreamInfo) {
        configure(inputStreamInfo, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$renderOutputFrame$3(long j2) {
        this.finalShaderProgramWrapper.renderOutputFrame(this.glObjectsProvider, j2);
    }

    /* access modifiers changed from: private */
    public void releaseGlObjects() {
        try {
            this.inputSwitcher.release();
            for (int i2 = 0; i2 < this.intermediateGlShaderPrograms.size(); i2++) {
                this.intermediateGlShaderPrograms.get(i2).release();
            }
            this.finalShaderProgramWrapper.release();
        } catch (Exception e) {
            Log.e("DefaultFrameProcessor", "Error releasing shader program", e);
        } catch (Throwable th) {
            if (this.shouldReleaseGlObjectsProvider) {
                try {
                    this.glObjectsProvider.release(this.eglDisplay);
                } catch (GlUtil.GlException e7) {
                    Log.e("DefaultFrameProcessor", "Error releasing GL objects", e7);
                }
            }
            throw th;
        }
        if (this.shouldReleaseGlObjectsProvider) {
            try {
                this.glObjectsProvider.release(this.eglDisplay);
            } catch (GlUtil.GlException e8) {
                Log.e("DefaultFrameProcessor", "Error releasing GL objects", e8);
            }
        }
    }

    public Surface getInputSurface() {
        return this.inputSwitcher.getInputSurface();
    }

    public int getPendingInputFrameCount() {
        if (this.inputSwitcher.hasActiveInput()) {
            return this.inputSwitcher.activeTextureManager().getPendingFrameCount();
        }
        return 0;
    }

    public boolean queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
        Assertions.checkState(!this.inputStreamEnded);
        boolean z = false;
        if (!this.inputStreamRegisteredCondition.isOpen() || this.released) {
            return false;
        }
        if (ColorInfo.isTransferHdr(this.outputColorInfo)) {
            if (Build.VERSION.SDK_INT >= 34 && bitmap.hasGainmap()) {
                z = true;
            }
            Assertions.checkArgument(z, "VideoFrameProcessor configured for HDR output, but either received SDR input, or is on an API level that doesn't support gainmaps. SDR to HDR tonemapping is not supported.");
        }
        this.inputSwitcher.activeTextureManager().queueInputBitmap(bitmap, (FrameInfo) Assertions.checkNotNull(this.nextInputFrameInfo), timestampIterator);
        return true;
    }

    public boolean queueInputTexture(int i2, long j2) {
        Assertions.checkState(!this.inputStreamEnded);
        if (!this.inputStreamRegisteredCondition.isOpen() || this.released) {
            return false;
        }
        this.inputSwitcher.activeTextureManager().queueInputTexture(i2, j2);
        return true;
    }

    public boolean registerInputFrame() {
        Assertions.checkState(!this.inputStreamEnded);
        Assertions.checkStateNotNull(this.nextInputFrameInfo, "registerInputStream must be called before registering input frames");
        if (!this.inputStreamRegisteredCondition.isOpen() || this.released) {
            return false;
        }
        this.inputSwitcher.activeTextureManager().registerInputFrame(this.nextInputFrameInfo);
        return true;
    }

    public void registerInputStream(int i2, Format format, List<Effect> list, long j2) {
        if (!this.released) {
            DebugTraceUtil.logEvent("VideoFrameProcessor", "RegisterNewInputStream", j2, "InputType %s - %dx%d", getInputTypeString(i2), Integer.valueOf(format.width), Integer.valueOf(format.height));
            long j3 = j2;
            this.nextInputFrameInfo = new FrameInfo(adjustForPixelWidthHeightRatio(format), j2);
            try {
                this.inputStreamRegisteredCondition.block();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                this.listenerExecutor.execute(new a(1, this, e));
            }
            synchronized (this.lock) {
                try {
                    InputStreamInfo inputStreamInfo = new InputStreamInfo(i2, format, list, j3);
                    if (!this.registeredFirstInputStream) {
                        this.registeredFirstInputStream = true;
                        this.inputStreamRegisteredCondition.close();
                        this.videoFrameProcessingTaskExecutor.submit(new c(1, this, inputStreamInfo));
                    } else {
                        this.pendingInputStreamInfo = inputStreamInfo;
                        this.inputStreamRegisteredCondition.close();
                        this.inputSwitcher.signalEndOfCurrentInputStream();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void release() {
        this.released = true;
        try {
            this.videoFrameProcessingTaskExecutor.release(new j(this, 0));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

    public void renderOutputFrame(long j2) {
        Assertions.checkState(!this.renderFramesAutomatically, "Calling this method is not allowed when renderFramesAutomatically is enabled");
        this.videoFrameProcessingTaskExecutor.submitWithHighPriority(new h(this, j2, 1));
    }

    public void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
        this.inputSwitcher.setOnInputFrameProcessedListener(onInputFrameProcessedListener);
    }

    public void setOutputSurfaceInfo(SurfaceInfo surfaceInfo) {
        this.finalShaderProgramWrapper.setOutputSurfaceInfo(surfaceInfo);
    }

    public void signalEndOfInput() {
        DebugTraceUtil.logEvent("VideoFrameProcessor", "ReceiveEndOfAllInput", Long.MIN_VALUE);
        Assertions.checkState(!this.inputStreamEnded);
        this.inputStreamEnded = true;
        if (!this.released) {
            this.inputSwitcher.signalEndOfCurrentInputStream();
        }
    }
}
