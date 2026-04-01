package com.samsung.android.sesl.visualeffect.lighteffects.guidinglight;

import android.animation.Animator;
import android.os.Handler;
import bd.a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\u0006¨\u0006\n"}, d2 = {"com/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationFactory$Companion$createAnimation$1$1", "Landroid/animation/Animator$AnimatorListener;", "Landroid/animation/Animator;", "animation", "Lme/x;", "onAnimationStart", "(Landroid/animation/Animator;)V", "onAnimationEnd", "onAnimationCancel", "onAnimationRepeat", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AnimationFactory$Companion$createAnimation$1$1 implements Animator.AnimatorListener {
    final /* synthetic */ Handler $handler;
    final /* synthetic */ Runnable $onCancel;
    final /* synthetic */ Runnable $onEnd;
    final /* synthetic */ Runnable $onStart;

    public AnimationFactory$Companion$createAnimation$1$1(Handler handler, Runnable runnable, Runnable runnable2, Runnable runnable3) {
        this.$handler = handler;
        this.$onStart = runnable;
        this.$onEnd = runnable2;
        this.$onCancel = runnable3;
    }

    /* access modifiers changed from: private */
    public static final void onAnimationCancel$lambda$2(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public static final void onAnimationEnd$lambda$1(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public static final void onAnimationStart$lambda$0(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    public void onAnimationCancel(Animator animator) {
        j.e(animator, "animation");
        this.$handler.post(new a(this.$onCancel, 2));
    }

    public void onAnimationEnd(Animator animator) {
        j.e(animator, "animation");
        this.$handler.post(new a(this.$onEnd, 1));
    }

    public void onAnimationRepeat(Animator animator) {
        j.e(animator, "animation");
    }

    public void onAnimationStart(Animator animator) {
        j.e(animator, "animation");
        this.$handler.post(new a(this.$onStart, 0));
    }
}
