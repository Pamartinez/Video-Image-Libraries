package com.samsung.o3dp.lib3dphotography;

import A.a;
import android.graphics.Rect;
import android.opengl.EGLContext;
import android.opengl.GLES20;
import android.view.Surface;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.o3dp.lib3dphotography.graphics.EGLHandler;
import com.samsung.o3dp.lib3dphotography.graphics.Texture;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ResultRenderer {
    private static final int[] EGL_CONFIG_ATTRIBS = {12352, 64, 12324, 8, 12323, 8, 12322, 8, 12344};
    private static final int LOG_INTERVAL = 300;
    private static final String TAG = "ResultRenderer";
    private float[] mBgColor = {1.0f, 1.0f, 1.0f, 1.0f};
    private int mCountBlitToFBO = 0;
    private final EGLHandler mEglHandler;
    private int mHOffset = 0;
    private int mHeight = -1;
    private boolean mNeedSwapBuffer = false;
    private int mVOffset = 0;
    private int mWidth = -1;

    public ResultRenderer(EGLHandler eGLHandler) {
        EGLHandler eGLHandler2 = new EGLHandler();
        this.mEglHandler = eGLHandler2;
        eGLHandler2.initEGLShared(eGLHandler.getEglContext(), EGL_CONFIG_ATTRIBS);
    }

    private void clearSurface() {
        float[] fArr = this.mBgColor;
        GLES20.glClearColor(fArr[0], fArr[1], fArr[2], fArr[3]);
        GLES20.glClear(16384);
    }

    public void blitToFBO(Texture texture, int i2, int i7, int i8, int i10, boolean z, Texture.Orientation orientation, Texture.FitMode fitMode) {
        if (i8 <= 0 || i10 <= 0) {
            String format = StringUtil.format("renderWithOffset failed. invalid size (%d, %d - %d, %d)", Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i10));
            LogUtil.e(TAG, format);
            throw new IllegalStateException(format);
        }
        int i11 = this.mCountBlitToFBO + 1;
        this.mCountBlitToFBO = i11;
        if (i11 == 300) {
            LogUtil.i(TAG, StringUtil.format("Rendering Info : size (%d, %d - %d, %d), preserveAspect : %b, orientation : %s, fitMode : %s", Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i10), Boolean.valueOf(z), orientation.name(), fitMode.name()));
            this.mCountBlitToFBO = 0;
        }
        GLES20.glBindFramebuffer(36160, 0);
        texture.blitToFBO(0, i2, i7, i8, i10, z, orientation, fitMode);
    }

    public void clear() {
        GLES20.glBindFramebuffer(36160, 0);
        clearSurface();
    }

    public void clearWithSwapBuffer() {
        clear();
        swapBuffer();
    }

    public EGLContext getEglContext() {
        return this.mEglHandler.getEglContext();
    }

    public int getHOffset() {
        return this.mHOffset;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public Rect getRenderRect() {
        int i2 = this.mHOffset;
        int i7 = this.mVOffset;
        return new Rect(i2, i7, this.mWidth + i2, this.mHeight + i7);
    }

    public int getVOffset() {
        return this.mVOffset;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void initEglSurface(Surface surface) {
        if (surface != null && !this.mNeedSwapBuffer) {
            this.mEglHandler.initEglSurface(surface);
            this.mNeedSwapBuffer = true;
        }
    }

    public void makeCurrent() {
        this.mEglHandler.makeCurrent();
    }

    public void release() {
        this.mEglHandler.release();
    }

    public void render(Texture texture, boolean z, Texture.Orientation orientation, Texture.FitMode fitMode) {
        renderWithOffset(texture, this.mHOffset, this.mVOffset, z, orientation, fitMode);
        if (this.mNeedSwapBuffer) {
            swapBuffer();
        }
    }

    public void renderWithOffset(Texture texture, int i2, int i7, boolean z, Texture.Orientation orientation, Texture.FitMode fitMode) {
        blitToFBO(texture, i2, i7, this.mWidth, this.mHeight, z, orientation, fitMode);
    }

    public void setBgColor(float[] fArr) {
        this.mBgColor = fArr;
    }

    public void setOffset(int i2, int i7) {
        LogUtil.i(TAG, a.d(i2, i7, "setOffset(", ArcCommonLog.TAG_COMMA, ")"));
        this.mHOffset = i2 / 2;
        this.mVOffset = i7 / 2;
    }

    public void setSize(int i2, int i7) {
        LogUtil.i(TAG, a.d(i2, i7, "setSize(", ArcCommonLog.TAG_COMMA, ")"));
        this.mWidth = i2;
        this.mHeight = i7;
    }

    public void swapBuffer() {
        this.mEglHandler.swapBuffer();
    }
}
