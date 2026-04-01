package androidx.media3.effect;

import android.graphics.Matrix;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Size;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Presentation implements MatrixTransformation {
    private final int layout;
    private float outputHeight;
    private float outputWidth;
    private final boolean preservePortraitWhenApplicable;
    private float requestedAspectRatio;
    private final int requestedHeightPixels;
    private final int requestedWidthPixels;
    private final int textureMinFilter;
    private Matrix transformationMatrix;

    private Presentation(int i2, int i7, float f, int i8, int i10, boolean z) {
        boolean z3;
        if (f == -1.0f || i2 == -1) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument(z3, "width and aspect ratio should not both be set");
        this.requestedWidthPixels = i2;
        this.requestedHeightPixels = i7;
        this.requestedAspectRatio = f;
        this.layout = i8;
        this.textureMinFilter = i10;
        this.preservePortraitWhenApplicable = z;
        this.outputWidth = -1.0f;
        this.outputHeight = -1.0f;
        this.transformationMatrix = new Matrix();
    }

    private void applyAspectRatio() {
        float f = this.outputWidth;
        float f5 = this.outputHeight;
        float f8 = f / f5;
        int i2 = this.layout;
        if (i2 == 0) {
            float f10 = this.requestedAspectRatio;
            if (f10 > f8) {
                this.transformationMatrix.setScale(f8 / f10, 1.0f);
                this.outputWidth = this.outputHeight * this.requestedAspectRatio;
                return;
            }
            this.transformationMatrix.setScale(1.0f, f10 / f8);
            this.outputHeight = this.outputWidth / this.requestedAspectRatio;
        } else if (i2 == 1) {
            float f11 = this.requestedAspectRatio;
            if (f11 > f8) {
                this.transformationMatrix.setScale(1.0f, f11 / f8);
                this.outputHeight = this.outputWidth / this.requestedAspectRatio;
                return;
            }
            this.transformationMatrix.setScale(f8 / f11, 1.0f);
            this.outputWidth = this.outputHeight * this.requestedAspectRatio;
        } else if (i2 == 2) {
            float f12 = this.requestedAspectRatio;
            if (f12 > f8) {
                this.outputWidth = f5 * f12;
            } else {
                this.outputHeight = f / f12;
            }
        }
    }

    private static void checkLayout(int i2) {
        boolean z = true;
        if (!(i2 == 0 || i2 == 1 || i2 == 2)) {
            z = false;
        }
        Assertions.checkArgument(z, "invalid layout " + i2);
    }

    public static Presentation createForWidthAndHeight(int i2, int i7, int i8) {
        boolean z;
        boolean z3 = false;
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "width " + i2 + " must be positive");
        if (i7 > 0) {
            z3 = true;
        }
        Assertions.checkArgument(z3, "height " + i7 + " must be positive");
        checkLayout(i8);
        return new Presentation(i2, i7, -1.0f, i8, 9729, false);
    }

    public Size configure(int i2, int i7) {
        boolean z;
        int i8;
        boolean z3 = false;
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "inputWidth must be positive");
        if (i7 > 0) {
            z3 = true;
        }
        Assertions.checkArgument(z3, "inputHeight must be positive");
        this.transformationMatrix = new Matrix();
        this.outputWidth = (float) i2;
        this.outputHeight = (float) i7;
        int i10 = this.requestedWidthPixels;
        if (!(i10 == -1 || (i8 = this.requestedHeightPixels) == -1)) {
            this.requestedAspectRatio = ((float) i10) / ((float) i8);
        }
        if (this.requestedAspectRatio != -1.0f) {
            applyAspectRatio();
        }
        int i11 = this.requestedHeightPixels;
        if (i11 != -1) {
            int i12 = this.requestedWidthPixels;
            if (i12 != -1) {
                this.outputWidth = (float) i12;
                this.outputHeight = (float) i11;
            } else if (!this.preservePortraitWhenApplicable || i7 <= i2) {
                this.outputWidth = (((float) i11) * this.outputWidth) / this.outputHeight;
                this.outputHeight = (float) i11;
            } else {
                this.outputHeight = (((float) i11) * this.outputHeight) / this.outputWidth;
                this.outputWidth = (float) i11;
            }
        }
        return new Size(Math.round(this.outputWidth), Math.round(this.outputHeight));
    }

    public int getGlTextureMinFilter() {
        return this.textureMinFilter;
    }

    public Matrix getMatrix(long j2) {
        return (Matrix) Assertions.checkStateNotNull(this.transformationMatrix, "configure must be called first");
    }

    public boolean isNoOp(int i2, int i7) {
        configure(i2, i7);
        if (((Matrix) Assertions.checkStateNotNull(this.transformationMatrix)).isIdentity() && i2 == Math.round(this.outputWidth) && i7 == Math.round(this.outputHeight)) {
            return true;
        }
        return false;
    }
}
