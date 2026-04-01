package com.samsung.android.gallery.app.service;

import a6.C0419b;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.samsung.android.gallery.app.service.abstraction.BaseTrashService;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.service.message.EmptyMsgMgr;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.helper.PrivateTrashEmptyHelper;
import com.samsung.android.gallery.module.trash.helper.TrashEmptyHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.scs.ai.language.a;
import com.sec.android.gallery3d.R;
import z2.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmptyService extends BaseTrashService {
    private int mEmptyCount = 0;
    private TrashEmptyHelper mHelper;
    private boolean mInvolveCloud = false;
    private boolean mIsEmptyAll = false;
    private int mTotalCount = 0;

    public EmptyService() {
        super("EmptyService", "com.samsung.android.gallery.app.service.EmptyService");
    }

    private String getGDPRFailMessage(Context context, int i2, int i7) {
        int i8;
        int i10 = i2 + i7;
        if (i10 == i2) {
            i8 = R.plurals.can_not_delete_images_gdpr;
        } else if (i10 == i7) {
            i8 = R.plurals.can_not_delete_videos_gdpr;
        } else {
            i8 = R.plurals.can_not_delete_items_gdpr;
        }
        String cloudBrand = StringResources.getCloudBrand();
        return context.getResources().getQuantityString(i8, i10, new Object[]{Integer.valueOf(i10), cloudBrand, cloudBrand});
    }

    private String getNotificationMessage() {
        if (this.mTotalCount == 1) {
            return getString(R.string.deleting_one_item_from_trash);
        }
        return getString(R.string.deleting_n_items_from_trash, new Object[]{Integer.valueOf(this.mEmptyCount), Integer.valueOf(this.mTotalCount)});
    }

    private String getScreenId() {
        return AnalyticsScreenId.SCREEN_RECYCLE_BIN_VIEW_NORMAL.toString();
    }

    /* access modifiers changed from: private */
    public void onGoToOneDriveClicked(View view) {
        try {
            Intent intent = new Intent();
            intent.setPackage("com.microsoft.skydrive");
            intent.setAction("com.microsoft.skydrive.samsunghandleractivity.action.navigatetorecyclebin");
            intent.addFlags(268435456);
            AnalyticsLogger.getInstance().postLog(getScreenId(), AnalyticsEventId.EVENT_RECYCLE_BIN_EMPTY_GOTO_ONE_DRIVE.toString());
            startActivity(intent);
        } catch (Exception e) {
            Log.e("EmptyService", "launch one drive trash failed e=" + e.getMessage());
            Utils.showToast((Context) this, (int) R.string.activity_not_found);
        }
    }

    public boolean addJobs(Intent intent) {
        MediaItem[] selectedItemList = getSelectedItemList(this.mIsEmptyAll);
        if (selectedItemList == null || selectedItemList.length == 0) {
            Log.w("EmptyService", "items are empty. adding failed.");
            this.mIsAbnormalFinished = true;
            return false;
        }
        for (MediaItem mediaItem : selectedItemList) {
            if (mediaItem != null) {
                addToQueue(new ProgressJob(mediaItem));
                if (!this.mInvolveCloud && mediaItem.isCloud()) {
                    this.mInvolveCloud = true;
                }
            }
        }
        if (isQueueEmpty()) {
            Log.w("EmptyService", "queue is empty. adding failed.");
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
            this.mEmptyCount++;
            MediaItem mediaItem = (MediaItem) progressJob.getParam(0);
            this.mNotificationTitle = TrashData.of(mediaItem).originalTitle;
            this.mNotificationMessage = getNotificationMessage();
            Log.d("EmptyService", "do job [" + this.mEmptyCount + "][" + this.mTotalCount + "]");
            this.mDialogHelper.updateDialog(this.mEmptyCount, this.mTotalCount, getPercentage());
            updateNotification();
            this.mHelper.emptyItem(mediaItem);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            ((a) this.mJobCallback).a();
            throw th;
        }
        ((a) this.mJobCallback).a();
    }

    public String getChannelName() {
        return getString(R.string.empty_trash);
    }

    public int getPercentage() {
        int i2 = this.mTotalCount;
        if (i2 == 0) {
            return 0;
        }
        return (this.mEmptyCount * 100) / i2;
    }

    public TrashLogType getTrashLogType() {
        return TrashLogType.EMPTY_MULTIPLE;
    }

    public void onContinueInternal() {
        this.mDialogHelper.showDialog(getString(R.string.deleting), this.mEmptyCount, this.mTotalCount, getPercentage());
    }

    public void onEndInternal() {
        if (this.mIsAbnormalFinished && this.mIsEmptyAll) {
            setCleanDelay(this.LATE_DELAY);
        }
        super.onEndInternal();
    }

    public boolean onPrepareInternal(Intent intent) {
        boolean onPrepareInternal = super.onPrepareInternal(intent);
        if (onPrepareInternal) {
            int i2 = 0;
            this.mIsEmptyAll = intent.getBooleanExtra("is_empty_all", false);
            if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !LocationKey.isPrivateTrash(this.mLocationKey)) {
                this.mHelper = TrashHelperFactory.getEmptyHelper(this, this.mIsEmptyAll);
            } else {
                this.mHelper = new PrivateTrashEmptyHelper(this, this.mIsEmptyAll);
            }
            if (this.mIsEmptyAll) {
                try {
                    int trashTotalCount = getTrashTotalCount();
                    this.mTotalCount = trashTotalCount;
                    if (trashTotalCount != 0) {
                        i2 = 100 / trashTotalCount;
                    }
                    this.mDialogHelper.showDialog(getString(R.string.deleting), 1, this.mTotalCount, i2);
                    return onPrepareInternal;
                } catch (ArithmeticException e) {
                    Log.w("EmptyService", "onPrepareInternal [" + this.mTotalCount + "] " + e.getMessage());
                }
            }
        }
        return onPrepareInternal;
    }

    public void onStartInternal() {
        int i2;
        super.onStartInternal();
        if (!this.mIsEmptyAll) {
            int i7 = this.mTotalCount;
            if (i7 == 0) {
                i2 = 0;
            } else {
                i2 = 100 / i7;
            }
            this.mDialogHelper.showDialog(getString(R.string.deleting), 1, this.mTotalCount, i2);
        }
    }

    public void showResult() {
        Activity readActivity;
        if (this.mHelper.isGDPRErrorHappened()) {
            Utils.showToast((Context) this, getGDPRFailMessage(this, this.mHelper.getGDPRImageFailedCount(), this.mHelper.getGDPRVideoFailedCount()));
            SamsungCloudCompat.changeSyncState(this, false);
        } else if (!this.mHelper.isSucceed()) {
            Utils.showToast((Context) this, (int) R.string.delete_item_failed);
        } else if (this.mInvolveCloud && CloudStateCompat.isEnabled() && !CloudStateCompat.isNewGalleryAvailable() && (readActivity = BlackboardUtils.readActivity(this.mBlackboard)) != null) {
            r j2 = r.j(readActivity.findViewById(16908290), -1, 0, false, EmptyMsgMgr.getCloudToastMessage(this, this.mTotalCount, this.mHelper.getImageSucceedCount(), this.mHelper.getVideoSucceedCount(), CloudStateCompat.isNewGalleryAvailable()));
            j2.k(getString(R.string.go_to_onedrive_action), new C0419b(5, this));
            j2.l();
        }
    }

    public void startActivity(Intent intent) {
        Activity readActivity = BlackboardUtils.readActivity(this.mBlackboard);
        if (readActivity != null) {
            readActivity.startActivity(intent);
        } else {
            super.startActivity(intent);
        }
    }

    public boolean supportUpdateTrashState() {
        if (!this.mHelper.isTrashEmpty()) {
            return false;
        }
        this.mEmptyState = true;
        return true;
    }

    public TrashEmptyHelper getTrashHelper() {
        return this.mHelper;
    }
}
