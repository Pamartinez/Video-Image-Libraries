package com.samsung.android.gallery.module.trash.helper;

import A.a;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompatGroup;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemRetryLoader;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.tag.UserTagManager;
import com.samsung.android.gallery.module.trash.abstraction.NoneDestructionOperationType;
import com.samsung.android.gallery.module.trash.abstraction.PostProcessingDeleteException;
import com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem;
import com.samsung.android.gallery.module.trash.support.TrashDeleteLogger;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.cache.Crc;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashDeleteHelper extends TrashHelper {
    private String mCurrentTitle;
    private long mDeleteTime;
    private boolean mFailCollect;
    private ArrayList<TrashDeleteItem> mFailData;
    protected final TrashDeleteLogger mLogger;
    private final int mNewDBVersion;
    private boolean mQuickDelete;
    private final boolean mSupportTrash;
    private final boolean mUseStoreApi;

    public TrashDeleteHelper(Context context) {
        super(context);
        this.mDeleteTime = 0;
        this.mCurrentTitle = null;
        this.mFailCollect = false;
        this.mSupportTrash = this.mTrashFactory.supportTrash();
        this.mUseStoreApi = this.mTrashFactory.useStoreApi();
        this.mLogger = new TrashDeleteLogger();
        this.mNewDBVersion = LocalDatabaseHelper.getInstance(context).getDatabaseVersion();
    }

    private void Step2And3(TrashDeleteItem trashDeleteItem, boolean z, String str) {
        if (step2ndDeleteDB(trashDeleteItem, str)) {
            if (Features.isEnabled(Features.SUPPORT_PPP_MENU) && trashDeleteItem.getSefFileType() == 2928) {
                trashDeleteItem.updatePpp();
            }
            step3rdInsertTrash(trashDeleteItem, str, z);
        }
    }

    private void addFailedData(TrashDeleteItem trashDeleteItem) {
        if (this.mFailCollect) {
            if (this.mFailData == null) {
                this.mFailData = new ArrayList<>();
            }
            this.mFailData.add(trashDeleteItem);
        }
    }

    private boolean checkValid(TrashDeleteItem trashDeleteItem) {
        StorageType storageType = trashDeleteItem.getStorageType();
        if (storageType == null || storageType.toInt() == 0 || storageType.toInt() > 3) {
            String str = this.TAG;
            Log.w(str, "invalid type [" + storageType + "]");
            return false;
        }
        String path = trashDeleteItem.getPath();
        String volumeName = getVolumeName(storageType, path);
        if (volumeName != null || !this.mSupportTrash) {
            trashDeleteItem.setVolumeName(volumeName);
            return true;
        }
        String str2 = this.TAG;
        Log.w(str2, "invalid volume [" + storageType + "][" + FileUtils.getMountedExternalNames() + "][" + Logger.getEncodedString(path) + "][" + FileUtils.getSdcardDir() + "][" + FileUtils.getSdcardRwDir() + "]");
        return false;
    }

    private void clearFailedData() {
        ArrayList<TrashDeleteItem> arrayList = this.mFailData;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0063 A[SYNTHETIC, Splitter:B:20:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006a A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void deleteGroupItem(com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem r8) {
        /*
            r7 = this;
            java.lang.String r0 = "can not query "
            com.samsung.android.gallery.database.dbtype.GroupType r2 = r7.getGroupType((com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem) r8)     // Catch:{ Exception -> 0x0077 }
            int r3 = r8.getBucketID()     // Catch:{ Exception -> 0x0077 }
            long r4 = r8.getGroupMediaId()     // Catch:{ Exception -> 0x0077 }
            boolean r6 = r7.mQuickDelete     // Catch:{ Exception -> 0x0077 }
            r1 = r7
            android.database.Cursor r7 = r1.getGroupCursor(r2, r3, r4, r6)     // Catch:{ Exception -> 0x0067 }
            if (r7 == 0) goto L_0x002f
            boolean r2 = r7.moveToFirst()     // Catch:{ all -> 0x002c }
            if (r2 == 0) goto L_0x002f
        L_0x001d:
            boolean r0 = r1.mInterrupted     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0022
            goto L_0x0061
        L_0x0022:
            r1.deleteItemFromCursor(r7, r8)     // Catch:{ all -> 0x002c }
            boolean r0 = r7.moveToNext()     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x001d
            goto L_0x0061
        L_0x002c:
            r0 = move-exception
            r8 = r0
            goto L_0x006b
        L_0x002f:
            java.lang.String r2 = r1.TAG     // Catch:{ all -> 0x002c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x002c }
            r3.<init>(r0)     // Catch:{ all -> 0x002c }
            int r0 = r8.getGroupType()     // Catch:{ all -> 0x002c }
            r3.append(r0)     // Catch:{ all -> 0x002c }
            java.lang.String r0 = " with ["
            r3.append(r0)     // Catch:{ all -> 0x002c }
            int r0 = r8.getBucketID()     // Catch:{ all -> 0x002c }
            r3.append(r0)     // Catch:{ all -> 0x002c }
            java.lang.String r0 = "]["
            r3.append(r0)     // Catch:{ all -> 0x002c }
            long r4 = r8.getGroupMediaId()     // Catch:{ all -> 0x002c }
            r3.append(r4)     // Catch:{ all -> 0x002c }
            java.lang.String r8 = "]"
            r3.append(r8)     // Catch:{ all -> 0x002c }
            java.lang.String r8 = r3.toString()     // Catch:{ all -> 0x002c }
            com.samsung.android.gallery.support.utils.Log.w(r2, r8)     // Catch:{ all -> 0x002c }
        L_0x0061:
            if (r7 == 0) goto L_0x006a
            r7.close()     // Catch:{ Exception -> 0x0067 }
            return
        L_0x0067:
            r0 = move-exception
        L_0x0068:
            r7 = r0
            goto L_0x007a
        L_0x006a:
            return
        L_0x006b:
            if (r7 == 0) goto L_0x0076
            r7.close()     // Catch:{ all -> 0x0071 }
            goto L_0x0076
        L_0x0071:
            r0 = move-exception
            r7 = r0
            r8.addSuppressed(r7)     // Catch:{ Exception -> 0x0067 }
        L_0x0076:
            throw r8     // Catch:{ Exception -> 0x0067 }
        L_0x0077:
            r0 = move-exception
            r1 = r7
            goto L_0x0068
        L_0x007a:
            r1.printError(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper.deleteGroupItem(com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem):void");
    }

    private void deleteItemFromCursor(Cursor cursor, TrashDeleteItem trashDeleteItem) {
        TrashDeleteItem trashDeleteItem2 = new TrashDeleteItem(cursor, TrashData.of(trashDeleteItem).deleteTime);
        trashDeleteItem2.setGroupType(trashDeleteItem.getGroupType());
        deleteItemInternal(trashDeleteItem2, true);
    }

    private void deleteItemInternal(TrashDeleteItem trashDeleteItem, boolean z) {
        increaseProgress();
        MediaType mediaType = trashDeleteItem.getMediaType();
        StorageType storageType = trashDeleteItem.getStorageType();
        if (checkValid(trashDeleteItem)) {
            this.mLogger.started(mediaType, storageType);
            this.mCurrentTitle = trashDeleteItem.getTitle();
            String step1stMoveFile = step1stMoveFile(trashDeleteItem);
            if (step1stMoveFile != null || !this.mSupportTrash) {
                try {
                    Step2And3(trashDeleteItem, z, step1stMoveFile);
                } catch (PostProcessingDeleteException unused) {
                    Log.w((CharSequence) this.TAG, "PostProcessingDeleteException ", Logger.getEncodedString((Object) trashDeleteItem));
                    MediaItem reloadCompletedMediaItem = reloadCompletedMediaItem(trashDeleteItem.getFileId());
                    if (reloadCompletedMediaItem != null) {
                        Log.w((CharSequence) this.TAG, "ppp retry delete after sync : ", reloadCompletedMediaItem);
                        Step2And3(new TrashDeleteItem((FileItemInterface) reloadCompletedMediaItem, getDeleteTime()), z, step1stMoveFile);
                    } else {
                        String str = this.TAG;
                        Log.e(str, "ppp delete fail " + trashDeleteItem.getFileId());
                        rollbackStep1(trashDeleteItem.getPath(), step1stMoveFile);
                        handleAbnormalCase(trashDeleteItem);
                        this.mLogger.failed(mediaType, storageType);
                    }
                }
                removeDiskCache(trashDeleteItem);
                return;
            }
            return;
        }
        handleAbnormalCase(trashDeleteItem);
        this.mLogger.failed(mediaType, storageType);
    }

    public static void deleteRelatedData(long j2) {
        UserTagManager.removeAllData(j2);
        PeopleDataManager.deletePeopleData(j2);
    }

    private int getBestImage(TrashDeleteItem trashDeleteItem) {
        if (trashDeleteItem.isSimilarShot()) {
            return 0;
        }
        return trashDeleteItem.getBestImage();
    }

    private String getCachePath(String str, long j2) {
        String str2;
        byte[] generateKey = CacheManager.generateKey(str, j2);
        if (generateKey.length > 0) {
            str2 = String.valueOf(Crc.getCrc64Long(generateKey));
        } else {
            str2 = String.valueOf((str + j2).hashCode());
        }
        StringBuilder s = C0212a.s(str2);
        s.append(getFileExtension(str));
        return s.toString();
    }

    private String getFileExtension(String str) {
        return FileUtils.getExtension(str, true);
    }

    private Cursor getGroupCursor(GroupType groupType, int i2, long j2, boolean z) {
        return DbCompatGroup.getGroupCursor(groupType, i2, j2, z);
    }

    private long getGroupMediaId(TrashDeleteItem trashDeleteItem) {
        if (trashDeleteItem.isSimilarShot()) {
            return 0;
        }
        return trashDeleteItem.getGroupMediaId();
    }

    private GroupType getGroupType(TrashDeleteItem trashDeleteItem) {
        if (trashDeleteItem.isSimilarShot()) {
            return GroupType.SIMILAR;
        }
        if (trashDeleteItem.isSingleTakenShot()) {
            return GroupType.SINGLE_TAKEN;
        }
        return GroupType.BURST;
    }

    private String getTargetCloudThumbName(TrashDeleteItem trashDeleteItem, String str) {
        String cloudServerId = trashDeleteItem.getCloudServerId();
        if (TextUtils.isEmpty(cloudServerId)) {
            return getCachePath(trashDeleteItem.getPath(), trashDeleteItem.getFileId());
        }
        StringBuilder s = C0212a.s(cloudServerId);
        s.append(getFileExtension(str));
        return s.toString();
    }

    private String getTargetTrashPath(TrashDeleteItem trashDeleteItem) {
        return getFinalTrashPath(trashDeleteItem, findTargetTrashDir(trashDeleteItem));
    }

    private String getVolumeName(StorageType storageType, String str) {
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU) && FileUtils.isPostProcessingFile(str)) {
            return FileUtils.EXTERNAL_STORAGE_DIR;
        }
        if (isCloudOnly(storageType)) {
            return FileUtils.EXTERNAL_STORAGE_DIR;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e(this.TAG, "getVolumeName failed. null path");
            return null;
        } else if (FileUtils.isInRemovableStorage(str)) {
            String sdcardVolume = FileUtils.getSdcardVolume(str);
            if (TextUtils.isEmpty(sdcardVolume)) {
                Log.e((CharSequence) this.TAG, "getVolumeName failed for sdcard", str);
            }
            return sdcardVolume;
        } else {
            for (String next : FileUtils.getStorageNameList()) {
                if (str.startsWith(next)) {
                    return next;
                }
            }
            return FileUtils.getStorageName(str);
        }
    }

    private void handleAbnormalCase(TrashDeleteItem trashDeleteItem) {
        String str = this.TAG;
        Log.w(str, "handle abnormal record [" + getDump(trashDeleteItem) + "]");
        String path = trashDeleteItem.getPath();
        String encodedString = Logger.getEncodedString(path);
        boolean exists = FileUtils.exists(path);
        if (TextUtils.isEmpty(path) || (isSecureDataPath(path) && !exists)) {
            String str2 = this.TAG;
            Log.w(str2, "delete abnormal record from db [" + this.mSupportTrash + "][" + encodedString + "][" + exists + "]");
            int deleteAbnormal = this.mTrashFactory.deleteAbnormal(trashDeleteItem);
            this.mLogger.deleteAbnormal(deleteAbnormal, encodedString, exists);
            if (deleteAbnormal <= 0) {
                addFailedData(trashDeleteItem);
            }
        }
    }

    private boolean handleDeleteCloudFail(TrashDeleteItem trashDeleteItem, StorageType storageType) {
        String str = this.TAG;
        Log.w(str, "unable to delete cloud to trash [" + getDump(trashDeleteItem) + "]");
        if (!isCloudOnly(storageType)) {
            return true;
        }
        this.mLogger.failed(trashDeleteItem.getMediaType(), storageType);
        addFailedData(trashDeleteItem);
        return false;
    }

    private void handleMoveFileFail(TrashDeleteItem trashDeleteItem) {
        String path = trashDeleteItem.getPath();
        String encodedString = Logger.getEncodedString(path);
        SecureFile secureFile = new SecureFile(path);
        String str = this.TAG;
        Log.w(str, "unable to move file to trash [" + getDump(trashDeleteItem) + "]");
        boolean exists = secureFile.exists();
        StorageType storageType = trashDeleteItem.getStorageType();
        if (!exists) {
            String str2 = this.TAG;
            Log.w(str2, "source file is not exist, so update not exist file record from db [" + encodedString + "]");
            if (!isSecureDataPath(path)) {
                int deleteAbnormal = this.mTrashFactory.deleteAbnormal(trashDeleteItem);
                this.mLogger.deleteAbnormal(deleteAbnormal, encodedString, false);
                if (deleteAbnormal <= 0) {
                    int handleSecMpRecordOnly = handleSecMpRecordOnly(trashDeleteItem);
                    this.mLogger.deleteSecAbnormal(handleSecMpRecordOnly, encodedString, false);
                    if (handleSecMpRecordOnly <= 0) {
                        addFailedData(trashDeleteItem);
                    }
                }
            } else {
                int handlePppFileNotFound = handlePppFileNotFound(trashDeleteItem);
                if (handlePppFileNotFound > 0) {
                    this.mLogger.deletePppAbnormal(handlePppFileNotFound, encodedString, false);
                }
            }
        } else {
            addFailedData(trashDeleteItem);
        }
        this.mLogger.failed(trashDeleteItem.getMediaType(), storageType);
        this.mLogger.deleteMoveFail(storageType, Logger.getEncodedString(path), trashDeleteItem.getMoveError(), exists);
    }

    private int handlePppFileNotFound(TrashDeleteItem trashDeleteItem) {
        if (trashDeleteItem.getSefFileType() != 2928 || !FileUtils.isPostProcessingFile(trashDeleteItem.getPath()) || FileUtils.exists(trashDeleteItem.getPath())) {
            return 0;
        }
        String str = this.TAG;
        Log.w(str, "it's ppp. but _data_draft path file not found : " + Logger.getEncodedString(trashDeleteItem.getPath()));
        if (!PocFeatures.SUPPORT_QUICK_DELETE) {
            return this.mTrashFactory.updateIsTrashed(String.valueOf(trashDeleteItem.getFileId()), true);
        }
        return 1;
    }

    private int handleSecMpRecordOnly(TrashDeleteItem trashDeleteItem) {
        if (trashDeleteItem.getSefFileType() == 2928 || FileUtils.isPostProcessingFile(trashDeleteItem.getPath())) {
            String str = this.TAG;
            Log.w(str, "skip handleSecMpRecordOnly: it's ppp [" + trashDeleteItem.getSefFileType() + "]");
            return 0;
        }
        int updateIsTrashed = this.mTrashFactory.updateIsTrashed(String.valueOf(trashDeleteItem.getFileId()), true);
        String str2 = this.TAG;
        StringBuilder o2 = C0086a.o(updateIsTrashed, "handleSecMpRecordOnly: [", "][");
        o2.append(trashDeleteItem.getFileId());
        o2.append("]");
        Log.d(str2, o2.toString());
        return updateIsTrashed;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$reloadCompletedMediaItem$4(MediaItem mediaItem) {
        boolean z;
        if (mediaItem.isPostProcessing() || mediaItem.getMediaId() == 0) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    private void loadGroupItemTrashed(FileItemInterface fileItemInterface, boolean z, ArrayList<Long> arrayList) {
        TrashDeleteHelper trashDeleteHelper;
        Cursor groupCursor;
        Throwable th;
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 0;
        try {
            trashDeleteHelper = this;
            try {
                groupCursor = trashDeleteHelper.getGroupCursor(getGroupType(fileItemInterface), fileItemInterface.getAlbumID(), fileItemInterface.getGroupMediaId(), !z);
                if (groupCursor != null) {
                    if (groupCursor.moveToFirst()) {
                        int columnIndex = groupCursor.getColumnIndex("__absID");
                        do {
                            arrayList.add(Long.valueOf(groupCursor.getLong(columnIndex)));
                            i2++;
                        } while (groupCursor.moveToNext());
                    }
                }
                if (groupCursor != null) {
                    groupCursor.close();
                }
            } catch (Exception unused) {
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (Exception unused2) {
            trashDeleteHelper = this;
        }
        String str = trashDeleteHelper.TAG;
        Log.i(str, "getGroupItemTrashed: " + Logger.vt(Integer.valueOf(i2), Long.valueOf(fileItemInterface.getFileId()), Long.valueOf(currentTimeMillis)));
        return;
        throw th;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String moveCloudThumbToTrash(com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem r9) {
        /*
            r8 = this;
            java.lang.String r0 = r9.getCloudServerId()
            java.lang.String r1 = r9.getCloudThumbPath()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0039
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x001f
            java.lang.String r2 = r8.TAG
            java.lang.String r5 = "can not find cloud thumbnail, thumb path and server id is null"
            com.samsung.android.gallery.support.utils.Log.w(r2, r5)
            r2 = r3
            goto L_0x003a
        L_0x001f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            com.samsung.android.gallery.module.trash.factory.AbsTrashFactory r2 = r8.mTrashFactory
            java.lang.String r2 = r2.getCloudThumbRootPath()
            r1.append(r2)
            r1.append(r0)
            java.lang.String r2 = ".jpg"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L_0x0039:
            r2 = r4
        L_0x003a:
            java.lang.String r5 = r8.getTargetCloudThumbName(r9, r1)
            com.samsung.android.gallery.support.utils.SecureFile r6 = new com.samsung.android.gallery.support.utils.SecureFile
            java.lang.String r7 = r8.getTargetCloudThumbTrashPath(r9)
            r6.<init>((java.lang.String) r7, (java.lang.String) r5)
            java.lang.String r5 = r6.getAbsolutePath()
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            if (r6 != 0) goto L_0x005e
            java.lang.String r2 = r8.moveFile(r9, r1, r5, r4)
            if (r2 != 0) goto L_0x0058
            goto L_0x0059
        L_0x0058:
            r3 = r4
        L_0x0059:
            boolean r4 = com.samsung.android.gallery.support.utils.FileUtils.exists(r1)
            r2 = r3
        L_0x005e:
            if (r2 == 0) goto L_0x0096
            java.lang.String r8 = r8.TAG
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r2 = "unable to move cloud thumbnail to trash ["
            r9.<init>(r2)
            r9.append(r0)
            java.lang.String r0 = "]["
            r9.append(r0)
            r9.append(r4)
            r9.append(r0)
            java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r1)
            r9.append(r1)
            r9.append(r0)
            java.lang.String r0 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r5)
            r9.append(r0)
            java.lang.String r0 = "]"
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            com.samsung.android.gallery.support.utils.Log.w(r8, r9)
            return r5
        L_0x0096:
            com.samsung.android.gallery.database.dbtype.StorageType r0 = r9.getStorageType()
            boolean r8 = r8.isLocalCloud(r0)
            if (r8 == 0) goto L_0x00a3
            r9.setLCThumbPath(r5)
        L_0x00a3:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper.moveCloudThumbToTrash(com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem):java.lang.String");
    }

    private String moveFileToTrash(TrashDeleteItem trashDeleteItem) {
        return moveFile(trashDeleteItem, trashDeleteItem.getPath(), new SecureFile(getTargetTrashPath(trashDeleteItem), getTargetLocalName(trashDeleteItem)).getAbsolutePath(), true);
    }

    public static int queryTrashedCount() {
        Cursor rawQuery;
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery("select count(*) from files where is_trashed=1 and media_type in (1,3)", "queryTrashedCount");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    int i2 = rawQuery.getInt(0);
                    rawQuery.close();
                    return i2;
                }
            }
            if (rawQuery == null) {
                return -1;
            }
            rawQuery.close();
            return -1;
        } catch (Exception e) {
            a.s(e, new StringBuilder("queryTrashedCount failed. e="), "TrashDeleteHelper");
            return -1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private MediaItem reloadCompletedMediaItem(long j2) {
        return new MediaItemRetryLoader(20, 50).setResultChecker(new q8.a(9)).getMediaItemFromFileIdSync(j2);
    }

    private void requestBulkDeletion(TrashDeleteItem trashDeleteItem) {
        this.mLogger.trashUpdated(this.mTrashFactory.bulkUpdate(trashDeleteItem, false));
        this.mLogger.deleted(this.mTrashFactory.bulkDelete(trashDeleteItem, false));
    }

    private void revertFailedData() {
        if (this.mFailCollect) {
            StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            ArrayList<TrashDeleteItem> arrayList = this.mFailData;
            if (arrayList != null && !arrayList.isEmpty()) {
                Iterator<TrashDeleteItem> it = this.mFailData.iterator();
                while (it.hasNext()) {
                    TrashDeleteItem next = it.next();
                    if (!next.isPostProcessing()) {
                        stringJoiner.add(Long.toString(next.getFileId()));
                    }
                }
                this.mLogger.revertTrashUpdated(this.mTrashFactory.updateIsTrashed(stringJoiner.toString(), false));
            }
        }
        clearFailedData();
    }

    private void rollbackStep1(String str, String str2) {
        String str3;
        Log.e((CharSequence) this.TAG, "rollbackStep1", Boolean.valueOf(FileUtils.exists(str2)));
        if (!FileUtils.isPostProcessingFile(str)) {
            boolean move = FileUtils.move(str2, str);
            String str4 = this.TAG;
            if (move) {
                str3 = "success";
            } else {
                str3 = "fail";
            }
            Log.w((CharSequence) str4, "rollbackStep1 ".concat(str3), str);
        } else if (!FileUtils.delete((File) new SecureFile(str2))) {
            a.u("rollbackStep1 : delete fail : ", str2, this.TAG);
        } else {
            String str5 = this.TAG;
            Log.w(str5, "rollbackStep1 : delete success : " + str2);
        }
    }

    private String step1stMoveFile(TrashDeleteItem trashDeleteItem) {
        String str;
        StorageType storageType = trashDeleteItem.getStorageType();
        if (!this.mSupportTrash) {
            return null;
        }
        if (!isCloud(storageType)) {
            str = null;
        } else if (this.mTrashFactory.useCloudThumbMove()) {
            str = moveCloudThumbToTrash(trashDeleteItem);
        } else {
            str = copyCloudThumbToTrash(trashDeleteItem);
        }
        if (!isLocal(storageType)) {
            return str;
        }
        String moveFileToTrash = moveFileToTrash(trashDeleteItem);
        if (moveFileToTrash != null) {
            return moveFileToTrash;
        }
        handleMoveFileFail(trashDeleteItem);
        return null;
    }

    private boolean step2ndDeleteDB(TrashDeleteItem trashDeleteItem, String str) {
        if (this.mTrashFactory.useMediaScanForMoveToTrash() && isLocal(trashDeleteItem.getStorageType())) {
            addScanPath(trashDeleteItem.getPath());
            addScanPath(str);
            bulkScan(false);
        }
        if (this.mUseStoreApi) {
            StorageType storageType = trashDeleteItem.getStorageType();
            if (isLocal(storageType)) {
                requestBulkDeletion(trashDeleteItem);
            }
            if (!isCloud(storageType) || this.mTrashFactory.deleteCloudFromRef(trashDeleteItem) || handleDeleteCloudFail(trashDeleteItem, storageType)) {
                return true;
            }
            return false;
        }
        requestBulkDeletion(trashDeleteItem);
        return true;
    }

    private void step3rdInsertTrash(TrashDeleteItem trashDeleteItem, String str, boolean z) {
        long j2;
        if (this.mSupportTrash) {
            if (!insertOrUpdateToTrash(trashDeleteItem, str, FileUtils.getNameFromPath(str))) {
                String str2 = this.TAG;
                Log.w(str2, "trash insertion failed [" + getDump(trashDeleteItem) + "]");
            } else {
                this.mTrashFactory.updateNoneDestructionDB(trashDeleteItem.getOriginFileHash(), NoneDestructionOperationType.MOVE_TO_TRASH, false);
            }
        }
        if (isCloudOnly(trashDeleteItem.getStorageType())) {
            j2 = trashDeleteItem.getFileId();
        } else {
            j2 = trashDeleteItem.getMediaId();
        }
        TrashDeleteLogger trashDeleteLogger = this.mLogger;
        trashDeleteLogger.deletePath(trashDeleteItem.getPath() + "(" + j2 + ")");
        this.mLogger.succeed(trashDeleteItem.getMediaType(), trashDeleteItem.getStorageType());
        if (z) {
            deleteRelatedData(trashDeleteItem.getFileId());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        com.samsung.android.gallery.support.utils.Log.w(r11.TAG, "can not query with [" + r12 + "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0084, code lost:
        if (r7 == null) goto L_0x0097;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int updateAlbumTrashed(java.util.ArrayList<java.lang.Integer> r12, java.util.ArrayList<java.lang.Integer> r13, boolean r14) {
        /*
            r11 = this;
            long r0 = java.lang.System.currentTimeMillis()
            com.samsung.android.gallery.support.utils.TimeTickLog r2 = new com.samsung.android.gallery.support.utils.TimeTickLog
            r2.<init>()
            java.util.stream.Stream r3 = r12.stream()
            A8.b r4 = new A8.b
            r5 = 25
            r4.<init>(r5)
            java.util.stream.IntStream r3 = r3.mapToInt(r4)
            int[] r3 = r3.toArray()
            int r4 = r3.length
            r5 = 0
            if (r4 <= 0) goto L_0x0096
            r4 = r5
            r6 = r4
        L_0x0022:
            java.lang.String r7 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES_ID     // Catch:{ Exception -> 0x002d }
            if (r14 == 0) goto L_0x002f
            ta.a r8 = new ta.a     // Catch:{ Exception -> 0x002d }
            r9 = 0
            r8.<init>(r3, r4, r9)     // Catch:{ Exception -> 0x002d }
            goto L_0x0035
        L_0x002d:
            r12 = move-exception
            goto L_0x0092
        L_0x002f:
            ta.a r8 = new ta.a     // Catch:{ Exception -> 0x002d }
            r9 = 1
            r8.<init>(r3, r4, r9)     // Catch:{ Exception -> 0x002d }
        L_0x0035:
            android.database.Cursor r7 = com.samsung.android.gallery.database.dal.DbCompat.query(r7, r8)     // Catch:{ Exception -> 0x002d }
            r2.tick()     // Catch:{ all -> 0x0067 }
            if (r7 == 0) goto L_0x0069
            boolean r8 = r7.moveToFirst()     // Catch:{ all -> 0x0067 }
            if (r8 == 0) goto L_0x0069
            java.lang.String r8 = r7.getString(r5)     // Catch:{ all -> 0x0067 }
            java.lang.String r9 = ","
            java.lang.String[] r9 = r8.split(r9)     // Catch:{ all -> 0x0067 }
            com.samsung.android.gallery.module.trash.factory.AbsTrashFactory r10 = r11.mTrashFactory     // Catch:{ all -> 0x0067 }
            int r8 = r10.updateIsTrashed(r8, r14)     // Catch:{ all -> 0x0067 }
            int r6 = r6 + r8
            r2.tick()     // Catch:{ all -> 0x0067 }
            int r8 = r9.length     // Catch:{ all -> 0x0067 }
            r9 = 25000(0x61a8, float:3.5032E-41)
            if (r8 >= r9) goto L_0x0061
        L_0x005d:
            r7.close()     // Catch:{ Exception -> 0x002d }
            goto L_0x0097
        L_0x0061:
            r7.close()     // Catch:{ Exception -> 0x002d }
            int r4 = r4 + 25000
            goto L_0x0022
        L_0x0067:
            r12 = move-exception
            goto L_0x0087
        L_0x0069:
            java.lang.String r2 = r11.TAG     // Catch:{ all -> 0x0067 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0067 }
            r3.<init>()     // Catch:{ all -> 0x0067 }
            java.lang.String r4 = "can not query with ["
            r3.append(r4)     // Catch:{ all -> 0x0067 }
            r3.append(r12)     // Catch:{ all -> 0x0067 }
            java.lang.String r12 = "]"
            r3.append(r12)     // Catch:{ all -> 0x0067 }
            java.lang.String r12 = r3.toString()     // Catch:{ all -> 0x0067 }
            com.samsung.android.gallery.support.utils.Log.w(r2, r12)     // Catch:{ all -> 0x0067 }
            if (r7 == 0) goto L_0x0097
            goto L_0x005d
        L_0x0087:
            if (r7 == 0) goto L_0x0091
            r7.close()     // Catch:{ all -> 0x008d }
            goto L_0x0091
        L_0x008d:
            r2 = move-exception
            r12.addSuppressed(r2)     // Catch:{ Exception -> 0x002d }
        L_0x0091:
            throw r12     // Catch:{ Exception -> 0x002d }
        L_0x0092:
            r11.printError(r12)
            goto L_0x0097
        L_0x0096:
            r6 = r5
        L_0x0097:
            boolean r12 = r13.isEmpty()
            if (r12 != 0) goto L_0x00a5
            com.samsung.android.gallery.module.album.AlbumHelper r12 = com.samsung.android.gallery.module.album.AlbumHelper.getInstance()
            int r5 = r12.deleteAlbumsForTrash(r13)
        L_0x00a5:
            java.lang.String r11 = r11.TAG
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "updateAlbumTrashed"
            r12.<init>(r13)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r6)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r14)
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.Object[] r13 = new java.lang.Object[]{r13, r2, r14, r0}
            java.lang.String r13 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r13)
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            com.samsung.android.gallery.support.utils.Log.d(r11, r12)
            int r6 = r6 + r5
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper.updateAlbumTrashed(java.util.ArrayList, java.util.ArrayList, boolean):int");
    }

    private int updateItemTrashed(ArrayList<MediaItem> arrayList, boolean z) {
        return updateItemTrashed(arrayList, z, true);
    }

    public void addToSucceedForEmptyAlbum() {
        this.mLogger.succeed(MediaType.Image, StorageType.Local);
        increaseProgress();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String copyCloudThumbToTrash(com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem r9) {
        /*
            r8 = this;
            java.lang.String r0 = r9.getCloudServerId()
            java.lang.String r1 = r9.getCloudThumbPath()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0039
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x001f
            java.lang.String r2 = r8.TAG
            java.lang.String r5 = "can not find cloud thumbnail, thumb path and server id is null"
            com.samsung.android.gallery.support.utils.Log.w(r2, r5)
            r2 = r3
            goto L_0x003a
        L_0x001f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            com.samsung.android.gallery.module.trash.factory.AbsTrashFactory r2 = r8.mTrashFactory
            java.lang.String r2 = r2.getCloudThumbRootPath()
            r1.append(r2)
            r1.append(r0)
            java.lang.String r2 = ".jpg"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L_0x0039:
            r2 = r4
        L_0x003a:
            java.lang.String r5 = r8.getTargetCloudThumbName(r9, r1)
            java.lang.String r6 = r8.getTargetCloudThumbTrashPath(r9)
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 == 0) goto L_0x004e
            boolean r4 = com.samsung.android.gallery.support.utils.FileUtils.exists(r1)
            r5 = r1
            goto L_0x006a
        L_0x004e:
            com.samsung.android.gallery.support.utils.SecureFile r7 = new com.samsung.android.gallery.support.utils.SecureFile
            r7.<init>((java.lang.String) r6, (java.lang.String) r5)
            java.lang.String r5 = r7.getAbsolutePath()
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            if (r6 != 0) goto L_0x006a
            java.lang.String r2 = r8.copyFile(r1, r5)
            if (r2 != 0) goto L_0x0064
            goto L_0x0065
        L_0x0064:
            r3 = r4
        L_0x0065:
            boolean r4 = com.samsung.android.gallery.support.utils.FileUtils.exists(r1)
            r2 = r3
        L_0x006a:
            if (r2 == 0) goto L_0x00a2
            java.lang.String r8 = r8.TAG
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r2 = "unable to copy cloud thumbnail to trash ["
            r9.<init>(r2)
            r9.append(r0)
            java.lang.String r0 = "]["
            r9.append(r0)
            r9.append(r4)
            r9.append(r0)
            java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r1)
            r9.append(r1)
            r9.append(r0)
            java.lang.String r0 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r5)
            r9.append(r0)
            java.lang.String r0 = "]"
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            com.samsung.android.gallery.support.utils.Log.w(r8, r9)
            return r5
        L_0x00a2:
            com.samsung.android.gallery.database.dbtype.StorageType r0 = r9.getStorageType()
            boolean r8 = r8.isLocalCloud(r0)
            if (r8 == 0) goto L_0x00af
            r9.setLCThumbPath(r5)
        L_0x00af:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper.copyCloudThumbToTrash(com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0092 A[SYNTHETIC, Splitter:B:24:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ce A[Catch:{ Exception -> 0x00de }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e0 A[EDGE_INSN: B:52:0x00e0->B:45:0x00e0 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deleteAlbum(int r12) {
        /*
            r11 = this;
            java.lang.String r0 = "]"
            java.lang.String r1 = "can not query with ["
            java.lang.String r2 = "album item preparing ["
            long r3 = java.lang.System.currentTimeMillis()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.lang.String r6 = r11.TAG
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "delete album ["
            r7.<init>(r8)
            r7.append(r12)
            java.lang.String r9 = "] start"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            com.samsung.android.gallery.support.utils.Log.d(r6, r7)
            java.lang.String r6 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES     // Catch:{ Exception -> 0x0035 }
            boolean r7 = r11.mQuickDelete     // Catch:{ Exception -> 0x0035 }
            if (r7 == 0) goto L_0x0037
            n4.a r7 = new n4.a     // Catch:{ Exception -> 0x0035 }
            r9 = 11
            r7.<init>(r12, r9)     // Catch:{ Exception -> 0x0035 }
            goto L_0x003e
        L_0x0035:
            r1 = move-exception
            goto L_0x00a1
        L_0x0037:
            n4.a r7 = new n4.a     // Catch:{ Exception -> 0x0035 }
            r9 = 12
            r7.<init>(r12, r9)     // Catch:{ Exception -> 0x0035 }
        L_0x003e:
            android.database.Cursor r6 = com.samsung.android.gallery.database.dal.DbCompat.query(r6, r7)     // Catch:{ Exception -> 0x0035 }
            if (r6 == 0) goto L_0x007c
            boolean r7 = r6.moveToFirst()     // Catch:{ all -> 0x007a }
            if (r7 == 0) goto L_0x007c
            java.lang.String r1 = r11.TAG     // Catch:{ all -> 0x007a }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x007a }
            r7.<init>(r2)     // Catch:{ all -> 0x007a }
            int r2 = r6.getCount()     // Catch:{ all -> 0x007a }
            r7.append(r2)     // Catch:{ all -> 0x007a }
            r7.append(r0)     // Catch:{ all -> 0x007a }
            java.lang.String r2 = r7.toString()     // Catch:{ all -> 0x007a }
            com.samsung.android.gallery.support.utils.Log.d(r1, r2)     // Catch:{ all -> 0x007a }
        L_0x0062:
            boolean r1 = r11.mInterrupted     // Catch:{ all -> 0x007a }
            if (r1 == 0) goto L_0x0067
            goto L_0x0090
        L_0x0067:
            com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem r1 = new com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem     // Catch:{ all -> 0x007a }
            long r9 = r11.getDeleteTime()     // Catch:{ all -> 0x007a }
            r1.<init>((android.database.Cursor) r6, (long) r9)     // Catch:{ all -> 0x007a }
            r5.add(r1)     // Catch:{ all -> 0x007a }
            boolean r1 = r6.moveToNext()     // Catch:{ all -> 0x007a }
            if (r1 != 0) goto L_0x0062
            goto L_0x0090
        L_0x007a:
            r1 = move-exception
            goto L_0x0096
        L_0x007c:
            java.lang.String r2 = r11.TAG     // Catch:{ all -> 0x007a }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x007a }
            r7.<init>(r1)     // Catch:{ all -> 0x007a }
            r7.append(r12)     // Catch:{ all -> 0x007a }
            r7.append(r0)     // Catch:{ all -> 0x007a }
            java.lang.String r1 = r7.toString()     // Catch:{ all -> 0x007a }
            com.samsung.android.gallery.support.utils.Log.w(r2, r1)     // Catch:{ all -> 0x007a }
        L_0x0090:
            if (r6 == 0) goto L_0x00a4
            r6.close()     // Catch:{ Exception -> 0x0035 }
            goto L_0x00a4
        L_0x0096:
            if (r6 == 0) goto L_0x00a0
            r6.close()     // Catch:{ all -> 0x009c }
            goto L_0x00a0
        L_0x009c:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch:{ Exception -> 0x0035 }
        L_0x00a0:
            throw r1     // Catch:{ Exception -> 0x0035 }
        L_0x00a1:
            r11.printError(r1)
        L_0x00a4:
            java.lang.String r1 = r11.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "album item prepared ["
            r2.<init>(r6)
            int r6 = r5.size()
            r2.append(r6)
            java.lang.String r6 = "]["
            r2.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r3
            r2.append(r6)
            N2.j.y(r2, r0, r1)
            java.util.Iterator r0 = r5.iterator()     // Catch:{ Exception -> 0x00de }
        L_0x00c8:
            boolean r1 = r0.hasNext()     // Catch:{ Exception -> 0x00de }
            if (r1 == 0) goto L_0x00e0
            java.lang.Object r1 = r0.next()     // Catch:{ Exception -> 0x00de }
            com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem r1 = (com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem) r1     // Catch:{ Exception -> 0x00de }
            boolean r2 = r11.mInterrupted     // Catch:{ Exception -> 0x00de }
            if (r2 == 0) goto L_0x00d9
            goto L_0x00e0
        L_0x00d9:
            r2 = 1
            r11.deleteItem(r1, r2)     // Catch:{ Exception -> 0x00de }
            goto L_0x00c8
        L_0x00de:
            r0 = move-exception
            goto L_0x00e8
        L_0x00e0:
            com.samsung.android.gallery.module.album.AlbumHelper r0 = com.samsung.android.gallery.module.album.AlbumHelper.getInstance()     // Catch:{ Exception -> 0x00de }
            r0.removeAlbumCover(r12)     // Catch:{ Exception -> 0x00de }
            goto L_0x00eb
        L_0x00e8:
            r11.printError(r0)
        L_0x00eb:
            java.lang.String r11 = r11.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r8)
            r0.append(r12)
            java.lang.String r12 = "] end"
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.d(r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper.deleteAlbum(int):void");
    }

    public void deleteItem(FileItemInterface fileItemInterface) {
        deleteItem(fileItemInterface, true);
    }

    public void deleteItemForUndo(FileItemInterface fileItemInterface) {
        if (PocFeatures.UNDO_DELETE) {
            deleteItemInternal(new TrashDeleteItem(fileItemInterface, getDeleteTime()), false);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a7 A[SYNTHETIC, Splitter:B:28:0x00a7] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e3 A[Catch:{ Exception -> 0x00ed }, LOOP:2: B:42:0x00dd->B:44:0x00e3, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deleteItems(long[] r14, boolean r15) {
        /*
            r13 = this;
            java.lang.String r0 = "]"
            java.lang.String r1 = "]["
            r2 = 1
            r3 = 0
            if (r14 == 0) goto L_0x010c
            int r4 = r14.length
            if (r4 != 0) goto L_0x000d
            goto L_0x010c
        L_0x000d:
            java.lang.String r4 = r13.TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "delete items ["
            r5.<init>(r6)
            java.lang.String r7 = java.util.Arrays.toString(r14)
            r5.append(r7)
            java.lang.String r7 = "] start"
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            com.samsung.android.gallery.support.utils.Log.d(r4, r5)
            long r4 = java.lang.System.currentTimeMillis()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.StringJoiner r8 = new java.util.StringJoiner
            java.lang.String r9 = ","
            r8.<init>(r9)
            int r9 = r14.length
            r10 = r3
        L_0x003b:
            if (r10 >= r9) goto L_0x0049
            r11 = r14[r10]
            java.lang.String r11 = java.lang.Long.toString(r11)
            r8.add(r11)
            int r10 = r10 + 1
            goto L_0x003b
        L_0x0049:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r9 = new com.samsung.android.gallery.database.dal.abstraction.query.QueryParams
            java.lang.String r10 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES
            r9.<init>((java.lang.String) r10)
            if (r15 == 0) goto L_0x005a
            java.lang.String r15 = r8.toString()
            r9.setFileIds(r15)
            goto L_0x0061
        L_0x005a:
            java.lang.String r15 = r8.toString()
            r9.setMediaIds(r15)
        L_0x0061:
            android.database.Cursor r15 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r9)     // Catch:{ Exception -> 0x00ab }
            if (r15 == 0) goto L_0x0082
            boolean r9 = r15.moveToFirst()     // Catch:{ all -> 0x0080 }
            if (r9 == 0) goto L_0x0082
        L_0x006d:
            com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem r2 = new com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem     // Catch:{ all -> 0x0080 }
            long r8 = r13.getDeleteTime()     // Catch:{ all -> 0x0080 }
            r2.<init>((android.database.Cursor) r15, (long) r8)     // Catch:{ all -> 0x0080 }
            r7.add(r2)     // Catch:{ all -> 0x0080 }
            boolean r2 = r15.moveToNext()     // Catch:{ all -> 0x0080 }
            if (r2 != 0) goto L_0x006d
            goto L_0x00a5
        L_0x0080:
            r2 = move-exception
            goto L_0x00ad
        L_0x0082:
            java.lang.String r9 = r13.TAG     // Catch:{ all -> 0x0080 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0080 }
            r10.<init>()     // Catch:{ all -> 0x0080 }
            java.lang.String r11 = "can not query with ids ["
            r10.append(r11)     // Catch:{ all -> 0x0080 }
            r10.append(r8)     // Catch:{ all -> 0x0080 }
            r10.append(r1)     // Catch:{ all -> 0x0080 }
            if (r15 != 0) goto L_0x0097
            goto L_0x0098
        L_0x0097:
            r2 = r3
        L_0x0098:
            r10.append(r2)     // Catch:{ all -> 0x0080 }
            r10.append(r0)     // Catch:{ all -> 0x0080 }
            java.lang.String r2 = r10.toString()     // Catch:{ all -> 0x0080 }
            com.samsung.android.gallery.support.utils.Log.w(r9, r2)     // Catch:{ all -> 0x0080 }
        L_0x00a5:
            if (r15 == 0) goto L_0x00bb
            r15.close()     // Catch:{ Exception -> 0x00ab }
            goto L_0x00bb
        L_0x00ab:
            r15 = move-exception
            goto L_0x00b8
        L_0x00ad:
            if (r15 == 0) goto L_0x00b7
            r15.close()     // Catch:{ all -> 0x00b3 }
            goto L_0x00b7
        L_0x00b3:
            r15 = move-exception
            r2.addSuppressed(r15)     // Catch:{ Exception -> 0x00ab }
        L_0x00b7:
            throw r2     // Catch:{ Exception -> 0x00ab }
        L_0x00b8:
            r13.printError(r15)
        L_0x00bb:
            java.lang.String r15 = r13.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r8 = "delete items prepared ["
            r2.<init>(r8)
            c0.C0086a.A(r2, r7, r1)
            int r8 = r14.length
            r2.append(r8)
            r2.append(r1)
            long r8 = java.lang.System.currentTimeMillis()
            long r8 = r8 - r4
            r2.append(r8)
            N2.j.y(r2, r0, r15)
            java.util.Iterator r15 = r7.iterator()     // Catch:{ Exception -> 0x00ed }
        L_0x00dd:
            boolean r0 = r15.hasNext()     // Catch:{ Exception -> 0x00ed }
            if (r0 == 0) goto L_0x00f1
            java.lang.Object r0 = r15.next()     // Catch:{ Exception -> 0x00ed }
            com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem r0 = (com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem) r0     // Catch:{ Exception -> 0x00ed }
            r13.deleteItem(r0, r3)     // Catch:{ Exception -> 0x00ed }
            goto L_0x00dd
        L_0x00ed:
            r15 = move-exception
            r13.printError(r15)
        L_0x00f1:
            java.lang.String r13 = r13.TAG
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>(r6)
            java.lang.String r14 = java.util.Arrays.toString(r14)
            r15.append(r14)
            java.lang.String r14 = "] end"
            r15.append(r14)
            java.lang.String r14 = r15.toString()
            com.samsung.android.gallery.support.utils.Log.d(r13, r14)
            return
        L_0x010c:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r0 = "delete items failed, invalid ids={null:"
            r15.<init>(r0)
            if (r14 != 0) goto L_0x0118
            goto L_0x0119
        L_0x0118:
            r2 = r3
        L_0x0119:
            java.lang.String r14 = "}"
            java.lang.String r14 = N2.j.h(r15, r2, r14)
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper.deleteItems(long[], boolean):void");
    }

    public void done() {
        String str = this.TAG;
        Log.d(str, "delete done [" + this.mSupportTrash + "][" + this.mUseStoreApi + "][" + this.mInterrupted + "]");
        bulkScan(true);
        this.mTrashFactory.updateNoneDestructionDB("", NoneDestructionOperationType.MOVE_TO_TRASH, true);
        this.mLogger.trashUpdated(this.mTrashFactory.bulkUpdate((TrashDeleteItem) null, true));
        this.mLogger.deleted(this.mTrashFactory.bulkDelete((TrashDeleteItem) null, true));
        revertFailedData();
        createNoMediaFile();
        super.done();
    }

    public File findTargetTrashDir(TrashDeleteItem trashDeleteItem) {
        String volumeName = trashDeleteItem.getVolumeName();
        File file = this.mTrashDirs.get(0);
        Iterator<File> it = this.mTrashDirs.iterator();
        while (it.hasNext()) {
            File next = it.next();
            if (next != null && next.getAbsolutePath().contains(volumeName)) {
                return next;
            }
        }
        return file;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054 A[SYNTHETIC, Splitter:B:20:0x0054] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getAlbumTotalCount(int[] r6) {
        /*
            r5 = this;
            java.lang.String r0 = "can not query with ["
            r1 = 0
            java.lang.String r2 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALBUMS_COUNT     // Catch:{ Exception -> 0x0010 }
            boolean r3 = r5.mQuickDelete     // Catch:{ Exception -> 0x0010 }
            if (r3 == 0) goto L_0x0012
            ta.b r3 = new ta.b     // Catch:{ Exception -> 0x0010 }
            r4 = 0
            r3.<init>(r6, r4)     // Catch:{ Exception -> 0x0010 }
            goto L_0x0018
        L_0x0010:
            r6 = move-exception
            goto L_0x0063
        L_0x0012:
            ta.b r3 = new ta.b     // Catch:{ Exception -> 0x0010 }
            r4 = 1
            r3.<init>(r6, r4)     // Catch:{ Exception -> 0x0010 }
        L_0x0018:
            android.database.Cursor r2 = com.samsung.android.gallery.database.dal.DbCompat.query(r2, r3)     // Catch:{ Exception -> 0x0010 }
            if (r2 == 0) goto L_0x0038
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x0036 }
            if (r3 == 0) goto L_0x0038
        L_0x0024:
            java.lang.String r6 = "__count"
            int r6 = r2.getColumnIndex(r6)     // Catch:{ all -> 0x0036 }
            int r6 = r2.getInt(r6)     // Catch:{ all -> 0x0036 }
            int r1 = r1 + r6
            boolean r6 = r2.moveToNext()     // Catch:{ all -> 0x0036 }
            if (r6 != 0) goto L_0x0024
            goto L_0x0052
        L_0x0036:
            r6 = move-exception
            goto L_0x0058
        L_0x0038:
            java.lang.String r3 = r5.TAG     // Catch:{ all -> 0x0036 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0036 }
            r4.<init>(r0)     // Catch:{ all -> 0x0036 }
            java.lang.String r6 = java.util.Arrays.toString(r6)     // Catch:{ all -> 0x0036 }
            r4.append(r6)     // Catch:{ all -> 0x0036 }
            java.lang.String r6 = "]"
            r4.append(r6)     // Catch:{ all -> 0x0036 }
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x0036 }
            com.samsung.android.gallery.support.utils.Log.w(r3, r6)     // Catch:{ all -> 0x0036 }
        L_0x0052:
            if (r2 == 0) goto L_0x0057
            r2.close()     // Catch:{ Exception -> 0x0010 }
        L_0x0057:
            return r1
        L_0x0058:
            if (r2 == 0) goto L_0x0062
            r2.close()     // Catch:{ all -> 0x005e }
            goto L_0x0062
        L_0x005e:
            r0 = move-exception
            r6.addSuppressed(r0)     // Catch:{ Exception -> 0x0010 }
        L_0x0062:
            throw r6     // Catch:{ Exception -> 0x0010 }
        L_0x0063:
            r5.printError(r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper.getAlbumTotalCount(int[]):int");
    }

    public String getCurrentTitle() {
        return this.mCurrentTitle;
    }

    public long getDeleteTime() {
        return this.mDeleteTime;
    }

    public int getExpiredDay() {
        return 31;
    }

    public String getFinalTrashPath(TrashDeleteItem trashDeleteItem, File file) {
        return file.getAbsolutePath();
    }

    public int getImageFailedCount() {
        return this.mLogger.getImageFailedCount();
    }

    public int getSucceedCount() {
        return this.mLogger.getVideoSucceedCount() + this.mLogger.getImageSucceedCount();
    }

    public String getTargetCloudThumbTrashPath(TrashDeleteItem trashDeleteItem) {
        return getTargetTrashPath(trashDeleteItem);
    }

    public String getTargetLocalName(TrashDeleteItem trashDeleteItem) {
        return getCachePath(trashDeleteItem.getPath(), trashDeleteItem.getFileId());
    }

    public int getVideoFailedCount() {
        return this.mLogger.getVideoFailedCount();
    }

    public boolean insertOrUpdateToTrash(TrashDeleteItem trashDeleteItem, String str, String str2) {
        String cloudServerId = trashDeleteItem.getCloudServerId();
        int isSameRecordExist = this.mTrashProvider.isSameRecordExist(cloudServerId);
        if (isSameRecordExist == -1) {
            setOriginalHashAndRestore(trashDeleteItem);
            return insertToLocalDB(trashDeleteItem, str, str2);
        }
        ContentValues contentValues = new ContentValues();
        StorageType storageType = trashDeleteItem.getStorageType();
        StorageType valueOf = StorageType.valueOf(isSameRecordExist);
        if (isLocal(storageType)) {
            contentValues.put("__absPath", str);
            contentValues.put("__Title", str2);
            contentValues.put("__originPath", trashDeleteItem.getPath());
            contentValues.put("__originTitle", trashDeleteItem.getTitle());
        }
        if (!storageType.equals(valueOf)) {
            contentValues.put("__storageType", 3);
        }
        contentValues.put("__database_version", Integer.valueOf(this.mNewDBVersion));
        if (contentValues.size() > 0) {
            return updateTrashDb(contentValues, "__cloudServerId =? ", new String[]{cloudServerId});
        }
        String str3 = this.TAG;
        Log.d(str3, "exactly same content is already exist [" + storageType.toInt() + "][" + isSameRecordExist + "]");
        return true;
    }

    public boolean insertToLocalDB(TrashDeleteItem trashDeleteItem, String str, String str2) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("__absPath", str);
        contentValues.put("__Title", str2);
        contentValues.put("__absID", Long.valueOf(trashDeleteItem.getFileId()));
        contentValues.put("__mediaType", Integer.valueOf(trashDeleteItem.getMediaType().toInt()));
        contentValues.put("__width", Integer.valueOf(trashDeleteItem.getWidth()));
        contentValues.put("__height", Integer.valueOf(trashDeleteItem.getHeight()));
        contentValues.put("__orientation", Integer.valueOf(trashDeleteItem.getOrientation()));
        contentValues.put("__originPath", trashDeleteItem.getPath());
        contentValues.put("__originTitle", trashDeleteItem.getTitle());
        long j2 = TrashData.of(trashDeleteItem).deleteTime;
        if (j2 <= 0 || !Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
            contentValues.put("__deleteTime", Long.valueOf(System.currentTimeMillis()));
        } else {
            contentValues.put("__deleteTime", Long.valueOf(j2));
        }
        contentValues.put("__storageType", Integer.valueOf(trashDeleteItem.getStorageType().toInt()));
        contentValues.put("__burstGroupID", Long.valueOf(getGroupMediaId(trashDeleteItem)));
        contentValues.put("__bestImage", Integer.valueOf(getBestImage(trashDeleteItem)));
        contentValues.put("__cloudServerId", trashDeleteItem.getCloudServerId());
        contentValues.put("__cloudTP", trashDeleteItem.getCloudThumbPath());
        contentValues.put("__restoreExtra", TrashData.of(trashDeleteItem).restoreData);
        contentValues.put("__volumeName", trashDeleteItem.getVolumeName());
        contentValues.put("__expiredPeriod", Integer.valueOf(getExpiredDay()));
        contentValues.put("__database_version", Integer.valueOf(this.mNewDBVersion));
        if (Features.isEnabled(Features.SUPPORT_SEC_MP_ORIENTATION_TAG)) {
            contentValues.put("__orientationTag", Integer.valueOf(trashDeleteItem.getOrientationTag()));
        }
        try {
            if (this.mTrashProvider.insertTrash(contentValues) != null) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } finally {
            String str3 = this.TAG;
            Log.d(str3, "insert to local db : " + trashDeleteItem.getFileId() + " +" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    public boolean isAbnormalRecordDeleteRequested() {
        return this.mLogger.isAbnormalDeleteRequest();
    }

    public boolean isAbnormalRecordDeleted() {
        return this.mLogger.isAbnormalDeleted();
    }

    public int quickAlbumDelete(ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2) {
        long currentTimeMillis = System.currentTimeMillis();
        int updateAlbumTrashed = updateAlbumTrashed(arrayList, arrayList2, true);
        String str = this.TAG;
        a.A(new Object[]{Integer.valueOf(updateAlbumTrashed), Long.valueOf(currentTimeMillis)}, new StringBuilder("quickAlbumDelete"), str);
        return updateAlbumTrashed;
    }

    public int quickDelete(ArrayList<MediaItem> arrayList, boolean z) {
        int updateItemTrashed = updateItemTrashed(arrayList, true, z);
        Log.i(this.TAG, "quickDelete", Integer.valueOf(updateItemTrashed));
        return updateItemTrashed;
    }

    public int revertTrashedItem(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, ArrayList<MediaItem> arrayList3) {
        int updateItemTrashed = updateItemTrashed(arrayList, false);
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        Iterator<MediaItem> it = arrayList2.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            arrayList4.add(Integer.valueOf(next.getAlbumID()));
            arrayList5.add(Integer.valueOf(next.getAlbumID()));
        }
        Iterator<MediaItem> it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            arrayList5.add(Integer.valueOf(it2.next().getAlbumID()));
        }
        int updateAlbumTrashed = updateItemTrashed + updateAlbumTrashed(arrayList4, arrayList5, false);
        Log.i(this.TAG, "revertTrashedItem", Integer.valueOf(updateAlbumTrashed));
        return updateAlbumTrashed;
    }

    public void setDeleteTime(long j2) {
        this.mDeleteTime = j2;
    }

    public void setFailCollect(boolean z) {
        this.mFailCollect = z;
    }

    public void setOriginalHashAndRestore(TrashDeleteItem trashDeleteItem) {
        trashDeleteItem.setOriginFileHash(this.mTrashFactory.getOriginFileHash(trashDeleteItem));
        trashDeleteItem.setRestoreExtra();
    }

    public void setQuickDelete(boolean z) {
        this.mQuickDelete = z;
    }

    public String tag() {
        return "TrashDeleteHelper";
    }

    private int updateItemTrashed(ArrayList<MediaItem> arrayList, boolean z, boolean z3) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList2 = new ArrayList();
        int i7 = 0;
        try {
            Iterator<MediaItem> it = arrayList.iterator();
            i2 = 0;
            while (it.hasNext()) {
                try {
                    MediaItem next = it.next();
                    if (!z3 || !next.isGroupShot()) {
                        arrayList2.add(Long.valueOf(next.getFileId()));
                    } else {
                        loadGroupItemTrashed(next, z, arrayList2);
                    }
                    if (arrayList2.size() >= 10000) {
                        i7 += this.mTrashFactory.updateIsTrashed(StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList2), z);
                        i2 += arrayList2.size();
                        arrayList2.clear();
                    }
                } catch (Exception e) {
                    e = e;
                    a.s(e, new StringBuilder("updateItemTrashed failed. e="), this.TAG);
                    Log.i(this.TAG, "updateItemTrashed" + Logger.vt(Boolean.valueOf(z), Integer.valueOf(arrayList.size()), Integer.valueOf(i2), Integer.valueOf(i7), Long.valueOf(currentTimeMillis)));
                    return i7;
                }
            }
            if (!arrayList2.isEmpty()) {
                i7 += this.mTrashFactory.updateIsTrashed(StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList2), z);
                int size = arrayList2.size() + i2;
                try {
                    arrayList2.clear();
                    i2 = size;
                } catch (Exception e7) {
                    i2 = size;
                    e = e7;
                    a.s(e, new StringBuilder("updateItemTrashed failed. e="), this.TAG);
                    Log.i(this.TAG, "updateItemTrashed" + Logger.vt(Boolean.valueOf(z), Integer.valueOf(arrayList.size()), Integer.valueOf(i2), Integer.valueOf(i7), Long.valueOf(currentTimeMillis)));
                    return i7;
                }
            }
        } catch (Exception e8) {
            e = e8;
            i2 = 0;
            a.s(e, new StringBuilder("updateItemTrashed failed. e="), this.TAG);
            Log.i(this.TAG, "updateItemTrashed" + Logger.vt(Boolean.valueOf(z), Integer.valueOf(arrayList.size()), Integer.valueOf(i2), Integer.valueOf(i7), Long.valueOf(currentTimeMillis)));
            return i7;
        }
        Log.i(this.TAG, "updateItemTrashed" + Logger.vt(Boolean.valueOf(z), Integer.valueOf(arrayList.size()), Integer.valueOf(i2), Integer.valueOf(i7), Long.valueOf(currentTimeMillis)));
        return i7;
    }

    public void deleteItem(FileItemInterface fileItemInterface, boolean z) {
        TrashDeleteItem trashDeleteItem = new TrashDeleteItem(fileItemInterface, getDeleteTime());
        if (!z || !trashDeleteItem.isGroupItem()) {
            deleteItemInternal(trashDeleteItem, true);
        } else {
            deleteGroupItem(trashDeleteItem);
        }
    }

    public TrashDeleteLogger getLogger() {
        return this.mLogger;
    }

    private GroupType getGroupType(FileItemInterface fileItemInterface) {
        if (fileItemInterface.isSimilarShot()) {
            return GroupType.SIMILAR;
        }
        if (fileItemInterface.isSingleTakenShot()) {
            return GroupType.SINGLE_TAKEN;
        }
        return GroupType.BURST;
    }

    public TrashDeleteHelper(Context context, boolean z) {
        super(context);
        this.mDeleteTime = 0;
        this.mCurrentTitle = null;
        this.mFailCollect = false;
        this.mSupportTrash = z;
        this.mUseStoreApi = this.mTrashFactory.useStoreApi();
        this.mLogger = new TrashDeleteLogger();
        this.mNewDBVersion = LocalDatabaseHelper.getInstance(context).getDatabaseVersion();
    }
}
