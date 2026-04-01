package com.samsung.android.gallery.module.map.manager;

import android.graphics.Bitmap;
import androidx.core.content.ContextCompat;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.AppResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleMarkerIconManager implements IMarkerIconManager {
    public Bitmap makeIcon(Bitmap bitmap, int i2, boolean z, ThumbnailInterface thumbnailInterface) {
        return BitmapUtils.getBitmapFromDrawable(ContextCompat.getDrawable(AppResources.getAppContext(), R$drawable.gallery_ic_memory_location_map));
    }

    public void setBackgroundResource(int i2, int i7) {
    }
}
