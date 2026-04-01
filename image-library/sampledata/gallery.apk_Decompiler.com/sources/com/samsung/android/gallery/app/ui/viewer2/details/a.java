package com.samsung.android.gallery.app.ui.viewer2.details;

import android.util.Size;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ DetailsLoadHandler.AnonymousClass1 d;
    public final /* synthetic */ Size e;
    public final /* synthetic */ Size f;

    public /* synthetic */ a(DetailsLoadHandler.AnonymousClass1 r1, Size size, Size size2) {
        this.d = r1;
        this.e = size;
        this.f = size2;
    }

    public final void run() {
        this.d.lambda$onLayoutChange$1(this.e, this.f);
    }
}
