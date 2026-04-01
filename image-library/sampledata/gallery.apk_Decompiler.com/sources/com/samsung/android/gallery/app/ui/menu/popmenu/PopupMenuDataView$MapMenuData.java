package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataView$MapMenuData extends SimplePopupMenuData {
    public PopupMenuDataView$MapMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_select);
        addMenu(R.id.action_create);
        addMenu(R.id.action_slideshow);
        addMenu(R.id.action_view_all);
    }

    public void buildItemAreaMenu() {
        addMenu(R.id.action_share);
        addMenu(R.id.action_delete);
        addMenu(R.id.action_copy_to_album);
        addMenu(R.id.action_move_to_album);
        addMenu(R.id.action_add_tag);
        addMenu(R.id.action_download);
        addMenu(R.id.action_edit_date_and_time);
        addMenu(R.id.action_edit_location, Features.isEnabled(Features.SUPPORT_LOCATION));
        addMenu(R.id.action_set_as_container);
        addKnoxMenu();
    }
}
