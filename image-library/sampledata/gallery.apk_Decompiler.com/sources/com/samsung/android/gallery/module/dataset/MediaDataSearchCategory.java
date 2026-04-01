package com.samsung.android.gallery.module.dataset;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.module.cache.MemoryCacheMgr;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.AbstractSorter;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.SearchSorter;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataSearchCategory extends MediaDataCursor {
    private boolean mIsExpanded;
    final boolean mIsSearchCategory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ThumbnailCategoryHolder {
        static final HashSet<String> THUMBNAIL_SET = new HashSet<>(List.of("location://search/fileList/Category/People", "location://search/fileList/Category/PeopleAndPets", "location://search/fileList/Category/Pet", "location://search/fileList/Category/Location", "location://search/fileList/Category/Documents"));

        public static boolean hasThumbnail(String str) {
            return THUMBNAIL_SET.contains(str);
        }
    }

    public MediaDataSearchCategory(Blackboard blackboard, String str) {
        this(blackboard, str, false);
    }

    private boolean equalsCursors(Cursor[] cursorArr, Cursor[] cursorArr2) {
        Cursor cursorFromArray = getCursorFromArray(cursorArr);
        if (cursorFromArray == null || cursorArr2 == null || cursorArr2.length <= 0 || !cursorFromArray.equals(cursorArr2[0])) {
            return false;
        }
        return true;
    }

    private Cursor getCursorFromArray(Cursor[] cursorArr) {
        if (cursorArr == null || cursorArr.length <= 0) {
            return null;
        }
        return cursorArr[0];
    }

    public /* bridge */ /* synthetic */ boolean acquireWriteLock(String str) {
        return super.acquireWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public AbstractSorter createSorter() {
        if (isNeedSorter()) {
            return getSearchSorter();
        }
        return null;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        if (this.mIsSearchCategory) {
            super.createSubscriberList(arrayList);
        }
    }

    public int findPositionBy(Object obj) {
        AbstractSorter abstractSorter = this.mSorter;
        Integer num = (Integer) obj;
        if (abstractSorter != null) {
            return abstractSorter.getIndex(num.intValue());
        }
        return num.intValue();
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

    public Bundle getExtras() {
        Cursor[] cursorArr = this.mCursors;
        if (cursorArr == null || cursorArr.length <= 0) {
            return null;
        }
        return cursorArr[0].getExtras();
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

    public int getPreloadThumbnailCount() {
        if (!PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            return 12;
        }
        Activity readActivity = BlackboardUtils.readActivity(this.mBlackboard);
        if (readActivity == null) {
            return 5;
        }
        return (int) Math.ceil((double) (((float) ResourceCompat.getWindowWidth(readActivity)) / ((float) (ResourceCompat.getDimensionPixelSize((Context) readActivity, R$dimen.search_category_item_size_61, 242) + ResourceCompat.getDimensionPixelSize((Context) readActivity, R$dimen.search_category_item_horizontal_spacing_61, 11)))));
    }

    public /* bridge */ /* synthetic */ int getRealCount() {
        return super.getRealCount();
    }

    public /* bridge */ /* synthetic */ int getRefCount() {
        return super.getRefCount();
    }

    public SearchSorter getSearchSorter() {
        return new SearchSorter(this.mLocationKey);
    }

    public int hashCode() {
        int i2;
        Cursor cursorFromArray = getCursorFromArray(this.mCursors);
        int hashCode = super.hashCode();
        if (cursorFromArray != null) {
            i2 = cursorFromArray.hashCode();
        } else {
            i2 = 0;
        }
        return hashCode + i2;
    }

    public /* bridge */ /* synthetic */ boolean isDataAvailable() {
        return super.isDataAvailable();
    }

    public boolean isNeedSorter() {
        if ("location://search/fileList/Category/Expressions".endsWith(this.mLocationKey)) {
            return false;
        }
        if (this.mIsSearchCategory && (LocationKey.isSearchCategoryOtherScene(getLocationKey()) || LocationKey.isSearchCategoryScreenShot(getLocationKey()))) {
            return false;
        }
        if (this.mIsSearchCategory || "location://search/fileList/Category/ShotMode".equals(getLocationKey()) || LocationKey.isSearchCategoryCreatureMatch(getLocationKey()) || LocationKey.isSearchCategoryCreatureSelectMatch(getLocationKey()) || LocationKey.isSearchCategoryPeopleHideMatch(getLocationKey()) || LocationKey.isSearchCategoryPeopleSelectMatch(getLocationKey()) || LocationKey.isSearchCategoryPeopleSelectForRelation(getLocationKey()) || LocationKey.isSearchPeopleClusterMatch(getLocationKey()) || LocationKey.isSearchCategoryPeopleAndPetsHideMatch(getLocationKey())) {
            return true;
        }
        if (!LocationKey.isSearchCategoryDocuments(getLocationKey()) || !Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS)) {
            return false;
        }
        return true;
    }

    public MediaItem loadMediaItem(Cursor cursor, int i2) {
        MediaItem loadMediaItem = super.loadMediaItem(cursor, i2);
        if (loadMediaItem != null && LocationKey.isSearchCategoryCreatureMatch(ArgumentsUtil.removeArgs(this.mLocationKey))) {
            loadMediaItem.setTag("is_rectangle_thumb", Boolean.valueOf(PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85));
        }
        return loadMediaItem;
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

    public void preloadThumbnail(MemoryCacheMgr<Integer, MediaItem> memoryCacheMgr) {
        if (ThumbnailCategoryHolder.hasThumbnail(this.mLocationKey)) {
            super.preloadThumbnail(memoryCacheMgr);
        }
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

    public void swap(Cursor[] cursorArr) {
        if (!equalsCursors(this.mCursors, cursorArr)) {
            super.swap(cursorArr);
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

    public MediaDataSearchCategory(Blackboard blackboard, String str, boolean z) {
        super(blackboard, str);
        this.mIsExpanded = false;
        this.mIsSearchCategory = z;
        this.mSorter = createSorter();
    }
}
