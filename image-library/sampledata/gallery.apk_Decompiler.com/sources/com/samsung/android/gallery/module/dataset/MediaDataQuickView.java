package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.module.cache.MemoryCacheMgr;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataQuickView extends MediaDataCursor {
    public MediaDataQuickView(Blackboard blackboard, String str) {
        super(blackboard, str);
        if (Features.isEnabled(Features.SUPPORT_PPP_V2)) {
            String argValue = ArgumentsUtil.getArgValue(str, "ppp_uri", "none");
            if (!"none".equals(argValue)) {
                preparePppObserver(argValue);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preparePppObserver$0(MediaItem mediaItem) {
        this.mPreloadedPppItem = mediaItem;
    }

    /* access modifiers changed from: private */
    public void onFirstData(Object obj, Bundle bundle) {
        MediaItem mediaItem = (MediaItem) obj;
        if (mediaItem != null && this.mDataCount < 1) {
            this.mRecentCache.addCache(0, mediaItem);
            this.mDataCount = 1;
        }
    }

    /* access modifiers changed from: private */
    public void onPppUpdate(MediaItem mediaItem) {
        this.mPppUpdater.onUpdatePppMediaItem(this, mediaItem, this.mBlackboard);
    }

    private void preparePppObserver(String str) {
        if (Features.isEnabled(Features.SUPPORT_PPP_V2)) {
            try {
                registerPppObserver(new Y(this, 0));
                preloadPppItem(str, new Y(this, 1));
            } catch (Exception unused) {
                Log.d(this.TAG, "preparePppObserver failed");
            }
        }
    }

    public /* bridge */ /* synthetic */ boolean acquireWriteLock(String str) {
        return super.acquireWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        subscribePppCompleted(arrayList);
        arrayList.add(new SubscriberInfo("data://viewer_first_data", new C0594c(this, 4)).setTriggerPreloadedData());
    }

    public int findPosition(long j2) {
        return findPositionBy(j2, "__fileMediaId");
    }

    public int findPositionBy(long j2, String str) {
        Cursor cursor;
        if (this.mLock.acquireWriteLock()) {
            try {
                Cursor[] cursorArr = this.mCursors;
                int i2 = 0;
                if (cursorArr == null || cursorArr.length <= 0) {
                    cursor = null;
                } else {
                    cursor = cursorArr[0];
                }
                if (cursor != null && cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(str);
                    if (columnIndex < 0) {
                        this.mLock.releaseWriteLock();
                        return -1;
                    }
                    do {
                        if (cursor.getLong(columnIndex) == j2) {
                            this.mLock.releaseWriteLock();
                            return i2;
                        }
                        i2++;
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                StringCompat stringCompat = this.TAG;
                Log.w(stringCompat, "findPositionBy fail : " + e.getMessage());
            } catch (Throwable th) {
                this.mLock.releaseWriteLock();
                throw th;
            }
            this.mLock.releaseWriteLock();
        }
        return -1;
    }

    public int findPositionByFileId(long j2) {
        return findPositionBy(j2, "__absID");
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

    public boolean isFilteredEvent(EventMessage eventMessage) {
        if (!Features.isEnabled(Features.SUPPORT_PPP_V2) || !this.mPppUpdater.isFilteredEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    public void notifyChanged(boolean z) {
        super.notifyChanged(z);
        if (z && this.mPreloadedPppItem != null) {
            Log.d(this.TAG, "pppUpdate with preloaded item");
            this.mPppUpdater.onUpdatePppMediaItem(this, this.mPreloadedPppItem, this.mBlackboard);
            this.mPreloadedPppItem = null;
        }
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        this.mPppUpdater.clear();
        if (Features.isEnabled(Features.SUPPORT_PPP_V2)) {
            unregisterPppObserver();
        }
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public boolean preload(MemoryCacheMgr<Integer, MediaItem> memoryCacheMgr, Cursor cursor) {
        if (this.mRecentCache.getSize() != 0 || cursor == null) {
            return super.preload(memoryCacheMgr, cursor);
        }
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
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

    public /* bridge */ /* synthetic */ MediaItem readById(long j2) {
        return super.readById(j2);
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

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ void unregister(MediaData.OnDataChangeListener onDataChangeListener) {
        super.unregister(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void updateDataStampByPpp(MediaItem mediaItem) {
        super.updateDataStampByPpp(mediaItem);
    }

    public void preloadThumbnail(MemoryCacheMgr<Integer, MediaItem> memoryCacheMgr) {
    }
}
