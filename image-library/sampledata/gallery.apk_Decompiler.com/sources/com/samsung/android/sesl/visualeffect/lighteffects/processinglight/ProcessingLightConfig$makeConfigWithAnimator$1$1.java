package com.samsung.android.sesl.visualeffect.lighteffects.processinglight;

import android.animation.Animator;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import bc.C0584a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\u0006¨\u0006\n"}, d2 = {"com/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$makeConfigWithAnimator$1$1", "Landroid/animation/Animator$AnimatorListener;", "Landroid/animation/Animator;", "animation", "Lme/x;", "onAnimationStart", "(Landroid/animation/Animator;)V", "onAnimationEnd", "onAnimationCancel", "onAnimationRepeat", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ProcessingLightConfig$makeConfigWithAnimator$1$1 implements Animator.AnimatorListener {
    final /* synthetic */ ProcessingLightConfig $this_apply;

    public ProcessingLightConfig$makeConfigWithAnimator$1$1(ProcessingLightConfig processingLightConfig) {
        this.$this_apply = processingLightConfig;
    }

    /* access modifiers changed from: private */
    public static final void onAnimationRepeat$lambda$0(Animator animator) {
        animator.resume();
    }

    public void onAnimationCancel(Animator animator) {
        j.e(animator, "animation");
    }

    public void onAnimationEnd(Animator animator) {
        j.e(animator, "animation");
        Log.i("ProcessingLightConfig", "Processing - onAnimationEnd");
    }

    public void onAnimationRepeat(Animator animator) {
        j.e(animator, "animation");
        if (this.$this_apply.getRepeatDelay() > 0) {
            animator.pause();
            new Handler(Looper.getMainLooper()).postDelayed(new C0584a(15, animator), this.$this_apply.getRepeatDelay());
        }
    }

    public void onAnimationStart(Animator animator) {
        j.e(animator, "animation");
        long durationInMillis = this.$this_apply.getDurationInMillis();
        long repeatDelay = this.$this_apply.getRepeatDelay();
        StringBuilder j2 = N2.j.j(durationInMillis, "Processing - onAnimationStart duration:", "L repeatDelay:");
        j2.append(repeatDelay);
        j2.append("L");
        Log.i("ProcessingLightConfig", j2.toString());
    }
}
