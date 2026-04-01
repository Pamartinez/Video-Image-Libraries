package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WidthAnimator<T extends View> extends PropertyAnimator {
    private WidthAnimationCallback<T> mCallback;
    private int mFrom;
    private T mTarget;
    private int mTo;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface WidthAnimationCallback<T extends View> {
        boolean isWidthAnimationNeeded(T t, int i2);
    }

    public WidthAnimator(T t) {
        super(t);
        this.mFrom = 0;
        this.mTo = 0;
        this.mTarget = t;
        setFloatValues(new float[]{0.0f, 1.0f});
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        int i2 = this.mFrom;
        int i7 = (int) ((this.mCurrentValue * ((float) (this.mTo - i2))) + ((float) i2));
        ViewGroup.LayoutParams layoutParams = this.mView.getLayoutParams();
        WidthAnimationCallback<T> widthAnimationCallback = this.mCallback;
        if (widthAnimationCallback == null || widthAnimationCallback.isWidthAnimationNeeded(this.mTarget, i7)) {
            layoutParams.width = i7;
            this.mView.setLayoutParams(layoutParams);
        }
    }

    public WidthAnimator(T t, int i2, int i7) {
        this(t);
        this.mFrom = i2;
        this.mTo = i7;
    }

    public WidthAnimator(T t, int i2, int i7, WidthAnimationCallback<T> widthAnimationCallback) {
        this(t);
        this.mFrom = i2;
        this.mTo = i7;
        this.mCallback = widthAnimationCallback;
    }
}
