package com.samsung.android.gallery.app.ui.list.stories.util;

import com.samsung.android.gallery.module.abstraction.StoryCoverData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CoverHelper {
    public static void changeAttributeOriginalCoverItem(MediaItem mediaItem) {
        StoryCoverData storyCoverData = (StoryCoverData) MediaItemStory.getStoryOriginCoverData(mediaItem);
        if (storyCoverData != null) {
            mediaItem.setPath(storyCoverData.path);
            mediaItem.setOrientation(storyCoverData.orientation);
            mediaItem.setMediaType(storyCoverData.mediaType);
            mediaItem.setSize(storyCoverData.width, storyCoverData.height);
        }
    }
}
