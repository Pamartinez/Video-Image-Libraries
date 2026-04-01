package com.samsung.android.gallery.module.dataset.tables;

import android.database.Cursor;
import c0.C0086a;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.sdk.scs.base.utils.Log;
import i.C0212a;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterTable extends DefaultTable<DefaultRecord> {
    private boolean mDisableDayMerge;
    private ClusterIndexer mIndexer;
    private ClusterIndexer mMonthIndexer;

    public ClusterTable(Cursor cursor) {
        super(cursor);
    }

    public void clearClusterIndexer() {
        this.mIndexer = null;
    }

    public void close() {
        ClusterIndexer clusterIndexer = this.mIndexer;
        if (clusterIndexer != null) {
            ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap = clusterIndexer.mTimelineItemMap;
            if (concurrentHashMap != null) {
                concurrentHashMap.clear();
            }
            this.mIndexer = null;
        }
        ClusterIndexer clusterIndexer2 = this.mMonthIndexer;
        if (clusterIndexer2 != null) {
            ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap2 = clusterIndexer2.mTimelineItemMap;
            if (concurrentHashMap2 != null) {
                concurrentHashMap2.clear();
            }
            this.mMonthIndexer = null;
        }
        super.close();
    }

    public MediaItem createMediaItem(Cursor cursor, int i2) {
        if (cursor.moveToPosition(i2)) {
            return MediaItemBuilder.createTimeline(cursor);
        }
        throw new ArrayIndexOutOfBoundsException(C0086a.i(i2, "fail to move : "));
    }

    public DefaultRecord createRecordInstance(MediaItem mediaItem) {
        return new DefaultRecord(mediaItem);
    }

    public ClusterIndexer getClusterIndexer(int i2) {
        if (this.mIndexer == null) {
            makeClusterIndex(i2);
        }
        return this.mIndexer;
    }

    public ClusterIndexer getClusterIndexerMonth(int i2) {
        ClusterIndexer clusterIndexer = new ClusterIndexer(!this.mDisableDayMerge);
        clusterIndexer.calculate(this, 1, i2);
        this.mMonthIndexer = clusterIndexer;
        return clusterIndexer;
    }

    public ClusterIndexer getClusterIndexerYear(int i2) {
        ClusterIndexer clusterIndexer = new ClusterIndexer(!this.mDisableDayMerge);
        clusterIndexer.calculate(this, 2, i2);
        this.mMonthIndexer = clusterIndexer;
        return clusterIndexer;
    }

    public int getFakeLoadingCount() {
        return 120;
    }

    public String getLog() {
        String str = this.TAG + Log.TAG_SEPARATOR + Integer.toHexString(hashCode()) + "{";
        if (this.mIndexer != null) {
            StringBuilder t = C0212a.t(str, "DAY(");
            t.append(this.mIndexer.getLog());
            t.append(")");
            str = t.toString();
        }
        if (this.mMonthIndexer != null) {
            StringBuilder t3 = C0212a.t(str, "\nMONTH(");
            t3.append(this.mMonthIndexer.getLog());
            t3.append(")");
            str = t3.toString();
        }
        return C0212a.A(str, "}");
    }

    public int getMaxPreloadCount() {
        return 9999999;
    }

    public boolean init() {
        super.init();
        boolean isFullLoaded = isFullLoaded();
        if (!isFullLoaded) {
            makeClusterIndex(120);
        }
        return isFullLoaded;
    }

    public boolean makeClusterIndex(int i2) {
        ClusterIndexer clusterIndexer = new ClusterIndexer(!this.mDisableDayMerge);
        clusterIndexer.calculate(this, i2);
        this.mIndexer = clusterIndexer;
        return clusterIndexer.isValid();
    }

    public void setDisableDayMerge() {
        this.mDisableDayMerge = true;
    }

    public String tag() {
        return "ClusterTable";
    }

    public ClusterTable(Cursor cursor, DefaultTable.OnLoadDoneListener onLoadDoneListener) {
        super(cursor, onLoadDoneListener);
    }
}
