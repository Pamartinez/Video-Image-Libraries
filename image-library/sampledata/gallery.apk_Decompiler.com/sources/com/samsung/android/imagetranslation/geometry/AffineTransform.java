package com.samsung.android.imagetranslation.geometry;

import android.graphics.Matrix;
import android.graphics.PointF;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AffineTransform extends Matrix {
    static final double ZERO = 1.0E-10d;

    public AffineTransform(float f, float f5, float f8, float f10, float f11, float f12) {
        convert(f, f5, f8, f10, f11, f12);
    }

    public static Matrix getRotateInstance(double d) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToRotation(d);
        return affineTransform;
    }

    public static Matrix getTranslateInstance(float f, float f5) {
        Matrix matrix = new Matrix();
        matrix.setTranslate(f, f5);
        return matrix;
    }

    public void concatenate(Matrix matrix) {
        preConcat(matrix);
    }

    public void convert(float f, float f5, float f8, float f10, float f11, float f12) {
        setValues(new float[]{f, f5, f11, f8, f10, f12, 0.0f, 0.0f, 1.0f});
    }

    public boolean scale(double d, double d2) {
        return preScale((float) d, (float) d2);
    }

    public void setToIdentity() {
        reset();
    }

    public void setToRotation(double d) {
        float f;
        float f5;
        float f8;
        float f10;
        float sin = (float) Math.sin(d);
        float cos = (float) Math.cos(d);
        if (((double) Math.abs(cos)) < ZERO) {
            if (sin > 0.0f) {
                f10 = 1.0f;
            } else {
                f10 = -1.0f;
            }
            f = f10;
            f5 = 0.0f;
        } else if (((double) Math.abs(sin)) < ZERO) {
            if (cos > 0.0f) {
                f8 = 1.0f;
            } else {
                f8 = -1.0f;
            }
            f5 = f8;
            f = 0.0f;
        } else {
            f5 = cos;
            f = sin;
        }
        convert(f5, -f, f, f5, 0.0f, 0.0f);
    }

    public void setTransform(Matrix matrix) {
        set(matrix);
    }

    public PointF transform(PointF pointF, PointF pointF2) {
        if (pointF2 == null) {
            pointF2 = new PointF();
        }
        float f = pointF.x;
        float f5 = pointF.y;
        float[] fArr = new float[9];
        getValues(fArr);
        pointF2.set((fArr[1] * f5) + (fArr[0] * f) + fArr[2], (f5 * fArr[4]) + (f * fArr[3]) + fArr[5]);
        return pointF2;
    }

    public boolean translate(double d, double d2) {
        return preTranslate((float) d, (float) d2);
    }

    public AffineTransform(float[] fArr) {
        convert(fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5]);
    }

    public AffineTransform() {
    }
}
