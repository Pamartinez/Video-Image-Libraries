package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.content.Context;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupPanelSingleTakeShareMenuItem extends SingleTakeShareMenuItem {
    public GroupPanelSingleTakeShareMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker);
    }

    private boolean isSupportedToolbarMenu() {
        FoldStateManager instance = FoldStateManager.getInstance(this.mEventContext.getBlackboard());
        if (instance == null || !instance.isTableMode() || !ResourceCompat.isLandscape((Context) this.mEventContext.getActivity())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !LocationKey.isCleanOut(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return isSupportedToolbarMenu();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$2(MediaItem mediaItem, String str) {
        if (this.mEventContext.getSelectedItems().length > 0) {
            return true;
        }
        return false;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_share).setFastOptionMenu().setShowAsActionFlag(2).exclude("location://mtp/fileList", "location://trash").validate(ViewerMenuItem.IS_GROUP_SHOT).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(new c(5)).validate(new f(this, 0)).validate(new f(this, 1));
    }
}
