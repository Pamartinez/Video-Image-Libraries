package com.samsung.android.gallery.module.aizoom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.sdk.scs.ai.AiServices;
import com.samsung.android.sdk.scs.ai.image.ImageUpscaler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SCSImageScaler implements ImageScaleInterface {
    private final ImageUpscaler mImageUpscale;

    public SCSImageScaler(Context context) {
        this.mImageUpscale = AiServices.getImageUpscaler(context);
    }

    public boolean createSession(int i2, String str) {
        return this.mImageUpscale.createSession(i2, str);
    }

    public int detectScene(Bitmap bitmap) {
        return this.mImageUpscale.detectScene(bitmap);
    }

    public void endSession() {
        this.mImageUpscale.endSession();
    }

    public Bitmap upscaleImage(Bitmap bitmap, int i2, Rect rect, int i7) {
        return this.mImageUpscale.upscaleImage(bitmap, i2, rect, i7);
    }
}
