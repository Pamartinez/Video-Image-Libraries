package com.samsung.android.gallery.widget.animations;

import android.animation.ValueAnimator;
import android.view.View;
import com.samsung.android.gallery.widget.story.CoverGradientBlur;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ n(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                CoverGradientBlur.apply((View) obj);
                return;
            default:
                ((SimpleShrinkHandler) obj).lambda$createBackgroundColorAnimator$1(valueAnimator);
                return;
        }
    }
}
