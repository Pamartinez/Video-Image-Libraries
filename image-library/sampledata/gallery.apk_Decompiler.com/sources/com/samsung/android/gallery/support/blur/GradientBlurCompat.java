package com.samsung.android.gallery.support.blur;

import android.graphics.RenderEffect;
import android.os.Build;
import com.samsung.android.gallery.support.utils.PocFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GradientBlurCompat {
    private static GradientBlurCompatible compat;

    public static RenderEffect getRenderEffect(BlurParams blurParams) {
        if (compat == null) {
            if (!PocFeatures.isEnabled(PocFeatures.GalleryGradientBlur) || Build.VERSION.SDK_INT < 33) {
                compat = new GraphicsGradientBlur();
            } else {
                compat = new GalleryGradientBlur();
            }
        }
        return compat.getRenderEffect(blurParams);
    }
}
