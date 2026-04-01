package com.samsung.android.gallery.plugins.panorama;

import android.animation.ValueAnimator;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PanoramaDelegate d;
    public final /* synthetic */ View e;
    public final /* synthetic */ float f;

    public /* synthetic */ e(PanoramaDelegate panoramaDelegate, float f5, View view) {
        this.d = panoramaDelegate;
        this.e = view;
        this.f = f5;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$introTransition$2(this.e, this.f, valueAnimator);
    }
}
