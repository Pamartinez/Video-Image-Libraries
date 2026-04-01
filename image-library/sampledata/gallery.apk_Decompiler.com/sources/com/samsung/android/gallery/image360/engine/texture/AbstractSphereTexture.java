package com.samsung.android.gallery.image360.engine.texture;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbstractSphereTexture extends AbstractTexture {
    private static final List<float[]> mTexture = new ArrayList();
    private static final List<FloatBuffer> mTextureBuffer = new ArrayList();
    private static final List<FloatBuffer> mVertexBuffer = new ArrayList();
    private static final List<float[]> mVerticesOrig = new ArrayList();
    float INITIAL_GL_DEPTH = -6.1f;
    float MAX_FOV = 6.0f;
    private final float MAX_X_SCROLL = getMaxXScroll();
    float MIN_FOV = 1.2f;
    private final float MIN_X_SCROLL = getMinXScroll();
    float mCurrentFov = -10000.0f;
    float mFrustumBottom = -1.0f;
    float mFrustumLeft = -1.0f;
    float mFrustumRight = 1.0f;
    float mFrustumTop = 1.0f;
    private float mThetaX = -10000.0f;
    private float mThetaY = -10000.0f;

    private void doScroll(float f, float f5) {
        float f8 = this.mThetaX - f;
        this.mThetaX = f8;
        float f10 = this.MAX_X_SCROLL;
        if (f8 > f10) {
            this.mThetaX = f10;
        } else {
            float f11 = this.MIN_X_SCROLL;
            if (f8 < f11) {
                this.mThetaX = f11;
            }
        }
        float f12 = this.mThetaY - f5;
        this.mThetaY = f12;
        if (f12 < 0.0f) {
            this.mThetaY = f12 + 6.2831855f;
        } else if (f12 > 6.2831855f) {
            this.mThetaY = f12 - 6.2831855f;
        }
    }

    private void drawSphereStrip(int i2) {
        try {
            List<FloatBuffer> list = mVertexBuffer;
            list.get(i2).position(0);
            GLES20.glVertexAttribPointer(this.mPositionHandle, 3, 5126, false, 0, list.get(i2));
            GLES20.glEnableVertexAttribArray(this.mPositionHandle);
            List<FloatBuffer> list2 = mTextureBuffer;
            list2.get(i2).position(0);
            GLES20.glVertexAttribPointer(this.mTextureCoordinateHandle, 2, 5126, false, 0, list2.get(i2));
            GLES20.glEnableVertexAttribArray(this.mTextureCoordinateHandle);
            Matrix.multiplyMM(this.mMVPMatrix, 0, this.mViewMatrix, 0, this.mModelMatrix, 0);
            GLES20.glUniformMatrix4fv(this.mMVMatrixHandle, 1, false, this.mMVPMatrix, 0);
            float[] fArr = this.mMVPMatrix;
            Matrix.multiplyMM(fArr, 0, this.mProjectionMatrix, 0, fArr, 0);
            GLES20.glUniformMatrix4fv(this.mMVPMatrixHandle, 1, false, this.mMVPMatrix, 0);
            GLES20.glDrawArrays(5, 0, mVerticesOrig.get(i2).length / 3);
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NullPointerException e) {
            Throwable th = e;
            String str = this.TAG;
            Log.e(str, "failed to draw sphere strip, e=" + th.getMessage());
        }
    }

    private void updateAnimValues() {
        if (this.mRepeatCount > 0) {
            this.mThetaX += this.mAnimationXDir;
            this.mThetaY += this.mAnimationYDir;
            this.mCurrentFov += this.mAnimationZDir;
        }
    }

    public void clearBuffers() {
        mTexture.clear();
        mTextureBuffer.clear();
        mVerticesOrig.clear();
        mVertexBuffer.clear();
    }

    public void draw() {
        getCommonDrawHandles();
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.mProgramHandle, "u_ThetaX");
        int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.mProgramHandle, "u_ThetaY");
        setCommonDrawAttributes();
        Matrix.translateM(this.mModelMatrix, 0, 0.0f, 0.0f, this.INITIAL_GL_DEPTH);
        GLES20.glUniform1f(glGetUniformLocation, this.mThetaX);
        GLES20.glUniform1f(glGetUniformLocation2, this.mThetaY);
        for (int i2 = 0; i2 < 160; i2++) {
            drawSphereStrip(i2);
        }
        if (this.mAnimate) {
            updateAnimValues();
            doAnimation();
        }
    }

    public float getMaxXScroll() {
        return -1.5707964f;
    }

    public float getMinXScroll() {
        return -4.712389f;
    }

    public void reset(IGallery360Viewer.DefaultPlaybackDirection defaultPlaybackDirection, float f) {
        float f5;
        if (defaultPlaybackDirection == IGallery360Viewer.DefaultPlaybackDirection.REAR) {
            f5 = 0.0f;
        } else {
            f5 = 3.1415927f;
        }
        float f8 = this.mCurrentFov;
        if (f8 != -10000.0f) {
            float f10 = this.mThetaX;
            if (f10 != -10000.0f) {
                float f11 = this.mThetaY;
                if (f11 != -10000.0f) {
                    if (((double) (f11 - f5)) > 3.141592653589793d) {
                        this.mThetaY = f11 - 6.2831855f;
                    }
                    this.mAnimationXDir = (f - f10) / 8.0f;
                    this.mAnimationYDir = (f5 - this.mThetaY) / 8.0f;
                    this.mAnimationZDir = (this.MIN_FOV - f8) / 8.0f;
                    setAnimValues();
                    return;
                }
            }
        }
        this.mCurrentFov = this.MIN_FOV;
        this.mThetaX = f;
        this.mThetaY = f5;
    }

    public void setScreenSize(int i2, int i7) {
        super.setScreenSize(i2, i7);
        if (i2 < i7) {
            float f = ((float) i2) / ((float) i7);
            this.mFrustumLeft = -f;
            this.mFrustumRight = f;
            this.mFrustumTop = 1.0f;
            this.mFrustumBottom = -1.0f;
            return;
        }
        float f5 = ((float) i7) / ((float) i2);
        this.mFrustumLeft = -1.0f;
        this.mFrustumRight = 1.0f;
        this.mFrustumTop = f5;
        this.mFrustumBottom = -f5;
    }

    public void setScroll(float f, float f5) {
        float f8 = this.mCurrentFov;
        float f10 = this.MIN_FOV;
        float f11 = ((-2.0f / (this.MAX_FOV - f10)) * (f8 - f10)) + 3.5f;
        doScroll((f5 * f11) / ((float) this.mHeight), (f11 * f) / ((float) this.mWidth));
    }

    public void setSensorScroll(float f, float f5) {
        doScroll(f, f5);
    }

    public void setZoom(float f) {
        float f5 = this.mCurrentFov;
        float f8 = this.MAX_FOV;
        float f10 = this.MIN_FOV;
        float f11 = (((f8 - f10) * f) / this.mMaxDisplacement) + f5;
        if (f11 > f8) {
            this.mCurrentFov = f8;
        } else if (f11 < f10) {
            this.mCurrentFov = f10;
        } else {
            this.mCurrentFov = f11;
        }
    }

    public void updatePosBuffers(float[] fArr, float[] fArr2, int i2) {
        List<float[]> list = mVerticesOrig;
        list.add(fArr);
        List<float[]> list2 = mTexture;
        list2.add(fArr2);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(18432);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(list.get(i2));
        asFloatBuffer.position(0);
        mVertexBuffer.add(asFloatBuffer);
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(12288);
        allocateDirect2.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer2 = allocateDirect2.asFloatBuffer();
        asFloatBuffer2.put(list2.get(i2));
        asFloatBuffer2.position(0);
        mTextureBuffer.add(asFloatBuffer2);
    }
}
