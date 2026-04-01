package com.samsung.android.sdk.sgpl.pip.surfaces;

import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.Surface;
import com.samsung.android.sdk.sgpl.pip.renderer.RenderTexture_GL_OES;
import com.samsung.android.sdk.sgpl.pip.util.Constants;
import com.samsung.android.sdk.sgpl.pip.util.OpenGlHelper;
import i.C0212a;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OutputSurface implements SurfaceTexture.OnFrameAvailableListener {
    private static final int EGL_OPENGL_ES2_BIT = 4;
    public static final String EXCEPTION_FRAME_NOT_AVAILABLE = "Surface frame wait timed out";
    private static final int HD_SIZE = 921600;
    private EGL10 mEGL;
    private EGLContext mEGLContext;
    private EGLDisplay mEGLDisplay;
    private EGLSurface mEGLSurface;
    private boolean mFrameAvailable;
    private Object mFrameSyncObject = new Object();
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private RenderTexture_GL_OES mTextureRenderer;

    public OutputSurface(int i2) {
        setup(i2);
    }

    private void checkEglError(String str) {
        boolean z = false;
        while (true) {
            int eglGetError = this.mEGL.eglGetError();
            if (eglGetError == 12288) {
                break;
            }
            StringBuilder t = C0212a.t(str, ": EGL error: 0x");
            t.append(Integer.toHexString(eglGetError));
            Log.e(Constants.TAG, t.toString());
            z = true;
        }
        if (z) {
            throw new RuntimeException("EGL error encountered (see log)");
        }
    }

    private void eglSetup(int i2, int i7) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        this.mEGL = egl10;
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        this.mEGLDisplay = eglGetDisplay;
        if (this.mEGL.eglInitialize(eglGetDisplay, (int[]) null)) {
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            if (this.mEGL.eglChooseConfig(this.mEGLDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12339, 1, 12352, 4, 12344}, eGLConfigArr, 1, new int[1])) {
                this.mEGLContext = this.mEGL.eglCreateContext(this.mEGLDisplay, eGLConfigArr[0], EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
                checkEglError("eglCreateContext");
                if (this.mEGLContext != null) {
                    this.mEGLSurface = this.mEGL.eglCreatePbufferSurface(this.mEGLDisplay, eGLConfigArr[0], new int[]{12375, i2, 12374, i7, 12344});
                    checkEglError("eglCreatePbufferSurface");
                    if (this.mEGLSurface == null) {
                        throw new RuntimeException("surface was null");
                    }
                    return;
                }
                throw new RuntimeException("null context");
            }
            throw new RuntimeException("unable to find RGB888+pbuffer EGL config");
        }
        throw new RuntimeException("unable to initialize EGL10");
    }

    private void setup(int i2) {
        RenderTexture_GL_OES renderTexture_GL_OES = new RenderTexture_GL_OES();
        this.mTextureRenderer = renderTexture_GL_OES;
        renderTexture_GL_OES.prepare(i2);
        Log.d(Constants.TAG, "textureID=" + this.mTextureRenderer.getTextureId());
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mTextureRenderer.getTextureId());
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        this.mSurface = new Surface(this.mSurfaceTexture);
    }

    public boolean checkForNewImage(int i2) {
        synchronized (this.mFrameSyncObject) {
            do {
                try {
                    if (!this.mFrameAvailable) {
                        this.mFrameSyncObject.wait((long) i2);
                    } else {
                        this.mFrameAvailable = false;
                        OpenGlHelper.checkGLError("before updateTexImage");
                        this.mSurfaceTexture.updateTexImage();
                        return true;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            } while (this.mFrameAvailable);
            return false;
        }
    }

    public void drawImage(int i2) {
        this.mTextureRenderer.draw(this.mSurfaceTexture, i2);
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public void initSurfaceAspectRatio(int i2, float f, float f5) {
        this.mTextureRenderer.initSurfaceAspectRatioAndScaleMVPMatrix(i2, f, f5);
    }

    public void notifyFrameSyncObject() {
        synchronized (this.mFrameSyncObject) {
            this.mFrameSyncObject.notifyAll();
        }
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        Log.d(Constants.TAG, "new frame available");
        synchronized (this.mFrameSyncObject) {
            try {
                if (!this.mFrameAvailable) {
                    this.mFrameAvailable = true;
                    this.mFrameSyncObject.notifyAll();
                } else {
                    throw new RuntimeException("mFrameAvailable already set, frame could be dropped");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void release() {
        EGL10 egl10 = this.mEGL;
        if (egl10 != null) {
            if (egl10.eglGetCurrentContext().equals(this.mEGLContext)) {
                EGL10 egl102 = this.mEGL;
                EGLDisplay eGLDisplay = this.mEGLDisplay;
                EGLSurface eGLSurface = EGL10.EGL_NO_SURFACE;
                egl102.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
            }
            this.mEGL.eglDestroySurface(this.mEGLDisplay, this.mEGLSurface);
            this.mEGL.eglDestroyContext(this.mEGLDisplay, this.mEGLContext);
        }
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
        }
        this.mEGLDisplay = null;
        this.mEGLContext = null;
        this.mEGLSurface = null;
        this.mEGL = null;
        RenderTexture_GL_OES renderTexture_GL_OES = this.mTextureRenderer;
        if (renderTexture_GL_OES != null) {
            renderTexture_GL_OES.release();
        }
        this.mTextureRenderer = null;
        this.mSurface = null;
        this.mSurfaceTexture = null;
    }

    public void setPipVideoPosition(int i2, int i7) {
        this.mTextureRenderer.setSurfacePosition(i2, i7);
    }

    public void setPipVideoRelatedPosition(float f, float f5) {
        this.mTextureRenderer.setMVPMatrixPosition(f, f5, 0.0f);
    }

    public OutputSurface(int i2, int i7, int i8, int i10, int i11, int i12, int i13) {
        setup(i2, i7, i8, i10, i11, i12, i13);
    }

    private void setup(int i2, int i7, int i8, int i10, int i11, int i12, int i13) {
        RenderTexture_GL_OES renderTexture_GL_OES = new RenderTexture_GL_OES();
        this.mTextureRenderer = renderTexture_GL_OES;
        renderTexture_GL_OES.prepare(i2, i7, i8, i10, i11, i12, i13, 0, 0);
        Log.d(Constants.TAG, "textureID=" + this.mTextureRenderer.getTextureId());
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mTextureRenderer.getTextureId());
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        this.mSurface = new Surface(this.mSurfaceTexture);
    }
}
