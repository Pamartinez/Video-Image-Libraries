package com.samsung.android.gallery.module.fileio.cmh;

import android.content.Context;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.abstraction.FileOpResult;
import com.samsung.android.gallery.module.fileio.abstraction.LocalCloudFileOperation;
import com.samsung.android.gallery.module.fileio.type.FileInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhLocalCloudFileOperation extends LocalCloudFileOperation {
    private FileOpResult copyInsteadOfMove(Context context, FileInfo fileInfo) {
        FileOpResult copyOperation = copyOperation(context, fileInfo);
        if (FileOpResult.OP_LOCAL_OK.equal(copyOperation)) {
            getDbOperation().updateDatabaseByCopyInsteadOfMove(context, fileInfo);
        }
        return copyOperation;
    }

    private FileOpResult copyOperation(Context context, FileInfo fileInfo) {
        if (!copyPrimitive(fileInfo.getMediaItem().getPath(), fileInfo.getDestPath(), fileInfo.getFileMode())) {
            return FileOpResult.OP_LOCAL_FAIL;
        }
        updateProgress(0);
        getDbOperation().updateDatabaseByCopy(context, fileInfo);
        return FileOpResult.OP_LOCAL_OK;
    }

    public FileOpResult copyInternal(Context context, FileInfo fileInfo) {
        return copyOperation(context, fileInfo);
    }

    public int getType() {
        return 0;
    }

    public FileOpResult moveInternal(Context context, FileInfo fileInfo) {
        MediaItem mediaItem = fileInfo.getMediaItem();
        String destPath = fileInfo.getDestPath();
        if (fileInfo.isCopyInsteadMoveMode()) {
            return copyInsteadOfMove(context, fileInfo);
        }
        if (!movePrimitive(mediaItem.getPath(), destPath)) {
            return FileOpResult.OP_LOCAL_FAIL;
        }
        getDbOperation().updateDatabaseByMove(context, fileInfo);
        updateProgress(mediaItem.getFileSize());
        return FileOpResult.OP_LOCAL_OK;
    }

    public boolean support(FileItemInterface fileItemInterface) {
        if (!fileItemInterface.isLocal() || fileItemInterface.isBurstShot()) {
            return false;
        }
        return true;
    }

    public String tag() {
        return "CmhLocalCloudFileOp";
    }
}
