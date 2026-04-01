package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.CreateGifCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateGifMenuItem extends ViewerMenuItem {
    public CreateGifMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.create_gif_menu);
    }

    private Object getItemsForCreateGif() {
        MediaItem[] allItems = this.mEventContext.getAllItems();
        if (allItems.length <= 100) {
            return allItems;
        }
        MediaItem[] mediaItemArr = new MediaItem[100];
        float length = ((float) allItems.length) / 100.0f;
        for (int i2 = 0; i2 < 100; i2++) {
            mediaItemArr[i2] = allItems[(int) (((float) i2) * length)];
        }
        return mediaItemArr;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isGroupShot()) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "CreateGif Menu Select failed: null item");
            return false;
        }
        new CreateGifCmd(ViewerMenuItem.isGroupShot(currentItem)).execute(this.mEventContext, getItemsForCreateGif());
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList").validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new a(13)).validate(ViewerMenuItem.IS_NOT_SINGLE_TAKE).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_BROKEN);
    }
}
