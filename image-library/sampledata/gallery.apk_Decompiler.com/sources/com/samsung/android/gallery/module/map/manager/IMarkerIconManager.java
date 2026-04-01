package com.samsung.android.gallery.module.map.manager;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMarkerIconManager {
    Bitmap makeIcon(Bitmap bitmap, int i2, boolean z, ThumbnailInterface thumbnailInterface);

    void setBackgroundResource(int i2, int i7);

    void setMarkerTitleVisibility(boolean z) {
    }

    void updateSize(Context context) {
    }
}
