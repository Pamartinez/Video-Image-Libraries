package com.samsung.android.gallery.module.data;

import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ HashMap f;
    public final /* synthetic */ MediaItem[] g;

    public /* synthetic */ t(ArrayList arrayList, HashMap hashMap, MediaItem[] mediaItemArr, int i2) {
        this.d = i2;
        this.e = arrayList;
        this.f = hashMap;
        this.g = mediaItemArr;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                UriItemLoader.loadMediaUri(this.e, new u(this.f, this.g, 0));
                return;
            case 1:
                UriItemLoader.loadMediaUri(this.e, new u(this.f, this.g, 1));
                return;
            default:
                UriItemLoader.loadLocalUri(this.e, this.f, this.g);
                return;
        }
    }
}
