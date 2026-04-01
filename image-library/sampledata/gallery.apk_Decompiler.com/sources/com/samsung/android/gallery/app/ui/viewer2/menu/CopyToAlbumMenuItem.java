package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CopyToAlbumMenuItem extends ViewerMenuItem {
    public CopyToAlbumMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.copy_to_album);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !isFromFlipWidget();
    }

    public boolean onMenuSelectInternal(View view) {
        new FileOpCmd().execute(this.mEventContext, FileOpCmdHelper.CmdType.TYPE_CHOICE_ALBUM, "copy");
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).include("location://albums/fileList").validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_HAVE_QUICK_VIEW_ARGUMENT).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_UPSM).validate(new b(this, 5));
    }
}
