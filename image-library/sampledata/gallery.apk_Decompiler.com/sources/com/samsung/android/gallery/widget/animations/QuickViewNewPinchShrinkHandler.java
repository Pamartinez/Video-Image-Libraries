package com.samsung.android.gallery.widget.animations;

import android.graphics.RectF;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.AnimationManager;
import com.samsung.android.gallery.widget.animator.CircleAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.RotateAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class QuickViewNewPinchShrinkHandler extends NewPinchShrinkHandler {
    private RectF mToRect;
    private TranslationAnimator mTranslationAnimator;

    public QuickViewNewPinchShrinkHandler(SimpleShrinkView simpleShrinkView, ListViewHolder listViewHolder) {
        super(simpleShrinkView, listViewHolder);
        this.mDuration = 270;
        this.mIsQuickView = true;
    }

    private AnimationManager createAnimator() {
        ImageView imageView;
        ListViewHolder listViewHolder = this.mViewHolder;
        if (listViewHolder != null) {
            imageView = listViewHolder.getImage();
        } else {
            imageView = null;
        }
        AnimationManager animationManager = new AnimationManager();
        animationManager.addAnimations(createAnimatorSet(this.mView.mShrinkView, imageView));
        animationManager.setAnimationListener(new AnimationManager.AnimationListener() {
            public void onAnimationCancel() {
                Log.stv(QuickViewNewPinchShrinkHandler.this.TAG, "onAnimationCancel");
                QuickViewNewPinchShrinkHandler.this.onShrinkFinish();
            }

            public void onAnimationEnd() {
                Log.stv(QuickViewNewPinchShrinkHandler.this.TAG, "onAnimationEnd");
                QuickViewNewPinchShrinkHandler.this.onShrinkFinish();
            }

            public void onAnimationStart() {
                Log.stv(QuickViewNewPinchShrinkHandler.this.TAG, "onAnimationStart");
            }
        });
        return animationManager;
    }

    private RectF getFromRect(View view) {
        return new RectF(view.getX(), view.getY(), view.getX(), view.getY());
    }

    private RectF getToRect(RectF rectF) {
        float width = rectF.width() / 2.0f;
        float height = rectF.height() / 2.0f;
        float f = this.mStartX;
        float f5 = this.mStartY;
        return new RectF(f - width, f5 - height, f + width, f5 + height);
    }

    public boolean canReturn() {
        return true;
    }

    public PropertyAnimator createTranslationAnimator(View view, RectF rectF, RectF rectF2) {
        TranslationAnimator translationAnimator = new TranslationAnimator(view, rectF, getToRect(rectF2));
        this.mTranslationAnimator = translationAnimator;
        return translationAnimator;
    }

    public RectF makeDummyRect(RectF rectF) {
        RectF makeDummyRectForQuickView = makeDummyRectForQuickView(rectF);
        this.mToRect = makeDummyRectForQuickView;
        return makeDummyRectForQuickView;
    }

    public void startShrinkAnimation() {
        AnimationManager createAnimator = createAnimator();
        TranslationAnimator translationAnimator = this.mTranslationAnimator;
        if (translationAnimator != null) {
            createAnimator.removeAnimation(translationAnimator);
        }
        createAnimator.addAnimation(new CircleAnimator(this.mView.mShrinkView).setDuration(this.mDuration));
        createAnimator.addAnimation(new RotateAnimator(this.mView.mShrinkView, 0.0f, 1.0f).setDuration(this.mDuration));
        ImageView imageView = this.mView.mShrinkView;
        createAnimator.addAnimation(new TranslationAnimator(imageView, getFromRect(imageView), this.mToRect));
        createAnimator.playLayoutAnimation(false, this.mDuration, new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f));
    }

    public void clearShrinkData(boolean z) {
    }
}
