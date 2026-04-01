package com.samsung.android.gallery.plugins.motionphoto;

import B2.h;
import Ba.p;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProgressAnimator {
    private static final CharSequence TAG = "ProgressAni";
    private final ValueAnimator animator;
    private float lastSeekFraction;
    private final Consumer<Float> onUpdate;
    boolean started;
    long startedTime;
    private final View view;

    public ProgressAnimator(View view2, long j2, Consumer<Float> consumer) {
        this.view = view2;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animator = ofFloat;
        ofFloat.setDuration(j2);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.addUpdateListener(new h(1, this));
        this.onUpdate = consumer;
    }

    /* access modifiers changed from: private */
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        if (animatedFraction >= this.lastSeekFraction) {
            this.lastSeekFraction = 0.0f;
            if (animatedFraction == 1.0f) {
                this.started = false;
            }
            this.onUpdate.accept(Float.valueOf(animatedFraction));
        }
    }

    public void pause() {
        Log.d(TAG, "pause");
        this.animator.pause();
        this.lastSeekFraction = 0.0f;
    }

    public void resume() {
        if (this.animator.isPaused()) {
            this.lastSeekFraction = this.animator.getAnimatedFraction();
            this.animator.resume();
            this.startedTime = System.currentTimeMillis();
            Log.d(TAG, "resume", Long.valueOf(this.animator.getCurrentPlayTime()), Float.valueOf(this.lastSeekFraction));
        } else if (!this.animator.isStarted()) {
            start();
        }
    }

    public void seekTo(float f) {
        float f5 = f / 100.0f;
        this.lastSeekFraction = f5;
        this.animator.setCurrentFraction(f5);
    }

    public void setProgress(float f) {
        if (System.currentTimeMillis() - this.startedTime > 300) {
            float animatedFraction = this.animator.getAnimatedFraction();
            float f5 = f / 100.0f;
            if (!this.animator.isStarted() || Math.abs(animatedFraction - f5) > 0.05f) {
                this.animator.setCurrentFraction(f5);
                if (this.started && !this.animator.isStarted()) {
                    View view2 = this.view;
                    ValueAnimator valueAnimator = this.animator;
                    Objects.requireNonNull(valueAnimator);
                    view2.postOnAnimation(new p(valueAnimator, 0));
                }
            }
        }
    }

    public void start() {
        Log.d(TAG, "start");
        this.startedTime = System.currentTimeMillis();
        this.started = true;
    }
}
