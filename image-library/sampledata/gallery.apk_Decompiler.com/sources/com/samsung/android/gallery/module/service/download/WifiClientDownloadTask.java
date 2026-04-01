package com.samsung.android.gallery.module.service.download;

import android.content.Context;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WifiClientDownloadTask extends CloudDownloadTask {
    final boolean SUPPORT = PocFeatures.isEnabled(PocFeatures.WifiGalleryClient);

    public WifiClientDownloadTask(Context context) {
        super(context);
    }

    public boolean downloadInternal(MediaItem mediaItem) {
        if (!this.SUPPORT || mediaItem.getStorageType() != StorageType.RemoteItem) {
            return super.downloadInternal(mediaItem);
        }
        return RemoteGalleryUtil.download(mediaItem, (Runnable) null);
    }

    public boolean filter(MediaItem mediaItem) {
        if ((!this.SUPPORT || mediaItem.getStorageType() != StorageType.RemoteItem) && !super.filter(mediaItem)) {
            return false;
        }
        return true;
    }

    public boolean isValid(MediaItem mediaItem) {
        if ((!this.SUPPORT || mediaItem.getStorageType() != StorageType.RemoteItem) && !super.isValid(mediaItem)) {
            return false;
        }
        return true;
    }
}
