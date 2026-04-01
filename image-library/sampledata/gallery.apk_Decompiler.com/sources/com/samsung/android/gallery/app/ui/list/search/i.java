package com.samsung.android.gallery.app.ui.list.search;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.dataset.MediaData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CategoryLocation2CardHolder e;
    public final /* synthetic */ Object f;

    public /* synthetic */ i(CategoryLocation2CardHolder categoryLocation2CardHolder, Object obj, int i2) {
        this.d = i2;
        this.e = categoryLocation2CardHolder;
        this.f = obj;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$loadLatestLocationInfo$0((MediaData) this.f);
                return;
            case 1:
                this.e.lambda$bindLocationInfo$2((MediaData) this.f);
                return;
            default:
                this.e.lambda$bindCachedMapImage$4((Bitmap) this.f);
                return;
        }
    }
}
