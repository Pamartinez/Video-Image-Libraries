package com.samsung.android.gallery.module.graphics.ai;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.graphics.ai.IUpscaler;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.media.photoremaster.SemPhotoRemasterManager;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RemasterUpscaler implements IUpscaler {
    private final List<Integer> ENHANCE_MODE_UPSCALE = Collections.singletonList(1);

    public Bitmap upscale(Bitmap bitmap, int i2, IUpscaler.Quality quality) {
        if (i2 == 2 || i2 == 4) {
            SemPhotoRemasterManager semPhotoRemasterManager = new SemPhotoRemasterManager();
            if (!semPhotoRemasterManager.tryInit(AppResources.getAppContext())) {
                Log.w("RemasterUpscaler", "fail init remaster manager");
                return null;
            }
            semPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_VOLLEY_CANCELED, bitmap);
            semPhotoRemasterManager.setParameter(1004, 0);
            semPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER, 10);
            semPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, "CROP_AND_ENHANCE");
            semPhotoRemasterManager.processImage(2, this.ENHANCE_MODE_UPSCALE);
            Bitmap bitmapParameter = semPhotoRemasterManager.getBitmapParameter(2203);
            semPhotoRemasterManager.deinit();
            return bitmapParameter;
        }
        throw new IllegalArgumentException("use 2 or 4 scale");
    }
}
