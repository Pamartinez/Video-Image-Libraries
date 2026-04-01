package com.samsung.android.gallery.widget.photoview;

import android.animation.ValueAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PhotoViewDelegate d;

    public /* synthetic */ o(PhotoViewDelegate photoViewDelegate) {
        this.d = photoViewDelegate;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$startBlendingAni$0(valueAnimator);
    }
}
