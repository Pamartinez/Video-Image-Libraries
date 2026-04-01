package com.samsung.android.gallery.module.dataset;

import android.os.Bundle;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.ChapterIndexer;
import com.samsung.android.gallery.module.dataset.tables.CleanOutDuplicateClusterIndexer;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.module.dataset.tables.IMediaDataTable;
import com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer;
import com.samsung.android.gallery.module.dataset.tables.SpanIndexer;
import com.samsung.android.gallery.support.utils.UnsupportedApiException;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaData extends Closeable {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDataChangeListener {
        boolean isInstant() {
            return false;
        }

        void onDataChanged();

        void onDataRangeChanged(int i2, int i7);

        void onDataRangeChanged(int i2, int i7, Object obj);

        void onDataRangeInserted(int i2, int i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDataReadListener {
        void onDataReadCompleted(MediaItem mediaItem);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class SimpleDataChangeListener implements SimpleOnDataChangeListener {
        final boolean isInstant;

        public SimpleDataChangeListener() {
            this(false);
        }

        public boolean isInstant() {
            return this.isInstant;
        }

        public SimpleDataChangeListener(boolean z) {
            this.isInstant = z;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SimpleOnDataChangeListener extends OnDataChangeListener {
        void onDataRangeChanged(int i2, int i7) {
            onDataChanged();
        }

        void onDataRangeInserted(int i2, int i7) {
            onDataChanged();
        }

        void onDataRangeChanged(int i2, int i7, Object obj) {
            onDataChanged();
        }
    }

    boolean acquireWriteLock(String str) {
        return true;
    }

    void close();

    long createFolderAt(int i2, MediaItem mediaItem, int i7, String str) {
        return -1;
    }

    int createNewItem(String str, String str2, int i2, String str3, int i7) {
        return -1;
    }

    int findPosition(long j2) {
        throw new UnsupportedApiException();
    }

    int findPositionBy(Object obj) {
        return -1;
    }

    int findPositionByFileId(long j2) {
        return -1;
    }

    ArrayList<MediaItem> getAllData() {
        return new ArrayList<>();
    }

    LinkedHashMap<String, Integer> getAllDatesCount() {
        return null;
    }

    HashMap<String, String> getAllDatesIds() {
        return null;
    }

    ChapterIndexer getChapterIndexer() {
        return null;
    }

    int getChildCount() {
        return 0;
    }

    MediaData getChildMediaData(int i2) {
        return null;
    }

    CleanOutDuplicateClusterIndexer getCleanOutDuplicateClusterIndexer() {
        return null;
    }

    ClusterIndexer getClusterIndexer(int i2) {
        return null;
    }

    IMediaDataTable getClusterTable(int i2) {
        return null;
    }

    int getCount();

    String getDataHash() {
        return null;
    }

    int getDataVersion() {
        return 0;
    }

    ArrayList<MediaItem> getExtraData() {
        return null;
    }

    Bundle getExtras() {
        return null;
    }

    List<Long> getFileIds() {
        return null;
    }

    ArrayList<MediaItem> getFullData() {
        return getAllData();
    }

    String getLocationKey();

    String getLocationReference() {
        return null;
    }

    ArrayList<String> getPriorityItems() {
        return new ArrayList<>();
    }

    int getRealCount() {
        return 0;
    }

    RealRatioIndexer getRealRatioIndexer() {
        return null;
    }

    int getRefCount() {
        return 0;
    }

    SpanIndexer getSpanIndexer() {
        return null;
    }

    boolean isDataAvailable();

    boolean isFullyLoaded() {
        return true;
    }

    MediaData open(String str) {
        return open(str, false);
    }

    MediaData open(String str, boolean z);

    MediaItem read(int i2);

    void readAsync(int i2, OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier);

    MediaItem readById(long j2) {
        int findPositionByFileId = findPositionByFileId(j2);
        if (findPositionByFileId < 0) {
            return null;
        }
        return read(findPositionByFileId);
    }

    MediaItem readByKey(String str) {
        return null;
    }

    MediaItem readCache(int i2);

    void register(OnDataChangeListener onDataChangeListener);

    void replaceItemAt(int i2, MediaItem mediaItem) {
        throw new UnsupportedOperationException();
    }

    boolean supportDayCluster() {
        return false;
    }

    boolean supportMonthCluster() {
        return false;
    }

    boolean supportMonthXsCluster() {
        return false;
    }

    boolean supportYearCluster() {
        return false;
    }

    void unregister(OnDataChangeListener onDataChangeListener);

    int updateCoverItem(int i2, String str, String str2) {
        return -1;
    }

    int updateFolderAt(int i2, int i7, String str) {
        return -1;
    }

    int findPositionBy(Predicate<MediaItem> predicate) {
        int realCount = getRealCount();
        for (int i2 = 0; i2 < realCount; i2++) {
            MediaItem read = read(i2);
            if (read != null && predicate.test(read)) {
                return i2;
            }
        }
        return -1;
    }

    MediaData getChildMediaData(String str) {
        return null;
    }

    void notifyIndexingDone() {
    }

    void acquireReadLock(String str) {
    }

    void changeOrder(Comparator<MediaItem> comparator) {
    }

    int getPosition(int i2) {
        return i2;
    }

    void reInit(String str) {
    }

    void recall(boolean z) {
    }

    void releaseReadLock(String str) {
    }

    void releaseWriteLock(String str) {
    }

    void removeItemAt(int i2) {
    }

    void reopen(String str) {
    }

    void insertItemAt(int i2, MediaItem mediaItem) {
    }

    void reorderData(int i2, int i7) {
    }
}
