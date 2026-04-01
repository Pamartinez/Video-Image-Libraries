package com.samsung.android.gallery.image360.engine.texture;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DualTexture extends AbstractTexture {
    private float mAnimationBottom;
    private float mAnimationLeft;
    private float mAnimationRight;
    private float mAnimationTop;
    private IGallery360Viewer.DefaultPlaybackDirection mPlaybackDir;
    private final FloatBuffer mPositionsBuffer;
    private float mScrollFactor;
    private float mScrollX;
    private float mScrollY;
    private float mSensorScrollX;
    private float mSensorScrollY;
    private float mTextureBottomCoordinate = 0.0f;
    private final float[] mTextureCoordinatesBottom;
    private final FloatBuffer mTextureCoordinatesBufferBottom;
    private final FloatBuffer mTextureCoordinatesBufferTop;
    private final float[] mTextureCoordinatesTop;
    private float mTextureLeftCoordinate = 0.25f;
    private float mTextureRightCoordinate = 0.75f;
    private float mTextureTopCoordinate = 1.0f;
    private float mTranslateLandscape = -1.0f;
    private float mTranslatePortrait = -1.0f;
    private volatile boolean mZoomPending;
    private float mZoomRatio;

    public DualTexture() {
        float[] fArr = {0.25f, 1.0f, 0.75f, 1.0f, 0.25f, 0.0f, 0.75f, 0.0f};
        this.mTextureCoordinatesTop = fArr;
        float[] fArr2 = {0.25f + 0.5f, 1.0f, 0.75f + 0.5f, 1.0f, 0.25f + 0.5f, 0.0f, 0.75f + 0.5f, 0.0f};
        this.mTextureCoordinatesBottom = fArr2;
        this.mPlaybackDir = IGallery360Viewer.DefaultPlaybackDirection.FRONT;
        this.mZoomRatio = 0.5f;
        this.mScrollFactor = 1.0f;
        this.mZoomPending = false;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(48).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mPositionsBuffer = asFloatBuffer;
        asFloatBuffer.put(new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f}).position(0);
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mTextureCoordinatesBufferTop = asFloatBuffer2;
        asFloatBuffer2.put(fArr).position(0);
        FloatBuffer asFloatBuffer3 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mTextureCoordinatesBufferBottom = asFloatBuffer3;
        asFloatBuffer3.put(fArr2).position(0);
    }

    private float adjustScrollBottom(float f) {
        int i2 = 5;
        while (true) {
            float[] fArr = this.mTextureCoordinatesTop;
            if (i2 >= fArr.length) {
                return f;
            }
            float f5 = fArr[i2];
            if (f5 < f) {
                f = f5;
            }
            i2 += 2;
        }
    }

    private float adjustScrollTop(float f) {
        int i2 = 1;
        while (true) {
            float[] fArr = this.mTextureCoordinatesTop;
            if (i2 >= fArr.length / 2) {
                return f;
            }
            float f5 = fArr[i2];
            if (f5 - 1.0f > f) {
                f = f5 - 1.0f;
            }
            i2 += 2;
        }
    }

    private void drawDualFace(float f, float f5, FloatBuffer floatBuffer) {
        setCommonDrawAttributes();
        if (this.mHeight > this.mWidth) {
            Matrix.translateM(this.mModelMatrix, 0, 0.0f, 0.0f, -4.9f);
            Matrix.translateM(this.mModelMatrix, 0, 0.0f, f, 0.0f);
        } else {
            Matrix.translateM(this.mModelMatrix, 0, 0.0f, 0.0f, -4.06f);
            Matrix.translateM(this.mModelMatrix, 0, f5, 0.0f, 0.0f);
        }
        this.mPositionsBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mPositionHandle, 3, 5126, false, 0, this.mPositionsBuffer);
        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mTextureCoordinateHandle, 2, 5126, false, 0, floatBuffer);
        GLES20.glEnableVertexAttribArray(this.mTextureCoordinateHandle);
        Matrix.multiplyMM(this.mMVPMatrix, 0, this.mViewMatrix, 0, this.mModelMatrix, 0);
        GLES20.glUniformMatrix4fv(this.mMVMatrixHandle, 1, false, this.mMVPMatrix, 0);
        float[] fArr = this.mMVPMatrix;
        Matrix.multiplyMM(fArr, 0, this.mProjectionMatrix, 0, fArr, 0);
        GLES20.glUniformMatrix4fv(this.mMVPMatrixHandle, 1, false, this.mMVPMatrix, 0);
        GLES20.glDrawArrays(5, 0, 4);
    }

    private float isZoomNeeded(float f) {
        if (f != 0.0f && !this.mZoomPending) {
            float f5 = f / this.mMaxDisplacement;
            if (f5 == 0.0f || ((Float.compare(f5, 0.0f) > 0 && Float.compare(this.mZoomRatio, 0.1f) == 0) || (Float.compare(f5, 0.0f) < 0 && Float.compare(this.mZoomRatio, 0.5f) == 0))) {
                return 0.0f;
            }
            return f5;
        }
        return 0.0f;
    }

    private float scrollX(float f) {
        float[] fArr = this.mTextureCoordinatesTop;
        float f5 = fArr[2] - f;
        if (fArr[0] - f <= -0.75f) {
            return f - 1.0f;
        }
        if (f5 >= 1.75f) {
            return f + 1.0f;
        }
        return f;
    }

    private float scrollY(float f) {
        if (f > 0.0f) {
            float[] fArr = this.mTextureCoordinatesTop;
            if (fArr[5] - f < 0.0f || fArr[7] - f < 0.0f) {
                return adjustScrollBottom(f);
            }
        }
        if (f < 0.0f) {
            float[] fArr2 = this.mTextureCoordinatesTop;
            if (fArr2[1] - f > 1.0f || fArr2[3] - f > 1.0f) {
                return adjustScrollTop(f);
            }
        }
        return f;
    }

    private void updateAnimValues() {
        if (this.mRepeatCount > 0) {
            this.mTextureLeftCoordinate += this.mAnimationLeft;
            this.mTextureRightCoordinate += this.mAnimationRight;
            this.mTextureTopCoordinate += this.mAnimationTop;
            this.mTextureBottomCoordinate += this.mAnimationBottom;
            this.mZoomRatio += this.mAnimationZDir;
            this.mZoomPending = true;
        }
    }

    private void updateScroll(float[] fArr, float f, float f5) {
        fArr[0] = fArr[0] - f;
        fArr[2] = fArr[2] - f;
        fArr[4] = fArr[4] - f;
        fArr[6] = fArr[6] - f;
        fArr[1] = fArr[1] - f5;
        fArr[3] = fArr[3] - f5;
        fArr[5] = fArr[5] - f5;
        fArr[7] = fArr[7] - f5;
    }

    private void updateTextureCoordinates() {
        DualTexture dualTexture;
        if (this.mZoomPending) {
            dualTexture = this;
            dualTexture.updateZoom(this.mTextureCoordinatesTop, this.mTextureLeftCoordinate, this.mTextureTopCoordinate, this.mTextureRightCoordinate, this.mTextureBottomCoordinate);
            dualTexture.updateZoom(dualTexture.mTextureCoordinatesBottom, dualTexture.mTextureLeftCoordinate + 0.5f, dualTexture.mTextureTopCoordinate, dualTexture.mTextureRightCoordinate + 0.5f, dualTexture.mTextureBottomCoordinate);
            dualTexture.mZoomPending = false;
        } else {
            dualTexture = this;
        }
        dualTexture.updateScroll(dualTexture.mTextureCoordinatesTop, dualTexture.mScrollX + dualTexture.mSensorScrollX, dualTexture.mScrollY + dualTexture.mSensorScrollY);
        dualTexture.mTextureCoordinatesBufferTop.clear();
        dualTexture.mTextureCoordinatesBufferTop.put(dualTexture.mTextureCoordinatesTop).position(0);
        dualTexture.updateScroll(dualTexture.mTextureCoordinatesBottom, dualTexture.mScrollX + dualTexture.mSensorScrollX, dualTexture.mScrollY + dualTexture.mSensorScrollY);
        dualTexture.mTextureCoordinatesBufferBottom.clear();
        dualTexture.mTextureCoordinatesBufferBottom.put(dualTexture.mTextureCoordinatesBottom).position(0);
        dualTexture.mScrollX = 0.0f;
        dualTexture.mScrollY = 0.0f;
        dualTexture.mSensorScrollX = 0.0f;
        dualTexture.mSensorScrollY = 0.0f;
    }

    private void updateZoom(float[] fArr, float f, float f5, float f8, float f10) {
        fArr[0] = f;
        fArr[1] = f5;
        fArr[2] = f8;
        fArr[3] = f5;
        fArr[4] = f;
        fArr[5] = f10;
        fArr[6] = f8;
        fArr[7] = f10;
    }

    private void zoomHorizontally(float f) {
        this.mTextureRightCoordinate += f;
        this.mTextureLeftCoordinate -= f;
    }

    private void zoomVertically(float f) {
        float f5 = this.mTextureTopCoordinate;
        float f8 = this.mTextureBottomCoordinate;
        if (1.0f - f5 >= f8) {
            if (f8 >= f) {
                this.mTextureTopCoordinate = f5 + f;
                this.mTextureBottomCoordinate = f8 - f;
                return;
            }
            this.mTextureTopCoordinate = (f - f8) + f5 + f;
            this.mTextureBottomCoordinate = 0.0f;
        } else if (1.0f - f5 >= f) {
            this.mTextureTopCoordinate = f5 + f;
            this.mTextureBottomCoordinate = f8 - f;
        } else {
            this.mTextureBottomCoordinate = (f8 - f) - (f - (1.0f - f5));
            this.mTextureTopCoordinate = 1.0f;
        }
    }

    public void draw() {
        int[] iArr = this.mTextureDataHandle;
        if (iArr != null && iArr[0] != 0) {
            float[] fArr = this.mProjectionMatrix;
            float f = this.mScreenSizeRatio;
            Matrix.frustumM(fArr, 0, -f, f, -1.0f, 1.0f, 1.0f, 10.0f);
            getCommonDrawHandles();
            updateTextureCoordinates();
            drawDualFace(this.mTranslatePortrait, -this.mTranslateLandscape, this.mTextureCoordinatesBufferTop);
            drawDualFace(-this.mTranslatePortrait, this.mTranslateLandscape, this.mTextureCoordinatesBufferBottom);
            if (this.mAnimate) {
                updateAnimValues();
                doAnimation();
            }
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
            if (!(defaultPlaybackDirection == null || this.mPlaybackDir == defaultPlaybackDirection)) {
                this.mPlaybackDir = defaultPlaybackDirection;
                if (defaultPlaybackDirection == IGallery360Viewer.DefaultPlaybackDirection.REAR) {
                    this.mTranslatePortrait = 1.0f;
                    this.mTranslateLandscape = 1.0f;
                } else {
                    this.mTranslatePortrait = -1.0f;
                    this.mTranslateLandscape = -1.0f;
                }
            }
            float[] fArr = this.mTextureCoordinatesTop;
            this.mTextureLeftCoordinate = fArr[0];
            this.mTextureTopCoordinate = fArr[1];
            this.mTextureRightCoordinate = fArr[2];
            this.mTextureBottomCoordinate = fArr[5];
            if (Float.compare(this.mZoomRatio, 0.5f) != 0 || Float.compare(this.mTextureLeftCoordinate, 0.25f) != 0) {
                float f = this.mTextureLeftCoordinate;
                if (0.25f - f > 0.5f) {
                    this.mTextureLeftCoordinate = f + 1.0f;
                    this.mTextureRightCoordinate += 1.0f;
                } else {
                    float f5 = this.mTextureRightCoordinate;
                    if (f5 - 0.75f > 0.5f) {
                        this.mTextureLeftCoordinate = f - 1.0f;
                        this.mTextureRightCoordinate = f5 - 1.0f;
                    }
                }
                this.mAnimationLeft = (0.25f - this.mTextureLeftCoordinate) / 8.0f;
                this.mAnimationRight = (0.75f - this.mTextureRightCoordinate) / 8.0f;
                this.mAnimationTop = (1.0f - this.mTextureTopCoordinate) / 8.0f;
                this.mAnimationBottom = (0.0f - this.mTextureBottomCoordinate) / 8.0f;
                this.mAnimationZDir = (0.5f - this.mZoomRatio) / 8.0f;
                setAnimValues();
            }
        }
    }

    public /* bridge */ /* synthetic */ void setRendererRequester(RenderRequestListener renderRequestListener) {
        super.setRendererRequester(renderRequestListener);
    }

    public void setScreenSize(int i2, int i7) {
        super.setScreenSize(i2, i7);
        int i8 = this.mHeight;
        int i10 = this.mWidth;
        if (i8 > i10) {
            this.mScrollFactor = ((float) i8) / 2.0f;
        } else {
            this.mScrollFactor = ((float) i10) / 2.0f;
        }
    }

    public void setScroll(float f, float f5) {
        if (f != 0.0f || f5 != 0.0f) {
            float f8 = this.mZoomRatio;
            float f10 = this.mScrollFactor;
            this.mScrollX = scrollX((f * f8) / f10);
            this.mScrollY = scrollY(((f8 * 2.0f) * f5) / f10);
        }
    }

    public void setSensorScroll(float f, float f5) {
        if (f != 0.0f || f5 != 0.0f) {
            this.mSensorScrollX = scrollX((float) (((double) f5) / 6.283185307179586d));
            this.mSensorScrollY = scrollY((float) (((double) f) / 3.141592653589793d));
        }
    }

    public /* bridge */ /* synthetic */ void setStatusHandler(StatusHandler statusHandler) {
        super.setStatusHandler(statusHandler);
    }

    public /* bridge */ /* synthetic */ void setTextureManager(TextureManager textureManager) {
        super.setTextureManager(textureManager);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0031, code lost:
        if (r10 < 0.1f) goto L_0x002a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setZoom(float r10) {
        /*
            r9 = this;
            float r10 = r9.isZoomNeeded(r10)
            r0 = 0
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 != 0) goto L_0x000a
            return
        L_0x000a:
            float[] r1 = r9.mTextureCoordinatesTop
            r2 = 0
            r2 = r1[r2]
            r9.mTextureLeftCoordinate = r2
            r3 = 1
            r4 = r1[r3]
            r9.mTextureTopCoordinate = r4
            r5 = 2
            r5 = r1[r5]
            r9.mTextureRightCoordinate = r5
            r6 = 5
            r1 = r1[r6]
            r9.mTextureBottomCoordinate = r1
            float r6 = r9.mZoomRatio
            float r10 = r6 - r10
            r7 = 1056964608(0x3f000000, float:0.5)
            int r8 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r8 <= 0) goto L_0x002c
        L_0x002a:
            r10 = r7
            goto L_0x0034
        L_0x002c:
            r7 = 1036831949(0x3dcccccd, float:0.1)
            int r8 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r8 >= 0) goto L_0x0034
            goto L_0x002a
        L_0x0034:
            float r6 = r6 - r10
            r7 = 1073741824(0x40000000, float:2.0)
            float r6 = r6 / r7
            int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x004a
            float r5 = r5 - r6
            r9.mTextureRightCoordinate = r5
            float r2 = r2 + r6
            r9.mTextureLeftCoordinate = r2
            float r6 = r6 * r7
            float r4 = r4 - r6
            r9.mTextureTopCoordinate = r4
            float r1 = r1 + r6
            r9.mTextureBottomCoordinate = r1
            goto L_0x0054
        L_0x004a:
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r6 = r6 * r0
            r9.zoomHorizontally(r6)
            float r6 = r6 * r7
            r9.zoomVertically(r6)
        L_0x0054:
            r9.mZoomRatio = r10
            r9.mZoomPending = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.image360.engine.texture.DualTexture.setZoom(float):void");
    }

    public void updateGlAttributes() {
        Matrix.setLookAtM(this.mViewMatrix, 0, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -100.0f, 0.0f, 1.0f, 0.0f);
        updateGlAttributes("uniform mat4 u_MVPMatrix; \nattribute vec4 a_Position;     \nattribute vec2 a_TexCoordinate;\nvarying vec2 v_TexCoordinate;  \nvoid main()                    \n{                              \nv_TexCoordinate = a_TexCoordinate;\n   gl_Position = u_MVPMatrix   \n               * a_Position;   \n}                              \n");
    }
}
