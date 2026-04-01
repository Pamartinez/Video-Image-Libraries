package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingPicturesImpl extends GridHelper {
    public SharingPicturesImpl() {
        super("location://sharing/albums/fileList");
    }

    public int[] buildSpanInfo() {
        return new int[]{2, 1, 1, 17};
    }

    public int getDefaultDepth() {
        return 2;
    }

    public int getGridArrayResource() {
        return R$array.sharing_pictures_column_count;
    }

    public String getNotifyKey() {
        return "command://SharingPicturesViewChanged";
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.SHARING_PICTURES_GRID_SIZE;
    }
}
