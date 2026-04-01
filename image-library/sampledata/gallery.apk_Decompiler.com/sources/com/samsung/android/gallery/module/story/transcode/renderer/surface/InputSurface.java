package com.samsung.android.gallery.module.story.transcode.renderer.surface;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.view.Surface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InputSurface {
    private EGLContext mEGLContext = EGL14.EGL_NO_CONTEXT;
    private EGLDisplay mEGLDisplay = EGL14.EGL_NO_DISPLAY;
    private EGLSurface mEGLSurface = EGL14.EGL_NO_SURFACE;
    private Surface mSurface;

    public InputSurface(Surface surface) {
        if (surface != null) {
            this.mSurface = surface;
            eglSetup();
            return;
        }
        throw new IllegalArgumentException("Surface passed is null.");
    }

    private void checkEglError(String str) {
        if (EGL14.eglGetError() != 12288) {
            throw new RuntimeException("EGL error encountered (see log)");
        }
    }

    private void eglSetup() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        this.mEGLDisplay = eglGetDisplay;
        if (eglGetDisplay != EGL14.EGL_NO_DISPLAY) {
            int[] iArr = new int[2];
            if (EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1)) {
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                if (EGL14.eglChooseConfig(this.mEGLDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12352, 4, 12610, 1, 12344}, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
                    this.mEGLContext = EGL14.eglCreateContext(this.mEGLDisplay, eGLConfigArr[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
                    checkEglError("eglCreateContext");
                    if (this.mEGLContext != null) {
                        this.mEGLSurface = EGL14.eglCreateWindowSurface(this.mEGLDisplay, eGLConfigArr[0], this.mSurface, new int[]{12344}, 0);
                        checkEglError("eglCreateWindowSurface");
                        if (this.mEGLSurface == null) {
                            throw new RuntimeException("surface was null");
                        }
                        return;
                    }
                    throw new RuntimeException("null context");
                }
                throw new RuntimeException("unable to find RGB888+recordable ES2 EGL config");
            }
            this.mEGLDisplay = null;
            throw new RuntimeException("unable to initialize EGL14");
        }
        throw new RuntimeException("unable to get EGL14 display");
    }

    public void makeCurrent() {
        EGLDisplay eGLDisplay = this.mEGLDisplay;
        EGLSurface eGLSurface = this.mEGLSurface;
        if (!EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.mEGLContext)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    public void release() {
        EGLContext eGLContext;
        EGLSurface eGLSurface;
        if (EGL14.eglGetCurrentContext().equals(this.mEGLContext)) {
            EGLDisplay eGLDisplay = this.mEGLDisplay;
            EGLSurface eGLSurface2 = EGL14.EGL_NO_SURFACE;
            EGL14.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, EGL14.EGL_NO_CONTEXT);
            try {
                EGLDisplay eGLDisplay2 = this.mEGLDisplay;
                if (!(eGLDisplay2 == null || (eGLSurface = this.mEGLSurface) == null)) {
                    EGL14.eglDestroySurface(eGLDisplay2, eGLSurface);
                }
                EGLDisplay eGLDisplay3 = this.mEGLDisplay;
                if (!(eGLDisplay3 == null || (eGLContext = this.mEGLContext) == null)) {
                    EGL14.eglDestroyContext(eGLDisplay3, eGLContext);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
        }
        this.mEGLDisplay = null;
        this.mEGLContext = null;
        this.mEGLSurface = null;
        this.mSurface = null;
    }

    public void setPresentationTime(long j2) {
        EGLExt.eglPresentationTimeANDROID(this.mEGLDisplay, this.mEGLSurface, j2);
    }

    public boolean swapBuffers() {
        return EGL14.eglSwapBuffers(this.mEGLDisplay, this.mEGLSurface);
    }
}
