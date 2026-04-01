package com.samsung.android.gallery.app.controller.internals;

import M5.a;
import O3.b;
import android.app.Activity;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.groupshot.GroupShotFormat;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeBestItemCmd extends BaseCommand {
    private void forceReload(MediaItem mediaItem) {
        if (mediaItem.isSimilarShot()) {
            BlackboardUtils.forceRefreshPicturesData(getBlackboard(), true);
        }
    }

    private double[] getLocation(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || !MapUtil.isValidLocation(mediaItem.getLatitude(), mediaItem.getLongitude())) {
            return new double[]{mediaItem2.getLatitude(), mediaItem2.getLongitude()};
        }
        return new double[]{mediaItem.getLatitude(), mediaItem.getLongitude()};
    }

    private int getScanAndSyncDelay() {
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            return 1000;
        }
        return 100;
    }

    private boolean isSameItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem.getFileId() != mediaItem2.getFileId()) {
            return false;
        }
        return true;
    }

    private boolean isTableState(Activity activity) {
        FoldStateManager instance = FoldStateManager.getInstance(Blackboard.getInstance(activity.toString()));
        if (instance == null || !instance.isTableMode() || DeviceInfo.isDexMode(activity) || SystemUi.isInMultiWindowMode(activity)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCompleted$2(MediaItem mediaItem) {
        int i2;
        hideProgress();
        Blackboard.postBroadcastEventGlobal(EventMessage.obtain(1033, 0, 0, Long.valueOf(mediaItem.getGroupMediaId())));
        if (mediaItem.isImage()) {
            i2 = R.string.picture_set_as_best_shot;
        } else {
            i2 = R.string.clip_set_as_best_shot;
        }
        showToast(i2);
        stopSelectionMode(getHandler());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem[] mediaItemArr, boolean z) {
        onCompleted(z, mediaItemArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$onExecute$1(GroupShotFormat groupShotFormat, MediaItem[] mediaItemArr, MediaItem mediaItem, double[] dArr, ThreadPool.JobContext jobContext) {
        groupShotFormat.setCompletionListener(new b(0, this, mediaItemArr));
        groupShotFormat.updateBestItem(getHandler().getContext(), mediaItemArr[0], mediaItem, dArr);
        return null;
    }

    private void onCompleted(boolean z, MediaItem mediaItem) {
        if (z) {
            ThreadUtil.postOnBgThreadDelayed(new a(12, this, mediaItem), (long) getScanAndSyncDelay());
            if (mediaItem.isVideo()) {
                getBlackboard().post("command://event/DataDirty", (Object) null);
            }
            getBlackboard().postEvent(EventMessage.obtain(3032, mediaItem));
            forceReload(mediaItem);
            return;
        }
        hideProgress();
    }

    private void stopSelectionMode(EventContext eventContext) {
        if (eventContext != null && eventContext.isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    public boolean checkFlexMode() {
        return isTableState(getActivity());
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SET_AS_BEST_SHOT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem;
        Object obj;
        MediaItem[] mediaItemArr = objArr[0];
        MediaItem mediaItem2 = objArr[1];
        if (mediaItem2 == null || mediaItem2.getBestImage() != 1) {
            mediaItem = null;
        } else {
            mediaItem = mediaItem2;
        }
        if (mediaItemArr.length != 1 || isSameItem(mediaItem, mediaItemArr[0])) {
            Utils.showToast(getContext(), (int) R.string.pick_out_the_best_shot);
            return;
        }
        MediaItem mediaItem3 = mediaItemArr[0];
        if (mediaItem3 == null || mediaItem3.isCloudOnly() || (mediaItem != null && mediaItem.isCloudOnly())) {
            Log.e(this.TAG, "Cloud only item is selected or targetItem is null");
            stopSelectionMode(eventContext);
            return;
        }
        double[] location = getLocation(mediaItem2, mediaItemArr[0]);
        GroupShotFormat groupShotFormat = objArr[2];
        String str = this.TAG;
        if (mediaItem == null) {
            obj = "null";
        } else {
            obj = Long.valueOf(mediaItem.getFileId());
        }
        Log.d(str, "request", obj, Long.valueOf(mediaItemArr[0].getFileId()));
        showProgress(true);
        ThreadPool.getInstance().submit(new J3.a(this, groupShotFormat, mediaItemArr, mediaItem, location, 1));
    }
}
