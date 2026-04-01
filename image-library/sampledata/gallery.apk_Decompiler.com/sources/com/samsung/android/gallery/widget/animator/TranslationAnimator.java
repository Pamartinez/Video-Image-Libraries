package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.graphics.RectF;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TranslationAnimator extends PropertyAnimator {
    private float mEndOffset;
    private float mFromX;
    private float mFromY;
    private float mStartOffset;
    private float mToX;
    private float mToY;

    public TranslationAnimator(View view, RectF rectF, RectF rectF2) {
        super(view);
        setFloatValues(new float[]{0.0f, 1.0f});
        float f = rectF.left;
        this.mFromX = f;
        this.mFromY = rectF.top;
        this.mToX = rectF2.left;
        this.mToY = rectF2.top;
        this.mView.setX(f);
        this.mView.setY(this.mFromY);
    }

    public TranslationAnimator addYOffset(float f) {
        this.mToY += f;
        return this;
    }

    public TranslationAnimator fromYRelative(float f) {
        this.mFromY += f;
        return this;
    }

    public TranslationAnimator offset(float f) {
        this.mStartOffset = f;
        this.mEndOffset = f;
        return this;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f;
        float f5;
        super.onAnimationUpdate(valueAnimator);
        float f8 = this.mStartOffset;
        if (f8 <= 0.0f || this.mCurrentValue > f8) {
            float f10 = this.mEndOffset;
            if (f10 <= 0.0f || this.mCurrentValue < 1.0f - f10) {
                float f11 = this.mToX;
                float f12 = this.mFromX;
                float f13 = this.mToY;
                float f14 = this.mFromY;
                float f15 = this.mCurrentValue;
                f = ((f11 - f12) * f15) + f12;
                f5 = (f15 * (f13 - f14)) + f14;
            } else {
                f = this.mToX;
                f5 = this.mToY;
            }
        } else {
            f = this.mFromX;
            f5 = this.mFromY;
        }
        this.mView.setX(f);
        this.mView.setY(f5);
    }

    public void setBy(View view) {
        setFloatValues(new float[]{0.0f, 1.0f});
        this.mFromX = view.getX();
        this.mFromY = view.getY();
        this.mToX = view.getX();
        this.mToY = view.getY();
        this.mView.setX(this.mFromX);
        this.mView.setY(this.mFromY);
    }

    public TranslationAnimator translateX(float f, float f5) {
        this.mFromX = f;
        this.mToX = f5;
        return this;
    }

    public TranslationAnimator translateXRelative(float f) {
        this.mToX = this.mFromX + f;
        return this;
    }

    public TranslationAnimator translateY(float f, float f5) {
        this.mFromY = f;
        this.mToY = f5;
        return this;
    }

    public TranslationAnimator translateYRelative(float f) {
        this.mToY = this.mFromY + f;
        return this;
    }

    public TranslationAnimator(View view) {
        super(view);
        setBy(view);
    }
}
