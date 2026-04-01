package com.samsung.android.gallery.database.dbtype;

import com.samsung.android.gallery.support.config.SdkConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FacePriorityScore {
    public static final int P_1;
    public static final int P_2;

    static {
        int i2;
        if (SdkConfig.atLeast(SdkConfig.SEM.V)) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        int i7 = 4 << i2;
        P_2 = i7;
        P_1 = i7 << 1;
    }
}
