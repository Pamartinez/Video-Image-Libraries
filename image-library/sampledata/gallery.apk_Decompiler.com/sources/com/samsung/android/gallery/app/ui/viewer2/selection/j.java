package com.samsung.android.gallery.app.ui.viewer2.selection;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Consumer {
    public final /* synthetic */ SelectionViewFragment d;
    public final /* synthetic */ int e;

    public /* synthetic */ j(SelectionViewFragment selectionViewFragment, int i2) {
        this.d = selectionViewFragment;
        this.e = i2;
    }

    public final void accept(Object obj) {
        this.d.lambda$onItemClick$0(this.e, (SelectionViewAdapter) obj);
    }
}
