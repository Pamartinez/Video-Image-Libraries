package com.samsung.android.gallery.app.ui.viewer2.slideshow.transformer;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SShowMarginPageTransformer implements ViewPager2.PageTransformer {
    private final int mMarginPx;

    public SShowMarginPageTransformer(int i2) {
        this.mMarginPx = i2;
    }

    private void resetProperty(View view) {
        ViewUtils.setAlpha(view, 1.0f);
        ViewUtils.setScale(view, 1.0f, 1.0f);
    }

    public void transformPage(View view, float f) {
        float f5 = ((float) this.mMarginPx) * f;
        if (Features.isEnabled(Features.IS_RTL)) {
            f5 = -f5;
        }
        view.setTranslationX(f5);
        resetProperty(view);
    }
}
