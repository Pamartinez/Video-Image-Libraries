package com.samsung.android.gallery.app.controller.viewer;

import B5.c;
import B8.k;
import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.abstraction.ViewerDownloadTask;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiDownloadForViewerCmd extends BaseCommand {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MultiDownloadTask extends ViewerDownloadTask {
        private final MediaItem[] mMediaItems;
        private ArrayList<MediaItem> mResultItems;

        public /* synthetic */ MultiDownloadTask(Blackboard blackboard, MediaItem[] mediaItemArr, Consumer consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr, int i2) {
            this(blackboard, mediaItemArr, consumer, downloadType, downloadSyncMgr);
        }

        public boolean checkDownloaded(ArrayList<Uri> arrayList) {
            Iterator<Uri> it = arrayList.iterator();
            while (it.hasNext()) {
                CloudErrorType parseOf = CloudErrorType.parseOf(it.next());
                if (parseOf != CloudErrorType.None) {
                    this.mErrorType = parseOf;
                    return false;
                }
            }
            if (this.mErrorType == CloudErrorType.None) {
                return true;
            }
            return false;
        }

        public MediaItem getBaseMediaItem() {
            return this.mMediaItems[0];
        }

        public boolean isDownloaded() {
            ArrayList<MediaItem> arrayList = this.mResultItems;
            if (arrayList == null || arrayList.isEmpty() || this.mListener == null) {
                return false;
            }
            return true;
        }

        public void notifyResult() {
            if (isDownloaded()) {
                this.mListener.accept(this.mResultItems.toArray(new MediaItem[0]));
            }
        }

        public void onDownLoadedItems(ArrayList<MediaItem> arrayList) {
            this.mResultItems = arrayList;
        }

        public boolean prepareDownloaded() {
            ArrayList<MediaItem> arrayList;
            if (!SdkConfig.atLeast(SdkConfig.GED.R) || (arrayList = this.mResultItems) == null || arrayList.isEmpty()) {
                return false;
            }
            Iterator<MediaItem> it = this.mResultItems.iterator();
            while (it.hasNext()) {
                prepareDownloadedInternal(it.next());
            }
            return true;
        }

        public ArrayList<Uri> processDownload(Context context, long j2) {
            ArrayList<Uri> arrayList = new ArrayList<>();
            for (MediaItem processDownloadItem : this.mMediaItems) {
                ArrayList<Uri> processDownloadItem2 = processDownloadItem(context, processDownloadItem, j2);
                if (processDownloadItem2 != null && !processDownloadItem2.isEmpty()) {
                    arrayList.add(processDownloadItem2.get(0));
                }
            }
            return arrayList;
        }

        private MultiDownloadTask(Blackboard blackboard, MediaItem[] mediaItemArr, Consumer<Object> consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr) {
            super(blackboard, consumer, downloadType, downloadSyncMgr);
            this.mMediaItems = mediaItemArr;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataCompleted$1(MediaItem[] mediaItemArr, Consumer consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr) {
        new MultiDownloadTask(getBlackboard(), mediaItemArr, consumer, downloadType, downloadSyncMgr, 0).execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem[] mediaItemArr, Consumer consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr, Boolean bool) {
        Consumer consumer2 = consumer;
        MediaItem[] mediaItemArr2 = mediaItemArr;
        boolean booleanValue = bool.booleanValue();
        DownloadSyncMgr downloadSyncMgr2 = downloadSyncMgr;
        onDataCompleted(booleanValue, mediaItemArr2, consumer2, downloadType, downloadSyncMgr2);
    }

    private void onDataCompleted(boolean z, MediaItem[] mediaItemArr, Consumer<Object> consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr) {
        if (z) {
            ThreadUtil.postOnUiThread(new c(this, mediaItemArr, consumer, downloadType, downloadSyncMgr, 13));
        } else {
            Log.e(this.TAG, "cancel or unexpected option selected [false]");
        }
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr = objArr[0];
        Consumer consumer = objArr[1];
        DownloadType downloadType = objArr[2];
        DownloadSyncMgr downloadSyncMgr = objArr[3];
        if (mediaItemArr == null) {
            Log.e(this.TAG, "delivered item is null");
            return;
        }
        checkNetworkStatus(eventContext, new k(this, mediaItemArr, consumer, downloadType, downloadSyncMgr, 4));
    }
}
