package com.samsung.android.gallery.app.ui.viewer2.container.pagetransformer;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.motionphoto.utils.ex.b;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MaskingTransformer implements ViewPager2.PageTransformer {
    private final int MASKING;
    private final HashMap<View, View> mMap = new HashMap<>();

    public MaskingTransformer(int i2) {
        this.MASKING = i2;
    }

    private void doMasking(View view, float f) {
        float f5 = ((float) this.MASKING) * f;
        if (!Features.isEnabled(Features.IS_RTL)) {
            f5 = -f5;
        }
        view.setTranslationX(f5);
    }

    private void resetMasking(View view) {
        view.setTranslationX(0.0f);
    }

    public void transformPage(View view, float f) {
        View computeIfAbsent = this.mMap.computeIfAbsent(view, new b(29, view));
        if (computeIfAbsent != null) {
            if (f == 0.0f || Math.abs(f) >= 0.99f) {
                resetMasking(computeIfAbsent);
            } else {
                doMasking(computeIfAbsent, f);
            }
        }
    }
}
