package com.samsung.android.gallery.app.controller.internals;

import Fa.C0571z;
import K5.a;
import N3.c;
import O3.k;
import android.content.Intent;
import android.os.ResultReceiver;
import com.samsung.android.gallery.app.controller.BaseSelectedCommand;
import com.samsung.android.gallery.app.controller.DelegateHelper;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.service.message.DownloadMsgMgr;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadCmd extends BaseSelectedCommand {
    private MediaItem[] mItems = null;
    private ResultReceiver mResultReceiver = null;

    private ResultReceiver getResultReceiverForViewer(boolean z) {
        k kVar;
        MediaItem currentItem = getHandler().getCurrentItem();
        if (z) {
            kVar = new k(this, 0);
        } else {
            kVar = null;
        }
        return new DownloadResultReceiver(currentItem, kVar);
    }

    private boolean isVideoFromViewer() {
        MediaItem mediaItem;
        String locationKey = getHandler().getLocationKey();
        if (locationKey == null || !locationKey.contains("viewer") || (mediaItem = this.mItems[0]) == null || !mediaItem.isVideo()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getResultReceiverForViewer$1() {
        getHandler().getBlackboard().post("command://event/DataDirty", (Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$0(EventContext eventContext, MediaItem[] mediaItemArr) {
        super.onPreExecute(eventContext, mediaItemArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startDownloadIfNetworkAvailable$2() {
        List<MediaItem> flatten = MediaItemLoader.flatten(this.mItems, (Predicate<MediaItem>) new C0571z(5));
        if (flatten.isEmpty()) {
            showToast(DownloadMsgMgr.getDownloadNotStartedToastMessage(getContext()));
        } else {
            startDownloadService((MediaItem[]) flatten.toArray(new MediaItem[0]));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startDownloadIfNetworkAvailable$3(Boolean bool) {
        if (bool.booleanValue()) {
            SimpleThreadPool.getInstance().execute(new k(this, 1));
        }
    }

    private void startDownloadIfNetworkAvailable() {
        DelegateHelper.checkNetworkStatus(getHandler(), isVideoFromViewer(), new a(18, this));
    }

    private void startDownloadService(MediaItem[] mediaItemArr) {
        Log.d(this.TAG, "startDownloadService", Integer.valueOf(this.mItems.length), Integer.valueOf(mediaItemArr.length));
        getBlackboard().publish("data://user/selected", mediaItemArr);
        getBlackboard().postEvent(EventMessage.obtain(1003));
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.DownloadService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("location_key", getHandler().getLocationKey());
        intent.putExtra("download_result_receiver", this.mResultReceiver);
        getContext().startService(intent);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_DOWNLOAD.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            MediaItem[] mediaItemArr = objArr[0];
            this.mItems = mediaItemArr;
            if (mediaItemArr == null || mediaItemArr.length == 0) {
                Log.e(this.TAG, "unable to download. no item selected.");
                return;
            }
            if (objArr.length >= 2) {
                this.mResultReceiver = objArr[1];
            } else if (LocationKey.isContentViewer(getHandler().getLocationKey())) {
                this.mResultReceiver = getResultReceiverForViewer(getHandler().getLocationKey().startsWith("location://selectedItems"));
            }
            if (!PocFeatures.isEnabled(PocFeatures.WifiGalleryClient) || this.mItems[0].getStorageType() != StorageType.RemoteItem) {
                startDownloadIfNetworkAvailable();
            } else {
                startDownloadService(this.mItems);
            }
        }
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            super.onPreExecute(eventContext, objArr);
        } else if (eventContext.isSelectionMode()) {
            loadAndExecute(eventContext, new c(8, this, eventContext));
        } else {
            super.onPreExecute(eventContext, eventContext.getSelectedItems());
        }
    }
}
