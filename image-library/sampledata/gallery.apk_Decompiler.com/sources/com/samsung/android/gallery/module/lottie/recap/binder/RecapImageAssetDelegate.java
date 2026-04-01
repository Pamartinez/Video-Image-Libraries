package com.samsung.android.gallery.module.lottie.recap.binder;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.HashMap;
import x0.C0325c;
import x0.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapImageAssetDelegate implements C0325c {
    HashMap<String, RecapImage> mImages;

    public RecapImageAssetDelegate(HashMap<String, RecapImage> hashMap) {
        this.mImages = hashMap;
    }

    public Bitmap fetchBitmap(y yVar) {
        HashMap<String, RecapImage> hashMap = this.mImages;
        String str = yVar.d;
        String str2 = yVar.d;
        RecapImage recapImage = hashMap.get(str.replace("png", "jpg"));
        if (recapImage != null) {
            Log.i("RecapImageAssetDelegate", "fetchBitmap", str2, Logger.getSimpleName((Object) recapImage.mTargetBitmap));
            return recapImage.mTargetBitmap;
        }
        throw new IllegalStateException("fetchBitmap fail : " + str2);
    }
}
