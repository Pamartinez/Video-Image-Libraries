package com.samsung.android.gallery.module.trash.helper;

import android.content.Context;
import android.text.TextUtils;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.trash.abstraction.NoneDestructionOperationType;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.module.trash.support.TrashRestoreLogger;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileAuth;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.scsp.media.Media;
import i.C0212a;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashRestoreHelper extends TrashHelper {
    private static final boolean SUPPORT_CHANGE_BEST_ITEM = Features.isEnabled(Features.SUPPORT_CHANGE_BEST_ITEM);
    private CloudErrorType mErrorType = CloudErrorType.None;
    private final boolean mIsMHS;
    private final boolean mIsRestoreAll;
    private final TrashRestoreLogger mLogger;

    public TrashRestoreHelper(Context context, boolean z) {
        super(context);
        this.mIsRestoreAll = z;
        this.mIsMHS = NetworkUtils.isMobileHotspotConnected(context);
        this.mLogger = new TrashRestoreLogger(context);
    }

    private void copyCloudThumbFromTrash(TrashRestoreItem trashRestoreItem) {
        String str;
        if (isCloudOnly(trashRestoreItem.getStorageType())) {
            str = trashRestoreItem.getPath();
        } else {
            str = trashRestoreItem.getLCThumbPath();
        }
        String cloudThumbPath = trashRestoreItem.getCloudThumbPath();
        String cloudServerId = trashRestoreItem.getCloudServerId();
        if (!isCopyOrDeleteCloudThumbnail(trashRestoreItem)) {
            Log.d(this.TAG, "skip cloud thumbnail handling");
            return;
        }
        boolean isEmpty = TextUtils.isEmpty(cloudThumbPath);
        boolean isEmpty2 = TextUtils.isEmpty(cloudServerId);
        boolean exists = FileUtils.exists(str);
        if (!exists || (isEmpty && isEmpty2)) {
            Log.w(this.TAG, "unable to copy cloud thumbnail to original path [" + cloudServerId + "][" + exists + "][false][" + Logger.getEncodedString(str) + "][" + Logger.getEncodedString(cloudThumbPath) + "]");
            return;
        }
        boolean z = false;
        if (isEmpty) {
            try {
                cloudThumbPath = this.mTrashFactory.getCloudThumbRootPath() + cloudServerId + ".jpg";
            } catch (Throwable th) {
                if (0 != 0) {
                    Log.w(this.TAG, "unable to copy cloud thumbnail to original path [" + cloudServerId + "][" + exists + "][false][" + Logger.getEncodedString(str) + "][" + Logger.getEncodedString(cloudThumbPath) + "]");
                } else {
                    FileAuth.setWritable(cloudThumbPath);
                }
                throw th;
            }
        }
        if (copyFile(str, cloudThumbPath) == null) {
            z = true;
        }
        boolean exists2 = FileUtils.exists(cloudThumbPath);
        if (z) {
            Log.w(this.TAG, "unable to copy cloud thumbnail to original path [" + cloudServerId + "][" + exists + "][" + exists2 + "][" + Logger.getEncodedString(str) + "][" + Logger.getEncodedString(cloudThumbPath) + "]");
            return;
        }
        FileAuth.setWritable(cloudThumbPath);
    }

    private void mediateStorageType(TrashRestoreItem trashRestoreItem, StorageType storageType) {
        if (storageType == null) {
            if (!TextUtils.isEmpty(trashRestoreItem.getCloudServerId()) || !TextUtils.isEmpty(trashRestoreItem.getCloudServerPath())) {
                String originalPath = TrashData.getOriginalPath(trashRestoreItem);
                if (TextUtils.isEmpty(originalPath) || isSecureDataPath(originalPath)) {
                    trashRestoreItem.setStorageType(StorageType.Cloud);
                } else {
                    trashRestoreItem.setStorageType(StorageType.LocalCloud);
                }
            } else {
                trashRestoreItem.setStorageType(StorageType.Local);
            }
            String str = this.TAG;
            Log.w(str, "storage type is null, so mediate it [" + getDump(trashRestoreItem) + "]");
        }
    }

    private void moveCloudThumbFromTrash(TrashRestoreItem trashRestoreItem) {
        String str;
        String str2;
        String str3;
        if (isCloudOnly(trashRestoreItem.getStorageType())) {
            str = trashRestoreItem.getPath();
        } else {
            str = trashRestoreItem.getLCThumbPath();
        }
        String cloudThumbPath = trashRestoreItem.getCloudThumbPath();
        String cloudServerId = trashRestoreItem.getCloudServerId();
        boolean isEmpty = TextUtils.isEmpty(cloudThumbPath);
        boolean isEmpty2 = TextUtils.isEmpty(cloudServerId);
        boolean exists = FileUtils.exists(str);
        if (!exists || (isEmpty && isEmpty2)) {
            str2 = this.TAG;
            str3 = "unable to copy cloud thumbnail to original path [" + cloudServerId + "][" + exists + "][false][" + Logger.getEncodedString(str) + "][" + Logger.getEncodedString(cloudThumbPath) + "]";
        } else {
            boolean z = false;
            if (isEmpty) {
                try {
                    cloudThumbPath = this.mTrashFactory.getCloudThumbRootPath() + cloudServerId + ".jpg";
                } catch (Throwable th) {
                    if (0 != 0) {
                        Log.w(this.TAG, "unable to copy cloud thumbnail to original path [" + cloudServerId + "][" + exists + "][false][" + Logger.getEncodedString(str) + "][" + Logger.getEncodedString(cloudThumbPath) + "]");
                    }
                    throw th;
                }
            }
            if (moveFile(trashRestoreItem, str, cloudThumbPath, false) == null) {
                z = true;
            }
            boolean exists2 = FileUtils.exists(cloudThumbPath);
            if (z) {
                str2 = this.TAG;
                str3 = "unable to copy cloud thumbnail to original path [" + cloudServerId + "][" + exists + "][" + exists2 + "][" + Logger.getEncodedString(str) + "][" + Logger.getEncodedString(cloudThumbPath) + "]";
            } else {
                return;
            }
        }
        Log.w(str2, str3);
    }

    private void removeMetaData(TrashRestoreItem trashRestoreItem) {
        SefInfo sefInfo;
        if (SUPPORT_CHANGE_BEST_ITEM && trashRestoreItem.getBestImage() == 1) {
            try {
                if (trashRestoreItem.getGroupType() == 1) {
                    sefInfo = SefInfo.BURST_SHOT_BEST_PHOTO_INFO;
                } else {
                    sefInfo = SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO;
                }
                SeApiCompat.getSefFileCompat().deleteData((File) new AtomicReference(new File(trashRestoreItem.getNewPath())).get(), sefInfo.keyName);
            } catch (Exception e) {
                String str = this.TAG;
                Log.e(str, "remove meta data failed e=" + e.getMessage() + ArcCommonLog.TAG_COMMA + trashRestoreItem);
            }
        }
    }

    private Media restoreCloud(TrashRestoreItem trashRestoreItem) {
        return SamsungCloudCompat.restore(AppResources.getAppContext(), getMedia(trashRestoreItem));
    }

    private void restoreItemInternal(TrashRestoreItem trashRestoreItem) {
        increaseProgress();
        MediaType mediaType = trashRestoreItem.getMediaType();
        StorageType storageType = trashRestoreItem.getStorageType();
        String path = trashRestoreItem.getPath();
        mediateStorageType(trashRestoreItem, storageType);
        this.mLogger.started(mediaType, storageType);
        if (isNormalTrash(trashRestoreItem)) {
            if (step1stMoveFile(trashRestoreItem) || !isLocal(storageType)) {
                boolean step2ndRestoreCloud = step2ndRestoreCloud(trashRestoreItem);
                StorageType storageType2 = trashRestoreItem.getStorageType();
                if (step2ndRestoreCloud || isLocal(storageType2)) {
                    if (isCopyOrDeleteCloudThumbnail(trashRestoreItem)) {
                        if (this.mTrashFactory.useCloudThumbMove()) {
                            moveCloudThumbFromTrash(trashRestoreItem);
                        } else {
                            if (isLocal(storageType2)) {
                                path = trashRestoreItem.getLCThumbPath();
                            }
                            deleteCloudThumbnail(path);
                        }
                    }
                } else if (isCloudOnly(storageType2)) {
                    this.mLogger.failed(mediaType, storageType2);
                    return;
                }
                step3rdInsertRecord(trashRestoreItem);
                storageType = storageType2;
            } else {
                this.mLogger.failed(mediaType, storageType);
                return;
            }
        }
        step4thUpdateRecord(trashRestoreItem);
        step5thDeleteTrash(trashRestoreItem);
        this.mLogger.succeed(mediaType, storageType);
        removeDiskCache(trashRestoreItem);
    }

    private boolean step1stInternal(TrashRestoreItem trashRestoreItem, String str) {
        StorageType storageType = trashRestoreItem.getStorageType();
        if (TextUtils.isEmpty(str)) {
            str = TrashData.getOriginalPath(trashRestoreItem);
        }
        trashRestoreItem.setNewPath(str);
        int bulkInsert = this.mTrashFactory.bulkInsert(trashRestoreItem, false);
        this.mLogger.inserted(bulkInsert);
        String str2 = this.TAG;
        Log.d(str2, "insert Database [" + storageType + "] [" + Logger.getEncodedString(str) + "]");
        if (bulkInsert > 0) {
            return true;
        }
        return false;
    }

    private boolean step1stMoveFile(TrashRestoreItem trashRestoreItem) {
        StorageType storageType = trashRestoreItem.getStorageType();
        String path = trashRestoreItem.getPath();
        String restoreTargetPath = getRestoreTargetPath(trashRestoreItem);
        if (this.mTrashFactory.useDBInsertFirst(trashRestoreItem)) {
            Log.d(this.TAG, "useDBInsertFirst: true");
            if (!step1stInternal(trashRestoreItem, restoreTargetPath)) {
                Log.w(this.TAG, "unable to insert database");
                return false;
            }
        }
        if (!this.mTrashFactory.useCloudThumbMove() && isCloud(storageType)) {
            copyCloudThumbFromTrash(trashRestoreItem);
        }
        if (isLocal(storageType)) {
            String moveFile = moveFile(trashRestoreItem, path, restoreTargetPath, true);
            if (moveFile != null) {
                trashRestoreItem.setNewPath(moveFile);
                removeMetaData(trashRestoreItem);
                return true;
            }
            boolean exists = FileUtils.exists(path);
            String encodedString = Logger.getEncodedString(path);
            String encodedString2 = Logger.getEncodedString(restoreTargetPath);
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("unable to move file to original area [");
            sb2.append(storageType);
            sb2.append("][");
            sb2.append(exists);
            sb2.append("][");
            Log.w(str, C0212a.q(sb2, encodedString, "][", encodedString2, "]"));
            this.mLogger.restoreMoveFail(storageType, encodedString, encodedString2, trashRestoreItem.getMoveError(), exists);
            if (!exists && isCloud(storageType)) {
                trashRestoreItem.setStorageType(StorageType.Cloud);
            }
        }
        return false;
    }

    private boolean step2ndRestoreCloud(TrashRestoreItem trashRestoreItem) {
        CloudErrorType errorCode;
        StorageType storageType = trashRestoreItem.getStorageType();
        if (!isCloud(storageType)) {
            return false;
        }
        String cloudServerId = trashRestoreItem.getCloudServerId();
        if (isCloudOnly(storageType) && TextUtils.isEmpty(cloudServerId)) {
            Log.w(this.TAG, "cloud only but null cloud server id, so just recover data.");
            return true;
        } else if (this.mTrashFactory.deleteCloudRecordDeleteTable(cloudServerId)) {
            return true;
        } else {
            if (this.mIsMHS) {
                errorCode = CloudErrorType.MHS;
            } else {
                Media restoreCloud = restoreCloud(trashRestoreItem);
                if (restoreCloud == null || restoreCloud.rcode != null) {
                    errorCode = getErrorCode(restoreCloud);
                    if (isLocal(storageType)) {
                        trashRestoreItem.setStorageType(StorageType.Local);
                    }
                } else {
                    trashRestoreItem.setMediaResult(restoreCloud);
                    return true;
                }
            }
            CloudErrorType cloudErrorType = errorCode;
            if (this.mErrorType == CloudErrorType.None) {
                this.mErrorType = cloudErrorType;
            }
            String encodedString = Logger.getEncodedString(TrashData.getOriginalPath(trashRestoreItem));
            String encodedString2 = Logger.getEncodedString(trashRestoreItem.getNewPath());
            String encodedString3 = Logger.getEncodedString(trashRestoreItem.getCloudServerPath());
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("unable to restore cloud [");
            sb2.append(storageType);
            sb2.append("][");
            sb2.append(cloudErrorType);
            sb2.append("][");
            C0086a.z(sb2, cloudServerId, "][", encodedString, "][");
            Log.w(str, C0212a.q(sb2, encodedString2, "][", encodedString3, "]"));
            this.mLogger.restoreCloudFail(storageType, cloudErrorType, cloudServerId, encodedString, encodedString3);
            return false;
        }
    }

    private void step3rdInsertRecord(TrashRestoreItem trashRestoreItem) {
        String newPath = trashRestoreItem.getNewPath();
        if (this.mTrashFactory.useMediaScanForRestore() && isLocal(trashRestoreItem.getStorageType())) {
            if (!Features.isEnabled(Features.SUPPORT_PPP_MENU) || trashRestoreItem.getSefFileType() != 2928) {
                addScanPath(trashRestoreItem.getPath());
                addScanPath(newPath);
                bulkScan(false);
            } else {
                String str = this.TAG;
                Log.d(str, "ppp restore skip scan : " + trashRestoreItem.getPath() + ArcCommonLog.TAG_COMMA + newPath);
            }
        }
        if (!TextUtils.isEmpty(newPath)) {
            this.mLogger.restorePath(newPath);
        }
        if (this.mTrashFactory.useDBInsertAfter(trashRestoreItem)) {
            this.mLogger.inserted(this.mTrashFactory.bulkInsert(trashRestoreItem, false));
        }
    }

    private void step4thUpdateRecord(TrashRestoreItem trashRestoreItem) {
        if (!updateMpRestoreItem(trashRestoreItem)) {
            String str = this.TAG;
            Log.e(str, "update mp restore failed, path = " + Logger.getEncodedString(trashRestoreItem.getDataPath()));
        }
    }

    private void step5thDeleteTrash(TrashRestoreItem trashRestoreItem) {
        String pathWithCheckDataMatch = getPathWithCheckDataMatch(trashRestoreItem);
        if (!deleteFromTrashDB(pathWithCheckDataMatch)) {
            String str = this.TAG;
            Log.w(str, "delete trash failed [" + Logger.getEncodedString(pathWithCheckDataMatch) + "]");
            return;
        }
        this.mTrashFactory.updateNoneDestructionDB(trashRestoreItem.getOriginFileHash(), NoneDestructionOperationType.RESTORE, false);
    }

    public void done() {
        String str = this.TAG;
        Log.d(str, "restore done [" + this.mIsRestoreAll + "][" + this.mInterrupted + "][" + this.mProgressListener + "]");
        bulkScan(true);
        this.mTrashFactory.updateNoneDestructionDB("", NoneDestructionOperationType.RESTORE, true);
        this.mLogger.inserted(this.mTrashFactory.bulkInsert((TrashRestoreItem) null, true));
        super.done();
    }

    public CloudErrorType getErrorType() {
        return this.mErrorType;
    }

    public int getImageFailedCount() {
        return this.mLogger.getImageFailedCount();
    }

    public int getImageSucceedCount() {
        return this.mLogger.getImageSucceedCount();
    }

    public String getRestoreTargetPath(TrashRestoreItem trashRestoreItem) {
        return TrashData.getOriginalPath(trashRestoreItem);
    }

    public int getVideoFailedCount() {
        return this.mLogger.getVideoFailedCount();
    }

    public int getVideoSucceedCount() {
        return this.mLogger.getVideoSucceedCount();
    }

    public boolean isNormalTrash(TrashRestoreItem trashRestoreItem) {
        return true;
    }

    public void restoreItem(FileItemInterface fileItemInterface) {
        restoreItemInternal(new TrashRestoreItem(fileItemInterface));
    }

    public String tag() {
        return "TrashRestoreHelper";
    }

    public boolean updateMpRestoreItem(TrashRestoreItem trashRestoreItem) {
        return true;
    }

    public TrashRestoreLogger getLogger() {
        return this.mLogger;
    }
}
