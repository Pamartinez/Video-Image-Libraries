package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.StaleDataException;
import android.os.Bundle;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataSearch extends MediaDataCursor {
    private Cursor[] mBackupCursors;
    /* access modifiers changed from: private */
    public final ArrayList<String> mCategoryLocationKey = new ArrayList<>();
    private final ConcurrentHashMap<String, MediaDataCursor> mCategoryMap = new ConcurrentHashMap<>();
    private final boolean mIsPickMode;

    public MediaDataSearch(Blackboard blackboard, String str) {
        super(blackboard, str);
        this.mIsPickMode = PickerUtil.isPickerMode(blackboard);
    }

    private boolean checkHiddenItemExisted(Cursor cursor, String str) {
        if (cursor.getExtras() != null) {
            if (!PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE || !LocationKey.isSearchCategoryPeopleMatch(str)) {
                if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER) && LocationKey.isSearchCategoryPeopleAndPetsMatch(str)) {
                    int i2 = cursor.getExtras().getInt("hiddenPeopleCount", 0);
                    int i7 = cursor.getExtras().getInt("hiddenPetsCount", 0);
                    if (i2 > 0 || i7 > 0) {
                        return true;
                    }
                    return false;
                }
            } else if (cursor.getExtras().getInt("hiddenPeopleCount", 0) > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private MediaData.SimpleDataChangeListener createDataChangeListener(final String str) {
        return new MediaData.SimpleDataChangeListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onDataChanged$0(String str) {
                int indexOf = MediaDataSearch.this.mCategoryLocationKey.indexOf(str);
                if (indexOf >= 0 && indexOf < MediaDataSearch.this.mCategoryLocationKey.size()) {
                    MediaDataSearch.this.notifyDataRangeChanged(indexOf, 1);
                }
            }

            public void onDataChanged() {
                ThreadUtil.runOnUiThread(new B(2, this, str));
            }
        };
    }

    /* access modifiers changed from: private */
    public MediaDataCursor getMediaDataSearchCategory(String str) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE && LocationKey.isSearchCategoryCreatureMatch(str)) {
            return new MediaDataSearchCreatureCategory(this.mBlackboard, str);
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY && LocationKey.isSearchCategoryMyQuery(str)) {
            return new MediaDataSearchMyQueryCategory(this.mBlackboard, str);
        }
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT && LocationKey.isSearchCategoryScreenShot(str)) {
            return new MediaDataSearchScreenShotCategory(this.mBlackboard, str, false);
        }
        if (!PreferenceFeatures.OneUi8x.COLLECTION_TAB || !LocationKey.isSearchCategoryStories(str)) {
            return new MediaDataSearchCategory(this.mBlackboard, str);
        }
        MediaDataSearchStories wrap = new MediaDataSearchStories(this.mBlackboard, str).wrap("location://story/albums");
        wrap.register(createDataChangeListener(str));
        return wrap;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$makeOrderedCategoryList$4(Map map, ArrayList arrayList, Integer num, String str) {
        if (map.containsKey(str)) {
            arrayList.add(str);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$removeIfDisabled$5(String str) {
        return !VisualSearchCategory.isEnabled(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$removeIfNoData$6(String str) {
        if (this.mCategoryMap.get(str) == null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$swap$0(Cursor[] cursorArr, Integer num, String str) {
        return Boolean.valueOf(swapChild(str, cursorArr[num.intValue()]));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$1(ArrayList arrayList, long j2) {
        if (this.mLock.acquireWriteLock()) {
            try {
                this.mCategoryLocationKey.clear();
                this.mCategoryLocationKey.addAll(arrayList);
                this.mDataCount = this.mCategoryLocationKey.size();
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "swap (" + this.mLocationKey + ",true," + this.mDataCount + ") +" + (System.currentTimeMillis() - j2));
                notifyChanged();
            } finally {
                this.mLock.releaseWriteLock();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$swapPartialCursors$2(Cursor[] cursorArr, Integer num, String str) {
        return Boolean.valueOf(swapChild(str, cursorArr[num.intValue()]));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swapPartialCursors$3(ArrayList arrayList, long j2) {
        if (this.mLock.acquireWriteLock()) {
            this.mCategoryLocationKey.clear();
            this.mCategoryLocationKey.addAll(arrayList);
            this.mDataCount = this.mCategoryLocationKey.size();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "swap > partial swap (" + this.mLocationKey + ",true," + this.mDataCount + ") +" + (System.currentTimeMillis() - j2));
            notifyChanged();
            this.mLock.releaseWriteLock();
        }
    }

    private ArrayList<String> makeOrderedCategoryList(Map<String, MediaDataCursor> map) {
        ArrayList<String> arrayList = new ArrayList<>();
        VisualSearchCategory.iterate(this.mIsPickMode, new g0(map, arrayList, 0));
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            removeIfDisabled(arrayList);
        }
        return arrayList;
    }

    private void removeIfDisabled(List<String> list) {
        list.removeIf(new C0598e(2));
    }

    private void removeIfNoData(List<String> list) {
        list.removeIf(new C0606k(2, this));
    }

    private boolean showEvenNoData(String str) {
        return "location://search/fileList/Category/Stories".equals(str);
    }

    private boolean swapChild(String str, Cursor cursor) {
        if (cursor != null) {
            try {
                if (!cursor.isClosed()) {
                    if (cursor.getCount() <= 0 && !checkHiddenItemExisted(cursor, str)) {
                        if (!showEvenNoData(str)) {
                            Optional.ofNullable(this.mCategoryMap.remove(str)).ifPresent(new C0596d(3));
                        }
                    }
                    this.mCategoryMap.computeIfAbsent(str, new C0608m(2, this)).swap(new Cursor[]{cursor});
                }
            } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException | NullPointerException e) {
                StringCompat stringCompat = this.TAG;
                Log.e((CharSequence) stringCompat, "swap > child fail but ignored {" + str + ',' + Logger.toString(cursor) + "}", e);
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void swapPartialCursors(Object obj, Bundle bundle) {
        if (this.mLock.acquireWriteLock()) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mLocationReference = ArgumentsUtil.removeArg(this.mLocationReference, "visual_search_partial_ready");
            Cursor[] cursorArr = (Cursor[]) obj;
            this.mBackupCursors = this.mCursors;
            this.mCursors = cursorArr;
            if (!VisualSearchCategory.iterateIfSuccess(this.mIsPickMode, new h0(this, cursorArr, 1))) {
                this.mLock.releaseWriteLock();
                return;
            }
            ArrayList<String> makeOrderedCategoryList = makeOrderedCategoryList(this.mCategoryMap);
            this.mLock.releaseWriteLock();
            runOnUiThread(new i0(this, makeOrderedCategoryList, currentTimeMillis, 1));
            return;
        }
        Log.e(this.TAG, "swap > fail to swap lock");
    }

    /* access modifiers changed from: private */
    public void updateCategoryLocationKeyOrder(Object obj, Bundle bundle) {
        int size;
        List<String> listOf = VisualSearchCategory.listOf();
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            removeIfDisabled(listOf);
            removeIfNoData(listOf);
        }
        synchronized (this.mCategoryLocationKey) {
            this.mCategoryLocationKey.clear();
            this.mCategoryLocationKey.addAll(listOf);
            size = this.mCategoryLocationKey.size();
            this.mDataCount = size;
        }
        Log.d(this.TAG, "updateCategoryLocationKeyOrder ", Integer.valueOf(size));
        notifyChanged();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("data://user/category/PartialCategoryReady", new C0601f0(this, 0)));
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            arrayList.add(new SubscriberInfo("command:///SearchCategoryReorder", new C0601f0(this, 1)).setWorkingOnUI());
        }
    }

    public ArrayList<MediaItem> getAllItems() {
        if (this.mLock.acquireReadLock("MDS.getAllItems")) {
            try {
                ArrayList<MediaItem> arrayList = new ArrayList<>();
                for (MediaData next : this.mCategoryMap.values()) {
                    for (int i2 = 0; i2 < next.getRealCount(); i2++) {
                        MediaItem read = next.read(i2);
                        if (read != null) {
                            arrayList.add(read);
                        }
                    }
                }
                return arrayList;
            } catch (IndexOutOfBoundsException e) {
                StringCompat stringCompat = this.TAG;
                Log.e(stringCompat, "getAllItems failed. e=" + e.getMessage());
                return null;
            } finally {
                this.mLock.releaseReadLock("MDS.getAllItems");
            }
        } else {
            Log.e(this.TAG, "getAllItems failed to lock");
            return null;
        }
    }

    public MediaData getChildMediaData(String str) {
        if (this.mLock.acquireReadLock("MDS.getChildMediaData key")) {
            try {
                return this.mCategoryMap.get(str);
            } finally {
                this.mLock.releaseReadLock("MDS.getChildMediaData key");
            }
        } else {
            Log.e(this.TAG, "getChildMediaData failed to lock");
            return null;
        }
    }

    public void onDestroy() {
        ArrayList arrayList = new ArrayList(this.mCategoryMap.values());
        this.mCategoryMap.clear();
        arrayList.forEach(new C0596d(4));
        super.onDestroy();
    }

    public void swap(Cursor[] cursorArr) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mLock.acquireWriteLock()) {
            this.mCursors = cursorArr;
            if (!VisualSearchCategory.iterateIfSuccess(this.mIsPickMode, new h0(this, cursorArr, 0))) {
                this.mLock.releaseWriteLock();
                return;
            }
            ArrayList<String> makeOrderedCategoryList = makeOrderedCategoryList(this.mCategoryMap);
            closeCursors(this.mBackupCursors);
            this.mBackupCursors = null;
            this.mLock.releaseWriteLock();
            runOnUiThread(new i0(this, makeOrderedCategoryList, currentTimeMillis, 0));
        }
    }

    public MediaData getChildMediaData(int i2) {
        MediaData mediaData = null;
        if (this.mLock.acquireReadLock("MDS.getChildMediaData idx")) {
            try {
                String str = this.mCategoryLocationKey.get(i2);
                if (str != null) {
                    mediaData = this.mCategoryMap.get(str);
                }
                return mediaData;
            } catch (IndexOutOfBoundsException e) {
                StringCompat stringCompat = this.TAG;
                Log.e((CharSequence) stringCompat, "getChildMediaData failed {index=" + i2 + ",size=" + this.mCategoryLocationKey.size() + '}', (Throwable) e);
            } finally {
                this.mLock.releaseReadLock("MDS.getChildMediaData idx");
            }
        } else {
            Log.e(this.TAG, "getChildMediaData failed to lock");
            return null;
        }
    }
}
