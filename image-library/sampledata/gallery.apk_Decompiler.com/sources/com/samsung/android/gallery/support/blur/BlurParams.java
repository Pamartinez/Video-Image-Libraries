package com.samsung.android.gallery.support.blur;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BlurParams {
    private final float mCurveLevel;
    private final float mCurveMaxX;
    private final float mCurveMaxY;
    private final float mCurveMinX;
    private final float mCurveMinY;
    private final float mDirection;
    private final boolean mDither;
    private final int mHeight;
    private final float mPercent;
    private final float mPivotPercent;
    private final float mRadius;
    private final float mSaturation;
    private final int mWidth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public float mCurveLevel = -15.0f;
        /* access modifiers changed from: private */
        public float mCurveMaxX = 250.0f;
        /* access modifiers changed from: private */
        public float mCurveMaxY = 219.3f;
        /* access modifiers changed from: private */
        public float mCurveMinX = 0.0f;
        /* access modifiers changed from: private */
        public float mCurveMinY = 0.0f;
        /* access modifiers changed from: private */
        public float mDirection = 0.0f;
        private final boolean mDither = true;
        /* access modifiers changed from: private */
        public final int mHeight;
        /* access modifiers changed from: private */
        public float mPercent = 0.0f;
        /* access modifiers changed from: private */
        public float mPivotPercent = 0.0f;
        /* access modifiers changed from: private */
        public float mRadius = 150.0f;
        /* access modifiers changed from: private */
        public float mSaturation = 0.2f;
        /* access modifiers changed from: private */
        public final int mWidth;

        public Builder(int i2, int i7) {
            this.mWidth = i2;
            this.mHeight = i7;
        }

        public BlurParams build() {
            return new BlurParams(this, 0);
        }

        public Builder setCurveLevel(float f) {
            this.mCurveLevel = f;
            return this;
        }

        public Builder setCurveMaxY(float f) {
            this.mCurveMaxY = f;
            return this;
        }

        public Builder setPercent(float f) {
            this.mPercent = f;
            return this;
        }

        public Builder setPivotPercent(float f) {
            this.mPivotPercent = f;
            return this;
        }

        public Builder setRadius(float f) {
            this.mRadius = f;
            return this;
        }

        public Builder setSaturation(float f) {
            this.mSaturation = f;
            return this;
        }
    }

    public /* synthetic */ BlurParams(Builder builder, int i2) {
        this(builder);
    }

    public float getCurveLevel() {
        return this.mCurveLevel;
    }

    public float getCurveMaxX() {
        return this.mCurveMaxX;
    }

    public float getCurveMaxY() {
        return this.mCurveMaxY;
    }

    public float getCurveMinX() {
        return this.mCurveMinX;
    }

    public float getCurveMinY() {
        return this.mCurveMinY;
    }

    public float getGradientBlurDirection() {
        return this.mDirection;
    }

    public float getGradientBlurPercent() {
        return this.mPercent;
    }

    public float getGradientBlurPivotPercent() {
        return this.mPivotPercent;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public float getSaturation() {
        return this.mSaturation;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean hasGradientBlur() {
        if (this.mPercent > 0.0f || this.mPivotPercent > 0.0f) {
            return true;
        }
        return false;
    }

    public boolean isDither() {
        return this.mDither;
    }

    private BlurParams(Builder builder) {
        this.mCurveLevel = builder.mCurveLevel;
        this.mCurveMinX = builder.mCurveMinX;
        this.mCurveMaxX = builder.mCurveMaxX;
        this.mCurveMinY = builder.mCurveMinY;
        this.mCurveMaxY = builder.mCurveMaxY;
        this.mDither = true;
        this.mDirection = builder.mDirection;
        this.mPercent = builder.mPercent;
        this.mPivotPercent = builder.mPivotPercent;
        this.mRadius = builder.mRadius;
        this.mSaturation = builder.mSaturation;
        this.mWidth = builder.mWidth;
        this.mHeight = builder.mHeight;
    }
}
