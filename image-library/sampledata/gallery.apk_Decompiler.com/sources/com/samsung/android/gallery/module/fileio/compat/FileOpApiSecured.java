package com.samsung.android.gallery.module.fileio.compat;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.nondestruction.NonDestructionManager;
import com.samsung.android.gallery.module.nondestruction.NondestructiveEditor;
import com.samsung.android.gallery.module.secured.KeepStorageManager;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.cache.Crc;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileOpApiSecured extends FileOpApi {
    private static final boolean SUPPORT_NDE = PreferenceFeatures.OneUi40.SUPPORT_NONDESTRUCTIVE_SLOW_MO;
    private final FileOpApi api;

    public FileOpApiSecured(FileOpApi fileOpApi) {
        this.api = fileOpApi;
    }

    private boolean moveFromSecured(FileOpJob fileOpJob) {
        String str = (String) fileOpJob.source.getExtra(ExtrasID.ORIGIN_PATH, "");
        fileOpJob.target = str;
        if (FileUtils.exists(str)) {
            fileOpJob.target = FileNumberingBuilder.build(fileOpJob.target);
        }
        if (!requireFileNotExistent(fileOpJob.target) || !copy(new SecureFile(fileOpJob.source.getPath()), new SecureFile(fileOpJob.target))) {
            fileOpJob.e(this.TAG, FileOpError.CopyFileFailed, "move#copy failed");
            return false;
        }
        if (SUPPORT_NDE && fileOpJob.source.getOriginalFileHash() != null) {
            moveSecuredToNde(fileOpJob.source);
        }
        if (!fileOpJob.target.startsWith("/data/sec")) {
            MediaScannerConnection.scanFile(AppResources.getAppContext(), new String[]{fileOpJob.target}, (String[]) null, new g(this));
        }
        if (PrivateDatabase.getInstance().delete(fileOpJob.source) > 0) {
            return true;
        }
        fileOpJob.e(this.TAG, FileOpError.DeleteFileFailed, "move#delete failed");
        return false;
    }

    private void moveNdeToSecured(FileItemInterface fileItemInterface) {
        String str;
        String originalFileHash = fileItemInterface.getOriginalFileHash();
        if (TextUtils.isEmpty(originalFileHash)) {
            str = null;
        } else {
            str = NonDestructionManager.getInstance().computePath(originalFileHash);
        }
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            File file2 = new File(KeepStorageManager.getInstance().getNdePath(), file.getName());
            if (!file.exists() || !copy(file, file2, (Consumer<Float>) null)) {
                Log.e((CharSequence) this.TAG, "moveNdsToSecured failed", Boolean.valueOf(file.exists()));
            } else if (!file.delete()) {
                Log.w(this.TAG, "moveNdsToSecured source-deleting failed");
            }
        }
    }

    private void moveSecuredToNde(FileItemInterface fileItemInterface) {
        String str;
        String originalFileHash = fileItemInterface.getOriginalFileHash();
        if (!TextUtils.isEmpty(originalFileHash)) {
            String[] split = originalFileHash.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            if (split.length > 1) {
                str = split[1];
            } else {
                str = null;
            }
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                File file2 = new File(KeepStorageManager.getInstance().getNdePath(), file.getName());
                if (!file2.exists() || !copy(file2, file, (Consumer<Float>) null)) {
                    Log.e((CharSequence) this.TAG, "moveSecuredToNds failed", Boolean.valueOf(file2.exists()));
                    return;
                }
                if (!file2.delete()) {
                    Log.w(this.TAG, "moveSecuredToNds source-deleting failed");
                }
                new NondestructiveEditor().insertOriginalData(AppResources.getAppContext(), file, split[0]);
            }
        }
    }

    private boolean moveToSecured(FileOpJob fileOpJob) {
        byte[] generateKey = CacheManager.generateKey(fileOpJob.source.getDiskCacheKey(), fileOpJob.source.getDateModified());
        fileOpJob.target = KeepStorageManager.getInstance().getFilesPath() + File.separator + Long.toHexString(Crc.getCrc64Long(generateKey)) + "." + FileUtils.getExtension(fileOpJob.source.getPath());
        if (fileOpJob.source.isCloudOnly()) {
            if (!downloadCloudFile(fileOpJob)) {
                Log.e(this.TAG, "move#download-cloud failed");
                return false;
            }
        } else if (!this.api.move(fileOpJob)) {
            Log.e(this.TAG, "move failed");
            return false;
        }
        if (SUPPORT_NDE) {
            moveNdeToSecured(fileOpJob.source);
        }
        if (PrivateDatabase.getInstance().upsert(fileOpJob.source, fileOpJob.target) <= 0) {
            return false;
        }
        if (!fileOpJob.source.isCloud()) {
            return true;
        }
        SecMediaStoreApi.deleteCloudByServerId(fileOpJob.source.getCloudServerId());
        return true;
    }

    public boolean delete(FileOpJob fileOpJob) {
        return this.api.delete(fileOpJob);
    }

    public boolean move(FileOpJob fileOpJob) {
        int i2 = fileOpJob.operation;
        if (i2 == 65541) {
            return moveToSecured(fileOpJob);
        }
        if (i2 == 131077) {
            return moveFromSecured(fileOpJob);
        }
        String str = this.TAG;
        Log.e(str, "move skip. wrong operation=" + Integer.toHexString(fileOpJob.operation));
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveFromSecured$0(String str, Uri uri) {
    }
}
