package com.samsung.android.gallery.app.ui.menu.list;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.LeaveSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.RenameSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.SortBySharingDialogCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingsMenuHandler extends MenuHandler {
    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_create_shared_album:
                postEvent(eventContext, EventMessage.obtain(6003));
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_delete_shared_album_in_list:
                new DeleteSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_SHARED_VIEW_SPACE_DIALOG_DELETE, eventContext.getSelectedItems());
                return true;
            case R.id.action_leave_shared_album_in_list:
                new LeaveSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_SHARED_VIEW_SPACE_DIALOG_LEAVE, eventContext.getSelectedItems());
                return true;
            case R.id.action_rename_shared_album_in_list:
                new RenameSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_SHARED_VIEW_SPACE_DIALOG_RENAME, eventContext.getSelectedItems());
                return true;
            case R.id.action_sharings_invitations:
            case R.id.action_sharings_invitations_no_item:
                PreferenceCache.SharedNewNotificationBadge.setBoolean(false);
                Blackboard.postEventGlobal(EventMessage.obtain(6002));
                moveTo(eventContext, "location://sharing/albums/invitations");
                eventContext.postAnalyticsLog(AnalyticsScreenId.SCREEN_SHARED_VIEW_NORMAL.toString(), AnalyticsEventId.EVENT_SHARED_VIEW_INVITATIONS);
                return true;
            case R.id.action_sharings_storage_use:
                moveTo(eventContext, "location://sharing/albums/storageUse");
                eventContext.postAnalyticsLog(AnalyticsScreenId.SCREEN_SHARED_VIEW_NORMAL.toString(), AnalyticsEventId.EVENT_SHARED_VIEW_MENU_STORAGE_IN_USE);
                return true;
            case R.id.action_sort_by_sharing:
                new SortBySharingDialogCmd().execute(eventContext, new Object[0]);
                return true;
            default:
                return false;
        }
    }
}
