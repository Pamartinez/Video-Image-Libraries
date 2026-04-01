package androidx.core.util;

import android.animation.ValueAnimator;
import androidx.core.util.SeslFadingEdgeHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SeslFadingEdgeHelper.ColorAnimationManager d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Runnable g;

    public /* synthetic */ a(SeslFadingEdgeHelper.ColorAnimationManager colorAnimationManager, int i2, int i7, Runnable runnable) {
        this.d = colorAnimationManager;
        this.e = i2;
        this.f = i7;
        this.g = runnable;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$startAnimation$0(this.e, this.f, this.g, valueAnimator);
    }
}
