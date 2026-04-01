package com.samsung.android.gallery.app.ui.menu.list;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.AutoGroupCmd;
import com.samsung.android.gallery.app.controller.album.ChangeAlbumLevelCmd;
import com.samsung.android.gallery.app.controller.album.CreateAlbumCmd;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import com.samsung.android.gallery.app.controller.album.FolderGroupCmd;
import com.samsung.android.gallery.app.controller.album.LockAlbumCmd;
import com.samsung.android.gallery.app.controller.album.RenameAlbumCmd;
import com.samsung.android.gallery.app.controller.album.SortByAlbumDialogCmd;
import com.samsung.android.gallery.app.controller.externals.ShareAlbumCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteAlbumCmd;
import com.samsung.android.gallery.app.controller.internals.ViewAsCmd;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.LeaveSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.RenameSharedAlbumCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.sec.android.gallery3d.R;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumsMenuHandler extends MenuHandler {
    private void checkAvailableHideAlbumNExecute(EventContext eventContext) {
        ThreadUtil.postOnBgThread(new a(this, eventContext));
    }

    private MediaItem getCurrentAlbum(EventContext eventContext) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        if (selectedItems == null || selectedItems.length != 1) {
            return null;
        }
        return selectedItems[0];
    }

    private ConcurrentHashMap<MediaItem, Integer> getMoveToViewAllUpdateValues(MediaItem[] mediaItemArr) {
        ConcurrentHashMap<MediaItem, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        for (MediaItem put : mediaItemArr) {
            concurrentHashMap.put(put, 0);
        }
        return concurrentHashMap;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAvailableHideAlbumNExecute$0(EventContext eventContext, boolean z) {
        if (eventContext.getContext() != null) {
            if (z) {
                Toast.makeText(eventContext.getContext(), R.string.no_albums_available_to_hide, 0).show();
            } else {
                moveTo(eventContext, "location://albums/hide");
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAvailableHideAlbumNExecute$1(EventContext eventContext) {
        try {
            Context context = eventContext.getContext();
            if (context != null) {
                ThreadUtil.postOnUiThread(new b(this, eventContext, !AlbumHelper.getInstance().hasAlbumsForHide(context)));
            }
        } catch (NullPointerException unused) {
            Log.w(this.TAG, "fail checkAvailableHideAlbumNExecute");
        }
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_album_view_new_album:
                new CreateAlbumCmd().execute(eventContext, 0, null, Boolean.FALSE);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_auto_grouping:
                new AutoGroupCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_copy_move_to_new_album:
                eventContext.getBlackboard().post("command://CopyMoveToNewAlbum", (Object) null);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_create_albums:
                eventContext.getBlackboard().postEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_AGENT_VOLLEY_CANCELED));
                return true;
            case R.id.action_delete_album_in_list:
                new DeleteAlbumCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_delete_shared_album_in_list:
                new DeleteSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_SHARED_VIEW_SPACE_DIALOG_DELETE, eventContext.getSelectedItems());
                return true;
            case R.id.action_folder_add_folder:
                new CreateFolderCmd().execute(eventContext, null, CreateFolderCmd.Type.CREATE_EMPTY, Boolean.TRUE, null);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_folder_grouping:
                new CreateFolderCmd().execute(eventContext, eventContext.getSelectedItems(), CreateFolderCmd.Type.GROUPING, Boolean.TRUE, null);
                return true;
            case R.id.action_folder_ungrouping:
                new FolderGroupCmd().execute(eventContext, eventContext.getSelectedItems(), Boolean.TRUE);
                return true;
            case R.id.action_hide_album:
                checkAvailableHideAlbumNExecute(eventContext);
                return true;
            case R.id.action_leave_shared_album_in_list:
                new LeaveSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_SHARED_VIEW_SPACE_DIALOG_LEAVE, eventContext.getSelectedItems());
                return true;
            case R.id.action_lock_album:
                if (!PocFeatures.SUPPORT_LOCKED_ALBUM) {
                    return true;
                }
                new LockAlbumCmd().execute(eventContext, Boolean.TRUE, getCurrentAlbum(eventContext));
                return true;
            case R.id.action_manage_albums:
                if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                    return true;
                }
                moveTo(eventContext, "location://albums/manage");
                return true;
            case R.id.action_move:
                eventContext.postAnalyticsLog(AnalyticsScreenId.SCREEN_ALBUM_VIEW_LONG_PRESS_EDIT.toString(), AnalyticsEventId.EVENT_MENU_MOVE_ALBUMS, (long) eventContext.getSelectedItems().length);
                eventContext.getBlackboard().post("command://EnterMoveMode", eventContext.getSelectedItems());
                return true;
            case R.id.action_remove_from_essential_albums:
                new ChangeAlbumLevelCmd().execute(eventContext, getMoveToViewAllUpdateValues(eventContext.getSelectedItems()));
                eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_MENU_REMOVE_FROM_ESSENTIAL_ALBUMS);
                return true;
            case R.id.action_rename_album_folder:
                new RenameAlbumCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_rename_shared_album_in_list:
                new RenameSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_SHARED_VIEW_SPACE_DIALOG_RENAME, eventContext.getSelectedItems());
                return true;
            case R.id.action_share_album:
                new ShareAlbumCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_sortby_album:
                new SortByAlbumDialogCmd().execute(eventContext, new Object[0]);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_view_as:
                new ViewAsCmd().execute(eventContext, new Object[0]);
                return true;
            default:
                return false;
        }
    }
}
