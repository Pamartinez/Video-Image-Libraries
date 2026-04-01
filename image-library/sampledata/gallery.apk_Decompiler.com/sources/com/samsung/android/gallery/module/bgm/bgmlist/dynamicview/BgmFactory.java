package com.samsung.android.gallery.module.bgm.bgmlist.dynamicview;

import com.samsung.android.gallery.support.config.SdkConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BgmFactory {
    private static volatile BgmCompat sInstance;

    static {
        BgmCompat bgmCompat;
        if (SdkConfig.atLeast(SdkConfig.SEM.S)) {
            bgmCompat = new BgmCompatS();
        } else {
            bgmCompat = new BgmCompatR();
        }
        sInstance = bgmCompat;
    }

    public static BgmCompat getInstance() {
        return sInstance;
    }
}
