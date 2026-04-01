package androidx.media3.effect;

import Ad.j;
import F2.C0040v;
import F2.N;
import F2.U;
import F2.y0;
import H.b;
import android.content.Context;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Build;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.effect.GlShaderProgram;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;
import com.google.common.util.concurrent.r;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DebugViewShaderProgram implements GlShaderProgram {
    private final Context context;
    private SurfaceView debugSurfaceView;
    private SurfaceViewWrapper debugSurfaceViewWrapper;
    private final DebugViewProvider debugViewProvider;
    private DefaultShaderProgram defaultShaderProgram;
    private EGLDisplay eglDisplay;
    private GlShaderProgram.ErrorListener errorListener = new j(22);
    private Executor errorListenerExecutor = r.INSTANCE;
    private GlShaderProgram.InputListener inputListener = new GlShaderProgram.InputListener() {
    };
    private final ColorInfo outputColorInfo;
    private int outputHeight = -1;
    private GlShaderProgram.OutputListener outputListener = new GlShaderProgram.OutputListener() {
    };
    private int outputWidth = -1;

    public DebugViewShaderProgram(Context context2, DebugViewProvider debugViewProvider2, ColorInfo colorInfo) {
        this.context = context2;
        this.debugViewProvider = debugViewProvider2;
        this.outputColorInfo = colorInfo;
    }

    private void ensureConfigured(int i2, int i7) {
        if (this.eglDisplay == null) {
            this.eglDisplay = GlUtil.getDefaultEglDisplay();
        }
        EGLContext currentContext = GlUtil.getCurrentContext();
        if (this.outputWidth == -1 || this.outputHeight == -1) {
            this.outputWidth = i2;
            this.outputHeight = i7;
        }
        DebugViewProvider debugViewProvider2 = this.debugViewProvider;
        int i8 = this.outputWidth;
        int i10 = this.outputHeight;
        ((j) debugViewProvider2).getClass();
        SurfaceView a7 = DebugViewProvider.lambda$static$0(i8, i10);
        if (a7 != null && !Objects.equals(this.debugSurfaceView, a7)) {
            this.debugSurfaceViewWrapper = new SurfaceViewWrapper(this.eglDisplay, currentContext, a7, this.outputColorInfo.colorTransfer);
        }
        this.debugSurfaceView = a7;
        if (this.defaultShaderProgram == null) {
            C0040v.c(4, "initialCapacity");
            Object[] objArr = new Object[4];
            int i11 = 0;
            Presentation createForWidthAndHeight = Presentation.createForWidthAndHeight(this.outputWidth, this.outputHeight, 0);
            createForWidthAndHeight.getClass();
            int e = N.e(4, 1);
            if (e > 4) {
                objArr = Arrays.copyOf(objArr, e);
            }
            objArr[0] = createForWidthAndHeight;
            Context context2 = this.context;
            y0 w = U.w(1, objArr);
            y0 y0Var = y0.f278h;
            ColorInfo colorInfo = this.outputColorInfo;
            if (colorInfo.colorTransfer == 1) {
                i11 = 2;
            }
            this.defaultShaderProgram = DefaultShaderProgram.createApplyingOetf(context2, w, y0Var, colorInfo, i11);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$queueInputFrame$2(Exception exc, long j2) {
        this.errorListener.onError(VideoFrameProcessingException.from(exc, j2));
    }

    public void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j2) {
        long j3;
        try {
            ensureConfigured(glTextureInfo.width, glTextureInfo.height);
            DefaultShaderProgram defaultShaderProgram2 = (DefaultShaderProgram) Assertions.checkNotNull(this.defaultShaderProgram);
            GlTextureInfo glTextureInfo2 = glTextureInfo;
            j3 = j2;
            try {
                ((SurfaceViewWrapper) Assertions.checkNotNull(this.debugSurfaceViewWrapper)).maybeRenderToSurfaceView(new d(defaultShaderProgram2, glTextureInfo2, j3, 0), glObjectsProvider);
                this.outputListener.onOutputFrameAvailable(glTextureInfo2, j3);
            } catch (VideoFrameProcessingException | GlUtil.GlException e) {
                e = e;
                this.errorListenerExecutor.execute(new b(this, e, j3, 0));
            }
        } catch (VideoFrameProcessingException | GlUtil.GlException e7) {
            e = e7;
            j3 = j2;
            this.errorListenerExecutor.execute(new b(this, e, j3, 0));
        }
    }

    public void release() {
        DefaultShaderProgram defaultShaderProgram2 = this.defaultShaderProgram;
        if (defaultShaderProgram2 != null) {
            defaultShaderProgram2.release();
        }
        try {
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    public void releaseOutputFrame(GlTextureInfo glTextureInfo) {
        this.inputListener.onInputFrameProcessed(glTextureInfo);
        this.inputListener.onReadyToAcceptInputFrame();
    }

    public void setErrorListener(Executor executor, GlShaderProgram.ErrorListener errorListener2) {
        this.errorListener = errorListener2;
        this.errorListenerExecutor = executor;
    }

    public void setInputListener(GlShaderProgram.InputListener inputListener2) {
        this.inputListener = inputListener2;
        inputListener2.onReadyToAcceptInputFrame();
    }

    public void setOutputListener(GlShaderProgram.OutputListener outputListener2) {
        this.outputListener = outputListener2;
    }

    public void signalEndOfCurrentInputStream() {
        this.outputListener.onCurrentOutputStreamEnded();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SurfaceViewWrapper implements SurfaceHolder.Callback {
        private final EGLContext eglContext;
        private final EGLDisplay eglDisplay;
        private EGLSurface eglSurface;
        private int height;
        public final int outputColorTransfer;
        private Surface surface;
        private int width;

        public SurfaceViewWrapper(EGLDisplay eGLDisplay, EGLContext eGLContext, SurfaceView surfaceView, int i2) {
            this.eglDisplay = eGLDisplay;
            this.eglContext = eGLContext;
            if (i2 == 7 && Build.VERSION.SDK_INT < 34) {
                i2 = 6;
            }
            this.outputColorTransfer = i2;
            surfaceView.getHolder().addCallback(this);
            this.surface = surfaceView.getHolder().getSurface();
            this.width = surfaceView.getWidth();
            this.height = surfaceView.getHeight();
        }

        public synchronized void maybeRenderToSurfaceView(VideoFrameProcessingTaskExecutor.Task task, GlObjectsProvider glObjectsProvider) {
            try {
                Surface surface2 = this.surface;
                if (surface2 != null) {
                    if (this.eglSurface == null) {
                        this.eglSurface = glObjectsProvider.createEglSurface(this.eglDisplay, surface2, this.outputColorTransfer, false);
                    }
                    EGLSurface eGLSurface = this.eglSurface;
                    GlUtil.focusEglSurface(this.eglDisplay, this.eglContext, eGLSurface, this.width, this.height);
                    task.run();
                    EGL14.eglSwapBuffers(this.eglDisplay, eGLSurface);
                    GLES20.glFinish();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }

        public synchronized void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i7, int i8) {
            this.width = i7;
            this.height = i8;
            Surface surface2 = surfaceHolder.getSurface();
            if (!surface2.equals(this.surface)) {
                this.surface = surface2;
                this.eglSurface = null;
            }
        }

        public synchronized void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.surface = null;
            this.eglSurface = null;
            this.width = -1;
            this.height = -1;
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
        }
    }
}
