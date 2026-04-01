package com.samsung.android.gallery.widget.story.transitory;

import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ViewPagerItemFactory {
    ListViewHolder createItemViewHolder(ViewGroup viewGroup, int i2);

    int getItemViewType(MediaItem mediaItem, boolean z);
}
