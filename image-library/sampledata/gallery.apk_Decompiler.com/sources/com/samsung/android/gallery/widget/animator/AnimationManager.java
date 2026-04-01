package com.samsung.android.gallery.widget.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import java.util.ArrayList;
import java.util.Iterator;
import o6.B;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AnimationManager implements Animator.AnimatorListener {
    private AnimatorSet mAnimSet;
    private boolean mAnimationCancelled = false;
    private boolean mAnimationStarted = false;
    private final ArrayList<Animator> mAnimators = new ArrayList<>();
    private float mCurrentAnimationProgress = 0.0f;
    private AnimationListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AnimationListener {
        void onAnimationCancel();

        void onAnimationEnd();

        void onAnimationStart();
    }

    private void setLayoutAnimationStartPoint(boolean z) {
        if (!this.mAnimators.isEmpty()) {
            Iterator<Animator> it = this.mAnimators.iterator();
            while (it.hasNext()) {
                Animator next = it.next();
                if (next instanceof PropertyAnimator) {
                    PropertyAnimator propertyAnimator = (PropertyAnimator) next;
                    if (z) {
                        propertyAnimator.reversePoint();
                    } else {
                        propertyAnimator.setStartPoint();
                    }
                } else if (next instanceof ValueAnimator) {
                    ValueAnimator valueAnimator = (ValueAnimator) next;
                    if (z) {
                        valueAnimator.reverse();
                    }
                }
            }
        }
    }

    public void addAnimation(Animator animator) {
        this.mAnimators.add(animator);
    }

    public void addAnimations(ArrayList<Animator> arrayList) {
        this.mAnimators.addAll(arrayList);
    }

    public void cancel() {
        if (this.mAnimationStarted) {
            this.mAnimationCancelled = true;
            this.mAnimators.forEach(new B(3));
            AnimatorSet animatorSet = this.mAnimSet;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
        }
    }

    public void clear() {
        this.mAnimators.clear();
    }

    public float getAnimationProgress() {
        return this.mCurrentAnimationProgress;
    }

    public ArrayList<Animator> getAnimators() {
        return this.mAnimators;
    }

    public boolean isEmpty() {
        return this.mAnimators.isEmpty();
    }

    public void onAnimationEnd(Animator animator) {
        Iterator<Animator> it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (next instanceof PropertyAnimator) {
                ((PropertyAnimator) next).notifyPropertyAnimationEnd();
            }
        }
        AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            if (this.mAnimationCancelled) {
                this.mAnimationCancelled = false;
                animationListener.onAnimationCancel();
            } else {
                animationListener.onAnimationEnd();
            }
        }
        this.mAnimSet = null;
        this.mAnimationStarted = false;
    }

    public void playLayoutAnimation(boolean z) {
        playLayoutAnimation(z, 350);
    }

    public void removeAnimation(Animator animator) {
        this.mAnimators.remove(animator);
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.mListener = animationListener;
    }

    public void setAnimationProgress(float f) {
        if (!this.mAnimators.isEmpty()) {
            if (!this.mAnimationStarted) {
                this.mAnimationStarted = true;
                AnimationListener animationListener = this.mListener;
                if (animationListener != null) {
                    animationListener.onAnimationStart();
                }
            }
            float abs = Math.abs(this.mCurrentAnimationProgress - f);
            Iterator<Animator> it = this.mAnimators.iterator();
            while (it.hasNext()) {
                Animator next = it.next();
                long j2 = (long) ((1.0f - abs) * 350.0f);
                if (next instanceof PropertyAnimator) {
                    PropertyAnimator propertyAnimator = (PropertyAnimator) next;
                    propertyAnimator.setFloatValues(new float[]{this.mCurrentAnimationProgress, f});
                    propertyAnimator.setDuration(j2);
                    propertyAnimator.setCurrentPlayTime(0);
                    propertyAnimator.start();
                } else if (next instanceof ValueAnimator) {
                    ValueAnimator valueAnimator = (ValueAnimator) next;
                    valueAnimator.setFloatValues(new float[]{this.mCurrentAnimationProgress, f});
                    valueAnimator.setDuration(j2);
                    valueAnimator.setCurrentPlayTime(0);
                    valueAnimator.start();
                }
            }
            this.mCurrentAnimationProgress = f;
        }
    }

    public void setAnimationProgressOnly(float f) {
        this.mCurrentAnimationProgress = f;
    }

    public int size() {
        return this.mAnimators.size();
    }

    public void playLayoutAnimation(boolean z, int i2) {
        playLayoutAnimation(z, i2, PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_80));
    }

    public void playLayoutAnimation(boolean z, int i2, TimeInterpolator timeInterpolator) {
        setLayoutAnimationStartPoint(z);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimSet = animatorSet;
        animatorSet.playTogether(this.mAnimators);
        this.mAnimSet.setInterpolator(timeInterpolator);
        this.mAnimSet.setDuration((long) i2);
        this.mAnimSet.addListener(this);
        this.mAnimSet.start();
        this.mCurrentAnimationProgress = 0.0f;
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }
}
