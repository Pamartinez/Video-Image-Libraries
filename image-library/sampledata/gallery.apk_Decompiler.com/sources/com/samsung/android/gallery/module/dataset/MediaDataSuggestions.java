package com.samsung.android.gallery.module.dataset;

import N2.j;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.SuggestedType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.data.SuggestedItemLoader;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.suggested.SuggestedHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataSuggestions extends MediaDataCursor {
    private ArrayList<MediaItem> mDataArray;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MultipleItem extends MediaItem {
        private final ArrayList<MediaItem> mItemList = new ArrayList<>();

        public void addItem(MediaItem mediaItem) {
            this.mItemList.add(mediaItem);
        }

        public int getItemCount() {
            return this.mItemList.size();
        }

        public MediaItem[] getItemsInFolder() {
            MediaItem[] mediaItemArr = new MediaItem[this.mItemList.size()];
            this.mItemList.toArray(mediaItemArr);
            return mediaItemArr;
        }

        public String getPath() {
            if (this.mItemList.isEmpty()) {
                return null;
            }
            StringBuilder sb2 = new StringBuilder();
            Iterator<MediaItem> it = this.mItemList.iterator();
            while (it.hasNext()) {
                sb2.append(it.next().getPath());
                sb2.append("|");
            }
            return sb2.toString();
        }
    }

    public MediaDataSuggestions(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private void copyMediaItem(MediaItem mediaItem, MediaItem mediaItem2) {
        mediaItem2.setTitle(mediaItem.getTitle());
        MediaItemSuggest.setType(mediaItem2, MediaItemSuggest.getType(mediaItem));
        MediaItemSuggest.setExtra(mediaItem2, MediaItemSuggest.getExtra(mediaItem));
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

    private int getTypeOrder(int i2) {
        if (i2 == SuggestedType.REMASTER.toInt()) {
            return 0;
        }
        if (i2 == SuggestedType.PORTRAIT.toInt()) {
            return 1;
        }
        if (i2 == SuggestedType.HIGHLIGHT.toInt()) {
            return 2;
        }
        if (i2 == SuggestedType.CLEANOUT.toInt()) {
            return 4;
        }
        if (i2 == SuggestedType.CLEANOUT_MOTION_PHOTO_CLIP.toInt()) {
            return 5;
        }
        if (i2 == SuggestedType.CLEANOUT_DUPLICATED_IMAGE.toInt()) {
            return 3;
        }
        if (i2 == SuggestedType.CLEANOUT_BURST_SIMILAR.toInt()) {
            return 6;
        }
        return Integer.MAX_VALUE;
    }

    private boolean isSame(MediaItem mediaItem, MediaItem mediaItem2) {
        if (MediaItemSuggest.getType(mediaItem) == MediaItemSuggest.getType(mediaItem2) && mediaItem.getAlbumID() == mediaItem2.getAlbumID() && MediaItemStory.getStoryNotifyStatus(mediaItem) == MediaItemStory.getStoryNotifyStatus(mediaItem2) && MediaItemStory.getStoryId(mediaItem) == MediaItemStory.getStoryId(mediaItem2) && MediaItemStory.getStoryType(mediaItem) == MediaItemStory.getStoryType(mediaItem2) && mediaItem.getCount() == mediaItem2.getCount() && MediaItemSuggest.getCleanOutState(mediaItem) == MediaItemSuggest.getCleanOutState(mediaItem2) && MediaItemSuggest.getCleanOutDeleteType(mediaItem) == MediaItemSuggest.getCleanOutDeleteType(mediaItem2) && MediaItemSuggest.getRevitalizedType(mediaItem) == MediaItemSuggest.getRevitalizedType(mediaItem2) && mediaItem.getOrientation() == mediaItem2.getOrientation() && TextUtils.equals(MediaItemSuggest.getExtraData(mediaItem), MediaItemSuggest.getExtraData(mediaItem2))) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int lambda$preload$3(MediaItem mediaItem) {
        return getTypeOrder(MediaItemSuggest.getType(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$2(ArrayList arrayList, boolean z, long j2) {
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

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateNewBadgeInfo$0(int i2) {
        notifyDataRangeChanged(0, i2, "update_new_badge");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateNewBadgeInfo$1(int i2) {
        SuggestedHelper.getInstance().updatePreferenceForTab();
        runOnUiThread(new w0(this, i2, 1));
    }

    private void loadMultipleItem(MediaItem mediaItem, Cursor cursor, HashMap<Integer, MultipleItem> hashMap, ArrayList<MediaItem> arrayList) {
        MultipleItem multipleItem = new MultipleItem();
        multipleItem.addItem(mediaItem);
        multipleItem.setAlbumID(mediaItem.getAlbumID());
        MediaItemSuggest.setType(multipleItem, MediaItemSuggest.getType(mediaItem));
        MediaItemSuggest.setCleanOutDeleteType(multipleItem, MediaItemSuggest.getCleanOutDeleteType(mediaItem));
        MediaItemSuggest.setRevitalizedType(multipleItem, MediaItemSuggest.getRevitalizedType(mediaItem));
        multipleItem.setCount(mediaItem.getCount());
        multipleItem.setOrientation(mediaItem.getOrientation());
        hashMap.put(-100, multipleItem);
        arrayList.add(multipleItem);
        String extraData = MediaItemSuggest.getExtraData(mediaItem);
        MediaItemSuggest.setExtraData(multipleItem, extraData);
        if (!TextUtils.isEmpty(extraData)) {
            for (String str : extraData.split("\\|")) {
                MediaItem mediaItem2 = new MediaItem();
                copyMediaItem(mediaItem, mediaItem2);
                if (!TextUtils.isEmpty(str)) {
                    MediaItemBuilder.setSuggestExtraData(mediaItem2, str);
                }
                multipleItem.addItem(mediaItem2);
            }
        }
    }

    private void swapInternal(ArrayList<MediaItem> arrayList) {
        this.mDataArray = arrayList;
        this.mDataCount = arrayList.size();
    }

    private void updateNewBadgeInfo(int i2) {
        SimpleThreadPool.getInstance().execute(new w0(this, i2, 0));
    }

    public ArrayList<MediaItem> getAllData() {
        return this.mDataArray;
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
        if (eventMessage.what == 1032) {
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
        if (cursor != null && !cursor.isClosed()) {
            HashMap hashMap = new HashMap();
            if (cursor.moveToFirst()) {
                do {
                    MediaItem loadSuggestedMediaItem = SuggestedItemLoader.loadSuggestedMediaItem(cursor);
                    if (loadSuggestedMediaItem != null) {
                        if (MediaItemSuggest.isCleanOutGroup(loadSuggestedMediaItem) || MediaItemSuggest.isIntelligentGroup(loadSuggestedMediaItem)) {
                            loadMultipleItem(loadSuggestedMediaItem, cursor, hashMap, arrayList);
                        } else {
                            arrayList.add(loadSuggestedMediaItem);
                        }
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            arrayList.sort(Comparator.comparingInt(new v0(this, 0)));
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

    public void swap(Cursor[] cursorArr) {
        Exception exc;
        MediaDataSuggestions mediaDataSuggestions;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            this.mCursors = cursorArr;
            preload(cursorArr[0], arrayList);
            boolean equalsList = equalsList(this.mDataArray, arrayList);
            boolean z = !equalsList;
            if (!equalsList) {
                try {
                    updateNewBadgeInfo(arrayList.size());
                } catch (Exception e) {
                    exc = e;
                    mediaDataSuggestions = this;
                }
            }
            mediaDataSuggestions = this;
            try {
                mediaDataSuggestions.runOnUiThread(new C(mediaDataSuggestions, arrayList, z, currentTimeMillis, 1));
            } catch (Exception e7) {
                e = e7;
                exc = e;
                mediaDataSuggestions.mLock.releaseWriteLock();
                Log.e((CharSequence) mediaDataSuggestions.TAG, "swap failed", (Throwable) exc);
            }
        } catch (Exception e8) {
            e = e8;
            mediaDataSuggestions = this;
            exc = e;
            mediaDataSuggestions.mLock.releaseWriteLock();
            Log.e((CharSequence) mediaDataSuggestions.TAG, "swap failed", (Throwable) exc);
        }
    }
}
