package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.view.animation.Animation;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup$createOverflowAnimationListener$listener$1", "Landroid/view/animation/Animation$AnimationListener;", "Landroid/view/animation/Animation;", "animation", "Lme/x;", "onAnimationStart", "(Landroid/view/animation/Animation;)V", "onAnimationEnd", "onAnimationRepeat", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCapturePopup$createOverflowAnimationListener$listener$1 implements Animation.AnimationListener {
    final /* synthetic */ ObjectCapturePopup this$0;

    public ObjectCapturePopup$createOverflowAnimationListener$listener$1(ObjectCapturePopup objectCapturePopup) {
        this.this$0 = objectCapturePopup;
    }

    /* access modifiers changed from: private */
    public static final void onAnimationEnd$lambda$0(ObjectCapturePopup objectCapturePopup) {
        objectCapturePopup.setPanelsStatesAtRestingPosition();
        objectCapturePopup.setContentAreaAsTouchableSurface();
    }

    public void onAnimationEnd(Animation animation) {
        j.e(animation, "animation");
        this.this$0.contentContainer.post(new c(this.this$0, 1));
    }

    public void onAnimationRepeat(Animation animation) {
        j.e(animation, "animation");
    }

    public void onAnimationStart(Animation animation) {
        j.e(animation, "animation");
        this.this$0.overflowButton.setEnabled(false);
        this.this$0.mainPanel.setVisibility(0);
        this.this$0.overflowPanel.setVisibility(0);
    }
}
