package androidx.core.widget;

import android.animation.ValueAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SeslGoToTopController e;

    public /* synthetic */ c(SeslGoToTopController seslGoToTopController, int i2) {
        this.d = i2;
        this.e = seslGoToTopController;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        SeslGoToTopController seslGoToTopController = this.e;
        switch (i2) {
            case 0:
                seslGoToTopController.lambda$initAnimators$1(valueAnimator);
                return;
            default:
                seslGoToTopController.lambda$initAnimators$2(valueAnimator);
                return;
        }
    }
}
