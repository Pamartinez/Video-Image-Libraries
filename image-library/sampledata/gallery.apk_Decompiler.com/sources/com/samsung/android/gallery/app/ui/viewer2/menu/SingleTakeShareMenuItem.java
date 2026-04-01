package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleTakeShareMenuItem extends ShareMenuItem {
    public SingleTakeShareMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !LocationKey.isCleanOut(str);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem[] mediaItemArr = (MediaItem[]) this.mEventContext.getSelectedItems().clone();
        if (mediaItemArr.length == 0) {
            Utils.showToast(this.mEventContext.getContext(), (int) R.string.select_pictures_to_share);
            return false;
        }
        publishPopoverInfo(getMenuId(), view);
        if (PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_SINGLE_TAKEN_SHARE_SHEET, new Object[0]);
        }
        new ShareViaCmd().addConfig(1).execute(this.mEventContext, mediaItemArr, null);
        return true;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_share).setFastOptionMenu().setShowAsActionFlag(2).exclude("location://mtp/fileList", "location://trash").validate(ViewerMenuItem.IS_GROUP_SHOT).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(new l(8));
    }
}
