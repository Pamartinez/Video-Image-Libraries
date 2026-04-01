package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumPicturesImpl70 extends AlbumPicturesImpl {
    public AlbumPicturesImpl70(String str) {
        super(str);
    }

    public int[] buildMonth() {
        if (GridHelper.NON_REAL_RATIO) {
            return new int[]{2};
        }
        if (PocFeatures.ALBUM_PICTURES_EXTEND) {
            return new int[]{3, 4};
        }
        return new int[]{3};
    }

    public int[] buildSpanInfo() {
        if (!PocFeatures.ALBUM_PICTURES_EXTEND || GridHelper.ModelSegment.SEP_LITE) {
            return new int[]{17, 1, 1, 2};
        }
        return new int[]{17, 1, 1, 2, 98};
    }

    public int getDefaultDepth() {
        if (GridHelper.NON_REAL_RATIO) {
            return 1;
        }
        return 2;
    }

    public int getGridArrayResource() {
        if (!PocFeatures.ALBUM_PICTURES_EXTEND || GridHelper.ModelSegment.SEP_LITE) {
            return R$array.n_pictures_column_count;
        }
        return R$array.n_album_pictures_column_count;
    }

    public int getGridDepth() {
        int loadInt = GalleryPreference.getInstance().loadInt(getPreferenceName(), -1);
        if (loadInt >= 0) {
            return loadInt;
        }
        int gridDepthFromLegacy = getGridDepthFromLegacy(AppResources.getAppContext(), new AlbumPicturesImpl(this.mLocationKey));
        GalleryPreference.getInstance().saveState(getPreferenceName(), gridDepthFromLegacy);
        return gridDepthFromLegacy;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.ALBUM_PICTURES_GRID_SIZE70;
    }

    public int getSplitArrayResource() {
        if (!PocFeatures.ALBUM_PICTURES_EXTEND || GridHelper.ModelSegment.SEP_LITE) {
            return R$array.n_pictures_split_column_count;
        }
        return R$array.n_album_pictures_split_column_count;
    }

    public boolean isMonth(int i2) {
        if (GridHelper.NON_REAL_RATIO) {
            if (i2 >= 2) {
                return true;
            }
            return false;
        } else if (i2 >= 3) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMonthForViewing() {
        return isMonthForViewing(getGridDepth());
    }

    public boolean isSelectable(int i2) {
        if (GridHelper.ModelSegment.SEP_LITE || i2 < 4) {
            return true;
        }
        return false;
    }

    public boolean isMonthForViewing(int i2) {
        if (!GridHelper.NON_REAL_RATIO && PocFeatures.ALBUM_PICTURES_EXTEND && i2 == 4) {
            return true;
        }
        return false;
    }
}
