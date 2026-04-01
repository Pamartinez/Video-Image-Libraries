package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataView$TrashMenuData extends SimplePopupMenuData {
    public PopupMenuDataView$TrashMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_select);
        addMenu(R.id.action_view_as);
        addMenu(R.id.action_empty);
    }

    public void buildItemAreaMenu() {
        addMenu(R.id.action_restore);
        addMenu(R.id.action_delete);
    }
}
