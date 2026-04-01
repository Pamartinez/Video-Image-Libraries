package com.samsung.android.gallery.widget.photoview;

import B2.h;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.ValueAnimatorIgnoreSetting;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScaleAnimation {
    /* access modifiers changed from: private */
    public static final List<Integer> VALID_EASING_STYLES = Arrays.asList(new Integer[]{2, 1});
    /* access modifiers changed from: private */
    public ScaleAnimationListener mAnimationListener;
    private long mDuration;
    private int mEasing;
    private PointF mEndPointOfView;
    private boolean mInterruptible;
    private int mOrigin;
    private final Animator.AnimatorListener mScaleAnimationListener;
    private float mScaleEnd;
    private float mScaleStart;
    private PointF mStartPointOfView;
    private final long mStartTime;
    private PointF mTargetSCenter;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public ScaleAnimationListener animationListener;
        /* access modifiers changed from: private */
        public long duration;
        /* access modifiers changed from: private */
        public int easing = 2;
        /* access modifiers changed from: private */
        public PointF endPointOfView;
        /* access modifiers changed from: private */
        public boolean interruptible = false;
        /* access modifiers changed from: private */
        public int origin = 1;
        /* access modifiers changed from: private */
        public final float scaleEnd;
        /* access modifiers changed from: private */
        public final float scaleStart;
        /* access modifiers changed from: private */
        public PointF startPointOfView;
        /* access modifiers changed from: private */
        public PointF targetSCenter;

        public Builder(float f, float f5) {
            this.scaleStart = f;
            this.scaleEnd = f5;
        }

        public ScaleAnimation build() {
            return new ScaleAnimation(this, 0);
        }

        public Builder setAnimationListener(ScaleAnimationListener scaleAnimationListener) {
            this.animationListener = scaleAnimationListener;
            return this;
        }

        public Builder setEndPointOfView(PointF pointF) {
            this.endPointOfView = pointF;
            return this;
        }

        public Builder setStartPointOfView(PointF pointF) {
            this.startPointOfView = pointF;
            return this;
        }

        public Builder setTargetSCenter(PointF pointF) {
            this.targetSCenter = pointF;
            return this;
        }

        public Builder withDuration(long j2) {
            this.duration = j2;
            return this;
        }

        public Builder withEasing(int i2) {
            if (ScaleAnimation.VALID_EASING_STYLES.contains(Integer.valueOf(i2))) {
                this.easing = i2;
                return this;
            }
            throw new IllegalArgumentException(C0086a.i(i2, "Unknown easing type: "));
        }

        public Builder withInterruptible() {
            this.interruptible = true;
            return this;
        }

        public Builder withOrigin(int i2) {
            this.origin = i2;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ScaleAnimationListener {
        void onAnimationUpdate();
    }

    public /* synthetic */ ScaleAnimation(Builder builder, int i2) {
        this(builder);
    }

    private float ease(int i2, long j2, float f, float f5, long j3) {
        if (i2 == 1) {
            return easeOutQuad(j2, f, f5, j3);
        }
        if (i2 == 2) {
            return easeInOutQuad(j2, f, f5, j3);
        }
        throw new IllegalStateException(C0086a.i(i2, "Unexpected easing type: "));
    }

    private float easeInOutQuad(long j2, float f, float f5, long j3) {
        float f8 = ((float) j2) / (((float) j3) / 2.0f);
        if (f8 < 1.0f) {
            return ((f5 / 2.0f) * f8 * f8) + f;
        }
        float f10 = f8 - 1.0f;
        return ((((f10 - 2.0f) * f10) - 1.0f) * ((-f5) / 2.0f)) + f;
    }

    private float easeOutQuad(long j2, float f, float f5, long j3) {
        float f8 = ((float) j2) / ((float) j3);
        return C0212a.a(f8, 2.0f, (-f5) * f8, f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ValueAnimator valueAnimator) {
        ScaleAnimationListener scaleAnimationListener = this.mAnimationListener;
        if (scaleAnimationListener != null) {
            scaleAnimationListener.onAnimationUpdate();
        }
    }

    public float getEaseFocusX(long j2) {
        long min = Math.min(j2 - this.mStartTime, this.mDuration);
        int i2 = this.mEasing;
        float f = this.mStartPointOfView.x;
        return ease(i2, min, f, this.mEndPointOfView.x - f, this.mDuration);
    }

    public float getEaseFocusY(long j2) {
        long min = Math.min(j2 - this.mStartTime, this.mDuration);
        int i2 = this.mEasing;
        float f = this.mStartPointOfView.y;
        return ease(i2, min, f, this.mEndPointOfView.y - f, this.mDuration);
    }

    public float getEaseScale(long j2) {
        long min = Math.min(j2 - this.mStartTime, this.mDuration);
        int i2 = this.mEasing;
        float f = this.mScaleStart;
        return ease(i2, min, f, this.mScaleEnd - f, this.mDuration);
    }

    public PointF getStartPointOfView() {
        return this.mStartPointOfView;
    }

    public float getStartScale() {
        return this.mScaleStart;
    }

    public PointF getTargetSCenter() {
        return this.mTargetSCenter;
    }

    public float getTargetScale() {
        return this.mScaleEnd;
    }

    public boolean isFinished(long j2) {
        if (j2 - this.mStartTime > this.mDuration) {
            return true;
        }
        return false;
    }

    public boolean isFixedScale() {
        if (this.mScaleEnd == this.mScaleStart) {
            return true;
        }
        return false;
    }

    public boolean isInterruptible() {
        return this.mInterruptible;
    }

    private ScaleAnimation(Builder builder) {
        AnonymousClass1 r0 = new Animator.AnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                if (ScaleAnimation.this.mAnimationListener != null) {
                    ScaleAnimation.this.mAnimationListener.onAnimationUpdate();
                }
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }
        };
        this.mScaleAnimationListener = r0;
        this.mStartTime = System.currentTimeMillis();
        this.mOrigin = builder.origin;
        this.mEasing = builder.easing;
        this.mInterruptible = builder.interruptible;
        this.mDuration = builder.duration;
        this.mScaleStart = builder.scaleStart;
        this.mScaleEnd = builder.scaleEnd;
        this.mTargetSCenter = builder.targetSCenter;
        this.mStartPointOfView = builder.startPointOfView;
        this.mEndPointOfView = builder.endPointOfView;
        this.mAnimationListener = builder.animationListener;
        ValueAnimator ofFloat = ValueAnimatorIgnoreSetting.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(this.mDuration);
        ofFloat.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_70));
        ofFloat.addUpdateListener(new h(3, this));
        ofFloat.addListener(r0);
        ofFloat.start();
        Log.d("ScaleAnimation", "ScaleAnimation{" + this.mScaleStart + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mScaleEnd + " (" + this.mTargetSCenter.x + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mTargetSCenter.y + "), S(" + this.mStartPointOfView.x + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mStartPointOfView.y + "), E(" + this.mEndPointOfView.x + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mEndPointOfView.y + ")}");
    }
}
