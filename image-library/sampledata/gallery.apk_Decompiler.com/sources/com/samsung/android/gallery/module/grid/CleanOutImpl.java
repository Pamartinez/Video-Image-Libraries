package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CleanOutImpl extends GridHelper {
    public CleanOutImpl(String str) {
        super(str);
    }

    public int[] buildSpanInfo() {
        return new int[]{1, 1};
    }

    public int getDefaultDepth() {
        return 0;
    }

    public int getGridArrayResource() {
        return R$array.clean_pictures_column_count;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.SUGGESTION_CLEAN_OUT_PICTURES_GRID_SIZE;
    }
}
