package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.viewer.SaveDualPhotoCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveDualShotPhotoMenuItem extends ViewerMenuItem {
    public SaveDualShotPhotoMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.save_as_new_file);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isShotMode("Dual capture")) {
            return false;
        }
        if (mediaItem.isLocal() || mediaItem.isStream()) {
            return true;
        }
        return false;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "SaveDualShotPhotoMenuItem failed: null item");
            return false;
        }
        new SaveDualPhotoCmd().execute(this.mEventContext, currentItem);
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://trash", "location://dynamicViewList", "location://AllDayTimeLapse", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new l(5));
    }
}
