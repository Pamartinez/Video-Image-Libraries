package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PopupMenuDataView$AlbumMenuData extends SimplePopupMenuData {
    public PopupMenuDataView$AlbumMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    private boolean isItemClickedWithSelectionMode(int i2) {
        if (!this.mParams.isSelectionMode() || i2 != 1) {
            return false;
        }
        return true;
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_view_as);
        if (!isPickerMode()) {
            addMenu(R.id.action_select);
            addMenu(R.id.action_album_view_new_album);
            addMenu(R.id.action_sortby_album);
            addMenu(R.id.action_hide_album);
            addMenu(R.id.action_folder_add_folder);
        }
    }

    public void buildItemAreaMenu() {
        if (isPickerMode()) {
            addMenu(R.id.action_view_as);
            return;
        }
        addMenu(R.id.action_move);
        addMenu(R.id.action_share_album);
        addMenu(R.id.action_rename_album_folder);
        addMenu(R.id.action_delete_album_in_list);
        addMenu(R.id.action_change_cover_image);
        addMenu(R.id.action_folder_ungrouping);
        addMenu(R.id.action_folder_grouping);
        addKnoxMenu();
    }

    public void initGroup() {
        if (isItemClickedWithSelectionMode(this.mParams.getInputType())) {
            this.mParams.getMenu().removeGroup(R.id.normal_mode);
        }
    }
}
