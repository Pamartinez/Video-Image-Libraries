package com.samsung.android.gallery.app.ui.viewer2.selection;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements AbsCacheMgr$EvictListener, ListViewHolder.OnItemClickListener {
    public final /* synthetic */ SelectionViewAdapter d;

    public /* synthetic */ e(SelectionViewAdapter selectionViewAdapter) {
        this.d = selectionViewAdapter;
    }

    public void OnEvicted(Object obj, Object obj2) {
        this.d.lambda$new$0((String) obj, (Bitmap) obj2);
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.d.lambda$new$1(listViewHolder, i2, mediaItem, i7);
    }
}
