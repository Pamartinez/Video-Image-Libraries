package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingsImpl extends GridHelper {
    public SharingsImpl() {
        super("location://sharing/albums");
    }

    public int[] buildSpanInfo() {
        return new int[]{1, 1, 1};
    }

    public int getDefaultDepth() {
        return 1;
    }

    public int getGridArrayResource() {
        return R$array.sharing_column_count_v31;
    }

    public int getGridArrayResourceOnDex() {
        return R$array.dex_album_list_column_count;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.ALBUMS_GRID_SIZE;
    }

    public int getSplitArrayResourceOnDex() {
        return R$array.dex_album_list_column_count_split;
    }
}
