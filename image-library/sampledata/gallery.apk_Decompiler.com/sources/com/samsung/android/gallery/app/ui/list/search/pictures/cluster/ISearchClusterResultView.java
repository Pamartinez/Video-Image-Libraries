package com.samsung.android.gallery.app.ui.list.search.pictures.cluster;

import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISearchClusterResultView extends ISearchPicturesView {
    void clearSearchCluster();

    void loadClusterData(String str, HashMap hashMap);

    void loadClusterDataIncludeCarousel(HashMap hashMap);

    void setContainerVisibility(boolean z);

    void setPendingLayoutChange();

    boolean supportMenu();

    void onStartDragViaItemLongClickedEvent(ListViewHolder listViewHolder, MediaItem mediaItem) {
    }
}
