package com.samsung.android.gallery.module.dataset.tables;

import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import java.lang.ref.SoftReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsClusterItem implements ClusterItem {
    private SoftReference<MediaItem> mMediaItem;

    public String getDateRaw() {
        return "";
    }

    public long getFileSize() {
        return 0;
    }

    public String getLatitudeList() {
        return "";
    }

    public String getLocation() {
        return "";
    }

    public String getLongitudeList() {
        return "";
    }

    public MediaItem getMediaItem() {
        SoftReference<MediaItem> softReference = this.mMediaItem;
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }

    public String getTitle() {
        return null;
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.mMediaItem = new SoftReference<>(mediaItem);
    }
}
