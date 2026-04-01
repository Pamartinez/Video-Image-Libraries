package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RotateAnimator2 extends PropertyAnimator {
    public RotateAnimator2(View view, float f, float f5) {
        super(view);
        setFloatValues(new float[]{f, f5});
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.mView.setRotation(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public RotateAnimator2 setDuration(long j2) {
        return (RotateAnimator2) super.setDuration(j2);
    }
}
