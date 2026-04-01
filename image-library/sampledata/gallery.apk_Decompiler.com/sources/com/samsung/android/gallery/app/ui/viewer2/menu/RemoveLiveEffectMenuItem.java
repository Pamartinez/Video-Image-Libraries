package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.RemoveLiveEffectCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveLiveEffectMenuItem extends ViewerMenuItem {
    public RemoveLiveEffectMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.remove_live_effect);
    }

    private boolean isLiveEffectImage(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isLocal() || !mediaItem.isImage() || !mediaItem.isLiveEffect()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return isLiveEffectImage(mediaItem);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null || TextUtils.isEmpty(currentItem.getPath())) {
            Log.d(this.TAG, "Removing Live effect failed: null item");
            return false;
        }
        new RemoveLiveEffectCmd().execute(this.mEventContext, currentItem);
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://trash", "location://family/shared/trash", "location://mtp/fileList", "location://LongExposure").validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new c(28)).validate(new b(this, 17));
    }
}
