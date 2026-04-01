package com.samsung.android.gallery.app.ui.list.search;

import androidx.core.util.Supplier;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ListViewHolder.OnItemClickListener, Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListViewHolder e;

    public /* synthetic */ h(ListViewHolder listViewHolder, int i2) {
        this.d = i2;
        this.e = listViewHolder;
    }

    public Object get() {
        return Boolean.valueOf(((CategoryStoriesCardHolder) this.e).isPlayable());
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        int i8 = this.d;
        ListViewHolder listViewHolder2 = this.e;
        switch (i8) {
            case 0:
                ((CategoryLocation2CardHolder) listViewHolder2).moveMap(listViewHolder, i2, mediaItem, i7);
                return;
            case 1:
                ((CategoryLocation3CardHolder) listViewHolder2).moveToMapView(listViewHolder, i2, mediaItem, i7);
                return;
            default:
                ((CategoryStoriesTransitoryHolder) listViewHolder2).onItemClick(listViewHolder, i2, mediaItem, i7);
                return;
        }
    }
}
