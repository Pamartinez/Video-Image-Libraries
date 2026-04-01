package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.module.grid.GridHelper;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataPictures$PicturesMenuData extends SimplePopupMenuData {
    public PopupMenuDataPictures$PicturesMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_select);
        addMenu(R.id.action_view_as);
        addMenu(R.id.action_create);
        addMenu(R.id.action_slideshow);
    }

    public void buildItemAreaMenu() {
        if (!GridHelper.isTimelineYear() && !GridHelper.getTimeline().isMonthForViewing()) {
            addSelectedBaseMenu();
            addKnoxMenu();
        }
    }
}
