package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartVisionIntelligenceCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BixbyVisionMenuItem extends ViewerMenuItem {
    public BixbyVisionMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.vision_intelligence_title);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !MediaItemMde.isSharingEditedItemUploading(mediaItem);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isImage()) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "BixbyVision Menu Select failed: null item");
            return false;
        }
        new StartVisionIntelligenceCmd().execute(this.mEventContext, currentItem);
        return true;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_vision).setToolbarOnly().setShowAsActionFlag(2).exclude("location://trash", "location://family/shared/trash", "location://mtp/fileList", "location://dynamicViewList", "location://AllDayTimeLapse", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_POSTPROCESSING_FOR_PPPV3).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new a(1)).validate(new a(2)).validate(new a(3)).setSupportPpp(false);
    }
}
