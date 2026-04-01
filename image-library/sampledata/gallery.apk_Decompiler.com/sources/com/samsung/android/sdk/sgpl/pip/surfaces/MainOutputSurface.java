package com.samsung.android.sdk.sgpl.pip.surfaces;

import A.a;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.util.Log;
import android.view.Surface;
import com.samsung.android.sdk.sgpl.pip.renderer.RenderTextureGL_2D_Main;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MainOutputSurface implements SurfaceTexture.OnFrameAvailableListener {
    private static final String TAG = "PIP";
    private EGL10 mEGL;
    private EGLContext mEGLContext;
    private EGLDisplay mEGLDisplay;
    private EGLSurface mEGLSurface;
    private boolean mFrameAvailable;
    private Object mFrameSyncObject = new Object();
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private RenderTextureGL_2D_Main mTextureRenderer;

    public MainOutputSurface(int i2, int i7, int i8, int i10, int i11) {
        Log.d("PIP", "OutputSurface: Created ");
        setup(i2, i7, i8, i10, i11);
    }

    private void setup(int i2, int i7, int i8, int i10, int i11) {
        StringBuilder h5 = a.h(i7, i8, "setup image : x : ", ", y: ", ", width : ");
        h5.append(i10);
        h5.append(", height: ");
        h5.append(i11);
        Log.d("PIP", h5.toString());
        RenderTextureGL_2D_Main renderTextureGL_2D_Main = new RenderTextureGL_2D_Main();
        this.mTextureRenderer = renderTextureGL_2D_Main;
        renderTextureGL_2D_Main.prepare(i2, i7, i8, i10, i11);
        Log.d("framebuffer", "textureID=" + this.mTextureRenderer.getTextureId());
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mTextureRenderer.getTextureId());
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        this.mSurface = new Surface(this.mSurfaceTexture);
    }

    public void drawImage() {
        this.mTextureRenderer.draw(this.mSurfaceTexture);
    }

    public void notifyFrameSyncObject() {
        synchronized (this.mFrameSyncObject) {
            this.mFrameSyncObject.notifyAll();
        }
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        Log.d("PIP", "new frame available");
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
        RenderTextureGL_2D_Main renderTextureGL_2D_Main = this.mTextureRenderer;
        if (renderTextureGL_2D_Main != null) {
            renderTextureGL_2D_Main.release();
        }
        this.mTextureRenderer = null;
        this.mSurface = null;
        this.mSurfaceTexture = null;
    }

    public void rotationSurface(int i2) {
        this.mTextureRenderer.rotationMainOutputSurface(i2);
    }

    public void setFrameBufferTarget() {
        Log.d("framebuffer", "setFrameBufferTarget: " + this.mTextureRenderer.getTextureId());
        GLES20.glBindTexture(3553, this.mTextureRenderer.getTextureId());
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.mTextureRenderer.getTextureId(), 0);
    }
}
