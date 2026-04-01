package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataList;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.module.dataset.tables.IMediaDataTable;
import com.samsung.android.gallery.module.dataset.tables.ListClusterTable;
import com.samsung.android.gallery.module.dataset.tables.ListRealRatioIndexer;
import com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataClusterMapList extends MediaDataList {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaDataMapArray extends MediaDataList.MediaDataArray {
        private ListClusterTable mClusterTable;
        private ListRealRatioIndexer mRealRatioIndexer;

        public MediaDataMapArray(Blackboard blackboard, String str, MediaDataList mediaDataList) {
            super(blackboard, str, mediaDataList);
        }

        private void initClusterIndexer(ArrayList<MediaItem> arrayList) {
            ListClusterTable listClusterTable = new ListClusterTable((Cursor) null, arrayList);
            this.mClusterTable = listClusterTable;
            listClusterTable.init();
        }

        private void initRealRatioIndexer(ArrayList<MediaItem> arrayList) {
            if (!Features.isEnabled(Features.SUPPORT_REAL_RATIO)) {
                Log.d(this.TAG, "not support real ratio");
                this.mRealRatioIndexer = null;
            }
            ClusterIndexer clusterIndexer = this.mClusterTable.getClusterIndexer(arrayList.size());
            this.mRealRatioIndexer = new ListRealRatioIndexer(arrayList, clusterIndexer.getTimelineItemMap(), clusterIndexer.getCount());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$openInternal$0(MediaItem mediaItem, MediaItem mediaItem2) {
            int compare = Long.compare(mediaItem2.getDateTaken(), mediaItem.getDateTaken());
            if (compare == 0) {
                return Long.compare(mediaItem2.getFileId(), mediaItem.getFileId());
            }
            return compare;
        }

        public IMediaDataTable getClusterTable(int i2) {
            return this.mClusterTable;
        }

        public RealRatioIndexer getRealRatioIndexer() {
            return this.mRealRatioIndexer;
        }

        public void onDestroy() {
            Utils.closeSilently(this.mRealRatioIndexer);
            this.mRealRatioIndexer = null;
            Utils.closeSilently(this.mClusterTable);
            this.mClusterTable = null;
            super.onDestroy();
        }

        public void openInternal(String str) {
            super.openInternal(str);
            changeOrder(new C0607l(0));
            ArrayList<MediaItem> allData = getAllData();
            initClusterIndexer(allData);
            initRealRatioIndexer(allData);
        }

        public void reopen(String str) {
            clearList();
            openInternal(str);
        }

        public boolean supportDayCluster() {
            return true;
        }

        public boolean supportMonthCluster() {
            return true;
        }
    }

    public MediaDataClusterMapList(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public MediaDataList.MediaDataArray createArray(String str) {
        return new MediaDataMapArray(this.mBlackboard, str, this);
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public /* bridge */ /* synthetic */ String getLocationKey() {
        return super.getLocationKey();
    }

    public /* bridge */ /* synthetic */ boolean isDataAvailable() {
        return super.isDataAvailable();
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public /* bridge */ /* synthetic */ MediaItem read(int i2) {
        return super.read(i2);
    }

    public /* bridge */ /* synthetic */ void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        super.readAsync(i2, onDataReadListener, booleanSupplier);
    }

    public /* bridge */ /* synthetic */ MediaItem readCache(int i2) {
        return super.readCache(i2);
    }

    public /* bridge */ /* synthetic */ void register(MediaData.OnDataChangeListener onDataChangeListener) {
        super.register(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void unregister(MediaData.OnDataChangeListener onDataChangeListener) {
        super.unregister(onDataChangeListener);
    }
}
