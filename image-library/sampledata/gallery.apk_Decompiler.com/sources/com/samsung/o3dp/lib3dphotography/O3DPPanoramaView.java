package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import com.samsung.o3dp.lib3dphotography.O3DPContext;
import com.samsung.o3dp.lib3dphotography.graphics.SharedRenderParams;
import com.samsung.o3dp.lib3dphotography.graphics.Texture;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import com.samsung.o3dp.lib3dphotography.utils.ImageUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class O3DPPanoramaView extends O3DPSurfaceView {
    private static final String TAG = "O3DPPanoramaView";
    private int mBitmapHeight = 0;
    private int mBitmapWidth = 0;

    public O3DPPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getBitmapHeight() {
        return this.mBitmapHeight;
    }

    public int getBitmapWidth() {
        return this.mBitmapWidth;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onSurfaceChanged(GL10 gl10, int i2, int i7) {
        super.onSurfaceChanged(gl10, i2, i7);
        if (getPipeline().isRenderStarted()) {
            getPipeline().getO3DPContext().updatePanoramaOutSize();
        }
    }

    public void pausePanoramaNavigation() {
        getPipeline().getO3DPContext().setOutputType(O3DPContext.OutputType.DEFAULT);
    }

    public void resumePanoramaNavitation() {
        getPipeline().getO3DPContext().setOutputType(O3DPContext.OutputType.LOOP);
    }

    public void setCameraEyeZ(float f) {
        SharedRenderParams sharedRenderParams = getPipeline().getO3DPContext().getSharedRenderParams();
        if (sharedRenderParams == null) {
            LogUtil.e(TAG, "setCameraEyeZ(): params are null!");
            return;
        }
        Vector3 cameraEye = sharedRenderParams.getCameraEye();
        cameraEye.z = f;
        sharedRenderParams.setCameraEye(cameraEye);
    }

    public void setImageBitmap(Bitmap bitmap, O3DPListener o3DPListener) {
        getConfig().setPanorama(true);
        getConfig().setFitMode(Texture.FitMode.FIT_BEST);
        if (ImageUtil.needCrop(bitmap.getWidth(), bitmap.getHeight())) {
            bitmap = ImageUtil.centerCrop(bitmap);
        }
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = bitmap.getHeight();
        super.setImageBitmap(bitmap, o3DPListener);
    }

    public void setPanoramaCurvature(float f) {
        SharedRenderParams sharedRenderParams = getPipeline().getO3DPContext().getSharedRenderParams();
        if (sharedRenderParams == null) {
            LogUtil.e(TAG, "setPanoramaCurvature(): params are null!");
        } else {
            sharedRenderParams.setPanoramaCurvature(f);
        }
    }
}
