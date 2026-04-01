package com.samsung.android.gallery.app.ui.menu.list;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.AddShortcutCmd;
import com.samsung.android.gallery.app.controller.album.ChangeAlbumCoverCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.app.controller.album.LockAlbumCmd;
import com.samsung.android.gallery.app.controller.album.RemoveAutoUpdatedItemsCmd;
import com.samsung.android.gallery.app.controller.album.RenameAlbumCmd;
import com.samsung.android.gallery.app.controller.album.SortByDialogCmd;
import com.samsung.android.gallery.app.controller.externals.CreateMovieMultiEditCmd;
import com.samsung.android.gallery.app.controller.internals.AlbumPicturesSearchCmd;
import com.samsung.android.gallery.app.controller.internals.StartCloudCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.share.QuickSharePrivacy;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumPicturesMenuHandler extends MenuHandler {
    private MediaItem getCurrentAlbum(EventContext eventContext) {
        MediaItem currentItem = eventContext.getCurrentItem();
        if (currentItem != null) {
            return currentItem;
        }
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        if (selectedItems == null || selectedItems.length != 1) {
            return null;
        }
        return selectedItems[0];
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        String str;
        MediaItem currentAlbum = getCurrentAlbum(eventContext);
        switch (menuItem.getItemId()) {
            case R.id.action_add_items_in_album:
                new FileOpCmd().execute(eventContext, FileOpCmdHelper.CmdType.TYPE_ADD_TO_ITEMS, currentAlbum);
                return true;
            case R.id.action_add_shortcut:
                new AddShortcutCmd().execute(eventContext, currentAlbum);
                return true;
            case R.id.action_album_search:
                new AlbumPicturesSearchCmd().execute(eventContext, currentAlbum);
                return true;
            case R.id.action_album_settings:
                if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || currentAlbum == null) {
                    return true;
                }
                eventContext.getBlackboard().publish("data://albums/current", currentAlbum);
                if (currentAlbum.isAutoAlbum()) {
                    str = "location://albums/AutoAlbumSetting";
                } else {
                    str = "location://albums/AlbumSetting";
                }
                moveTo(eventContext, ArgumentsUtil.appendArgs(str, "type", String.valueOf(currentAlbum.getAlbumType().toInt())));
                return true;
            case R.id.action_change_cover_image:
                new ChangeAlbumCoverCmd().execute(eventContext, currentAlbum);
                return true;
            case R.id.action_cloud_sync_album:
                new StartCloudCmd().execute(eventContext, currentAlbum);
                return true;
            case R.id.action_create_movie:
                new CreateMovieMultiEditCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_quick_share_privacy:
                QuickSharePrivacy.getInstance().start(eventContext.getContext(), eventContext.getScreenId());
                return true;
            case R.id.action_remove_from_auto_album:
                if (currentAlbum != null) {
                    new RemoveAutoUpdatedItemsCmd().execute(eventContext, Integer.valueOf(currentAlbum.getAlbumID()), Integer.valueOf(currentAlbum.getAlbumType().toInt()));
                    return true;
                }
                Log.e(this.TAG, "remove item failed(null)");
                return true;
            case R.id.action_rename_album:
                new RenameAlbumCmd().execute(eventContext, new Object[0]);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_sortby:
                new SortByDialogCmd().execute(eventContext, currentAlbum);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_unlock_album:
                if (!PocFeatures.SUPPORT_LOCKED_ALBUM) {
                    return true;
                }
                new LockAlbumCmd().execute(eventContext, Boolean.FALSE, currentAlbum);
                return true;
            case R.id.action_view_mode:
                postEvent(eventContext, EventMessage.obtain(1001));
                return true;
            default:
                return false;
        }
    }
}
