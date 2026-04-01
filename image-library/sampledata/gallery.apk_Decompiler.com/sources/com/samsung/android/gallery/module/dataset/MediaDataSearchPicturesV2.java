package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.dataset.MediaDataEntire;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataSearchPicturesV2 extends MediaDataTimeline2 {
    private final HashSet<Long> mFeedbackSet = new HashSet<>();
    private HashMap<Long, String> mFrameIdMap = new HashMap<>();
    private final ArrayList<MediaItem> mLocationInfo = new ArrayList<>();
    private HashSet<Long> mOcrIdSet = new HashSet<>();
    protected Cursor mScsCursor;
    private Bundle mScsExtras = new Bundle();

    public MediaDataSearchPicturesV2(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private int findPositionOnFeedbackCursor(long j2) {
        Cursor cursor = this.mScsCursor;
        if (cursor == null || cursor.isClosed()) {
            return -1;
        }
        this.mScsCursor.moveToFirst();
        int i2 = 0;
        do {
            Cursor cursor2 = this.mScsCursor;
            if (j2 == cursor2.getLong(cursor2.getColumnIndex("_id"))) {
                return i2;
            }
            i2++;
        } while (this.mScsCursor.moveToNext());
        return i2;
    }

    private boolean hasLocationCursor(Cursor[] cursorArr) {
        if (cursorArr.length <= 7 || cursorArr[7] == null) {
            return false;
        }
        return true;
    }

    private boolean hasScsCursor(Cursor[] cursorArr) {
        if (cursorArr.length <= 6 || cursorArr[6] == null) {
            return false;
        }
        return true;
    }

    private boolean isCreatureCoverChoice(String str, String str2, boolean z) {
        if (!LocationKey.isCreatureCoverChoice(str)) {
            return false;
        }
        if (z) {
            return IdentityCreatureUtil.isPet(str2);
        }
        return IdentityCreatureUtil.isPerson(str2);
    }

    private boolean needKeywordSearchOnSupportMultipleKeyword(String str) {
        if ((!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || !ArgumentsUtil.getArgValue(str, "need_keyword_search", false)) && !ArgumentsUtil.getArgValue(str, "support_cluster_from_visual_search", false)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onSuggesterFeedback(Object obj, Bundle bundle) {
        try {
            Cursor cursor = this.mScsCursor;
            if (cursor != null && !cursor.isClosed()) {
                this.mScsCursor.respond((Bundle) ((Object[]) obj)[0]);
            }
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "onSuggesterFeedback(failed) : " + e);
        }
    }

    private boolean queryToAllPictures() {
        if (LocationKey.isSearchKeyword(this.mLocationReference) || needKeywordSearchOnSupportMultipleKeyword(this.mLocationReference)) {
            return true;
        }
        if (LocationKey.isSearchCategoryCreature(this.mLocationKey) || LocationKey.isSearchCategoryShotMode(this.mLocationKey) || LocationKey.isCreatureCoverChoice(this.mLocationKey)) {
            return false;
        }
        return true;
    }

    private void resetLocationInfo() {
        this.mLocationInfo.clear();
    }

    private void setScsCursor(Cursor cursor) {
        this.mScsCursor = cursor;
    }

    private void setScsExtras(Cursor[] cursorArr) {
        int i2;
        Bundle bundle;
        if (PreferenceFeatures.OneUi6x.SUPPORT_QOD_SEARCH) {
            i2 = 5;
        } else {
            i2 = 0;
        }
        if (cursorArr.length > i2) {
            Cursor cursor = cursorArr[i2];
            if (cursor == null || cursor.getExtras() == null) {
                bundle = new Bundle();
            } else {
                bundle = cursor.getExtras();
            }
            this.mScsExtras = bundle;
            updateOcrInfo();
            updateFrameIdMap();
        }
    }

    private void updateFrameIdMap() {
        if (PreferenceFeatures.OneUi8x.VIDEO_SEARCH) {
            HashMap<Long, String> hashMap = (HashMap) this.mScsExtras.getSerializable("frame_id_map");
            if (hashMap == null) {
                hashMap = new HashMap<>();
            }
            this.mFrameIdMap = hashMap;
        }
    }

    private void updateLocationInfo(Cursor cursor) {
        this.mLocationInfo.clear();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                this.mLocationInfo.add(MediaItemBuilder.createGpsDate(cursor));
            } while (cursor.moveToNext());
        }
    }

    private void updateOcrInfo() {
        this.mOcrIdSet.clear();
        String string = this.mScsExtras.getString("ocr_ids");
        if (!TextUtils.isEmpty(string)) {
            this.mOcrIdSet = (HashSet) Stream.of(string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new K(15)).collect(Collectors.toSet());
        }
    }

    public HashMap<Long, MediaItem> buildTempDataMap(BooleanSupplier booleanSupplier, Cursor cursor) {
        HashMap<Long, MediaItem> hashMap = new HashMap<>();
        if (cursor != null && cursor.moveToFirst()) {
            while (true) {
                MediaItem load = MediaItemLoader.load(cursor);
                DetailsData.of(load).hasOcr = this.mOcrIdSet.contains(Long.valueOf(load.getFileId()));
                VideoPropData.of(load).videoFrameIds = this.mFrameIdMap.get(Long.valueOf(load.getFileId()));
                hashMap.put(Long.valueOf(load.getFileId()), load);
                if (!cursor.moveToNext() || (booleanSupplier != null && booleanSupplier.getAsBoolean())) {
                    break;
                }
            }
        }
        return hashMap;
    }

    public HashMap<Long, MediaItem> buildTempDataMapInternal(String str, BooleanSupplier booleanSupplier, Blackboard blackboard, String str2) {
        QueryParams fileIds = new QueryParams().setGroupTypes(GroupType.BURST).setFileIds(str);
        if (ArgumentsUtil.getArgValue(this.mLocationReference, "showHidden", false)) {
            fileIds.setShowHidden(true);
        }
        if (LocationKey.isCreatureCoverChoice(this.mLocationKey)) {
            fileIds.mNeedFaceScore = true;
        }
        if (queryToAllPictures()) {
            return buildTempDataMapInternalFromSCS(fileIds.setDbKey(DbKey.ALL_PICTURES), booleanSupplier);
        }
        return buildTempDataMapInternalFromCategory(fileIds, booleanSupplier, str2);
    }

    /* JADX INFO: finally extract failed */
    public HashMap<Long, MediaItem> buildTempDataMapInternalFromCategory(QueryParams queryParams, BooleanSupplier booleanSupplier, String str) {
        try {
            composeQueryParams(queryParams, str, ArgumentsUtil.getArgValue(this.mLocationReference, "sub"));
            Cursor query = DbCompat.query(queryParams);
            HashMap<Long, MediaItem> buildTempDataMap = buildTempDataMap(booleanSupplier, query);
            Utils.closeSilently(query);
            return buildTempDataMap;
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "buildTempDataMapInternalFromCategory failed : " + e);
            Utils.closeSilently((Closeable) null);
            return new HashMap<>();
        } catch (Throwable th) {
            Utils.closeSilently((Closeable) null);
            throw th;
        }
    }

    public HashMap<Long, MediaItem> buildTempDataMapInternalFromSCS(QueryParams queryParams, BooleanSupplier booleanSupplier) {
        Cursor query;
        try {
            query = DbCompat.query(queryParams);
            HashMap<Long, MediaItem> buildTempDataMap = buildTempDataMap(booleanSupplier, query);
            if (query == null) {
                return buildTempDataMap;
            }
            query.close();
            return buildTempDataMap;
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "buildTempDataMapInternalFromSCS failed : " + e);
            return new HashMap<>();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public boolean checkDataMismatching(Cursor[] cursorArr, DataTable dataTable, ClusterTable[] clusterTableArr, MediaDataEntire.Candidates candidates) {
        if (isTimelineDisabled() || !super.checkDataMismatching(cursorArr, dataTable, clusterTableArr, candidates)) {
            return false;
        }
        return true;
    }

    public void composeQueryParams(QueryParams queryParams, String str, String str2) {
        queryParams.setSubCategory(str2);
        if (LocationKey.isSearchCategoryPeople(str) || isCreatureCoverChoice(str, str2, false)) {
            queryParams.setDbKey("mp://People/files");
            if (!Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
                queryParams.mCreatureTagName = ArgumentsUtil.getArgValue(str, "title");
            }
        } else if (LocationKey.isSearchCategoryLocation(str)) {
            queryParams.setDbKey("mp://Location/files");
        } else if (LocationKey.isSearchCategoryPet(str) || isCreatureCoverChoice(str, str2, true)) {
            queryParams.setDbKey("mp://Pets/files");
        } else if (LocationKey.isSearchCategoryMyTag(str)) {
            queryParams.setDbKey("mp://myTag/files");
        } else if (LocationKey.isSearchCategoryShotMode(str)) {
            queryParams.setDbKey("mp://ShotMode/files");
            if (ArgumentsUtil.getArgValue(this.mLocationReference, "SelectedFilter") == null) {
                queryParams.addGroupType(GroupType.SINGLE_TAKEN);
            }
        } else if (LocationKey.isSearchCategoryExpressions(str)) {
            queryParams.setDbKey("mp://Expression/files");
        } else if (LocationKey.isSearchCategoryDocuments(str)) {
            queryParams.setDbKey("mp://Document/files");
        } else if (LocationKey.isSearchCategoryOtherScene(str) || LocationKey.isSearchCategoryThings(str) || LocationKey.isSearchCategoryOtherScenery(str)) {
            queryParams.setDbKey("mp://Scene/files");
        }
    }

    public ClusterTable createClusterTable(int i2, Cursor[] cursorArr) {
        ClusterTable createClusterTable = super.createClusterTable(i2, cursorArr);
        if (this.mDisableDayMerge) {
            createClusterTable.setDisableDayMerge();
        }
        return createClusterTable;
    }

    public DataTable createDataTable(Cursor cursor) {
        if (this.mDisableDayMerge) {
            return super.createDataTable(cursor).setDisableDayMerge();
        }
        return super.createDataTable(cursor);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://event/FeedbackSearchedItem", new o0(this, 0)));
        arrayList.add(new SubscriberInfo("command://event/FeedbackSuggesterItem", new o0(this, 1)));
    }

    public ArrayList<MediaItem> getExtraData() {
        return this.mLocationInfo;
    }

    public Bundle getExtras() {
        Bundle bundle = new Bundle();
        bundle.putBundle("scsCursorExtra", this.mScsExtras);
        return bundle;
    }

    public void initExtraTable(Cursor[] cursorArr, CountDownLatch countDownLatch, MediaDataEntire.Candidates candidates) {
        super.initExtraTable(cursorArr, countDownLatch, candidates);
        setScsExtras(cursorArr);
        if (hasScsCursor(cursorArr)) {
            setScsCursor(cursorArr[6]);
            countDownLatch.countDown();
            CountDownLatch countDownLatch2 = this.mFullLoadLatch;
            if (countDownLatch2 != null) {
                countDownLatch2.countDown();
            }
        }
        if (hasLocationCursor(cursorArr)) {
            updateLocationInfo(cursorArr[7]);
            countDownLatch.countDown();
            CountDownLatch countDownLatch3 = this.mFullLoadLatch;
            if (countDownLatch3 != null) {
                countDownLatch3.countDown();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Utils.closeSilently(this.mScsCursor);
        resetLocationInfo();
        this.mFeedbackSet.clear();
        this.mOcrIdSet.clear();
    }

    public void onFeedback(Object obj, Bundle bundle) {
        try {
            Cursor cursor = this.mScsCursor;
            if (cursor != null && !cursor.isClosed()) {
                Object[] objArr = (Object[]) obj;
                boolean z = false;
                int intValue = ((Integer) objArr[0]).intValue();
                Long l = (Long) objArr[1];
                long longValue = l.longValue();
                if (intValue == -1) {
                    if (this.mFeedbackSet.contains(l)) {
                        Log.w((CharSequence) this.TAG, "onFeedback() << already send feedback ", l);
                        return;
                    } else {
                        intValue = findPositionOnFeedbackCursor(longValue);
                        z = true;
                    }
                }
                if (intValue < 0 || !this.mScsCursor.moveToPosition(intValue)) {
                    Log.w((CharSequence) this.TAG, "onFeedback(failed) << moveToPosition failed", Integer.valueOf(intValue), l);
                    return;
                }
                Cursor cursor2 = this.mScsCursor;
                long j2 = cursor2.getLong(cursor2.getColumnIndex("_id"));
                if (longValue == j2) {
                    if (z) {
                        this.mFeedbackSet.add(l);
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("feedback_selected", intValue);
                    bundle2.putBoolean("enable_debug", PocFeatures.isEnabled(PocFeatures.SearchDebugInfo));
                    this.mScsCursor.respond(bundle2);
                    return;
                }
                Log.w((CharSequence) this.TAG, "onFeedback(failed) << wrong expect media", Integer.valueOf(intValue), l, "expect=" + j2);
            }
        } catch (Exception e) {
            Log.e(this.TAG, "onFeedback(failed) : " + e);
        }
    }

    public void swapClusterTables(Cursor[] cursorArr, ClusterTable[] clusterTableArr, DefaultTable[] defaultTableArr, int i2, CountDownLatch countDownLatch) {
        super.swapClusterTables(cursorArr, clusterTableArr, defaultTableArr, i2, countDownLatch);
        setScsExtras(cursorArr);
        if (hasScsCursor(cursorArr)) {
            Utils.closeSilently(this.mScsCursor);
            setScsCursor(cursorArr[6]);
            countDownLatch.countDown();
        }
        if (hasLocationCursor(cursorArr)) {
            updateLocationInfo(cursorArr[7]);
            countDownLatch.countDown();
            return;
        }
        resetLocationInfo();
    }

    public DataTable createDataTable(Cursor cursor, DefaultTable.OnLoadDoneListener onLoadDoneListener, int i2) {
        if (this.mDisableDayMerge) {
            return super.createDataTable(cursor, onLoadDoneListener, i2).setDisableDayMerge();
        }
        return super.createDataTable(cursor, onLoadDoneListener, i2);
    }

    public ClusterTable createClusterTable(int i2, Cursor[] cursorArr, DefaultTable.OnLoadDoneListener onLoadDoneListener) {
        ClusterTable createClusterTable = super.createClusterTable(i2, cursorArr, onLoadDoneListener);
        if (this.mDisableDayMerge) {
            createClusterTable.setDisableDayMerge();
        }
        return createClusterTable;
    }
}
