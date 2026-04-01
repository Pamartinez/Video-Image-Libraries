package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataView$SharingsMenuData extends SimplePopupMenuData {
    public PopupMenuDataView$SharingsMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_sharings_storage_use);
        addMenu(R.id.action_sort_by_sharing);
    }

    public void buildItemAreaMenu() {
        addMenu(R.id.action_delete_shared_album_in_list);
        addMenu(R.id.action_rename_shared_album_in_list);
        addMenu(R.id.action_leave_shared_album_in_list);
    }
}
