package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HeightAnimator<T extends View> extends PropertyAnimator {
    private int mFrom;
    private int mTo;

    public HeightAnimator(T t) {
        super(t);
        this.mFrom = 0;
        this.mTo = 0;
        setFloatValues(new float[]{0.0f, 1.0f});
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        int i2 = this.mFrom;
        ViewGroup.LayoutParams layoutParams = this.mView.getLayoutParams();
        layoutParams.height = (int) ((this.mCurrentValue * ((float) (this.mTo - i2))) + ((float) i2));
        this.mView.setLayoutParams(layoutParams);
    }

    public HeightAnimator(T t, int i2, int i7) {
        this(t);
        this.mFrom = i2;
        this.mTo = i7;
    }
}
