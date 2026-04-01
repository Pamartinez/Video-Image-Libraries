package com.samsung.android.gallery.module.fileio.compat;

import A.a;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileOpApiLocal extends FileOpApi {
    public boolean copy(FileOpJob fileOpJob) {
        String str = this.TAG;
        Log.d(str, "copy " + fileOpJob);
        if (!requireFileExistent(fileOpJob.source.getPath())) {
            fileOpJob.e(this.TAG, FileOpError.SourceInvalid, "source not available");
            return false;
        } else if (fileOpJob.isOverWrite()) {
            return copyOverwrite(fileOpJob);
        } else {
            if (requireFileNotExistent(fileOpJob.target) && copy(new SecureFile(fileOpJob.source.getPath()), new SecureFile(fileOpJob.target))) {
                return true;
            }
            fileOpJob.e(this.TAG, FileOpError.CopyFileFailed, "copy failed");
            return false;
        }
    }

    public boolean copyOverwrite(FileOpJob fileOpJob) {
        String makeUniqueFilename = FileUtils.makeUniqueFilename(fileOpJob.target, "_new_");
        if (!requireFileNotExistent(makeUniqueFilename) || !copy(new SecureFile(fileOpJob.source.getPath()), new SecureFile(makeUniqueFilename))) {
            String str = this.TAG;
            FileOpError fileOpError = FileOpError.CopyFileFailed;
            fileOpJob.e(str, fileOpError, "copy tmp failed " + Logger.getEncodedString(makeUniqueFilename));
            return false;
        } else if (!MediaStoreApi.moveToTrash(fileOpJob.target) && !MediaStoreApi.delete(fileOpJob.target)) {
            String str2 = this.TAG;
            FileOpError fileOpError2 = FileOpError.DeleteUriFailed;
            fileOpJob.e(str2, fileOpError2, "delete target failed " + Logger.getEncodedString(fileOpJob.target));
            return false;
        } else if (FileUtils.rename(makeUniqueFilename, fileOpJob.target)) {
            return true;
        } else {
            fileOpJob.e(this.TAG, FileOpError.RenameFileFailed, "rename target failed");
            return false;
        }
    }

    public boolean delete(FileOpJob fileOpJob) {
        Uri writableUri = ContentUri.getWritableUri(fileOpJob.source);
        Log.d(this.TAG, OpenSessionApi.ITEM_LIMIT_DELETE, Long.valueOf(fileOpJob.source.getFileId()), writableUri);
        if (MediaStoreApi.delete(writableUri) || MediaStoreApi.delete(fileOpJob.source.getPath())) {
            return true;
        }
        String str = this.TAG;
        FileOpError fileOpError = FileOpError.DeleteUriFailed;
        fileOpJob.e(str, fileOpError, "delete source failed " + writableUri + ArcCommonLog.TAG_COMMA + FileUtils.info(fileOpJob.source.getPath()));
        return false;
    }

    public boolean move(FileOpJob fileOpJob) {
        if (!copy(fileOpJob) || !delete(fileOpJob)) {
            return false;
        }
        return true;
    }

    public boolean rename(FileOpJob fileOpJob) {
        String str = this.TAG;
        Log.d(str, "rename " + fileOpJob);
        if (!requireFileExistent(fileOpJob.source.getPath())) {
            fileOpJob.e(this.TAG, FileOpError.SourceInvalid, "source not available");
            return false;
        } else if (fileOpJob.source.isDrm()) {
            return renameFile(fileOpJob);
        } else {
            if (fileOpJob.isOverWrite()) {
                return renameOverwrite(fileOpJob);
            }
            if (requireFileNotExistent(fileOpJob.target) && MediaStoreApi.rename(ContentUri.getWritableUri(fileOpJob.source), fileOpJob.target)) {
                return true;
            }
            fileOpJob.e(this.TAG, FileOpError.RenameUriFailed, "rename failed");
            return false;
        }
    }

    public boolean renameFile(FileOpJob fileOpJob) {
        if (fileOpJob.isOverWrite()) {
            String makeUniqueFilename = FileUtils.makeUniqueFilename(fileOpJob.target, "_tmp_");
            if (!FileUtils.rename(fileOpJob.target, makeUniqueFilename) || !FileUtils.rename(fileOpJob.source.getPath(), fileOpJob.target)) {
                fileOpJob.e(this.TAG, FileOpError.RenameFileFailed, "file-rename-overwrite failed");
                return false;
            } else if (!FileUtils.delete(makeUniqueFilename)) {
                a.u("renameFile failed to delete tmp ", makeUniqueFilename, this.TAG);
            }
        } else if (!requireFileNotExistent(fileOpJob.target) || !FileUtils.rename(fileOpJob.source.getPath(), fileOpJob.target)) {
            fileOpJob.e(this.TAG, FileOpError.RenameFileFailed, "file-rename failed");
            return false;
        }
        MediaScanner.scanFiles(AppResources.getAppContext(), new String[]{fileOpJob.source.getPath(), fileOpJob.target}, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
        return true;
    }

    public boolean renameOverwrite(FileOpJob fileOpJob) {
        String makeUniqueFilename = FileUtils.makeUniqueFilename(fileOpJob.target, "_new_");
        if (!requireFileNotExistent(makeUniqueFilename) || !MediaStoreApi.rename(ContentUri.getWritableUri(fileOpJob.source), makeUniqueFilename)) {
            String str = this.TAG;
            FileOpError fileOpError = FileOpError.RenameUriFailed;
            fileOpJob.e(str, fileOpError, "rename(mp) tmp failed " + Logger.getEncodedString(makeUniqueFilename));
            return false;
        } else if (!MediaStoreApi.moveToTrash(fileOpJob.target) && !MediaStoreApi.delete(fileOpJob.target)) {
            String str2 = this.TAG;
            FileOpError fileOpError2 = FileOpError.DeleteUriFailed;
            fileOpJob.e(str2, fileOpError2, "delete target failed " + Logger.getEncodedString(fileOpJob.target));
            return false;
        } else if (MediaStoreApi.rename(ContentUri.getWritableUri(fileOpJob.source), fileOpJob.target)) {
            return true;
        } else {
            fileOpJob.e(this.TAG, FileOpError.RenameFileFailed, "rename target failed");
            return false;
        }
    }
}
