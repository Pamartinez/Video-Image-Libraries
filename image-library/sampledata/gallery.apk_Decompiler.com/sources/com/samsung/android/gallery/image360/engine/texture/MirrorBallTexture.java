package com.samsung.android.gallery.image360.engine.texture;

import android.opengl.Matrix;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MirrorBallTexture extends AbstractSphereTexture {
    public MirrorBallTexture() {
        this.MIN_FOV = 0.45f;
        this.MAX_FOV = 5.0f;
        this.INITIAL_GL_DEPTH = -5.1f;
    }

    public void draw() {
        int[] iArr = this.mTextureDataHandle;
        if (iArr != null && iArr[0] != 0) {
            Matrix.setLookAtM(this.mViewMatrix, 0, 0.0f, 0.0f, -this.mCurrentFov, 0.0f, 0.0f, -10.0f, 0.0f, 1.0f, 0.0f);
            Matrix.frustumM(this.mProjectionMatrix, 0, this.mFrustumLeft, this.mFrustumRight, this.mFrustumBottom, this.mFrustumTop, 1.0f, 10.0f);
            super.draw();
        }
    }

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

    public void updateGlAttributes() {
        super.updateGlAttributes("uniform mat4 u_MVPMatrix; \nattribute vec4 a_Position;     \nattribute vec2 a_TexCoordinate;\nvarying vec2 v_TexCoordinate;  \nuniform float u_ThetaX;  \nuniform float u_ThetaY;  \nvoid main()                    \n{                              \nvec4 rotationMatY = a_Position;\nvec4 rotationMatYX = a_Position;\nrotationMatY.x = a_Position.x * cos(u_ThetaY) - a_Position.z * sin(u_ThetaY); \nrotationMatY.z = a_Position.x * sin(u_ThetaY) + a_Position.z * cos(u_ThetaY); \nrotationMatY.y = a_Position.y; \nrotationMatYX.x = rotationMatY.x; \nrotationMatYX.y = rotationMatY.y * cos(u_ThetaX) - rotationMatY.z * sin(u_ThetaX); \nrotationMatYX.z = rotationMatY.y * sin(u_ThetaX) + rotationMatY.z * cos(u_ThetaX); \nv_TexCoordinate = a_TexCoordinate;\n   gl_Position = u_MVPMatrix   \n               * rotationMatYX;   \n}                              \n");
    }
}
