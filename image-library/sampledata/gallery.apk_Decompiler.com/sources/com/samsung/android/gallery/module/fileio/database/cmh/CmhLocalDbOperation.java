package com.samsung.android.gallery.module.fileio.database.cmh;

import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOpObject;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.gallery.support.utils.FileUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhLocalDbOperation implements DbOperationInterface {
    public void updateDatabaseByCopy(Context context, FileInfo fileInfo) {
        Uri uri;
        MediaItem mediaItem = fileInfo.getMediaItem();
        if (mediaItem.isVideo()) {
            uri = getVideoUri();
        } else {
            uri = getImageUri();
        }
        getDatabaseOperation(context).add(DatabaseOpObject.burkInsert(uri).addValues(getContentValues().getCopyValues(mediaItem, fileInfo.getDestPath(), fileInfo.getFileMode())));
    }

    public void updateDatabaseByCopyInsteadOfMove(Context context, FileInfo fileInfo) {
        MediaItem mediaItem = fileInfo.getMediaItem();
        getDatabaseOperation(context).add(DatabaseOpObject.delete(ContentUri.getWritableUri(mediaItem)));
        String directoryFromPath = FileUtils.getDirectoryFromPath(fileInfo.getDestPath(), false);
        if (mediaItem.getStorageType() == StorageType.LocalCloud && SamsungCloudCompat.isSyncOff(context, directoryFromPath)) {
            SamsungCloudCompat.delete(context, mediaItem.getCloudServerId());
        }
    }

    public void updateDatabaseByMove(Context context, FileInfo fileInfo) {
        MediaItem mediaItem = fileInfo.getMediaItem();
        getDatabaseOperation(context).add(DatabaseOpObject.update(ContentUri.getWritableUri(mediaItem)).addValues(getContentValues().getMoveValues(mediaItem, fileInfo.getDestPath(), fileInfo.getFileMode())));
        String directoryFromPath = FileUtils.getDirectoryFromPath(fileInfo.getDestPath(), false);
        if (mediaItem.getStorageType() == StorageType.LocalCloud && SamsungCloudCompat.isSyncOff(context, directoryFromPath)) {
            SamsungCloudCompat.delete(context, mediaItem.getCloudServerId());
        }
    }
}
