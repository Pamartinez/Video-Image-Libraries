package com.samsung.android.gallery.module.map.transition;

import B2.h;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import com.samsung.android.gallery.module.map.transition.AbsTask;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AnimateMarkerTask extends AbsTask {
    private final double[] mAnimateFrom;
    private final double[] mAnimateTo;
    /* access modifiers changed from: private */
    public final MarkerWithPosition mMarkerToAnimate;
    /* access modifiers changed from: private */
    public final boolean mRemoveOnComplete;

    public AnimateMarkerTask(boolean z, double[] dArr, double[] dArr2, MarkerWithPosition markerWithPosition, BaseMarkerManager baseMarkerManager) {
        this.mPriority = AbsTask.TASK_PRIORITY.ANIMATE;
        this.mRemoveOnComplete = z;
        this.mAnimateFrom = dArr2;
        this.mAnimateTo = dArr;
        this.mMarkerToAnimate = markerWithPosition;
        this.mMarkerManager = baseMarkerManager;
    }

    /* access modifiers changed from: private */
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f;
        float animatedFraction = valueAnimator.getAnimatedFraction();
        BaseMarkerManager baseMarkerManager = this.mMarkerManager;
        MarkerWithPosition markerWithPosition = this.mMarkerToAnimate;
        double[] dArr = this.mAnimateTo;
        double[] dArr2 = this.mAnimateFrom;
        if (this.mRemoveOnComplete) {
            f = 1.0f - animatedFraction;
        } else {
            f = animatedFraction;
        }
        baseMarkerManager.animateMarker(markerWithPosition, animatedFraction, dArr, dArr2, f);
    }

    public void perform(TransitionQueueScheduler transitionQueueScheduler) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(150);
        ofFloat.setInterpolator(TransitionOptions.TRANSITION_INTERPOLATOR);
        ofFloat.addUpdateListener(new h(12, this));
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (AnimateMarkerTask.this.mRemoveOnComplete) {
                    AnimateMarkerTask.this.mMarkerToAnimate.getMarker().remove();
                }
                AnimateMarkerTask.this.mMarkerManager = null;
            }
        });
        ofFloat.start();
    }

    public String tag() {
        return "AnimateMarkerTask";
    }

    public void destroy() {
    }
}
