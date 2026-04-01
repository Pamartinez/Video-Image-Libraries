package com.samsung.android.gallery.app.service;

import A8.C0545b;
import android.content.Context;
import android.content.Intent;
import bc.C0584a;
import com.samsung.android.gallery.app.service.abstraction.BaseTrashService;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.service.message.DeleteMsgMgr;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.scs.ai.language.a;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteService extends BaseTrashService {
    protected ProgressJob mCurrentJob;
    private int mDeleteCount = 0;
    private String mDialogTitle;
    protected TrashDeleteHelper mHelper;
    private boolean mIsDirectDelete;
    private boolean mIsForceUpdateNeeded;
    private boolean mIsTrashEmptyBefore;
    private String mScreenId;
    private int mTotalCount = 0;
    protected final boolean mUseTrash = PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash);

    public DeleteService() {
        super("DeleteService", "com.samsung.android.gallery.app.service.DeleteService");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0051, code lost:
        r6.mHelper.addToSucceedForEmptyAlbum();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0056, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0035, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        com.samsung.android.gallery.support.utils.Log.e("DeleteService", "unable to delete auto album. albumId = " + r7.getAlbumID());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x003d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void deleteAutoAlbum(com.samsung.android.gallery.module.data.MediaItem r7) {
        /*
            r6 = this;
            java.lang.String r0 = "DeleteService"
            java.lang.String r1 = "unable to delete auto album. albumId = "
            java.lang.String r2 = "delete auto album : "
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x003d }
            r3.<init>()     // Catch:{ Exception -> 0x003d }
            int r4 = r7.getAlbumID()     // Catch:{ Exception -> 0x003d }
            long r4 = (long) r4     // Catch:{ Exception -> 0x003d }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x003d }
            r3.add(r4)     // Catch:{ Exception -> 0x003d }
            com.samsung.android.gallery.module.album.AutoAlbumHelper r4 = com.samsung.android.gallery.module.album.AutoAlbumHelper.getInstance()     // Catch:{ Exception -> 0x003d }
            int r3 = r4.deleteAutoAlbums(r3)     // Catch:{ Exception -> 0x003d }
            if (r3 <= 0) goto L_0x0037
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003d }
            r3.<init>(r2)     // Catch:{ Exception -> 0x003d }
            int r2 = r7.getAlbumID()     // Catch:{ Exception -> 0x003d }
            r3.append(r2)     // Catch:{ Exception -> 0x003d }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x003d }
            com.samsung.android.gallery.support.utils.Log.d(r0, r2)     // Catch:{ Exception -> 0x003d }
            goto L_0x0037
        L_0x0035:
            r7 = move-exception
            goto L_0x0051
        L_0x0037:
            com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper r6 = r6.mHelper
            r6.addToSucceedForEmptyAlbum()
            return
        L_0x003d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0035 }
            r2.<init>(r1)     // Catch:{ all -> 0x0035 }
            int r7 = r7.getAlbumID()     // Catch:{ all -> 0x0035 }
            r2.append(r7)     // Catch:{ all -> 0x0035 }
            java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x0035 }
            com.samsung.android.gallery.support.utils.Log.e(r0, r7)     // Catch:{ all -> 0x0035 }
            goto L_0x0037
        L_0x0051:
            com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper r6 = r6.mHelper
            r6.addToSucceedForEmptyAlbum()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.service.DeleteService.deleteAutoAlbum(com.samsung.android.gallery.module.data.MediaItem):void");
    }

    private void deleteEmptyAlbum(MediaItem mediaItem) {
        String path = mediaItem.getPath();
        if (path != null) {
            String str = File.separator;
            if (path.contains(str)) {
                String str2 = null;
                try {
                    str2 = path.substring(0, path.lastIndexOf(str));
                    if (AlbumHelper.getInstance().deleteEmptyAlbum(getApplicationContext(), mediaItem.getAlbumID(), str2, mediaItem.getPath()) > 0) {
                        this.mHelper.addToSucceedForEmptyAlbum();
                    }
                } catch (Exception unused) {
                    Log.e("DeleteService", "unable to delete empty album. [" + path + "]");
                }
                deleteEmptyDirectory(str2);
                return;
            }
        }
        Log.w("DeleteService", "album path is wrong, so skip. [" + path + "]");
    }

    private void deleteEmptyDirectory(String str) {
        if (str == null) {
            Log.w("DeleteService", "album path is null, so can not delete empty directory.");
            return;
        }
        SecureFile secureFile = new SecureFile(str);
        File[] listFiles = secureFile.listFiles();
        if (listFiles != null && listFiles.length == 0 && secureFile.delete()) {
            Log.d("DeleteService", "delete a empty folder");
        }
    }

    private void deleteFolder(MediaItem mediaItem) {
        if (!MediaItemUtil.isGroupedAlbum(mediaItem)) {
            return;
        }
        if (AlbumHelper.getInstance().removeFolder(getApplicationContext(), new ArrayList(Collections.singletonList(Integer.valueOf(mediaItem.getAlbumID()))), (List<Integer>) null)) {
            this.mHelper.addToSucceedForEmptyAlbum();
            return;
        }
        Log.e("DeleteService", "unable to delete folder. [" + mediaItem.getFolderName() + "]");
    }

    private String getNotificationMessage() {
        if (this.mTotalCount == 1) {
            return getString(R.string.deleting_one_item);
        }
        return getString(R.string.deleting_n_items, new Object[]{Integer.valueOf(this.mDeleteCount), Integer.valueOf(this.mTotalCount)});
    }

    private boolean isDeletedAll() {
        int succeedCount = this.mHelper.getSucceedCount();
        if (succeedCount == 0 || succeedCount != ArgumentsUtil.getArgValue(this.mLocationKey, "groupShotTotalCount", 0)) {
            return false;
        }
        return true;
    }

    private boolean isForceReloadNeeded() {
        return !SdkConfig.atLeast(SdkConfig.GED.Q);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$forceRefreshData$0() {
        this.mBlackboard.postEvent(EventMessage.obtain(3060, Boolean.FALSE));
    }

    public void addFolderItem(MediaItem mediaItem, ArrayList<Integer> arrayList) {
        addToQueue(new ProgressJob(mediaItem, Boolean.FALSE, Boolean.TRUE, Integer.valueOf(mediaItem.getAlbumType().toInt())));
        MediaItem[] itemsInFolder = mediaItem.getItemsInFolder();
        if (itemsInFolder.length != 0) {
            for (MediaItem mediaItem2 : itemsInFolder) {
                if (mediaItem2 != null) {
                    if (MediaItemUtil.isGroupedAlbum(mediaItem2)) {
                        addFolderItem(mediaItem2, arrayList);
                    } else if (!BucketUtils.isRecent(mediaItem2.getAlbumID()) && !BucketUtils.isFavourite(mediaItem2.getAlbumID())) {
                        arrayList.add(Integer.valueOf(mediaItem2.getAlbumID()));
                        addToQueue(new ProgressJob(mediaItem2, Boolean.TRUE, Boolean.FALSE, Integer.valueOf(mediaItem.getAlbumType().toInt())));
                    }
                }
            }
        }
    }

    public boolean addJobs(Intent intent) {
        MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.w("DeleteService", "items are empty. adding failed.");
            return false;
        }
        boolean isAlbums = isAlbums();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i7 = 0;
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                if (MediaItemUtil.isGroupedAlbum(mediaItem)) {
                    addFolderItem(mediaItem, arrayList);
                } else if (isAlbums) {
                    if (isValidAlbum(mediaItem)) {
                        arrayList.add(Integer.valueOf(mediaItem.getAlbumID()));
                        addToQueue(new ProgressJob(mediaItem, Boolean.TRUE, Boolean.FALSE, Integer.valueOf(mediaItem.getAlbumType().toInt())));
                    }
                } else if (mediaItem.isVideo()) {
                    i2++;
                    Boolean bool = Boolean.FALSE;
                    addToQueue(new ProgressJob(mediaItem, bool, bool, Integer.valueOf(AlbumType.None.toInt()), Boolean.valueOf(isBurstShotSelection(intent))));
                } else {
                    i7++;
                    Boolean bool2 = Boolean.FALSE;
                    addToQueue(new ProgressJob(mediaItem, bool2, bool2, Integer.valueOf(AlbumType.None.toInt()), Boolean.valueOf(isBurstShotSelection(intent))));
                }
            }
        }
        if (isQueueEmpty()) {
            Log.w("DeleteService", "queue is empty. adding failed.");
            return false;
        }
        addProgressInfo(intent, i7, i2, mediaItemArr, arrayList);
        return true;
    }

    public void addProgressInfo(Intent intent, int i2, int i7, MediaItem[] mediaItemArr, ArrayList<Integer> arrayList) {
        boolean z;
        this.mTotalCount = getQueueSize();
        if (!this.mUseTrash || this.mIsDirectDelete) {
            z = false;
        } else {
            z = true;
        }
        int i8 = i2;
        int i10 = i7;
        this.mDialogTitle = DeleteMsgMgr.getProgressTitleRes(this, z, isAlbums(), this.mTotalCount, i8, i10);
        this.mHelper.preDump(TrashLogType.DELETE_MULTIPLE_REQUEST, mediaItemArr.length, i8, i10, isAlbums(), this.mLocationKey);
        this.mHelper.setProgressListener(getTotalCount(intent, mediaItemArr, isAlbums(), arrayList), this);
    }

    public void doJob(ProgressJob progressJob) {
        try {
            this.mDeleteCount++;
            boolean z = false;
            MediaItem mediaItem = (MediaItem) progressJob.getParam(0);
            boolean booleanValue = ((Boolean) progressJob.getParam(1)).booleanValue();
            boolean booleanValue2 = ((Boolean) progressJob.getParam(2)).booleanValue();
            int intValue = ((Integer) progressJob.getParam(3)).intValue();
            if (progressJob.getParamSize() > 4) {
                z = ((Boolean) progressJob.getParam(4)).booleanValue();
            }
            this.mNotificationTitle = mediaItem.getTitle();
            this.mNotificationMessage = getNotificationMessage();
            Log.d("DeleteService", "do job [" + this.mDeleteCount + "][" + this.mTotalCount + "]");
            this.mCurrentJob = progressJob;
            updateDialog(this.mDeleteCount, this.mTotalCount, getPercentage());
            updateNotification();
            if (booleanValue) {
                if (AlbumType.isAutoAlbum(intValue)) {
                    deleteAutoAlbum(mediaItem);
                } else {
                    this.mHelper.deleteAlbum(mediaItem.getAlbumID());
                    deleteEmptyAlbum(mediaItem);
                }
            } else if (booleanValue2) {
                deleteFolder(mediaItem);
            } else {
                this.mHelper.deleteItem(mediaItem, !z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            ((a) this.mJobCallback).a();
            throw th;
        }
        ((a) this.mJobCallback).a();
    }

    public void forceRefreshData() {
        if (this.mBlackboard != null) {
            if (isViewer()) {
                if (isDeletedAll()) {
                    this.mBlackboard.postBroadcastEvent(EventMessage.obtain(3015));
                } else {
                    this.mBlackboard.postBroadcastEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_AGENT_WIFI_ONLY_MODEL));
                }
                ThreadUtil.postOnUiThreadDelayed(new C0584a(6, this), 300);
            } else if (this.mIsForceUpdateNeeded) {
                this.mBlackboard.postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
            }
            if (this.mHelper.isAbnormalRecordDeleteRequested() || isForceReloadNeeded()) {
                BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, !SdkConfig.atLeast(SdkConfig.GED.Q));
            }
        }
    }

    public String getChannelName() {
        return getString(R.string.delete);
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public int getPercentage() {
        return this.mHelper.getRate();
    }

    public int getTotalCount(Intent intent, MediaItem[] mediaItemArr, boolean z, ArrayList<Integer> arrayList) {
        int i2;
        if (z) {
            int albumTotalCount = this.mHelper.getAlbumTotalCount(arrayList.stream().mapToInt(new C0545b(20)).toArray());
            if (albumTotalCount > 0) {
                return albumTotalCount;
            }
            return this.mTotalCount;
        } else if (isBurstShotSelection(intent)) {
            return mediaItemArr.length;
        } else {
            int i7 = 0;
            for (MediaItem mediaItem : mediaItemArr) {
                if (mediaItem != null) {
                    if (!mediaItem.isSimilarShot() || mediaItem.getCount() != DbTable.UNLOADED) {
                        if (mediaItem.getCount() > 0) {
                            i2 = mediaItem.getCount();
                        } else {
                            i2 = 1;
                        }
                        i7 += i2;
                    } else {
                        i7 = SimilarPhotoHelper.getSimilarCount(mediaItem.getBucketID(), mediaItem.getGroupMediaId()) + i7;
                    }
                }
            }
            return i7;
        }
    }

    public TrashLogType getTrashLogType() {
        if (this.mUseTrash) {
            return TrashLogType.MOVE_TO_TRASH_MULTIPLE;
        }
        return TrashLogType.DELETE_MULTIPLE;
    }

    public boolean isAlbums() {
        if ("location://albums".equals(this.mLocationKey) || LocationKey.isFolder(this.mLocationKey) || LocationKey.isAllAlbumsMatch(this.mLocationKey)) {
            return true;
        }
        return false;
    }

    public boolean isBurstShotSelection(Intent intent) {
        if (intent == null || !intent.getBooleanExtra("is_burst_shot_selection", false)) {
            return false;
        }
        return true;
    }

    public boolean isValidAlbum(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return true;
        }
        if (mediaItem.isVirtualAlbum() || BucketUtils.isVirtualAlbum(mediaItem.getAlbumID())) {
            return false;
        }
        return true;
    }

    public boolean isViewer() {
        return LocationKey.isContentViewer(this.mLocationKey);
    }

    public void onCancelInternal() {
        super.onCancelInternal();
    }

    public void onContinueInternal() {
        showDialog(this.mDialogTitle, this.mDeleteCount, this.mTotalCount, getPercentage());
    }

    public void onEndInternal() {
        setCleanDelay(this.LATE_DELAY);
        super.onEndInternal();
    }

    public boolean onPrepareInternal(Intent intent) {
        this.mLocationKey = intent.getStringExtra("location_key");
        boolean z = false;
        this.mIsDirectDelete = intent.getBooleanExtra("is_direct_delete", false);
        this.mIsForceUpdateNeeded = intent.getBooleanExtra("is_force_update_needed", false);
        this.mScreenId = intent.getStringExtra("screenId");
        if (this.mHelper == null) {
            this.mHelper = TrashHelperFactory.getDeleteHelper(this);
        }
        if (this.mUseTrash && this.mHelper.isTrashEmpty()) {
            z = true;
        }
        this.mIsTrashEmptyBefore = z;
        return super.onPrepareInternal(intent);
    }

    public void onProgress(int i2) {
        this.mNotificationTitle = this.mHelper.getCurrentTitle();
        updateDialog(this.mDeleteCount, this.mTotalCount, i2);
        updateNotification();
    }

    public void onStartInternal() {
        super.onStartInternal();
        showDialog(this.mDialogTitle, 1, this.mTotalCount, getPercentage());
        this.mBlackboard.postEvent(EventMessage.obtain(3060, Boolean.TRUE));
    }

    public void showDialog(String str, int i2, int i7, int i8) {
        this.mDialogHelper.showDialog(str, i2, i7, i8);
    }

    public void showResult() {
        if (!this.mHelper.isSucceed() && !this.mHelper.isAbnormalRecordDeleted() && !isViewer()) {
            if (this.mUseTrash) {
                int deleteFailedToastMessage = DeleteMsgMgr.getDeleteFailedToastMessage(this.mHelper.getImageFailedCount(), this.mHelper.getVideoFailedCount());
                if (deleteFailedToastMessage != -1) {
                    Utils.showToast((Context) this, deleteFailedToastMessage);
                    return;
                }
                return;
            }
            Utils.showToast((Context) this, (int) R.string.delete_item_failed);
        }
    }

    public boolean supportUpdateTrashState() {
        if (!this.mUseTrash || !this.mIsTrashEmptyBefore) {
            return false;
        }
        this.mEmptyState = false;
        return true;
    }

    public void updateDialog(int i2, int i7, int i8) {
        this.mDialogHelper.updateDialog(i2, i7, i8);
    }

    public void updateTotalCount(int i2) {
        this.mTotalCount += i2;
    }

    public TrashDeleteHelper getTrashHelper() {
        return this.mHelper;
    }

    public DeleteService(String str, String str2) {
        super(str, str2);
    }
}
