package com.samsung.android.gallery.plugins.panorama;

import android.animation.ValueAnimator;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PanoramaDelegate d;
    public final /* synthetic */ View e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    public /* synthetic */ b(PanoramaDelegate panoramaDelegate, View view, int i2, int i7) {
        this.d = panoramaDelegate;
        this.e = view;
        this.f = i2;
        this.g = i7;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$outroTransition$3(this.e, this.f, this.g, valueAnimator);
    }
}
