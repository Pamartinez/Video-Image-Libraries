package com.samsung.android.gallery.module.graphics;

import com.samsung.android.gallery.support.helper.DeviceInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BitmapSizeHolder {
    public static int size() {
        if (DeviceInfo.getDefaultDisplay().getLongSideLength() <= 1340) {
            return 1092;
        }
        return 1434;
    }
}
