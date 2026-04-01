package com.samsung.android.gallery.widget.listview.handler;

import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.handler.PenSelectionHandler;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ PenSelectionHandler d;
    public final /* synthetic */ PenSelectionHandler.Item e;

    public /* synthetic */ a(PenSelectionHandler penSelectionHandler, PenSelectionHandler.Item item) {
        this.d = penSelectionHandler;
        this.e = item;
    }

    public final void accept(Object obj) {
        this.d.lambda$addViewHolder$1(this.e, (GalleryListAdapter) obj);
    }
}
