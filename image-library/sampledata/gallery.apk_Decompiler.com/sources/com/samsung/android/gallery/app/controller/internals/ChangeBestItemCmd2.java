package com.samsung.android.gallery.app.controller.internals;

import A4.C0387w;
import Fa.C0571z;
import M5.a;
import O3.c;
import android.app.Activity;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.groupshot.GroupShotFormat;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.sec.android.gallery3d.R;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeBestItemCmd2 extends BaseCommand {
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
    public /* synthetic */ void lambda$onCompleted$4(MediaItem mediaItem) {
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
    public static /* synthetic */ MediaItem[] lambda$onExecute$0(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onExecute$1(MediaItem[] mediaItemArr, GroupShotFormat groupShotFormat, double[] dArr, FileItemInterface fileItemInterface) {
        if (fileItemInterface.equals(mediaItemArr[0])) {
            return Boolean.valueOf(groupShotFormat.setBestItemC2pa(getContext(), fileItemInterface, dArr));
        }
        return Boolean.valueOf(groupShotFormat.unsetBestItemC2pa(fileItemInterface));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$2(int i2, int i7, int i8) {
        Log.d(this.TAG, "updated result : ", Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$3(MediaItem[] mediaItemArr, Integer num, Integer num2) {
        onCompleted(true, mediaItemArr[0]);
    }

    private void onCompleted(boolean z, MediaItem mediaItem) {
        if (z) {
            ThreadUtil.postOnBgThreadDelayed(new a(13, this, mediaItem), (long) getScanAndSyncDelay());
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
        FileRedactorBuilder.edit((FileItemInterface[]) Arrays.stream(new MediaItem[]{mediaItemArr[0], mediaItem}).filter(new C0571z(4)).toArray(new C0387w(23))).setOperation(new c(this, mediaItemArr, groupShotFormat, location, 0)).setCallback(new K4.a(17, this)).ignoreGroup().withMediaScan(new A9.a(7, this, mediaItemArr)).build().run();
    }
}
