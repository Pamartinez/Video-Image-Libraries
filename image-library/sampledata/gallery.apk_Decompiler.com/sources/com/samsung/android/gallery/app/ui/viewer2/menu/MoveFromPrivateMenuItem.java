package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.MoveFilesOnPrivateCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MoveFromPrivateMenuItem extends ViewerMenuItem {
    public MoveFromPrivateMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.move);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isPrivateItem()) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null || !currentItem.isPrivateItem()) {
            Log.w(this.TAG, "Menu Select failed. null or wrong item");
            return false;
        }
        this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(3015));
        new MoveFilesOnPrivateCmd().execute(this.mEventContext, "move_from_secured", new MediaItem[]{currentItem});
        return true;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_bottombar_move).setIconTintId(R.color.daynight_gallery_color_primary).setFastOptionMenu().setShowAsActionFlag(2).include("location://albums/private/fileList").validate(new c(7));
    }
}
