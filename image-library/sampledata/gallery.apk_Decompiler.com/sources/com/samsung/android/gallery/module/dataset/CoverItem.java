package com.samsung.android.gallery.module.dataset;

import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CoverItem extends MediaItem {
    private boolean hasValidCover() {
        return !TextUtils.isEmpty(getAlbumCover());
    }

    public String buildThumbCacheKey() {
        return "cover/" + getFileId() + '/' + getPath() + '/' + getFileSize() + '/' + getOrientation();
    }

    public long getAlbumDateModified() {
        return ((Long) getTag("album:modified-time", Long.valueOf(getDateModified()))).longValue();
    }

    public long getAlbumFileId() {
        return ((Long) getTag("album:file-id", Long.valueOf(getFileId()))).longValue();
    }

    public String getPath() {
        if (hasValidCover()) {
            return getAlbumCover();
        }
        return super.getPath();
    }

    public boolean isCustomCover() {
        return hasValidCover();
    }
}
