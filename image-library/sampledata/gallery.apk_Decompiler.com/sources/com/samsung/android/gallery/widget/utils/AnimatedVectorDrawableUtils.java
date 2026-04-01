package com.samsung.android.gallery.widget.utils;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AnimatedVectorDrawableUtils {
    public static void play(ImageView imageView, final boolean z) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AnimatedVectorDrawable) {
                final AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
                    public void onAnimationEnd(Drawable drawable) {
                        if (z) {
                            animatedVectorDrawable.start();
                        }
                    }
                });
                animatedVectorDrawable.start();
            }
        }
    }

    public static void stop(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AnimatedVectorDrawable) {
                AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                animatedVectorDrawable.stop();
                animatedVectorDrawable.clearAnimationCallbacks();
            }
        }
    }
}
