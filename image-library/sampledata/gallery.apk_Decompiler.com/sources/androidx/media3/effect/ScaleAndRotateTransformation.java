package androidx.media3.effect;

import android.graphics.Matrix;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Size;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScaleAndRotateTransformation implements MatrixTransformation {
    private Matrix adjustedTransformationMatrix;
    public final float rotationDegrees;
    public final float scaleX;
    public final float scaleY;
    private final Matrix transformationMatrix;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private float rotationDegrees = 0.0f;
        private float scaleX = 1.0f;
        private float scaleY = 1.0f;

        public ScaleAndRotateTransformation build() {
            return new ScaleAndRotateTransformation(this.scaleX, this.scaleY, this.rotationDegrees);
        }

        public Builder setRotationDegrees(float f) {
            float f5 = f % 360.0f;
            this.rotationDegrees = f5;
            if (f5 < 0.0f) {
                this.rotationDegrees = f5 + 360.0f;
            }
            return this;
        }
    }

    public Size configure(int i2, int i7) {
        boolean z;
        boolean z3;
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "inputWidth must be positive");
        if (i7 > 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument(z3, "inputHeight must be positive");
        this.adjustedTransformationMatrix = new Matrix(this.transformationMatrix);
        if (this.transformationMatrix.isIdentity()) {
            return new Size(i2, i7);
        }
        float f = (float) i2;
        float f5 = (float) i7;
        float f8 = f / f5;
        this.adjustedTransformationMatrix.preScale(f8, 1.0f);
        this.adjustedTransformationMatrix.postScale(1.0f / f8, 1.0f);
        float[][] fArr = {new float[]{-1.0f, -1.0f, 0.0f, 1.0f}, new float[]{-1.0f, 1.0f, 0.0f, 1.0f}, new float[]{1.0f, -1.0f, 0.0f, 1.0f}, new float[]{1.0f, 1.0f, 0.0f, 1.0f}};
        float f10 = Float.MIN_VALUE;
        float f11 = Float.MAX_VALUE;
        float f12 = Float.MAX_VALUE;
        float f13 = Float.MIN_VALUE;
        for (int i8 = 0; i8 < 4; i8++) {
            float[] fArr2 = fArr[i8];
            this.adjustedTransformationMatrix.mapPoints(fArr2);
            f11 = Math.min(f11, fArr2[0]);
            f10 = Math.max(f10, fArr2[0]);
            f12 = Math.min(f12, fArr2[1]);
            f13 = Math.max(f13, fArr2[1]);
        }
        float f14 = (f10 - f11) / 2.0f;
        float f15 = (f13 - f12) / 2.0f;
        this.adjustedTransformationMatrix.postScale(1.0f / f14, 1.0f / f15);
        return new Size(Math.round(f * f14), Math.round(f5 * f15));
    }

    public Matrix getMatrix(long j2) {
        return (Matrix) Assertions.checkStateNotNull(this.adjustedTransformationMatrix, "configure must be called first");
    }

    public boolean isNoOp(int i2, int i7) {
        Size configure = configure(i2, i7);
        if (((Matrix) Assertions.checkStateNotNull(this.adjustedTransformationMatrix)).isIdentity() && i2 == configure.getWidth() && i7 == configure.getHeight()) {
            return true;
        }
        return false;
    }

    private ScaleAndRotateTransformation(float f, float f5, float f8) {
        this.scaleX = f;
        this.scaleY = f5;
        this.rotationDegrees = f8;
        Matrix matrix = new Matrix();
        this.transformationMatrix = matrix;
        matrix.postScale(f, f5);
        matrix.postRotate(f8);
    }
}
