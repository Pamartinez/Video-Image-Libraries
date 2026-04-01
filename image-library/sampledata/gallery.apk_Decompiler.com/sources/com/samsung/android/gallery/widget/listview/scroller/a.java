package com.samsung.android.gallery.widget.listview.scroller;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ float d;

    public /* synthetic */ a(float f) {
        this.d = f;
    }

    public final void accept(Object obj) {
        ((YearData) obj).getView().setAlpha(this.d);
    }
}
