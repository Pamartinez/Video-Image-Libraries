package com.samsung.android.gallery.widget.hoverview;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HoverPreviewListData implements IHoverPreviewData {
    private final ArrayList<MediaItem> mMediaItemList;

    public HoverPreviewListData(ArrayList<MediaItem> arrayList) {
        this.mMediaItemList = arrayList;
    }

    public int getCount() {
        return this.mMediaItemList.size();
    }

    public MediaItem getMediaItem(int i2) {
        return this.mMediaItemList.get(i2);
    }

    public void recycle() {
        this.mMediaItemList.clear();
    }

    public void updateListData(ArrayList<MediaItem> arrayList) {
        this.mMediaItemList.clear();
        this.mMediaItemList.addAll(arrayList);
    }
}
