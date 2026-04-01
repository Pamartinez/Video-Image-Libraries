package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataView$SearchMenuData extends SimplePopupMenuData {
    public PopupMenuDataView$SearchMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            addMenu(R.id.action_trash);
            addMenu(R.id.action_settings);
        }
    }

    public void buildItemAreaMenu() {
    }
}
