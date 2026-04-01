package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.RobinServiceCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RobinMenuItem extends ViewerMenuItem {
    public RobinMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.robin_service);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isImage() || !mediaItem.isLocal()) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null || TextUtils.isEmpty(currentItem.getPath())) {
            Log.d(this.TAG, "RobinMenuItem failed: null item");
            return false;
        }
        new RobinServiceCmd().execute(this.mEventContext, currentItem);
        return true;
    }

    public void setMenuAttribute() {
        setFastOptionMenu().setShowAsActionFlag(2).setIconResId(R.drawable.ic_nano_banana).exclude("location://mtp/fileList", "location://trash").validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new c(29));
    }
}
