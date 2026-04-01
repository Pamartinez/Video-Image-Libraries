package com.samsung.android.gallery.module.dataset.tables;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DataRecord extends DefaultRecord {
    final long mDateModified;
    final long mMediaId;
    boolean mUsed = false;

    public DataRecord(MediaItem mediaItem) {
        super(mediaItem);
        this.mMediaId = mediaItem.getMediaId();
        this.mDateModified = mediaItem.getDateModified();
    }

    public MediaItem get() {
        boolean z;
        if (this.mUsed || this.mMediaItem != null) {
            z = true;
        } else {
            z = false;
        }
        this.mUsed = z;
        return this.mMediaItem;
    }

    public boolean isUsed() {
        return this.mUsed;
    }
}
