package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataPictures$SharingPicturesMenuData extends SimplePopupMenuData {
    public PopupMenuDataPictures$SharingPicturesMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_select);
        addMenu(R.id.action_rename_shared_album);
        addMenu(R.id.action_delete_shared_album);
        addMenu(R.id.action_view_as);
        addMenu(R.id.action_change_sharing_cover_image);
        addMenu(R.id.action_leave_shared_album);
        addMenu(R.id.action_sortby, Features.isEnabled(Features.SUPPORT_SHARED_SORT));
        addMenu(R.id.action_album_settings);
    }

    public void buildItemAreaMenu() {
        addMenu(R.id.action_download_in_sharing_album);
        addMenu(R.id.action_remove);
    }
}
