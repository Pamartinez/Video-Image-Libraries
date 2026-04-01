package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IClusterResult {
    ClusterResultViewAdapter getAdapter();

    int getItemType();

    GalleryListView getListView();

    String getLocationKey();

    int getMaxItemCount();

    MediaData getMediaData();

    boolean isViewActive();

    void onClusterItemClicked(ListViewHolder listViewHolder, MediaItem mediaItem, int i2);

    void onDataChangedOnUi(MediaData mediaData);

    boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        return false;
    }
}
