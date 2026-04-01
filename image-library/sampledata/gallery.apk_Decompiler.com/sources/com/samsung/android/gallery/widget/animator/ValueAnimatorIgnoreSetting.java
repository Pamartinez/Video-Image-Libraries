package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ValueAnimatorIgnoreSetting extends ValueAnimator {
    public ValueAnimatorIgnoreSetting() {
        SimpleAnimator.ignoreDurationScaleIfDisabled(this);
    }

    public static ValueAnimator ofFloat(float... fArr) {
        ValueAnimatorIgnoreSetting valueAnimatorIgnoreSetting = new ValueAnimatorIgnoreSetting();
        valueAnimatorIgnoreSetting.setFloatValues(fArr);
        return valueAnimatorIgnoreSetting;
    }

    public static ValueAnimator ofInt(int... iArr) {
        ValueAnimatorIgnoreSetting valueAnimatorIgnoreSetting = new ValueAnimatorIgnoreSetting();
        valueAnimatorIgnoreSetting.setIntValues(iArr);
        return valueAnimatorIgnoreSetting;
    }
}
