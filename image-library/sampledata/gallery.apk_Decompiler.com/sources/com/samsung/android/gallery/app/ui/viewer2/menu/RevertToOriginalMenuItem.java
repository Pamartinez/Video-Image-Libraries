package com.samsung.android.gallery.app.ui.viewer2.menu;

import K5.a;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.RevertOriginalImageCmd;
import com.samsung.android.gallery.app.controller.internals.RevertOriginalMotionPhotoCmd;
import com.samsung.android.gallery.app.controller.internals.RevertOriginalVideoCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.nondestruction.NonDestructionManager;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevertToOriginalMenuItem extends ViewerMenuItem {
    public RevertToOriginalMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.revert_to_original);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return supportedRestore(mediaItem);
    }

    private boolean supportedRestore(MediaItem mediaItem) {
        String path;
        if (mediaItem == null || (path = NonDestructionManager.getInstance().getPath(mediaItem.getOriginalFileHash())) == null || !FileUtils.exists(path)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void updateMenuDimEnabled(Object obj) {
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            this.mActionInvoker.invoke(ViewerAction.UPDATE_MENU_DIM, Integer.valueOf(getMenuId()), Boolean.TRUE);
        }
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "RemasterRestoreMenuItem failed: null item");
            return false;
        }
        this.mActionInvoker.invoke(ViewerAction.UPDATE_MENU_DIM, Integer.valueOf(getMenuId()), Boolean.FALSE);
        this.mActionInvoker.invoke(ViewerAction.FILM_STRIP_RESTORE, Boolean.TRUE);
        this.mActionInvoker.invoke(ViewerAction.STOP_GIF, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.CLOSE_MOTION_PHOTO, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.DISABLE_TEXT_EXTRACTION_VIEW, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.DISABLE_OBJECT_CAPTURE_VIEW, new Object[0]);
        if (currentItem.isVideo()) {
            new RevertOriginalVideoCmd().execute(this.mEventContext, currentItem);
        } else if (currentItem.isMotionPhoto()) {
            new RevertOriginalMotionPhotoCmd().addExecuteListener(new a(29, this)).execute(this.mEventContext, currentItem);
        } else {
            new RevertOriginalImageCmd().execute(this.mEventContext, currentItem);
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_SELECT_REVERT_TO_ORIGINAL.toString());
        return true;
    }

    public void setMenuAttribute() {
        exclude("location://mtp/fileList", "location://trash", "location://LongExposure").validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new b(this, 18));
    }
}
