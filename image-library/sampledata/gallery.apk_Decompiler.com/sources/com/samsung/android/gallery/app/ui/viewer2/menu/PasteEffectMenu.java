package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PasteEffectCmd;
import com.samsung.android.gallery.app.provider.LocalClipboard;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PasteEffectMenu extends ViewerMenuItem {
    public PasteEffectMenu(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.paste_edits);
    }

    private boolean isGif(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isGif()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !ViewerMenuItem.isCloudOnly(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return !isGif(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$3(MediaItem mediaItem, String str) {
        return supportPasteEffect();
    }

    private boolean supportPasteEffect() {
        try {
            return LocalClipboard.isFilterAvailable();
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "paste failed, error=", (Throwable) e);
            return false;
        }
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "null item");
            return false;
        }
        if (currentItem.isMotionPhoto()) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_STOP, new Object[0]);
        }
        new PasteEffectCmd().execute(this.mEventContext, new MediaItem[]{currentItem});
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://trash", "location://family/shared/trash", "location://dynamicViewList", "location://AllDayTimeLapse", "location://revitalized", "location://revitalized/fileList", "location://revitalized/single", "location://LongExposure", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_VIDEO).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(new c(15)).validate(new k(this, 0)).validate(new c(16)).validate(new k(this, 1));
    }
}
