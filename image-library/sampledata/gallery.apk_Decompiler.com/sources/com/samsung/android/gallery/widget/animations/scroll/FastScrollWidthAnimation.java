package com.samsung.android.gallery.widget.animations.scroll;

import android.animation.ValueAnimator;
import com.samsung.android.gallery.widget.animations.scroll.FastScrollAnimation;
import com.samsung.scsp.media.api.d;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastScrollWidthAnimation extends FastScrollAnimation {
    public FastScrollWidthAnimation(FastScrollAnimation.SimpleAnimationSpec simpleAnimationSpec, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        super(simpleAnimationSpec, animatorUpdateListener);
    }

    public void animateTo(float f) {
        float floatValue = ((Float) Optional.ofNullable(this.mAnimator).map(new d(24)).orElse(Float.valueOf(1.0f))).floatValue();
        if (floatValue != f) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{floatValue, f});
            setAnimator(ofFloat);
            ofFloat.start();
        }
    }
}
