package com.samsung.android.gallery.app.service;

import A4.B;
import V8.a;
import a6.g;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import c4.C0431a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.fileio.abstraction.FileOpResult;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.service.message.FileOperationMsgMgr;
import com.samsung.android.gallery.module.service.message.FileOperationMsgParams;
import com.samsung.android.gallery.module.service.support.FileOperationHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileOperationService extends AbsProgressService {
    private static final int LATE_DELAY;
    private DialogState mDialogState = DialogState.PROGRESS;
    private String mDialogTitle = null;
    private int mFailCount = 0;
    private FileInfo mFileInfo;
    private final FileOperationHelper mFileOpHelper = new FileOperationHelper();
    private int mImageCount = 0;
    private boolean mIsChecked = false;
    private boolean mIsEmptyAlbum = false;
    private boolean mIsNewAlbum = false;
    private int mOperateCount = 0;
    private long mOperateSize = 0;
    private String mOperationType = null;
    private int mPPPCount = 0;
    private int mRenameFileCount = 0;
    private int mReplaceCount = 0;
    private String mResultMsg = null;
    private FileOpResult mResultType = FileOpResult.OP_LOCAL_FAIL;
    private int mSelectedAlbumId = 0;
    private int mSkipCount = 0;
    private final ArrayList<String> mSourceAlbumPathList = new ArrayList<>();
    private int mSourcePathNullCount = 0;
    private String mStorageState = null;
    private int mSuccessCount = 0;
    private String mTargetAlbumPath = null;
    private int mTotalCount = 0;
    private long mTotalSize = 0;
    private int mVideoCount = 0;
    private int mWhichChecked = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DialogState {
        PROGRESS,
        DUPLICATE,
        RENAME
    }

    static {
        int i2;
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            i2 = 500;
        } else {
            i2 = 0;
        }
        LATE_DELAY = i2;
    }

    public FileOperationService() {
        super("FileOperationService", "com.samsung.android.gallery.app.service.FileOperationService");
    }

    private void addFileOperationLog() {
        String str;
        if (this.mSourceAlbumPathList.isEmpty()) {
            str = "load failed";
        } else {
            str = (String) this.mSourceAlbumPathList.stream().limit(5).map(new C0431a(0)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        }
        StringBuilder sb2 = new StringBuilder("[FileOpLog_ver3][type=");
        sb2.append(this.mOperationType);
        sb2.append("][");
        sb2.append(this.mResultType);
        sb2.append("][src_path][");
        sb2.append(Logger.getEncodedString(str));
        sb2.append("][dst_path][");
        sb2.append(Logger.getEncodedString(this.mTargetAlbumPath));
        sb2.append("][total=");
        sb2.append(this.mTotalCount);
        sb2.append("][success=");
        sb2.append(this.mSuccessCount);
        sb2.append("][fail=");
        sb2.append(this.mFailCount);
        sb2.append("][replace=");
        sb2.append(this.mReplaceCount);
        sb2.append("][rename_file=");
        sb2.append(this.mRenameFileCount);
        sb2.append("][skip=");
        sb2.append(this.mSkipCount);
        sb2.append("][ppp_fail(src)=");
        sb2.append(this.mPPPCount);
        sb2.append("][storage_state =");
        sb2.append(this.mStorageState);
        sb2.append("][empty_album=");
        sb2.append(this.mIsEmptyAlbum);
        sb2.append("][new_album=");
        sb2.append(this.mIsNewAlbum);
        sb2.append("][src_path_null=");
        sb2.append(this.mSourcePathNullCount);
        sb2.append("][msg=");
        DebugLogger.getDeleteInstance().insertLog(C0212a.p(sb2, this.mResultMsg, "]"));
    }

    private void addInternal(MediaItem mediaItem, boolean z, long j2) {
        long size = this.mFileOpHelper.getSize(mediaItem);
        String referencePath = mediaItem.getReferencePath();
        if (mediaItem.isVideo()) {
            this.mVideoCount++;
        } else {
            this.mImageCount++;
        }
        if (mediaItem.isPostProcessing()) {
            this.mPPPCount++;
            Log.w("FileOperationService", "skip adding, ppp. [" + referencePath + "]");
        } else if (TextUtils.isEmpty(referencePath)) {
            this.mSourcePathNullCount++;
            Log.w("FileOperationService", "skip adding, path is null. [" + mediaItem.isCloudOnly() + "]");
        } else {
            boolean z3 = z;
            if (isMemoryUnavailable(z3, j2, size, referencePath)) {
                setResult(FileOpResult.OP_NOT_ENOUGH_STORAGE);
            } else if (!z3 || !mediaItem.isCloudOnly()) {
                String directoryFromPath = FileUtils.getDirectoryFromPath(referencePath, false);
                if (!this.mSourceAlbumPathList.contains(directoryFromPath)) {
                    this.mSourceAlbumPathList.add(directoryFromPath);
                }
                this.mTotalSize += size;
                addToQueue(new ProgressJob(new FileInfo(mediaItem, this.mTargetAlbumPath, this.mOperationType)));
                this.mFileOpHelper.startPreloadMyTag(getApplicationContext(), mediaItem, this.mOperationType);
            } else {
                this.mFailCount++;
            }
        }
    }

    private void cancelOperation() {
        this.mFileOpHelper.cancelOperation();
        AnalyticsLogger.getInstance().postLog(getScreenId(), AnalyticsEventId.EVENT_COPY_MOVE_TO_ALBUM_CANCEL.toString());
    }

    private void finishOperation() {
        updateResult();
        updateCurrentAlbumState();
        this.mFileOpHelper.finishOperation(getApplicationContext(), this.mTargetAlbumPath);
        addFileOperationLog();
        PreferenceCache.LastFileOpPath.clear();
        if (isMove()) {
            Blackboard.getApplicationInstance().post("global://stories/data_pendingUpdate", (Object) null);
        }
    }

    private MediaItem[] getMediaItemList() {
        if (!isRename()) {
            return (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        }
        boolean z = this.mIsEmptyAlbum;
        if (z) {
            return (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        }
        return this.mFileOpHelper.getItemListForRename(this.mSelectedAlbumId, z);
    }

    private String getNotificationMessage() {
        return FileOperationMsgMgr.getNotificationMessage(this, FileOperationMsgParams.builder().setIsCopy(isCopy()).setIsMove(isMove()).setIsRename(isRename()).setOperateCount(this.mOperateCount).setTotalCount(this.mTotalCount).build());
    }

    private String getScreenId() {
        if (isCopy()) {
            return AnalyticsScreenId.SCREEN_COPY_TO_ALBUM.toString();
        }
        return AnalyticsScreenId.SCREEN_MOVE_TO_ALBUM.toString();
    }

    private void handleOperationFail(FileInfo fileInfo) {
        try {
            String srcPath = fileInfo.getSrcPath();
            StringBuilder sb2 = new StringBuilder("operation failed [");
            sb2.append(this.mOperationType);
            sb2.append("][");
            sb2.append(this.mResultType);
            sb2.append("][");
            sb2.append(fileInfo.getStorageType());
            sb2.append("][");
            sb2.append(Logger.getEncodedString(srcPath + "::" + fileInfo.getDestPath()));
            Log.d("FileOperationService", sb2.toString());
        } catch (Exception e) {
            Log.e("FileOperationService", "operation failed [" + this.mOperationType + "][" + this.mResultType + "][" + e.getMessage());
        }
    }

    private boolean isCanceled() {
        return FileOpResult.isCanceled(this.mResultType);
    }

    private boolean isCopy() {
        return "copy".equals(this.mOperationType);
    }

    private boolean isMemoryUnavailable(boolean z, long j2, long j3, String str) {
        if (isRename() || ((isMove() && FileUtils.isInRemovableStorage(str) == z) || this.mTotalSize + j3 < j2)) {
            return false;
        }
        return true;
    }

    private boolean isMove() {
        return "move".equals(this.mOperationType);
    }

    private boolean isNotEnoughStorage() {
        return FileOpResult.isNotEnoughStorage(this.mResultType);
    }

    private boolean isRename() {
        return "rename".equals(this.mOperationType);
    }

    private boolean isSameAlbum() {
        FileInfo fileInfo = this.mFileInfo;
        if (fileInfo == null || fileInfo.getMediaItem() == null) {
            Log.e("FileOperationService", "isSameAlbum: item is null");
            return false;
        }
        MediaItem mediaItem = this.mFileInfo.getMediaItem();
        if (TextUtils.isEmpty(mediaItem.getPath()) || TextUtils.isEmpty(this.mTargetAlbumPath)) {
            return false;
        }
        return this.mTargetAlbumPath.equals(FileUtils.getParent(mediaItem.getPath()));
    }

    private boolean isSuccess() {
        return FileOpResult.isSuccess(this.mResultType);
    }

    /* access modifiers changed from: private */
    public void onUpdate(long j2) {
        long j3 = this.mOperateSize + j2;
        this.mOperateSize = j3;
        long j8 = this.mTotalSize;
        if (j8 != 0 && j3 > j8) {
            Log.w("FileOperationService", "operated size is larger than total size. [" + this.mOperateSize + "][" + this.mTotalSize + "]");
            this.mOperateSize = this.mTotalSize;
        }
        this.mDialogHelper.updateDialog(this.mOperateCount, this.mTotalCount, getPercentage());
        updateNotification();
    }

    private boolean operateUserOption(int i2, String str) {
        if (i2 == -3) {
            this.mSkipCount++;
            onUpdate(this.mFileOpHelper.getSize(this.mFileInfo.getMediaItem()));
            return true;
        } else if (i2 == -2) {
            this.mReplaceCount++;
            removeThumbnailCache(str);
            performOperation(str, 8);
            return true;
        } else if (i2 == -1) {
            this.mRenameFileCount++;
            String renamedPath = this.mFileOpHelper.getRenamedPath(this, this.mFileInfo.getMediaItem(), str);
            if (this.mIsChecked) {
                performOperation(renamedPath, 0);
                return true;
            }
            this.mDialogState = DialogState.RENAME;
            this.mDialogHelper.showRenameDialog(renamedPath);
            return false;
        } else if (i2 != 0) {
            return true;
        } else {
            performOperation(str, 0);
            return true;
        }
    }

    private void performOperation(String str, int i2) {
        this.mFileInfo.setDestPath(str);
        this.mFileInfo.setFileMode(i2);
        setResult(this.mFileOpHelper.execute(getApplicationContext(), this.mFileInfo));
        if (isSuccess()) {
            this.mSuccessCount++;
        } else {
            handleOperationFail(this.mFileInfo);
        }
    }

    private void removeThumbnailCache(String str) {
        Cursor query;
        try {
            query = DbCompat.query(DbKey.ALL_PICTURES, new B(str, 22));
            if (query != null) {
                if (query.moveToFirst()) {
                    ThumbnailLoader.getInstance().removeCache(MediaItemBuilder.create(query));
                }
            }
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void saveScanPaths() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mTargetAlbumPath);
        Iterator<String> it = this.mSourceAlbumPathList.iterator();
        while (it.hasNext()) {
            sb2.append(NumericEnum.SEP);
            sb2.append(it.next());
        }
        PreferenceCache.LastFileOpPath.setString(sb2.toString());
    }

    private void setResult(FileOpResult fileOpResult) {
        this.mResultType = fileOpResult;
    }

    private void showResult() {
        if (isNotEnoughStorage()) {
            this.mBlackboard.post(CommandKey.DATA_REQUEST(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", getString(R.string.not_enough_space)).appendArg("description", FileOperationMsgMgr.getNotEnoughStorageMessage(this, FileOperationMsgParams.builder().setIsCopy(isCopy()).setImageCount(this.mImageCount).setVideoCount(this.mVideoCount).setTotalCount(this.mImageCount + this.mVideoCount).build())).appendArg("option1", getString(R.string.ok)).appendArg("hideCancel", true).build()), (Object) null);
        } else if (!isRename()) {
            Utils.showToast((Context) this, FileOperationMsgMgr.getResultMessage((Context) this, this.mResultType, FileOperationMsgParams.builder().setAlbumPath(this.mTargetAlbumPath).setIsCopy(isCopy()).setSuccessCount(this.mSuccessCount).setFailCount(this.mFailCount).setTotalCount(this.mTotalCount).build()));
        }
    }

    private boolean startFileOperation() {
        if (this.mFileInfo.isGroupShot() || !this.mFileOpHelper.existData(this, this.mFileInfo.getMediaItem(), this.mFileInfo.getDestPath())) {
            performOperation(this.mFileInfo.getDestPath(), 0);
            return true;
        } else if (this.mIsChecked) {
            operateUserOption(this.mWhichChecked, this.mFileInfo.getDestPath());
            return true;
        } else {
            this.mDialogState = DialogState.DUPLICATE;
            this.mDialogHelper.showFileOperationDialog(this.mFileInfo.getTitle(), this.mFileInfo.getDestPath(), getScreenId());
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0126 A[SYNTHETIC, Splitter:B:48:0x0126] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateCurrentAlbumState() {
        /*
            r9 = this;
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r9.mBlackboard
            if (r0 == 0) goto L_0x015b
            int r1 = r9.mSuccessCount
            if (r1 != 0) goto L_0x000a
            goto L_0x015b
        L_0x000a:
            java.lang.String r1 = "location://variable/currentv1"
            r2 = 0
            java.lang.Object r0 = r0.read(r1, r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "FileOperationService"
            if (r0 == 0) goto L_0x0156
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L_0x001f
            goto L_0x0156
        L_0x001f:
            java.lang.String r0 = com.samsung.android.gallery.support.utils.ArgumentsUtil.removeArgs(r0)
            boolean r2 = r9.isRename()
            java.lang.String r3 = r9.mTargetAlbumPath
            int r3 = com.samsung.android.gallery.support.utils.FileUtils.getBucketId(r3)
            boolean r4 = r9.mIsNewAlbum
            if (r4 != 0) goto L_0x0033
            if (r2 == 0) goto L_0x013a
        L_0x0033:
            if (r2 == 0) goto L_0x009b
            com.samsung.android.gallery.module.album.AlbumHelper r4 = com.samsung.android.gallery.module.album.AlbumHelper.getInstance()
            int r5 = r9.mSelectedAlbumId
            java.lang.String r6 = r9.mTargetAlbumPath
            r4.replaceAlbumCover(r5, r6)
            java.util.ArrayList<java.lang.String> r4 = r9.mSourceAlbumPathList
            r5 = 0
            java.lang.Object r4 = r4.get(r5)
            java.lang.String r4 = (java.lang.String) r4
            com.samsung.android.gallery.support.utils.FileUtils.deleteEmptyDirectory(r4)
            boolean r5 = r9.isCanceled()
            if (r5 == 0) goto L_0x0068
            com.samsung.android.gallery.module.album.AlbumHelper r5 = com.samsung.android.gallery.module.album.AlbumHelper.getInstance()
            java.lang.Object[] r5 = r5.getTargetAlbumInfoForRename(r3)
            java.util.Optional r5 = java.util.Optional.ofNullable(r5)
            A4.B r6 = new A4.B
            r7 = 21
            r6.<init>(r4, r7)
            r5.ifPresent(r6)
        L_0x0068:
            com.samsung.android.gallery.support.utils.GalleryPreference r4 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstance()
            int r5 = r9.mSelectedAlbumId
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r6 = 12
            int r5 = r4.loadSortBy(r5, r6)
            if (r5 == r6) goto L_0x0081
            java.lang.String r6 = java.lang.String.valueOf(r3)
            r4.saveSortBy(r6, r5)
        L_0x0081:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            com.samsung.android.gallery.support.utils.PreferenceName r6 = com.samsung.android.gallery.support.utils.PreferenceName.SORT_BY
            java.lang.String r6 = r6.key()
            r5.append(r6)
            int r6 = r9.mSelectedAlbumId
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.removeState((java.lang.String) r5)
        L_0x009b:
            boolean r4 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isAlbumPictures(r0)
            if (r4 != 0) goto L_0x00ab
            boolean r4 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi5x.MX_ALBUMS
            if (r4 == 0) goto L_0x013a
            boolean r4 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isAlbumSetting(r0)
            if (r4 == 0) goto L_0x013a
        L_0x00ab:
            java.lang.String r4 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALBUMS     // Catch:{ Exception -> 0x012a }
            A4.d r5 = new A4.d     // Catch:{ Exception -> 0x012a }
            r6 = 15
            r5.<init>(r3, r6)     // Catch:{ Exception -> 0x012a }
            android.database.Cursor r4 = com.samsung.android.gallery.database.dal.DbCompat.query(r4, r5)     // Catch:{ Exception -> 0x012a }
            java.lang.String r5 = "post target album: "
            if (r4 == 0) goto L_0x00fe
            boolean r6 = r4.moveToFirst()     // Catch:{ all -> 0x00e4 }
            if (r6 == 0) goto L_0x00fe
            com.samsung.android.gallery.module.data.MediaItem r6 = com.samsung.android.gallery.module.data.MediaItemLoader.load(r4)     // Catch:{ all -> 0x00e4 }
            com.samsung.android.gallery.support.blackboard.Blackboard r7 = r9.mBlackboard     // Catch:{ all -> 0x00e4 }
            java.lang.String r8 = "data://albums/moveTo/target"
            r7.publish(r8, r6)     // Catch:{ all -> 0x00e4 }
            if (r2 == 0) goto L_0x00e6
            com.samsung.android.gallery.support.blackboard.Blackboard r7 = r9.mBlackboard     // Catch:{ all -> 0x00e4 }
            boolean r0 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isAlbumPictures(r0)     // Catch:{ all -> 0x00e4 }
            if (r0 == 0) goto L_0x00da
            r0 = 104(0x68, float:1.46E-43)
            goto L_0x00dc
        L_0x00da:
            r0 = 2012(0x7dc, float:2.82E-42)
        L_0x00dc:
            com.samsung.android.gallery.support.blackboard.key.EventMessage r0 = com.samsung.android.gallery.support.blackboard.key.EventMessage.obtain(r0)     // Catch:{ all -> 0x00e4 }
            r7.postEvent(r0)     // Catch:{ all -> 0x00e4 }
            goto L_0x00e6
        L_0x00e4:
            r0 = move-exception
            goto L_0x012c
        L_0x00e6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e4 }
            r0.<init>(r5)     // Catch:{ all -> 0x00e4 }
            java.lang.String r5 = r6.getDisplayName()     // Catch:{ all -> 0x00e4 }
            java.lang.String r5 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r5)     // Catch:{ all -> 0x00e4 }
            r0.append(r5)     // Catch:{ all -> 0x00e4 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00e4 }
            com.samsung.android.gallery.support.utils.Log.d(r1, r0)     // Catch:{ all -> 0x00e4 }
            goto L_0x0124
        L_0x00fe:
            boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.SUPPORT_RENAME_BY_MP     // Catch:{ all -> 0x00e4 }
            if (r0 != 0) goto L_0x011f
            if (r2 == 0) goto L_0x011f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e4 }
            r0.<init>(r5)     // Catch:{ all -> 0x00e4 }
            r0.append(r3)     // Catch:{ all -> 0x00e4 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00e4 }
            com.samsung.android.gallery.support.utils.Log.d(r1, r0)     // Catch:{ all -> 0x00e4 }
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r9.mBlackboard     // Catch:{ all -> 0x00e4 }
            java.lang.String r1 = "data://albums/moveTo/rename"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00e4 }
            r0.publish(r1, r5)     // Catch:{ all -> 0x00e4 }
            goto L_0x0124
        L_0x011f:
            java.lang.String r0 = "cannot find target album after rename "
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)     // Catch:{ all -> 0x00e4 }
        L_0x0124:
            if (r4 == 0) goto L_0x013a
            r4.close()     // Catch:{ Exception -> 0x012a }
            goto L_0x013a
        L_0x012a:
            r0 = move-exception
            goto L_0x0137
        L_0x012c:
            if (r4 == 0) goto L_0x0136
            r4.close()     // Catch:{ all -> 0x0132 }
            goto L_0x0136
        L_0x0132:
            r1 = move-exception
            r0.addSuppressed(r1)     // Catch:{ Exception -> 0x012a }
        L_0x0136:
            throw r0     // Catch:{ Exception -> 0x012a }
        L_0x0137:
            r0.printStackTrace()
        L_0x013a:
            if (r2 != 0) goto L_0x015b
            if (r3 == 0) goto L_0x015b
            com.samsung.android.gallery.support.utils.PreferenceCache r0 = com.samsung.android.gallery.support.utils.PreferenceCache.AlbumLatestUpdatedId
            java.lang.String r9 = r9.mTargetAlbumPath
            int r9 = com.samsung.android.gallery.support.utils.FileUtils.getBucketId(r9)
            r0.setInt(r9)
            com.samsung.android.gallery.support.utils.PreferenceCache r9 = com.samsung.android.gallery.support.utils.PreferenceCache.AlbumLatestUpdatedTime
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            r9.setLong(r0)
            goto L_0x015b
        L_0x0156:
            java.lang.String r9 = "unable to update current album state."
            com.samsung.android.gallery.support.utils.Log.e(r1, r9)
        L_0x015b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.service.FileOperationService.updateCurrentAlbumState():void");
    }

    private void updateResult() {
        FileOpResult fileOpResult;
        if (isInterrupted()) {
            setResult(FileOpResult.OP_CANCEL);
        } else if (!isSuccess() && this.mSuccessCount > 0) {
            if (this.mFailCount > 0) {
                fileOpResult = FileOpResult.OP_LOCAL_OK_SYNC_OFF;
            } else {
                fileOpResult = FileOpResult.OP_LOCAL_OK;
            }
            setResult(fileOpResult);
        }
    }

    public boolean addJobs(Intent intent) {
        String str;
        String str2;
        MediaItem[] mediaItemList = getMediaItemList();
        if (mediaItemList == null || mediaItemList.length == 0) {
            Log.w("FileOperationService", "items are empty. adding failed.");
            setResult(FileOpResult.OP_ITEMS_ALL_NULL);
            return false;
        }
        boolean isInRemovableStorage = FileUtils.isInRemovableStorage(this.mTargetAlbumPath);
        this.mStorageState = MemoryUtils.getStorageState(isInRemovableStorage ? 1 : 0);
        Log.d("FileOperationService", "current storage state : " + this.mStorageState);
        long availableMemorySize = MemoryUtils.getAvailableMemorySize(isInRemovableStorage);
        if (availableMemorySize == -1) {
            StringBuilder sb2 = new StringBuilder();
            if (isInRemovableStorage) {
                str2 = "sdcard";
            } else {
                str2 = IdentityApiContract.Parameter.DEVICE;
            }
            sb2.append(str2);
            sb2.append(" storage state : ");
            sb2.append(this.mStorageState);
            String sb3 = sb2.toString();
            Log.w("FileOperationService", sb3);
            setResult(FileOpResult.OP_STORAGE_STATE_FAIL, sb3);
            return false;
        }
        Log.d("FileOperationService", "targetPath = " + Logger.getEncodedString(this.mTargetAlbumPath));
        int length = mediaItemList.length;
        for (int i2 = 0; i2 < length; i2++) {
            MediaItem mediaItem = mediaItemList[i2];
            if (mediaItem != null) {
                addInternal(mediaItem, isInRemovableStorage, availableMemorySize);
            }
        }
        this.mFileOpHelper.finishPreloadMyTag(getApplicationContext(), this.mOperationType);
        if (isNotEnoughStorage()) {
            Log.w("FileOperationService", "available memory is exceeded. adding failed.");
            return false;
        } else if (!isQueueEmpty()) {
            this.mTotalCount = getQueueSize();
            saveScanPaths();
            if (this.mSourceAlbumPathList.isEmpty()) {
                str = "addJobs failed";
            } else {
                str = "src path : " + ((String) this.mSourceAlbumPathList.stream().limit(5).map(new a(29)).collect(Collectors.joining(ArcCommonLog.TAG_COMMA)));
            }
            Log.d("FileOperationService", "End onSetup : " + str);
            return true;
        } else if (this.mFailCount > 0) {
            setResult(FileOpResult.OP_FAIL_SYNC_OFF);
            Log.w("FileOperationService", "to sd card folder (only cloud item)");
            return false;
        } else if (this.mPPPCount == mediaItemList.length) {
            setResult(FileOpResult.OP_PPP_FAIL, "source items are ppp");
            Log.w("FileOperationService", "source items are ppp. adding failed.");
            return false;
        } else {
            setResult(FileOpResult.OP_ADDING_FAIL_WRONG_ITEMS);
            Log.w("FileOperationService", "queue is empty. adding failed.");
            return false;
        }
    }

    public void doJob(ProgressJob progressJob) {
        try {
            boolean z = true;
            this.mOperateCount++;
            FileInfo fileInfo = (FileInfo) progressJob.getParam(0);
            this.mFileInfo = fileInfo;
            if (!TextUtils.isEmpty(fileInfo.getFileName())) {
                this.mNotificationTitle = this.mFileInfo.getFileName();
                this.mNotificationMessage = getNotificationMessage();
                Log.d("FileOperationService", "do job [" + this.mOperateCount + "][" + this.mTotalCount + "]");
                this.mDialogHelper.updateDialog(this.mOperateCount, this.mTotalCount, getPercentage());
                updateNotification();
                z = startFileOperation();
            }
            if (!z) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
            throw th;
        }
        ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
    }

    public void forceRefreshData() {
        if (LocationKey.isContentViewer(this.mLocationKey) && isMove() && !isSameAlbum()) {
            this.mBlackboard.postEvent(EventMessage.obtain(3015));
        }
        super.forceRefreshData();
    }

    public String getChannelName() {
        return FileOperationMsgMgr.getServiceName(this, isCopy(), isRename());
    }

    public int getPercentage() {
        long j2 = this.mTotalSize;
        if (j2 != 0) {
            return (int) ((this.mOperateSize * 100) / j2);
        }
        Log.e("FileOperationService", "total size is zero so it is impossible to get a percentage");
        return 0;
    }

    public void onCancelInternal() {
        cancelOperation();
    }

    public void onCleanInternal() {
        this.mDialogHelper.dismissDialog();
        showResult();
        if (isSuccess()) {
            forceRefreshData();
        }
        super.onCleanInternal();
    }

    public void onContinueInternal() {
        Log.d("FileOperationService", "continue requested [" + this.mDialogState + "]");
        this.mDialogHelper.showDialog(this.mDialogTitle, this.mOperateCount, this.mTotalCount, getPercentage());
        if (this.mDialogState.ordinal() >= DialogState.DUPLICATE.ordinal()) {
            String destPath = this.mFileInfo.getDestPath();
            this.mDialogHelper.showFileOperationDialog(this.mFileInfo.getTitle(), this.mFileInfo.getDestPath(), getScreenId());
            if (this.mDialogState == DialogState.RENAME) {
                this.mDialogHelper.showRenameDialog(FileUtils.getNewFilePath(this.mFileOpHelper.getRenamedPath(this, this.mFileInfo.getMediaItem(), destPath)));
            }
        }
    }

    public void onEndInternal() {
        try {
            finishOperation();
            setChangeCollectOff();
        } catch (Exception e) {
            e.printStackTrace();
            this.mSuccessCount = 0;
            this.mFailCount = this.mTotalCount;
            this.mResultType = FileOpResult.OP_LOCAL_FAIL;
        }
        setCleanDelay(LATE_DELAY);
        super.onEndInternal();
    }

    public boolean onInterruptInternal(Intent intent) {
        Log.d("FileOperationService", "interrupt requested [" + this.mDialogState + "]");
        cancelOperation();
        if (this.mDialogState.ordinal() <= DialogState.PROGRESS.ordinal()) {
            return true;
        }
        ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
        return true;
    }

    public void onOptionInternal(Object obj, Bundle bundle) {
        boolean z = false;
        if (!(obj == null || bundle == null)) {
            this.mWhichChecked = BundleWrapper.getInt(bundle, "target", -1);
            this.mIsChecked = BundleWrapper.getBoolean(bundle, "checked", false);
            if (!isInterrupted()) {
                z = operateUserOption(this.mWhichChecked, (String) obj);
            } else {
                Log.d("FileOperationService", "skip option by interrupt");
            }
        }
        if (z) {
            this.mDialogState = DialogState.PROGRESS;
            ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
        }
    }

    public boolean onPrepareInternal(Intent intent) {
        this.mResultType = FileOpResult.OP_LOCAL_OK;
        this.mFileOpHelper.init(getApplicationContext(), new g(13, this));
        String stringExtra = intent.getStringExtra("target_album_path");
        this.mTargetAlbumPath = stringExtra;
        if (TextUtils.isEmpty(stringExtra)) {
            setResult(FileOpResult.OP_LOAD_FAIL_OF_INTENT_VALUE, "target album path is null prepare failed");
            return false;
        }
        String str = this.mTargetAlbumPath;
        if (str.charAt(str.length() - 1) == File.separatorChar) {
            this.mTargetAlbumPath = C0280e.d(1, 0, this.mTargetAlbumPath);
        }
        if (!FileUtils.isValidPath(this.mTargetAlbumPath)) {
            setResult(FileOpResult.OP_PATH_NOT_MIGRATED);
            return false;
        } else if (!FileUtils.makeDirectoryIfAbsent(this.mTargetAlbumPath)) {
            setResult(FileOpResult.OP_LOAD_FAIL_OF_INTENT_VALUE, "target directory is not created prepare failed");
            return false;
        } else {
            this.mOperationType = intent.getStringExtra("operation_type");
            if (isCopy() || isMove() || isRename()) {
                this.mSelectedAlbumId = intent.getIntExtra("selected_album_id", 0);
                if (!isRename() || this.mSelectedAlbumId != 0) {
                    this.mIsEmptyAlbum = intent.getBooleanExtra("is_empty_album", false);
                    this.mIsNewAlbum = intent.getBooleanExtra("is_new_album", false);
                    this.mDialogTitle = FileOperationMsgMgr.getDialogTitle(this, this.mTargetAlbumPath, isCopy(), isRename(), (FileOperationMsgParams) null);
                    return super.onPrepareInternal(intent);
                }
                setResult(FileOpResult.OP_LOAD_FAIL_OF_INTENT_VALUE, "wrong album id for rename prepare failed");
                return false;
            }
            FileOpResult fileOpResult = FileOpResult.OP_LOAD_FAIL_OF_INTENT_VALUE;
            setResult(fileOpResult, "wrong operation type prepare failed " + this.mOperationType);
            return false;
        }
    }

    public void onRenameInternal(Object obj, Bundle bundle) {
        if (obj != null) {
            operateUserOption(0, (String) obj);
        }
        this.mDialogState = DialogState.PROGRESS;
        ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
    }

    public void onStartInternal() {
        super.onStartInternal();
        this.mDialogHelper.showDialog(this.mDialogTitle, 1, this.mTotalCount, getPercentage());
        this.mBlackboard.postEvent(EventMessage.obtain(1003));
    }

    private void setResult(FileOpResult fileOpResult, String str) {
        this.mResultType = fileOpResult;
        this.mResultMsg = str.replace(' ', '_');
        Log.w("FileOperationService", str);
    }
}
