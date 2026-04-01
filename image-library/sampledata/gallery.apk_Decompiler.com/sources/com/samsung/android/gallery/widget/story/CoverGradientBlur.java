package com.samsung.android.gallery.widget.story;

import D5.a;
import android.graphics.RenderEffect;
import android.os.Build;
import android.view.View;
import com.samsung.android.gallery.support.blur.BlurEffectHolder;
import com.samsung.android.gallery.support.blur.BlurParams;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CoverGradientBlur {
    public static boolean apply(View view) {
        if (view == null || Build.VERSION.SDK_INT < 31) {
            return false;
        }
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            view.post(new a(view, 3));
            return true;
        }
        applyInternal(view);
        return true;
    }

    /* access modifiers changed from: private */
    public static void applyInternal(View view) {
        if (Build.VERSION.SDK_INT >= 31) {
            view.setRenderEffect(createEffect(view.getWidth(), view.getHeight()));
        }
    }

    public static boolean applyRecap(View view) {
        if (view == null || Build.VERSION.SDK_INT < 31) {
            return false;
        }
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            view.post(new a(view, 4));
            return true;
        }
        applyRecapInternal(view);
        return true;
    }

    /* access modifiers changed from: private */
    public static void applyRecapInternal(View view) {
        if (Build.VERSION.SDK_INT >= 31) {
            view.setRenderEffect(applyRecapInternal(view.getWidth(), view.getHeight()));
        }
    }

    public static RenderEffect createEffect(int i2, int i7) {
        return BlurEffectHolder.getRenderEffectForStories(new BlurParams.Builder(i2, i7).setPercent(0.5f).setPivotPercent(0.25f).build());
    }

    public static void release(View view) {
        if (Build.VERSION.SDK_INT >= 31 && view != null) {
            view.setRenderEffect((RenderEffect) null);
        }
    }

    public static RenderEffect applyRecapInternal(int i2, int i7) {
        return BlurEffectHolder.getRenderEffectForStories(new BlurParams.Builder(i2, i7).setPercent(0.3f).setPivotPercent(0.15f).build());
    }
}
