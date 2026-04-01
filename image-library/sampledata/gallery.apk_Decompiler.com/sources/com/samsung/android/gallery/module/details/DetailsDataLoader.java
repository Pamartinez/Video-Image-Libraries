package com.samsung.android.gallery.module.details;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsDataLoader {
    public static void notify(DetailsDataLoadCallback detailsDataLoadCallback, MediaItem mediaItem, DetailsUpdateKey detailsUpdateKey) {
        notify(detailsDataLoadCallback, mediaItem, new DetailsLoadResult(detailsUpdateKey));
    }

    public static void notify(DetailsDataLoadCallback detailsDataLoadCallback, MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        if (detailsDataLoadCallback == null || mediaItem == null) {
            Log.e((CharSequence) "DetailsDataLoader", "notify failed", Boolean.valueOf(detailsDataLoadCallback != null), mediaItem != null ? Long.valueOf(mediaItem.getFileId()) : "null");
        } else {
            detailsDataLoadCallback.onLoaded(mediaItem, detailsLoadResult);
        }
    }
}
