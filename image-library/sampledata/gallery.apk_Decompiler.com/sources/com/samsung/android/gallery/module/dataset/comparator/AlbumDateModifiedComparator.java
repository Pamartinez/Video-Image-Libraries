package com.samsung.android.gallery.module.dataset.comparator;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.BucketUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumDateModifiedComparator implements IAlbumComparator {
    private final boolean mIsAscending;
    private final boolean mShowSystemFoldersTop;

    public AlbumDateModifiedComparator(boolean z, boolean z3) {
        this.mIsAscending = z;
        this.mShowSystemFoldersTop = z3;
    }

    public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
        int index = BucketUtils.index(mediaItem.getAlbumID());
        int index2 = BucketUtils.index(mediaItem2.getAlbumID());
        if (this.mShowSystemFoldersTop && index != index2) {
            return Integer.compare(index, index2);
        }
        if (BucketUtils.isCameras(mediaItem.getAlbumID()) && BucketUtils.isCameras(mediaItem2.getAlbumID())) {
            return BucketUtils.isCamera(mediaItem.getAlbumID()) ? -1 : 1;
        }
        if (BucketUtils.isCameras(mediaItem.getAlbumID())) {
            return -1;
        }
        if (BucketUtils.isCameras(mediaItem2.getAlbumID())) {
            return 1;
        }
        if (mediaItem.isFolder() && !mediaItem2.isFolder()) {
            return -1;
        }
        if (!mediaItem.isFolder() && mediaItem2.isFolder()) {
            return 1;
        }
        if (this.mIsAscending) {
            return Long.compare(mediaItem.getDateModified(), mediaItem2.getDateModified());
        }
        return Long.compare(mediaItem2.getDateModified(), mediaItem.getDateModified());
    }
}
