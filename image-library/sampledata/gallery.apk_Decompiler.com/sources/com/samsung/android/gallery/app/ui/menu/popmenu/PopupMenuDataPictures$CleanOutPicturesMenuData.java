package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataPictures$CleanOutPicturesMenuData extends SimplePopupMenuData {
    public PopupMenuDataPictures$CleanOutPicturesMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_select);
    }

    public void buildItemAreaMenu() {
        addMenu(R.id.action_keep_contents);
        addMenu(R.id.action_clean_contents);
    }
}
