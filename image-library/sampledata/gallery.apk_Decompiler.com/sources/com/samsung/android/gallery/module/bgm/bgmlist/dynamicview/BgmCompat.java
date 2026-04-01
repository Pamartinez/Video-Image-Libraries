package com.samsung.android.gallery.module.bgm.bgmlist.dynamicview;

import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BgmCompat {
    protected final List<String> mBgmList = new ArrayList();

    public List<String> getBgmList() {
        return this.mBgmList;
    }

    public abstract String getBgmName(int i2, int i7);
}
