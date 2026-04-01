package com.samsung.android.gallery.widget.livemotion.zoom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsZoomHandler {
    /* access modifiers changed from: private */
    public final String TAG = getClass().getSimpleName();
    /* access modifiers changed from: private */
    public Animator mRestore;
    private Animator mZoomIn;

    public abstract AnimatorSet createRestoreAnimator(View view, int i2, float f);

    public float getMaxScale() {
        return 2.31f;
    }

    public float getMinScale() {
        return 1.0f;
    }

    public float getThreshold() {
        return 0.003f;
    }

    public boolean isAnimating() {
        if (this.mZoomIn == null && this.mRestore == null) {
            return false;
        }
        return true;
    }

    public abstract void move(View view, TouchCoordinates touchCoordinates, float f);

    public abstract void onScale(View view, float f);

    public abstract void onScaleBegin(View view, float f, float f5);

    public void reset() {
        this.mZoomIn = null;
        this.mRestore = null;
    }

    public void restore(View view, final Consumer<Boolean> consumer, int i2, float f) {
        AnimatorSet createRestoreAnimator = createRestoreAnimator(view, i2, f);
        this.mRestore = createRestoreAnimator;
        createRestoreAnimator.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                AbsZoomHandler.this.mRestore = null;
                Log.d(AbsZoomHandler.this.TAG, "zoomOut end");
                Consumer consumer = consumer;
                if (consumer != null) {
                    consumer.accept(Boolean.TRUE);
                }
            }
        });
        this.mRestore.start();
    }

    public boolean restore(View view, float f) {
        if (this.mRestore != null) {
            return false;
        }
        Animator animator = this.mZoomIn;
        if (animator != null) {
            animator.cancel();
        }
        restore(view, (Consumer<Boolean>) null, 0, f);
        return true;
    }
}
