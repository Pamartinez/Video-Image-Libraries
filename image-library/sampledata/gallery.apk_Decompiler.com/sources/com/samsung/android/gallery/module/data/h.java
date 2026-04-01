package com.samsung.android.gallery.module.data;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ h(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MediaCacheLoader) this.e).lambda$apply$3((MediaCache) this.f);
                return;
            case 1:
                ((MediaItemRetryLoader) this.e).lambda$load$0((Uri) this.f);
                return;
            default:
                ((StoriesPinCache) this.e).lambda$loadComplete$1((StoriesPinData) this.f);
                return;
        }
    }
}
