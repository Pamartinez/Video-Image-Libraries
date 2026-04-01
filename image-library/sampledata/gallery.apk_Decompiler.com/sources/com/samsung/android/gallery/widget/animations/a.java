package com.samsung.android.gallery.widget.animations;

import android.animation.Animator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Animator e;

    public /* synthetic */ a(Animator animator, int i2) {
        this.d = i2;
        this.e = animator;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Animator animator = this.e;
        Animator.AnimatorListener animatorListener = (Animator.AnimatorListener) obj;
        switch (i2) {
            case 0:
                animatorListener.onAnimationStart(animator);
                return;
            case 1:
                animatorListener.onAnimationRepeat(animator);
                return;
            case 2:
                animatorListener.onAnimationEnd(animator);
                return;
            case 3:
                animatorListener.onAnimationCancel(animator);
                return;
            case 4:
                animatorListener.onAnimationCancel(animator);
                return;
            case 5:
                animatorListener.onAnimationStart(animator);
                return;
            case 6:
                animatorListener.onAnimationEnd(animator);
                return;
            default:
                animatorListener.onAnimationRepeat(animator);
                return;
        }
    }
}
