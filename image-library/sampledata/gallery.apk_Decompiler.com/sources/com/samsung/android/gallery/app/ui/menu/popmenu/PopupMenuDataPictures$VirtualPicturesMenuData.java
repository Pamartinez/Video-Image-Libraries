package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataPictures$VirtualPicturesMenuData extends SimplePopupMenuData {
    public PopupMenuDataPictures$VirtualPicturesMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_select);
        addMenu(R.id.action_add_shortcut);
        addMenu(R.id.action_view_as);
        addMenu(R.id.action_create);
        addMenu(R.id.action_sortby);
        addMenu(R.id.action_slideshow);
    }

    public void buildItemAreaMenu() {
        addSelectedBaseMenu();
        addKnoxMenu();
    }
}
