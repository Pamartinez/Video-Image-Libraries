package com.samsung.android.gallery.widget.animations.textexpand;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.PathInterpolator;
import com.samsung.android.gallery.widget.R$anim;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.effects.LightingEffectView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import hb.C0693a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextExpandAnimation {
    protected AnimatorSet mAnimatorSet;
    private ValueAnimator mBgExpandAnimation;
    protected int mBgExpandWidth = 0;
    protected View mBgView;
    protected final int mCircleWidth;
    private int mCircleXPosition;
    protected LightingEffectView mEffectView;
    private int mExpandXPosition = 0;
    protected ObjectAnimator mItemFadeInAnimation;
    private ValueAnimator mItemPositionAnimation;
    /* access modifiers changed from: protected */
    public View mLayout;
    /* access modifiers changed from: protected */
    public View mTextLayout;
    protected AnimatorSet mTextLayoutAnimation;

    public TextExpandAnimation(View view) {
        bindView(view);
        this.mCircleWidth = this.mLayout.getResources().getDimensionPixelOffset(getCircleSizeResId());
        createAnimation();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createBgAnimation$1(ValueAnimator valueAnimator) {
        this.mBgView.getLayoutParams().width = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mBgView.requestLayout();
        LightingEffectView lightingEffectView = this.mEffectView;
        if (lightingEffectView != null) {
            lightingEffectView.getLayoutParams().width = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            this.mEffectView.requestLayout();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createItemAnimation$0(ValueAnimator valueAnimator) {
        ViewMarginUtils.setStartMargin(this.mLayout, ((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public void bindView(View view) {
        this.mLayout = (View) view.getParent();
        this.mBgView = view.findViewById(getBackgroundViewId());
        this.mTextLayout = view.findViewById(getTitleLayoutViewId());
    }

    public void cancel() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mAnimatorSet.cancel();
        }
        LightingEffectView lightingEffectView = this.mEffectView;
        if (lightingEffectView != null) {
            lightingEffectView.releaseEffect();
        }
    }

    public void createAnimation() {
        createItemAnimation();
        createTextAnimation();
        createBgAnimation();
    }

    public void createBgAnimation() {
        if (this.mBgExpandAnimation == null) {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mCircleWidth, this.mBgExpandWidth});
            this.mBgExpandAnimation = ofInt;
            ofInt.setInterpolator(new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f));
            this.mBgExpandAnimation.setDuration(350);
            this.mBgExpandAnimation.addUpdateListener(new C0693a(this, 1));
        }
    }

    public void createItemAnimation() {
        if (this.mItemFadeInAnimation == null && this.mItemPositionAnimation == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mLayout, "alpha", new float[]{0.0f, 1.0f});
            this.mItemFadeInAnimation = ofFloat;
            ofFloat.setDuration(100);
            this.mItemFadeInAnimation.setInterpolator(new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f));
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mCircleXPosition, this.mExpandXPosition});
            this.mItemPositionAnimation = ofInt;
            ofInt.setInterpolator(new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f));
            this.mItemPositionAnimation.setDuration(350);
            this.mItemPositionAnimation.addUpdateListener(new C0693a(this, 0));
        }
    }

    public void createTextAnimation() {
        if (this.mTextLayoutAnimation == null) {
            AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this.mLayout.getContext(), R$anim.viewer_ai_edit_text_anim);
            this.mTextLayoutAnimation = animatorSet;
            animatorSet.setTarget(this.mTextLayout);
        }
    }

    public int getBackgroundViewId() {
        return R$id.ai_edit_item_bg;
    }

    public int getCircleSizeResId() {
        return R$dimen.ai_edit_circle_size;
    }

    public int getExpandWidth() {
        return this.mLayout.getMeasuredWidth();
    }

    public int getTitleLayoutViewId() {
        return R$id.ai_edit_text_layout;
    }

    public boolean isRunning() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet == null || !animatorSet.isRunning()) {
            return false;
        }
        return true;
    }

    public void play() {
        if (this.mAnimatorSet == null) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.mAnimatorSet = animatorSet;
            animatorSet.play(this.mItemPositionAnimation).with(this.mBgExpandAnimation).with(this.mTextLayoutAnimation).after(this.mItemFadeInAnimation);
        }
        this.mAnimatorSet.start();
    }

    public void prepare(int i2, int i7, int i8) {
        int i10 = (this.mCircleWidth + i7) * i2;
        this.mCircleXPosition = i10;
        ViewMarginUtils.setStartMargin(this.mLayout, i10);
        this.mBgView.getLayoutParams().width = this.mCircleWidth;
        this.mBgView.requestLayout();
        LightingEffectView lightingEffectView = this.mEffectView;
        if (lightingEffectView != null) {
            lightingEffectView.getLayoutParams().width = this.mCircleWidth;
            this.mEffectView.requestLayout();
        }
        this.mExpandXPosition = i8;
        ValueAnimator valueAnimator = this.mItemPositionAnimation;
        if (valueAnimator != null) {
            valueAnimator.setIntValues(new int[]{this.mCircleXPosition, i8});
            this.mItemPositionAnimation.setStartDelay(((long) i2) * 40);
        }
        if (this.mBgExpandWidth == 0) {
            this.mLayout.measure(0, 0);
            int expandWidth = getExpandWidth();
            this.mBgExpandWidth = expandWidth;
            ValueAnimator valueAnimator2 = this.mBgExpandAnimation;
            if (valueAnimator2 != null) {
                valueAnimator2.setIntValues(new int[]{this.mCircleWidth, expandWidth});
            }
        }
        this.mTextLayout.setAlpha(0.0f);
        this.mLayout.setAlpha(0.0f);
        this.mLayout.setVisibility(0);
        LightingEffectView lightingEffectView2 = this.mEffectView;
        if (lightingEffectView2 != null) {
            lightingEffectView2.applyEffect();
        }
    }

    public void resetBgExpandWidth() {
        this.mBgExpandWidth = 0;
    }
}
