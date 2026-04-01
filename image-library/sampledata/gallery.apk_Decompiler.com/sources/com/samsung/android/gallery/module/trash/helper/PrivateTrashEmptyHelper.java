package com.samsung.android.gallery.module.trash.helper;

import A.a;
import android.content.Context;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PrivateTrashEmptyHelper extends TrashEmptyHelper {
    public PrivateTrashEmptyHelper(Context context, boolean z) {
        super(context, z);
    }

    public void emptyItem(FileItemInterface fileItemInterface) {
        if (!this.mInterrupted) {
            long currentTimeMillis = System.currentTimeMillis();
            increaseProgress();
            MediaType mediaType = fileItemInterface.getMediaType();
            StorageType storageType = fileItemInterface.getStorageType();
            this.mLogger.started(mediaType, storageType);
            if (PrivateDatabase.getInstance().delete(fileItemInterface) > 0) {
                this.mLogger.succeed(mediaType, storageType);
            } else {
                String str = this.TAG;
                Log.e(str, "emptyItem failed" + Logger.v(Long.valueOf(fileItemInterface.getFileId()), Boolean.valueOf(FileUtils.exists(fileItemInterface.getPath()))) + " " + fileItemInterface);
                this.mLogger.failed(mediaType, storageType);
            }
            ThumbnailLoader.getInstance().removeDiskCache(fileItemInterface);
            String str2 = this.TAG;
            a.A(new Object[]{Long.valueOf(fileItemInterface.getFileId()), Long.valueOf(currentTimeMillis)}, new StringBuilder("emptyItem"), str2);
        }
    }
}
