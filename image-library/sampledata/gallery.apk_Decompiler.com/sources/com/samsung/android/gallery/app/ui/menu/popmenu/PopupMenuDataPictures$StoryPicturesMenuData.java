package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PopupMenuDataPictures$StoryPicturesMenuData extends SimplePopupMenuData {
    public PopupMenuDataPictures$StoryPicturesMenuData(PopupMenuParams popupMenuParams) {
        super(popupMenuParams);
    }

    public void buildItemAreaMenu() {
        if (PreferenceFeatures.OneUi30.MEMORIES) {
            addMenu(R.id.action_share);
        } else {
            addMenu(R.id.action_delete);
        }
        addMenu(R.id.action_remove_from_story);
    }

    public void buildEmptyAreaMenu() {
    }
}
