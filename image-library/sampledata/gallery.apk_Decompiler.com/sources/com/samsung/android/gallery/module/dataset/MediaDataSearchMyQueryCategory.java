package com.samsung.android.gallery.module.dataset;

import N2.j;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataSearchMyQueryCategory extends MediaDataSearchCategory {
    private ArrayList<MediaItem> mDataArray;

    public MediaDataSearchMyQueryCategory(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private boolean equalsList(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        if (arrayList == null || arrayList.size() != arrayList2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (!isSame(arrayList.get(i2), arrayList2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    private boolean isSame(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || !TextUtils.equals(mediaItem.getDisplayName(), mediaItem2.getDisplayName())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$0(ArrayList arrayList, boolean z, long j2) {
        if (this.mLock.acquireWriteLock()) {
            swapInternal(arrayList);
            if (z) {
                notifyChanged();
            }
            this.mLock.releaseWriteLock();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "swap > preparing {full," + this.mLocationKey + ",changed=" + z + ",count=" + arrayList.size() + "} +" + (System.currentTimeMillis() - j2));
        }
    }

    private void swapInternal(ArrayList<MediaItem> arrayList) {
        this.mDataArray = arrayList;
        this.mDataCount = arrayList.size();
    }

    public ArrayList<MediaItem> getAllData() {
        ArrayList<MediaItem> arrayList = this.mDataArray;
        if (arrayList == null) {
            return new ArrayList<>();
        }
        return arrayList;
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
        if (super.isListeningEvent(eventMessage) || eventMessage.what == 103) {
            return true;
        }
        return false;
    }

    public void onDestroy() {
        ArrayList<MediaItem> arrayList = this.mDataArray;
        if (arrayList != null) {
            arrayList.clear();
            this.mDataArray = null;
        }
        super.onDestroy();
    }

    public void preload(Cursor cursor, ArrayList<MediaItem> arrayList) {
        if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
            do {
                MediaItem load = MyQueryUtil.load(cursor);
                if (load != null) {
                    arrayList.add(load);
                }
            } while (cursor.moveToNext());
        }
    }

    public MediaItem read(int i2) {
        try {
            ArrayList<MediaItem> arrayList = this.mDataArray;
            if (arrayList != null) {
                return arrayList.get(i2);
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("position is an error {size=");
            j.x(sb2, this.mDataCount, ",pos=", i2, "} e=");
            sb2.append(e.getMessage());
            Log.d(stringCompat, sb2.toString());
            return null;
        }
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        onDataReadListener.onDataReadCompleted(this.mDataArray.get(i2));
    }

    public MediaItem readCache(int i2) {
        ArrayList<MediaItem> arrayList = this.mDataArray;
        if (arrayList != null) {
            return arrayList.get(i2);
        }
        return null;
    }

    public void recall(boolean z) {
        String str;
        if (z) {
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("recall#query ");
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr != null) {
                str = Logger.toString(cursorArr);
            } else {
                str = "null";
            }
            sb2.append(str);
            Log.d(stringCompat, sb2.toString());
            requestData(this.mLocationReference);
        }
    }

    public void swap(Cursor[] cursorArr) {
        MediaDataSearchMyQueryCategory mediaDataSearchMyQueryCategory;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            this.mCursors = cursorArr;
            preload(cursorArr[0], arrayList);
            mediaDataSearchMyQueryCategory = this;
            try {
                mediaDataSearchMyQueryCategory.runOnUiThread(new C(mediaDataSearchMyQueryCategory, arrayList, !equalsList(this.mDataArray, arrayList), currentTimeMillis, 2));
            } catch (Exception e) {
                e = e;
                Exception exc = e;
                mediaDataSearchMyQueryCategory.mLock.releaseWriteLock();
                Log.e((CharSequence) mediaDataSearchMyQueryCategory.TAG, "swap failed", (Throwable) exc);
            }
        } catch (Exception e7) {
            e = e7;
            mediaDataSearchMyQueryCategory = this;
            Exception exc2 = e;
            mediaDataSearchMyQueryCategory.mLock.releaseWriteLock();
            Log.e((CharSequence) mediaDataSearchMyQueryCategory.TAG, "swap failed", (Throwable) exc2);
        }
    }

    public MediaDataSearchMyQueryCategory(Blackboard blackboard, String str, boolean z) {
        super(blackboard, str, z);
    }
}
