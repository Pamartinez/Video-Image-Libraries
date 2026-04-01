package androidx.media3.effect;

import F2.C0020h0;
import F2.C0022i0;
import F2.C0040v;
import F2.G;
import F2.N;
import F2.U;
import F2.y0;
import android.content.Context;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.util.SparseArray;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.OverlaySettings;
import androidx.media3.common.VideoCompositorSettings;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.effect.GlTextureProducer;
import androidx.media3.effect.VideoCompositor;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultVideoCompositor implements VideoCompositor {
    private boolean allInputsEnded;
    private final CompositorGlProgram compositorGlProgram;
    private ColorInfo configuredColorInfo;
    private EGLDisplay eglDisplay;
    private final GlObjectsProvider glObjectsProvider;
    private final SparseArray<InputSource> inputSources = new SparseArray<>();
    private final VideoCompositor.Listener listener;
    private final TexturePool outputTexturePool;
    private final LongArrayQueue outputTextureTimestamps;
    private EGLSurface placeholderEglSurface;
    private int primaryInputIndex = -1;
    private final LongArrayQueue syncObjects;
    private final GlTextureProducer.Listener textureOutputListener;
    private VideoCompositorSettings videoCompositorSettings;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CompositorGlProgram {
        private final Context context;
        private GlProgram glProgram;
        private final OverlayMatrixProvider overlayMatrixProvider = new OverlayMatrixProvider();

        public CompositorGlProgram(Context context2) {
            this.context = context2;
        }

        private void blendOntoFocusedTexture(InputFrameInfo inputFrameInfo) {
            GlProgram glProgram2 = (GlProgram) Assertions.checkNotNull(this.glProgram);
            GlTextureInfo glTextureInfo = inputFrameInfo.timedGlTextureInfo.glTextureInfo;
            glProgram2.setSamplerTexIdUniform("uTexSampler", glTextureInfo.texId, 0);
            glProgram2.setFloatsUniform("uTransformationMatrix", this.overlayMatrixProvider.getTransformationMatrix(new Size(glTextureInfo.width, glTextureInfo.height), inputFrameInfo.overlaySettings));
            glProgram2.setFloatUniform("uAlphaScale", inputFrameInfo.overlaySettings.getAlphaScale());
            glProgram2.bindAttributesAndUniforms();
            GLES20.glDrawArrays(5, 0, 4);
            GlUtil.checkGlError();
        }

        private void ensureConfigured() {
            if (this.glProgram == null) {
                try {
                    GlProgram glProgram2 = new GlProgram(this.context, "shaders/vertex_shader_transformation_es2.glsl", "shaders/fragment_shader_alpha_scale_es2.glsl");
                    this.glProgram = glProgram2;
                    glProgram2.setBufferAttribute("aFramePosition", GlUtil.getNormalizedCoordinateBounds(), 4);
                    this.glProgram.setFloatsUniform("uTexTransformationMatrix", GlUtil.create4x4IdentityMatrix());
                } catch (IOException e) {
                    throw new VideoFrameProcessingException((Throwable) e);
                }
            }
        }

        public void drawFrame(List<InputFrameInfo> list, GlTextureInfo glTextureInfo) {
            ensureConfigured();
            GlUtil.focusFramebufferUsingCurrentContext(glTextureInfo.fboId, glTextureInfo.width, glTextureInfo.height);
            this.overlayMatrixProvider.configure(new Size(glTextureInfo.width, glTextureInfo.height));
            GlUtil.clearFocusedBuffers();
            ((GlProgram) Assertions.checkNotNull(this.glProgram)).use();
            GLES20.glEnable(3042);
            GLES20.glBlendFuncSeparate(ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8, ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8, 1, ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8);
            GlUtil.checkGlError();
            for (int size = list.size() - 1; size >= 0; size--) {
                blendOntoFocusedTexture(list.get(size));
            }
            GLES20.glDisable(3042);
            GlUtil.checkGlError();
        }

        public void release() {
            try {
                GlProgram glProgram2 = this.glProgram;
                if (glProgram2 != null) {
                    glProgram2.delete();
                }
            } catch (GlUtil.GlException e) {
                Log.e("CompositorGlProgram", "Error releasing GL Program", e);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class InputFrameInfo {
        public final OverlaySettings overlaySettings;
        public final GlTextureProducer textureProducer;
        public final TimedGlTextureInfo timedGlTextureInfo;

        public InputFrameInfo(GlTextureProducer glTextureProducer, TimedGlTextureInfo timedGlTextureInfo2, OverlaySettings overlaySettings2) {
            this.textureProducer = glTextureProducer;
            this.timedGlTextureInfo = timedGlTextureInfo2;
            this.overlaySettings = overlaySettings2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class InputSource {
        public final Queue<InputFrameInfo> frameInfos = new ArrayDeque();
        public boolean isInputEnded;
    }

    public DefaultVideoCompositor(Context context, GlObjectsProvider glObjectsProvider2, ExecutorService executorService, VideoCompositor.Listener listener2, GlTextureProducer.Listener listener3, int i2) {
        this.listener = listener2;
        this.textureOutputListener = listener3;
        this.glObjectsProvider = glObjectsProvider2;
        this.compositorGlProgram = new CompositorGlProgram(context);
        this.outputTexturePool = new TexturePool(false, i2);
        this.outputTextureTimestamps = new LongArrayQueue(i2);
        this.syncObjects = new LongArrayQueue(i2);
        this.videoCompositorSettings = VideoCompositorSettings.DEFAULT;
        Objects.requireNonNull(listener2);
        VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2 = new VideoFrameProcessingTaskExecutor(executorService, false, new g(0, listener2));
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
        videoFrameProcessingTaskExecutor2.submit(new f(this, 1));
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [F2.N, F2.Q] */
    private synchronized U getFramesToComposite() {
        if (this.outputTexturePool.freeTextureCount() == 0) {
            G g = U.e;
            return y0.f278h;
        }
        for (int i2 = 0; i2 < this.inputSources.size(); i2++) {
            if (this.inputSources.valueAt(i2).frameInfos.isEmpty()) {
                G g3 = U.e;
                return y0.f278h;
            }
        }
        ? n = new N(4);
        InputFrameInfo element = this.inputSources.get(this.primaryInputIndex).frameInfos.element();
        n.a(element);
        for (int i7 = 0; i7 < this.inputSources.size(); i7++) {
            if (this.inputSources.keyAt(i7) != this.primaryInputIndex) {
                InputSource valueAt = this.inputSources.valueAt(i7);
                if (valueAt.frameInfos.size() != 1 || valueAt.isInputEnded) {
                    Iterator<InputFrameInfo> it = valueAt.frameInfos.iterator();
                    long j2 = Long.MAX_VALUE;
                    InputFrameInfo inputFrameInfo = null;
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        InputFrameInfo next = it.next();
                        long j3 = next.timedGlTextureInfo.presentationTimeUs;
                        long abs = Math.abs(j3 - element.timedGlTextureInfo.presentationTimeUs);
                        if (abs < j2) {
                            inputFrameInfo = next;
                            j2 = abs;
                        }
                        if (j3 > element.timedGlTextureInfo.presentationTimeUs || (!it.hasNext() && valueAt.isInputEnded)) {
                            n.a((InputFrameInfo) Assertions.checkNotNull(inputFrameInfo));
                        }
                    }
                    n.a((InputFrameInfo) Assertions.checkNotNull(inputFrameInfo));
                } else {
                    G g10 = U.e;
                    return y0.f278h;
                }
            }
        }
        y0 f = n.f();
        if (f.g == this.inputSources.size()) {
            return f;
        }
        return y0.f278h;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$releaseExcessFramesInSecondaryStream$1(long j2, InputFrameInfo inputFrameInfo) {
        if (inputFrameInfo.timedGlTextureInfo.presentationTimeUs <= j2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public synchronized void maybeComposite() {
        DefaultVideoCompositor defaultVideoCompositor;
        try {
            U framesToComposite = getFramesToComposite();
            if (!framesToComposite.isEmpty()) {
                InputFrameInfo inputFrameInfo = (InputFrameInfo) framesToComposite.get(this.primaryInputIndex);
                try {
                    C0040v.c(4, "initialCapacity");
                    Object[] objArr = new Object[4];
                    int i2 = 0;
                    int i7 = 0;
                    while (i2 < framesToComposite.size()) {
                        GlTextureInfo glTextureInfo = ((InputFrameInfo) framesToComposite.get(i2)).timedGlTextureInfo.glTextureInfo;
                        Size size = new Size(glTextureInfo.width, glTextureInfo.height);
                        int i8 = i7 + 1;
                        int e = N.e(objArr.length, i8);
                        if (e > objArr.length) {
                            objArr = Arrays.copyOf(objArr, e);
                        }
                        objArr[i7] = size;
                        i2++;
                        i7 = i8;
                    }
                    Size outputSize = this.videoCompositorSettings.getOutputSize(U.w(i7, objArr));
                    this.outputTexturePool.ensureConfigured(this.glObjectsProvider, outputSize.getWidth(), outputSize.getHeight());
                    GlTextureInfo useTexture = this.outputTexturePool.useTexture();
                    long j2 = inputFrameInfo.timedGlTextureInfo.presentationTimeUs;
                    this.outputTextureTimestamps.add(j2);
                    this.compositorGlProgram.drawFrame(framesToComposite, useTexture);
                    long createGlSyncFence = GlUtil.createGlSyncFence();
                    this.syncObjects.add(createGlSyncFence);
                    defaultVideoCompositor = this;
                    try {
                        this.textureOutputListener.onTextureRendered(defaultVideoCompositor, useTexture, j2, createGlSyncFence);
                        InputSource inputSource = defaultVideoCompositor.inputSources.get(defaultVideoCompositor.primaryInputIndex);
                        defaultVideoCompositor.releaseFrames(inputSource, 1);
                        defaultVideoCompositor.releaseExcessFramesInAllSecondaryStreams();
                        if (defaultVideoCompositor.allInputsEnded && inputSource.frameInfos.isEmpty()) {
                            defaultVideoCompositor.listener.onEnded();
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    defaultVideoCompositor = this;
                    th = th2;
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            defaultVideoCompositor = this;
            throw th;
        }
    }

    private synchronized void releaseExcessFramesInAllSecondaryStreams() {
        for (int i2 = 0; i2 < this.inputSources.size(); i2++) {
            if (this.inputSources.keyAt(i2) != this.primaryInputIndex) {
                releaseExcessFramesInSecondaryStream(this.inputSources.valueAt(i2));
            }
        }
    }

    private synchronized void releaseExcessFramesInSecondaryStream(InputSource inputSource) {
        long j2;
        int i2;
        InputSource inputSource2 = this.inputSources.get(this.primaryInputIndex);
        if (!inputSource2.frameInfos.isEmpty() || !inputSource2.isInputEnded) {
            InputFrameInfo peek = inputSource2.frameInfos.peek();
            if (peek != null) {
                j2 = peek.timedGlTextureInfo.presentationTimeUs;
            } else {
                j2 = -9223372036854775807L;
            }
            Queue<InputFrameInfo> queue = inputSource.frameInfos;
            i iVar = new i(j2);
            queue.getClass();
            C0020h0 h0Var = new C0020h0(queue, iVar);
            if (h0Var instanceof Collection) {
                i2 = ((Collection) h0Var).size();
            } else {
                Iterator it = h0Var.iterator();
                long j3 = 0;
                while (true) {
                    C0022i0 i0Var = (C0022i0) it;
                    if (!i0Var.hasNext()) {
                        break;
                    }
                    i0Var.next();
                    j3++;
                }
                i2 = C0246a.j0(j3);
            }
            releaseFrames(inputSource, Math.max(i2 - 1, 0));
            return;
        }
        releaseFrames(inputSource, inputSource.frameInfos.size());
    }

    private synchronized void releaseFrames(InputSource inputSource, int i2) {
        for (int i7 = 0; i7 < i2; i7++) {
            InputFrameInfo remove = inputSource.frameInfos.remove();
            remove.textureProducer.releaseOutputTexture(remove.timedGlTextureInfo.presentationTimeUs);
        }
    }

    /* access modifiers changed from: private */
    public void releaseGlObjects() {
        try {
            this.compositorGlProgram.release();
            this.outputTexturePool.deleteAllTextures();
            GlUtil.destroyEglSurface(this.eglDisplay, this.placeholderEglSurface);
        } catch (GlUtil.GlException e) {
            Log.e("DefaultVideoCompositor", "Error releasing GL resources", e);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: releaseOutputTextureInternal */
    public synchronized void lambda$releaseOutputTexture$0(long j2) {
        while (this.outputTexturePool.freeTextureCount() < this.outputTexturePool.capacity() && this.outputTextureTimestamps.element() <= j2) {
            try {
                this.outputTexturePool.freeTexture();
                this.outputTextureTimestamps.remove();
                GlUtil.deleteSyncObject(this.syncObjects.remove());
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        maybeComposite();
    }

    /* access modifiers changed from: private */
    public void setupGlObjects() {
        EGLDisplay defaultEglDisplay = GlUtil.getDefaultEglDisplay();
        this.eglDisplay = defaultEglDisplay;
        this.placeholderEglSurface = this.glObjectsProvider.createFocusedPlaceholderEglSurface(this.glObjectsProvider.createEglContext(defaultEglDisplay, 2, GlUtil.EGL_CONFIG_ATTRIBUTES_RGBA_8888), this.eglDisplay);
    }

    public synchronized void queueInputTexture(int i2, GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, ColorInfo colorInfo, long j2) {
        try {
            Assertions.checkState(Util.contains(this.inputSources, i2));
            InputSource inputSource = this.inputSources.get(i2);
            Assertions.checkState(!inputSource.isInputEnded);
            Assertions.checkStateNotNull(Boolean.valueOf(!ColorInfo.isTransferHdr(colorInfo)), "HDR input is not supported.");
            if (this.configuredColorInfo == null) {
                this.configuredColorInfo = colorInfo;
            }
            Assertions.checkState(this.configuredColorInfo.equals(colorInfo), "Mixing different ColorInfos is not supported.");
            inputSource.frameInfos.add(new InputFrameInfo(glTextureProducer, new TimedGlTextureInfo(glTextureInfo, j2), this.videoCompositorSettings.getOverlaySettings(i2, j2)));
            if (i2 == this.primaryInputIndex) {
                releaseExcessFramesInAllSecondaryStreams();
            } else {
                releaseExcessFramesInSecondaryStream(inputSource);
            }
            this.videoFrameProcessingTaskExecutor.submit(new f(this, 2));
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void registerInputSource(int i2) {
        Assertions.checkState(!Util.contains(this.inputSources, i2));
        this.inputSources.put(i2, new InputSource());
        if (this.primaryInputIndex == -1) {
            this.primaryInputIndex = i2;
        }
    }

    public synchronized void release() {
        try {
            this.videoFrameProcessingTaskExecutor.release(new f(this, 0));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

    public void releaseOutputTexture(long j2) {
        this.videoFrameProcessingTaskExecutor.submit(new h(this, j2, 0));
    }

    public void setVideoCompositorSettings(VideoCompositorSettings videoCompositorSettings2) {
        this.videoCompositorSettings = videoCompositorSettings2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0081, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void signalEndOfInputSource(int r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r0 = r4.inputSources     // Catch:{ all -> 0x003a }
            boolean r0 = androidx.media3.common.util.Util.contains(r0, r5)     // Catch:{ all -> 0x003a }
            androidx.media3.common.util.Assertions.checkState(r0)     // Catch:{ all -> 0x003a }
            int r0 = r4.primaryInputIndex     // Catch:{ all -> 0x003a }
            r1 = -1
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x0013
            r0 = r3
            goto L_0x0014
        L_0x0013:
            r0 = r2
        L_0x0014:
            androidx.media3.common.util.Assertions.checkState(r0)     // Catch:{ all -> 0x003a }
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r0 = r4.inputSources     // Catch:{ all -> 0x003a }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x003a }
            androidx.media3.effect.DefaultVideoCompositor$InputSource r0 = (androidx.media3.effect.DefaultVideoCompositor.InputSource) r0     // Catch:{ all -> 0x003a }
            r0.isInputEnded = r3     // Catch:{ all -> 0x003a }
            r0 = r2
        L_0x0022:
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r1 = r4.inputSources     // Catch:{ all -> 0x003a }
            int r1 = r1.size()     // Catch:{ all -> 0x003a }
            if (r0 >= r1) goto L_0x003c
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r1 = r4.inputSources     // Catch:{ all -> 0x003a }
            java.lang.Object r1 = r1.valueAt(r0)     // Catch:{ all -> 0x003a }
            androidx.media3.effect.DefaultVideoCompositor$InputSource r1 = (androidx.media3.effect.DefaultVideoCompositor.InputSource) r1     // Catch:{ all -> 0x003a }
            boolean r1 = r1.isInputEnded     // Catch:{ all -> 0x003a }
            if (r1 != 0) goto L_0x0037
            goto L_0x003d
        L_0x0037:
            int r0 = r0 + 1
            goto L_0x0022
        L_0x003a:
            r5 = move-exception
            goto L_0x0082
        L_0x003c:
            r2 = r3
        L_0x003d:
            r4.allInputsEnded = r2     // Catch:{ all -> 0x003a }
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r0 = r4.inputSources     // Catch:{ all -> 0x003a }
            int r1 = r4.primaryInputIndex     // Catch:{ all -> 0x003a }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x003a }
            androidx.media3.effect.DefaultVideoCompositor$InputSource r0 = (androidx.media3.effect.DefaultVideoCompositor.InputSource) r0     // Catch:{ all -> 0x003a }
            java.util.Queue<androidx.media3.effect.DefaultVideoCompositor$InputFrameInfo> r0 = r0.frameInfos     // Catch:{ all -> 0x003a }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0061
            int r0 = r4.primaryInputIndex     // Catch:{ all -> 0x003a }
            if (r5 != r0) goto L_0x0058
            r4.releaseExcessFramesInAllSecondaryStreams()     // Catch:{ all -> 0x003a }
        L_0x0058:
            if (r2 == 0) goto L_0x0061
            androidx.media3.effect.VideoCompositor$Listener r5 = r4.listener     // Catch:{ all -> 0x003a }
            r5.onEnded()     // Catch:{ all -> 0x003a }
            monitor-exit(r4)
            return
        L_0x0061:
            int r0 = r4.primaryInputIndex     // Catch:{ all -> 0x003a }
            if (r5 == r0) goto L_0x0080
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r0 = r4.inputSources     // Catch:{ all -> 0x003a }
            java.lang.Object r5 = r0.get(r5)     // Catch:{ all -> 0x003a }
            androidx.media3.effect.DefaultVideoCompositor$InputSource r5 = (androidx.media3.effect.DefaultVideoCompositor.InputSource) r5     // Catch:{ all -> 0x003a }
            java.util.Queue<androidx.media3.effect.DefaultVideoCompositor$InputFrameInfo> r5 = r5.frameInfos     // Catch:{ all -> 0x003a }
            int r5 = r5.size()     // Catch:{ all -> 0x003a }
            if (r5 != r3) goto L_0x0080
            androidx.media3.effect.VideoFrameProcessingTaskExecutor r5 = r4.videoFrameProcessingTaskExecutor     // Catch:{ all -> 0x003a }
            androidx.media3.effect.f r0 = new androidx.media3.effect.f     // Catch:{ all -> 0x003a }
            r1 = 2
            r0.<init>(r4, r1)     // Catch:{ all -> 0x003a }
            r5.submit(r0)     // Catch:{ all -> 0x003a }
        L_0x0080:
            monitor-exit(r4)
            return
        L_0x0082:
            monitor-exit(r4)     // Catch:{ all -> 0x003a }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.DefaultVideoCompositor.signalEndOfInputSource(int):void");
    }
}
