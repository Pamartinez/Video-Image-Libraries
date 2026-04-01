package com.samsung.android.gallery.app.ui.viewer2.contentviewer;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface GroupLoader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SubItemLoadListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SubItemsInfo {
        public int mBestItemIndex = -1;
        public int mCurrentIndex = -1;
        public MediaItem mCurrentMediaItem;
        public final ArrayList<MediaItem> mSubItemList = new ArrayList<>();

        public boolean isLoaded() {
            if (this.mCurrentIndex != -1) {
                return true;
            }
            return false;
        }
    }

    void invalidateSubItems(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7, SubItemLoadListener subItemLoadListener);

    void updateModelSubItems(SubItemsInfo subItemsInfo);
}
