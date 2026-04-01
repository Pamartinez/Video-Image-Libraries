package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.view.View;
import com.samsung.android.gallery.support.blur.BlurImageInfo;
import com.samsung.android.gallery.widget.PinchBlurImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GradientAnimator extends PropertyAnimator {
    private final PinchBlurImageView mBlurView;
    private final BlurImageInfo mInfo;

    public GradientAnimator(View view, BlurImageInfo blurImageInfo) {
        super(view);
        PinchBlurImageView pinchBlurImageView;
        this.mInfo = blurImageInfo;
        if (view instanceof PinchBlurImageView) {
            pinchBlurImageView = (PinchBlurImageView) view;
        } else {
            pinchBlurImageView = null;
        }
        this.mBlurView = pinchBlurImageView;
    }

    private float getPercent(float f) {
        return ((float) Math.round(((this.mInfo.getTargetViewHeight() * 2.0f) / f) * 100.0f)) / 100.0f;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f;
        super.onAnimationUpdate(valueAnimator);
        if (this.mBlurView == null) {
            return;
        }
        if (this.mInfo.isSkipBlur()) {
            this.mBlurView.setPercent(0.0f);
        } else if (this.mInfo.isGridType()) {
            this.mBlurView.setProgress(1.0f);
            this.mBlurView.setPercent(getPercent(this.mView.getScaleY() * ((float) this.mView.getHeight())));
            this.mBlurView.invalidate();
        } else {
            if (this.mInfo.isReverseOperation()) {
                f = 1.0f - this.mCurrentValue;
            } else {
                f = this.mCurrentValue;
            }
            this.mBlurView.setProgress(f);
            this.mBlurView.setPercent(f * getPercent((float) this.mView.getLayoutParams().height));
        }
    }
}
