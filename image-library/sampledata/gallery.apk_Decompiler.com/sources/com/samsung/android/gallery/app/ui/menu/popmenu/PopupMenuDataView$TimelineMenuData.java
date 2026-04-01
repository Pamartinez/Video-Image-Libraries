package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataView$TimelineMenuData extends SimplePopupMenuData {
    public PopupMenuDataView$TimelineMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        addMenu(R.id.action_view_as);
        if (!isPickerMode()) {
            addMenu(R.id.action_select);
            addMenu(R.id.action_create);
            addMenu(R.id.action_similar);
            addMenu(R.id.action_slideshow);
        }
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            addMenu(R.id.action_trash);
            addMenu(R.id.action_settings);
        }
    }

    public void buildItemAreaMenu() {
        if (isPickerMode()) {
            addMenu(R.id.action_view_as);
        } else if (!GridHelper.isTimelineYear() && !GridHelper.getTimeline().isMonthForViewing()) {
            addSelectedBaseMenu();
            addKnoxMenu();
        }
    }
}
