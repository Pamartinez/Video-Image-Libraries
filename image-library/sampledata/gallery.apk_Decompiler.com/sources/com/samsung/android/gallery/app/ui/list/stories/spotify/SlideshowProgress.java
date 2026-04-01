package com.samsung.android.gallery.app.ui.list.stories.spotify;

import android.animation.ValueAnimator;
import android.widget.ProgressBar;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SlideshowProgress {
    Consumer<Integer> mCallback;
    int mCount;
    int mPosition;
    int mPositionOffset;
    ValueAnimator mProgressAnimator;
    ProgressBar mProgressBar;
    Boolean mProgressPaused;

    private int getViewPosition(int i2) {
        return i2 - this.mPositionOffset;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startNext$0(AtomicBoolean atomicBoolean, ValueAnimator valueAnimator) {
        int animatedFraction = (int) (valueAnimator.getAnimatedFraction() * 100.0f);
        int viewPosition = (getViewPosition(this.mPosition) * 100) + animatedFraction;
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setProgress(viewPosition);
        }
        if (animatedFraction > 94 && atomicBoolean.getAndSet(false) && this.mCallback != null) {
            Log.d("SlideshowProgress", "onAnimationUpdate#notify", Integer.valueOf(getViewPosition(this.mPosition)), Integer.valueOf(viewPosition), Integer.valueOf(animatedFraction));
            this.mCallback.accept(Integer.valueOf(this.mPosition));
        }
    }

    public SlideshowProgress bindView(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
        return this;
    }

    public void cancelNext() {
        ValueAnimator valueAnimator = this.mProgressAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mProgressAnimator = null;
        }
    }

    public void moveTo(int i2) {
        cancelNext();
        if (this.mProgressBar != null && this.mCount > 0) {
            int viewPosition = (getViewPosition(i2) % this.mCount) + this.mPositionOffset;
            this.mPosition = viewPosition;
            this.mProgressBar.setProgress(getViewPosition(viewPosition) * 100, true);
            startNext();
        }
    }

    public void pause() {
        this.mProgressPaused = Boolean.TRUE;
        cancelNext();
    }

    public void release() {
        this.mProgressBar = null;
        this.mCallback = null;
        cancelNext();
    }

    public void resume() {
        Boolean bool = this.mProgressPaused;
        if (bool != null && bool.booleanValue()) {
            this.mProgressPaused = null;
            startNext();
        }
    }

    public SlideshowProgress setCallback(Consumer<Integer> consumer) {
        this.mCallback = consumer;
        return this;
    }

    public SlideshowProgress setCount(int i2) {
        this.mCount = i2;
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setMax(i2 * 100);
        }
        return this;
    }

    public SlideshowProgress setOffset(int i2) {
        this.mPosition = i2;
        this.mPositionOffset = i2;
        return this;
    }

    public void startNext() {
        if (this.mProgressBar != null && this.mCount != 0) {
            Log.d("SlideshowProgress", "startNext", Integer.valueOf(this.mPosition), Integer.valueOf(this.mPositionOffset));
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            ValueAnimator ignoreDurationScaleIfDisabled = SimpleAnimator.ignoreDurationScaleIfDisabled(ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}));
            this.mProgressAnimator = ignoreDurationScaleIfDisabled;
            ignoreDurationScaleIfDisabled.setDuration(5300);
            this.mProgressAnimator.addUpdateListener(new a(this, atomicBoolean));
            this.mProgressAnimator.start();
        }
    }

    public boolean support() {
        if (this.mProgressBar != null) {
            return true;
        }
        return false;
    }
}
