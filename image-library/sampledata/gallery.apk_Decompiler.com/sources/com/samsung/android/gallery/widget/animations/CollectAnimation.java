package com.samsung.android.gallery.widget.animations;

import android.animation.Animator;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sum.core.descriptor.b;
import e6.C0453a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import o6.B;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollectAnimation {
    /* access modifiers changed from: private */
    public final List<Animator> mAnimators = Collections.synchronizedList(new ArrayList());
    /* access modifiers changed from: private */
    public Animator.AnimatorListener mListener;
    private boolean mPlaySequentially;
    /* access modifiers changed from: private */
    public final List<Animator> mPlayingSet = Collections.synchronizedList(new ArrayList());
    /* access modifiers changed from: private */
    public final AtomicBoolean mStarted = new AtomicBoolean();

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlaySequentiallyListener$1(Animator animator) {
        animator.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
                Optional.ofNullable(CollectAnimation.this.mListener).ifPresent(new a(animator, 4));
            }

            public void onAnimationEnd(Animator animator) {
                if (CollectAnimation.this.mAnimators.isEmpty()) {
                    Optional.ofNullable(CollectAnimation.this.mListener).ifPresent(new a(animator, 6));
                    return;
                }
                CollectAnimation.this.mPlayingSet.clear();
                CollectAnimation.this.mPlayingSet.add((Animator) CollectAnimation.this.mAnimators.remove(0));
                CollectAnimation.this.mPlayingSet.forEach(new C0453a(2));
            }

            public void onAnimationRepeat(Animator animator) {
                Optional.ofNullable(CollectAnimation.this.mListener).ifPresent(new a(animator, 7));
            }

            public void onAnimationStart(Animator animator) {
                if (!CollectAnimation.this.mStarted.getAndSet(true)) {
                    Optional.ofNullable(CollectAnimation.this.mListener).ifPresent(new a(animator, 5));
                }
            }
        });
    }

    private void setPlaySequentiallyListener() {
        this.mAnimators.forEach(new b(19, this));
    }

    private void setPlayTogetherListener() {
        Animator animator = null;
        for (Animator next : this.mAnimators) {
            if (animator == null || next.getDuration() > animator.getDuration()) {
                animator = next;
            }
        }
        if (animator != null) {
            animator.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    Optional.ofNullable(CollectAnimation.this.mListener).ifPresent(new a(animator, 3));
                }

                public void onAnimationEnd(Animator animator) {
                    Optional.ofNullable(CollectAnimation.this.mListener).ifPresent(new a(animator, 2));
                }

                public void onAnimationRepeat(Animator animator) {
                    Optional.ofNullable(CollectAnimation.this.mListener).ifPresent(new a(animator, 1));
                }

                public void onAnimationStart(Animator animator) {
                    Optional.ofNullable(CollectAnimation.this.mListener).ifPresent(new a(animator, 0));
                }
            });
            return;
        }
        throw new IllegalStateException("no Animator");
    }

    public CollectAnimation add(Animator animator) {
        this.mAnimators.add(animator);
        return this;
    }

    public void cancel() {
        ArrayList arrayList = new ArrayList(this.mPlayingSet);
        this.mAnimators.clear();
        this.mPlayingSet.clear();
        arrayList.forEach(new B(3));
    }

    public boolean isPaused() {
        if (this.mPlayingSet.size() <= 0 || !this.mPlayingSet.get(0).isPaused()) {
            return false;
        }
        return true;
    }

    public boolean isRunning() {
        if (this.mPlayingSet.size() <= 0 || this.mPlayingSet.get(0).isPaused()) {
            return false;
        }
        return true;
    }

    public void pause() {
        this.mPlayingSet.forEach(new C0453a(3));
    }

    public CollectAnimation playSequentially() {
        this.mPlaySequentially = true;
        return this;
    }

    public void resume() {
        this.mPlayingSet.forEach(new C0453a(4));
    }

    public CollectAnimation setListener(Animator.AnimatorListener animatorListener) {
        this.mListener = animatorListener;
        return this;
    }

    public void start() {
        if (this.mAnimators.isEmpty()) {
            Optional.ofNullable(this.mListener).ifPresent(new C0453a(1));
            Log.w("CollectAnimation", "no animators, notify done");
            return;
        }
        if (this.mPlaySequentially) {
            setPlaySequentiallyListener();
            this.mPlayingSet.add(this.mAnimators.remove(0));
        } else {
            setPlayTogetherListener();
            this.mPlayingSet.addAll(this.mAnimators);
        }
        this.mPlayingSet.forEach(new C0453a(2));
    }
}
