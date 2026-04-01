package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FocusPositionHolder {
    MediaItem mCurrent;
    int mPosition = -1;

    public void clear() {
        this.mCurrent = null;
        this.mPosition = -1;
    }

    public boolean equals(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getFileId() != mediaItem2.getFileId()) {
            return false;
        }
        return true;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public boolean isVideo() {
        MediaItem mediaItem = this.mCurrent;
        if (mediaItem == null || !mediaItem.isVideo()) {
            return false;
        }
        return true;
    }

    public void restore(MediaData mediaData) {
        if (mediaData == null || mediaData.getCount() <= 0) {
            this.mCurrent = null;
            this.mPosition = -1;
            return;
        }
        int count = mediaData.getCount();
        int i2 = this.mPosition;
        if (i2 >= count) {
            int i7 = count - 1;
            this.mPosition = i7;
            this.mCurrent = mediaData.readCache(i7);
        } else if (!equals(mediaData.readCache(i2), this.mCurrent)) {
            int min = Math.min(this.mPosition + 2, count - 1);
            for (int max = Math.max(this.mPosition - 2, 0); max <= min; max++) {
                if (equals(mediaData.readCache(max), this.mCurrent)) {
                    this.mCurrent = mediaData.readCache(max);
                    this.mPosition = max;
                    return;
                }
            }
            for (int i8 = 0; i8 < mediaData.getCount(); i8++) {
                if (equals(mediaData.readCache(i8), this.mCurrent)) {
                    this.mCurrent = mediaData.readCache(i8);
                    this.mPosition = i8;
                    return;
                }
            }
            int i10 = this.mPosition;
            if (i10 >= 0 && i10 < count) {
                this.mCurrent = mediaData.readCache(i10);
            }
        }
    }

    public void update(MediaItem mediaItem, int i2) {
        this.mCurrent = mediaItem;
        this.mPosition = i2;
    }
}
