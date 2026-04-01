package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedAlbumItemCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedAlbumDeleteMenuItem extends ViewerMenuItem {
    public SharedAlbumDeleteMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.remove_from_album);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null || !currentItem.isSharing()) {
            Log.d(this.TAG, "Shared Album Delete Menu Select failed: item null or not sharing");
            return false;
        }
        new DeleteSharedAlbumItemCmd().execute(this.mEventContext, AnalyticsEventId.EVENT_DETAIL_VIEW_FS_DELETE, new MediaItem[]{currentItem});
        return true;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_delete).setFastOptionMenu().setShowAsActionFlag(2).exclude("location://mtp/fileList", "location://dynamicViewList", "location://revitalized", "location://AllDayTimeLapse", "location://family/shared/trash", "location://colorCorrectView").validate(ViewerMenuItem.IS_SHARING).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(ViewerMenuItem.IS_NOT_GROUP_SHOT).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_DELETABLE_FOR_SHARING).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_URI_ITEM);
    }
}
