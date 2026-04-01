package com.samsung.android.gallery.app.ui.list.stories.headeritem;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface HeaderItem {
    void close();

    MediaItem getHeaderItem();

    MediaData getStoriesData();

    MediaItem getStoryById(int i2);

    HeaderItem open();

    int positionInStories();
}
