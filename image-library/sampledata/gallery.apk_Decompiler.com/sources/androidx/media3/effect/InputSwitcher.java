package androidx.media3.effect;

import android.content.Context;
import android.util.SparseArray;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.effect.GlShaderProgram;
import c0.C0086a;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class InputSwitcher {
    private TextureManager activeTextureManager;
    private final Context context;
    private GlShaderProgram downstreamShaderProgram;
    private final Executor errorListenerExecutor;
    private final boolean experimentalAdjustSurfaceTextureTransformationMatrix;
    private final GlObjectsProvider glObjectsProvider;
    private final SparseArray<Input> inputs;
    private final ColorInfo outputColorInfo;
    private final GlShaderProgram.ErrorListener samplingShaderProgramErrorListener;
    private final int sdrWorkingColorSpace;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class GatedChainingListenerWrapper implements GlShaderProgram.OutputListener, GlShaderProgram.InputListener {
        private final ChainingGlShaderProgramListener chainingGlShaderProgramListener;
        private boolean isActive;

        public GatedChainingListenerWrapper(GlObjectsProvider glObjectsProvider, GlShaderProgram glShaderProgram, GlShaderProgram glShaderProgram2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor) {
            this.chainingGlShaderProgramListener = new ChainingGlShaderProgramListener(glObjectsProvider, glShaderProgram, glShaderProgram2, videoFrameProcessingTaskExecutor);
        }

        public synchronized void onCurrentOutputStreamEnded() {
            if (this.isActive) {
                this.chainingGlShaderProgramListener.onCurrentOutputStreamEnded();
            }
        }

        public void onInputFrameProcessed(GlTextureInfo glTextureInfo) {
            if (this.isActive) {
                this.chainingGlShaderProgramListener.onInputFrameProcessed(glTextureInfo);
            }
        }

        public synchronized void onOutputFrameAvailable(GlTextureInfo glTextureInfo, long j2) {
            if (this.isActive) {
                this.chainingGlShaderProgramListener.onOutputFrameAvailable(glTextureInfo, j2);
            }
        }

        public void onReadyToAcceptInputFrame() {
            if (this.isActive) {
                this.chainingGlShaderProgramListener.onReadyToAcceptInputFrame();
            }
        }

        public void setActive(boolean z) {
            this.isActive = z;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Input {
        /* access modifiers changed from: private */
        public GatedChainingListenerWrapper gatedChainingListenerWrapper;
        private boolean released;
        private ExternalShaderProgram samplingGlShaderProgram;
        public final TextureManager textureManager;

        public Input(TextureManager textureManager2) {
            this.textureManager = textureManager2;
        }

        public ExternalShaderProgram getSamplingGlShaderProgram() {
            return this.samplingGlShaderProgram;
        }

        public void release() {
            if (!this.released) {
                this.released = true;
                this.textureManager.release();
                ExternalShaderProgram externalShaderProgram = this.samplingGlShaderProgram;
                if (externalShaderProgram != null) {
                    externalShaderProgram.release();
                }
            }
        }

        public void setActive(boolean z) {
            GatedChainingListenerWrapper gatedChainingListenerWrapper2 = this.gatedChainingListenerWrapper;
            if (gatedChainingListenerWrapper2 != null) {
                gatedChainingListenerWrapper2.setActive(z);
            }
        }

        public void setChainingListener(GatedChainingListenerWrapper gatedChainingListenerWrapper2) {
            this.gatedChainingListenerWrapper = gatedChainingListenerWrapper2;
            ((ExternalShaderProgram) Assertions.checkNotNull(this.samplingGlShaderProgram)).setOutputListener(gatedChainingListenerWrapper2);
        }

        public void setSamplingGlShaderProgram(ExternalShaderProgram externalShaderProgram) {
            ExternalShaderProgram externalShaderProgram2 = this.samplingGlShaderProgram;
            if (externalShaderProgram2 != null) {
                externalShaderProgram2.release();
            }
            this.samplingGlShaderProgram = externalShaderProgram;
            this.textureManager.setSamplingGlShaderProgram(externalShaderProgram);
            externalShaderProgram.setInputListener(this.textureManager);
        }
    }

    public InputSwitcher(Context context2, ColorInfo colorInfo, GlObjectsProvider glObjectsProvider2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2, Executor executor, GlShaderProgram.ErrorListener errorListener, int i2, boolean z, boolean z3, boolean z7) {
        this.context = context2;
        this.outputColorInfo = colorInfo;
        this.glObjectsProvider = glObjectsProvider2;
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
        this.errorListenerExecutor = executor;
        this.samplingShaderProgramErrorListener = errorListener;
        SparseArray<Input> sparseArray = new SparseArray<>();
        this.inputs = sparseArray;
        this.sdrWorkingColorSpace = i2;
        this.experimentalAdjustSurfaceTextureTransformationMatrix = z3;
        Input input = new Input(new ExternalTextureManager(glObjectsProvider2, videoFrameProcessingTaskExecutor2, z, z3));
        sparseArray.put(1, input);
        sparseArray.put(4, input);
        sparseArray.put(2, new Input(new BitmapTextureManager(glObjectsProvider2, videoFrameProcessingTaskExecutor2, z7)));
        sparseArray.put(3, new Input(new TexIdTextureManager(glObjectsProvider2, videoFrameProcessingTaskExecutor2)));
    }

    private DefaultShaderProgram createSamplingShaderProgram(ColorInfo colorInfo, int i2) {
        DefaultShaderProgram defaultShaderProgram;
        if (i2 != 1) {
            if (i2 == 2 || i2 == 3) {
                defaultShaderProgram = DefaultShaderProgram.createWithInternalSampler(this.context, colorInfo, this.outputColorInfo, this.sdrWorkingColorSpace, i2);
                defaultShaderProgram.setErrorListener(this.errorListenerExecutor, this.samplingShaderProgramErrorListener);
                return defaultShaderProgram;
            } else if (i2 != 4) {
                throw new VideoFrameProcessingException(C0086a.i(i2, "Unsupported input type "));
            }
        }
        defaultShaderProgram = DefaultShaderProgram.createWithExternalSampler(this.context, colorInfo, this.outputColorInfo, this.sdrWorkingColorSpace, this.experimentalAdjustSurfaceTextureTransformationMatrix);
        defaultShaderProgram.setErrorListener(this.errorListenerExecutor, this.samplingShaderProgramErrorListener);
        return defaultShaderProgram;
    }

    public TextureManager activeTextureManager() {
        return (TextureManager) Assertions.checkStateNotNull(this.activeTextureManager);
    }

    public Surface getInputSurface() {
        Assertions.checkState(Util.contains(this.inputs, 1));
        return this.inputs.get(1).textureManager.getInputSurface();
    }

    public boolean hasActiveInput() {
        if (this.activeTextureManager != null) {
            return true;
        }
        return false;
    }

    public void release() {
        for (int i2 = 0; i2 < this.inputs.size(); i2++) {
            SparseArray<Input> sparseArray = this.inputs;
            sparseArray.get(sparseArray.keyAt(i2)).release();
        }
    }

    public void setDownstreamShaderProgram(GlShaderProgram glShaderProgram) {
        this.downstreamShaderProgram = glShaderProgram;
    }

    public void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
        Assertions.checkState(Util.contains(this.inputs, 3));
        this.inputs.get(3).textureManager.setOnInputFrameProcessedListener(onInputFrameProcessedListener);
    }

    public void signalEndOfCurrentInputStream() {
        ((TextureManager) Assertions.checkNotNull(this.activeTextureManager)).signalEndOfCurrentInputStream();
    }

    public void switchToInput(int i2, FrameInfo frameInfo) {
        Assertions.checkStateNotNull(this.downstreamShaderProgram);
        Assertions.checkState(Util.contains(this.inputs, i2), "Input type not registered: " + i2);
        boolean z = false;
        for (int i7 = 0; i7 < this.inputs.size(); i7++) {
            SparseArray<Input> sparseArray = this.inputs;
            sparseArray.get(sparseArray.keyAt(i7)).setActive(false);
        }
        Input input = this.inputs.get(i2);
        input.setSamplingGlShaderProgram(createSamplingShaderProgram((ColorInfo) Assertions.checkNotNull(frameInfo.format.colorInfo), i2));
        input.setChainingListener(new GatedChainingListenerWrapper(this.glObjectsProvider, (GlShaderProgram) Assertions.checkNotNull(input.getSamplingGlShaderProgram()), this.downstreamShaderProgram, this.videoFrameProcessingTaskExecutor));
        input.setActive(true);
        this.downstreamShaderProgram.setInputListener((GlShaderProgram.InputListener) Assertions.checkNotNull(input.gatedChainingListenerWrapper));
        TextureManager textureManager = input.textureManager;
        this.activeTextureManager = textureManager;
        if (i2 == 4) {
            z = true;
        }
        ((TextureManager) Assertions.checkNotNull(textureManager)).setInputFrameInfo(frameInfo, z);
    }
}
