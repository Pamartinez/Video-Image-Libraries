package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TrashImpl extends AlbumPicturesImpl {
    public TrashImpl(String str) {
        super(str);
    }

    public int[] buildSpanInfo() {
        return new int[]{1, 1, 17};
    }

    public int getGridArrayResource() {
        return R$array.trash_column_count;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.TRASH_PICTURES_GRID_SIZE;
    }

    public int getSplitArrayResource() {
        return R$array.trash_column_count;
    }
}
