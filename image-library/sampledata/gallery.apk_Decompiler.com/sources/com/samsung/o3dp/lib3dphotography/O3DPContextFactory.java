package com.samsung.o3dp.lib3dphotography;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import javax.microedition.khronos.egl.EGL10;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class O3DPContextFactory implements GLSurfaceView.EGLContextFactory {
    private static final String TAG = "O3DPContextFactory";
    private EGLContext mEGLContext;
    private int mEGLContextClientVersion = 2;

    private static javax.microedition.khronos.egl.EGLContext egl10ContextFromEgl14Context(EGLContext eGLContext) {
        EGLSurface eGLSurface;
        EGLContext eglGetCurrentContext = EGL14.eglGetCurrentContext();
        EGLDisplay eglGetCurrentDisplay = EGL14.eglGetCurrentDisplay();
        EGLSurface eglGetCurrentSurface = EGL14.eglGetCurrentSurface(12377);
        EGLSurface eglGetCurrentSurface2 = EGL14.eglGetCurrentSurface(12378);
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        if (eglGetDisplay != EGL14.EGL_NO_DISPLAY) {
            if (!eglGetCurrentContext.equals(eGLContext)) {
                int[] iArr = new int[1];
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                if (!EGL14.eglChooseConfig(eglGetDisplay, new int[]{12324, 1, 12323, 1, 12322, 1, 12344}, 0, eGLConfigArr, 0, 1, iArr, 0)) {
                    throw new RuntimeException("eglChooseConfig failed: eglError " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                } else if (iArr[0] > 0) {
                    EGLConfig eGLConfig = eGLConfigArr[0];
                    if (eGLConfig != null) {
                        eGLSurface = EGL14.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, new int[]{12375, 1, 12374, 1, 12344}, 0);
                        if (eGLSurface == null || eGLSurface == EGL14.EGL_NO_SURFACE) {
                            throw new RuntimeException("failed to create surface: eglError " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                        } else if (!EGL14.eglMakeCurrent(eglGetDisplay, eGLSurface, eGLSurface, eGLContext)) {
                            throw new RuntimeException("failed to make temporary context current: " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                        }
                    } else {
                        throw new RuntimeException("eglConfig is null");
                    }
                } else {
                    throw new RuntimeException("No configs match requested attributes");
                }
            } else {
                eGLSurface = null;
            }
            javax.microedition.khronos.egl.EGLContext eglGetCurrentContext2 = ((EGL10) javax.microedition.khronos.egl.EGLContext.getEGL()).eglGetCurrentContext();
            if (!eglGetCurrentContext.equals(eGLContext)) {
                if (eglGetCurrentDisplay == EGL14.EGL_NO_DISPLAY) {
                    eglGetCurrentDisplay = eglGetDisplay;
                }
                EGL14.eglMakeCurrent(eglGetCurrentDisplay, eglGetCurrentSurface, eglGetCurrentSurface2, eglGetCurrentContext);
                EGL14.eglDestroySurface(eglGetDisplay, eGLSurface);
            }
            return eglGetCurrentContext2;
        }
        throw new RuntimeException("defaultDisplay is EGL_NO_DISPLAY");
    }

    public javax.microedition.khronos.egl.EGLContext createContext(EGL10 egl10, javax.microedition.khronos.egl.EGLDisplay eGLDisplay, javax.microedition.khronos.egl.EGLConfig eGLConfig) {
        javax.microedition.khronos.egl.EGLContext egl10ContextFromEgl14Context = egl10ContextFromEgl14Context(this.mEGLContext);
        int i2 = this.mEGLContextClientVersion;
        int[] iArr = {12440, i2, 12344};
        if (i2 == 0) {
            iArr = null;
        }
        return egl10.eglCreateContext(eGLDisplay, eGLConfig, egl10ContextFromEgl14Context, iArr);
    }

    public void destroyContext(EGL10 egl10, javax.microedition.khronos.egl.EGLDisplay eGLDisplay, javax.microedition.khronos.egl.EGLContext eGLContext) {
        if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
            throw new RuntimeException("eglDestroyContext: " + egl10.eglGetError());
        }
    }

    public void setEGLContext(EGLContext eGLContext) {
        this.mEGLContext = eGLContext;
    }

    public void setEGLContextClientVersion(int i2) {
        this.mEGLContextClientVersion = i2;
    }
}
