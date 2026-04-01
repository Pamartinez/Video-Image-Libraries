package com.samsung.android.gallery.widget.livemotion.abstraction;

import android.view.animation.Interpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerScrolledValues {
    private final Interpolator mInterpolator;
    private final boolean mIsPageInOut;
    private final boolean mIsSwipeMode;
    private final int mPosition;
    private final float mPositionOffset;

    public ViewPagerScrolledValues(int i2, float f, boolean z, boolean z3, Interpolator interpolator) {
        this.mPosition = i2;
        this.mPositionOffset = f;
        this.mIsPageInOut = z;
        this.mIsSwipeMode = z3;
        this.mInterpolator = interpolator;
    }

    public float getInterpolation(float f) {
        Interpolator interpolator;
        if (this.mIsSwipeMode || (interpolator = this.mInterpolator) == null) {
            return f;
        }
        return interpolator.getInterpolation(f);
    }

    public int getPosition() {
        return this.mPosition;
    }

    public float getPositionOffset() {
        return this.mPositionOffset;
    }

    public boolean isPageInOut() {
        return this.mIsPageInOut;
    }

    public boolean isSwipeMode() {
        return this.mIsSwipeMode;
    }
}
