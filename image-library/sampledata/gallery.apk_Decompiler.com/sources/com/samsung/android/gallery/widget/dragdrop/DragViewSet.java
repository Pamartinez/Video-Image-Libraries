package com.samsung.android.gallery.widget.dragdrop;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DragViewSet {
    View mAnimView;
    int mIndex;
    ViewGroup mViewHolderItemView;
    View mViewHolderThumbnailView;

    public DragViewSet(ViewGroup viewGroup, View view, int i2) {
        this.mViewHolderItemView = viewGroup;
        this.mViewHolderThumbnailView = viewGroup.getChildAt(0);
        this.mAnimView = view;
        this.mIndex = i2;
    }

    private float calcGatherAnimAlpha(float f) {
        float f5 = (0.7f / f) + 0.2f;
        return C0212a.a(0.7f, f5, ((float) this.mIndex) / f, f5);
    }

    public long getMoveAnimDelay(int i2) {
        return ((long) (i2 - this.mIndex)) * ((long) Math.min(50, 400 / i2));
    }

    public void setAnimViewAlphaForGatherAnim(int i2) {
        this.mAnimView.setAlpha(calcGatherAnimAlpha((float) i2));
    }

    public void startMoveAnim(int i2, int i7, int i8) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mAnimView, "x", new float[]{(float) i2});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mAnimView, "y", new float[]{(float) i7});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(50);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setStartDelay(getMoveAnimDelay(i8));
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.start();
    }
}
