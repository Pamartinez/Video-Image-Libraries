package com.samsung.android.gallery.widget.filmstrip3;

import Bb.n;
import Bb.o;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.PathInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animator.ValueAnimatorIgnoreSetting;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewHolderAnimation {
    private final boolean IS_RTL = Features.isEnabled(Features.IS_RTL);
    /* access modifiers changed from: private */
    public ValueAnimator mValueAnimator;
    private final View mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnFinish {
        void onFinish();
    }

    public ViewHolderAnimation(View view) {
        this.mView = view;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$toSeeker$0(FilmStripView filmStripView, float f, OnFinish onFinish) {
        if (filmStripView != null) {
            SeekerUnfoldAnimation.scrollToOffset(this.mView, f, filmStripView, this.IS_RTL);
        }
        if (onFinish != null) {
            onFinish.onFinish();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$toSeeker$1(FilmStripView filmStripView, float f, OnFinish onFinish) {
        ThreadUtil.postOnUiThreadDelayed(new o(this, filmStripView, f, onFinish), 10);
    }

    public void restorePhoto(RecyclerView recyclerView, boolean z, OnFinish onFinish) {
        runFoldAnimation(recyclerView, z, onFinish, 0.75f);
    }

    public void restoreVideo(RecyclerView recyclerView, boolean z, OnFinish onFinish) {
        runFoldAnimation(recyclerView, z, onFinish, 1.3333f);
    }

    public void runFoldAnimation(RecyclerView recyclerView, boolean z, OnFinish onFinish, float f) {
        long j2;
        FoldAnimation foldAnimation = new FoldAnimation(this.mView, recyclerView);
        int paddingEnd = this.mView.getPaddingEnd() + this.mView.getPaddingStart() + Math.round(((float) this.mView.getHeight()) * f);
        if (z) {
            j2 = 200;
        } else {
            j2 = 0;
        }
        runWidth(foldAnimation, paddingEnd, j2, onFinish);
    }

    public void runHeight(ValueAnimator.AnimatorUpdateListener animatorUpdateListener, int i2, long j2, final OnFinish onFinish) {
        if (this.mValueAnimator == null) {
            ValueAnimator ofInt = ValueAnimatorIgnoreSetting.ofInt(this.mView.getHeight(), i2);
            this.mValueAnimator = ofInt;
            ofInt.setDuration(j2);
            this.mValueAnimator.addUpdateListener(animatorUpdateListener);
            this.mValueAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ViewHolderAnimation.this.mValueAnimator = null;
                    OnFinish onFinish = onFinish;
                    if (onFinish != null) {
                        onFinish.onFinish();
                    }
                }
            });
            this.mValueAnimator.start();
        }
    }

    public void runWidth(ValueAnimator.AnimatorUpdateListener animatorUpdateListener, int i2, long j2, final OnFinish onFinish) {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mValueAnimator.cancel();
            this.mValueAnimator = null;
        }
        ValueAnimator ofInt = ValueAnimatorIgnoreSetting.ofInt(this.mView.getWidth(), i2);
        this.mValueAnimator = ofInt;
        ofInt.setDuration(j2);
        this.mValueAnimator.addUpdateListener(animatorUpdateListener);
        this.mValueAnimator.setInterpolator(new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f));
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ViewHolderAnimation.this.mValueAnimator = null;
                OnFinish onFinish = onFinish;
                if (onFinish != null) {
                    onFinish.onFinish();
                }
            }
        });
        this.mValueAnimator.start();
    }

    public void setHeight(RecyclerView recyclerView, int i2) {
        if (this.mValueAnimator == null) {
            runHeight(new ItemSetHeightAnimation(this.mView, recyclerView), i2, 100, (OnFinish) null);
        }
    }

    public void toSeeker(FilmStripView filmStripView, int i2, float f, OnFinish onFinish) {
        runWidth(new SeekerUnfoldAnimation(this.mView, filmStripView, f), i2, 300, new n(this, filmStripView, f, onFinish));
    }
}
