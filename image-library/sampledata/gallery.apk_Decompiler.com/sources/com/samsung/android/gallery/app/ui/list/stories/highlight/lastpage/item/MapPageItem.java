package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MapPageItem extends PageItem {
    private MediaItem[] mItems = new MediaItem[0];
    private int mLatitudeKey;
    private int mLongitudeKey;

    private void initLocationKey(MediaItem[] mediaItemArr) {
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for (MediaItem mediaItem : mediaItemArr) {
            sb2.append(mediaItem.getLatitude());
            sb3.append(mediaItem.getLongitude());
        }
        this.mLatitudeKey = sb2.toString().hashCode();
        this.mLongitudeKey = sb3.toString().hashCode();
    }

    public boolean equalItems(PageItem pageItem) {
        if (!(pageItem instanceof MapPageItem)) {
            return super.equalItems(pageItem);
        }
        MapPageItem mapPageItem = (MapPageItem) pageItem;
        if (mapPageItem.mLatitudeKey == this.mLatitudeKey && mapPageItem.mLongitudeKey == this.mLongitudeKey) {
            return true;
        }
        return false;
    }

    public int getType() {
        return 3;
    }

    public MapPageItem setItems(MediaItem[] mediaItemArr) {
        this.mItems = mediaItemArr;
        initLocationKey(mediaItemArr);
        return this;
    }
}
