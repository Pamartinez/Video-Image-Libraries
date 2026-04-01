package com.samsung.o3dp.lib3dphotography.graphics;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLUtils;
import android.view.Surface;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EGLHandler {
    private static final String TAG = "EGLHandler";
    private final int[] DEFAULT_ATTRIBS = {12352, 64, 12324, 8, 12323, 8, 12322, 8, 12325, 24, 12326, 8, 12344};
    private EGLConfig eglConfig;
    private EGLContext eglContext = EGL14.EGL_NO_CONTEXT;
    private EGLDisplay eglDisplay = EGL14.EGL_NO_DISPLAY;
    private EGLSurface eglSurface = EGL14.EGL_NO_SURFACE;

    public static boolean isCurrentContextValid() {
        if (EGL14.eglGetCurrentContext() != EGL14.EGL_NO_CONTEXT) {
            return true;
        }
        return false;
    }

    public EGLContext getEglContext() {
        return this.eglContext;
    }

    public void initEGL() {
        initEGLShared(EGL14.EGL_NO_CONTEXT, this.DEFAULT_ATTRIBS);
    }

    public void initEGLShared(EGLContext eGLContext, int[] iArr) {
        LogUtil.i(TAG, "the eglDisplay is starting to get initialized");
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        this.eglDisplay = eglGetDisplay;
        if (eglGetDisplay == EGL14.EGL_NO_DISPLAY) {
            int eglGetError = EGL14.eglGetError();
            LogUtil.e(TAG, "The eglDisplay is EGL_NO_DISPLAY! eglError: " + eglGetError);
        }
        int[] iArr2 = new int[2];
        if (!EGL14.eglInitialize(this.eglDisplay, iArr2, 0, iArr2, 1)) {
            int eglGetError2 = EGL14.eglGetError();
            LogUtil.e(TAG, "The eglInitialize failed! eglError: " + eglGetError2);
        }
        EGL14.eglBindAPI(12448);
        int[] iArr3 = new int[1];
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (!EGL14.eglChooseConfig(this.eglDisplay, iArr, 0, eGLConfigArr, 0, 1, iArr3, 0)) {
            int eglGetError3 = EGL14.eglGetError();
            LogUtil.e(TAG, "The eglChooseConfig failed! eglError: " + eglGetError3);
        } else if (iArr3[0] > 0) {
            this.eglConfig = eGLConfigArr[0];
        }
        if (this.eglConfig == null) {
            int eglGetError4 = EGL14.eglGetError();
            LogUtil.e(TAG, "The eglConfig is null! eglError: " + eglGetError4);
        }
        EGLContext eglCreateContext = EGL14.eglCreateContext(this.eglDisplay, this.eglConfig, eGLContext, new int[]{12440, 3, 12344}, 0);
        this.eglContext = eglCreateContext;
        if (eglCreateContext == null) {
            int eglGetError5 = EGL14.eglGetError();
            LogUtil.e(TAG, "The eglContext is null! eglError: " + eglGetError5);
        }
        EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(this.eglDisplay, this.eglConfig, new int[]{12344}, 0);
        this.eglSurface = eglCreatePbufferSurface;
        if (eglCreatePbufferSurface == null || eglCreatePbufferSurface == EGL14.EGL_NO_SURFACE) {
            int eglGetError6 = EGL14.eglGetError();
            if (eglGetError6 == 12299) {
                LogUtil.e(TAG, "EGL_BAD_NATIVE_WINDOW");
                return;
            }
            LogUtil.e(TAG, "eglError: " + eglGetError6);
        }
        makeCurrent();
    }

    public void initEglSurface(Surface surface) {
        this.eglSurface = EGL14.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, surface, new int[]{12344}, 0);
        makeCurrent();
    }

    public void makeCurrent() {
        EGLDisplay eGLDisplay = this.eglDisplay;
        EGLSurface eGLSurface = this.eglSurface;
        if (!EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.eglContext)) {
            throw new RuntimeException("eglMakeCurrent failed " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        }
    }

    public void release() {
        EGLDisplay eGLDisplay = this.eglDisplay;
        if (eGLDisplay != EGL14.EGL_NO_DISPLAY) {
            EGL14.eglDestroySurface(eGLDisplay, this.eglSurface);
            EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.eglDisplay);
        }
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        this.eglSurface = EGL14.EGL_NO_SURFACE;
    }

    public void swapBuffer() {
        EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
    }
}
