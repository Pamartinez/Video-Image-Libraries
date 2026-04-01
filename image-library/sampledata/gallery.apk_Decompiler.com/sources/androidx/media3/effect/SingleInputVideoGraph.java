package androidx.media3.effect;

import F2.G;
import F2.N;
import F2.U;
import F2.y0;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoCompositorSettings;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoGraph;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.TimestampIterator;
import com.google.common.util.concurrent.r;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleInputVideoGraph implements VideoGraph {
    private U compositionEffects = y0.f278h;
    private final Context context;
    private final DebugViewProvider debugViewProvider;
    /* access modifiers changed from: private */
    public volatile boolean hasProducedFrameWithTimestampZero;
    private int inputIndex;
    /* access modifiers changed from: private */
    public final VideoGraph.Listener listener;
    /* access modifiers changed from: private */
    public final Executor listenerExecutor;
    private final ColorInfo outputColorInfo;
    private SurfaceInfo outputSurfaceInfo;
    private boolean released;
    private final boolean renderFramesAutomatically;
    private VideoFrameProcessor videoFrameProcessor;
    private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements VideoGraph.Factory {
        private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

        public Factory(VideoFrameProcessor.Factory factory) {
            this.videoFrameProcessorFactory = factory;
        }

        public SingleInputVideoGraph create(Context context, ColorInfo colorInfo, DebugViewProvider debugViewProvider, VideoGraph.Listener listener, Executor executor, long j2, boolean z) {
            DebugViewProvider debugViewProvider2 = debugViewProvider;
            ColorInfo colorInfo2 = colorInfo;
            return new SingleInputVideoGraph(context, this.videoFrameProcessorFactory, colorInfo2, listener, debugViewProvider2, executor, z);
        }
    }

    public SingleInputVideoGraph(Context context2, VideoFrameProcessor.Factory factory, ColorInfo colorInfo, VideoGraph.Listener listener2, DebugViewProvider debugViewProvider2, Executor executor, boolean z) {
        this.context = context2;
        this.videoFrameProcessorFactory = factory;
        this.outputColorInfo = colorInfo;
        this.listener = listener2;
        this.debugViewProvider = debugViewProvider2;
        this.listenerExecutor = executor;
        G g = U.e;
        this.renderFramesAutomatically = z;
        this.inputIndex = -1;
    }

    public Surface getInputSurface(int i2) {
        Assertions.checkStateNotNull(this.videoFrameProcessor);
        return this.videoFrameProcessor.getInputSurface();
    }

    public int getPendingInputFrameCount(int i2) {
        Assertions.checkStateNotNull(this.videoFrameProcessor);
        return this.videoFrameProcessor.getPendingInputFrameCount();
    }

    public boolean hasProducedFrameWithTimestampZero() {
        return this.hasProducedFrameWithTimestampZero;
    }

    public boolean queueInputBitmap(int i2, Bitmap bitmap, TimestampIterator timestampIterator) {
        Assertions.checkStateNotNull(this.videoFrameProcessor);
        return this.videoFrameProcessor.queueInputBitmap(bitmap, timestampIterator);
    }

    public void registerInput(int i2) {
        boolean z;
        boolean z3 = false;
        if (this.videoFrameProcessor != null || this.released) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkStateNotNull(Boolean.valueOf(z));
        if (this.inputIndex == -1) {
            z3 = true;
        }
        Assertions.checkState(z3, "This VideoGraph supports only one input.");
        this.inputIndex = i2;
        VideoFrameProcessor create = this.videoFrameProcessorFactory.create(this.context, this.debugViewProvider, this.outputColorInfo, this.renderFramesAutomatically, r.INSTANCE, new VideoFrameProcessor.Listener() {
            private long lastProcessedFramePresentationTimeUs;

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onEnded$4() {
                SingleInputVideoGraph.this.listener.onEnded(this.lastProcessedFramePresentationTimeUs);
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onError$3(VideoFrameProcessingException videoFrameProcessingException) {
                SingleInputVideoGraph.this.listener.onError(videoFrameProcessingException);
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onOutputFrameAvailableForRendering$2(long j2, boolean z) {
                SingleInputVideoGraph.this.listener.onOutputFrameAvailableForRendering(j2, z);
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onOutputFrameRateChanged$1(float f) {
                SingleInputVideoGraph.this.listener.onOutputFrameRateChanged(f);
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onOutputSizeChanged$0(int i2, int i7) {
                SingleInputVideoGraph.this.listener.onOutputSizeChanged(i2, i7);
            }

            public void onEnded() {
                SingleInputVideoGraph.this.listenerExecutor.execute(new o(2, this));
            }

            public void onError(VideoFrameProcessingException videoFrameProcessingException) {
                SingleInputVideoGraph.this.listenerExecutor.execute(new p(3, this, videoFrameProcessingException));
            }

            public void onOutputFrameAvailableForRendering(long j2, boolean z) {
                if (j2 == 0) {
                    boolean unused = SingleInputVideoGraph.this.hasProducedFrameWithTimestampZero = true;
                }
                this.lastProcessedFramePresentationTimeUs = j2;
                SingleInputVideoGraph.this.listenerExecutor.execute(new v(this, j2, z, 1));
            }

            public void onOutputFrameRateChanged(float f) {
                SingleInputVideoGraph.this.listenerExecutor.execute(new t(this, f, 1));
            }

            public void onOutputSizeChanged(int i2, int i7) {
                SingleInputVideoGraph.this.listenerExecutor.execute(new u(this, i2, i7, 1));
            }
        });
        this.videoFrameProcessor = create;
        SurfaceInfo surfaceInfo = this.outputSurfaceInfo;
        if (surfaceInfo != null) {
            create.setOutputSurfaceInfo(surfaceInfo);
        }
    }

    public boolean registerInputFrame(int i2) {
        Assertions.checkStateNotNull(this.videoFrameProcessor);
        return this.videoFrameProcessor.registerInputFrame();
    }

    /* JADX WARNING: type inference failed for: r7v2, types: [F2.N, F2.Q] */
    public void registerInputStream(int i2, int i7, Format format, List<Effect> list, long j2) {
        Assertions.checkStateNotNull(this.videoFrameProcessor);
        VideoFrameProcessor videoFrameProcessor2 = this.videoFrameProcessor;
        ? n = new N(4);
        n.c(list);
        n.c(this.compositionEffects);
        videoFrameProcessor2.registerInputStream(i7, format, n.f(), j2);
    }

    public void release() {
        if (!this.released) {
            VideoFrameProcessor videoFrameProcessor2 = this.videoFrameProcessor;
            if (videoFrameProcessor2 != null) {
                videoFrameProcessor2.release();
            }
            this.released = true;
        }
    }

    public void renderOutputFrame(long j2) {
        Assertions.checkStateNotNull(this.videoFrameProcessor);
        this.videoFrameProcessor.renderOutputFrame(j2);
    }

    public void setCompositionEffects(List<Effect> list) {
        this.compositionEffects = U.y(list);
    }

    public void setCompositorSettings(VideoCompositorSettings videoCompositorSettings) {
        Assertions.checkArgument(videoCompositorSettings.equals(VideoCompositorSettings.DEFAULT), "SingleInputVideoGraph does not use VideoCompositor, and therefore cannot apply VideoCompositorSettings");
    }

    public void setOutputSurfaceInfo(SurfaceInfo surfaceInfo) {
        this.outputSurfaceInfo = surfaceInfo;
        VideoFrameProcessor videoFrameProcessor2 = this.videoFrameProcessor;
        if (videoFrameProcessor2 != null) {
            videoFrameProcessor2.setOutputSurfaceInfo(surfaceInfo);
        }
    }

    public void signalEndOfInput(int i2) {
        Assertions.checkStateNotNull(this.videoFrameProcessor);
        this.videoFrameProcessor.signalEndOfInput();
    }

    public void initialize() {
    }
}
