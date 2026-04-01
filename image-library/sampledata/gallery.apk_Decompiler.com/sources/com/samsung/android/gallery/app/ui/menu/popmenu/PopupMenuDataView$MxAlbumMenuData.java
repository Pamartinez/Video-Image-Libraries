package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataView$MxAlbumMenuData extends PopupMenuDataView$AlbumMenuData {
    public PopupMenuDataView$MxAlbumMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    private static boolean isSharings(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isSharing()) {
            return false;
        }
        return true;
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_select);
        addMenu(R.id.action_view_as);
        addMenu(R.id.action_album_view_new_album);
        addMenu(R.id.action_hide_album);
        addMenu(R.id.action_manage_albums);
        addMenu(R.id.action_folder_add_folder);
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            addMenu(R.id.action_trash);
            addMenu(R.id.action_settings);
        }
    }

    public void buildItemAreaMenu() {
        if (isSharings(this.mParams.getFocusedItem())) {
            addSharingAlbumMenu();
            return;
        }
        addMenu(R.id.action_move);
        addMenu(R.id.action_share_album);
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums)) {
            addMenu(R.id.action_remove_from_essential_albums);
        }
        addMenu(R.id.action_rename_album_folder);
        addMenu(R.id.action_delete_album_in_list);
        addMenu(R.id.action_change_cover_image);
        addMenu(R.id.action_folder_ungrouping);
        addMenu(R.id.action_folder_grouping);
        addKnoxMenu();
    }
}
