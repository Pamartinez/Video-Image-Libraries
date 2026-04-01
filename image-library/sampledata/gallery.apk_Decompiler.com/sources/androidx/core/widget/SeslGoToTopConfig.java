package androidx.core.widget;

import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslGoToTopConfig {
    private Drawable mBackgroundBlur;
    private int mBackgroundColorBlur;
    private Drawable mBackgroundDark;
    private Drawable mBackgroundLight;
    private int mDefaultPaddingBottom;
    private float mElevation;
    private Interpolator mFadeInInterpolator;
    private Interpolator mFadeOutInterpolator;
    private Drawable mIconDark;
    private Drawable mIconLight;
    private boolean mIsDefaultPaddingBottomSet;
    private int mOverlayFeatureHiddenHeightPx;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    /* access modifiers changed from: private */
    public int mScrollToTopDurationMs;
    private int mSize;
    private boolean mSizeChanged;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private Drawable backgroundBlur;
        private int backgroundColorBlur;
        private Drawable backgroundDark;
        private Drawable backgroundLight;
        private float elevation;
        private Interpolator fadeInInterpolator;
        private Interpolator fadeOutInterpolator;
        private Drawable iconDark;
        private Drawable iconLight;
        private boolean isSizeChanged;
        private int overlayFeatureHiddenHeightPx;
        private int paddingBottom;
        private int paddingLeft;
        private int paddingRight;
        private int scrollToTopDurationMs;
        private int size;

        public SeslGoToTopConfig build() {
            Drawable drawable;
            Drawable drawable2;
            Drawable drawable3;
            Drawable drawable4;
            Interpolator interpolator;
            Drawable drawable5 = this.iconLight;
            if (drawable5 == null || (drawable = this.iconDark) == null || (drawable2 = this.backgroundLight) == null || (drawable3 = this.backgroundDark) == null || (drawable4 = this.backgroundBlur) == null) {
                throw new IllegalStateException("All drawables must be provided");
            }
            int i2 = this.backgroundColorBlur;
            if (i2 != -1) {
                Interpolator interpolator2 = this.fadeInInterpolator;
                if (interpolator2 == null || (interpolator = this.fadeOutInterpolator) == null) {
                    throw new IllegalStateException("Fade interpolators must be provided");
                }
                int i7 = this.size;
                if (i7 > 0) {
                    float f = this.elevation;
                    if (f >= 0.0f) {
                        SeslGoToTopConfig seslGoToTopConfig = new SeslGoToTopConfig(drawable5, drawable, drawable2, drawable3, drawable4, i2, this.paddingBottom, this.paddingLeft, this.paddingRight, i7, f, this.overlayFeatureHiddenHeightPx, this.isSizeChanged, interpolator2, interpolator);
                        int unused = seslGoToTopConfig.mScrollToTopDurationMs = this.scrollToTopDurationMs;
                        return seslGoToTopConfig;
                    }
                    throw new IllegalStateException("elevation must be >= 0");
                }
                throw new IllegalStateException("size must be > 0");
            }
            throw new IllegalStateException("All colors must be provided");
        }

        public Builder setBackgroundBlur(Drawable drawable) {
            this.backgroundBlur = drawable;
            return this;
        }

        public Builder setBackgroundColorBlur(int i2) {
            this.backgroundColorBlur = i2;
            return this;
        }

        public Builder setBackgroundDark(Drawable drawable) {
            this.backgroundDark = drawable;
            return this;
        }

        public Builder setBackgroundLight(Drawable drawable) {
            this.backgroundLight = drawable;
            return this;
        }

        public Builder setElevation(float f) {
            this.elevation = f;
            return this;
        }

        public Builder setFadeInInterpolator(Interpolator interpolator) {
            this.fadeInInterpolator = interpolator;
            return this;
        }

        public Builder setFadeOutInterpolator(Interpolator interpolator) {
            this.fadeOutInterpolator = interpolator;
            return this;
        }

        public Builder setIconDark(Drawable drawable) {
            this.iconDark = drawable;
            return this;
        }

        public Builder setIconLight(Drawable drawable) {
            this.iconLight = drawable;
            return this;
        }

        public Builder setOverlayFeatureHiddenHeightPx(int i2) {
            this.overlayFeatureHiddenHeightPx = i2;
            return this;
        }

        public Builder setPaddingBottom(int i2) {
            this.paddingBottom = i2;
            return this;
        }

        public Builder setPaddingLeft(int i2) {
            this.paddingLeft = i2;
            return this;
        }

        public Builder setPaddingRight(int i2) {
            this.paddingRight = i2;
            return this;
        }

        public Builder setScrollToTopDurationMs(int i2) {
            this.scrollToTopDurationMs = i2;
            return this;
        }

        public Builder setSize(int i2) {
            this.size = i2;
            return this;
        }

        public Builder setSizeChanged(boolean z) {
            this.isSizeChanged = z;
            return this;
        }
    }

    public Drawable getBackgroundBlur() {
        return this.mBackgroundBlur;
    }

    public Drawable getBackgroundDark() {
        return this.mBackgroundDark;
    }

    public Drawable getBackgroundLight() {
        return this.mBackgroundLight;
    }

    public int getDefaultPaddingBottom() {
        return this.mDefaultPaddingBottom;
    }

    public float getElevation() {
        return this.mElevation;
    }

    public Interpolator getFadeInInterpolator() {
        return this.mFadeInInterpolator;
    }

    public Interpolator getFadeOutInterpolator() {
        return this.mFadeOutInterpolator;
    }

    public Drawable getIcon(boolean z) {
        if (z) {
            return this.mIconLight;
        }
        return this.mIconDark;
    }

    public int getOverlayFeatureHiddenHeightPx() {
        return this.mOverlayFeatureHiddenHeightPx;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    public int getScrollToTopDurationMs() {
        return this.mScrollToTopDurationMs;
    }

    public int getSize() {
        return this.mSize;
    }

    public boolean hasDefaultBottomPadding() {
        return this.mIsDefaultPaddingBottomSet;
    }

    public boolean isSizeChanged() {
        return this.mSizeChanged;
    }

    public void setOverlayFeatureHiddenHeightPx(int i2) {
        this.mOverlayFeatureHiddenHeightPx = i2;
    }

    public void setPaddingBottom(int i2) {
        if (!this.mIsDefaultPaddingBottomSet) {
            this.mDefaultPaddingBottom = i2;
            this.mIsDefaultPaddingBottomSet = true;
        }
        this.mPaddingBottom = i2;
        this.mSizeChanged = true;
    }

    public void setPaddingLeft(int i2) {
        this.mPaddingLeft = i2;
        this.mSizeChanged = true;
    }

    public void setPaddingRight(int i2) {
        this.mPaddingRight = i2;
        this.mSizeChanged = true;
    }

    public void setSizeChanged(boolean z) {
        this.mSizeChanged = z;
    }

    private SeslGoToTopConfig(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, int i2, int i7, int i8, int i10, int i11, float f, int i12, boolean z, Interpolator interpolator, Interpolator interpolator2) {
        this.mIconLight = drawable;
        this.mIconDark = drawable2;
        this.mBackgroundLight = drawable3;
        this.mBackgroundDark = drawable4;
        this.mBackgroundBlur = drawable5;
        this.mBackgroundColorBlur = i2;
        this.mPaddingBottom = i7;
        this.mPaddingLeft = i8;
        this.mPaddingRight = i10;
        this.mDefaultPaddingBottom = i7;
        this.mIsDefaultPaddingBottomSet = true;
        this.mSize = i11;
        this.mElevation = f;
        this.mOverlayFeatureHiddenHeightPx = i12;
        this.mSizeChanged = z;
        this.mFadeInInterpolator = interpolator;
        this.mFadeOutInterpolator = interpolator2;
    }
}
