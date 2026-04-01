package com.samsung.android.gallery.widget.livemotion.transform;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageTransformSwipe implements ViewPager2.PageTransformer {
    private final int mMarginPixel;

    public PageTransformSwipe(int i2) {
        this.mMarginPixel = i2;
    }

    public void transformPage(View view, float f) {
        ViewUtils.setAlpha(view, 1.0f);
        float f5 = ((float) this.mMarginPixel) * f;
        if (Features.isEnabled(Features.IS_RTL)) {
            f5 = -f5;
        }
        view.setTranslationX(f5);
    }
}
