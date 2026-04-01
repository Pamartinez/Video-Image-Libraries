package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataEntire;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataExpandedSearchPictures extends MediaDataSearchPicturesV2 {
    private LinkedHashMap<String, Integer> mAllDatesCount;
    private HashMap<String, String> mAllDatesIds;

    public MediaDataExpandedSearchPictures(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private boolean hasAllDatesCursor(Cursor[] cursorArr) {
        if (cursorArr.length <= 8 || cursorArr[8] == null) {
            return false;
        }
        return true;
    }

    private boolean isInsufficientItems(QueryParams queryParams, HashMap<Long, MediaItem> hashMap) {
        if (hashMap.size() < queryParams.getFileIds().split(GlobalPostProcInternalPPInterface.SPLIT_REGEX).length) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$buildTempDataMapInternalFromCategory$0(HashMap hashMap, HashMap hashMap2, Long l, MediaItem mediaItem) {
        if (!hashMap.containsKey(l)) {
            hashMap.put(l, (MediaItem) hashMap2.get(l));
        }
    }

    private void updateAllDatesInfo(Cursor cursor) {
        this.mAllDatesCount = new LinkedHashMap<>();
        this.mAllDatesIds = new HashMap<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                this.mAllDatesCount.put(cursor.getString(cursor.getColumnIndex("__day")), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("__count"))));
                this.mAllDatesIds.put(cursor.getString(cursor.getColumnIndex("__day")), cursor.getString(cursor.getColumnIndex("__fileIds")));
            } while (cursor.moveToNext());
        }
    }

    public HashMap<Long, MediaItem> buildTempDataMapInternalFromCategory(QueryParams queryParams, BooleanSupplier booleanSupplier, String str) {
        try {
            HashMap<Long, MediaItem> buildTempDataMapInternalFromCategory = super.buildTempDataMapInternalFromCategory(queryParams, booleanSupplier, str);
            if (!isInsufficientItems(queryParams, buildTempDataMapInternalFromCategory)) {
                return buildTempDataMapInternalFromCategory;
            }
            HashMap<Long, MediaItem> buildTempDataMapInternalFromSCS = buildTempDataMapInternalFromSCS(queryParams.setDbKey(DbKey.ALL_PICTURES), booleanSupplier);
            buildTempDataMapInternalFromSCS.forEach(new g0(buildTempDataMapInternalFromCategory, buildTempDataMapInternalFromSCS, 1));
            return buildTempDataMapInternalFromCategory;
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "buildTempDataMapInternalFromCategory failed : " + e);
            return new HashMap<>();
        }
    }

    public LinkedHashMap<String, Integer> getAllDatesCount() {
        return this.mAllDatesCount;
    }

    public HashMap<String, String> getAllDatesIds() {
        return this.mAllDatesIds;
    }

    public void initExtraTable(Cursor[] cursorArr, CountDownLatch countDownLatch, MediaDataEntire.Candidates candidates) {
        super.initExtraTable(cursorArr, countDownLatch, candidates);
        if (hasAllDatesCursor(cursorArr)) {
            updateAllDatesInfo(cursorArr[8]);
            countDownLatch.countDown();
            CountDownLatch countDownLatch2 = this.mFullLoadLatch;
            if (countDownLatch2 != null) {
                countDownLatch2.countDown();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        LinkedHashMap<String, Integer> linkedHashMap = this.mAllDatesCount;
        if (linkedHashMap != null) {
            linkedHashMap.clear();
        }
        HashMap<String, String> hashMap = this.mAllDatesIds;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public void swapClusterTables(Cursor[] cursorArr, ClusterTable[] clusterTableArr, DefaultTable[] defaultTableArr, int i2, CountDownLatch countDownLatch) {
        super.swapClusterTables(cursorArr, clusterTableArr, defaultTableArr, i2, countDownLatch);
        if (hasAllDatesCursor(cursorArr)) {
            updateAllDatesInfo(cursorArr[8]);
            countDownLatch.countDown();
        }
    }
}
