package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import com.samsung.android.gallery.app.controller.DelegateHelper;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.viewer.DownloadForViewerCmd;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.ViewerObjectThread;
import java.util.function.Consumer;
import o4.a;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CloudDownloader {
    private static final CharSequence TAG = "CloudDownloader";
    private final EventContext eventContext;
    private final MediaItem item;
    Consumer<Object> listener;
    private final ViewerObjectThread mThread;

    public CloudDownloader(ContentModel contentModel, ViewerObjectThread viewerObjectThread) {
        this.mThread = viewerObjectThread;
        this.item = contentModel.getMediaItem();
        this.eventContext = contentModel.getContainerModel().getEventContext();
    }

    public static boolean acceptable(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isCloudOnly() || TrashData.isTrash(mediaItem) || mediaItem.isSharing()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void afterConfirm(boolean z) {
        if (z && this.item != null) {
            this.mThread.runOnBgThread(new e(11, this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$afterConfirm$0(DownloadSyncMgr downloadSyncMgr) {
        new DownloadForViewerCmd().execute(this.eventContext, this.item, this.listener, DownloadType.SHOT_MODE, downloadSyncMgr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$afterConfirm$1() {
        DownloadSyncMgr downloadSyncMgr = new DownloadSyncMgr();
        if (downloadSyncMgr.valid(this.item.getFileId())) {
            this.mThread.runOnUiThread(new q6.e(22, this, downloadSyncMgr));
            return;
        }
        CharSequence charSequence = TAG;
        Log.w(charSequence, "skip downloading. [" + this.item.getFileId() + "] is not valid");
    }

    public void exec(Consumer<Object> consumer) {
        this.listener = consumer;
        DelegateHelper.checkNetworkStatus(this.eventContext, true, new a(20, this));
    }
}
