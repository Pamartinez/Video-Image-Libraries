package com.samsung.android.gallery.image360.engine.texture;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseSpreadTexture extends AbstractSphereTexture {
    public void calculatePoint(float[] fArr, float[] fArr2, int i2, double d, double d2) {
        int i7 = i2 * 3;
        int i8 = i2 * 2;
        double cos = Math.cos(d) * 1.899999976158142d;
        double sin = Math.sin(d2) * cos;
        fArr[i7] = (float) (Math.cos(d2) * cos);
        fArr[i7 + 1] = (float) (Math.sin(d) * 1.899999976158142d);
        fArr[i7 + 2] = (float) sin;
        fArr2[i8] = ((float) (1.0d - ((d2 / 3.141592653589793d) * 2.0d))) / 4.0f;
        fArr2[i8 + 1] = getNextTexturePoint(d);
    }

    public void draw() {
        int[] iArr = this.mTextureDataHandle;
        if (iArr != null && iArr[0] != 0) {
            GLES20.glEnable(2929);
            GLES20.glDepthFunc(ASVLOFFSCREEN.ASVL_PAF_RGB24_B8G8R8);
            Matrix.frustumM(this.mProjectionMatrix, 0, this.mFrustumLeft, this.mFrustumRight, this.mFrustumBottom, this.mFrustumTop, this.mCurrentFov, 10.0f);
            super.draw();
            GLES20.glDisable(2929);
        }
    }

    public abstract float getNextTexturePoint(double d);

    public void reset(Object... objArr) {
        IGallery360Viewer.DefaultPlaybackDirection defaultPlaybackDirection;
        if (objArr != null && objArr.length > 0) {
            IGallery360Viewer.DefaultPlaybackDirection defaultPlaybackDirection2 = objArr[0];
            if (defaultPlaybackDirection2 instanceof IGallery360Viewer.DefaultPlaybackDirection) {
                defaultPlaybackDirection = defaultPlaybackDirection2;
            } else {
                defaultPlaybackDirection = null;
            }
            reset(defaultPlaybackDirection, -3.1415927f);
        }
    }

    public /* bridge */ /* synthetic */ void setRendererRequester(RenderRequestListener renderRequestListener) {
        super.setRendererRequester(renderRequestListener);
    }

    public /* bridge */ /* synthetic */ void setScreenSize(int i2, int i7) {
        super.setScreenSize(i2, i7);
    }

    public /* bridge */ /* synthetic */ void setScroll(float f, float f5) {
        super.setScroll(f, f5);
    }

    public /* bridge */ /* synthetic */ void setSensorScroll(float f, float f5) {
        super.setSensorScroll(f, f5);
    }

    public /* bridge */ /* synthetic */ void setStatusHandler(StatusHandler statusHandler) {
        super.setStatusHandler(statusHandler);
    }

    public /* bridge */ /* synthetic */ void setTextureManager(TextureManager textureManager) {
        super.setTextureManager(textureManager);
    }

    public /* bridge */ /* synthetic */ void setZoom(float f) {
        super.setZoom(f);
    }
}
