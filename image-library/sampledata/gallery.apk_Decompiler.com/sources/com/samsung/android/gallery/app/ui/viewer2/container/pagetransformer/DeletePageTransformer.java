package com.samsung.android.gallery.app.ui.viewer2.container.pagetransformer;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.animator.PathInterpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeletePageTransformer implements ViewPager2.PageTransformer {
    private final int RTL_FLAG;
    private final float mFadeCorrectionRatio;
    private final Interpolator mInterpolator;
    private Boolean mPositive = null;
    private final boolean mReverse;
    private final float mScrollCorrectionRatio;
    private View mView = null;
    private final float mWaitPos;

    public DeletePageTransformer(boolean z, int[] iArr) {
        int i2;
        if (Features.isEnabled(Features.IS_RTL)) {
            i2 = -1;
        } else {
            i2 = 1;
        }
        this.RTL_FLAG = i2;
        this.mInterpolator = PathInterpolator.create(0.22f, 0.25f, 0.0f, 1.0f);
        this.mReverse = z;
        int i7 = iArr[1];
        int i8 = iArr[2];
        float f = (float) (i7 + i8);
        this.mFadeCorrectionRatio = f / ((float) iArr[0]);
        this.mWaitPos = 1.0f - (((float) i7) / f);
        this.mScrollCorrectionRatio = f / ((float) i8);
    }

    public Boolean isPositive(float f) {
        Boolean bool;
        if (f > 0.0f) {
            bool = Boolean.TRUE;
        } else {
            bool = null;
        }
        if (f < 0.0f) {
            return Boolean.FALSE;
        }
        return bool;
    }

    public void restoreView(View view) {
        if (view != null) {
            view.bringToFront();
            view.setTranslationX(0.0f);
            view.setAlpha(1.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        }
    }

    public void scrollPage(View view, float f, int i2) {
        if (Math.abs(f) > this.mWaitPos) {
            view.setTranslationX((float) (view.getWidth() * this.RTL_FLAG * i2));
            return;
        }
        float abs = Math.abs(f);
        view.setTranslationX(((1.0f - this.mInterpolator.getInterpolation(1.0f - (this.mScrollCorrectionRatio * abs))) - abs) * ((float) this.RTL_FLAG) * ((float) view.getWidth()) * ((float) i2));
    }

    public void transformPage(View view, float f) {
        if (this.mView == null && f == 0.0f) {
            this.mView = view;
        }
        int i2 = 1;
        if (this.mPositive == null) {
            Boolean isPositive = isPositive(f);
            this.mPositive = isPositive;
            if (isPositive != null) {
                if (this.mReverse) {
                    this.mPositive = Boolean.valueOf(!isPositive.booleanValue());
                }
            } else {
                return;
            }
        }
        if ((!this.mPositive.booleanValue() || f >= 0.0f) && (this.mPositive.booleanValue() || f <= 0.0f)) {
            View view2 = this.mView;
            if (view2 != view) {
                restoreView(view2);
                this.mView = view;
            }
            if (Math.abs(f) < 1.0f) {
                view.setTranslationX(((float) this.RTL_FLAG) * (-f) * ((float) view.getWidth()));
                float min = Math.min(this.mInterpolator.getInterpolation(Math.abs(f * this.mFadeCorrectionRatio)), 1.0f);
                float abs = 1.0f - (Math.abs(min) * 0.7f);
                view.setAlpha(1.0f - Math.abs(min));
                view.setScaleX(abs);
                view.setScaleY(abs);
                return;
            }
            restoreView(view);
            return;
        }
        if (view.getAlpha() < 1.0f) {
            restoreView(view);
        }
        if (f <= 0.0f) {
            i2 = -1;
        }
        scrollPage(view, f, i2);
    }
}
