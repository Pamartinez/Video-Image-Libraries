package com.samsung.android.gallery.module.bgm.bgmlist.dynamicview;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmCompatR extends BgmCompat {
    static final String[] BGM_NAMES = {"A Day on the Trains", "Cartoon", "Deep House", "Latin Shoes", "Travel", "Serene", "Jump And Run", "Red Fantasia", "Whistle Your Cares"};

    public BgmCompatR() {
        this.mBgmList.addAll(Arrays.asList(BGM_NAMES));
    }

    public String getBgmName(int i2, int i7) {
        String[] strArr = BGM_NAMES;
        int length = i2 % strArr.length;
        if (i7 == 0) {
            return strArr[length];
        }
        if (i7 != 1) {
            return strArr[0];
        }
        int i8 = i2 % 5;
        if (i8 == length) {
            i8 = (i8 + 1) % 5;
        }
        return strArr[i8];
    }
}
