package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataPictures$SearchPicturesMenuData extends SimplePopupMenuData {
    public PopupMenuDataPictures$SearchPicturesMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildEmptyAreaMenu() {
        int i2;
        addMenu(R.id.action_select);
        addMenu(R.id.action_view_as);
        addMenu(R.id.action_create);
        addMenu(R.id.action_slideshow);
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            if (LocationKey.isSearchCategoryMyTag(this.mParams.getLocationKey())) {
                i2 = R.string.remove_items_from_tag_group;
            } else {
                i2 = R.string.remove_items_from_collection;
            }
            addMenu(R.id.action_remove_from_result, true, AppResources.getString(i2));
            return;
        }
        addMenu(R.id.action_remove_from_result);
    }

    public void buildItemAreaMenu() {
        addSelectedBaseMenu();
        addKnoxMenu();
    }
}
