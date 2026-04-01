package com.samsung.android.gallery.widget.animations;

import android.view.animation.AlphaAnimation;
import android.view.animation.Transformation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FadeAnimation extends AlphaAnimation {
    private final Runnable mFrameCallback;

    public FadeAnimation(float f, float f5, Runnable runnable) {
        super(f, f5);
        this.mFrameCallback = runnable;
    }

    public void applyTransformation(float f, Transformation transformation) {
        super.applyTransformation(f, transformation);
        Runnable runnable = this.mFrameCallback;
        if (runnable != null) {
            runnable.run();
        }
    }
}
