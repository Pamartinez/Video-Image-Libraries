package com.samsung.android.gallery.app.ui.list.stories.category;

import androidx.core.util.Supplier;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ListViewHolder.OnItemClickListener, Supplier {
    public final /* synthetic */ Object d;

    public /* synthetic */ b(Object obj) {
        this.d = obj;
    }

    public Object get() {
        return Boolean.valueOf(((StoriesCategory2Presenter) this.d).isPreviewPlayable());
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ((IStoriesCategory2View) this.d).onListItemClick(listViewHolder, i2, mediaItem, i7);
    }
}
