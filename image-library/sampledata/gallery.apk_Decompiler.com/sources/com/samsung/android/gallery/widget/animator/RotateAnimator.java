package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RotateAnimator extends PropertyAnimator {
    private float mStartRotation;

    public RotateAnimator(View view, float f, float f5) {
        super(view);
        this.mStartRotation = view.getRotation();
        setFloatValues(new float[]{f, f5});
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.mView.setRotation((1.0f - valueAnimator.getAnimatedFraction()) * this.mStartRotation);
    }

    public RotateAnimator setDuration(long j2) {
        return (RotateAnimator) super.setDuration(j2);
    }
}
