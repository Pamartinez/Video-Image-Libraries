package com.samsung.android.gallery.module.dataset;

import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataSearch e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ long g;

    public /* synthetic */ i0(MediaDataSearch mediaDataSearch, ArrayList arrayList, long j2, int i2) {
        this.d = i2;
        this.e = mediaDataSearch;
        this.f = arrayList;
        this.g = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$swap$1(this.f, this.g);
                return;
            default:
                this.e.lambda$swapPartialCursors$3(this.f, this.g);
                return;
        }
    }
}
