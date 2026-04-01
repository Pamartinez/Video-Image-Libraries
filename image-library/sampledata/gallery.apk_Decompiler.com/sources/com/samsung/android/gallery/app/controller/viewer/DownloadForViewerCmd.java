package com.samsung.android.gallery.app.controller.viewer;

import B5.c;
import B8.k;
import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.abstraction.ViewerDownloadTask;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadForViewerCmd extends BaseCommand {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SingleDownloadTask extends ViewerDownloadTask {
        private final MediaItem mMediaItem;
        private MediaItem mResult = null;

        public SingleDownloadTask(Blackboard blackboard, MediaItem mediaItem, Consumer<Object> consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr) {
            super(blackboard, consumer, downloadType, downloadSyncMgr);
            this.mMediaItem = mediaItem;
        }

        private void downloadCloudNDEOriginalFile(MediaItem mediaItem) {
            if (this.mMediaItem != null) {
                SamsungCloudCompat.downloadOriginalFile(AppResources.getAppContext(), this.mMediaItem.getCloudServerId(), mediaItem.getPath(), mediaItem.isVideo(), new FilesApi().getCloudOriginalBinaryHash(this.mMediaItem.getFileId()));
            }
        }

        public boolean checkDownloaded(ArrayList<Uri> arrayList) {
            CloudErrorType parseOf = CloudErrorType.parseOf(arrayList.get(0));
            this.mErrorType = parseOf;
            if (parseOf == CloudErrorType.None) {
                return true;
            }
            return false;
        }

        public MediaItem getBaseMediaItem() {
            return this.mMediaItem;
        }

        public boolean isDownloaded() {
            if (this.mResult == null || this.mListener == null) {
                return false;
            }
            return true;
        }

        public void notifyResult() {
            Consumer<Object> consumer;
            MediaItem mediaItem = this.mResult;
            if (mediaItem != null && (consumer = this.mListener) != null) {
                consumer.accept(mediaItem);
            }
        }

        public void onDownLoadedItems(ArrayList<MediaItem> arrayList) {
            MediaItem mediaItem = arrayList.get(0);
            this.mResult = mediaItem;
            if (mediaItem != null && this.mType == DownloadType.EDIT && SdkConfig.atLeast(SdkConfig.SEM.T_MR1)) {
                downloadCloudNDEOriginalFile(this.mResult);
            }
        }

        public boolean prepareDownloaded() {
            MediaItem mediaItem;
            if (!SdkConfig.atLeast(SdkConfig.GED.R) || (mediaItem = this.mResult) == null) {
                return false;
            }
            prepareDownloadedInternal(mediaItem);
            return true;
        }

        public ArrayList<Uri> processDownload(Context context, long j2) {
            return processDownloadItem(context, this.mMediaItem, j2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataCompleted$1(MediaItem mediaItem, Consumer consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr) {
        new SingleDownloadTask(getBlackboard(), mediaItem, consumer, downloadType, downloadSyncMgr).execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem mediaItem, Consumer consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr, Boolean bool) {
        Consumer consumer2 = consumer;
        MediaItem mediaItem2 = mediaItem;
        boolean booleanValue = bool.booleanValue();
        DownloadSyncMgr downloadSyncMgr2 = downloadSyncMgr;
        onDataCompleted(booleanValue, mediaItem2, consumer2, downloadType, downloadSyncMgr2);
    }

    private void onDataCompleted(boolean z, MediaItem mediaItem, Consumer<Object> consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr) {
        if (z) {
            ThreadUtil.postOnUiThread(new c(this, mediaItem, consumer, downloadType, downloadSyncMgr, 11));
        } else {
            Log.e(this.TAG, "cancel or unexpected option selected [false]");
        }
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        Consumer consumer = objArr[1];
        DownloadType downloadType = objArr[2];
        DownloadSyncMgr downloadSyncMgr = objArr[3];
        if (mediaItem == null) {
            Log.e(this.TAG, "delivered item is null");
            return;
        }
        Log.d(this.TAG, "start download : ", mediaItem);
        checkNetworkStatus(eventContext, new k(this, mediaItem, consumer, downloadType, downloadSyncMgr, 3));
    }
}
