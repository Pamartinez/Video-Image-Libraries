package androidx.media3.common.util;

import android.graphics.Bitmap;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Build;
import c0.C0086a;
import i.C0212a;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GlUtil {
    public static final int[] EGL_CONFIG_ATTRIBUTES_RGBA_1010102 = {12352, 4, 12324, 10, 12323, 10, 12322, 10, 12321, 2, 12325, 0, 12326, 0, 12344};
    public static final int[] EGL_CONFIG_ATTRIBUTES_RGBA_8888 = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_HLG = {12445, 13632, 12344, 12344};
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_PQ = {12445, 13120, 12344, 12344};
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_NONE = {12344};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class GlException extends Exception {
        public GlException(String str) {
            super(str);
        }
    }

    private static void assertValidTextureSize(int i2, int i7) {
        boolean z = true;
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(3379, iArr, 0);
        int i8 = iArr[0];
        if (i8 <= 0) {
            z = false;
        }
        Assertions.checkState(z, "Create a OpenGL context first or run the GL methods on an OpenGL thread.");
        if (i2 < 0 || i7 < 0) {
            throw new GlException("width or height is less than 0");
        } else if (i2 > i8 || i7 > i8) {
            throw new GlException(C0086a.i(i8, "width or height is greater than GL_MAX_TEXTURE_SIZE "));
        }
    }

    public static void bindTexture(int i2, int i7, int i8) {
        GLES20.glBindTexture(i2, i7);
        checkGlError();
        GLES20.glTexParameteri(i2, 10240, i8);
        checkGlError();
        GLES20.glTexParameteri(i2, 10241, i8);
        checkGlError();
        GLES20.glTexParameteri(i2, 10242, 33071);
        checkGlError();
        GLES20.glTexParameteri(i2, 10243, 33071);
        checkGlError();
    }

    private static void checkEglException(String str) {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError != 12288) {
            StringBuilder t = C0212a.t(str, ", error code: 0x");
            t.append(Integer.toHexString(eglGetError));
            throw new GlException(t.toString());
        }
    }

    public static void checkGlError() {
        StringBuilder sb2 = new StringBuilder();
        boolean z = false;
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError == 0) {
                break;
            }
            if (z) {
                sb2.append(10);
            }
            String gluErrorString = GLU.gluErrorString(glGetError);
            if (gluErrorString == null) {
                gluErrorString = "error code: 0x" + Integer.toHexString(glGetError);
            }
            sb2.append("glError: ");
            sb2.append(gluErrorString);
            z = true;
        }
        if (z) {
            throw new GlException(sb2.toString());
        }
    }

    public static void checkGlException(boolean z, String str) {
        if (!z) {
            throw new GlException(str);
        }
    }

    public static void clearFocusedBuffers() {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClearDepthf(1.0f);
        GLES20.glClear(16640);
        checkGlError();
    }

    public static float[] create4x4IdentityMatrix() {
        float[] fArr = new float[16];
        setToIdentity(fArr);
        return fArr;
    }

    public static FloatBuffer createBuffer(float[] fArr) {
        return (FloatBuffer) createBuffer(fArr.length).put(fArr).flip();
    }

    public static EGLContext createEglContext(EGLDisplay eGLDisplay) {
        return createEglContext(EGL14.EGL_NO_CONTEXT, eGLDisplay, 2, EGL_CONFIG_ATTRIBUTES_RGBA_8888);
    }

    public static EGLSurface createEglSurface(EGLDisplay eGLDisplay, Object obj, int i2, boolean z) {
        int[] iArr;
        int[] iArr2;
        if (i2 == 3 || i2 == 10) {
            iArr2 = EGL_CONFIG_ATTRIBUTES_RGBA_8888;
            iArr = EGL_WINDOW_SURFACE_ATTRIBUTES_NONE;
        } else if (i2 == 7 || i2 == 6) {
            iArr2 = EGL_CONFIG_ATTRIBUTES_RGBA_1010102;
            if (z) {
                iArr = EGL_WINDOW_SURFACE_ATTRIBUTES_NONE;
            } else if (i2 == 6) {
                if (isBt2020PqExtensionSupported()) {
                    iArr = EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_PQ;
                } else {
                    throw new GlException("BT.2020 PQ OpenGL output isn't supported.");
                }
            } else if (isBt2020HlgExtensionSupported()) {
                iArr = EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_HLG;
            } else {
                throw new GlException("BT.2020 HLG OpenGL output isn't supported.");
            }
        } else {
            throw new IllegalArgumentException(C0086a.i(i2, "Unsupported color transfer: "));
        }
        EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(eGLDisplay, getEglConfig(eGLDisplay, iArr2), obj, iArr, 0);
        checkEglException("Error creating a new EGL surface");
        return eglCreateWindowSurface;
    }

    public static int createExternalTexture() {
        int generateTexture = generateTexture();
        bindTexture(36197, generateTexture, 9729);
        return generateTexture;
    }

    public static int createFboForTexture(int i2) {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        checkGlError();
        GLES20.glBindFramebuffer(36160, iArr[0]);
        checkGlError();
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, i2, 0);
        checkGlError();
        return iArr[0];
    }

    public static EGLSurface createFocusedPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay) {
        EGLSurface eGLSurface;
        int[] iArr = EGL_CONFIG_ATTRIBUTES_RGBA_8888;
        if (isSurfacelessContextExtensionSupported()) {
            eGLSurface = EGL14.EGL_NO_SURFACE;
        } else {
            eGLSurface = createPbufferSurface(eGLDisplay, 1, 1, iArr);
        }
        focusEglSurface(eGLDisplay, eGLContext, eGLSurface, 1, 1);
        return eGLSurface;
    }

    public static long createGlSyncFence() {
        if (getContextMajorVersion() < 3) {
            return 0;
        }
        long glFenceSync = GLES30.glFenceSync(37143, 0);
        checkGlError();
        GLES20.glFlush();
        checkGlError();
        return glFenceSync;
    }

    private static EGLSurface createPbufferSurface(EGLDisplay eGLDisplay, int i2, int i7, int[] iArr) {
        EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, getEglConfig(eGLDisplay, iArr), new int[]{12375, i2, 12374, i7, 12344}, 0);
        checkEglException("Error creating a new EGL Pbuffer surface");
        return eglCreatePbufferSurface;
    }

    public static int createTexture(Bitmap bitmap) {
        int generateTexture = generateTexture();
        setTexture(generateTexture, bitmap);
        return generateTexture;
    }

    private static int createTextureUninitialized(int i2, int i7, int i8, int i10) {
        assertValidTextureSize(i2, i7);
        int generateTexture = generateTexture();
        bindTexture(3553, generateTexture, 9729);
        GLES20.glTexImage2D(3553, 0, i8, i2, i7, 0, 6408, i10, (Buffer) null);
        checkGlError();
        return generateTexture;
    }

    public static float[] createVertexBuffer(List<float[]> list) {
        float[] fArr = new float[(list.size() * 4)];
        for (int i2 = 0; i2 < list.size(); i2++) {
            System.arraycopy(list.get(i2), 0, fArr, i2 * 4, 4);
        }
        return fArr;
    }

    public static void deleteFbo(int i2) {
        GLES20.glDeleteFramebuffers(1, new int[]{i2}, 0);
        checkGlError();
    }

    public static void deleteRbo(int i2) {
        GLES20.glDeleteRenderbuffers(1, new int[]{i2}, 0);
        checkGlError();
    }

    public static void deleteSyncObject(long j2) {
        deleteSyncObjectQuietly(j2);
        checkGlError();
    }

    public static void deleteSyncObjectQuietly(long j2) {
        GLES30.glDeleteSync(j2);
    }

    public static void deleteTexture(int i2) {
        GLES20.glDeleteTextures(1, new int[]{i2}, 0);
        checkGlError();
    }

    public static void destroyEglContext(EGLDisplay eGLDisplay, EGLContext eGLContext) {
        if (eGLDisplay != null && !eGLDisplay.equals(EGL14.EGL_NO_DISPLAY)) {
            EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
            EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT);
            checkEglException("Error releasing context");
            if (eGLContext != null && !eGLContext.equals(EGL14.EGL_NO_CONTEXT)) {
                EGL14.eglDestroyContext(eGLDisplay, eGLContext);
                checkEglException("Error destroying context");
            }
            EGL14.eglReleaseThread();
            checkEglException("Error releasing thread");
            EGL14.eglTerminate(eGLDisplay);
            checkEglException("Error terminating display");
        }
    }

    public static void destroyEglSurface(EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
        if (eGLDisplay != null && !eGLDisplay.equals(EGL14.EGL_NO_DISPLAY) && eGLSurface != null && !eGLSurface.equals(EGL14.EGL_NO_SURFACE)) {
            EGL14.eglDestroySurface(eGLDisplay, eGLSurface);
            checkEglException("Error destroying surface");
        }
    }

    public static void focusEglSurface(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i2, int i7) {
        focusRenderTarget(eGLDisplay, eGLContext, eGLSurface, 0, i2, i7);
    }

    public static void focusFramebufferUsingCurrentContext(int i2, int i7, int i8) {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        if (iArr[0] != i2) {
            GLES20.glBindFramebuffer(36160, i2);
        }
        checkGlError();
        GLES20.glViewport(0, 0, i7, i8);
        checkGlError();
    }

    private static void focusRenderTarget(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i2, int i7, int i8) {
        EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, eGLContext);
        checkEglException("Error making context current");
        focusFramebufferUsingCurrentContext(i2, i7, i8);
    }

    public static int generateTexture() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        checkGlError();
        return iArr[0];
    }

    public static long getContextMajorVersion() {
        int[] iArr = new int[1];
        EGL14.eglQueryContext(EGL14.eglGetDisplay(0), EGL14.eglGetCurrentContext(), 12440, iArr, 0);
        checkGlError();
        return (long) iArr[0];
    }

    public static EGLContext getCurrentContext() {
        return EGL14.eglGetCurrentContext();
    }

    public static EGLDisplay getDefaultEglDisplay() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        checkGlException(!eglGetDisplay.equals(EGL14.EGL_NO_DISPLAY), "No EGL display.");
        checkGlException(EGL14.eglInitialize(eglGetDisplay, new int[1], 0, new int[1], 0), "Error in eglInitialize.");
        checkGlError();
        return eglGetDisplay;
    }

    private static EGLConfig getEglConfig(EGLDisplay eGLDisplay, int[] iArr) {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (EGL14.eglChooseConfig(eGLDisplay, iArr, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
            return eGLConfigArr[0];
        }
        throw new GlException("eglChooseConfig failed.");
    }

    public static float[] getNormalizedCoordinateBounds() {
        return new float[]{-1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    }

    public static boolean isBt2020HlgExtensionSupported() {
        return isExtensionSupported("EGL_EXT_gl_colorspace_bt2020_hlg");
    }

    public static boolean isBt2020PqExtensionSupported() {
        if (Build.VERSION.SDK_INT < 33 || !isExtensionSupported("EGL_EXT_gl_colorspace_bt2020_pq")) {
            return false;
        }
        return true;
    }

    private static boolean isExtensionSupported(String str) {
        String eglQueryString = EGL14.eglQueryString(getDefaultEglDisplay(), 12373);
        if (eglQueryString == null || !eglQueryString.contains(str)) {
            return false;
        }
        return true;
    }

    public static boolean isSurfacelessContextExtensionSupported() {
        return isExtensionSupported("EGL_KHR_surfaceless_context");
    }

    public static boolean isYuvTargetExtensionSupported() {
        String str;
        if (Objects.equals(EGL14.eglGetCurrentContext(), EGL14.EGL_NO_CONTEXT)) {
            try {
                EGLDisplay defaultEglDisplay = getDefaultEglDisplay();
                EGLContext createEglContext = createEglContext(defaultEglDisplay);
                createFocusedPlaceholderEglSurface(createEglContext, defaultEglDisplay);
                str = GLES20.glGetString(7939);
                destroyEglContext(defaultEglDisplay, createEglContext);
            } catch (GlException unused) {
                return false;
            }
        } else {
            str = GLES20.glGetString(7939);
        }
        if (str == null || !str.contains("GL_EXT_YUV_target")) {
            return false;
        }
        return true;
    }

    public static void setTexture(int i2, Bitmap bitmap) {
        assertValidTextureSize(bitmap.getWidth(), bitmap.getHeight());
        bindTexture(3553, i2, 9729);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        checkGlError();
    }

    public static void setToIdentity(float[] fArr) {
        Matrix.setIdentityM(fArr, 0);
    }

    private static FloatBuffer createBuffer(int i2) {
        return ByteBuffer.allocateDirect(i2 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    public static EGLContext createEglContext(EGLContext eGLContext, EGLDisplay eGLDisplay, int i2, int[] iArr) {
        boolean z = true;
        Assertions.checkArgument(Arrays.equals(iArr, EGL_CONFIG_ATTRIBUTES_RGBA_8888) || Arrays.equals(iArr, EGL_CONFIG_ATTRIBUTES_RGBA_1010102));
        if (!(i2 == 2 || i2 == 3)) {
            z = false;
        }
        Assertions.checkArgument(z);
        EGLContext eglCreateContext = EGL14.eglCreateContext(eGLDisplay, getEglConfig(eGLDisplay, iArr), eGLContext, new int[]{12440, i2, 12344}, 0);
        if (eglCreateContext == null || eglCreateContext.equals(EGL14.EGL_NO_CONTEXT)) {
            EGL14.eglTerminate(eGLDisplay);
            throw new GlException(C0086a.i(i2, "eglCreateContext() failed to create a valid context. The device may not support EGL version "));
        }
        checkGlError();
        return eglCreateContext;
    }

    public static int createTexture(int i2, int i7, boolean z) {
        if (z) {
            return createTextureUninitialized(i2, i7, 34842, 5131);
        }
        return createTextureUninitialized(i2, i7, 6408, 5121);
    }
}
