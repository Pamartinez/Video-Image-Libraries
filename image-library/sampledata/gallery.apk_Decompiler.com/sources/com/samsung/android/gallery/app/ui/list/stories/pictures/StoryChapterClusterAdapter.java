package com.samsung.android.gallery.app.ui.list.stories.pictures;

import com.samsung.android.gallery.app.ui.list.pictures.adapter.Cluster;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.MultiClusterAdapterFactory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import com.samsung.android.gallery.module.dataset.tables.ChapterIndexer;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoryChapterClusterAdapter extends MultiClusterAdapterFactory.MultiClusterBothAdapter {
    private final ChapterIndexer.LayoutLookup mLayoutLookUp;

    public StoryChapterClusterAdapter(GalleryRecyclerView galleryRecyclerView, MediaData mediaData, ChapterIndexer.LayoutLookup layoutLookup) {
        super(mediaData);
        this.mLayoutLookUp = layoutLookup;
        this.mClusters = createCluster(galleryRecyclerView, mediaData, false, false);
    }

    public Cluster[] createCluster(GalleryRecyclerView galleryRecyclerView, MediaData mediaData, boolean z, boolean z3) {
        return createCluster(galleryRecyclerView, mediaData);
    }

    public Cluster getCluster(int i2) {
        return this.mClusters[0];
    }

    public LayoutInfo getLayoutInfo(int i2) {
        return ((StoryChapterCluster) this.mClusters[0]).getLayoutInfo(i2);
    }

    private StoryChapterCluster[] createCluster(GalleryRecyclerView galleryRecyclerView, MediaData mediaData) {
        StoryChapterCluster[] storyChapterClusterArr = {null, null, null, null};
        if (mediaData != null) {
            storyChapterClusterArr[0] = new StoryChapterCluster(mediaData, this.mLayoutLookUp, galleryRecyclerView.getSpecProvider());
        }
        return storyChapterClusterArr;
    }
}
