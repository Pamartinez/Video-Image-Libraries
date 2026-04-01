package com.samsung.android.gallery.module.fileio.cmh;

import android.content.Context;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.fileio.abstraction.FileOpResult;
import com.samsung.android.gallery.module.fileio.abstraction.FileOperation;
import com.samsung.android.gallery.module.fileio.type.FileInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhCloudFileOperation extends FileOperation {
    public FileOpResult copyInternal(Context context, FileInfo fileInfo) {
        FileOpResult result = FileOpResult.toResult(SamsungCloudCompat.copy(context, fileInfo.getMediaItem().getCloudServerId(), fileInfo.getDestPath()));
        updateProgress(1);
        return result;
    }

    public int getType() {
        return 1;
    }

    public FileOpResult moveInternal(Context context, FileInfo fileInfo) {
        FileOpResult result = FileOpResult.toResult(SamsungCloudCompat.move(context, fileInfo.getMediaItem().getCloudServerId(), fileInfo.getDestPath(), true));
        updateProgress(1);
        return result;
    }

    public boolean support(FileItemInterface fileItemInterface) {
        if (!fileItemInterface.isCloudOnly() || fileItemInterface.isBurstShot()) {
            return false;
        }
        return true;
    }

    public String tag() {
        return "CmhCloudFileOp";
    }
}
