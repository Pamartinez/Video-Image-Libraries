package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Color2Animator extends PropertyAnimator {
    View mExtraView;
    private boolean mLightTheme;
    private float mThreshold = 0.6f;

    public Color2Animator(View view, int i2, int i7) {
        super(view);
        setFloatValues(new float[]{0.0f, 1.0f});
    }

    public Color2Animator addView(View view) {
        if (view != null) {
            this.mExtraView = view;
        }
        return this;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f;
        super.onAnimationUpdate(valueAnimator);
        View view = this.mView;
        float f5 = this.mCurrentValue;
        float f8 = this.mThreshold;
        float f10 = 0.0f;
        if (f5 < f8) {
            f = Math.max((float) Math.cos(((((double) f5) * 3.141592653589793d) * 0.5d) / ((double) f8)), 0.0f);
        } else {
            f = 0.0f;
        }
        view.setAlpha(f);
        View view2 = this.mExtraView;
        if (view2 != null) {
            float f11 = this.mCurrentValue;
            float f12 = this.mThreshold;
            if (f11 < f12) {
                f10 = Math.max((float) Math.cos(((((double) f11) * 3.141592653589793d) * 0.5d) / ((double) f12)), 0.0f);
            }
            view2.setAlpha(f10);
        }
    }

    public Color2Animator setLightTheme(boolean z) {
        this.mLightTheme = z;
        return this;
    }

    public Color2Animator setThreshold(float f) {
        if (f > 0.0f) {
            this.mThreshold = f;
        }
        return this;
    }
}
