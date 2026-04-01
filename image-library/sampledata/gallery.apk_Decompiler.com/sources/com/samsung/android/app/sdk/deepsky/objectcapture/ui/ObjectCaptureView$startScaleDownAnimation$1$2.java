package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.animation.Animator;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\u0006¨\u0006\n"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureView$startScaleDownAnimation$1$2", "Landroid/animation/Animator$AnimatorListener;", "Landroid/animation/Animator;", "animator", "Lme/x;", "onAnimationStart", "(Landroid/animation/Animator;)V", "onAnimationEnd", "onAnimationCancel", "onAnimationRepeat", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCaptureView$startScaleDownAnimation$1$2 implements Animator.AnimatorListener {
    final /* synthetic */ Rect $dndRect;
    final /* synthetic */ float $scaleFactor;
    final /* synthetic */ ObjectCaptureView this$0;

    public ObjectCaptureView$startScaleDownAnimation$1$2(ObjectCaptureView objectCaptureView, float f, Rect rect) {
        this.this$0 = objectCaptureView;
        this.$scaleFactor = f;
        this.$dndRect = rect;
    }

    public void onAnimationCancel(Animator animator) {
        j.e(animator, "animator");
    }

    public void onAnimationEnd(Animator animator) {
        j.e(animator, "animator");
        boolean touchProcessing = this.this$0.getTouchProcessing();
        LibLogger.d("ObjectCaptureView", "scale down animation end ! : " + touchProcessing);
        if (this.this$0.getTouchProcessing()) {
            this.this$0.startDragAndDrop(this.$scaleFactor, this.$dndRect);
            return;
        }
        ObjectCaptureViewListener access$getListener$p = this.this$0.listener;
        if (access$getListener$p != null) {
            access$getListener$p.showObjectCaptureResult(false);
        }
    }

    public void onAnimationRepeat(Animator animator) {
        j.e(animator, "animator");
    }

    public void onAnimationStart(Animator animator) {
        j.e(animator, "animator");
    }
}
