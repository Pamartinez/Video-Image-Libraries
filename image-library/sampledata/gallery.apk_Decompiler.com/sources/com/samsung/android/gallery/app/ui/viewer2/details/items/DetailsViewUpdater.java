package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface DetailsViewUpdater {
    void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult);

    void refinedItemInternal(MediaItem mediaItem) {
    }
}
