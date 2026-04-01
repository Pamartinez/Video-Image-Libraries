package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumsPaneImpl extends GridHelper {
    public AlbumsPaneImpl() {
        super("location://albums/pane");
    }

    public int[] buildSpanInfo() {
        return new int[]{1};
    }

    public int getDefaultDepth() {
        return 0;
    }

    public int getGridArrayResource() {
        return R$array.n_single_column_count;
    }

    public PreferenceName getPreferenceName() {
        return null;
    }

    public int getSplitArrayResource() {
        return R$array.n_single_split_column_count;
    }
}
