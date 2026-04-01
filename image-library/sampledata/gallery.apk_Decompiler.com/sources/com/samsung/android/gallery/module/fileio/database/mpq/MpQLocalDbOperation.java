package com.samsung.android.gallery.module.fileio.database.mpq;

import android.content.ContentUris;
import android.content.Context;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOpObject;
import com.samsung.android.gallery.module.fileio.type.FileInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpQLocalDbOperation implements DbOperationInterface {
    public void updateDatabaseByCopy(Context context, FileInfo fileInfo) {
        getDatabaseOperation(context).add(DatabaseOpObject.burkInsert(getFilesUri(fileInfo.getDestPath())).addValues(getContentValues().getCopyValues(fileInfo.getMediaItem(), fileInfo.getDestPath(), fileInfo.getFileMode())));
    }

    public void updateDatabaseByCopyInsteadOfMove(Context context, FileInfo fileInfo) {
        MediaItem mediaItem = fileInfo.getMediaItem();
        getDatabaseOperation(context).add(DatabaseOpObject.delete(ContentUris.withAppendedId(getFilesUri(), mediaItem.getMediaId())));
        if (mediaItem.getStorageType() == StorageType.LocalCloud) {
            getDatabaseOperation(context).add(DatabaseOpObject.delete(getCloudUri()).withSelection("cloud_server_id = ?", new String[]{String.valueOf(mediaItem.getCloudServerId())}));
        }
    }

    public void updateDatabaseByMove(Context context, FileInfo fileInfo) {
        MediaItem mediaItem = fileInfo.getMediaItem();
        getDatabaseOperation(context).add(DatabaseOpObject.update(ContentUris.withAppendedId(getFilesUri(fileInfo.getDestPath()), mediaItem.getMediaId())).addValues(getContentValues().getMoveValues(mediaItem, fileInfo.getDestPath(), fileInfo.getFileMode())));
    }

    public void updateDatabaseByOverWrite(Context context, FileInfo fileInfo) {
        getDatabaseOperation(context).apply();
        getDatabaseOperation(context).add(DatabaseOpObject.delete(getFilesUri()).immediate().withSelection("_data = ?", new String[]{fileInfo.getDestPath()}));
        if (fileInfo.getStorageType() == StorageType.LocalCloud) {
            getDatabaseOperation(context).add(DatabaseOpObject.delete(getCloudUri()).immediate().withSelection("_data = ?", new String[]{fileInfo.getDestPath()}));
        }
    }
}
