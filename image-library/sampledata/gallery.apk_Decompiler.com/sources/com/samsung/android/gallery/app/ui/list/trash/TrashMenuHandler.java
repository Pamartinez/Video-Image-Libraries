package com.samsung.android.gallery.app.ui.list.trash;

import T6.a;
import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ViewAsCmd;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedItemFromTrashCmd;
import com.samsung.android.gallery.app.controller.sharing.EmptySharedItemFromTrashCmd;
import com.samsung.android.gallery.app.controller.sharing.RestoreSharedItemFromTrashCmd;
import com.samsung.android.gallery.app.controller.trash.EmptyTrashCmd;
import com.samsung.android.gallery.app.controller.trash.RestoreTrashCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashMenuHandler extends MenuHandler {
    private void deleteFromTrash(EventContext eventContext) {
        if (isFamilySharedTrash(eventContext)) {
            new DeleteSharedItemFromTrashCmd().execute(eventContext, eventContext.getSelectedItems());
        } else if (eventContext.isSelectAll()) {
            new EmptyTrashCmd().execute(eventContext, null, Boolean.TRUE, Boolean.FALSE);
        } else {
            EmptyTrashCmd emptyTrashCmd = new EmptyTrashCmd();
            MediaItem[] selectedItems = eventContext.getSelectedItems();
            Boolean bool = Boolean.FALSE;
            emptyTrashCmd.execute(eventContext, selectedItems, bool, bool);
        }
    }

    private void emptyTrash(EventContext eventContext) {
        if (isFamilySharedTrash(eventContext)) {
            new EmptySharedItemFromTrashCmd().execute(eventContext, Integer.valueOf(eventContext.getTotalCount()));
        } else {
            new EmptyTrashCmd().execute(eventContext, null, Boolean.TRUE, Boolean.FALSE);
        }
    }

    private void handleCancel(EventContext eventContext) {
        ThreadUtil.postOnUiThreadDelayed(new a(eventContext, 0), 100);
    }

    private boolean isFamilySharedTrash(EventContext eventContext) {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) || !LocationKey.isFamilySharedTrash(eventContext.getLocationKey())) {
            return false;
        }
        return true;
    }

    private void restoreTrash(EventContext eventContext) {
        if (isFamilySharedTrash(eventContext)) {
            new RestoreSharedItemFromTrashCmd().execute(eventContext, eventContext.getSelectedItems());
        } else if (eventContext.isSelectAll()) {
            new RestoreTrashCmd().execute(eventContext, null, Boolean.TRUE);
        } else {
            new RestoreTrashCmd().execute(eventContext, eventContext.getSelectedItems(), Boolean.FALSE);
        }
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_cancel:
                handleCancel(eventContext);
                return true;
            case R.id.action_delete:
                deleteFromTrash(eventContext);
                return true;
            case R.id.action_empty:
                emptyTrash(eventContext);
                return true;
            case R.id.action_restore:
                restoreTrash(eventContext);
                return true;
            case R.id.action_select:
                eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_RECYCLE_BIN_MENU_EDIT);
                postEvent(eventContext, EventMessage.obtain(1002));
                return true;
            case R.id.action_view_as:
                new ViewAsCmd().execute(eventContext, new Object[0]);
                return true;
            default:
                return false;
        }
    }
}
