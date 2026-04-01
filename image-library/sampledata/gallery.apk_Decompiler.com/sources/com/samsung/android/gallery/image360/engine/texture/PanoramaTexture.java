package com.samsung.android.gallery.image360.engine.texture;

import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.Matrix;
import i.C0212a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PanoramaTexture extends AbstractTexture {
    private final RectF mFrustumRect = new RectF(-1.0f, 1.0f, 1.0f, -1.0f);
    private final FloatBuffer mPositions;
    private volatile float mRotateX;
    private volatile float mRotateY;
    private final FloatBuffer mTextureCoordinates;
    private float mZoomLevel = 1.0f;

    public PanoramaTexture() {
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(48).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mPositions = asFloatBuffer;
        asFloatBuffer.put(new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f}).position(0);
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mTextureCoordinates = asFloatBuffer2;
        asFloatBuffer2.put(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f}).position(0);
    }

    private void adjustRotatePosition(RectF rectF) {
        if (this.mRotateY > 0.0f) {
            float f = rectF.top;
            if (f < 1.0f) {
                float f5 = f + this.mRotateY;
                rectF.top = f5;
                if (f5 <= 1.0f || rectF.bottom <= -1.0f) {
                    rectF.bottom += this.mRotateY;
                    return;
                }
                rectF.bottom = (1.0f - (f5 - this.mRotateY)) + rectF.bottom;
                rectF.top = 1.0f;
                return;
            }
        }
        if (this.mRotateY < 0.0f) {
            float f8 = rectF.bottom;
            if (f8 > -1.0f) {
                float f10 = f8 + this.mRotateY;
                rectF.bottom = f10;
                if (f10 >= -1.0f || rectF.top >= 1.0f) {
                    rectF.top += this.mRotateY;
                    return;
                }
                rectF.top = (-1.0f - (f10 - this.mRotateY)) + rectF.top;
                rectF.bottom = -1.0f;
            }
        }
    }

    private void adjustZoomPosition(RectF rectF, float f) {
        float f5 = this.mScreenSizeRatio;
        if (f5 < f) {
            float f8 = f / f5;
            float f10 = this.mZoomLevel;
            rectF.bottom = C0212a.a(f10, 1.0f, f8, -f8);
            rectF.top = f8 - ((f10 - 1.0f) * f8);
            rectF.left = (f10 - 1.0f) + rectF.left;
            rectF.right -= f10 - 1.0f;
            return;
        }
        float f11 = f5 / f;
        float f12 = this.mZoomLevel;
        rectF.left = C0212a.a(f12, 1.0f, f11, -f11);
        rectF.right = f11 - ((f12 - 1.0f) * f11);
        rectF.top -= f12 - 1.0f;
        rectF.bottom = (f12 - 1.0f) + rectF.bottom;
    }

    private void drawPanorama() {
        this.mPositions.position(0);
        GLES20.glVertexAttribPointer(this.mPositionHandle, 3, 5126, false, 0, this.mPositions);
        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        this.mTextureCoordinates.position(0);
        GLES20.glVertexAttribPointer(this.mTextureCoordinateHandle, 2, 5126, false, 0, this.mTextureCoordinates);
        GLES20.glEnableVertexAttribArray(this.mTextureCoordinateHandle);
        Matrix.multiplyMM(this.mMVPMatrix, 0, this.mViewMatrix, 0, this.mModelMatrix, 0);
        GLES20.glUniformMatrix4fv(this.mMVMatrixHandle, 1, false, this.mMVPMatrix, 0);
        float[] fArr = this.mMVPMatrix;
        Matrix.multiplyMM(fArr, 0, this.mProjectionMatrix, 0, fArr, 0);
        GLES20.glUniformMatrix4fv(this.mMVPMatrixHandle, 1, false, this.mMVPMatrix, 0);
        GLES20.glDrawArrays(5, 0, 4);
    }

    private RectF getFrustumRect() {
        RectF rectF = this.mFrustumRect;
        rectF.left = -1.0f;
        float f = 1.0f;
        rectF.top = 1.0f;
        rectF.right = 1.0f;
        rectF.bottom = -1.0f;
        TextureManager textureManager = this.mTextureManager;
        if (textureManager != null) {
            float bitmapHeight = (float) textureManager.getBitmapHeight();
            float bitmapWidth = (float) this.mTextureManager.getBitmapWidth();
            if (bitmapHeight > 0.0f && bitmapWidth > 0.0f) {
                f = bitmapWidth / bitmapHeight;
            }
        }
        adjustZoomPosition(this.mFrustumRect, f);
        RectF rectF2 = this.mFrustumRect;
        if (rectF2.top - rectF2.bottom >= 2.0f) {
            this.mRotateY = 0.0f;
        }
        adjustRotatePosition(this.mFrustumRect);
        return this.mFrustumRect;
    }

    private void updateAnimValues() {
        if (this.mRepeatCount > 0) {
            this.mRotateX += this.mAnimationXDir;
            this.mRotateY += this.mAnimationYDir;
            this.mZoomLevel += this.mAnimationZDir;
        }
    }

    public void draw() {
        int[] iArr = this.mTextureDataHandle;
        if (iArr != null && iArr[0] != 0) {
            RectF frustumRect = getFrustumRect();
            Matrix.frustumM(this.mProjectionMatrix, 0, frustumRect.left, frustumRect.right, frustumRect.bottom, frustumRect.top, 0.1f, 10.0f);
            getCommonDrawHandles();
            int glGetUniformLocation = GLES20.glGetUniformLocation(this.mProgramHandle, "u_ThetaX");
            setCommonDrawAttributes();
            GLES20.glUniform1f(glGetUniformLocation, this.mRotateX);
            drawPanorama();
            if (this.mAnimate) {
                updateAnimValues();
                doAnimation();
            }
        }
    }

    public void reset(Object... objArr) {
        if (this.mRotateX != 0.0f || this.mRotateY != 0.0f || this.mZoomLevel != 1.0f) {
            this.mAnimationXDir = (0.0f - this.mRotateX) / 8.0f;
            this.mAnimationYDir = (0.0f - this.mRotateY) / 8.0f;
            this.mAnimationZDir = (1.0f - this.mZoomLevel) / 8.0f;
            setAnimValues();
        }
    }

    public /* bridge */ /* synthetic */ void setRendererRequester(RenderRequestListener renderRequestListener) {
        super.setRendererRequester(renderRequestListener);
    }

    public /* bridge */ /* synthetic */ void setScreenSize(int i2, int i7) {
        super.setScreenSize(i2, i7);
    }

    public void setScroll(float f, float f5) {
        float f8;
        this.mRotateX = (f / (((float) this.mWidth) / (2.0f - this.mZoomLevel))) + this.mRotateX;
        if (this.mRotateX > 1.0f) {
            this.mRotateX -= 1.0f;
        } else if (this.mRotateX < -1.0f) {
            this.mRotateX += 1.0f;
        }
        RectF frustumRect = getFrustumRect();
        int i2 = this.mHeight;
        if (i2 > this.mWidth) {
            f8 = f5 / ((float) i2);
        } else {
            float f10 = this.mZoomLevel;
            f8 = ((3.0f - f10) * f5) / (((this.mMaxDisplacement * f10) * f10) * f10);
        }
        if (frustumRect.top >= 1.0f && f8 > 0.0f) {
            return;
        }
        if (frustumRect.bottom > -1.0f || f8 >= 0.0f) {
            this.mRotateY += f8;
        }
    }

    public void setSensorScroll(float f, float f5) {
        this.mRotateX = (float) ((((double) f5) / 6.283185307179586d) + ((double) this.mRotateX));
        if (this.mRotateX > 1.0f) {
            this.mRotateX -= 1.0f;
        } else if (this.mRotateX < -1.0f) {
            this.mRotateX += 1.0f;
        }
        RectF frustumRect = getFrustumRect();
        float f8 = (float) (((double) f) / 3.141592653589793d);
        if (frustumRect.top >= 1.0f && f8 > 0.0f) {
            return;
        }
        if (frustumRect.bottom > -1.0f || f8 >= 0.0f) {
            this.mRotateY += f8;
        }
    }

    public /* bridge */ /* synthetic */ void setStatusHandler(StatusHandler statusHandler) {
        super.setStatusHandler(statusHandler);
    }

    public /* bridge */ /* synthetic */ void setTextureManager(TextureManager textureManager) {
        super.setTextureManager(textureManager);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        if (r3 < 1.0f) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setZoom(float r3) {
        /*
            r2 = this;
            float r0 = r2.mZoomLevel
            float r1 = r2.mMaxDisplacement
            float r3 = r3 / r1
            r1 = 992204554(0x3b23d70a, float:0.0025)
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 >= 0) goto L_0x0014
            r1 = -1155279094(0xffffffffbb23d70a, float:-0.0025)
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x0014
            return
        L_0x0014:
            float r3 = r3 * r0
            float r3 = r3 + r0
            r0 = 1072483533(0x3feccccd, float:1.85)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L_0x001f
        L_0x001d:
            r3 = r0
            goto L_0x0026
        L_0x001f:
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x0026
            goto L_0x001d
        L_0x0026:
            r2.mZoomLevel = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.image360.engine.texture.PanoramaTexture.setZoom(float):void");
    }

    public void updateGlAttributes() {
        GLES20.glEnable(2884);
        Matrix.setLookAtM(this.mViewMatrix, 0, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, -100.0f, 0.0f, 1.0f, 0.0f);
        updateGlAttributes("uniform mat4 u_MVPMatrix; \nattribute vec4 a_Position;     \nattribute vec2 a_TexCoordinate;\nvarying vec2 v_TexCoordinate;  \nuniform float u_ThetaX;  \nvoid main()                    \n{                              \nvec2 tempTex = a_TexCoordinate;\ntempTex.x = tempTex.x - u_ThetaX;\nv_TexCoordinate = tempTex;\n   gl_Position = u_MVPMatrix   \n               * a_Position;   \n}                              \n");
    }
}
