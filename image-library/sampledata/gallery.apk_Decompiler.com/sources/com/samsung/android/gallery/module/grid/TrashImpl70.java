package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TrashImpl70 extends AlbumPicturesImpl {
    private static final boolean FEATURE_SUPPORT_REAL_RATIO = Features.isEnabled(Features.SUPPORT_REAL_RATIO);

    public TrashImpl70(String str) {
        super(str);
    }

    public int[] buildSpanInfo() {
        return new int[]{17, 1, 1, 2};
    }

    public int getDefaultDepth() {
        if (FEATURE_SUPPORT_REAL_RATIO) {
            return super.getDefaultDepth();
        }
        return 1;
    }

    public int getGridArrayResource() {
        return R$array.n_pictures_column_count;
    }

    public int getGridDepth() {
        int loadInt = GalleryPreference.getInstance().loadInt(getPreferenceName(), -1);
        if (loadInt >= 0) {
            return loadInt;
        }
        int gridDepthFromLegacy = getGridDepthFromLegacy(AppResources.getAppContext(), new TrashImpl(this.mLocationKey));
        GalleryPreference.getInstance().saveState(getPreferenceName(), gridDepthFromLegacy);
        return gridDepthFromLegacy;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.TRASH_PICTURES_GRID_SIZE70;
    }

    public int getSplitArrayResource() {
        return R$array.n_pictures_column_count;
    }
}
