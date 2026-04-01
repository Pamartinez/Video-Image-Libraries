package androidx.media3.effect;

import Ad.j;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import androidx.media3.effect.GlShaderProgram;
import com.google.common.util.concurrent.r;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseGlShaderProgram implements GlShaderProgram {
    private GlShaderProgram.ErrorListener errorListener = new j(21);
    private Executor errorListenerExecutor = r.INSTANCE;
    private int inputHeight = -1;
    private GlShaderProgram.InputListener inputListener = new GlShaderProgram.InputListener() {
    };
    private int inputWidth = -1;
    private GlShaderProgram.OutputListener outputListener = new GlShaderProgram.OutputListener() {
    };
    protected final TexturePool outputTexturePool;

    public BaseGlShaderProgram(boolean z, int i2) {
        this.outputTexturePool = new TexturePool(z, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$queueInputFrame$1(Exception exc) {
        this.errorListener.onError(VideoFrameProcessingException.from(exc));
    }

    public abstract Size configure(int i2, int i7);

    public abstract void drawFrame(int i2, long j2);

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0045 A[Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void queueInputFrame(androidx.media3.common.GlObjectsProvider r4, androidx.media3.common.GlTextureInfo r5, long r6) {
        /*
            r3 = this;
            int r0 = r3.inputWidth     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            int r1 = r5.width     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            if (r0 != r1) goto L_0x0017
            int r0 = r3.inputHeight     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            int r1 = r5.height     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            if (r0 != r1) goto L_0x0017
            androidx.media3.effect.TexturePool r0 = r3.outputTexturePool     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            boolean r0 = r0.isConfigured()     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            if (r0 != 0) goto L_0x0030
            goto L_0x0017
        L_0x0015:
            r4 = move-exception
            goto L_0x0058
        L_0x0017:
            int r0 = r5.width     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            r3.inputWidth = r0     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            int r1 = r5.height     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            r3.inputHeight = r1     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            androidx.media3.common.util.Size r0 = r3.configure(r0, r1)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            androidx.media3.effect.TexturePool r1 = r3.outputTexturePool     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            int r2 = r0.getWidth()     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            int r0 = r0.getHeight()     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            r1.ensureConfigured(r4, r2, r0)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
        L_0x0030:
            androidx.media3.effect.TexturePool r4 = r3.outputTexturePool     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            androidx.media3.common.GlTextureInfo r4 = r4.useTexture()     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            int r0 = r4.fboId     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            int r1 = r4.width     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            int r2 = r4.height     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            androidx.media3.common.util.GlUtil.focusFramebufferUsingCurrentContext(r0, r1, r2)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            boolean r0 = r3.shouldClearTextureBuffer()     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            if (r0 == 0) goto L_0x0048
            androidx.media3.common.util.GlUtil.clearFocusedBuffers()     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
        L_0x0048:
            int r0 = r5.texId     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            r3.drawFrame(r0, r6)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            androidx.media3.effect.GlShaderProgram$InputListener r0 = r3.inputListener     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            r0.onInputFrameProcessed(r5)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            androidx.media3.effect.GlShaderProgram$OutputListener r5 = r3.outputListener     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            r5.onOutputFrameAvailable(r4, r6)     // Catch:{ VideoFrameProcessingException | GlException -> 0x0015 }
            return
        L_0x0058:
            java.util.concurrent.Executor r5 = r3.errorListenerExecutor
            H.a r6 = new H.a
            r7 = 0
            r6.<init>(r7, r3, r4)
            r5.execute(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.BaseGlShaderProgram.queueInputFrame(androidx.media3.common.GlObjectsProvider, androidx.media3.common.GlTextureInfo, long):void");
    }

    public void release() {
        try {
            this.outputTexturePool.deleteAllTextures();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    public void releaseOutputFrame(GlTextureInfo glTextureInfo) {
        if (this.outputTexturePool.isUsingTexture(glTextureInfo)) {
            this.outputTexturePool.freeTexture(glTextureInfo);
            this.inputListener.onReadyToAcceptInputFrame();
        }
    }

    public void setErrorListener(Executor executor, GlShaderProgram.ErrorListener errorListener2) {
        this.errorListenerExecutor = executor;
        this.errorListener = errorListener2;
    }

    public void setInputListener(GlShaderProgram.InputListener inputListener2) {
        this.inputListener = inputListener2;
        for (int i2 = 0; i2 < this.outputTexturePool.freeTextureCount(); i2++) {
            inputListener2.onReadyToAcceptInputFrame();
        }
    }

    public void setOutputListener(GlShaderProgram.OutputListener outputListener2) {
        this.outputListener = outputListener2;
    }

    public boolean shouldClearTextureBuffer() {
        return true;
    }

    public void signalEndOfCurrentInputStream() {
        this.outputListener.onCurrentOutputStreamEnded();
    }
}
