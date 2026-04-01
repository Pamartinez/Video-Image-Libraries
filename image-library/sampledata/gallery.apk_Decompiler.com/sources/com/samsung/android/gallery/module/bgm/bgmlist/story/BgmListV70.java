package com.samsung.android.gallery.module.bgm.bgmlist.story;

import A4.A;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmListV70 extends BgmListV61 {
    public BgmListV70() {
        update();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$replaceHappy$0(HashMap hashMap, String str) {
        if (str.equals("Sunrise")) {
            this.mHappy.put("Morning Stroll", Boolean.TRUE);
        } else {
            this.mHappy.put(str, (Boolean) hashMap.get(str));
        }
    }

    private void replaceHappy() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.mHappy);
        this.mHappy.clear();
        linkedHashMap.keySet().forEach(new A(20, (Object) this, (Object) linkedHashMap));
    }

    private void update() {
        replaceHappy();
    }
}
