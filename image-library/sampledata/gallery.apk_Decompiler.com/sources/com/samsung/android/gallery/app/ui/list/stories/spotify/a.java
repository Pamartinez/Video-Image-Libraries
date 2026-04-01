package com.samsung.android.gallery.app.ui.list.stories.spotify;

import android.animation.ValueAnimator;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SlideshowProgress d;
    public final /* synthetic */ AtomicBoolean e;

    public /* synthetic */ a(SlideshowProgress slideshowProgress, AtomicBoolean atomicBoolean) {
        this.d = slideshowProgress;
        this.e = atomicBoolean;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$startNext$0(this.e, valueAnimator);
    }
}
