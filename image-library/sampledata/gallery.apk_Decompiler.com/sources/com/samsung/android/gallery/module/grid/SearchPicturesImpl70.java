package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchPicturesImpl70 extends SearchPicturesImpl {
    public int[] buildMonth() {
        return new int[]{3};
    }

    public int[] buildSpanInfo() {
        return new int[]{17, 1, 1, 2};
    }

    public int getGridArrayResource() {
        return R$array.n_pictures_column_count;
    }

    public int getGridDepth() {
        int loadInt = GalleryPreference.getInstance().loadInt(getPreferenceName(), -1);
        if (loadInt >= 0) {
            return loadInt;
        }
        int gridDepthFromLegacy = getGridDepthFromLegacy(AppResources.getAppContext(), new SearchPicturesImpl());
        GalleryPreference.getInstance().saveState(getPreferenceName(), gridDepthFromLegacy);
        return gridDepthFromLegacy;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.SEARCH_PICTURES_GRID_SIZE70;
    }

    public boolean isMonth(int i2) {
        if (i2 == 3) {
            return true;
        }
        return false;
    }
}
