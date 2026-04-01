package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataMap extends MediaDataCursor {
    private ArrayList<MediaItem> mLocationDateInfo;
    private ConcurrentHashMap<Long, Integer> mPositionMap;

    public MediaDataMap(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private boolean hasLocationCursor(Cursor[] cursorArr) {
        if (cursorArr.length <= 7 || cursorArr[7] == null) {
            return false;
        }
        return true;
    }

    private void updateLocationDateInfo(Cursor cursor) {
        this.mLocationDateInfo = new ArrayList<>();
        this.mPositionMap = new ConcurrentHashMap<>();
        if (cursor != null && cursor.moveToFirst()) {
            int i2 = 0;
            while (true) {
                MediaItem createGpsDate = MediaItemBuilder.createGpsDate(cursor);
                this.mLocationDateInfo.add(createGpsDate);
                int i7 = i2 + 1;
                this.mPositionMap.put(Long.valueOf(createGpsDate.getFileId()), Integer.valueOf(i2));
                if (cursor.moveToNext()) {
                    i2 = i7;
                } else {
                    return;
                }
            }
        }
    }

    public /* bridge */ /* synthetic */ boolean acquireWriteLock(String str) {
        return super.acquireWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public /* bridge */ /* synthetic */ ArrayList getAllData() {
        return super.getAllData();
    }

    public /* bridge */ /* synthetic */ ClusterTable getClusterTable(int i2) {
        return super.getClusterTable(i2);
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public /* bridge */ /* synthetic */ int getDataVersion() {
        return super.getDataVersion();
    }

    public ArrayList<MediaItem> getExtraData() {
        return this.mLocationDateInfo;
    }

    public /* bridge */ /* synthetic */ ArrayList getFileIds() {
        return super.getFileIds();
    }

    public /* bridge */ /* synthetic */ String getLocationKey() {
        return super.getLocationKey();
    }

    public /* bridge */ /* synthetic */ String getLocationReference() {
        return super.getLocationReference();
    }

    public /* bridge */ /* synthetic */ int getPosition(int i2) {
        return super.getPosition(i2);
    }

    public /* bridge */ /* synthetic */ int getRealCount() {
        return super.getRealCount();
    }

    public /* bridge */ /* synthetic */ int getRefCount() {
        return super.getRefCount();
    }

    public /* bridge */ /* synthetic */ boolean isDataAvailable() {
        return super.isDataAvailable();
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        ArrayList<MediaItem> arrayList = this.mLocationDateInfo;
        if (arrayList != null) {
            arrayList.clear();
            this.mLocationDateInfo = null;
        }
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

    public MediaItem readById(long j2) {
        MediaItem readById = super.readById(j2);
        if (readById != null) {
            return readById;
        }
        Integer num = this.mPositionMap.get(Long.valueOf(j2));
        if (num != null) {
            return read(num.intValue());
        }
        return null;
    }

    public /* bridge */ /* synthetic */ MediaItem readCache(int i2) {
        return super.readCache(i2);
    }

    public /* bridge */ /* synthetic */ void register(MediaData.OnDataChangeListener onDataChangeListener) {
        super.register(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void releaseWriteLock(String str) {
        super.releaseWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void removeItemAt(int i2) {
        super.removeItemAt(i2);
    }

    public /* bridge */ /* synthetic */ void reopen(String str) {
        super.reopen(str);
    }

    public void swap(Cursor[] cursorArr) {
        if (hasLocationCursor(cursorArr)) {
            updateLocationDateInfo(cursorArr[7]);
        }
        super.swap(cursorArr);
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
