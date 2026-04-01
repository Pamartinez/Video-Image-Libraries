package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandPageItem extends PageItem {
    MediaItem mItem;

    public MediaItem getMediaItem() {
        return this.mItem;
    }

    public int getType() {
        return 5;
    }

    public OnDemandPageItem setMediaItem(MediaItem mediaItem) {
        this.mItem = mediaItem;
        return this;
    }
}
