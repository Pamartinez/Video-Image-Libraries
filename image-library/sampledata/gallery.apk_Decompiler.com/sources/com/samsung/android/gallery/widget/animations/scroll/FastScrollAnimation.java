package com.samsung.android.gallery.widget.animations.scroll;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.Interpolator;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FastScrollAnimation {
    protected ValueAnimator mAnimator;
    private final SimpleAnimationSpec mDefaultAnimationSpec;
    private final ValueAnimator.AnimatorUpdateListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SimpleAnimationSpec {
        private final long mDuration;
        private final Interpolator mInterpolator;

        public SimpleAnimationSpec(long j2, Interpolator interpolator) {
            this.mDuration = j2;
            this.mInterpolator = interpolator;
        }

        public void invoke(ValueAnimator valueAnimator) {
            valueAnimator.setDuration(this.mDuration);
            valueAnimator.setInterpolator(this.mInterpolator);
        }
    }

    public FastScrollAnimation(SimpleAnimationSpec simpleAnimationSpec, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.mDefaultAnimationSpec = simpleAnimationSpec;
        this.mListener = animatorUpdateListener;
    }

    private void setListener(ValueAnimator valueAnimator) {
        valueAnimator.addUpdateListener(this.mListener);
        valueAnimator.addListener(new SimpleAnimatorListener() {
            public void onAnimationCancel(Animator animator) {
                FastScrollAnimation.this.dispose();
            }

            public void onAnimationEnd(Animator animator) {
                FastScrollAnimation.this.dispose();
            }
        });
    }

    public void dispose() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.mAnimator.cancel();
        }
    }

    public void setAnimator(ValueAnimator valueAnimator) {
        this.mAnimator = valueAnimator;
        this.mDefaultAnimationSpec.invoke(valueAnimator);
        setListener(valueAnimator);
    }
}
