package com.samsung.android.gallery.module.data;

import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashMap e;

    public /* synthetic */ i(int i2, HashMap hashMap) {
        this.d = i2;
        this.e = hashMap;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        HashMap hashMap = this.e;
        String str = (String) obj;
        switch (i2) {
            case 0:
                hashMap.put("coverMediaId", str);
                return;
            default:
                hashMap.put("coverRectRatio", str);
                return;
        }
    }
}
