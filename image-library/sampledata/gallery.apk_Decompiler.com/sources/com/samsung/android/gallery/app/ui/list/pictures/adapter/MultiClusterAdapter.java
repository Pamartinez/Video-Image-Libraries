package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import android.util.Pair;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiClusterAdapter {
    protected Cluster[] mClusters;
    private Cluster mCurrentCluster;
    protected Cluster mFakeCluster;
    private GalleryListView mListView;
    private final MediaData mMediaData;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TimelineModeLookup {
    }

    public MultiClusterAdapter(GalleryListView galleryListView, MediaData mediaData, boolean z, boolean z3, TimelineModeLookup timelineModeLookup) {
        this.mListView = galleryListView;
        this.mMediaData = mediaData;
        this.mClusters = createCluster(galleryListView, mediaData, z, z3);
    }

    private int getRealGridSize(int i2) {
        return GridValue.revert(i2);
    }

    private boolean isFirstColumn(int i2, int i7) {
        if (getCluster(i7).getStartSpan(getHeaderDeltaPosition(i2), getRealGridSize(i7)) == 0) {
            return true;
        }
        return false;
    }

    public void clear() {
        Cluster[] clusterArr = this.mClusters;
        if (clusterArr != null) {
            for (Cluster cluster : clusterArr) {
                if (cluster != null) {
                    cluster.clear();
                }
            }
        }
        this.mListView = null;
    }

    public Cluster[] createCluster(GalleryRecyclerView galleryRecyclerView, MediaData mediaData, boolean z, boolean z3) {
        Cluster[] createMultipleCluster = ClusterFactory.createMultipleCluster(mediaData, z, z3, galleryRecyclerView.getSpecProvider());
        this.mClusters = createMultipleCluster;
        return createMultipleCluster;
    }

    public void createMonthXsFakeCluster(int i2, int i7) {
        this.mFakeCluster = ClusterFactory.createMonthXsFakeCluster(this.mMediaData, i2, i7);
    }

    public Cluster getCluster(int i2) {
        Cluster cluster;
        if (GridValue.isFake(i2) && (cluster = this.mFakeCluster) != null) {
            return cluster;
        }
        return this.mClusters[this.mListView.getGridSpans().clusterOf(i2)];
    }

    public ClusterItem getClusterItem(int i2) {
        return this.mCurrentCluster.getClusterItem(getHeaderDeltaPosition(i2));
    }

    public int[] getClusterItemRange(MediaItem mediaItem) {
        return this.mCurrentCluster.getClusterItemRange(mediaItem);
    }

    public int getCount() {
        return this.mCurrentCluster.getCount();
    }

    public int getDataPosition(int i2) {
        return this.mCurrentCluster.getDataPosition(getHeaderDeltaPosition(i2));
    }

    public Integer getDateCount(int i2) {
        return this.mCurrentCluster.getDateCount(getHeaderDeltaPosition(i2));
    }

    public String getDateIds(int i2) {
        return this.mCurrentCluster.getDateIds(getHeaderDeltaPosition(i2));
    }

    public int getDividerIndex(int i2) {
        return this.mCurrentCluster.getDividerIndex(getHeaderDeltaPosition(i2));
    }

    public ArrayList<Pair<String, Integer>> getDividerScroll(int i2, int i7, int i8, int i10, int i11) {
        return this.mCurrentCluster.getDividerScroll(i2, i7, i8, i10, i11);
    }

    public ArrayList<Integer> getDividers() {
        return this.mCurrentCluster.getDividers();
    }

    public ClusterItem getHintClusterItem(int i2, int i7) {
        return getCluster(i7).getClusterItem(getHeaderDeltaPosition(i2));
    }

    public int getHintColumnSpan(int i2, int i7) {
        return getCluster(i7).getColumnSpan(getHeaderDeltaPosition(i2), getRealGridSize(i7));
    }

    public int getHintDataCount(int i2, int i7) {
        ArrayList<Integer> hintDataPositionList = getHintDataPositionList(i2, i7);
        if (hintDataPositionList != null) {
            return hintDataPositionList.size();
        }
        return 0;
    }

    public int getHintDataPosition(int i2, int i7) {
        return getCluster(i7).getDataPosition(getHeaderDeltaPosition(i2));
    }

    public ArrayList<Integer> getHintDataPositionList(int i2, int i7) {
        return getCluster(i7).getDataPositionList(getHeaderDeltaPosition(i2));
    }

    public int getHintExtraOffset(int i2, int i7, int i8) {
        return getCluster(i8).getExtraOffset(getHeaderDeltaPosition(i2), i7, getRealGridSize(i8));
    }

    public int getHintItemCount(int i2) {
        return getCluster(i2).getCount();
    }

    public int getHintItemHeight(int i2, int i7) {
        return getCluster(i7).getItemHeight(getHeaderDeltaPosition(i2), getRealGridSize(i7));
    }

    public int getHintItemViewType(int i2, int i7) {
        return getCluster(i7).getItemViewType(getHeaderDeltaPosition(i2));
    }

    public int getHintNextDividerIndex(int i2, int i7) {
        return getCluster(i7).getNextDividerIndex(getHeaderDeltaPosition(i2));
    }

    public int getHintNextRowIndex(int i2, int i7, int i8) {
        int i10;
        int i11 = i2 + 1;
        if (isFirstColumn(i11, i7)) {
            return Math.min(i8 - 1, i11);
        }
        do {
            i11++;
            i10 = i8 - 1;
            if (i11 >= i10 || isFirstColumn(i11, i7)) {
            }
            i11++;
            i10 = i8 - 1;
            break;
        } while (isFirstColumn(i11, i7));
        return Math.min(i10, i11);
    }

    public int getHintPrevDividerIndex(int i2, int i7) {
        return getCluster(i7).getPrevDividerIndex(getHeaderDeltaPosition(i2));
    }

    public int getHintPrevRowIndex(int i2, int i7) {
        int i8 = i2 - 1;
        if (isFirstColumn(i2, i7)) {
            return Math.max(0, i8);
        }
        while (true) {
            if (i8 <= 0) {
                break;
            }
            int i10 = i8 - 1;
            if (isFirstColumn(i8, i7)) {
                i8 = i10;
                break;
            }
            i8 = i10;
        }
        return Math.max(0, i8);
    }

    public int getHintRowCount(int i2) {
        return getCluster(i2).getRowCount(getRealGridSize(i2));
    }

    public Integer[] getHintRowInfo(int i2, int i7) {
        return getCluster(i7).getRowInfo(getHeaderDeltaPosition(i2), getRealGridSize(i7));
    }

    public int getHintRowSpan(int i2, int i7) {
        return getCluster(i7).getRowSpan(getHeaderDeltaPosition(i2), getRealGridSize(i7));
    }

    public SpanInfo getHintSpanInfo(int i2, int i7) {
        return getCluster(i7).getSpanInfo(getHeaderDeltaPosition(i2), getRealGridSize(i7));
    }

    public int getHintViewCount(int i2) {
        return getCluster(i2).getViewCount();
    }

    public int getHintViewPosition(int i2, int i7) {
        return getCluster(i7).getViewPosition(i2);
    }

    public synchronized ArrayList<MediaItem> getHintYearItemList(int i2, int i7) {
        return getCluster(i2).getYearItemList(i7);
    }

    public int getItemCount() {
        return this.mMediaData.getCount();
    }

    public int getMaxScroll(int i2, float f, int i7, int i8) {
        return this.mCurrentCluster.getMaxScroll(i2, f, i7, i8);
    }

    public int getScrollIndex(int i2) {
        return this.mCurrentCluster.getScrollIndex(getHeaderDeltaPosition(i2));
    }

    public String[] getScrollIndexDates() {
        return this.mCurrentCluster.getScrollIndexDates();
    }

    public ScrollText getScrollText(int i2) {
        return this.mCurrentCluster.getScrollText(getHeaderDeltaPosition(i2));
    }

    public int getSpanCount(int i2) {
        return getCluster(i2).getSpanCount();
    }

    public int getStartSpan(int i2, int i7) {
        return getCluster(i7).getStartSpan(getHeaderDeltaPosition(i2), getRealGridSize(i7));
    }

    public int getViewPosition(int i2) {
        return this.mCurrentCluster.getViewPosition(i2);
    }

    public boolean isDivider(int i2) {
        return this.mCurrentCluster.isDivider(getHeaderDeltaPosition(i2));
    }

    public boolean isHeader(int i2) {
        return false;
    }

    public void notifyDataChanged(GalleryRecyclerView galleryRecyclerView, boolean z, boolean z3) {
        this.mClusters = createCluster(galleryRecyclerView, this.mMediaData, z, z3);
    }

    public void onGridSizeChanged(int i2) {
        getCluster(i2).onGridSizeChanged(i2);
    }

    public void recalculateDayPosition(int i2) {
        Cluster cluster = this.mClusters[0];
        if (cluster != null) {
            cluster.recalculatePosition(i2);
        }
    }

    public void recalculateMonthForViewingPosition(int i2) {
        Cluster cluster = this.mClusters[4];
        if (cluster != null) {
            cluster.recalculatePosition(i2);
        }
    }

    public void recalculateRealRatioPosition(int i2) {
        Cluster cluster = this.mClusters[3];
        if (cluster != null) {
            cluster.recalculatePosition(i2);
        }
    }

    public void recalculateYearPosition(int i2) {
        Cluster cluster = this.mClusters[2];
        if (cluster != null) {
            cluster.recalculatePosition(i2);
        }
    }

    public void resetFakeCluster() {
        this.mFakeCluster = null;
    }

    public boolean supportExpand(int i2) {
        return this.mCurrentCluster.supportExpand(getHeaderDeltaPosition(i2));
    }

    public synchronized void updateCluster(int i2) {
        this.mCurrentCluster = getCluster(i2);
    }

    public int getDividerIndex(long j2) {
        return this.mCurrentCluster.getDividerIndex(j2);
    }

    public int getHintDataPosition(int i2, float f, float f5, int i7) {
        return getCluster(i7).getDataPosition(getHeaderDeltaPosition(i2), f, f5, getRealGridSize(i7));
    }

    public boolean isDivider(int i2, int i7) {
        return getCluster(i7).isDivider(getHeaderDeltaPosition(i2));
    }

    public int getDividerIndex(MediaItem mediaItem) {
        return (isHeader(0) ? 1 : 0) + this.mCurrentCluster.getDividerIndex(mediaItem);
    }

    public void recalculateMonthForViewingPosition(int i2, int i7) {
        Cluster cluster = this.mClusters[4];
        if (cluster != null) {
            cluster.recalculatePosition(i2, i7);
        }
    }

    public MultiClusterAdapter(MediaData mediaData) {
        this.mMediaData = mediaData;
    }

    public int getHeaderDeltaPosition(int i2) {
        return i2;
    }
}
