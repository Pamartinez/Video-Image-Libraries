package com.samsung.android.gallery.widget.listview.scroller;

import Kb.d;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.animation.Interpolator;
import androidx.core.view.animation.PathInterpolatorCompat;
import androidx.recyclerview.R$dimen;
import com.samsung.android.gallery.widget.FastScrollerBgDrawable;
import com.samsung.android.gallery.widget.animations.scroll.FastScrollAnimation;
import com.samsung.android.gallery.widget.animations.scroll.FastScrollColorAnimation;
import com.samsung.android.gallery.widget.animations.scroll.FastScrollWidthAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastScrollerV2ThumbAnimator {
    private static final Interpolator COLOR_INTERPOLATOR = PathInterpolatorCompat.create(0.0f, 0.0f, 1.0f, 1.0f);
    private static final Interpolator WIDTH_INTERPOLATOR = PathInterpolatorCompat.create(0.22f, 0.25f, 0.0f, 1.0f);
    private final int mActivatedColor;
    private final FastScrollerBgDrawable mBgDrawable;
    private final FastScrollColorAnimation mColorAnimator;
    private final int mDefaultColor;
    private final float mMaxWidthPx;
    private final float mMinWidthPx;
    private final FastScrollWidthAnimation mWidthAnimator;

    public FastScrollerV2ThumbAnimator(Context context, FastScrollerBgDrawable fastScrollerBgDrawable, int i2, int i7, Runnable runnable) {
        this.mBgDrawable = fastScrollerBgDrawable;
        this.mMinWidthPx = context.getResources().getDimension(R$dimen.sesl_fast_scroller_thumb_min_width);
        this.mMaxWidthPx = context.getResources().getDimension(R$dimen.sesl_fast_scroller_thumb_max_width);
        this.mDefaultColor = i2;
        this.mActivatedColor = i7;
        FastScrollWidthAnimation fastScrollWidthAnimation = new FastScrollWidthAnimation(new FastScrollAnimation.SimpleAnimationSpec(350, WIDTH_INTERPOLATOR), new d(this, runnable, 0));
        this.mWidthAnimator = fastScrollWidthAnimation;
        FastScrollColorAnimation fastScrollColorAnimation = new FastScrollColorAnimation(new FastScrollAnimation.SimpleAnimationSpec(150, COLOR_INTERPOLATOR), new d(this, runnable, 1));
        this.mColorAnimator = fastScrollColorAnimation;
        fastScrollWidthAnimation.animateTo(0.0f);
        fastScrollColorAnimation.animateTo(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Runnable runnable, ValueAnimator valueAnimator) {
        float f = this.mMinWidthPx;
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.mBgDrawable.setWidth((floatValue * (this.mMaxWidthPx - f)) + f);
        runnable.run();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(Runnable runnable, ValueAnimator valueAnimator) {
        this.mBgDrawable.setArgb(((Integer) valueAnimator.getAnimatedValue()).intValue());
        runnable.run();
    }

    public void dispose() {
        this.mWidthAnimator.dispose();
        this.mColorAnimator.dispose();
    }

    public void setDragging(boolean z) {
        float f;
        int i2;
        FastScrollWidthAnimation fastScrollWidthAnimation = this.mWidthAnimator;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        fastScrollWidthAnimation.animateTo(f);
        FastScrollColorAnimation fastScrollColorAnimation = this.mColorAnimator;
        if (z) {
            i2 = this.mActivatedColor;
        } else {
            i2 = this.mDefaultColor;
        }
        fastScrollColorAnimation.animateTo(i2);
    }

    public void setThumbHeight(float f) {
        this.mBgDrawable.setHeight(f);
    }
}
