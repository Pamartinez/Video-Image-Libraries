package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import com.samsung.android.gallery.app.ui.list.pictures.adapter.Cluster;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.MultiClusterAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.MultiClusterAdapterFactory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.CleanOutDuplicateClusterIndexer;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CleanOutClusterHeaderAdapter extends MultiClusterAdapterFactory.MultiClusterHeaderAdapter {
    public CleanOutClusterHeaderAdapter(MediaData mediaData) {
        super((GalleryListView) null, mediaData, false, false, (MultiClusterAdapter.TimelineModeLookup) null);
    }

    public Cluster[] createCluster(GalleryRecyclerView galleryRecyclerView, MediaData mediaData, boolean z, boolean z3) {
        return createCluster(mediaData);
    }

    public Cluster getCluster(int i2) {
        return this.mClusters[0];
    }

    private CleanOutDuplicateCluster[] createCluster(MediaData mediaData) {
        CleanOutDuplicateClusterIndexer cleanOutDuplicateClusterIndexer;
        CleanOutDuplicateCluster[] cleanOutDuplicateClusterArr = {null, null, null, null};
        if (!(mediaData == null || (cleanOutDuplicateClusterIndexer = mediaData.getCleanOutDuplicateClusterIndexer()) == null)) {
            cleanOutDuplicateClusterArr[0] = new CleanOutDuplicateCluster(cleanOutDuplicateClusterIndexer, mediaData.getCount());
        }
        return cleanOutDuplicateClusterArr;
    }
}
