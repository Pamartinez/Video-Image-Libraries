package com.samsung.android.gallery.module.trash.helper;

import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.trash.abstraction.NoneDestructionOperationType;
import com.samsung.android.gallery.module.trash.abstraction.TrashEmptyItem;
import com.samsung.android.gallery.module.trash.support.TrashEmptyLogger;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.scsp.media.Media;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashEmptyHelper extends TrashHelper {
    private final boolean mIsCloudEnabled = CloudStateCompat.isEnabled();
    private final boolean mIsEmptyAll;
    final TrashEmptyLogger mLogger;

    public TrashEmptyHelper(Context context, boolean z) {
        super(context);
        this.mIsEmptyAll = z;
        this.mLogger = new TrashEmptyLogger(context, z);
    }

    private void deleteCloudThumbnail(TrashEmptyItem trashEmptyItem) {
        String lCThumbPath = trashEmptyItem.getLCThumbPath();
        if (!TextUtils.isEmpty(lCThumbPath)) {
            deleteFile(lCThumbPath);
        }
    }

    private void deleteFile(String str) {
        boolean z;
        Exception e;
        boolean z3 = false;
        try {
            SecureFile secureFile = new SecureFile(str);
            z = secureFile.exists();
            if (z) {
                try {
                    z3 = secureFile.delete();
                } catch (Exception e7) {
                    e = e7;
                    try {
                        e.printStackTrace();
                        String message = e.getMessage();
                        String encodedString = Logger.getEncodedString(str);
                        Log.w(this.TAG, "file is not delete [" + z + "][" + encodedString + "]");
                        this.mLogger.emptyDeleteFail(z, encodedString, message);
                    } catch (Throwable th) {
                        th = th;
                        String encodedString2 = Logger.getEncodedString(str);
                        Log.w(this.TAG, "file is not delete [" + z + "][" + encodedString2 + "]");
                        this.mLogger.emptyDeleteFail(z, encodedString2, (String) null);
                        throw th;
                    }
                }
            }
            if (!z3) {
                String encodedString3 = Logger.getEncodedString(str);
                Log.w(this.TAG, "file is not delete [" + z + "][" + encodedString3 + "]");
                this.mLogger.emptyDeleteFail(z, encodedString3, (String) null);
            }
        } catch (Exception e8) {
            z = false;
            e = e8;
            e.printStackTrace();
            String message2 = e.getMessage();
            String encodedString4 = Logger.getEncodedString(str);
            Log.w(this.TAG, "file is not delete [" + z + "][" + encodedString4 + "]");
            this.mLogger.emptyDeleteFail(z, encodedString4, message2);
        } catch (Throwable th2) {
            z = false;
            th = th2;
            String encodedString22 = Logger.getEncodedString(str);
            Log.w(this.TAG, "file is not delete [" + z + "][" + encodedString22 + "]");
            this.mLogger.emptyDeleteFail(z, encodedString22, (String) null);
            throw th;
        }
    }

    private Media emptyCloud(Context context, TrashEmptyItem trashEmptyItem) {
        return SamsungCloudCompat.empty(context, getMedia(trashEmptyItem));
    }

    private void emptyItemInternal(TrashEmptyItem trashEmptyItem) {
        boolean deleteFromTrashDB;
        increaseProgress();
        MediaType mediaType = trashEmptyItem.getMediaType();
        StorageType storageType = trashEmptyItem.getStorageType();
        this.mLogger.started(mediaType, storageType);
        String path = trashEmptyItem.getPath();
        deleteFile(path);
        try {
            if (isLocalOnly(storageType)) {
                if (deleteFromTrashDB(getPathWithCheckDataMatch(trashEmptyItem))) {
                    if (isLocal(storageType)) {
                        addScanPath(path);
                        bulkScan(false);
                    }
                    this.mTrashFactory.updateNoneDestructionDB(trashEmptyItem.getOriginFileHash(), NoneDestructionOperationType.EMPTY, false);
                    this.mLogger.succeed(mediaType, storageType);
                } else {
                    String str = this.TAG;
                    Log.w(str, "empty trash failed [" + Logger.getEncodedString(path) + "]");
                    this.mLogger.failed(mediaType, storageType);
                }
                removeDiskCache(trashEmptyItem);
                return;
            }
            if (isLocalCloud(storageType) && isCopyOrDeleteCloudThumbnail(trashEmptyItem)) {
                deleteCloudThumbnail(trashEmptyItem);
            }
            if (!this.mIsCloudEnabled) {
                if (!TextUtils.isEmpty(trashEmptyItem.getCloudServerId())) {
                    Media emptyCloud = emptyCloud(AppResources.getAppContext(), trashEmptyItem);
                    if (emptyCloud == null || emptyCloud.rcode != null) {
                        handleEmptyCloudFail(emptyCloud, trashEmptyItem);
                    }
                    if (deleteFromTrashDB(getPathWithCheckDataMatch(trashEmptyItem))) {
                        if (isLocal(storageType)) {
                            addScanPath(path);
                            bulkScan(false);
                        }
                        this.mTrashFactory.updateNoneDestructionDB(trashEmptyItem.getOriginFileHash(), NoneDestructionOperationType.EMPTY, false);
                        this.mLogger.succeed(mediaType, storageType);
                    } else {
                        String str2 = this.TAG;
                        Log.w(str2, "empty trash failed [" + Logger.getEncodedString(path) + "]");
                        this.mLogger.failed(mediaType, storageType);
                    }
                    removeDiskCache(trashEmptyItem);
                    return;
                }
            }
            if (!deleteFromTrashDB) {
                String str3 = this.TAG;
                Log.w(str3, "empty trash failed [" + Logger.getEncodedString(path) + "]");
                this.mLogger.failed(mediaType, storageType);
            }
        } finally {
            if (deleteFromTrashDB(getPathWithCheckDataMatch(trashEmptyItem))) {
                if (isLocal(storageType)) {
                    addScanPath(path);
                    bulkScan(false);
                }
                this.mTrashFactory.updateNoneDestructionDB(trashEmptyItem.getOriginFileHash(), NoneDestructionOperationType.EMPTY, false);
                this.mLogger.succeed(mediaType, storageType);
            } else {
                String str4 = this.TAG;
                Log.w(str4, "empty trash failed [" + Logger.getEncodedString(path) + "]");
                this.mLogger.failed(mediaType, storageType);
            }
            removeDiskCache(trashEmptyItem);
        }
    }

    private void handleEmptyCloudFail(Media media, TrashEmptyItem trashEmptyItem) {
        CloudErrorType errorCode = getErrorCode(media);
        this.mLogger.emptyCloudFail(trashEmptyItem, media, errorCode);
        String str = this.TAG;
        Log.w(str, "unable to empty cloud [" + getDump(trashEmptyItem) + "][" + errorCode + "]");
    }

    public void done() {
        String str = this.TAG;
        Log.d(str, "empty done [" + this.mIsEmptyAll + "][" + this.mInterrupted + "][" + this.mProgressListener + "]");
        bulkScan(true);
        super.done();
        this.mTrashFactory.updateNoneDestructionDB("", NoneDestructionOperationType.EMPTY, true);
    }

    public void emptyItem(FileItemInterface fileItemInterface) {
        emptyItemInternal(new TrashEmptyItem(fileItemInterface));
    }

    public int getGDPRImageFailedCount() {
        return this.mLogger.getGDPRImageCount();
    }

    public int getGDPRVideoFailedCount() {
        return this.mLogger.getGDPRVideoCount();
    }

    public int getImageSucceedCount() {
        return this.mLogger.getImageSucceedCount();
    }

    public int getVideoSucceedCount() {
        return this.mLogger.getVideoSucceedCount();
    }

    public boolean isGDPRErrorHappened() {
        return this.mLogger.isGDPRErrorHappened();
    }

    public String tag() {
        return "TrashEmptyHelper";
    }

    public TrashEmptyLogger getLogger() {
        return this.mLogger;
    }
}
