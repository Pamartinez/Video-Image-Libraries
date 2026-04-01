package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PicturesImpl70 extends PicturesImpl {
    public PicturesImpl70(String str) {
        super(str);
    }

    public int[] buildMonth() {
        return new int[]{3};
    }

    public int[] buildSpanInfo() {
        return new int[]{17, 1, 1, 2};
    }

    public int getDefaultDepth() {
        return 2;
    }

    public int getGridArrayResource() {
        return R$array.n_pictures_column_count;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.PICTURES_PICKER_GRID_SIZE70;
    }

    public int getSplitArrayResource() {
        return R$array.n_pictures_split_column_count;
    }

    public boolean isMonth(int i2) {
        if (i2 == 3) {
            return true;
        }
        return false;
    }
}
