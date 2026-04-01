package com.samsung.android.gallery.plugins.motionphoto;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MotionPhotoViewImpl {
    private int mMeasuredHeight;
    private float mMeasuredScale = 1.0f;
    private int mMeasuredWidth;
    private float mScale = 1.0f;
    private int mSurfaceHeight;
    private int mSurfaceWidth;
    private int mVideoHeight;
    private int mVideoWidth;

    private boolean isInvalidScale() {
        float f = this.mScale;
        int i2 = (int) (((float) this.mSurfaceWidth) * f);
        int i7 = (int) (((float) this.mSurfaceHeight) * f);
        if (this.mMeasuredScale != f) {
            return false;
        }
        if (this.mMeasuredWidth == i2 && this.mMeasuredHeight == i7) {
            return false;
        }
        return true;
    }

    private int resolveAdjustedSize(int i2, int i7) {
        int mode = View.MeasureSpec.getMode(i7);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(i2, View.MeasureSpec.getSize(i7));
        }
        if (mode != 1073741824) {
            return i2;
        }
        return View.MeasureSpec.getSize(i7);
    }

    public int getSurfaceHeight() {
        return this.mSurfaceHeight;
    }

    public int getSurfaceWidth() {
        return this.mSurfaceWidth;
    }

    public int[] getVideoSize() {
        int i2;
        int i7 = this.mVideoWidth;
        if (i7 <= 0 || (i2 = this.mVideoHeight) <= 0) {
            return null;
        }
        return new int[]{i7, i2};
    }

    public boolean onMeasure(int i2, int i7) {
        float f;
        int i8 = this.mVideoWidth;
        if (i8 == 0 || this.mVideoHeight == 0) {
            return false;
        }
        this.mSurfaceWidth = resolveAdjustedSize(i8, i2);
        int resolveAdjustedSize = resolveAdjustedSize(this.mVideoHeight, i7);
        this.mSurfaceHeight = resolveAdjustedSize;
        int i10 = this.mVideoWidth;
        int i11 = i10 * resolveAdjustedSize;
        int i12 = this.mVideoHeight;
        int i13 = this.mSurfaceWidth;
        if (i11 > i12 * i13) {
            this.mSurfaceHeight = (int) Math.ceil(((double) (i13 * i12)) / ((double) i10));
        } else {
            this.mSurfaceWidth = (int) Math.ceil(((double) (resolveAdjustedSize * i10)) / ((double) i12));
        }
        if (isInvalidScale()) {
            f = 1.0f;
        } else {
            f = this.mScale;
        }
        if (f > 1.0f) {
            this.mSurfaceWidth = (int) (((float) this.mSurfaceWidth) * f);
            this.mSurfaceHeight = (int) (((float) this.mSurfaceHeight) * f);
        }
        this.mMeasuredWidth = this.mSurfaceWidth;
        this.mMeasuredHeight = this.mSurfaceHeight;
        this.mMeasuredScale = f;
        return true;
    }

    public void setScale(float f) {
        this.mScale = f;
    }

    public void setVideoSize(int i2, int i7) {
        this.mVideoWidth = i2;
        this.mVideoHeight = i7;
    }
}
