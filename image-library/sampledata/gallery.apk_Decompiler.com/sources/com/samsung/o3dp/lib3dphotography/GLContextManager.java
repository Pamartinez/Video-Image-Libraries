package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.o3dp.lib3dphotography.MeshUtils;
import com.samsung.o3dp.lib3dphotography.O3DPContext;
import com.samsung.o3dp.lib3dphotography.O3DPListener;
import com.samsung.o3dp.lib3dphotography.graphics.Texture;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class GLContextManager {
    private static final String TAG = "GLContextManager";
    private final IGLThread mGLThread;
    private boolean mIsRenderStarted = false;
    private final O3DPContext mO3DPContext;
    private final O3DPhotoConfig mO3DPhotoConfig;

    public GLContextManager(O3DPContext o3DPContext, O3DPhotoConfig o3DPhotoConfig, IGLThread iGLThread) {
        this.mO3DPContext = o3DPContext;
        this.mO3DPhotoConfig = o3DPhotoConfig;
        this.mGLThread = iGLThread;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init3DShader$0(O3DPContext.LayerBitmapInfo[] layerBitmapInfoArr, Context context, O3DPListener o3DPListener) {
        this.mO3DPContext.buildShader(layerBitmapInfoArr, context);
        visualize(o3DPListener);
        this.mO3DPContext.resetAnimationParams();
        this.mIsRenderStarted = true;
        o3DPListener.onRenderStart();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init3DShader$1(O3DPContext.LayerBitmapInfo[] layerBitmapInfoArr, Context context, O3DPListener o3DPListener) {
        this.mO3DPContext.applyInPainting(layerBitmapInfoArr, context);
        this.mGLThread.runOnGLThread(new c(this, layerBitmapInfoArr, context, o3DPListener, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init3DShader$2(Bitmap bitmap, Bitmap bitmap2, DepthMap depthMap, DepthMap depthMap2, O3DPListener o3DPListener, Context context) {
        this.mO3DPContext.create3DShader(bitmap, bitmap2, depthMap, depthMap2);
        try {
            this.mO3DPContext.initRenderer(true);
            ThreadHandler.getInstance().runOnWorkerThread(new c(this, this.mO3DPContext.getInpaintLayerBitmaps(), context, o3DPListener, 1));
        } catch (IllegalStateException e) {
            IllegalStateException illegalStateException = e;
            LogUtil.e(TAG, illegalStateException.toString(), illegalStateException);
            o3DPListener.onFailed(O3DPListener.ErrorType.RENDERING_FAIL, illegalStateException.toString());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init3DShader$3(Context context, MeshUtils.MeshInfo meshInfo, O3DPListener o3DPListener) {
        this.mO3DPContext.buildShader(context, meshInfo);
        visualize(o3DPListener);
        this.mO3DPContext.resetAnimationParams();
        this.mIsRenderStarted = true;
        o3DPListener.onRenderStart();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init3DShader$4(Context context, MeshUtils.MeshInfo meshInfo, O3DPListener o3DPListener) {
        this.mGLThread.runOnGLThread(new e(this, context, meshInfo, o3DPListener, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init3DShader$5(int i2, int i7, O3DPListener o3DPListener, Context context, MeshUtils.MeshInfo meshInfo) {
        this.mO3DPContext.resizeColorTex(i2, i7);
        try {
            this.mO3DPContext.initRenderer(false);
            ThreadHandler.getInstance().runOnWorkerThread(new e(this, context, meshInfo, o3DPListener, 0));
        } catch (IllegalStateException e) {
            IllegalStateException illegalStateException = e;
            LogUtil.e(TAG, illegalStateException.toString(), illegalStateException);
            o3DPListener.onFailed(O3DPListener.ErrorType.RENDERING_FAIL, illegalStateException.toString());
        }
    }

    public void init3DShader(Context context, Bitmap bitmap, Bitmap bitmap2, DepthMap depthMap, DepthMap depthMap2, O3DPListener o3DPListener) {
        this.mGLThread.runOnGLThread(new f(this, bitmap, bitmap2, depthMap, depthMap2, o3DPListener, context));
    }

    public boolean isRenderStarted() {
        return this.mIsRenderStarted;
    }

    public void visualize(O3DPListener o3DPListener) {
        Texture.FitMode fitMode;
        boolean z;
        if (!this.mO3DPContext.visualize()) {
            LogUtil.d(TAG, "Not ready for rendering");
            return;
        }
        this.mO3DPContext.getRenderer().clear();
        O3DPhotoConfig o3DPhotoConfig = this.mO3DPhotoConfig;
        if (o3DPhotoConfig != null) {
            fitMode = o3DPhotoConfig.getFitMode();
        } else {
            fitMode = Texture.FitMode.FIT_FULL;
        }
        if (fitMode == Texture.FitMode.FIT_FULL) {
            z = true;
        } else {
            z = false;
        }
        this.mO3DPContext.getRenderer().render(this.mO3DPContext.getOutTex(), z, Texture.Orientation.ORI_FLIP_Y, fitMode);
        o3DPListener.onRenderUpdate();
        if (this.mO3DPContext.isSupportForegroundMask()) {
            ((O3DPLayerEffectListener) o3DPListener).onForegroundMask(this.mO3DPContext.getForegroundMask(), this.mO3DPContext.getRenderer().getHOffset(), this.mO3DPContext.getRenderer().getVOffset());
        }
    }

    public void init3DShader(Context context, MeshUtils.MeshInfo meshInfo, int i2, int i7, O3DPListener o3DPListener) {
        this.mGLThread.runOnGLThread(new d(this, i2, i7, o3DPListener, context, meshInfo));
    }
}
