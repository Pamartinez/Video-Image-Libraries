package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataPictures$AlbumPicturesMenuData extends SimplePopupMenuData {
    public PopupMenuDataPictures$AlbumPicturesMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_select);
        addMenu(R.id.action_add_shortcut);
        addMenu(R.id.action_view_mode, DeviceInfo.isDexMode(Blackboard.getContext()));
        addMenu(R.id.action_view_as);
        addMenu(R.id.action_create);
        addMenu(R.id.action_sortby);
        addMenu(R.id.action_slideshow);
        addMenu(R.id.action_album_settings);
    }

    public void buildItemAreaMenu() {
        addSelectedBaseMenu();
        addKnoxMenu();
    }
}
