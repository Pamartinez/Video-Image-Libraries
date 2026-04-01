package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.viewer.SaveSingleTakenShotCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleTakeSaveMenuItem extends ViewerMenuItem {
    public SingleTakeSaveMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.save);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem[] mediaItemArr = (MediaItem[]) this.mEventContext.getSelectedItems().clone();
        if (mediaItemArr.length == 0) {
            Utils.showToast(this.mEventContext.getContext(), (int) R.string.select_pictures_to_save);
            return false;
        }
        SaveSingleTakenShotCmd saveSingleTakenShotCmd = new SaveSingleTakenShotCmd();
        EventContext eventContext = this.mEventContext;
        saveSingleTakenShotCmd.execute(eventContext, mediaItemArr, Integer.valueOf(eventContext.getAllItems().length));
        return true;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_save).setFastOptionMenu().setShowAsActionFlag(2).setExecutableOnScreenLocked().validate(ViewerMenuItem.IS_GROUP_SHOT).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_BROKEN);
    }
}
