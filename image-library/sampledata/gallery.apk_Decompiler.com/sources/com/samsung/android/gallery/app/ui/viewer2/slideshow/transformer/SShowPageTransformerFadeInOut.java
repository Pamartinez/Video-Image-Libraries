package com.samsung.android.gallery.app.ui.viewer2.slideshow.transformer;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SShowPageTransformerFadeInOut implements ViewPager2.PageTransformer {
    private final boolean mIsScaleUp;
    private final int mMarginPx;
    float mScaleFactor;

    public SShowPageTransformerFadeInOut(boolean z, int i2) {
        float f;
        this.mMarginPx = i2;
        this.mIsScaleUp = z;
        if (z) {
            f = 1.0f;
        } else {
            f = 1.1f;
        }
        this.mScaleFactor = f;
    }

    private boolean checkKenBurn(float f) {
        if (-0.5f <= f) {
            return true;
        }
        return false;
    }

    private void doKenBurnAndFadeOut(View view, float f) {
        float f5;
        if (checkKenBurn(f)) {
            float abs = Math.abs(f) * 0.2f;
            if (this.mIsScaleUp) {
                f5 = abs + 1.0f;
            } else {
                f5 = 1.1f - abs;
            }
            this.mScaleFactor = f5;
            view.setScaleX(f5);
            view.setScaleY(this.mScaleFactor);
            return;
        }
        setForegroundAlpha(view, 1.0f - (1.0f - ((Math.abs(f) * 2.0f) - 0.5f)));
    }

    private void hideView(View view, float f) {
        float f5 = ((float) this.mMarginPx) * f;
        if (Features.isEnabled(Features.IS_RTL)) {
            f5 *= -1.0f;
        }
        view.setTranslationX(f5);
        view.setAlpha(0.0f);
        setForegroundAlpha(view, 0.0f);
    }

    private void setForegroundAlpha(View view, float f) {
        ViewUtils.setAlpha(view.findViewById(R.id.foreground_image_view), f);
    }

    public boolean checkRemain(float f) {
        if (0.5f < f) {
            return true;
        }
        return false;
    }

    public void doFadeIn(View view, float f) {
        if (checkRemain(f)) {
            view.setAlpha(0.0f);
            return;
        }
        view.setAlpha(1.0f - (Math.abs(f) * 2.0f));
        view.setScaleX(this.mScaleFactor);
        view.setScaleY(this.mScaleFactor);
    }

    public void transformPage(View view, float f) {
        if (((double) Math.abs(f)) >= 0.95d) {
            hideView(view, f);
            return;
        }
        if (f < 0.0f) {
            doKenBurnAndFadeOut(view, f);
        } else if (f == 0.0f) {
            view.setAlpha(1.0f);
            setForegroundAlpha(view, 0.0f);
            view.setScaleX(this.mScaleFactor);
            view.setScaleY(this.mScaleFactor);
        } else {
            doFadeIn(view, f);
        }
        float width = (float) view.getWidth();
        if (!Features.isEnabled(Features.IS_RTL)) {
            f = -f;
        }
        view.setTranslationX(width * f);
    }
}
