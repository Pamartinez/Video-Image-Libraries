package com.samsung.android.gallery.module.fileio.database.mp;

import android.content.ContentUris;
import android.content.Context;
import com.samsung.android.gallery.module.cloud.SCloudHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOpObject;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.gallery.support.utils.FileUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpCloudDbOperation implements DbOperationInterface {
    public void updateDatabaseByCopy(Context context, FileInfo fileInfo) {
        getDatabaseOperation(context).add(DatabaseOpObject.insert(getCloudUri()).immediate().addValues(getContentValues().getCopyValues(fileInfo.getMediaItem(), fileInfo.getDestPath(), fileInfo.getFileMode())));
    }

    public void updateDatabaseByMove(Context context, FileInfo fileInfo) {
        MediaItem mediaItem = fileInfo.getMediaItem();
        getDatabaseOperation(context).add(DatabaseOpObject.update(ContentUris.withAppendedId(getCloudUri(), mediaItem.getMediaId())).immediate().addValues(getContentValues().getMoveValues(mediaItem, fileInfo.getDestPath(), fileInfo.getFileMode())));
    }

    public void updateDatabaseByOverWrite(Context context, FileInfo fileInfo) {
        getDatabaseOperation(context).add(DatabaseOpObject.delete(getCloudUri()).immediate().withSelection("_data2 = ?", new String[]{SCloudHelper.getCloudRemotePath(fileInfo.getMediaItem().getCloudData2(), FileUtils.getDirectoryFromPath(fileInfo.getDestPath(), false), FileUtils.getNameFromPath(fileInfo.getDestPath()))}));
    }
}
