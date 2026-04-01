package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BackgroundAlphaAnimator extends PropertyAnimator {
    private Drawable mDrawable;
    private float mFrom;
    private float mTo;

    public BackgroundAlphaAnimator(View view) {
        super(view);
        this.mFrom = 0.0f;
        this.mTo = 0.0f;
        this.mDrawable = null;
        setFloatValues(new float[]{0.0f, 1.0f});
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        if (this.mView instanceof CheckBox) {
            Drawable drawable = this.mDrawable;
            if (drawable != null) {
                float f = this.mFrom;
                drawable.setAlpha((int) ((((this.mTo - f) * this.mCurrentValue) + f) * 255.0f));
                this.mView.setBackground(this.mDrawable);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Support tint animation for Check only");
    }

    public BackgroundAlphaAnimator(View view, float f, float f5) {
        this(view);
        this.mFrom = f;
        this.mTo = f5;
    }

    public BackgroundAlphaAnimator(View view, Drawable drawable, float f, float f5) {
        this(view, f, f5);
        this.mDrawable = drawable;
    }
}
