package com.samsung.android.gallery.module.fileio.compat;

import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileOpApiCloud extends FileOpApi {
    public boolean copy(FileOpJob fileOpJob) {
        Log.e(this.TAG, "copy failed. not support for cloud only");
        return false;
    }

    public boolean move(FileOpJob fileOpJob) {
        Log.e(this.TAG, "move failed. not support for cloud only");
        return false;
    }

    public boolean rename(FileOpJob fileOpJob) {
        if (fileOpJob.isOverWrite()) {
            Log.e(this.TAG, "rename-overwrite failed. not support for cloud only");
            return false;
        }
        String str = this.TAG;
        Log.d(str, "rename " + fileOpJob);
        if (SecMediaStoreApi.renameCloud(fileOpJob.source, fileOpJob.target)) {
            return true;
        }
        String str2 = this.TAG;
        FileOpError fileOpError = FileOpError.UpdateUriFailed;
        fileOpJob.e(str2, fileOpError, "rename cloud failed " + fileOpJob.source.getFileId());
        return false;
    }
}
