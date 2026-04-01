package com.samsung.android.gallery.app.ui.list.search;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.LocationValue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ CategoryLocation2CardHolder d;
    public final /* synthetic */ LocationValue e;
    public final /* synthetic */ Bitmap f;
    public final /* synthetic */ byte[] g;

    public /* synthetic */ k(CategoryLocation2CardHolder categoryLocation2CardHolder, LocationValue locationValue, Bitmap bitmap, byte[] bArr) {
        this.d = categoryLocation2CardHolder;
        this.e = locationValue;
        this.f = bitmap;
        this.g = bArr;
    }

    public final void run() {
        this.d.lambda$addMapView$6(this.e, this.f, this.g);
    }
}
