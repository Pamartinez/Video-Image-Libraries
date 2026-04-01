package com.samsung.android.gallery.widget.livemotion.theme;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThemeInterpolator extends LinearInterpolator {
    private final Interpolator mTransitionInterpolator = createInterpolator();
    private final float mTransitionStart;

    public ThemeInterpolator(float f) {
        this.mTransitionStart = f;
    }

    private Interpolator createInterpolator() {
        return PathInterpolator.create(0.4f, 0.0f, 0.2f, 1.0f);
    }

    public float getInterpolation(float f) {
        float interpolation = super.getInterpolation(f);
        float f5 = this.mTransitionStart;
        if (f5 > interpolation) {
            return interpolation;
        }
        float interpolation2 = this.mTransitionInterpolator.getInterpolation((interpolation - f5) / (1.0f - f5));
        float f8 = this.mTransitionStart;
        return Math.min(((1.0f - f8) * interpolation2) + f8, 1.0f);
    }
}
