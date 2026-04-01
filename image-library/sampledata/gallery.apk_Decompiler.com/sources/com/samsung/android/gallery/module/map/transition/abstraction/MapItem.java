package com.samsung.android.gallery.module.map.transition.abstraction;

import U3.a;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapPresenterV2;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.map.clustering.IClusterItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MapItem implements IClusterItem {
    private long mFileId;
    private boolean mIsEntryItem;
    private ThumbnailInterface mMediaItem;
    private final double[] mPosition;
    private MediaItemProvider mProvider;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MediaItemProvider {
    }

    public MapItem(long j2, double d, double d2, boolean z, MediaItemProvider mediaItemProvider) {
        this.mFileId = j2;
        this.mProvider = mediaItemProvider;
        this.mPosition = new double[]{d, d2};
        this.mIsEntryItem = z;
        if (z) {
            Log.d("MapItem", "Create entry MapItem", Long.valueOf(j2), Double.valueOf(d), Double.valueOf(d2));
        }
    }

    public long getFileId() {
        return this.mFileId;
    }

    public ThumbnailInterface getMediaItem() {
        if (this.mMediaItem == null) {
            MediaItemProvider mediaItemProvider = this.mProvider;
            this.mMediaItem = ClusteringMapPresenterV2.lambda$addClusterItem$2((MediaData) ((a) mediaItemProvider).e, this.mFileId);
        }
        return this.mMediaItem;
    }

    public double[] getPosition() {
        return this.mPosition;
    }

    public boolean isEntryItem() {
        return this.mIsEntryItem;
    }

    public boolean isItemIncluded(long j2) {
        if (this.mFileId == j2) {
            return true;
        }
        return false;
    }

    public void setEntryItem(boolean z) {
        this.mIsEntryItem = z;
    }

    public MapItem(ThumbnailInterface thumbnailInterface, double d, double d2, boolean z) {
        this.mFileId = thumbnailInterface.getFileId();
        this.mMediaItem = thumbnailInterface;
        this.mPosition = new double[]{d, d2};
        this.mIsEntryItem = z;
        if (z) {
            Log.d("MapItem", "Create entry MapItem", thumbnailInterface, Double.valueOf(d), Double.valueOf(d2));
        }
    }
}
