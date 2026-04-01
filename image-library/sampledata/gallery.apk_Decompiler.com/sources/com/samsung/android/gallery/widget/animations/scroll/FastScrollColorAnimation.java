package com.samsung.android.gallery.widget.animations.scroll;

import android.animation.ValueAnimator;
import com.samsung.android.gallery.widget.animations.scroll.FastScrollAnimation;
import com.samsung.scsp.media.api.d;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastScrollColorAnimation extends FastScrollAnimation {
    public FastScrollColorAnimation(FastScrollAnimation.SimpleAnimationSpec simpleAnimationSpec, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        super(simpleAnimationSpec, animatorUpdateListener);
    }

    public void animateTo(int i2) {
        int intValue = ((Integer) Optional.ofNullable(this.mAnimator).map(new d(24)).orElse(0)).intValue();
        if (intValue != i2) {
            ValueAnimator ofArgb = ValueAnimator.ofArgb(new int[]{intValue, i2});
            setAnimator(ofArgb);
            ofArgb.start();
        }
    }
}
