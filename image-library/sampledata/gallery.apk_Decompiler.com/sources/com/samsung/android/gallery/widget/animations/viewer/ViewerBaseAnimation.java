package com.samsung.android.gallery.widget.animations.viewer;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.PathInterpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewerBaseAnimation extends Animation {
    protected final String TAG = getClass().getSimpleName();
    boolean mRunning;
    View mView;

    public ViewerBaseAnimation() {
        setDuration(400);
        setInterpolator(new PathInterpolator(0.4f, 0.2f, 0.0f, 1.0f));
        setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ViewerBaseAnimation.this.onAnimationEnded();
            }

            public void onAnimationStart(Animation animation) {
                ViewerBaseAnimation.this.onAnimationStarted();
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void onAnimationEnded() {
        this.mRunning = false;
    }

    public void onAnimationStarted() {
    }
}
