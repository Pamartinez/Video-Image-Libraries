package com.samsung.android.gallery.app.service;

import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.app.service.abstraction.BaseTrashService;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.message.RestoreMsgMgr;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.module.trash.helper.TrashRestoreHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.scs.ai.language.a;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RestoreService extends BaseTrashService {
    private TrashRestoreHelper mHelper;
    private boolean mIsRestoreAll = false;
    private int mRestoreCount = 0;
    private int mTotalCount = 0;

    public RestoreService() {
        super("RestoreService", "com.samsung.android.gallery.app.service.RestoreService");
    }

    private String getNotificationMessage() {
        if (this.mTotalCount == 1) {
            return getString(R.string.restoring_one_item);
        }
        return getString(R.string.restoring_n_items, new Object[]{Integer.valueOf(this.mRestoreCount), Integer.valueOf(this.mTotalCount)});
    }

    public boolean addJobs(Intent intent) {
        int i2;
        MediaItem[] selectedItemList = getSelectedItemList(this.mIsRestoreAll);
        if (selectedItemList == null || selectedItemList.length == 0) {
            Log.w("RestoreService", "items are empty. adding failed.");
            this.mIsAbnormalFinished = true;
            return false;
        }
        boolean z = false;
        for (MediaItem mediaItem : selectedItemList) {
            if (mediaItem != null) {
                if (!Features.isEnabled(Features.SUPPORT_PPP_MENU) || !mediaItem.isPostProcessing()) {
                    addToQueue(new ProgressJob(mediaItem));
                } else {
                    Log.d("RestoreService", "skip ppp : " + mediaItem.getFileId());
                    z = true;
                }
            }
        }
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU) && z) {
            Context baseContext = getBaseContext();
            if (getQueueSize() > 0) {
                i2 = R.string.unsupported_file_deselected;
            } else {
                i2 = R.string.unsupported_file;
            }
            Utils.showToast(baseContext, i2);
        }
        if (isQueueEmpty()) {
            Log.w("RestoreService", "queue is empty. adding failed.");
            this.mIsAbnormalFinished = true;
            return false;
        }
        int queueSize = getQueueSize();
        this.mTotalCount = queueSize;
        this.mHelper.setProgressListener(queueSize, this);
        return true;
    }

    public void doJob(ProgressJob progressJob) {
        try {
            this.mRestoreCount++;
            MediaItem mediaItem = (MediaItem) progressJob.getParam(0);
            this.mNotificationTitle = TrashData.of(mediaItem).originalTitle;
            this.mNotificationMessage = getNotificationMessage();
            Log.d("RestoreService", "do job [" + this.mRestoreCount + "][" + this.mTotalCount + "]");
            this.mDialogHelper.updateDialog(this.mRestoreCount, this.mTotalCount, getPercentage());
            updateNotification();
            this.mHelper.restoreItem(mediaItem);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            ((a) this.mJobCallback).a();
            throw th;
        }
        ((a) this.mJobCallback).a();
    }

    public void forceRefreshData() {
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            BlackboardUtils.forceRefreshPicturesDataGlobal();
        } else {
            super.forceRefreshData();
        }
    }

    public String getChannelName() {
        return getString(R.string.restore);
    }

    public int getPercentage() {
        int i2 = this.mTotalCount;
        if (i2 == 0) {
            return 0;
        }
        return (this.mRestoreCount * 100) / i2;
    }

    public TrashLogType getTrashLogType() {
        return TrashLogType.RESTORE_MULTIPLE;
    }

    public void onContinueInternal() {
        this.mDialogHelper.showDialog(getString(R.string.restoring), this.mRestoreCount, this.mTotalCount, getPercentage());
    }

    public void onEndInternal() {
        if (this.mIsAbnormalFinished && this.mIsRestoreAll) {
            setCleanDelay(this.LATE_DELAY);
        }
        super.onEndInternal();
    }

    public boolean onPrepareInternal(Intent intent) {
        boolean onPrepareInternal = super.onPrepareInternal(intent);
        if (onPrepareInternal) {
            int i2 = 0;
            boolean booleanExtra = intent.getBooleanExtra("is_restore_all", false);
            this.mIsRestoreAll = booleanExtra;
            this.mHelper = TrashHelperFactory.getRestoreHelper(this, booleanExtra);
            if (this.mIsRestoreAll) {
                this.mTotalCount = getTrashTotalCount();
                DialogHelper dialogHelper = this.mDialogHelper;
                String string = getString(R.string.restoring);
                int i7 = this.mTotalCount;
                if (i7 != 0) {
                    i2 = 100 / i7;
                }
                dialogHelper.showDialog(string, 1, i7, i2);
            }
        }
        return onPrepareInternal;
    }

    public void onStartInternal() {
        int i2;
        super.onStartInternal();
        if (!this.mIsRestoreAll) {
            DialogHelper dialogHelper = this.mDialogHelper;
            String string = getString(R.string.restoring);
            int i7 = this.mTotalCount;
            if (i7 == 0) {
                i2 = 0;
            } else {
                i2 = 100 / i7;
            }
            dialogHelper.showDialog(string, 1, i7, i2);
        }
    }

    public void showResult() {
        boolean z;
        CloudErrorType errorType = this.mHelper.getErrorType();
        if (errorType == CloudErrorType.GDPR) {
            z = true;
        } else {
            z = false;
        }
        if (!this.mHelper.isSucceed() || z) {
            if (z) {
                SamsungCloudCompat.changeSyncState(this, false);
            }
            if (this.mHelper.getImageFailedCount() != 0 || this.mHelper.getVideoFailedCount() != 0) {
                Utils.showToast((Context) this, RestoreMsgMgr.getFailMessage(this, errorType, this.mHelper.isSucceed(), this.mHelper.getImageFailedCount(), this.mHelper.getVideoFailedCount()));
                return;
            }
            return;
        }
        Utils.showToast((Context) this, RestoreMsgMgr.getSuccessMessage(this, this.mHelper.getImageSucceedCount(), this.mHelper.getVideoSucceedCount()));
    }

    public boolean supportUpdateTrashState() {
        if (!this.mHelper.isTrashEmpty()) {
            return false;
        }
        this.mEmptyState = true;
        return true;
    }

    public TrashRestoreHelper getTrashHelper() {
        return this.mHelper;
    }
}
