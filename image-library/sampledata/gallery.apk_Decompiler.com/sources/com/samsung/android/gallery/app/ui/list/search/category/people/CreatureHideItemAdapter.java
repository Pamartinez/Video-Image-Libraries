package com.samsung.android.gallery.app.ui.list.search.category.people;

import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryPeopleItemViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureHideItemAdapter<V extends ICreatureSelectView> extends CategoryItemAdapterV2<V> {
    public CreatureHideItemAdapter(V v, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(v, str, galleryListView, categoryPropertyHelper, z);
    }

    public void onUpdateViewHolder(int i2) {
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mGalleryListView.findViewHolderForAdapterPosition(getViewPosition(i2));
        if (findViewHolderForAdapterPosition != null) {
            ((CategoryPeopleItemViewHolder) findViewHolderForAdapterPosition).toggleVisualCue();
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem != null && CreatureData.of(mediaItem).isCreatureHide) {
            ((ImageViewHolder) listViewHolder).drawVisualCue();
        }
    }
}
