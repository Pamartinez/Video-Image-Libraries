package com.samsung.android.gallery.module.dataset;

import N2.j;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.SortableMergeCursor;
import com.samsung.android.gallery.module.data.HideRuleData;
import com.samsung.android.gallery.module.data.HideRuleItemLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataHideRule extends MediaDataCursor {
    private ArrayList<MediaItem> mHideRules;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HideRuleItem extends MediaItem {
        private MediaItem mBaseItem;
        private final ArrayList<MediaItem> mItemList = new ArrayList<>();

        public void addItem(MediaItem mediaItem) {
            this.mItemList.add(mediaItem);
            if (this.mBaseItem == null) {
                this.mBaseItem = mediaItem;
            }
        }

        public int getCount() {
            return this.mItemList.size();
        }

        public long getFileId() {
            MediaItem mediaItem = this.mBaseItem;
            if (mediaItem != null) {
                return mediaItem.getFileId();
            }
            return -1;
        }

        public int getItemCount() {
            return this.mItemList.size();
        }

        public MediaItem[] getItemsInFolder() {
            MediaItem[] mediaItemArr = new MediaItem[this.mItemList.size()];
            this.mItemList.toArray(mediaItemArr);
            return mediaItemArr;
        }
    }

    public MediaDataHideRule(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private HideRuleItem createHideRuleItem(int i2) {
        HideRuleItem hideRuleItem = new HideRuleItem();
        HideRuleData.of(hideRuleItem).hideRuleType = i2;
        return hideRuleItem;
    }

    private boolean equalsList(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        if (arrayList == null || arrayList.size() != arrayList2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (!isSame((HideRuleItem) arrayList.get(i2), (HideRuleItem) arrayList2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    private boolean isSame(HideRuleItem hideRuleItem, HideRuleItem hideRuleItem2) {
        return false;
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

    private void sort(Cursor cursor) {
        if (cursor instanceof SortableMergeCursor) {
            ((SortableMergeCursor) cursor).addOrderBy("__rule_id", false).addOrderBy("__dateTaken", false).addOrderBy("__absID", false).orderBy();
        }
    }

    private void swapInternal(ArrayList<MediaItem> arrayList) {
        this.mHideRules = arrayList;
        this.mDataCount = arrayList.size();
    }

    public ArrayList<MediaItem> getAllData() {
        return this.mHideRules;
    }

    public boolean isDataAvailable() {
        if (this.mHideRules == null || this.mDataCount < 0) {
            return false;
        }
        return true;
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        return false;
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (eventMessage.what == 1036) {
            return true;
        }
        return false;
    }

    public void onDestroy() {
        ArrayList<MediaItem> arrayList = this.mHideRules;
        if (arrayList != null) {
            arrayList.clear();
            this.mHideRules = null;
        }
        super.onDestroy();
    }

    public void preload(Cursor cursor, HideRuleItem hideRuleItem, int i2) {
        if (cursor != null && !cursor.isClosed()) {
            sort(cursor);
            if (cursor.moveToFirst()) {
                do {
                    hideRuleItem.addItem(HideRuleItemLoader.loadHideRuleMediaItem(cursor, i2));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    public MediaItem read(int i2) {
        try {
            ArrayList<MediaItem> arrayList = this.mHideRules;
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
        onDataReadListener.onDataReadCompleted(this.mHideRules.get(i2));
    }

    public MediaItem readCache(int i2) {
        return read(i2);
    }

    public void swap(Cursor[] cursorArr) {
        MediaDataHideRule mediaDataHideRule;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.mCursors = cursorArr;
            HideRuleItem createHideRuleItem = createHideRuleItem(0);
            HideRuleItem createHideRuleItem2 = createHideRuleItem(1);
            ArrayList arrayList = new ArrayList(Arrays.asList(new HideRuleItem[]{createHideRuleItem, createHideRuleItem2}));
            long currentTimeMillis2 = System.currentTimeMillis();
            preload(cursorArr[0], createHideRuleItem, 0);
            preload(cursorArr[1], createHideRuleItem2, 1);
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "preloadElapsed = " + (System.currentTimeMillis() - currentTimeMillis2));
            mediaDataHideRule = this;
            try {
                mediaDataHideRule.runOnUiThread(new C(mediaDataHideRule, arrayList, !equalsList(this.mHideRules, arrayList), currentTimeMillis, 0));
            } catch (Exception e) {
                e = e;
                Exception exc = e;
                mediaDataHideRule.mLock.releaseWriteLock();
                Log.e((CharSequence) mediaDataHideRule.TAG, "swap failed", (Throwable) exc);
            }
        } catch (Exception e7) {
            e = e7;
            mediaDataHideRule = this;
            Exception exc2 = e;
            mediaDataHideRule.mLock.releaseWriteLock();
            Log.e((CharSequence) mediaDataHideRule.TAG, "swap failed", (Throwable) exc2);
        }
    }
}
