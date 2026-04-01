package com.samsung.android.gallery.app.controller.viewer;

import A4.C0371f;
import B5.c;
import B8.k;
import Ca.d;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DirectorsViewEditCmd extends BaseCommand {
    private void executeAfterDownload(EventContext eventContext, MediaItem mediaItem, DownloadSyncMgr downloadSyncMgr, Consumer<Object> consumer) {
        DownloadSyncMgr downloadSyncMgr2 = downloadSyncMgr;
        ThreadUtil.postOnBgThread(new c(this, downloadSyncMgr2, mediaItem, eventContext, consumer, 10));
    }

    private void executorEdit(MediaItem mediaItem, Consumer<MediaItem> consumer) {
        DownloadSyncMgr downloadSyncMgr = new DownloadSyncMgr();
        EventContext handler = getHandler();
        new DirectorsViewChooseCmd().execute(handler, new k(this, mediaItem, handler, downloadSyncMgr, consumer, 2));
    }

    private void handleDualEdit(MediaItem mediaItem, EventContext eventContext, DownloadSyncMgr downloadSyncMgr) {
        Log.d(this.TAG, "handleDualEdit");
        new StartDirectorsViewDualEditCmd().execute(eventContext, new MediaItem[]{mediaItem, DirectorsViewCache.getInstance().findPairItem(mediaItem)}, downloadSyncMgr);
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_FS_DIRECTORS_VIEW);
    }

    private void handleSingleEdit(MediaItem mediaItem, EventContext eventContext, DownloadSyncMgr downloadSyncMgr, Consumer<MediaItem> consumer) {
        Log.d(this.TAG, "handleSingleEdit");
        if (mediaItem.isCloudOnly()) {
            executeAfterDownload(eventContext, mediaItem, downloadSyncMgr, new C0371f((Object) this, (Object) eventContext, (Object) consumer, 13));
        } else {
            ThreadUtil.runOnUiThread(new d(1, mediaItem, consumer));
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_FS_SINGLE_VIEW);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeAfterDownload$4(DownloadSyncMgr downloadSyncMgr, MediaItem mediaItem, EventContext eventContext, Consumer consumer) {
        if (downloadSyncMgr.valid(mediaItem.getFileId())) {
            new DownloadForViewerCmd().execute(eventContext, mediaItem, consumer, DownloadType.EDIT, downloadSyncMgr);
            return;
        }
        String str = this.TAG;
        Log.w(str, "skip downloading. [" + mediaItem.getFileId() + "] is not valid");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executorEdit$0(MediaItem mediaItem, EventContext eventContext, DownloadSyncMgr downloadSyncMgr, Consumer consumer, Integer num) {
        if (num.intValue() == R.string.dual_video) {
            handleDualEdit(mediaItem, eventContext, downloadSyncMgr);
        } else if (num.intValue() == R.string.single_video) {
            handleSingleEdit(mediaItem, eventContext, downloadSyncMgr, consumer);
        } else {
            String str = this.TAG;
            Log.e(str, "DirectorsViewEditCmd error=" + num);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onDownloaded */
    public void lambda$handleSingleEdit$1(EventContext eventContext, MediaItem mediaItem, Consumer<MediaItem> consumer) {
        String str = this.TAG;
        Log.d(str, "postDownloaded " + MediaItemUtil.getDebugLog(mediaItem));
        if (eventContext.getContext() == null) {
            Log.e(this.TAG, "handleDirectorsViewEdit failed. context");
            return;
        }
        DirectorsViewCache.getInstance().updateMediaItem(mediaItem);
        if (SeApiCompat.isActivityResumed(eventContext.getActivity())) {
            ThreadUtil.runOnUiThread(new d(2, mediaItem, consumer));
        } else {
            Log.d(this.TAG, "failed to start editor -> activity is paused");
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        executorEdit(objArr[0], objArr[1]);
    }
}
