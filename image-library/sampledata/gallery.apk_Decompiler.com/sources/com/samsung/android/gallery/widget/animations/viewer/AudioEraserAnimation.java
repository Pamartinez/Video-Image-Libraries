package com.samsung.android.gallery.widget.animations.viewer;

import B2.h;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.effects.LightingEffectView;
import com.samsung.android.gallery.widget.utils.AnimatedVectorDrawableUtils;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.function.Predicate;
import x0.C0330h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioEraserAnimation {
    private String ANALYZE_LOTTIE = "intelligence_progress_color.json";
    private final float BACKGROUND_OFF_ALPHA = 0.8f;
    private final float BACKGROUND_ON_ALPHA = 0.5f;
    private final int CIRCLE_SIZE;
    private final int LOTTIE_SIZE;
    private final int TEXT_VIEW_X_TRANSLATE;
    private State mAnimState;
    private View mBgView;
    private View mContainer;
    /* access modifiers changed from: private */
    public View mEffectView;
    private int mExpandSize;
    private View mIconLayout;
    /* access modifiers changed from: private */
    public LottieAnimationView mIconView;
    /* access modifiers changed from: private */
    public LottieAnimationView mLottieAnim;
    private AnimatorSet mSet;
    /* access modifiers changed from: private */
    public TextView mTextView;
    /* access modifiers changed from: private */
    public final View mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum State {
        NONE,
        ANALYZING_ANIM,
        ERASED_ANIM
    }

    public AudioEraserAnimation(View view) {
        this.mView = view;
        this.mAnimState = State.NONE;
        this.CIRCLE_SIZE = view.getResources().getDimensionPixelOffset(R$dimen.audio_eraser_item_size);
        this.LOTTIE_SIZE = view.getResources().getDimensionPixelOffset(R$dimen.audio_eraser_lottie_width);
        int dimensionPixelOffset = view.getResources().getDimensionPixelOffset(R$dimen.audio_eraser_padding_for_effect);
        view.setTranslationX((float) (Features.isEnabled(Features.IS_RTL) ? -dimensionPixelOffset : dimensionPixelOffset));
        this.TEXT_VIEW_X_TRANSLATE = view.getResources().getDimensionPixelOffset(R$dimen.audio_eraser_text_x_translate);
        bindView();
    }

    private void bindView() {
        this.mContainer = this.mView.findViewById(R$id.container);
        this.mBgView = this.mView.findViewById(R$id.background_view);
        this.mTextView = (TextView) this.mView.findViewById(R$id.audio_eraser_item_text);
        this.mIconLayout = this.mView.findViewById(R$id.audio_eraser_image_layout);
        this.mIconView = (LottieAnimationView) this.mView.findViewById(R$id.audio_eraser_icon);
        this.mLottieAnim = (LottieAnimationView) this.mView.findViewById(R$id.audio_eraser_animation);
        this.mEffectView = this.mView.findViewById(R$id.effect_view);
    }

    private void clearAnimatorSet() {
        AnimatorSet animatorSet = this.mSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mSet.cancel();
            this.mSet = null;
        }
    }

    private ObjectAnimator getAlphaAnim(View view, float f, float f5, int i2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", new float[]{f, f5});
        ofFloat.setInterpolator(new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f));
        ofFloat.setDuration((long) i2);
        return ofFloat;
    }

    private ObjectAnimator getFadeInOutAnim(View view, boolean z, int i2) {
        float f;
        float f5 = 1.0f;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (!z) {
            f5 = 0.0f;
        }
        return getAlphaAnim(view, f, f5, i2);
    }

    private ObjectAnimator getTranslateXAnim(final View view, int i2, int i7) {
        if (Features.isEnabled(Features.IS_RTL)) {
            i2 = -i2;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", new float[]{0.0f, (float) i2});
        ofFloat.setDuration((long) i7);
        ofFloat.setInterpolator(new PathInterpolator(0.17f, 0.17f, 0.67f, 1.0f));
        ofFloat.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                view.setTranslationX(0.0f);
            }
        });
        return ofFloat;
    }

    private ValueAnimator getWidthUpdateAnim(int i2, int i7) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mContainer.getMeasuredWidth(), i2});
        ofInt.setInterpolator(new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f));
        ofInt.setDuration((long) i7);
        ofInt.addUpdateListener(new h(15, this));
        return ofInt;
    }

    private void initializeValue() {
        if (this.mExpandSize == 0) {
            Log.d("AudioEraserAnimation", "initialize");
            switchIconVisibility(State.ANALYZING_ANIM, false);
            ViewUtils.setAlpha(this.mView, 0.0f);
            ViewUtils.setVisibility(this.mView, 0);
            this.mContainer.measure(0, 0);
            this.mExpandSize = this.mContainer.getMeasuredWidth();
            ViewUtils.setHeight(this.mContainer, this.CIRCLE_SIZE);
            ViewUtils.setHeight(this.mEffectView, this.CIRCLE_SIZE);
            ViewUtils.setHeight(this.mBgView, this.CIRCLE_SIZE);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getWidthUpdateAnim$0(ValueAnimator valueAnimator) {
        updateLayoutWidth(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    /* access modifiers changed from: private */
    public void stopIcon() {
        if (ViewUtils.isVisible(this.mLottieAnim)) {
            this.mLottieAnim.a();
        }
        if (ViewUtils.isVisible(this.mIconView)) {
            AnimatedVectorDrawableUtils.stop(this.mIconView);
        }
    }

    private void switchIconVisibility(State state, boolean z) {
        Log.d("AudioEraserAnimation", "updateIcon(" + z + ") : " + state);
        stopIcon();
        State state2 = State.NONE;
        int i2 = 0;
        if (state == state2 || state == State.ANALYZING_ANIM) {
            updateIconView(state);
            ViewUtils.setVisibility(this.mLottieAnim, 8);
            ViewUtils.setVisibility(this.mIconView, 0);
            TextView textView = this.mTextView;
            if (state == state2) {
                i2 = 8;
            }
            ViewUtils.setVisibility(textView, i2);
            if (z) {
                this.mIconView.c();
                return;
            }
            return;
        }
        ViewUtils.setVisibility(this.mIconView, 8);
        ViewUtils.setVisibility(this.mTextView, 8);
        ViewUtils.setVisibility(this.mLottieAnim, 0);
        if (z) {
            this.mLottieAnim.c();
        }
    }

    private void updateIconView(State state) {
        int i2;
        State state2 = State.NONE;
        if (state == state2 || state == State.ANALYZING_ANIM) {
            if (state == state2) {
                this.mIconView.setImageResource(R$drawable.gallery_ic_detail_audio_eraser_icon);
            } else {
                this.mIconView.setAnimation(this.ANALYZE_LOTTIE);
                this.mIconView.setRepeatCount(-1);
            }
            if (state == state2) {
                i2 = 0;
            } else {
                i2 = this.mView.getResources().getDimensionPixelOffset(R$dimen.audio_eraser_avd_icon_vertical_margin);
            }
            ViewMarginUtils.setVerticalPadding(this.mIconView, i2, i2);
            ViewMarginUtils.setStartPadding(this.mIconView, i2);
        }
    }

    private void updateLayoutWidth(int i2) {
        ViewUtils.setWidth(this.mContainer, i2);
        ViewUtils.setWidth(this.mBgView, i2);
        ViewUtils.setWidth(this.mEffectView, i2);
    }

    public void hideButton(boolean z) {
        Log.d("AudioEraserAnimation", "hide", Boolean.valueOf(z));
        if (z) {
            ObjectAnimator fadeInOutAnim = getFadeInOutAnim(this.mView, false, StatusCodes.INPUT_MISSING);
            fadeInOutAnim.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    AudioEraserAnimation.this.stopIcon();
                    ((LightingEffectView) AudioEraserAnimation.this.mEffectView).releaseEffect();
                    ViewUtils.setVisibility(AudioEraserAnimation.this.mView, 8);
                }
            });
            fadeInOutAnim.start();
            return;
        }
        stopIcon();
        ((LightingEffectView) this.mEffectView).releaseEffect();
        ViewUtils.setVisibility(this.mView, 8);
    }

    public boolean isAnimating() {
        if (this.mAnimState == State.NONE || !ViewUtils.isVisible(this.mView)) {
            return false;
        }
        return true;
    }

    public boolean isErasingAnimating() {
        if (this.mAnimState != State.ERASED_ANIM || !ViewUtils.isVisible(this.mView)) {
            return false;
        }
        return true;
    }

    public void pauseErasingAnimation() {
        if (this.mAnimState == State.ERASED_ANIM && ViewUtils.isVisible(this.mView)) {
            LottieAnimationView lottieAnimationView = this.mLottieAnim;
            lottieAnimationView.l = false;
            lottieAnimationView.f1099h.j();
        }
    }

    public void resumeErasingAnimation() {
        if (this.mAnimState == State.ERASED_ANIM && ViewUtils.isVisible(this.mView) && !this.mLottieAnim.f1099h.i()) {
            LottieAnimationView lottieAnimationView = this.mLottieAnim;
            lottieAnimationView.n.add(C0330h.PLAY_OPTION);
            lottieAnimationView.f1099h.m();
        }
    }

    public void showButton(boolean z, final Predicate<?> predicate) {
        int i2;
        float f;
        State state;
        LottieAnimationView lottieAnimationView;
        Log.d("AudioEraserAnimation", "show", Boolean.valueOf(z));
        initializeValue();
        if (z) {
            i2 = this.LOTTIE_SIZE;
        } else {
            i2 = this.CIRCLE_SIZE;
        }
        updateLayoutWidth(i2);
        View view = this.mBgView;
        if (z) {
            f = 0.5f;
        } else {
            f = 0.8f;
        }
        ViewUtils.setAlpha(view, f);
        ViewUtils.setAlpha(this.mView, 0.0f);
        ViewUtils.setVisibility(this.mView, 0);
        if (z) {
            state = State.ERASED_ANIM;
        } else {
            state = State.NONE;
        }
        this.mAnimState = state;
        if (z) {
            lottieAnimationView = this.mLottieAnim;
        } else {
            lottieAnimationView = this.mIconView;
        }
        ViewUtils.setAlpha(lottieAnimationView, 1.0f);
        switchIconVisibility(this.mAnimState, true);
        ((LightingEffectView) this.mEffectView).applyEffect();
        ObjectAnimator fadeInOutAnim = getFadeInOutAnim(this.mView, true, StatusCodes.INPUT_MISSING);
        fadeInOutAnim.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                if (predicate.test((Object) null)) {
                    ViewUtils.setAlpha(AudioEraserAnimation.this.mView, 0.4f);
                }
            }
        });
        fadeInOutAnim.start();
    }

    public void startAnalyzingAnim() {
        Log.d("AudioEraserAnimation", "start anim(analyzing)", this.mAnimState);
        clearAnimatorSet();
        this.mAnimState = State.ANALYZING_ANIM;
        ViewUtils.setAlpha(this.mIconLayout, 0.0f);
        ViewUtils.setAlpha(this.mTextView, 0.0f);
        switchIconVisibility(this.mAnimState, true);
        ObjectAnimator fadeInOutAnim = getFadeInOutAnim(this.mTextView, true, StatusCodes.INPUT_MISSING);
        ObjectAnimator fadeInOutAnim2 = getFadeInOutAnim(this.mIconLayout, true, StatusCodes.INPUT_MISSING);
        ValueAnimator widthUpdateAnim = getWidthUpdateAnim(this.mExpandSize, 500);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mSet = animatorSet;
        animatorSet.play(widthUpdateAnim).with(fadeInOutAnim).with(fadeInOutAnim2);
        this.mSet.start();
    }

    public void startEraseOffAnim() {
        Log.d("AudioEraserAnimation", "start anim(off)", this.mAnimState);
        clearAnimatorSet();
        this.mAnimState = State.NONE;
        ViewUtils.setAlpha(this.mIconView, 0.0f);
        ViewUtils.setVisibility(this.mIconView, 0);
        updateIconView(this.mAnimState);
        View view = this.mBgView;
        ObjectAnimator alphaAnim = getAlphaAnim(view, view.getAlpha(), 0.8f, StatusCodes.INPUT_MISSING);
        ValueAnimator widthUpdateAnim = getWidthUpdateAnim(this.CIRCLE_SIZE, 500);
        ObjectAnimator fadeInOutAnim = getFadeInOutAnim(this.mIconView, true, StatusCodes.INPUT_MISSING);
        fadeInOutAnim.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                ViewUtils.setVisibility(AudioEraserAnimation.this.mTextView, 8);
            }

            public void onAnimationStart(Animator animator) {
                AudioEraserAnimation.this.mLottieAnim.a();
                ViewUtils.setVisibility(AudioEraserAnimation.this.mLottieAnim, 8);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        this.mSet = animatorSet;
        animatorSet.play(fadeInOutAnim).with(widthUpdateAnim).with(alphaAnim);
        this.mSet.start();
    }

    public void startErasedAnim(final Predicate<?> predicate) {
        Log.d("AudioEraserAnimation", "start anim(erased)", this.mAnimState);
        clearAnimatorSet();
        this.mAnimState = State.ERASED_ANIM;
        ViewUtils.setAlpha(this.mLottieAnim, 0.0f);
        ViewUtils.setVisibility(this.mLottieAnim, 0);
        View view = this.mBgView;
        ObjectAnimator alphaAnim = getAlphaAnim(view, view.getAlpha(), 0.5f, StatusCodes.INPUT_MISSING);
        alphaAnim.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                if (!predicate.test((Object) null)) {
                    AudioEraserAnimation.this.pauseErasingAnimation();
                }
            }

            public void onAnimationStart(Animator animator) {
                AudioEraserAnimation.this.mIconView.a();
                ViewUtils.setVisibility(AudioEraserAnimation.this.mIconView, 8);
            }
        });
        ObjectAnimator fadeInOutAnim = getFadeInOutAnim(this.mTextView, false, 150);
        fadeInOutAnim.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                ViewUtils.setVisibility(AudioEraserAnimation.this.mTextView, 8);
            }
        });
        ObjectAnimator translateXAnim = getTranslateXAnim(this.mTextView, this.TEXT_VIEW_X_TRANSLATE, 500);
        ObjectAnimator fadeInOutAnim2 = getFadeInOutAnim(this.mLottieAnim, true, StatusCodes.INPUT_MISSING);
        ValueAnimator widthUpdateAnim = getWidthUpdateAnim(this.LOTTIE_SIZE, 500);
        if (predicate.test((Object) null)) {
            this.mLottieAnim.c();
        }
        AnimatorSet animatorSet = new AnimatorSet();
        this.mSet = animatorSet;
        animatorSet.play(fadeInOutAnim).with(fadeInOutAnim2).with(widthUpdateAnim).with(alphaAnim).with(translateXAnim);
        this.mSet.start();
    }
}
