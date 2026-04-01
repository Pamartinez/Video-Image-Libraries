package com.samsung.android.gallery.app.ui.menu.list;

import T6.a;
import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.ChangeAlbumCoverCmd;
import com.samsung.android.gallery.app.controller.album.CreateAlbumCmd;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import com.samsung.android.gallery.app.controller.album.FolderGroupCmd;
import com.samsung.android.gallery.app.controller.album.LockAlbumCmd;
import com.samsung.android.gallery.app.controller.album.RenameAlbumCmd;
import com.samsung.android.gallery.app.controller.externals.FetchContentsForKnoxCmd;
import com.samsung.android.gallery.app.controller.externals.ShareAlbumCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteAlbumCmd;
import com.samsung.android.gallery.app.controller.internals.ViewAsCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FolderMenuHandler extends MenuHandler {
    private MediaItem getCurrentAlbum(EventContext eventContext) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        if (selectedItems == null || selectedItems.length != 1) {
            return null;
        }
        return selectedItems[0];
    }

    private void handleCancel(EventContext eventContext) {
        ThreadUtil.postOnUiThreadDelayed(new a(eventContext, 1), 100);
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_album_view_new_album:
                MediaItem currentItem = eventContext.getCurrentItem();
                if (currentItem == null) {
                    return true;
                }
                new CreateAlbumCmd().execute(eventContext, Integer.valueOf(currentItem.getFolderID()), currentItem.getFolderName(), Boolean.FALSE);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_cancel:
                handleCancel(eventContext);
                return true;
            case R.id.action_change_cover_image:
                new ChangeAlbumCoverCmd().execute(eventContext, getCurrentAlbum(eventContext));
                return true;
            case R.id.action_delete_album_in_list:
                new DeleteAlbumCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_folder_add_album:
                postEvent(eventContext, EventMessage.obtain(2007));
                return true;
            case R.id.action_folder_add_folder:
                new CreateFolderCmd().execute(eventContext, null, CreateFolderCmd.Type.CREATE_EMPTY, Boolean.TRUE, eventContext.getCurrentItem());
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_folder_grouping:
                new CreateFolderCmd().execute(eventContext, eventContext.getSelectedItems(), CreateFolderCmd.Type.GROUPING, Boolean.TRUE, eventContext.getCurrentItem());
                return true;
            case R.id.action_folder_ungrouping:
                new FolderGroupCmd().execute(eventContext, eventContext.getSelectedItems(), Boolean.TRUE);
                return true;
            case R.id.action_lock_album:
                if (!PocFeatures.SUPPORT_LOCKED_ALBUM) {
                    return true;
                }
                new LockAlbumCmd().execute(eventContext, Boolean.TRUE, getCurrentAlbum(eventContext));
                return true;
            case R.id.action_move:
                eventContext.postAnalyticsLog(AnalyticsScreenId.SCREEN_ALBUM_GROUP_EDIT.toString(), AnalyticsEventId.EVENT_MENU_MOVE_ALBUMS);
                eventContext.getBlackboard().post("command://EnterMoveMode", eventContext.getSelectedItems());
                return true;
            case R.id.action_move_to_knox:
                new FetchContentsForKnoxCmd().execute(eventContext, eventContext.getLocationKey(), KnoxUtil.MoveType.MOVE_TO_KNOX);
                return true;
            case R.id.action_move_to_secure_folder:
                new FetchContentsForKnoxCmd().execute(eventContext, eventContext.getLocationKey(), KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER);
                return true;
            case R.id.action_remove_from_knox:
                new FetchContentsForKnoxCmd().execute(eventContext, eventContext.getLocationKey(), KnoxUtil.MoveType.REMOVE_FROM_KNOX);
                return true;
            case R.id.action_remove_from_secure_folder:
                new FetchContentsForKnoxCmd().execute(eventContext, eventContext.getLocationKey(), KnoxUtil.MoveType.REMOVE_FROM_SECURE_FOLDER);
                return true;
            case R.id.action_rename_folder_album:
                new RenameAlbumCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_select:
                postEvent(eventContext, EventMessage.obtain(1002));
                return true;
            case R.id.action_share_album:
                new ShareAlbumCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_view_as:
                new ViewAsCmd().execute(eventContext, new Object[0]);
                return true;
            default:
                return false;
        }
    }
}
