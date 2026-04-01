package com.samsung.android.gallery.module.dataset.comparator;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumDefaultComparator implements IAlbumComparator {
    private final boolean mShowSystemFoldersTop;

    public AlbumDefaultComparator(boolean z) {
        this.mShowSystemFoldersTop = z;
    }

    private int getBucketIndex(int i2) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return BucketUtils.indexEx(i2);
        }
        return BucketUtils.index(i2);
    }

    public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem.getDisplayName() == null || mediaItem2.getDisplayName() == null) {
            return 0;
        }
        int bucketIndex = getBucketIndex(mediaItem.getAlbumID());
        int bucketIndex2 = getBucketIndex(mediaItem2.getAlbumID());
        if (this.mShowSystemFoldersTop && bucketIndex != bucketIndex2) {
            return Integer.compare(bucketIndex, bucketIndex2);
        }
        if (mediaItem.getAlbumOrder() != mediaItem2.getAlbumOrder()) {
            return Long.compare(mediaItem.getAlbumOrder(), mediaItem2.getAlbumOrder());
        }
        if (bucketIndex != bucketIndex2) {
            return Integer.compare(bucketIndex, bucketIndex2);
        }
        int compareTo = mediaItem.getDisplayName().toUpperCase().compareTo(mediaItem2.getDisplayName().toUpperCase());
        if (compareTo != 0) {
            return compareTo;
        }
        return Integer.compare(mediaItem.getAlbumID(), mediaItem2.getAlbumID());
    }
}
