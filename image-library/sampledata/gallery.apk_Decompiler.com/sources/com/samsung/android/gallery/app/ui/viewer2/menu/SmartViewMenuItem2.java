package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.viewer.StartSmartViewCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmartViewMenuItem2 extends ViewerMenuItem {
    public SmartViewMenuItem2(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.smart_view);
    }

    private boolean isRemoteDisplayBusy() {
        return RemoteDisplayState.getInstance().isBackgroundPlaying();
    }

    public static boolean isRemoteDisplayConnected() {
        RemoteDisplayState instance = RemoteDisplayState.getInstance();
        if (instance.isConnected() || instance.isPauseConnected() || instance.isDlnaConnected()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !ViewerMenuItem.isDexMode(this.mEventContext.getContext());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$3(MediaItem mediaItem, String str) {
        return !isRemoteDisplayConnected();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$4(MediaItem mediaItem, String str) {
        return !MediaItemMde.isSharingEditedItemUploading(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$5(MediaItem mediaItem, String str) {
        return !isRemoteDisplayBusy();
    }

    public boolean isRotateRecoveryRequired() {
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "SmartView Menu Select failed: null item");
            return false;
        }
        new StartSmartViewCmd().execute(this.mEventContext, currentItem);
        return true;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_smart_view).setToolbarOnly().setShowAsActionFlag(2).validate(new o(this, 0)).validate(new l(9)).validate(new l(10)).validate(ViewerMenuItem.IS_NOT_DRM_WO_PRIVATE).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING_FOR_PPPV3).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(ViewerMenuItem.IS_NOT_PRIVATE_ITEM).validate(new l(11)).validate(new l(12)).validate(new o(this, 1)).setSupportPpp(true);
    }
}
