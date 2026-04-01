package com.samsung.android.gallery.module.dataset;

import android.text.TextUtils;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer;
import com.samsung.android.gallery.module.dataset.tables.SpanIndexer;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataStoryBase extends MediaDataTimeline {
    public MediaDataStoryBase(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private boolean compare(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getFileId() != mediaItem2.getFileId() || mediaItem.getDateTaken() != mediaItem2.getDateTaken() || mediaItem.getTagType() != mediaItem2.getTagType() || !TextUtils.equals(mediaItem.getCategory(), mediaItem.getCategory()) || !TextUtils.equals(mediaItem.getSubCategory(), mediaItem.getSubCategory()) || !TextUtils.equals(mediaItem.getLocation(), mediaItem.getLocation()) || !TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath()) || !TextUtils.equals(mediaItem.getTitle(), mediaItem2.getTitle()) || !TextUtils.equals(DetailsData.of(mediaItem).capturedUrl, DetailsData.of(mediaItem2).capturedUrl) || MediaItemStory.getStoryCoverId(mediaItem) != MediaItemStory.getStoryCoverId(mediaItem2)) {
            return false;
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void acquireReadLock(String str) {
        super.acquireReadLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public boolean compareDataTable(DataTable dataTable) {
        try {
            DataTable dataTable2 = this.mDataTable;
            if (!(dataTable2 == null || dataTable == null)) {
                if (dataTable2.getRealCount() == dataTable.getRealCount()) {
                    for (int i2 = 0; i2 < this.mDataTable.getRealCount(); i2++) {
                        MediaItem mediaItem = this.mDataTable.get(i2);
                        if (mediaItem == null) {
                            mediaItem = this.mDataTable.loadAndGet(i2);
                        }
                        MediaItem mediaItem2 = dataTable.get(i2);
                        if (mediaItem2 == null) {
                            mediaItem2 = dataTable.loadAndGet(i2);
                        }
                        if (!compare(mediaItem, mediaItem2)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        } catch (NullPointerException unused) {
            Log.e(this.TAG, "fail to compare data table : " + this.mDataTable);
            if (this.mDataTable == null) {
                return true;
            }
            return false;
        }
    }

    public /* bridge */ /* synthetic */ int findPosition(long j2) {
        return super.findPosition(j2);
    }

    public /* bridge */ /* synthetic */ int findPositionByFileId(long j2) {
        return super.findPositionByFileId(j2);
    }

    public /* bridge */ /* synthetic */ ArrayList getAllData() {
        return super.getAllData();
    }

    public /* bridge */ /* synthetic */ ClusterIndexer getClusterIndexer(int i2) {
        return super.getClusterIndexer(i2);
    }

    public /* bridge */ /* synthetic */ ClusterTable getClusterTable(int i2) {
        return super.getClusterTable(i2);
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public /* bridge */ /* synthetic */ String getDataHash() {
        return super.getDataHash();
    }

    public /* bridge */ /* synthetic */ int getDataVersion() {
        return super.getDataVersion();
    }

    public /* bridge */ /* synthetic */ List getFileIds() {
        return super.getFileIds();
    }

    public /* bridge */ /* synthetic */ String getLocationKey() {
        return super.getLocationKey();
    }

    public /* bridge */ /* synthetic */ String getLocationReference() {
        return super.getLocationReference();
    }

    public /* bridge */ /* synthetic */ int getRealCount() {
        return super.getRealCount();
    }

    public /* bridge */ /* synthetic */ RealRatioIndexer getRealRatioIndexer() {
        return super.getRealRatioIndexer();
    }

    public /* bridge */ /* synthetic */ int getRefCount() {
        return super.getRefCount();
    }

    public /* bridge */ /* synthetic */ SpanIndexer getSpanIndexer() {
        super.getSpanIndexer();
        return null;
    }

    public /* bridge */ /* synthetic */ boolean isDataAvailable() {
        return super.isDataAvailable();
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        return false;
    }

    public /* bridge */ /* synthetic */ boolean isFullyLoaded() {
        return super.isFullyLoaded();
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 102) {
            return true;
        }
        if (!this.mForceSwap || i2 != 101) {
            return false;
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void notifyIndexingDone() {
        super.notifyIndexingDone();
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public /* bridge */ /* synthetic */ void reInit(String str) {
        super.reInit(str);
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

    public /* bridge */ /* synthetic */ void releaseReadLock(String str) {
        super.releaseReadLock(str);
    }

    public /* bridge */ /* synthetic */ void removeItemAt(int i2) {
        super.removeItemAt(i2);
    }

    public /* bridge */ /* synthetic */ void reopen(String str) {
        super.reopen(str);
    }

    public /* bridge */ /* synthetic */ void replaceItemAt(int i2, MediaItem mediaItem) {
        super.replaceItemAt(i2, mediaItem);
    }

    public boolean supportDayCluster() {
        return !isTimelineDisabled();
    }

    public boolean supportMonthCluster() {
        return false;
    }

    public /* bridge */ /* synthetic */ boolean supportMonthXsCluster() {
        return super.supportMonthXsCluster();
    }

    public /* bridge */ /* synthetic */ boolean supportYearCluster() {
        return super.supportYearCluster();
    }

    public /* bridge */ /* synthetic */ String toDebugString() {
        return super.toDebugString();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ void unregister(MediaData.OnDataChangeListener onDataChangeListener) {
        super.unregister(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void updateDataStampByPpp(MediaItem mediaItem) {
        super.updateDataStampByPpp(mediaItem);
    }
}
