package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoExportCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExportMotionPhotoMenuItem extends ViewerMenuItem {
    public ExportMotionPhotoMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.export_as);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return !Features.isEnabled(Features.IS_GED);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$2(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isMotionPhoto()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$3(MediaItem mediaItem, String str) {
        return !MediaItemUtil.isMotionPhotoAutoPlayViewMode(mediaItem);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$4(MediaItem mediaItem, String str) {
        return !ViewerMenuItem.isCloudOnly(mediaItem);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "Export Menu Select failed: null item");
            return false;
        }
        this.mActionInvoker.invoke(ViewerAction.CLOSE_MOTION_PHOTO, new Object[0]);
        publishPopoverInfo(getMenuId(), view);
        new MotionPhotoExportCmd().execute(this.mEventContext, currentItem);
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://trash", "location://mtp/fileList", "location://LongExposure").validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(new a(27)).validate(new a(28)).validate(new a(29)).validate(new c(0)).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new c(1));
    }
}
