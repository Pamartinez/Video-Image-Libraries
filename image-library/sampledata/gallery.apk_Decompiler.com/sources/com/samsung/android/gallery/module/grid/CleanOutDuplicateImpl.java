package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CleanOutDuplicateImpl extends CleanOutImpl {
    public CleanOutDuplicateImpl() {
        super("location://cleanOut/duplicated/fileList");
    }

    public int[] buildSpanInfo() {
        return new int[]{1};
    }

    public int getDefaultDepth() {
        return 0;
    }

    public int getGridArrayResource() {
        return R$array.clean_duplicate_pictures_column_count;
    }
}
