package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.MoveFilesOnPrivateCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MoveToPrivateMenuItem extends ViewerMenuItem {
    public MoveToPrivateMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.move_to_private_album);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem != null) {
            new MoveFilesOnPrivateCmd().execute(this.mEventContext, "move_to_secured", new MediaItem[]{currentItem});
            return true;
        }
        Log.w(this.TAG, "Menu Select failed. null or wrong item");
        return false;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://trash", "location://dynamicViewList", "location://AllDayTimeLapse", "location://superSlowViewList", "location://highlightViewList", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_KNOX).validate(ViewerMenuItem.IS_NOT_AFW).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST);
    }
}
