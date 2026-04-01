package com.samsung.android.gallery.module.data;

import com.samsung.android.gallery.support.utils.Copyable;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CloudData implements Copyable<CloudData> {
    public int albumSyncStatus;
    public String data2;
    public int fileStatus;
    public String hash;
    public long id = -1;
    public String originalBinaryHash;
    public long originalBinarySize;
    public long originalSize;
    public int revision = -1;
    public String serverId;
    public String serverPath;
    public String thumbPath;

    public static boolean isAlbumCloudSync(MediaItem mediaItem) {
        if (mediaItem != null) {
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS && mediaItem.isMergedAlbum()) {
                for (MediaItem next : mediaItem.getChildList()) {
                    if (next != null && next.getCloudData().albumSyncStatus == 0) {
                        return false;
                    }
                }
                return true;
            } else if (mediaItem.getCloudData().albumSyncStatus != 0) {
                return true;
            }
        }
        return false;
    }

    public static CloudData of(MediaItem mediaItem) {
        return mediaItem.getCloudData();
    }

    public CloudData parse(CursorHolder cursorHolder, String str) {
        if (str == null) {
            str = cursorHolder.getString(cursorHolder.indexCloudServerId, (String) null);
        }
        this.serverId = str;
        this.id = cursorHolder.getLong(cursorHolder.indexCloudId, 0);
        this.fileStatus = cursorHolder.getInt(cursorHolder.indexFileStatus, 0);
        this.thumbPath = cursorHolder.getString(cursorHolder.indexCloudThumbPath, (String) null);
        this.serverPath = cursorHolder.getString(cursorHolder.indexCloudServerPath, (String) null);
        this.hash = cursorHolder.getString(cursorHolder.indexHash, (String) null);
        this.data2 = cursorHolder.getString(cursorHolder.indexData2, (String) null);
        this.originalSize = cursorHolder.getLong(cursorHolder.indexCloudOriginalSize, 0);
        this.revision = cursorHolder.getInt(cursorHolder.indexCloudRevision, -1);
        this.originalBinaryHash = cursorHolder.getString(cursorHolder.indexCloudOriginalBinaryHash, (String) null);
        this.originalBinarySize = cursorHolder.getLong(cursorHolder.indexCloudOriginalBinarySize, 0);
        return this;
    }

    public CloudData copyOf() {
        try {
            return (CloudData) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e((CharSequence) getClass().getSimpleName(), "clone failed", (Throwable) e);
            return this;
        }
    }
}
