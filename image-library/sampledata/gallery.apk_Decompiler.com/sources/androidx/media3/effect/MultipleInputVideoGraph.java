package androidx.media3.effect;

import D7.g;
import F2.G;
import F2.U;
import F2.y0;
import H.a;
import H.c;
import H.d;
import H.e;
import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.util.SparseArray;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoCompositorSettings;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoGraph;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.common.util.Util;
import androidx.media3.effect.DefaultVideoFrameProcessor;
import androidx.media3.effect.VideoCompositor;
import com.google.common.util.concurrent.r;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MultipleInputVideoGraph implements VideoGraph {
    private List<Effect> compositionEffects;
    private VideoFrameProcessor compositionVideoFrameProcessor;
    private boolean compositorEnded;
    private Size compositorOutputSize;
    private final SparseArray<CompositorOutputTextureRelease> compositorOutputTextureReleases;
    private final Queue<TimedGlTextureInfo> compositorOutputTextures;
    private final Context context;
    private final DebugViewProvider debugViewProvider;
    private final GlObjectsProvider glObjectsProvider;
    /* access modifiers changed from: private */
    public volatile boolean hasProducedFrameWithTimestampZero;
    /* access modifiers changed from: private */
    public long lastRenderedPresentationTimeUs;
    /* access modifiers changed from: private */
    public final VideoGraph.Listener listener;
    /* access modifiers changed from: private */
    public final Executor listenerExecutor;
    private final ColorInfo outputColorInfo;
    private final SparseArray<VideoFrameProcessor> preProcessors;
    private boolean released;
    private final boolean renderFramesAutomatically;
    private final ExecutorService sharedExecutorService;
    private VideoCompositor videoCompositor;
    private VideoCompositorSettings videoCompositorSettings;
    private final DefaultVideoFrameProcessor.Factory videoFrameProcessorFactory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CompositorOutputTextureRelease {
        private final long presentationTimeUs;
        private final GlTextureProducer textureProducer;

        public CompositorOutputTextureRelease(GlTextureProducer glTextureProducer, long j2) {
            this.textureProducer = glTextureProducer;
            this.presentationTimeUs = j2;
        }

        public void release() {
            this.textureProducer.releaseOutputTexture(this.presentationTimeUs);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements VideoGraph.Factory {
        private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

        public Factory(VideoFrameProcessor.Factory factory) {
            this.videoFrameProcessorFactory = factory;
        }

        public MultipleInputVideoGraph create(Context context, ColorInfo colorInfo, DebugViewProvider debugViewProvider, VideoGraph.Listener listener, Executor executor, long j2, boolean z) {
            DebugViewProvider debugViewProvider2 = debugViewProvider;
            ColorInfo colorInfo2 = colorInfo;
            Executor executor2 = executor;
            VideoGraph.Listener listener2 = listener;
            return new MultipleInputVideoGraph(context, this.videoFrameProcessorFactory, colorInfo2, debugViewProvider2, listener2, executor2, z);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SingleContextGlObjectsProvider implements GlObjectsProvider {
        private final GlObjectsProvider glObjectsProvider = new DefaultGlObjectsProvider();
        private EGLContext singleEglContext;

        public GlTextureInfo createBuffersForTexture(int i2, int i7, int i8) {
            return this.glObjectsProvider.createBuffersForTexture(i2, i7, i8);
        }

        public EGLContext createEglContext(EGLDisplay eGLDisplay, int i2, int[] iArr) {
            if (this.singleEglContext == null) {
                this.singleEglContext = this.glObjectsProvider.createEglContext(eGLDisplay, i2, iArr);
            }
            return this.singleEglContext;
        }

        public EGLSurface createEglSurface(EGLDisplay eGLDisplay, Object obj, int i2, boolean z) {
            return this.glObjectsProvider.createEglSurface(eGLDisplay, obj, i2, z);
        }

        public EGLSurface createFocusedPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay) {
            return this.glObjectsProvider.createFocusedPlaceholderEglSurface(eGLContext, eGLDisplay);
        }

        public void release(EGLDisplay eGLDisplay) {
            EGLContext eGLContext = this.singleEglContext;
            if (eGLContext != null) {
                GlUtil.destroyEglContext(eGLDisplay, eGLContext);
            }
        }
    }

    private VideoFrameProcessor getProcessor(int i2) {
        Assertions.checkState(Util.contains(this.preProcessors, i2));
        return this.preProcessors.get(i2);
    }

    /* access modifiers changed from: private */
    public void handleVideoFrameProcessingException(Exception exc) {
        this.listenerExecutor.execute(new a(2, this, exc));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleVideoFrameProcessingException$2(Exception exc) {
        VideoFrameProcessingException videoFrameProcessingException;
        VideoGraph.Listener listener2 = this.listener;
        if (exc instanceof VideoFrameProcessingException) {
            videoFrameProcessingException = (VideoFrameProcessingException) exc;
        } else {
            videoFrameProcessingException = VideoFrameProcessingException.from(exc);
        }
        listener2.onError(videoFrameProcessingException);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerInput$0(int i2, GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j2, long j3) {
        queuePreProcessingOutputToCompositor(i2, glTextureProducer, glTextureInfo, j2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$1() {
        try {
            this.glObjectsProvider.release(GlUtil.getDefaultEglDisplay());
        } catch (Exception e) {
            Log.e("MultiInputVG", "Error releasing GlObjectsProvider", e);
        }
    }

    /* access modifiers changed from: private */
    public void onCompositionVideoFrameProcessorInputFrameProcessed(int i2, long j2) {
        Assertions.checkState(Util.contains(this.compositorOutputTextureReleases, i2));
        this.compositorOutputTextureReleases.get(i2).release();
        this.compositorOutputTextureReleases.remove(i2);
        queueCompositionOutputInternal();
    }

    /* access modifiers changed from: private */
    public void onPreProcessingVideoFrameProcessorEnded(int i2) {
        ((VideoCompositor) Assertions.checkNotNull(this.videoCompositor)).signalEndOfInputSource(i2);
    }

    /* access modifiers changed from: private */
    public void onVideoCompositorEnded() {
        this.compositorEnded = true;
        if (this.compositorOutputTextures.isEmpty()) {
            ((VideoFrameProcessor) Assertions.checkNotNull(this.compositionVideoFrameProcessor)).signalEndOfInput();
        } else {
            queueCompositionOutputInternal();
        }
    }

    /* access modifiers changed from: private */
    public void processCompositorOutputTexture(GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j2, long j3) {
        Assertions.checkState(!this.compositorEnded);
        DebugTraceUtil.logEvent("Compositor", "OutputTextureRendered", j2);
        this.compositorOutputTextures.add(new TimedGlTextureInfo(glTextureInfo, j2));
        this.compositorOutputTextureReleases.put(glTextureInfo.texId, new CompositorOutputTextureRelease(glTextureProducer, j2));
        queueCompositionOutputInternal();
    }

    /* access modifiers changed from: private */
    public void queueCompositionOutputInternal() {
        TimedGlTextureInfo peek = this.compositorOutputTextures.peek();
        if (peek != null) {
            VideoFrameProcessor videoFrameProcessor = (VideoFrameProcessor) Assertions.checkStateNotNull(this.compositionVideoFrameProcessor);
            GlTextureInfo glTextureInfo = peek.glTextureInfo;
            int i2 = glTextureInfo.width;
            int i7 = glTextureInfo.height;
            if (!(i2 == this.compositorOutputSize.getWidth() && i7 == this.compositorOutputSize.getHeight())) {
                videoFrameProcessor.registerInputStream(3, new Format.Builder().setColorInfo(this.outputColorInfo).setWidth(i2).setHeight(i7).build(), this.compositionEffects, 0);
                this.compositorOutputSize = new Size(i2, i7);
            }
            if (videoFrameProcessor.queueInputTexture(peek.glTextureInfo.texId, peek.presentationTimeUs)) {
                this.compositorOutputTextures.remove();
                if (this.compositorEnded && this.compositorOutputTextures.isEmpty()) {
                    videoFrameProcessor.signalEndOfInput();
                }
            }
        }
    }

    private void queuePreProcessingOutputToCompositor(int i2, GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j2) {
        DebugTraceUtil.logEvent("VideoFrameProcessor", "OutputTextureRendered", j2);
        ((VideoCompositor) Assertions.checkNotNull(this.videoCompositor)).queueInputTexture(i2, glTextureProducer, glTextureInfo, this.outputColorInfo, j2);
    }

    public Surface getInputSurface(int i2) {
        return getProcessor(i2).getInputSurface();
    }

    public int getPendingInputFrameCount(int i2) {
        return getProcessor(i2).getPendingInputFrameCount();
    }

    public boolean hasProducedFrameWithTimestampZero() {
        return this.hasProducedFrameWithTimestampZero;
    }

    public void initialize() {
        boolean z;
        if (this.preProcessors.size() == 0 && this.videoCompositor == null && this.compositionVideoFrameProcessor == null && !this.released) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        DefaultVideoFrameProcessor create = this.videoFrameProcessorFactory.create(this.context, this.debugViewProvider, this.outputColorInfo, this.renderFramesAutomatically, (Executor) r.INSTANCE, (VideoFrameProcessor.Listener) new VideoFrameProcessor.Listener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onEnded$3() {
                MultipleInputVideoGraph.this.listener.onEnded(MultipleInputVideoGraph.this.lastRenderedPresentationTimeUs);
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onOutputFrameAvailableForRendering$2(long j2, boolean z) {
                MultipleInputVideoGraph.this.listener.onOutputFrameAvailableForRendering(j2, z);
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onOutputFrameRateChanged$1(float f) {
                MultipleInputVideoGraph.this.listener.onOutputFrameRateChanged(f);
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onOutputSizeChanged$0(int i2, int i7) {
                MultipleInputVideoGraph.this.listener.onOutputSizeChanged(i2, i7);
            }

            public void onEnded() {
                MultipleInputVideoGraph.this.listenerExecutor.execute(new o(1, this));
            }

            public void onError(VideoFrameProcessingException videoFrameProcessingException) {
                MultipleInputVideoGraph.this.handleVideoFrameProcessingException(videoFrameProcessingException);
            }

            public void onInputStreamRegistered(int i2, Format format, List<Effect> list) {
                MultipleInputVideoGraph.this.queueCompositionOutputInternal();
            }

            public void onOutputFrameAvailableForRendering(long j2, boolean z) {
                if (j2 == 0) {
                    boolean unused = MultipleInputVideoGraph.this.hasProducedFrameWithTimestampZero = true;
                }
                long unused2 = MultipleInputVideoGraph.this.lastRenderedPresentationTimeUs = j2;
                MultipleInputVideoGraph.this.listenerExecutor.execute(new v(this, j2, z, 0));
            }

            public void onOutputFrameRateChanged(float f) {
                MultipleInputVideoGraph.this.listenerExecutor.execute(new t(this, f, 0));
            }

            public void onOutputSizeChanged(int i2, int i7) {
                MultipleInputVideoGraph.this.listenerExecutor.execute(new u(this, i2, i7, 0));
            }
        });
        this.compositionVideoFrameProcessor = create;
        create.setOnInputFrameProcessedListener(new c(this));
        DefaultVideoCompositor defaultVideoCompositor = new DefaultVideoCompositor(this.context, this.glObjectsProvider, this.sharedExecutorService, new VideoCompositor.Listener() {
            public void onEnded() {
                MultipleInputVideoGraph.this.onVideoCompositorEnded();
            }

            public void onError(VideoFrameProcessingException videoFrameProcessingException) {
                MultipleInputVideoGraph.this.handleVideoFrameProcessingException(videoFrameProcessingException);
            }
        }, new d(this), 1);
        this.videoCompositor = defaultVideoCompositor;
        defaultVideoCompositor.setVideoCompositorSettings(this.videoCompositorSettings);
    }

    public boolean queueInputBitmap(int i2, Bitmap bitmap, TimestampIterator timestampIterator) {
        return getProcessor(i2).queueInputBitmap(bitmap, timestampIterator);
    }

    public void registerInput(final int i2) {
        Assertions.checkState(!Util.contains(this.preProcessors, i2));
        ((VideoCompositor) Assertions.checkNotNull(this.videoCompositor)).registerInputSource(i2);
        this.preProcessors.put(i2, this.videoFrameProcessorFactory.buildUpon().setTextureOutput(new e(this, i2), 2).build().create(this.context, DebugViewProvider.NONE, this.outputColorInfo, true, this.listenerExecutor, (VideoFrameProcessor.Listener) new VideoFrameProcessor.Listener() {
            public void onEnded() {
                MultipleInputVideoGraph.this.onPreProcessingVideoFrameProcessorEnded(i2);
            }

            public void onError(VideoFrameProcessingException videoFrameProcessingException) {
                MultipleInputVideoGraph.this.handleVideoFrameProcessingException(videoFrameProcessingException);
            }
        }));
    }

    public boolean registerInputFrame(int i2) {
        return getProcessor(i2).registerInputFrame();
    }

    public void registerInputStream(int i2, int i7, Format format, List<Effect> list, long j2) {
        getProcessor(i2).registerInputStream(i7, format, list, j2);
    }

    public void release() {
        if (!this.released) {
            for (int i2 = 0; i2 < this.preProcessors.size(); i2++) {
                SparseArray<VideoFrameProcessor> sparseArray = this.preProcessors;
                sparseArray.get(sparseArray.keyAt(i2)).release();
            }
            VideoCompositor videoCompositor2 = this.videoCompositor;
            if (videoCompositor2 != null) {
                videoCompositor2.release();
                this.videoCompositor = null;
            }
            VideoFrameProcessor videoFrameProcessor = this.compositionVideoFrameProcessor;
            if (videoFrameProcessor != null) {
                videoFrameProcessor.release();
                this.compositionVideoFrameProcessor = null;
            }
            this.sharedExecutorService.submit(new g(25, this));
            this.sharedExecutorService.shutdown();
            try {
                this.sharedExecutorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                Log.e("MultiInputVG", "Thread interrupted while waiting for executor service termination");
            }
            this.released = true;
        }
    }

    public void renderOutputFrame(long j2) {
        ((VideoFrameProcessor) Assertions.checkNotNull(this.compositionVideoFrameProcessor)).renderOutputFrame(j2);
    }

    public void setCompositionEffects(List<Effect> list) {
        this.compositionEffects = list;
    }

    public void setCompositorSettings(VideoCompositorSettings videoCompositorSettings2) {
        this.videoCompositorSettings = videoCompositorSettings2;
        VideoCompositor videoCompositor2 = this.videoCompositor;
        if (videoCompositor2 != null) {
            videoCompositor2.setVideoCompositorSettings(videoCompositorSettings2);
        }
    }

    public void setOutputSurfaceInfo(SurfaceInfo surfaceInfo) {
        ((VideoFrameProcessor) Assertions.checkNotNull(this.compositionVideoFrameProcessor)).setOutputSurfaceInfo(surfaceInfo);
    }

    public void signalEndOfInput(int i2) {
        getProcessor(i2).signalEndOfInput();
    }

    private MultipleInputVideoGraph(Context context2, VideoFrameProcessor.Factory factory, ColorInfo colorInfo, DebugViewProvider debugViewProvider2, VideoGraph.Listener listener2, Executor executor, boolean z) {
        Assertions.checkArgument(factory instanceof DefaultVideoFrameProcessor.Factory);
        this.context = context2;
        this.outputColorInfo = colorInfo;
        this.debugViewProvider = debugViewProvider2;
        this.listener = listener2;
        this.listenerExecutor = executor;
        this.renderFramesAutomatically = z;
        this.lastRenderedPresentationTimeUs = -9223372036854775807L;
        this.preProcessors = new SparseArray<>();
        ScheduledExecutorService newSingleThreadScheduledExecutor = Util.newSingleThreadScheduledExecutor("Effect:MultipleInputVideoGraph:Thread");
        this.sharedExecutorService = newSingleThreadScheduledExecutor;
        SingleContextGlObjectsProvider singleContextGlObjectsProvider = new SingleContextGlObjectsProvider();
        this.glObjectsProvider = singleContextGlObjectsProvider;
        this.videoFrameProcessorFactory = ((DefaultVideoFrameProcessor.Factory) factory).buildUpon().setGlObjectsProvider(singleContextGlObjectsProvider).setExecutorService(newSingleThreadScheduledExecutor).build();
        this.compositorOutputTextures = new ArrayDeque();
        this.compositorOutputTextureReleases = new SparseArray<>();
        this.compositorOutputSize = Size.UNKNOWN;
        G g = U.e;
        this.compositionEffects = y0.f278h;
        this.videoCompositorSettings = VideoCompositorSettings.DEFAULT;
    }
}
