package com.samsung.android.gallery.module.graphics;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.samsung.android.gallery.support.utils.ExifUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MatrixBuilder {
    private Rect mCropRect = null;
    private boolean mIsStretchable = false;
    private int mOrientation = 0;
    private int mOrientationTag = 0;
    private final Bitmap mSource;
    private final int mTargetHeight;
    private final int mTargetWidth;

    public MatrixBuilder(Bitmap bitmap, int i2, int i7) {
        this.mSource = bitmap;
        this.mTargetWidth = i2;
        this.mTargetHeight = i7;
    }

    private float checkRange(float f, float f5, float f8) {
        return Math.max(Math.min(f, f5), f8);
    }

    private Rect createFullSizeRect(int i2, int i7) {
        return new Rect(0, 0, i2, i7);
    }

    private float getCropRectScale(Rect rect) {
        int min;
        if (this.mIsStretchable) {
            min = Math.min(this.mSource.getWidth(), this.mSource.getHeight());
        } else {
            min = Math.min(rect.width(), rect.height());
        }
        return ((float) Math.min(this.mTargetWidth, this.mTargetHeight)) / ((float) min);
    }

    private float getDx(float f, float f5) {
        float f8;
        float f10;
        int i2 = this.mOrientation;
        if (i2 == 90 || i2 == 180) {
            int i7 = this.mTargetWidth;
            f10 = ((((float) i7) / 2.0f) + f) - f5;
            f8 = (float) i7;
        } else {
            int i8 = this.mTargetWidth;
            f10 = (((float) i8) / 2.0f) - f5;
            f8 = -(f - ((float) i8));
            f = 0.0f;
        }
        return checkRange(f10, f, f8);
    }

    private float getDy(float f, float f5) {
        float f8;
        float f10;
        int i2 = this.mOrientation;
        if (i2 == 0 || i2 == 90) {
            int i7 = this.mTargetHeight;
            f10 = (((float) i7) / 2.0f) - f5;
            f8 = -(f - ((float) i7));
            f = 0.0f;
        } else {
            int i8 = this.mTargetHeight;
            f10 = ((((float) i8) / 2.0f) + f) - f5;
            f8 = (float) i8;
        }
        return checkRange(f10, f, f8);
    }

    private float getSourceScale(int i2, int i7) {
        int i8 = this.mTargetWidth;
        if (i8 == i2 && this.mTargetHeight == i7) {
            return 1.0f;
        }
        return Math.max(((float) this.mTargetHeight) / ((float) i7), ((float) i8) / ((float) i2));
    }

    private Matrix updateMatrix(Rect rect, float f, float f5, float f8, float f10) {
        Matrix matrix = new Matrix();
        matrix.setScale(f, f5);
        matrix.postRotate((float) this.mOrientation);
        matrix.postTranslate(f8, f10);
        if (ExifUtils.isHorizontalMirror(this.mOrientationTag)) {
            matrix.postScale(-1.0f, 1.0f);
            matrix.postTranslate((float) this.mTargetWidth, 0.0f);
        }
        return matrix;
    }

    public Matrix build() {
        float sourceScale;
        int rotatedWidth = BitmapUtils.getRotatedWidth(this.mSource, this.mOrientation);
        int rotatedHeight = BitmapUtils.getRotatedHeight(this.mSource, this.mOrientation);
        Rect rect = this.mCropRect;
        if (rect != null) {
            sourceScale = getCropRectScale(rect);
        } else {
            sourceScale = getSourceScale(rotatedWidth, rotatedHeight);
        }
        float f = sourceScale;
        Rect rect2 = this.mCropRect;
        if (rect2 == null) {
            rect2 = createFullSizeRect(rotatedWidth, rotatedHeight);
        }
        Rect rect3 = rect2;
        return updateMatrix(rect3, f, f, getDx(((float) rotatedWidth) * f, ((float) rect3.centerX()) * f), getDy(((float) rotatedHeight) * f, ((float) rect3.centerY()) * f));
    }

    public MatrixBuilder setCropRect(Rect rect, boolean z) {
        this.mCropRect = rect;
        this.mIsStretchable = z;
        return this;
    }

    public MatrixBuilder setOrientation(int i2, int i7) {
        this.mOrientation = i2;
        this.mOrientationTag = i7;
        return this;
    }
}
