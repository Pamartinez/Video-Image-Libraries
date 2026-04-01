package com.samsung.android.gallery.module.abstraction;

import com.samsung.android.gallery.support.config.SdkConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoryItemCuration {
    public static final int MAX_CURATION;

    static {
        int i2;
        if (SdkConfig.atLeast(SdkConfig.SEM.T_MR5)) {
            i2 = 100;
        } else {
            i2 = 60;
        }
        MAX_CURATION = i2;
    }
}
