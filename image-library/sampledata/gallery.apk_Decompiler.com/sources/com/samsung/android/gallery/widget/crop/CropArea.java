package com.samsung.android.gallery.widget.crop;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.crop.config.CropViewConfig;
import i.C0212a;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CropArea extends RectF {
    private final float mCropDefaultMargin;
    private boolean mDirty;
    private final RectF mImageBoundary = new RectF();
    private final RectF mMaximumBoundary;
    private boolean mReserveReset;
    private final Resize mResize;
    private RectF mRestoreRatio;
    private final float mTouchPadding;
    private TouchPosition mTouchPosition = TouchPosition.NONE;
    private final View mView;
    private final CropViewConfig mViewConfig;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Resize {
        void resize(RectF rectF, TouchPosition touchPosition, float f, float f5);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TouchPosition {
        NONE,
        CENTER,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        LEFT_TOP,
        RIGHT_TOP,
        LEFT_BOTTOM,
        RIGHT_BOTTOM
    }

    public CropArea(View view, CropViewConfig cropViewConfig) {
        Resize resize;
        RectF rectF = new RectF();
        this.mMaximumBoundary = rectF;
        this.mView = view;
        this.mViewConfig = cropViewConfig;
        this.mCropDefaultMargin = view.getResources().getDimension(R$dimen.crop_default_margin);
        this.mTouchPadding = view.getResources().getDimension(R$dimen.crop_view_touch_radius);
        if (cropViewConfig.mUseFixedAspectRatio) {
            resize = new FixedRatioResize(rectF, cropViewConfig.mWidthHeightRatio, cropViewConfig.mHeightWidthRatio);
        } else {
            resize = new FreeRatioResize(rectF);
        }
        this.mResize = resize;
    }

    private void adjustToAspectRatio() {
        float width = width();
        float height = height();
        float f = this.mViewConfig.mHeightWidthRatio * width;
        if (Float.compare(height, f) != 0) {
            if (height > f) {
                float f5 = (height - f) / 2.0f;
                this.top += f5;
                this.bottom -= f5;
                return;
            }
            float f8 = (width - (height * this.mViewConfig.mWidthHeightRatio)) / 2.0f;
            this.left += f8;
            this.right -= f8;
        }
    }

    private void adjustToMinMax() {
        expandToMinSize();
        shrinkToMaxSize();
    }

    private void adjustToSmartCrop() {
        if (this.mViewConfig.mSmartCropRect != null) {
            float min = Math.min((0.5f - this.mViewConfig.mSmartCropRect.centerY()) * this.mMaximumBoundary.height(), this.top);
            this.top -= min;
            this.bottom -= min;
            float min2 = Math.min((0.5f - this.mViewConfig.mSmartCropRect.centerX()) * this.mMaximumBoundary.width(), this.left);
            this.left -= min2;
            this.right -= min2;
        }
    }

    private void applyDefaultMargin() {
        RectF rectF = this.mMaximumBoundary;
        float f = rectF.left;
        float f5 = this.mCropDefaultMargin;
        this.left = f + f5;
        this.top = rectF.top + f5;
        this.right = rectF.right - f5;
        this.bottom = rectF.bottom - f5;
    }

    private void applyImageScale(float f, RectF rectF) {
        float f5 = this.left;
        RectF rectF2 = this.mImageBoundary;
        rectF.left = (f5 - rectF2.left) * f;
        rectF.top = (this.top - rectF2.top) * f;
        rectF.right = (this.right - rectF2.left) * f;
        rectF.bottom = (this.bottom - rectF2.top) * f;
    }

    private void applyOrientationRatio(Bitmap bitmap, RectF rectF) {
        float width;
        float height;
        float width2;
        int i2 = this.mViewConfig.mOrientation;
        if (i2 == 90 || i2 == 270) {
            width = ((float) bitmap.getWidth()) / this.mMaximumBoundary.height();
            height = (float) bitmap.getHeight();
            width2 = this.mMaximumBoundary.width();
        } else {
            width = ((float) bitmap.getWidth()) / this.mMaximumBoundary.width();
            height = (float) bitmap.getHeight();
            width2 = this.mMaximumBoundary.height();
        }
        float max = Math.max(width, height / width2);
        rectF.left *= max;
        rectF.top *= max;
        rectF.right *= max;
        rectF.bottom *= max;
    }

    private void createRestoreRatio() {
        RectF rectF = new RectF();
        this.mRestoreRatio = rectF;
        float f = this.left;
        RectF rectF2 = this.mMaximumBoundary;
        rectF.left = (f - rectF2.left) / rectF2.width();
        RectF rectF3 = this.mRestoreRatio;
        float f5 = this.top;
        RectF rectF4 = this.mMaximumBoundary;
        rectF3.top = (f5 - rectF4.top) / rectF4.height();
        RectF rectF5 = this.mRestoreRatio;
        float f8 = this.right;
        RectF rectF6 = this.mMaximumBoundary;
        rectF5.right = (f8 - rectF6.left) / rectF6.width();
        RectF rectF7 = this.mRestoreRatio;
        float f10 = this.bottom;
        RectF rectF8 = this.mMaximumBoundary;
        rectF7.bottom = (f10 - rectF8.top) / rectF8.height();
    }

    private void decideTouchArea(float f, float f5) {
        TouchPosition touchPosition;
        TouchPosition touchPosition2;
        TouchPosition touchPosition3;
        float width = width() / 12.0f;
        if (f5 < this.top + width) {
            if (f < this.left + width) {
                touchPosition3 = TouchPosition.LEFT_TOP;
            } else if (f > this.right - width) {
                touchPosition3 = TouchPosition.RIGHT_TOP;
            } else {
                touchPosition3 = TouchPosition.TOP;
            }
            this.mTouchPosition = touchPosition3;
        } else if (f5 > this.bottom - width) {
            if (f < this.left + width) {
                touchPosition2 = TouchPosition.LEFT_BOTTOM;
            } else if (f > this.right - width) {
                touchPosition2 = TouchPosition.RIGHT_BOTTOM;
            } else {
                touchPosition2 = TouchPosition.BOTTOM;
            }
            this.mTouchPosition = touchPosition2;
        } else {
            if (f < this.left + width) {
                touchPosition = TouchPosition.LEFT;
            } else {
                touchPosition = TouchPosition.RIGHT;
            }
            this.mTouchPosition = touchPosition;
        }
    }

    private void draw() {
        this.mView.invalidate();
    }

    private void expandToMinSize() {
        if (shorterThanMinSize(width()) || shorterThanMinSize(height())) {
            CropViewConfig cropViewConfig = this.mViewConfig;
            float max = Math.max(cropViewConfig.mWidthHeightRatio * 200.0f, cropViewConfig.mHeightWidthRatio * 200.0f);
            if (this.mMaximumBoundary.height() > this.mMaximumBoundary.width()) {
                float min = (Math.min(this.mMaximumBoundary.width(), max) - width()) / 2.0f;
                this.left -= min;
                this.right += min;
                float f = this.top;
                float f5 = this.mViewConfig.mHeightWidthRatio;
                this.top = f - (min * f5);
                this.bottom = (min * f5) + this.bottom;
            } else {
                float min2 = (Math.min(this.mMaximumBoundary.height(), max) - height()) / 2.0f;
                this.top -= min2;
                this.bottom += min2;
                float f8 = this.left;
                float f10 = this.mViewConfig.mWidthHeightRatio;
                this.left = f8 - (min2 * f10);
                this.right = (min2 * f10) + this.right;
            }
            restoreExceededMaximumBoundary();
        }
    }

    private String getFormattedRectString(String str, RectF rectF) {
        String str2;
        StringBuilder s = C0212a.s(str);
        if (rectF == null) {
            str2 = "(NULL) ";
        } else {
            str2 = "(".concat(String.format(Locale.ENGLISH, "%.1f, %.1f, %.1f, %.1f) ", new Object[]{Float.valueOf(rectF.left), Float.valueOf(rectF.top), Float.valueOf(rectF.right), Float.valueOf(rectF.bottom)}));
        }
        s.append(str2);
        return s.toString();
    }

    private String getFormattedSizeString(RectF rectF) {
        return " W,H(".concat(String.format(Locale.ENGLISH, "%.1f, %.1f) ", new Object[]{Float.valueOf(rectF.width()), Float.valueOf(rectF.height())}));
    }

    private void initPosition() {
        applyDefaultMargin();
        adjustToMinMax();
        adjustToAspectRatio();
        adjustToSmartCrop();
        adjustToMinMax();
    }

    private boolean isInside(float f, float f5, float f8) {
        if (f < this.left - f8 || f > this.right + f8 || f5 < this.top - f8 || f5 > this.bottom + f8) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0017, code lost:
        if ((r0 + r5) > r3) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void move(float r5, float r6) {
        /*
            r4 = this;
            float r0 = r4.left
            float r1 = r0 + r5
            android.graphics.RectF r2 = r4.mMaximumBoundary
            float r3 = r2.left
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x000f
        L_0x000c:
            float r5 = r3 - r0
            goto L_0x001a
        L_0x000f:
            float r0 = r4.right
            float r1 = r0 + r5
            float r3 = r2.right
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x001a
            goto L_0x000c
        L_0x001a:
            float r0 = r4.top
            float r1 = r0 + r6
            float r3 = r2.top
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0027
            float r6 = r3 - r0
            goto L_0x0033
        L_0x0027:
            float r0 = r4.bottom
            float r1 = r0 + r6
            float r2 = r2.bottom
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0033
            float r6 = r2 - r0
        L_0x0033:
            r4.offset(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.crop.CropArea.move(float, float):void");
    }

    private boolean needInitialize() {
        if (this.mView.getVisibility() == 4 || width() == 0.0f || height() == 0.0f) {
            return true;
        }
        return false;
    }

    private boolean notResizable() {
        if (shorterThanMinSize(width()) || shorterThanMinSize(height()) || shorterThanMinSize(this.mMaximumBoundary.width()) || shorterThanMinSize(this.mMaximumBoundary.height())) {
            return true;
        }
        return false;
    }

    private void reset() {
        initPosition();
        this.mReserveReset = false;
    }

    private void resize(float f, float f5) {
        if (!notResizable()) {
            this.mResize.resize(this, this.mTouchPosition, f, f5);
        }
    }

    private void restoreExceededMaximumBoundary() {
        float f = this.left;
        RectF rectF = this.mMaximumBoundary;
        float f5 = rectF.left;
        if (f < f5) {
            float f8 = f5 - f;
            this.left = f + f8;
            this.right += f8;
        } else {
            float f10 = this.right;
            float f11 = rectF.right;
            if (f10 > f11) {
                float f12 = f10 - f11;
                this.left = f - f12;
                this.right = f10 - f12;
            }
        }
        float f13 = this.top;
        float f14 = rectF.top;
        if (f13 < f14) {
            float f15 = f14 - f13;
            this.top = f13 + f15;
            this.bottom += f15;
            return;
        }
        float f16 = this.bottom;
        float f17 = rectF.bottom;
        if (f16 > f17) {
            float f18 = f16 - f17;
            this.top = f13 - f18;
            this.bottom = f16 - f18;
        }
    }

    private void restoreUsingRatio() {
        RectF rectF = this.mMaximumBoundary;
        this.left = (rectF.width() * this.mRestoreRatio.left) + rectF.left;
        RectF rectF2 = this.mMaximumBoundary;
        this.top = (rectF2.height() * this.mRestoreRatio.top) + rectF2.top;
        RectF rectF3 = this.mMaximumBoundary;
        this.right = (rectF3.width() * this.mRestoreRatio.right) + rectF3.left;
        RectF rectF4 = this.mMaximumBoundary;
        this.bottom = (rectF4.height() * this.mRestoreRatio.bottom) + rectF4.top;
        adjustToMinMax();
    }

    private void setDirty() {
        this.mDirty = true;
        this.mRestoreRatio = null;
    }

    public static boolean shorterThanMinSize(float f) {
        if (((float) Math.round(f * 100.0f)) / 100.0f < 200.0f) {
            return true;
        }
        return false;
    }

    private void shrinkToMaxSize() {
        float f = this.left;
        RectF rectF = this.mMaximumBoundary;
        float f5 = rectF.left;
        if (f < f5 || this.top < rectF.top || this.right > rectF.right || this.bottom > rectF.bottom) {
            this.left = Math.max(f, f5);
            this.top = Math.max(this.top, this.mMaximumBoundary.top);
            this.right = Math.min(this.right, this.mMaximumBoundary.right);
            this.bottom = Math.min(this.bottom, this.mMaximumBoundary.bottom);
        }
    }

    private void updateImageBoundary(Bitmap bitmap, Matrix matrix) {
        float[] fArr = {0.0f, 0.0f, (float) bitmap.getWidth(), 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight(), 0.0f, (float) bitmap.getHeight()};
        matrix.mapPoints(fArr);
        this.mImageBoundary.set(Math.min(Math.min(Math.min(fArr[0], fArr[2]), fArr[4]), fArr[6]), Math.min(Math.min(Math.min(fArr[1], fArr[3]), fArr[5]), fArr[7]), Math.max(Math.max(Math.max(fArr[0], fArr[2]), fArr[4]), fArr[6]), Math.max(Math.max(Math.max(fArr[1], fArr[3]), fArr[5]), fArr[7]));
    }

    private boolean updateMaximumBoundary() {
        RectF rectF = new RectF(this.mMaximumBoundary);
        this.mMaximumBoundary.set(Math.max(this.mImageBoundary.left, 0.0f), Math.max(this.mImageBoundary.top, 0.0f), Math.min(this.mImageBoundary.right, (float) this.mView.getWidth()), Math.min(this.mImageBoundary.bottom, (float) this.mView.getHeight()));
        return !rectF.equals(this.mMaximumBoundary);
    }

    public RectF getResult(Bitmap bitmap, float f) {
        RectF rectF = new RectF();
        applyImageScale(f, rectF);
        applyOrientationRatio(bitmap, rectF);
        return rectF;
    }

    public boolean isDirty() {
        return this.mDirty;
    }

    public boolean isReady() {
        if (width() <= 0.0f || height() <= 0.0f) {
            return false;
        }
        return true;
    }

    public boolean isTouching() {
        if (this.mTouchPosition != TouchPosition.NONE) {
            return true;
        }
        return false;
    }

    public boolean onActionDown(float f, float f5) {
        if (!isInside(f, f5, this.mTouchPadding)) {
            return false;
        }
        if (isInside(f, f5, -(width() / 12.0f))) {
            this.mTouchPosition = TouchPosition.CENTER;
            return true;
        }
        decideTouchArea(f, f5);
        return true;
    }

    public void onActionMove(float f, float f5) {
        setDirty();
        if (this.mTouchPosition == TouchPosition.CENTER) {
            move(f, f5);
        } else {
            resize(f, f5);
        }
        draw();
    }

    public void onConfigurationChanged(boolean z) {
        if (z) {
            this.mReserveReset = true;
        } else {
            createRestoreRatio();
        }
    }

    public void onMatrixChanged(Bitmap bitmap, Matrix matrix) {
        updateImageBoundary(bitmap, matrix);
        boolean updateMaximumBoundary = updateMaximumBoundary();
        if (needInitialize()) {
            initPosition();
            this.mView.setVisibility(0);
        } else if (this.mReserveReset) {
            reset();
        } else if (this.mRestoreRatio != null) {
            restoreUsingRatio();
            this.mRestoreRatio = null;
        } else if (updateMaximumBoundary) {
            adjustToMinMax();
            adjustToAspectRatio();
        }
        draw();
    }

    public void onTouchOut() {
        this.mTouchPosition = TouchPosition.NONE;
    }

    public String toString() {
        return getFormattedRectString("CropArea", this) + getFormattedSizeString(this) + System.lineSeparator() + getFormattedRectString("MaxB", this.mMaximumBoundary) + getFormattedSizeString(this.mMaximumBoundary) + System.lineSeparator() + getFormattedRectString("ImageB", this.mImageBoundary) + getFormattedSizeString(this.mImageBoundary) + System.lineSeparator() + getFormattedRectString("SC", this.mViewConfig.mSmartCropRect) + System.lineSeparator() + getFormattedRectString("RestoreRatio", this.mRestoreRatio) + this.mTouchPosition.toString();
    }
}
