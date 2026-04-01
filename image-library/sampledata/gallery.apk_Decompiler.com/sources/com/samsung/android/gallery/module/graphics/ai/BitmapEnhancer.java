package com.samsung.android.gallery.module.graphics.ai;

import A.a;
import android.graphics.Bitmap;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.graphics.ai.IUpscaler;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BitmapEnhancer {
    private static IUpscaler createUpscaler() {
        if (Features.isEnabled(Features.SUPPORT_PHOTO_REMASTER_UPSCALE_ULTRA)) {
            return new RemasterUpscaler();
        }
        return null;
    }

    public static Bitmap upscale(Bitmap bitmap, int i2, FileItemInterface fileItemInterface) {
        IUpscaler.Quality quality;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            IUpscaler createUpscaler = createUpscaler();
            if (createUpscaler == null) {
                Log.i("BitmapEnhancer", "unsupported");
                return null;
            }
            if (PreferenceFeatures.OneUi7x.isMaxAutoUpscale()) {
                quality = IUpscaler.Quality.ULTRA;
            } else {
                quality = IUpscaler.Quality.NORMAL;
            }
            Bitmap upscale = createUpscaler.upscale(bitmap, i2, quality);
            if (upscale != null) {
                Log.i("BitmapEnhancer", "upscale : " + Logger.vt(createUpscaler.getClass().getSimpleName(), bitmap, upscale, Long.valueOf(currentTimeMillis)));
                return upscale;
            }
            Log.w("BitmapEnhancer", "upscale fail");
            return null;
        } catch (Throwable th) {
            a.z(th, new StringBuilder("UpScale failed e="), "BitmapEnhancer");
        }
    }
}
