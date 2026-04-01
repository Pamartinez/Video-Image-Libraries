package com.samsung.android.sesl.visualeffect.lighteffects.radialgrad;

import Ae.b;
import android.animation.Animator;
import android.animation.ValueAnimator;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\u0006R\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"com/samsung/android/sesl/visualeffect/lighteffects/radialgrad/WiggleAnimationConfig$build$xFormAnim$4$2", "Landroid/animation/Animator$AnimatorListener;", "Landroid/animation/Animator;", "animation", "Lme/x;", "onAnimationStart", "(Landroid/animation/Animator;)V", "onAnimationEnd", "onAnimationCancel", "onAnimationRepeat", "", "cancelled", "Z", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WiggleAnimationConfig$build$xFormAnim$4$2 implements Animator.AnimatorListener {
    final /* synthetic */ b $onAnimationFinish;
    private boolean cancelled;

    public WiggleAnimationConfig$build$xFormAnim$4$2(b bVar) {
        this.$onAnimationFinish = bVar;
    }

    public void onAnimationCancel(Animator animator) {
        j.e(animator, "animation");
        this.cancelled = true;
    }

    public void onAnimationEnd(Animator animator) {
        j.e(animator, "animation");
        if (!this.cancelled) {
            this.$onAnimationFinish.invoke((ValueAnimator) animator);
        }
    }

    public void onAnimationRepeat(Animator animator) {
        j.e(animator, "animation");
    }

    public void onAnimationStart(Animator animator) {
        j.e(animator, "animation");
    }
}
