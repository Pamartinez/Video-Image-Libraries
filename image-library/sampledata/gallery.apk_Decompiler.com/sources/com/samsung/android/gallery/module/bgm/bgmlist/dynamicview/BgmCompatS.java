package com.samsung.android.gallery.module.bgm.bgmlist.dynamicview;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmCompatS extends BgmCompat {
    static final String[] BGM_HIGHLIGHT = {"Neo", "Sunny Day", "Sweet Groove", "Folky Fun", "Pitter Patter"};
    static final String[] BGM_SPEED_MIX = {"Easy Beat", "Tumble Time", "Ragtime Piano", "Ocean", "Up All Night"};
    static final String[] BGM_SPEED_RUN = {"Calm", "Gentle Piano", "Moments", "Cafe"};

    public BgmCompatS() {
        this.mBgmList.addAll(Arrays.asList(BGM_SPEED_MIX));
        this.mBgmList.addAll(Arrays.asList(BGM_SPEED_RUN));
        this.mBgmList.addAll(Arrays.asList(BGM_HIGHLIGHT));
    }

    public String getBgmName(int i2, int i7) {
        if (i7 == 0) {
            String[] strArr = BGM_SPEED_MIX;
            return strArr[i2 % strArr.length];
        } else if (i7 == 1) {
            String[] strArr2 = BGM_SPEED_RUN;
            return strArr2[i2 % strArr2.length];
        } else if (i7 != 3) {
            return BGM_SPEED_MIX[0];
        } else {
            String[] strArr3 = BGM_HIGHLIGHT;
            return strArr3[i2 % strArr3.length];
        }
    }
}
