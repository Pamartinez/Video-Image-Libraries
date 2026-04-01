package com.samsung.android.sdk.sgpl.graphics;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MatrixBuilder {
    Rect cropRect;
    int orientation;
    int orientationTag;
    final int sourceHeight;
    final int sourceWidth;
    boolean stretchable;
    int targetHeight;
    int targetWidth;

    public MatrixBuilder(Bitmap bitmap, int i2, int i7) {
        this(bitmap.getWidth(), bitmap.getHeight());
        this.targetWidth = i2;
        this.targetHeight = i7;
    }

    public Matrix build() {
        int i2;
        int i7;
        float f;
        int i8;
        int i10;
        int i11 = this.orientation;
        if (i11 % MOCRLang.KHMER == 0) {
            i2 = this.sourceWidth;
        } else {
            i2 = this.sourceHeight;
        }
        if (i11 % MOCRLang.KHMER == 0) {
            i7 = this.sourceHeight;
        } else {
            i7 = this.sourceWidth;
        }
        Rect rect = this.cropRect;
        if (rect != null) {
            f = getCropRectScale(rect);
        } else {
            f = getSourceScale(i2, i7);
        }
        Rect rect2 = this.cropRect;
        if (rect2 != null) {
            i8 = rect2.centerX();
        } else {
            i8 = i2 >> 1;
        }
        Rect rect3 = this.cropRect;
        if (rect3 != null) {
            i10 = rect3.centerY();
        } else {
            i10 = i7 >> 1;
        }
        float dx = getDx(((float) i2) * f, ((float) i8) * f);
        float dy = getDy(((float) i7) * f, ((float) i10) * f);
        Matrix matrix = new Matrix();
        matrix.setScale(f, f);
        matrix.postRotate((float) this.orientation);
        matrix.postTranslate(dx, dy);
        int i12 = this.orientationTag;
        if (i12 > 0 && isHorizontalMirror(i12)) {
            matrix.postScale(-1.0f, 1.0f);
            matrix.postTranslate((float) this.targetWidth, 0.0f);
        }
        return matrix;
    }

    public float checkRange(float f, float f5, float f8) {
        return Math.max(Math.min(f, f5), f8);
    }

    public float getCropRectScale(Rect rect) {
        int min;
        if (this.stretchable) {
            min = Math.min(this.sourceWidth, this.sourceHeight);
        } else {
            min = Math.min(rect.width(), rect.height());
        }
        return ((float) Math.min(this.targetWidth, this.targetHeight)) / ((float) min);
    }

    public float getDx(float f, float f5) {
        int i2 = this.orientation;
        if (i2 == 90 || i2 == 180) {
            int i7 = this.targetWidth;
            return checkRange(((((float) i7) / 2.0f) + f) - f5, f, (float) i7);
        }
        int i8 = this.targetWidth;
        return checkRange((((float) i8) / 2.0f) - f5, 0.0f, -(f - ((float) i8)));
    }

    public float getDy(float f, float f5) {
        int i2 = this.orientation;
        if (i2 == 0 || i2 == 90) {
            int i7 = this.targetHeight;
            return checkRange((((float) i7) / 2.0f) - f5, 0.0f, -(f - ((float) i7)));
        }
        int i8 = this.targetHeight;
        return checkRange(((((float) i8) / 2.0f) + f) - f5, f, (float) i8);
    }

    public float getSourceScale(int i2, int i7) {
        int i8 = this.targetWidth;
        if (i8 == i2 && this.targetHeight == i7) {
            return 1.0f;
        }
        return Math.max(((float) this.targetHeight) / ((float) i7), ((float) i8) / ((float) i2));
    }

    public boolean isHorizontalMirror(int i2) {
        if (i2 == 2 || i2 == 4 || i2 == 5 || i2 == 7) {
            return true;
        }
        return false;
    }

    public MatrixBuilder setCropRect(Rect rect, boolean z) {
        this.cropRect = rect;
        this.stretchable = z;
        return this;
    }

    public MatrixBuilder setOrientation(int i2, int i7) {
        this.orientation = i2;
        this.orientationTag = i7;
        return this;
    }

    public MatrixBuilder setTargetSize(int i2, int i7) {
        this.targetWidth = i2;
        this.targetHeight = i7;
        return this;
    }

    public MatrixBuilder(int i2, int i7) {
        this.sourceWidth = i2;
        this.sourceHeight = i7;
    }
}
