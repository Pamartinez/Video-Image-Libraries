package com.samsung.android.gallery.app.ui.list.hover;

import B8.f;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ HoverHandler.DataLoadCallback d;
    public final /* synthetic */ ArrayList e;

    public /* synthetic */ b(HoverHandler.DataLoadCallback dataLoadCallback, ArrayList arrayList) {
        this.d = dataLoadCallback;
        this.e = arrayList;
    }

    public final void run() {
        ((f) this.d).a(this.e);
    }
}
