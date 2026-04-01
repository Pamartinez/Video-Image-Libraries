package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataView$FolderMenuData extends SimplePopupMenuData {
    public PopupMenuDataView$FolderMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_select);
        addMenu(R.id.action_view_as);
        addMenu(R.id.action_album_view_new_album);
        addMenu(R.id.action_folder_add_album);
        addMenu(R.id.action_folder_add_folder);
    }

    public void buildItemAreaMenu() {
        addMenu(R.id.action_move);
        addMenu(R.id.action_share_album);
        addMenu(R.id.action_rename_folder_album);
        addMenu(R.id.action_delete_album_in_list);
        addMenu(R.id.action_change_cover_image);
        addMenu(R.id.action_folder_grouping);
        addMenu(R.id.action_folder_ungrouping);
        addKnoxMenu();
    }
}
