package com.samsung.android.gallery.widget.animator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PropertyAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {
    protected float mCurrentValue;
    private PropertyAnimationListener mListener;
    private AtomicBoolean mLiteNavigationBar = new AtomicBoolean(false);
    private AtomicBoolean mLiteStatusBar = new AtomicBoolean(false);
    protected View mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PropertyAnimationListener {
        void onAnimationEnd(View view);
    }

    public PropertyAnimator(View view) {
        this.mView = view;
        addUpdateListener(this);
    }

    public void notifyPropertyAnimationEnd() {
        PropertyAnimationListener propertyAnimationListener = this.mListener;
        if (propertyAnimationListener != null) {
            propertyAnimationListener.onAnimationEnd(this.mView);
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.mCurrentValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
    }

    public void reversePoint() {
        setFloatValues(new float[]{this.mCurrentValue, 0.0f});
        if (this.mCurrentValue > 0.0f) {
            setCurrentPlayTime(0);
        }
    }

    public PropertyAnimator setAnimationListener(PropertyAnimationListener propertyAnimationListener) {
        this.mListener = propertyAnimationListener;
        return this;
    }

    public PropertyAnimator setDelay(long j2) {
        super.setStartDelay(j2);
        return this;
    }

    public PropertyAnimator setDuration(int i2) {
        return (PropertyAnimator) super.setDuration((long) i2);
    }

    public PropertyAnimator setInterpolator(Interpolator interpolator) {
        super.setInterpolator(interpolator);
        return this;
    }

    public void setStartPoint() {
        setFloatValues(new float[]{this.mCurrentValue, 1.0f});
        if (this.mCurrentValue > 0.0f) {
            setCurrentPlayTime(0);
        }
    }

    public PropertyAnimator withEndAction(final Runnable runnable) {
        if (runnable != null) {
            super.addListener(new SimpleAnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    runnable.run();
                }

                public void onAnimationEnd(Animator animator) {
                    runnable.run();
                }
            });
        }
        return this;
    }

    public PropertyAnimator withStartAction(final Runnable runnable) {
        if (runnable != null) {
            super.addListener(new SimpleAnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    runnable.run();
                }
            });
        }
        return this;
    }
}
