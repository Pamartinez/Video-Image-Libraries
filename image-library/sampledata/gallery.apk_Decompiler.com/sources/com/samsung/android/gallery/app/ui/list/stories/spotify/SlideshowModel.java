package com.samsung.android.gallery.app.ui.list.stories.spotify;

import com.samsung.android.gallery.database.dbtype.StoryCategoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowModel {
    public String getSubtitle(MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        return MediaItemStory.getStoryTimeDuration(mediaItem);
    }

    public String getTitle(MediaItem mediaItem) {
        if (mediaItem == null) {
            return AppResources.getString(R.string.story_category_my_story);
        }
        if (PreferenceFeatures.OneUi30.MEMORIES) {
            return mediaItem.getTitle();
        }
        return AppResources.getString(StoryCategoryType.getTitle(MediaItemStory.getStoryCategoryType(mediaItem)));
    }
}
