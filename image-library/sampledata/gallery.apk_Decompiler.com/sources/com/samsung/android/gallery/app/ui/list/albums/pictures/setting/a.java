package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import androidx.recyclerview.widget.RecyclerView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ RecyclerView.ViewHolder d;

    public /* synthetic */ a(RecyclerView.ViewHolder viewHolder) {
        this.d = viewHolder;
    }

    public final void accept(Object obj) {
        ((ScreenshotFilterCustomViewAdapter) obj).resetHighlightedBorder(this.d);
    }
}
