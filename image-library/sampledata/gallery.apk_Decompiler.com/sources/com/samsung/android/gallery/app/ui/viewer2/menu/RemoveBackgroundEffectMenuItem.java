package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.RemoveBackgroundEffectInfoCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.type.ShotModeType;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveBackgroundEffectMenuItem extends ViewerMenuItem {
    public RemoveBackgroundEffectMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.remove_background_effect_info);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || !ShotModeType.isLiveFocus(mediaItem.getSefFileType())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return !ViewerMenuItem.isCloudOnly(mediaItem);
    }

    public boolean onMenuSelectInternal(View view) {
        RemoveBackgroundEffectInfoCmd removeBackgroundEffectInfoCmd = new RemoveBackgroundEffectInfoCmd();
        EventContext eventContext = this.mEventContext;
        removeBackgroundEffectInfoCmd.execute(eventContext, new MediaItem[]{eventContext.getCurrentItem()}, null);
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://trash", "location://mtp/fileList", "location://LongExposure").validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new c(22)).validate(new c(23));
    }
}
