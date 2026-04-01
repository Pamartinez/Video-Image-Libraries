package com.samsung.android.gallery.module.graphics;

import Ec.a;
import N2.j;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.RectUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapOperator {
    private static final Paint PAINT = new Paint(6);
    private int mFaceCropFlag = 0;
    private int mHeight = -1;
    private Rect mInCropRect;
    private RectF mInCropRectRatio;
    private boolean mInCropRectRatioOriented;
    private boolean mIncludeHdr;
    private int mOrientation;
    private int mOrientationTag;
    private final Bitmap mSource;
    private boolean mStretchable;
    private int mWidth = -1;

    public BitmapOperator(Bitmap bitmap) {
        if (bitmap != null) {
            this.mSource = bitmap;
            return;
        }
        throw new IllegalArgumentException("BitmapOperator failed. null input");
    }

    private Bitmap getBitmap(Bitmap bitmap) {
        return BitmapPool.getInstance().create(this.mWidth, this.mHeight, bitmap);
    }

    private boolean isInvalidSize(int i2, int i7) {
        if (i2 <= 0 || i7 <= 0) {
            return true;
        }
        return false;
    }

    private void updateSizeIfInvalid(Rect rect) {
        if (!isInvalidSize(this.mWidth, this.mHeight)) {
            return;
        }
        if (rect != null) {
            this.mWidth = rect.width();
            this.mHeight = rect.height();
            return;
        }
        this.mWidth = BitmapUtils.getRotatedWidth(this.mSource, this.mOrientation);
        this.mHeight = BitmapUtils.getRotatedHeight(this.mSource, this.mOrientation);
    }

    public Bitmap apply() {
        Rect buildCropRect = buildCropRect(this.mSource, this.mOrientation);
        if (isInvalidSize(this.mWidth, this.mHeight) && buildCropRect == null && this.mOrientation == 0 && !ExifUtils.isHorizontalMirror(this.mOrientationTag)) {
            return this.mSource;
        }
        updateSizeIfInvalid(buildCropRect);
        if (isInvalidSize(this.mWidth, this.mHeight)) {
            Log.w((CharSequence) "BitmapOperator", "apply failed. invalid size", Logger.v(Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight)));
            return this.mSource;
        }
        Bitmap bitmap = getBitmap(this.mSource);
        if (bitmap == null) {
            Log.e("BitmapOperator", "apply failed. null bitmap");
            return this.mSource;
        }
        new Canvas(bitmap).drawBitmap(this.mSource, new MatrixBuilder(this.mSource, this.mWidth, this.mHeight).setCropRect(buildCropRect, this.mStretchable).setOrientation(this.mOrientation, this.mOrientationTag).build(), PAINT);
        if (this.mIncludeHdr && Build.VERSION.SDK_INT >= 34 && this.mSource.hasGainmap()) {
            try {
                a.k();
                bitmap.setGainmap(a.y(applyInGainmap(this.mSource, buildCropRect)));
                return bitmap;
            } catch (Exception e) {
                j.s(e, new StringBuilder("apply#gainmap failed. e="), "BitmapOperator");
            }
        }
        return bitmap;
    }

    public Bitmap applyInGainmap(Bitmap bitmap, Rect rect) {
        int i2;
        Bitmap e = bitmap.getGainmap().getGainmapContents();
        float max = ((float) Math.max(e.getWidth(), e.getHeight())) / ((float) Math.max(bitmap.getWidth(), bitmap.getHeight()));
        int i7 = (max > 1.0f ? 1 : (max == 1.0f ? 0 : -1));
        if (!(i7 == 0 || rect == null)) {
            rect = new Rect((int) ((((float) rect.left) * max) + 0.5f), (int) ((((float) rect.top) * max) + 0.5f), (int) ((((float) rect.right) * max) + 0.5f), (int) ((((float) rect.bottom) * max) + 0.5f));
        }
        int i8 = this.mWidth;
        if (i7 != 0) {
            i8 = (int) ((((float) i8) * max) + 0.5f);
        }
        if (i7 != 0) {
            i2 = (int) ((((float) this.mHeight) * max) + 0.5f);
        } else {
            i2 = this.mHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i8, i2, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(e, new MatrixBuilder(e, i8, i2).setCropRect(rect, this.mStretchable).setOrientation(this.mOrientation, this.mOrientationTag).build(), PAINT);
        return createBitmap;
    }

    public Rect buildCropRect(Bitmap bitmap, int i2) {
        Rect rect = this.mInCropRect;
        if (RectUtils.isValidRect(this.mInCropRectRatio)) {
            if (this.mInCropRectRatioOriented) {
                rect = RectUtils.getSmartCropRect(bitmap, this.mInCropRectRatio, i2, true, this.mFaceCropFlag);
            } else {
                rect = RectUtils.getSmartCropRect(bitmap, this.mInCropRectRatio, this.mFaceCropFlag);
            }
        }
        if (this.mInCropRectRatioOriented) {
            return rect;
        }
        return RectUtils.getRotatedRect(rect, bitmap.getWidth(), bitmap.getHeight(), i2);
    }

    public BitmapOperator crop(Rect rect) {
        this.mInCropRect = rect;
        return this;
    }

    public BitmapOperator faceCropFlag(int i2) {
        this.mFaceCropFlag = i2;
        return this;
    }

    public BitmapOperator resize(int i2) {
        return resize(i2, i2);
    }

    public BitmapOperator rotate(int i2) {
        this.mOrientation = i2;
        return this;
    }

    public BitmapOperator stretchable(boolean z) {
        this.mStretchable = z;
        return this;
    }

    public BitmapOperator crop(RectF rectF) {
        this.mInCropRectRatio = rectF;
        this.mInCropRectRatioOriented = false;
        return this;
    }

    public BitmapOperator resize(int i2, int i7) {
        this.mWidth = i2;
        this.mHeight = i7;
        return this;
    }

    public BitmapOperator rotate(int i2, int i7) {
        this.mOrientation = i2;
        this.mOrientationTag = i7;
        return this;
    }

    public BitmapOperator crop(RectF rectF, boolean z) {
        this.mInCropRectRatio = rectF;
        this.mInCropRectRatioOriented = z;
        return this;
    }

    public BitmapOperator writeDebugText(String str) {
        return this;
    }
}
