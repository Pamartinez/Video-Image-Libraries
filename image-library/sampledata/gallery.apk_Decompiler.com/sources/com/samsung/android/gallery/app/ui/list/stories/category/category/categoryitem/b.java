package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ListViewHolder.OnItemClickListener {
    public final /* synthetic */ StoriesCatTransitoryRecapContentViewHolder d;

    public /* synthetic */ b(StoriesCatTransitoryRecapContentViewHolder storiesCatTransitoryRecapContentViewHolder) {
        this.d = storiesCatTransitoryRecapContentViewHolder;
    }

    public final void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.d.onListItemClick(listViewHolder, i2, mediaItem, i7);
    }
}
