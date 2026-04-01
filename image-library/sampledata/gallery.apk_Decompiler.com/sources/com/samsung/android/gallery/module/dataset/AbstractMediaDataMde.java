package com.samsung.android.gallery.module.dataset;

import A.a;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.StaleDataException;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractMediaDataMde extends MediaDataCursor {
    private ArrayList<MediaItem> mDataArray;
    private HashMap<String, MediaItem> mHashMap;

    public AbstractMediaDataMde(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private boolean equalsList(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        if (arrayList != null && arrayList.size() == arrayList2.size() && arrayList2.size() <= 1024) {
            try {
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (!equalsItem(arrayList.get(i2), arrayList2.get(i2))) {
                        return false;
                    }
                }
                return true;
            } catch (Exception e) {
                a.r(e, new StringBuilder("equalsList failed e="), this.TAG);
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$0(ArrayList arrayList, HashMap hashMap, Cursor[] cursorArr, boolean z, long j2) {
        String str;
        swapInternal(arrayList, hashMap, cursorArr);
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("swap");
        if (z) {
            str = "";
        } else {
            str = " skip";
        }
        sb2.append(str);
        sb2.append(" (");
        Log.d(stringCompat, C0086a.j(j2, this.mLocationKey, ") +", sb2));
        if (z) {
            notifyChanged();
        }
    }

    private void preload(Cursor cursor, ArrayList<MediaItem> arrayList, HashMap<String, MediaItem> hashMap) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    MediaItem loadMediaItem = loadMediaItem(cursor);
                    arrayList.add(loadMediaItem);
                    hashMap.put(MediaItemMde.getGroupId(loadMediaItem), loadMediaItem);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    private void swapInternal(ArrayList<MediaItem> arrayList, HashMap<String, MediaItem> hashMap, Cursor[] cursorArr) {
        this.mDataCount = arrayList.size();
        this.mDataArray = arrayList;
        this.mHashMap = hashMap;
    }

    public /* bridge */ /* synthetic */ boolean acquireWriteLock(String str) {
        return super.acquireWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getCount() != mediaItem2.getCount() || !TextUtils.equals(mediaItem.getTitle(), mediaItem2.getTitle()) || !TextUtils.equals(MediaItemMde.getGroupId(mediaItem), MediaItemMde.getGroupId(mediaItem2)) || !TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath())) {
            return false;
        }
        return true;
    }

    public ArrayList<MediaItem> getAllData() {
        return this.mDataArray;
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

    public abstract int getListeningEventKey();

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

    public boolean isDataAvailable() {
        if (this.mDataArray == null || this.mDataCount < 0) {
            return false;
        }
        return true;
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        return false;
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (eventMessage.what == getListeningEventKey()) {
            return true;
        }
        return false;
    }

    public boolean isValidPosition(int i2) {
        ArrayList<MediaItem> arrayList = this.mDataArray;
        if (arrayList == null || i2 < 0 || i2 >= arrayList.size()) {
            return false;
        }
        return true;
    }

    public abstract MediaItem loadMediaItem(Cursor cursor);

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        ArrayList<MediaItem> arrayList = this.mDataArray;
        if (arrayList != null) {
            arrayList.clear();
            this.mDataArray = null;
        }
        HashMap<String, MediaItem> hashMap = this.mHashMap;
        if (hashMap != null) {
            hashMap.clear();
            this.mHashMap = null;
        }
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public /* bridge */ /* synthetic */ void reInit(String str) {
        super.reInit(str);
    }

    public MediaItem read(int i2) {
        if (isValidPosition(i2)) {
            return this.mDataArray.get(i2);
        }
        return null;
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        onDataReadListener.onDataReadCompleted(read(i2));
    }

    public /* bridge */ /* synthetic */ MediaItem readById(long j2) {
        return super.readById(j2);
    }

    public MediaItem readByKey(String str) {
        HashMap<String, MediaItem> hashMap = this.mHashMap;
        if (hashMap != null) {
            return hashMap.get(str);
        }
        return null;
    }

    public MediaItem readCache(int i2) {
        if (isValidPosition(i2)) {
            return this.mDataArray.get(i2);
        }
        return null;
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
        AbstractMediaDataMde abstractMediaDataMde;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            preload(cursorArr[0], arrayList, hashMap);
            abstractMediaDataMde = this;
            try {
                abstractMediaDataMde.runOnUiThread(new C0590a(abstractMediaDataMde, arrayList, hashMap, cursorArr, !equalsList(this.mDataArray, arrayList), currentTimeMillis));
            } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException e) {
                e = e;
                Log.e((CharSequence) abstractMediaDataMde.TAG, "swap failed. BG error(cursor maybe closed on another thread. ignore exception)", e);
            }
        } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException e7) {
            e = e7;
            abstractMediaDataMde = this;
            Log.e((CharSequence) abstractMediaDataMde.TAG, "swap failed. BG error(cursor maybe closed on another thread. ignore exception)", e);
        }
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
