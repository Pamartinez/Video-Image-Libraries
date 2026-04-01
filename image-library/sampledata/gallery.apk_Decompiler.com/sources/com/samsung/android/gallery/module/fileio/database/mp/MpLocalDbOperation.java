package com.samsung.android.gallery.module.fileio.database.mp;

import android.content.ContentUris;
import android.content.Context;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOpObject;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.gallery.support.utils.FileUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpLocalDbOperation implements DbOperationInterface {
    public void updateDatabaseByCopy(Context context, FileInfo fileInfo) {
        getDatabaseOperation(context).add(DatabaseOpObject.burkInsert(getFilesUri(fileInfo.getDestPath())).addValues(getContentValues().getCopyValues(fileInfo.getMediaItem(), fileInfo.getDestPath(), fileInfo.getFileMode())).addMyTag(fileInfo.getMediaItem(), fileInfo.getDestPath()));
    }

    public void updateDatabaseByCopyInsteadOfMove(Context context, FileInfo fileInfo) {
        MediaItem mediaItem = fileInfo.getMediaItem();
        getDatabaseOperation(context).add(DatabaseOpObject.delete(ContentUri.getWritableUri(mediaItem)));
        String directoryFromPath = FileUtils.getDirectoryFromPath(fileInfo.getDestPath(), false);
        if (mediaItem.getStorageType() == StorageType.LocalCloud && SamsungCloudCompat.isSyncOff(context, directoryFromPath)) {
            getDatabaseOperation(context).add(DatabaseOpObject.delete(getCloudUri()).withSelection("cloud_server_id = ?", new String[]{String.valueOf(mediaItem.getCloudServerId())}));
        }
    }

    public void updateDatabaseByMove(Context context, FileInfo fileInfo) {
        getDatabaseOperation(context).add(DatabaseOpObject.update(ContentUris.withAppendedId(getFilesUri(fileInfo.getDestPath()), fileInfo.getMediaItem().getFileId())).addValues(getContentValues().getMoveValues(fileInfo.getMediaItem(), fileInfo.getDestPath(), fileInfo.getFileMode())));
    }

    public void updateDatabaseByOverWrite(Context context, FileInfo fileInfo) {
        getDatabaseOperation(context).apply();
        getDatabaseOperation(context).add(DatabaseOpObject.delete(getFilesUri()).immediate().withSelection("_data = ?", new String[]{fileInfo.getDestPath()}));
    }
}
