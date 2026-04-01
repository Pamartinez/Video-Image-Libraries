package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.RestoreSharedItemFromTrashCmd;
import com.samsung.android.gallery.app.controller.trash.RestoreSingleTrashCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashRestoreMenuItem extends ViewerMenuItem {
    public TrashRestoreMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.restore);
    }

    public boolean onMenuSelectInternal(View view) {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) || !LocationKey.isFamilySharedTrash(this.mEventContext.getLocationKey())) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_FORCE_SWIPE, new Object[0]);
            RestoreSingleTrashCmd restoreSingleTrashCmd = new RestoreSingleTrashCmd();
            EventContext eventContext = this.mEventContext;
            restoreSingleTrashCmd.execute(eventContext, eventContext.getCurrentItem());
            return true;
        }
        RestoreSharedItemFromTrashCmd restoreSharedItemFromTrashCmd = new RestoreSharedItemFromTrashCmd();
        EventContext eventContext2 = this.mEventContext;
        restoreSharedItemFromTrashCmd.execute(eventContext2, new MediaItem[]{eventContext2.getCurrentItem()});
        return true;
    }

    public void setMenuAttribute() {
        setFastOptionMenu().setIconResId(R.drawable.menu_bottombar_icon_restore).setShowAsActionFlag(6).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).include("location://trash", "location://family/shared/trash").setSupportPpp(true);
    }
}
