package com.samsung.android.gallery.app.controller.viewer;

import B5.c;
import U9.b;
import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.abstraction.ViewerDownloadTask;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadNdeOriginalCmd extends BaseCommand {
    Consumer<Object> mConsumer;
    DownloadSyncMgr mDownloadSyncMgr;
    private MediaItem mItem;
    DownloadType mType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SingleDownloadTask extends ViewerDownloadTask {
        private final MediaItem mMediaItem;
        private MediaItem mResult = null;

        public SingleDownloadTask(Blackboard blackboard, MediaItem mediaItem, Consumer<Object> consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr) {
            super(blackboard, consumer, downloadType, downloadSyncMgr);
            this.mMediaItem = mediaItem;
        }

        private boolean downloadCloudNDEOriginalFile(MediaItem mediaItem) {
            if (this.mMediaItem != null) {
                return SamsungCloudCompat.downloadOriginalFile(AppResources.getAppContext(), this.mMediaItem.getCloudServerId(), mediaItem.getPath(), mediaItem.isVideo(), new FilesApi().getCloudOriginalBinaryHash(this.mMediaItem.getFileId()));
            }
            return false;
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
            this.mResult = arrayList.get(0);
        }

        public boolean prepareDownloaded() {
            return true;
        }

        public ArrayList<Uri> processDownload(Context context, long j2) {
            if (downloadCloudNDEOriginalFile(this.mMediaItem)) {
                return new ArrayList<>(List.of(ContentUri.getUri(this.mMediaItem)));
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataCompleted$1(MediaItem mediaItem, Consumer consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr) {
        new SingleDownloadTask(getBlackboard(), mediaItem, consumer, downloadType, downloadSyncMgr).execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(Boolean bool) {
        onDataCompleted(bool.booleanValue(), this.mItem, this.mConsumer, this.mType, this.mDownloadSyncMgr);
    }

    private void onDataCompleted(boolean z, MediaItem mediaItem, Consumer<Object> consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr) {
        if (z) {
            ThreadUtil.postOnUiThread(new c(this, mediaItem, consumer, downloadType, downloadSyncMgr, 12));
        } else {
            Log.e(this.TAG, "cancel or unexpected option selected [false]");
        }
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mItem = mediaItem;
        this.mConsumer = objArr[1];
        this.mType = objArr[2];
        this.mDownloadSyncMgr = objArr[3];
        if (mediaItem == null) {
            Log.e(this.TAG, "delivered item is null");
        } else {
            checkNetworkStatus(eventContext, new b(4, this));
        }
    }
}
