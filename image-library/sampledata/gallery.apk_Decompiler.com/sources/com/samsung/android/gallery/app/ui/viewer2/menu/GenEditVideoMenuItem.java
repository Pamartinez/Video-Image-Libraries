package com.samsung.android.gallery.app.ui.viewer2.menu;

import J6.c;
import Q7.f;
import Q7.g;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.externals.StartVideoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GenEditVideoMenuItem extends EditMenuItem {
    public static final boolean SUPPORT = Features.isEnabled(Features.SUPPORT_AUDIO_ERASER);
    private final Runnable[] mAppTransitionCallback = {null, new f(this, 0), new f(this, 1)};

    public GenEditVideoMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.audio_eraser);
    }

    /* access modifiers changed from: private */
    /* renamed from: executeEditor */
    public void lambda$handleEditor$3(MediaItem mediaItem, Runnable runnable) {
        this.mEventContext.getBlackboard().publish("data://viewer_app_transition_callback", this.mAppTransitionCallback);
        if (!Features.isEnabled(Features.SUPPORT_APP_TRANSITION) && runnable != null) {
            runnable.run();
        }
        new StartVideoEditorCmd().addConfig(1).execute(this.mEventContext, mediaItem, Boolean.FALSE);
    }

    private boolean isSupportEdit(MediaItem mediaItem) {
        return mediaItem.isVideo();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEditor$1(Runnable runnable, Object obj) {
        if (SeApiCompat.isActivityResumed(BlackboardUtils.readActivity(this.mEventContext.getBlackboard()))) {
            handleEditor((MediaItem) obj, runnable);
        } else {
            Log.d(this.TAG, "failed to start editor -> activity is paused");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEditor$2(Runnable runnable, Object obj) {
        if (SeApiCompat.isActivityResumed(BlackboardUtils.readActivity(this.mEventContext.getBlackboard()))) {
            handleEditor((MediaItem) obj, runnable);
        } else {
            Log.d(this.TAG, "failed to start editor -> activity is paused");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (!SUPPORT || mediaItem == null || !isSupportEdit(mediaItem)) {
            return false;
        }
        return true;
    }

    public void handleEditor(MediaItem mediaItem, Runnable runnable) {
        if (this.mEventContext.getContext() == null) {
            Log.e(this.TAG, "set editor failed. null context");
        } else if (mediaItem.isSharing()) {
            executeAfterDownloadInSharing(mediaItem, runnable);
        } else if (isDownloadableNdeOriginal(mediaItem)) {
            executeAfterDownload(this.mEventContext, mediaItem, DownloadType.NDE_ORIGINAL, new g(this, runnable, 0));
        } else if (mediaItem.isCloudOnly()) {
            executeAfterDownload(this.mEventContext, mediaItem, DownloadType.EDIT, new g(this, runnable, 1));
        } else {
            ThreadUtil.runOnUiThread(new c(this, mediaItem, runnable, 27));
        }
    }

    public void postEditMenuAnalyticsLog(MediaItem mediaItem) {
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_FS_GEN_EDIT.toString());
    }

    public void prepareEditor() {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem != null) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, currentItem);
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.FALSE);
        }
    }

    public void setMenuAttribute() {
        super.setMenuAttribute();
        setIconResId(R.drawable.gallery_ic_detail_intelligence).exclude("location://sharing/albums/fileList", "location://mtp/fileList").validate(new b(this, 11));
    }
}
