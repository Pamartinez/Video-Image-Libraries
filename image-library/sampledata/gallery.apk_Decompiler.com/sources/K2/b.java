package k2;

import android.animation.ValueAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ float d;
    public final /* synthetic */ d e;

    public b(d dVar, float f) {
        this.e = dVar;
        this.d = f;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.e.d(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.d);
    }
}
