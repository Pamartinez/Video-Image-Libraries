package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\u0006¨\u0006\n"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/MaskedImageView$initViewUpdateAnimator$2", "Landroid/animation/Animator$AnimatorListener;", "Landroid/animation/Animator;", "animation", "Lme/x;", "onAnimationStart", "(Landroid/animation/Animator;)V", "onAnimationEnd", "onAnimationCancel", "onAnimationRepeat", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MaskedImageView$initViewUpdateAnimator$2 implements Animator.AnimatorListener {
    final /* synthetic */ AnimatorSet $animatorSet;
    final /* synthetic */ MaskedImageView this$0;

    public MaskedImageView$initViewUpdateAnimator$2(MaskedImageView maskedImageView, AnimatorSet animatorSet) {
        this.this$0 = maskedImageView;
        this.$animatorSet = animatorSet;
    }

    public void onAnimationCancel(Animator animator) {
        j.e(animator, "animation");
        this.$animatorSet.removeAllListeners();
        this.this$0.recycleBitmap();
    }

    public void onAnimationEnd(Animator animator) {
        j.e(animator, "animation");
        this.this$0.setAlpha(0.0f);
        this.$animatorSet.cancel();
        this.$animatorSet.removeAllListeners();
        this.this$0.recycleBitmap();
    }

    public void onAnimationRepeat(Animator animator) {
        j.e(animator, "animation");
    }

    public void onAnimationStart(Animator animator) {
        j.e(animator, "animation");
    }
}
