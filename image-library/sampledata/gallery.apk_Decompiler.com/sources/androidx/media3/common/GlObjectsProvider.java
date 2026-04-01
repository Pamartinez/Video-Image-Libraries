package androidx.media3.common;

import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface GlObjectsProvider {
    GlTextureInfo createBuffersForTexture(int i2, int i7, int i8);

    EGLContext createEglContext(EGLDisplay eGLDisplay, int i2, int[] iArr);

    EGLSurface createEglSurface(EGLDisplay eGLDisplay, Object obj, int i2, boolean z);

    EGLSurface createFocusedPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay);

    void release(EGLDisplay eGLDisplay);
}
