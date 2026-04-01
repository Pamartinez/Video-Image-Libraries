package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapCoverItem extends PageItem {
    private MediaItem mItem;
    private String mSubTitle;
    private String mTitle;

    public RecapCoverItem(MediaItem mediaItem) {
        this.mItem = mediaItem;
    }

    public MediaItem getMediaItem() {
        return this.mItem;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getType() {
        return 7;
    }

    public void setSubTitle(String str) {
        this.mSubTitle = str;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }
}
