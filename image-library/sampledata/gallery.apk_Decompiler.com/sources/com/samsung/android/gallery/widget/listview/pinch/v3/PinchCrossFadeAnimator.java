package com.samsung.android.gallery.widget.listview.pinch.v3;

import android.animation.ValueAnimator;
import android.view.View;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.widget.PinchImageView;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchCrossFadeAnimator extends PropertyAnimator {
    private PinchImageView mImageView;

    public PinchCrossFadeAnimator(View view) {
        super(view);
        setFloatValues(new float[]{0.0f, 1.0f});
        if (view instanceof PinchImageView) {
            this.mImageView = (PinchImageView) view;
        } else {
            new InternalException("Use wrong image view").post();
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        PinchImageView pinchImageView = this.mImageView;
        float f = this.mCurrentValue;
        pinchImageView.updateAlpha(1.0f - f, f);
        this.mImageView.invalidate();
    }
}
