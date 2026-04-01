package androidx.media3.effect;

import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.util.GlUtil;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultGlObjectsProvider implements GlObjectsProvider {
    private final List<EGLContext> createdEglContexts;
    private final EGLContext sharedEglContext;

    public DefaultGlObjectsProvider() {
        this((EGLContext) null);
    }

    public GlTextureInfo createBuffersForTexture(int i2, int i7, int i8) {
        return new GlTextureInfo(i2, GlUtil.createFboForTexture(i2), -1, i7, i8);
    }

    public EGLContext createEglContext(EGLDisplay eGLDisplay, int i2, int[] iArr) {
        EGLContext createEglContext = GlUtil.createEglContext(this.sharedEglContext, eGLDisplay, i2, iArr);
        this.createdEglContexts.add(createEglContext);
        return createEglContext;
    }

    public EGLSurface createEglSurface(EGLDisplay eGLDisplay, Object obj, int i2, boolean z) {
        return GlUtil.createEglSurface(eGLDisplay, obj, i2, z);
    }

    public EGLSurface createFocusedPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay) {
        return GlUtil.createFocusedPlaceholderEglSurface(eGLContext, eGLDisplay);
    }

    public void release(EGLDisplay eGLDisplay) {
        for (int i2 = 0; i2 < this.createdEglContexts.size(); i2++) {
            GlUtil.destroyEglContext(eGLDisplay, this.createdEglContexts.get(i2));
        }
    }

    public DefaultGlObjectsProvider(EGLContext eGLContext) {
        this.sharedEglContext = eGLContext == null ? EGL14.EGL_NO_CONTEXT : eGLContext;
        this.createdEglContexts = new ArrayList();
    }
}
