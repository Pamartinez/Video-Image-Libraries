package com.samsung.android.gallery.module.story.transcode.renderer.render;

import android.graphics.PointF;
import android.opengl.GLES20;
import com.samsung.android.gallery.module.story.transcode.renderer.render.GLRenderTexture;
import com.samsung.android.ocr.MOCRLang;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RoundRenderObject extends RenderObject {
    private float mMaxX;
    private float mMaxY;
    private FloatBuffer mNormalizedBuffer;
    private final float[] mOutputSize;
    private final RectCorners mRoundness;

    public RoundRenderObject(GLRenderTexture.Builder builder) {
        super(builder);
        this.mRoundness = builder.roundness;
        this.mOutputSize = new float[]{0.0f, 0.0f, (float) builder.width, (float) builder.height};
    }

    private PointF applyMatrix(float[] fArr, float[] fArr2) {
        float[] fArr3 = new float[4];
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            int i7 = i2 + 1;
            float[] applyTransform = applyTransform(fArr2, fArr[i2], fArr[i7]);
            fArr3[i2] = applyTransform[0];
            fArr3[i7] = applyTransform[1];
        }
        return new PointF(Math.abs(fArr3[2] - fArr3[0]), Math.abs(fArr3[3] - fArr3[1]));
    }

    private float[] applyTransform(float[] fArr, float f, float f5) {
        return new float[]{(fArr[1] * f5) + (fArr[0] * f) + fArr[3], (fArr[5] * f5) + (fArr[4] * f) + fArr[7]};
    }

    private int getOrientation(float[] fArr) {
        float f = fArr[0];
        float f5 = fArr[1];
        if (Math.abs(f - 1.0f) < 1.0E-5f && Math.abs(f5) < 1.0E-5f) {
            return 0;
        }
        if (Math.abs(f) < 1.0E-5f && Math.abs(f5 - 1.0f) < 1.0E-5f) {
            return 90;
        }
        if (Math.abs(f + 1.0f) < 1.0E-5f && Math.abs(f5) < 1.0E-5f) {
            return MOCRLang.KHMER;
        }
        if (Math.abs(f) >= 1.0E-5f || Math.abs(f5 + 1.0f) >= 1.0E-5f) {
            return -1;
        }
        return 270;
    }

    private float[] rotateCoordinates(float[] fArr, int i2) {
        float[] fArr2 = new float[fArr.length];
        int[] iArr = i2 != 90 ? i2 != 180 ? i2 != 270 ? new int[]{0, 1, 2, 3, 4, 5, 6, 7} : new int[]{4, 5, 6, 7, 0, 1, 2, 3} : new int[]{6, 7, 4, 5, 2, 3, 0, 1} : new int[]{2, 3, 0, 1, 6, 7, 4, 5};
        for (int i7 = 0; i7 < iArr.length; i7++) {
            fArr2[i7] = fArr[iArr[i7]];
        }
        return fArr2;
    }

    private void setNormalizedReferenceCoordinate(float[] fArr, float f, float f5) {
        float f8;
        float f10;
        float[] fArr2;
        int i2 = (f > f5 ? 1 : (f == f5 ? 0 : -1));
        if (i2 > 0) {
            f8 = 1.0f;
        } else {
            f8 = f / f5;
        }
        this.mMaxX = f8;
        if (f5 > f) {
            f10 = 1.0f;
        } else {
            f10 = f5 / f;
        }
        this.mMaxY = f10;
        if (i2 > 0) {
            float f11 = f5 / f;
            fArr2 = new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, f11, 1.0f, f11};
        } else {
            float f12 = f / f5;
            fArr2 = new float[]{0.0f, 0.0f, f12, 0.0f, 0.0f, 1.0f, f12, 1.0f};
        }
        float[] rotateCoordinates = rotateCoordinates(fArr2, getOrientation(fArr));
        FloatBuffer put = ByteBuffer.allocateDirect(rotateCoordinates.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(rotateCoordinates);
        this.mNormalizedBuffer = put;
        put.position(0);
    }

    private void setRoundnessRatio(Shader shader, PointF pointF) {
        float min = Math.min(this.mMaxX, this.mMaxY);
        if (this.mRoundness.inPixel()) {
            float min2 = Math.min(pointF.x, pointF.y);
            int i2 = shader.mu_Roundness;
            RectCorners rectCorners = this.mRoundness;
            GLES20.glUniform4f(i2, (((float) rectCorners.leftTopPixel) / min2) * min, (((float) rectCorners.rightTopPixel) / min2) * min, (((float) rectCorners.leftBottomPixel) / min2) * min, (((float) rectCorners.rightBottomPixel) / min2) * min);
            return;
        }
        int i7 = shader.mu_Roundness;
        RectCorners rectCorners2 = this.mRoundness;
        GLES20.glUniform4f(i7, rectCorners2.leftTopRatio * min, rectCorners2.rightTopRatio * min, rectCorners2.rightBottomRatio * min, rectCorners2.leftBottomRatio * min);
    }

    private void setUniforms(Shader shader, float[] fArr) {
        PointF applyMatrix = applyMatrix(this.mOutputSize, fArr);
        setNormalizedReferenceCoordinate(fArr, applyMatrix.x, applyMatrix.y);
        setRoundnessRatio(shader, applyMatrix);
        GLES20.glUniform1f(shader.mu_MaxX, this.mMaxX);
        GLES20.glUniform1f(shader.mu_MaxY, this.mMaxY);
    }

    public void draw(Shader shader, float[] fArr) {
        setUniforms(shader, fArr);
        GLES20.glVertexAttribPointer(shader.ma_ReferencePositionHandle, 2, 5126, false, 0, this.mNormalizedBuffer);
        GLES20.glEnableVertexAttribArray(shader.ma_ReferencePositionHandle);
        checkGLError("[RoundRenderObject] glEnableVertexAttribArray ma_ReferencePositionHandle");
        super.draw(shader, fArr);
    }

    public void release() {
        super.release();
        this.mNormalizedBuffer = null;
    }
}
