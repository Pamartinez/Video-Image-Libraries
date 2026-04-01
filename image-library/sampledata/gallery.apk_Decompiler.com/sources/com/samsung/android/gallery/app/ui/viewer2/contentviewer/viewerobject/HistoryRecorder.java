package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.os.Handler;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.debugger.HistoryLogger;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import u7.C0520a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HistoryRecorder extends ViewerObject {
    private static final Handler sHandler = ThreadUtil.createLazyHandler();
    private int mProcessLog = 7;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DonateRun implements Runnable {
        private final MediaItem item;
        private int mLogMask;

        public DonateRun(MediaItem mediaItem, int i2) {
            this.item = mediaItem;
            this.mLogMask = i2;
        }

        public void run() {
            MediaItem mediaItem = this.item;
            if (mediaItem == null) {
                return;
            }
            if (GppmHelper.SUPPORT_DONATION && (this.mLogMask & 4) > 0 && HistoryRecorder.isPpp(mediaItem.getSefFileType())) {
                GppmHelper.donate((FileItemInterface) this.item);
            } else if (HistoryRecorder.isLoggable(this.item)) {
                if (PreferenceFeatures.OneUi41.SUPPORT_DEEP_SKY_DONATION && (this.mLogMask & 1) > 0 && !this.item.isPostProcessing() && DeepSkyHelper.isDonationSupported()) {
                    DeepSkyHelper.donate(AppResources.getAppContext(), ContentUri.getUriString(this.item), this.item.getMimeType());
                }
                if (PreferenceFeatures.OneUi41.LOG_ACTION_VIEW && (this.mLogMask & 2) > 0) {
                    HistoryLogger.getInstance().addViewHistory(this.item.getFileId());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean isLoggable(MediaItem mediaItem) {
        if (mediaItem.isSharing() || mediaItem.isDrm() || mediaItem.isUriItem() || mediaItem.isPrivateItem()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static boolean isPpp(int i2) {
        if (i2 == 2928 || i2 == 2784) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        if (!sHandler.hasMessages(0)) {
            logViewHistory(1);
        }
    }

    public void addActionInvokeListener() {
        if ((this.mProcessLog & 1) > 0) {
            this.mActionInvoker.add(ViewerAction.DONATE_DEEP_SKY, new C0520a(2, this));
        }
    }

    public HistoryRecorder forGppm() {
        this.mProcessLog = 4;
        return this;
    }

    public void logViewHistory(int i2) {
        if (PreferenceFeatures.OneUi41.SUPPORT_DEEP_SKY_DONATION || PreferenceFeatures.OneUi41.LOG_ACTION_VIEW || GppmHelper.SUPPORT_DONATION) {
            Handler handler = sHandler;
            handler.removeCallbacksAndMessages((Object) null);
            handler.postDelayed(new DonateRun(this.mModel.getMediaItem(), i2), 1000);
        }
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        logViewHistory(this.mProcessLog);
    }

    public void onViewDetached() {
        sHandler.removeCallbacksAndMessages((Object) null);
        super.onViewDetached();
    }
}
