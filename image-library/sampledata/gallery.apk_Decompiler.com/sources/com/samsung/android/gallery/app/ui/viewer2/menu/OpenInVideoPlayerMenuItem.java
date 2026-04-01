package com.samsung.android.gallery.app.ui.viewer2.menu;

import G7.e;
import M9.o;
import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayVideoCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OpenInVideoPlayerMenuItem extends ViewerMenuItem {
    public OpenInVideoPlayerMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.open_in_video_player);
    }

    private boolean connectMirroring() {
        if (!PreferenceFeatures.Remote.PLAY_VIDEO_IN_PRESENTATION || !RemoteDisplayState.getInstance().isConnected()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$2() {
        this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$3(Object obj, Bundle bundle) {
        ThreadUtil.runOnUiThread(new o(28, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isVideo() || connectMirroring()) {
            return false;
        }
        return true;
    }

    public boolean isRotateRecoveryRequired() {
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "OpenInVideoPlayer Menu Select failed: null item");
            return false;
        }
        if (SeApiCompat.isFreeFormMode(false) || SeApiCompat.isDexMode(this.mEventContext.getContext())) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_VIEW_PAUSE_FOR_MULTI_WINDOW, new Object[0]);
        } else {
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.FALSE);
        }
        this.mActionInvoker.invoke(ViewerAction.PREPARE_VIDEO_APP_TRANSITION, new Object[0]);
        this.mEventContext.subscribeInstant("lifecycle://on_activity_resume", new e(2, this));
        new PlayVideoCmd().execute(this.mEventContext, currentItem);
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).setExecutableOnScreenLocked().exclude("location://mtp/fileList", "location://dynamicViewList", "location://AllDayTimeLapse", "location://trash", "location://superSlowViewList", "location://highlightViewList", "location://LongExposure", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView", "location://private/trash").validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new b(this, 14)).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SINGLE_TAKE).validate(new c(11));
        if (PreferenceFeatures.Poc.VIDEO_PLAY_ICON_ON_TOOLBAR) {
            setToolbarOnly().setIconTintId(R.color.daynight_gallery_color_primary).setIconResId(R.drawable.gallery_ic_detail_play_menu).setShowAsActionFlag(2);
        }
    }
}
