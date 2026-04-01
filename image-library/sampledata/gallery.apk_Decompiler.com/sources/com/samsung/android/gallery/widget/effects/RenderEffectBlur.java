package com.samsung.android.gallery.widget.effects;

import B2.h;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import androidx.core.content.ContextCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import java.util.Optional;
import n4.C0491c;
import x7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenderEffectBlur {
    private View mBlurTargetView;
    /* access modifiers changed from: private */
    public boolean mDestroyed;
    /* access modifiers changed from: private */
    public Runnable mDimTouchListener;
    /* access modifiers changed from: private */
    public DimView mDimView;
    private final ValueAnimator mHideAnimator;
    private long mInvalidatorInterval;
    /* access modifiers changed from: private */
    public Handler mKeepInvalidator;
    private final ValueAnimator mShowAnimator;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        public int mBlurTargetResId;
        public long mInvalidateInterval;
        public boolean mKeepInvalidating;
        public Handler mKeepInvalidator;
        public View mRootView;

        public Builder(View view) {
            this.mRootView = view;
        }

        public RenderEffectBlur build() {
            return new RenderEffectBlur(this);
        }
    }

    public RenderEffectBlur(Builder builder) {
        Optional.ofNullable(builder.mRootView).ifPresent(new C0491c(24, this, builder));
        if (builder.mKeepInvalidating) {
            this.mKeepInvalidator = builder.mKeepInvalidator;
            this.mInvalidatorInterval = builder.mInvalidateInterval;
        }
        ValueAnimator duration = ValueAnimator.ofFloat(new float[]{0.0f, 150.0f}).setDuration(200);
        this.mShowAnimator = duration;
        addBlurAnimation(duration);
        addShowAnimationListener();
        ValueAnimator duration2 = ValueAnimator.ofFloat(new float[]{150.0f, 0.0f}).setDuration(120);
        this.mHideAnimator = duration2;
        addBlurAnimation(duration2);
        addHideAnimationListener();
    }

    private void addBlurAnimation(ValueAnimator valueAnimator) {
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new h(19, this));
    }

    private void addHideAnimationListener() {
        this.mHideAnimator.addListener(new SimpleAnimatorListener() {
            public void onAnimationCancel(Animator animator) {
                RenderEffectBlur.this.hide();
            }

            public void onAnimationEnd(Animator animator) {
                RenderEffectBlur.this.hide();
            }

            public void onAnimationStart(Animator animator) {
                if (RenderEffectBlur.this.mKeepInvalidator != null) {
                    RenderEffectBlur.this.mKeepInvalidator.removeCallbacksAndMessages((Object) null);
                }
            }
        });
    }

    private void addShowAnimationListener() {
        this.mShowAnimator.addListener(new SimpleAnimatorListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onAnimationEnd$0() {
                RenderEffectBlur.this.invalidateBlurTargetView();
            }

            public void onAnimationEnd(Animator animator) {
                if (RenderEffectBlur.this.mKeepInvalidator != null && !RenderEffectBlur.this.mDestroyed) {
                    RenderEffectBlur.this.mKeepInvalidator.post(new a(this));
                }
            }

            public void onAnimationStart(Animator animator) {
                if (RenderEffectBlur.this.mDimView != null) {
                    RenderEffectBlur.this.mDimView.setVisible(RenderEffectBlur.this.mDimTouchListener);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void invalidateBlurTargetView() {
        View view = this.mBlurTargetView;
        if (view != null && !this.mDestroyed) {
            view.invalidate();
            Handler handler = this.mKeepInvalidator;
            if (handler != null) {
                handler.postDelayed(new l(6, this), this.mInvalidatorInterval);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addBlurAnimation$1(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        DimView dimView = this.mDimView;
        if (dimView != null) {
            dimView.setAlpha(floatValue / 150.0f);
        }
        try {
            View view = this.mBlurTargetView;
            if (view != null) {
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                view.setRenderEffect(RenderEffect.createBlurEffect(floatValue, floatValue, Shader.TileMode.CLAMP));
            }
        } catch (IllegalArgumentException e) {
            Log.e("RenderEffectBlur", "setRenderEffect failed. e=" + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Builder builder, View view) {
        int i2 = builder.mBlurTargetResId;
        if (i2 <= 0) {
            i2 = R$id.dim_view;
        }
        this.mDimView = (DimView) view.findViewById(i2);
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.InAppAssistLook)) {
            this.mBlurTargetView = view.findViewWithTag("render_effect_blur_target_view");
            return;
        }
        DimView dimView = this.mDimView;
        if (dimView != null) {
            dimView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R$color.search_no_blur_dim_color));
        }
    }

    public void hide() {
        DimView dimView = this.mDimView;
        if (dimView != null) {
            dimView.setGone();
        }
        View view = this.mBlurTargetView;
        if (view != null) {
            view.setRenderEffect((RenderEffect) null);
        }
    }

    public void setDimTouchListener(Runnable runnable) {
        this.mDimTouchListener = runnable;
    }

    public void show() {
        DimView dimView = this.mDimView;
        if (dimView != null) {
            dimView.setVisible(this.mDimTouchListener);
        }
        View view = this.mBlurTargetView;
        if (view != null) {
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            view.setRenderEffect(RenderEffect.createBlurEffect(150.0f, 150.0f, Shader.TileMode.CLAMP));
        }
    }
}
