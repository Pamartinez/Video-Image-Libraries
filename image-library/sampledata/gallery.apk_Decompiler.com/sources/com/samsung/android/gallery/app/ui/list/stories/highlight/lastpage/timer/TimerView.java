package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.timer;

import H7.x;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimerView implements ValueAnimator.AnimatorUpdateListener {
    private ValueAnimator mAnimator;
    private ProgressBar mCircleProgress;
    private TextView mCountView;
    private int mDuration = Encode.BitRate.VIDEO_HD_BITRATE;
    /* access modifiers changed from: private */
    public Consumer<Void> mOnFinishListener;
    private float mProgress = 0.0f;
    private Consumer<Float> mProgressListener;
    private int mTime = 8;

    private void clearAnimator() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mAnimator.removeAllListeners();
            this.mAnimator.removeUpdateListener(this);
            this.mAnimator = null;
        }
    }

    private ValueAnimator createAnimator() {
        ValueAnimator duration = SimpleAnimator.ignoreDurationScaleIfDisabled(ValueAnimator.ofFloat(new float[]{1.0f, 0.0f})).setDuration((long) this.mDuration);
        duration.setInterpolator(new LinearInterpolator());
        duration.addUpdateListener(this);
        duration.addListener(new SimpleAnimatorListener() {
            boolean interrupted;

            public void onAnimationCancel(Animator animator) {
                this.interrupted = true;
            }

            /* JADX WARNING: type inference failed for: r1v2, types: [java.util.function.Consumer, java.lang.Object] */
            public void onAnimationEnd(Animator animator) {
                if (!this.interrupted) {
                    Optional.ofNullable(TimerView.this.mOnFinishListener).ifPresent(new Object());
                }
            }
        });
        return duration;
    }

    private int getDisplayNumber() {
        return Math.max(((int) Math.ceil((double) (getDisplayProgress() * ((float) this.mTime)))) - 1, 0);
    }

    private float getDisplayProgress() {
        return 1.0f - this.mProgress;
    }

    private void invalidate() {
        String valueOf = String.valueOf(getDisplayNumber());
        if (!valueOf.contentEquals(this.mCountView.getText())) {
            this.mCountView.setText(valueOf);
        }
        this.mCircleProgress.setProgress((int) (getDisplayProgress() * 100.0f));
    }

    private void notifyProgress(float f) {
        Optional.ofNullable(this.mProgressListener).ifPresent(new x(f, 6));
    }

    private void start() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null || !valueAnimator.isPaused()) {
            clearAnimator();
            ValueAnimator createAnimator = createAnimator();
            this.mAnimator = createAnimator;
            createAnimator.setCurrentPlayTime((long) ((int) (this.mProgress * ((float) this.mDuration))));
            this.mAnimator.start();
            invalidate();
            return;
        }
        this.mAnimator.resume();
    }

    public void clear() {
        clearAnimator();
    }

    public void destroy() {
        reset();
        this.mOnFinishListener = null;
    }

    public void initView(View view) {
        this.mCircleProgress = (ProgressBar) view.findViewById(R.id.progress_bar);
        this.mCountView = (TextView) view.findViewById(R.id.count);
        reset();
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.mProgress = valueAnimator.getAnimatedFraction();
        invalidate();
        notifyProgress(this.mProgress);
    }

    public void pause() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mAnimator.pause();
        }
    }

    public void reset() {
        clear();
        setProgress(0.0f);
        notifyProgress(this.mProgress);
    }

    public void resume() {
        start();
    }

    public void setOnFinishListener(Consumer<Void> consumer) {
        this.mOnFinishListener = consumer;
    }

    public void setProgress(float f) {
        this.mProgress = f;
        invalidate();
    }

    public void setProgressListener(Consumer<Float> consumer) {
        this.mProgressListener = consumer;
    }

    public TimerView setTime(int i2) {
        this.mTime = i2;
        this.mDuration = i2 * 1000;
        return this;
    }
}
