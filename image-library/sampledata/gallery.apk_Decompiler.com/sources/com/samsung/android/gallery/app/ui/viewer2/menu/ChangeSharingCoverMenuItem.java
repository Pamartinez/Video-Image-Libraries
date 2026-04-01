package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.ChangeSharedAlbumCoverCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeSharingCoverMenuItem extends ViewerMenuItem {
    public ChangeSharingCoverMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.set_as_cover_image);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !MediaItemMde.isSharingEditedItemUploading(mediaItem);
    }

    /* access modifiers changed from: private */
    public boolean supportChangeSharingCover(MediaItem mediaItem, String str) {
        if (mediaItem == null || str == null || !ArgumentsUtil.getArgValue(str, "owner", false)) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        ChangeSharedAlbumCoverCmd changeSharedAlbumCoverCmd = new ChangeSharedAlbumCoverCmd();
        EventContext eventContext = this.mEventContext;
        changeSharedAlbumCoverCmd.execute(eventContext, eventContext.getCurrentItem());
        return false;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).validate(ViewerMenuItem.IS_SHARING).validate(new a(4)).validate(new b(this, 1));
    }
}
