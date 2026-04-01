package com.samsung.android.gallery.module.dataset;

import A.a;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.type.SuggestionKeyword;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeDuration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataFilter extends MediaDataEntire {
    /* access modifiers changed from: private */
    public final Comparator<MediaItem> mComparator = new Comparator<MediaItem>() {
        public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
            String title = mediaItem.getTitle();
            String title2 = mediaItem2.getTitle();
            if (title2 == null || title == null) {
                return 0;
            }
            String lowerCase = MediaDataFilter.this.mKeyword.toLowerCase();
            int indexOf = title.toLowerCase().indexOf(lowerCase) - title2.toLowerCase().indexOf(lowerCase);
            return indexOf == 0 ? title.compareToIgnoreCase(title2) : indexOf;
        }
    };
    /* access modifiers changed from: private */
    public final MediaDataFilteredList mFilteredList;
    /* access modifiers changed from: private */
    public String mKeyword;
    /* access modifiers changed from: private */
    public MediaDataSearch mMediaDataSearch;
    private final MediaData.SimpleDataChangeListener mSearchDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            if (MediaDataFilter.this.mRwLock.acquireWriteLock()) {
                if (MediaDataFilter.this.mKeyword != null) {
                    Log.d(MediaDataFilter.this.TAG, "onSearchDataChanged");
                    MediaDataFilter.this.mFilteredList.filterMediaItems();
                }
                MediaDataFilter.this.mRwLock.releaseWriteLock();
            }
        }
    };

    public MediaDataFilter(Blackboard blackboard, String str) {
        super(blackboard, str);
        this.mFilteredList = new MediaDataFilteredList(blackboard, str);
    }

    /* access modifiers changed from: private */
    public boolean filter(String str, String str2) {
        if (TextUtils.isEmpty(str2) || !str.toLowerCase().contains(str2.toLowerCase())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void notifyChangedFilterSet() {
        notifyChanged();
    }

    /* access modifiers changed from: private */
    public void onKeywordChanged(Object obj, Bundle bundle) {
        if (this.mRwLock.acquireWriteLock()) {
            this.mKeyword = (String) obj;
            MediaDataFilteredList mediaDataFilteredList = this.mFilteredList;
            if (mediaDataFilteredList != null) {
                mediaDataFilteredList.filterMediaItems();
            }
            this.mRwLock.releaseWriteLock();
        }
    }

    /* access modifiers changed from: private */
    public void updateTitleForTime(MediaItem mediaItem) {
        if (SuggestionKeyword.TIME.name().equals(mediaItem.getSubCategory())) {
            mediaItem.setTitle(TranslationManager.getInstance().getTranslatedString(mediaItem.getCategory(), TimeDuration.calculateDuration(mediaItem.getDateTaken()).toString()));
        }
    }

    public void close() {
        this.mMediaDataSearch.unregister(this.mSearchDataChangeListener);
        this.mMediaDataSearch.close();
        super.close();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://event/KeywordChanged", new C0594c(this, 1)));
    }

    public int getCount() {
        try {
            acquireReadLock("getCount");
            return this.mFilteredList.getCount();
        } finally {
            releaseReadLock("getCount");
        }
    }

    public void onDestroy() {
        this.mKeyword = null;
        this.mFilteredList.onDestroy();
        super.onDestroy();
    }

    public void onInitDone() {
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
        String str = this.mKeyword;
        if (str == null) {
            str = ArgumentsUtil.getArgValue(readLocationKeyCurrent, "keyword");
        }
        this.mKeyword = str;
        if (isFullyLoaded()) {
            this.mFilteredList.filterMediaItems();
        }
    }

    public void onNotifyFullLoaded() {
        if (this.mRwLock.acquireWriteLock()) {
            MediaDataFilteredList mediaDataFilteredList = this.mFilteredList;
            if (mediaDataFilteredList != null) {
                mediaDataFilteredList.filterMediaItems();
            }
            this.mRwLock.releaseWriteLock();
        }
    }

    public MediaData open(String str, boolean z) {
        MediaDataSearch mediaDataSearch = (MediaDataSearch) MediaDataFactory.getInstance(getBlackboard()).open(LocationKey.getCollectionDataKey(), true);
        this.mMediaDataSearch = mediaDataSearch;
        mediaDataSearch.register(this.mSearchDataChangeListener);
        return super.open(str, z);
    }

    public MediaItem read(int i2) {
        try {
            acquireReadLock("read");
            return this.mFilteredList.read(i2);
        } finally {
            releaseReadLock("read");
        }
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        try {
            acquireReadLock("readAsync");
            this.mFilteredList.readAsync(i2, onDataReadListener, (BooleanSupplier) null);
        } finally {
            releaseReadLock("readAsync");
        }
    }

    public MediaItem readCache(int i2) {
        try {
            acquireReadLock("readCache");
            return this.mFilteredList.readCache(i2);
        } finally {
            releaseReadLock("readCache");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MediaDataFilteredList extends MediaDataRef {
        private final ArrayList<MediaItem> mFilteredItems = new ArrayList<>();
        private ArrayList<String> mTitleList = new ArrayList<>();

        public MediaDataFilteredList(Blackboard blackboard, String str) {
            super(blackboard, str);
        }

        private void addToFilteredList(ArrayList<MediaItem> arrayList, MediaItem mediaItem) {
            try {
                MediaDataFilter.this.updateTitleForTime(mediaItem);
                String title = mediaItem.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    String trim = title.trim();
                    MediaDataFilter mediaDataFilter = MediaDataFilter.this;
                    if (mediaDataFilter.filter(trim, mediaDataFilter.mKeyword.trim()) && !duplicateKeyword(trim)) {
                        arrayList.add(mediaItem);
                        this.mTitleList.add(trim);
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        private boolean duplicateKeyword(String str) {
            Iterator<String> it = this.mTitleList.iterator();
            while (it.hasNext()) {
                if (it.next().equals(str)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public void filterMediaItems() {
            String str;
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("collect filtered items - start : ");
            if (Logger.isAllowPrivateLog()) {
                str = MediaDataFilter.this.mKeyword;
            } else {
                str = "";
            }
            sb2.append(str);
            Log.d(stringCompat, sb2.toString());
            ArrayList arrayList = new ArrayList();
            ArrayList<MediaItem> allItems = MediaDataFilter.this.mMediaDataSearch.getAllItems();
            if (allItems != null) {
                Iterator<MediaItem> it = allItems.iterator();
                while (it.hasNext()) {
                    addToFilteredList(arrayList, it.next());
                }
            }
            if (arrayList.size() != 0 || MediaDataFilter.this.mDataTable != null) {
                int i2 = 0;
                while (i2 < MediaDataFilter.this.mDataTable.getLoadedCount()) {
                    try {
                        MediaItem temp = MediaDataFilter.this.mDataTable.getTemp(i2);
                        if (temp != null) {
                            addToFilteredList(arrayList, temp);
                        }
                        i2++;
                    } catch (Exception e) {
                        a.r(e, new StringBuilder("filterMediaItems failed while reading e="), this.TAG);
                    }
                }
                arrayList.sort(MediaDataFilter.this.mComparator);
                runOnUiThread(new B(0, this, arrayList));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$filterMediaItems$0(ArrayList arrayList) {
            this.mFilteredItems.clear();
            this.mFilteredItems.addAll(arrayList);
            this.mDataCount = this.mFilteredItems.size();
            this.mTitleList.clear();
            MediaDataFilter.this.notifyChangedFilterSet();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "collect filtered items - end : " + this.mDataCount);
        }

        public MediaItem read(int i2) {
            try {
                MediaItem mediaItem = this.mFilteredItems.get(i2);
                if (mediaItem.getFileId() != 0 || mediaItem.getFileSize() != 0) {
                    return mediaItem;
                }
                MediaItem createBrokenMedia = MediaItemBuilder.createBrokenMedia();
                Log.d(this.TAG, "replace dummy to broken");
                return createBrokenMedia;
            } catch (IndexOutOfBoundsException e) {
                StringCompat stringCompat = this.TAG;
                StringBuilder o2 = C0086a.o(i2, "[Postion]: ", " [Size]: ");
                o2.append(this.mFilteredItems.size());
                o2.append(" ->");
                o2.append(e.toString());
                Log.e(stringCompat, o2.toString());
                return null;
            }
        }

        public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
            if (this.mFilteredItems.isEmpty() || i2 >= this.mFilteredItems.size()) {
                StringCompat stringCompat = this.TAG;
                StringBuilder o2 = C0086a.o(i2, "read async fail. mList is empty : ", "/");
                o2.append(this.mFilteredItems.size());
                Log.e(stringCompat, o2.toString());
                return;
            }
            onDataReadListener.onDataReadCompleted(read(i2));
        }

        public MediaItem readCache(int i2) {
            return read(i2);
        }

        public void requestData(String str) {
        }
    }
}
