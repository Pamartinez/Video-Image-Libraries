package com.google.android.material.appbar;

import android.animation.Animator;
import com.google.android.material.appbar.AppBarLayout;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements Animator.AnimatorListener {
    public final /* synthetic */ AppBarLayout d;
    public final /* synthetic */ AppBarLayout.BaseBehavior e;

    public b(AppBarLayout.BaseBehavior baseBehavior, AppBarLayout appBarLayout) {
        this.e = baseBehavior;
        this.d = appBarLayout;
    }

    public final void onAnimationCancel(Animator animator) {
        List<Animator.AnimatorListener> list = this.d.mAnimateOffsetListener;
        if (list != null) {
            for (Animator.AnimatorListener onAnimationCancel : list) {
                onAnimationCancel.onAnimationCancel(animator);
            }
        }
    }

    public final void onAnimationEnd(Animator animator) {
        AppBarLayout appBarLayout = this.d;
        List<Animator.AnimatorListener> list = appBarLayout.mAnimateOffsetListener;
        if (list != null) {
            for (Animator.AnimatorListener onAnimationEnd : list) {
                onAnimationEnd.onAnimationEnd(animator);
            }
        }
        appBarLayout.seslStopForceExpanded();
    }

    public final void onAnimationRepeat(Animator animator) {
        List<Animator.AnimatorListener> list = this.d.mAnimateOffsetListener;
        if (list != null) {
            for (Animator.AnimatorListener onAnimationRepeat : list) {
                onAnimationRepeat.onAnimationRepeat(animator);
            }
        }
    }

    public final void onAnimationStart(Animator animator) {
        List<Animator.AnimatorListener> list = this.d.mAnimateOffsetListener;
        if (list != null) {
            for (Animator.AnimatorListener onAnimationStart : list) {
                onAnimationStart.onAnimationStart(animator);
            }
        }
        this.e.getClass();
    }
}
