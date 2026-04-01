package com.samsung.o3dp.lib3dphotography.animation.animations;

import android.animation.ValueAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TouchGestureAnimation e;

    public /* synthetic */ c(TouchGestureAnimation touchGestureAnimation, int i2) {
        this.d = i2;
        this.e = touchGestureAnimation;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        TouchGestureAnimation touchGestureAnimation = this.e;
        switch (i2) {
            case 0:
                touchGestureAnimation.lambda$new$0(valueAnimator);
                return;
            default:
                touchGestureAnimation.lambda$new$1(valueAnimator);
                return;
        }
    }
}
