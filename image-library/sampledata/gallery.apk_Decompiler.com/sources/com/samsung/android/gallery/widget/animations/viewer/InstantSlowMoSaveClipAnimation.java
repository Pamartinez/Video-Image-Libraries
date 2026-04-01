package com.samsung.android.gallery.widget.animations.viewer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.TextView;
import c2.k;
import com.samsung.android.gallery.widget.R$bool;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimation;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import e5.C0451a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InstantSlowMoSaveClipAnimation extends TextExpandAnimation {
    private AnimatorSet mHideAnimatorSet;
    /* access modifiers changed from: private */
    public boolean mIncludeTitle;
    private ObjectAnimator mItemFadeOutAnimation;
    private ObjectAnimator mSaveIconFadeInAnimation;
    private View mSaveIconLayout;
    private AnimatorSet mSimpleShowAnimatorSet;
    private ValueAnimator mTextLayoutShrinkAnimation;

    public InstantSlowMoSaveClipAnimation(View view) {
        super(view);
    }

    private void createSaveIconAnimation() {
        if (this.mSaveIconFadeInAnimation == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mSaveIconLayout, "alpha", new float[]{0.0f, 1.0f});
            this.mSaveIconFadeInAnimation = ofFloat;
            ofFloat.setDuration(200);
            this.mSaveIconFadeInAnimation.setInterpolator(new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createTextAnimation$0(int i2, int i7, ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        int i8 = this.mCircleWidth;
        float f = ((float) (intValue - i8)) / ((float) (this.mBgExpandWidth - i8));
        View view = this.mTextLayout;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMarginStart((int) (((float) i2) * f));
            marginLayoutParams.setMarginEnd((int) (((float) i7) * f));
            marginLayoutParams.width = intValue;
            this.mTextLayout.setLayoutParams(marginLayoutParams);
            this.mTextLayout.setAlpha(f);
            View view2 = this.mLayout;
            if (view2 != null && f == 0.0f) {
                String string = view2.getContext().getResources().getString(R$string.save_instant_slow_mo_clip);
                this.mLayout.setContentDescription(string);
                this.mLayout.setTooltipText(string);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hide$1() {
        ViewUtils.setVisibility(this.mLayout, 8);
    }

    private void playSimple() {
        if (this.mSimpleShowAnimatorSet == null) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.mSimpleShowAnimatorSet = animatorSet;
            animatorSet.setDuration(450);
            this.mSimpleShowAnimatorSet.play(this.mItemFadeInAnimation);
        }
        this.mSimpleShowAnimatorSet.start();
    }

    private void prepareHide() {
        if (this.mIncludeTitle) {
            ViewUtils.setWidth(this.mBgView, this.mCircleWidth);
            ValueAnimator valueAnimator = this.mTextLayoutShrinkAnimation;
            if (valueAnimator != null) {
                valueAnimator.setIntValues(new int[]{this.mBgExpandWidth, this.mCircleWidth});
            }
            ObjectAnimator objectAnimator = this.mSaveIconFadeInAnimation;
            if (objectAnimator != null) {
                objectAnimator.setStartDelay(400);
            }
            ObjectAnimator objectAnimator2 = this.mItemFadeOutAnimation;
            if (objectAnimator2 != null) {
                objectAnimator2.setStartDelay(5400);
                return;
            }
            return;
        }
        ObjectAnimator objectAnimator3 = this.mItemFadeOutAnimation;
        if (objectAnimator3 != null) {
            objectAnimator3.setStartDelay(0);
        }
    }

    private void prepareShow() {
        float f;
        prepare(0, 0, 0);
        ViewUtils.setVisibleOrGone(this.mTextLayout, false);
        View view = this.mSaveIconLayout;
        if (this.mIncludeTitle) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        ViewUtils.setAlpha(view, f);
    }

    public void bindView(View view) {
        this.mLayout = view;
        this.mBgView = view.findViewById(getBackgroundViewId());
        this.mTextLayout = view.findViewById(getTitleLayoutViewId());
        this.mSaveIconLayout = view.findViewById(R$id.save_icon_layout);
    }

    public void cancel() {
        super.cancel();
        AnimatorSet animatorSet = this.mSimpleShowAnimatorSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mSimpleShowAnimatorSet.cancel();
        }
        AnimatorSet animatorSet2 = this.mHideAnimatorSet;
        if (animatorSet2 != null && animatorSet2.isRunning()) {
            this.mHideAnimatorSet.cancel();
        }
    }

    public void createAnimation() {
        super.createAnimation();
        createSaveIconAnimation();
    }

    public void createItemAnimation() {
        super.createItemAnimation();
        if (this.mItemFadeOutAnimation == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mLayout, "alpha", new float[]{1.0f, 0.0f});
            this.mItemFadeOutAnimation = ofFloat;
            ofFloat.setDuration(200);
            this.mItemFadeOutAnimation.setInterpolator(new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f));
        }
    }

    public void createTextAnimation() {
        float f;
        if (this.mTextLayoutAnimation == null) {
            this.mTextLayoutAnimation = new AnimatorSet();
            boolean z = this.mTextLayout.getContext().getResources().getBoolean(R$bool.is_right_to_left);
            View view = this.mTextLayout;
            Property property = View.TRANSLATION_X;
            if (z) {
                f = 37.0f;
            } else {
                f = -37.0f;
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, property, new float[]{f, 0.0f});
            ofFloat.setInterpolator(new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f));
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mTextLayout, View.ALPHA, new float[]{0.0f, 1.0f});
            ofFloat2.setInterpolator(new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f));
            this.mTextLayoutAnimation.playTogether(new Animator[]{ofFloat, ofFloat2});
            this.mTextLayoutAnimation.setDuration(350);
            this.mTextLayoutAnimation.addListener(new SimpleAnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    if (InstantSlowMoSaveClipAnimation.this.mIncludeTitle) {
                        ViewUtils.setVisibleOrGone(InstantSlowMoSaveClipAnimation.this.mTextLayout, true);
                    }
                }
            });
        }
        if (this.mTextLayoutShrinkAnimation == null) {
            this.mTextLayoutShrinkAnimation = ValueAnimator.ofInt(new int[]{this.mBgExpandWidth, this.mCircleWidth});
            this.mTextLayoutShrinkAnimation.addUpdateListener(new k(this, ViewMarginUtils.getStartMargin(this.mTextLayout), ViewMarginUtils.getEndMargin(this.mTextLayout), 1));
            this.mTextLayoutShrinkAnimation.setInterpolator(new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f));
            this.mTextLayoutShrinkAnimation.setDuration(400);
        }
    }

    public int getBackgroundViewId() {
        return R$id.save_clip_layout_bg;
    }

    public int getCircleSizeResId() {
        return R$dimen.video_instant_slow_mo_save_clip_circle_size;
    }

    public int getExpandWidth() {
        int measureTextViewWidth = ViewUtils.getMeasureTextViewWidth((TextView) this.mTextLayout);
        return this.mTextLayout.getPaddingEnd() + this.mTextLayout.getPaddingStart() + measureTextViewWidth;
    }

    public int getTitleLayoutViewId() {
        return R$id.save_title;
    }

    public void hide() {
        try {
            prepareHide();
            AnimatorSet animatorSet = new AnimatorSet();
            this.mHideAnimatorSet = animatorSet;
            if (this.mIncludeTitle) {
                animatorSet.playTogether(new Animator[]{this.mTextLayoutShrinkAnimation, this.mSaveIconFadeInAnimation, this.mItemFadeOutAnimation});
            } else {
                animatorSet.play(this.mItemFadeOutAnimation);
            }
            this.mHideAnimatorSet.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator, boolean z) {
                    ViewUtils.setVisibility(InstantSlowMoSaveClipAnimation.this.mLayout, 8);
                }
            });
            this.mHideAnimatorSet.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideDirectly() {
        cancel();
        ViewUtils.setVisibility(this.mLayout, 8);
    }

    public boolean includeTitle() {
        return this.mIncludeTitle;
    }

    public void play() {
        if (this.mIncludeTitle) {
            super.play();
        } else {
            playSimple();
        }
    }

    public void recycle() {
        this.mAnimatorSet = null;
        this.mItemFadeOutAnimation = null;
        this.mSaveIconFadeInAnimation = null;
        this.mTextLayoutShrinkAnimation = null;
        this.mHideAnimatorSet = null;
        this.mSimpleShowAnimatorSet = null;
    }

    public void setIncludeTitle(boolean z) {
        this.mIncludeTitle = z;
        ViewUtils.setVisibleOrGone(this.mTextLayout, z);
    }

    public void show() {
        prepareShow();
        play();
    }

    public void hide(boolean z) {
        if (!ViewUtils.isShown(this.mLayout) || !z) {
            ViewUtils.post(this.mLayout, new C0451a(25, this));
        } else {
            hide();
        }
    }
}
