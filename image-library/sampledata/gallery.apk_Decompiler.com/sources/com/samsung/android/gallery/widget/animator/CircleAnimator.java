package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CircleAnimator extends PropertyAnimator {
    private final boolean mIsWide;

    public CircleAnimator(View view) {
        super(view);
        boolean z;
        if (view.getWidth() > view.getHeight()) {
            z = true;
        } else {
            z = false;
        }
        this.mIsWide = z;
        setFloatValues(new float[]{0.0f, 1.0f});
    }

    private void setAlpha() {
        float f = this.mCurrentValue;
        if (f > 0.99f) {
            ViewUtils.setAlpha(this.mView, (1.0f - f) * 50.0f);
            if (this.mCurrentValue > 0.995f) {
                ViewUtils.setVisibility(this.mView, 8);
            }
        }
    }

    private void setShape() {
        int max = Math.max(this.mView.getWidth(), this.mView.getHeight());
        ViewGroup.LayoutParams layoutParams = this.mView.getLayoutParams();
        if (this.mIsWide) {
            int i2 = layoutParams.width;
            layoutParams.width = (int) (((float) i2) - (((float) (i2 - layoutParams.height)) * this.mCurrentValue));
        } else {
            int i7 = layoutParams.height;
            layoutParams.height = (int) (((float) i7) - (((float) (i7 - layoutParams.width)) * this.mCurrentValue));
        }
        this.mView.setLayoutParams(layoutParams);
        ViewUtils.setViewShape(this.mView, 1, (((float) max) * this.mCurrentValue) + 65.0f);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        setShape();
        setAlpha();
    }
}
