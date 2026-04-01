package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle;

import Ae.a;
import android.animation.Animator;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\u0006¨\u0006\n"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$showHandleAnimation$1$2", "Landroid/animation/Animator$AnimatorListener;", "Landroid/animation/Animator;", "animation", "Lme/x;", "onAnimationStart", "(Landroid/animation/Animator;)V", "onAnimationEnd", "onAnimationCancel", "onAnimationRepeat", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Handle$showHandleAnimation$1$2 implements Animator.AnimatorListener {
    final /* synthetic */ a $handleAnimationCallback;
    final /* synthetic */ Handle this$0;

    public Handle$showHandleAnimation$1$2(Handle handle, a aVar) {
        this.this$0 = handle;
        this.$handleAnimationCallback = aVar;
    }

    public void onAnimationCancel(Animator animator) {
        j.e(animator, "animation");
        if (this.this$0.isAnimationActive) {
            this.$handleAnimationCallback.invoke();
        }
    }

    public void onAnimationEnd(Animator animator) {
        j.e(animator, "animation");
        if (this.this$0.isAnimationActive) {
            this.$handleAnimationCallback.invoke();
        }
    }

    public void onAnimationRepeat(Animator animator) {
        j.e(animator, "animation");
    }

    public void onAnimationStart(Animator animator) {
        j.e(animator, "animation");
    }
}
