package com.samsung.android.gallery.module.publisher;

import A.a;
import android.database.Cursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.FilterResultsKeySet;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.publisher.SearchDataBasePublisher;
import com.samsung.android.gallery.module.publisher.retrieval.IntelligentSearchRetrieval;
import com.samsung.android.gallery.module.publisher.retrieval.SQLiteRetrieval;
import com.samsung.android.gallery.module.publisher.retrieval.StorageRetrieval;
import com.samsung.android.gallery.module.search.autocomplete.SearchAutoCompleteItemComposer;
import com.samsung.android.gallery.module.search.engine.BaseSearchEngine;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.module.search.engine.SearchEngineFactory;
import com.samsung.android.gallery.module.search.root.FilterOnlyThem;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.module.settings.SmartSuggestionsSettingApi;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ScreenShotHelper;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.android.sum.core.Def;
import i.C0212a;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchDataPublisher extends SearchDataBasePublisher {
    private final Object LOCK = new Object();
    private boolean mCategoriesFullLoadDone = false;
    private final HashMap<Integer, Future<Cursor>> mCategoriesFutureMap = new HashMap<>();
    private Cursor[] mEntryCacheCursors;
    final SearchKeywordQuery mKeywordQuery;
    private String mRequestedAutoCompleteKeyword;
    private boolean mSuggestionKeywordRequested = false;

    public SearchDataPublisher(Blackboard blackboard) {
        super(blackboard);
        this.mKeywordQuery = new SearchKeywordQuery(blackboard);
    }

    private FilterResultsEntry buildFilterResultEntry(int i2, String str, LinkedHashMap<String, String> linkedHashMap) {
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
        String argValue = ArgumentsUtil.getArgValue(readLocationKeyCurrent, "title", "");
        boolean z = UnsafeCast.toBoolean(ArgumentsUtil.getArgValue(readLocationKeyCurrent, "searchToolbar", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE));
        FilterResultsEntry.Builder builder = new FilterResultsEntry.Builder(this.mBlackboard);
        if (LocationKey.isSearchRelationshipPreview(readLocationKeyCurrent)) {
            builder.setMinCreatureItemCount(1);
        }
        return builder.extract(str, argValue, i2, z, linkedHashMap).setMaxFilterNum(20).setSortByCount(true).build();
    }

    private boolean checkCategoriesDataClosed(Cursor[] cursorArr, String str, String str2) {
        boolean z;
        if (cursorArr == null || !isCategoriesCursorAlreadyClosed(cursorArr)) {
            z = false;
        } else {
            releaseCategoriesJob(cursorArr, str);
            z = true;
        }
        if (z) {
            this.mBlackboard.erase(DataKey.DATA_CURSOR(str));
            Log.d(this.TAG, "checkCategoriesDataClosed", str2);
        }
        return z;
    }

    private boolean checkCategoriesDataClosedOnCacheLoad(String str) {
        if (this.mCategoriesFullLoadDone || !checkCategoriesDataClosed(this.mEntryCacheCursors, str, "Cache")) {
            return false;
        }
        return true;
    }

    private SearchFilter createAutoCompleteSearchFilter(String str) {
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        SearchFilter.Builder filterEnable = new SearchFilter.Builder(str).setFilterEnable(false);
        if (launchIntent != null) {
            setPickerSearchFilterIfNeeded(launchIntent, filterEnable);
        }
        return filterEnable.build(this.mAppContext);
    }

    private boolean existFallbackResultCollection(Object obj) {
        if (Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION_V2_AS_LIST)) {
            if (obj == null || ((ArrayList) obj).isEmpty()) {
                return false;
            }
            return true;
        } else if (!Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION_V2) || obj == null || ((HashMap) obj).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private Bundle getFilterResults(String str, Bundle bundle, int i2) {
        return getFilterResults(str, i2, getSubCategoryForRequestFilter(str, bundle), bundle);
    }

    private Object[] getQueryBundleObject(Cursor[] cursorArr, SearchFilter searchFilter) {
        Cursor cursor;
        Object[] objArr = null;
        if (cursorArr.length > 5) {
            if (searchFilter.isForQueryOnDemand()) {
                cursor = cursorArr[5];
            } else {
                cursor = cursorArr[0];
            }
            if (cursor.getExtras() != null) {
                objArr = new Object[]{cursor.getExtras().getBundle("searchScsQueryExtra"), cursor.getExtras().getString("searchScsQueryResultIdList")};
            }
            StringCompat stringCompat = this.TAG;
            Log.s(stringCompat, "get an objects: " + objArr);
            return objArr;
        }
        Log.se(this.TAG, "failed to get an objects");
        return null;
    }

    private List<String> getSupportCategories() {
        if (isPickMode()) {
            return (List) VisualSearchCategory.listOf().stream().filter(new V(this)).collect(Collectors.toList());
        }
        return VisualSearchCategory.listOf();
    }

    private String getTopRecommendResultInfo(ArrayList<String> arrayList) {
        if (arrayList != null) {
            return String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList);
        }
        return " ";
    }

    private boolean hasNoRelationshipPeople() {
        try {
            int peopleNoRelationshipCount = new MpHelper().getPeopleNoRelationshipCount();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "hasNoRelationshipPeople : " + peopleNoRelationshipCount);
            if (peopleNoRelationshipCount > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            a.r(e, new StringBuilder("fail to hasNoRelationshipPeople(), "), this.TAG);
            return false;
        }
    }

    private void invokeFaceServiceIfNeeded() {
        Cursor rawQuery;
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            PreferenceCache preferenceCache = PreferenceCache.FaceServiceFaceGroupInvoke;
            if (!preferenceCache.getBoolean()) {
                try {
                    rawQuery = new SecMpQueryExecutor().rawQuery("SELECT F.group_id FROM faces as F LEFT JOIN faces_group as FG ON F.group_id = FG.group_id WHERE F.group_id > 0 and FG.group_id is NULL limit 1", "CheckFaceGroupInvoking");
                    if (rawQuery != null) {
                        if (rawQuery.moveToFirst()) {
                            preferenceCache.setBoolean(true);
                            Bundle call = this.mAppContext.getContentResolver().call(Uri.parse("content://com.samsung.faceservice"), "mkgrouptable", (String) null, (Bundle) null);
                            StringCompat stringCompat = this.TAG;
                            Log.d(stringCompat, "Method call successful. Result: " + call);
                        }
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                        return;
                    }
                    return;
                } catch (Error | Exception e) {
                    StringCompat stringCompat2 = this.TAG;
                    Log.e(stringCompat2, "invokeFaceServiceIfNeeded failed. e=" + e.getMessage());
                    return;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                return;
            }
        } else {
            return;
        }
        throw th;
    }

    private boolean isCategoriesCursorAlreadyClosed(Cursor[] cursorArr) {
        for (Cursor cursor : cursorArr) {
            if (cursor != null && cursor.isClosed()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getSupportCategories$8(String str) {
        return VisualSearchCategory.support(str, isPickMode());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publish$0(String str, Bundle bundle, int i2) {
        publishFilterAndClusterResultsFrom(str, getFilterResults(str, i2, getSubCategoryForRequestFilter(str, bundle), bundle), i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAllScreenShotFiles$20(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[1] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAllScreenShotFiles$21(QueryParams queryParams, Cursor[] cursorArr, long j2, Bundle bundle) {
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publish publishAllScreenShotFiles(T) " + queryParams + " " + getCursorListInfo(cursorArr, j2));
        publishCursorArray(getLocationKeyFromReq(bundle), cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAllScreenShotFiles$22(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[3] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAllScreenShotFiles$23(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[5] = loadFileIdCursor(queryParams, DbKey.ALBUM_FILE_IDS_ORDERED);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCategoriesCache$7(String str) {
        if (!checkCategoriesDataClosedOnCacheLoad(str)) {
            this.mBlackboard.postBroadcastEvent(EventMessage.obtain(103));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCategoriesData$4(Integer num, String str, Cursor[] cursorArr, CountDownLatch countDownLatch, long j2, String str2, boolean z, Future future) {
        synchronized (this.LOCK) {
            try {
                this.mCategoriesFutureMap.remove(num);
                Cursor cursor = (Cursor) future.get();
                if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
                    checkHiddenPeopleAndPets(cursor, str);
                }
                cursorArr[num.intValue()] = cursor;
                countDownLatch.countDown();
                long count = countDownLatch.getCount();
                StringCompat stringCompat = this.TAG;
                Log.i(stringCompat, "fillDataCursors {" + count + ',' + ArgumentsUtil.getLastSegment(str) + ',' + Logger.toString(cursorArr[num.intValue()]) + "} +" + (System.currentTimeMillis() - j2));
                publishCategoryCursorsIfReady(str2, cursorArr, count, z, j2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private /* synthetic */ void lambda$publishCategoriesData$5(Cursor[] cursorArr, CountDownLatch countDownLatch, long j2, String str, boolean z, Integer num, String str2) {
        String str3 = str2;
        if (this.mSearchJobs.get(str3) != null) {
            long j3 = j2;
            String str4 = str;
            Integer num2 = num;
            this.mCategoriesFutureMap.put(num2, ThreadPool.getInstance().submit(this.mSearchJobs.get(str3), new U(j3, this, num2, str3, str4, countDownLatch, z, cursorArr)));
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.w(stringCompat, "publishCategoriesData: no searchJon for [ + " + str3 + "]");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCategoryCache$11(String str) {
        MediaData open;
        try {
            open = MediaDataFactory.getInstance(this.mBlackboard).open(str, true);
            open.reopen(str);
            open.close();
            return;
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCategoryData$10(String str, Future future) {
        this.mBlackboard.erase(DataKey.DATA_CURSOR(str));
        TimeTickLog timeTickLog = new TimeTickLog(C0212a.m("update [", str, "] cache cursor"));
        CacheProviderHelper.cacheCursor(str, (Cursor) future.get());
        timeTickLog.tock(100);
        publishCursorArray(str, new Cursor[]{(Cursor) future.get()});
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishFilterAndClusterResultsFrom$13(Cursor cursor, int i2, LinkedHashMap linkedHashMap) {
        String cursorStringExtra = getCursorStringExtra(cursor, "FilterResults");
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "filter: " + cursorStringExtra + ", pdeRecommend: " + linkedHashMap);
        publishFilterResults(i2, cursorStringExtra, getFilterOnlyThemFromCursor(cursor), linkedHashMap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishScreenShotFiles$17(String str, String str2, String str3, Cursor[] cursorArr) {
        QueryParams parentCategory = createCommonQueryParams().setParentCategory(str);
        if (str2 != null) {
            parentCategory.setFileIds(str2);
        }
        Cursor[] sceneFileIds = getRetriever(parentCategory, false).getSceneFileIds(str3, (SearchFilter.Builder) null, "publishScreenShotFiles", -1);
        cursorArr[5] = sceneFileIds[5];
        cursorArr[1] = sceneFileIds[1];
        cursorArr[3] = sceneFileIds[3];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishScreenShotFiles$18(Cursor[] cursorArr, int i2, QueryParams queryParams) {
        cursorArr[i2] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishScreenShotFiles$19(Cursor[] cursorArr, long j2, Bundle bundle) {
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishScreenShotFiles(R) " + getCursorListInfo(cursorArr, j2));
        publishCursorArray(getLocationKeyFromReq(bundle), cursorArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishSearchAutoCompleteV1$15(Cursor[] cursorArr) {
        Cursor albumAutoCompleteCursor = new LocalAlbumsApi(createCommonQueryParams()).getAlbumAutoCompleteCursor();
        cursorArr[0] = albumAutoCompleteCursor;
        if (albumAutoCompleteCursor == null || albumAutoCompleteCursor.getCount() == 0) {
            Log.e(this.TAG, "empty local albums, get from external db");
            cursorArr[0] = DbCompat.query(createCommonQueryParams().setDbKey(DbKey.ALBUMS_AUTO_COMPLETE));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishSearchAutoCompleteV1$16(Cursor[] cursorArr) {
        cursorArr[1] = DbCompat.query(createCommonQueryParams().setDbKey(DbKey.STORY_AUTO_COMPLETE));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishSearchAutoCompleteV2$14(Bundle bundle, String str, ArrayList arrayList) {
        if (TextUtils.equals(this.mRequestedAutoCompleteKeyword, str)) {
            this.mBlackboard.post("data://user/AutoComplete", arrayList);
            this.mBlackboard.erase(ArgumentsUtil.getSubscribeKey(bundle));
            this.mCancelSignals.clear();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishSuggestionKeywords$12() {
        getQuerySuggester().preloadSuggestion(this.mAppContext, this.mBlackboard);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishWithFilterRequest$1(boolean z, AtomicReference atomicReference, String str, Bundle bundle) {
        if (z) {
            atomicReference.set(getFilterResults(str, bundle, ((Integer) this.mBlackboard.pop("data://user/category/itemCount", 0)).intValue()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishWithFilterRequest$2(Cursor[][] cursorArr, String str, String str2, String str3, Bundle bundle) {
        cursorArr[0] = getFilterDataCursors(str, str2, (String) null, str3, getExpandedDates(bundle), getAddedCount(bundle));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishWithFilterRequest$3(Cursor[][] cursorArr, boolean z, Bundle bundle, AtomicReference atomicReference, String str, long j2, String str2) {
        int cursorIndex = getCursorIndex(cursorArr[0]);
        if (cursorArr[0][cursorIndex] != null) {
            if (!z && TextUtils.isEmpty(BundleWrapper.getString(bundle, "prev_sub_category", ""))) {
                atomicReference.set(getFilterResults(str, bundle, cursorArr[0][cursorIndex].getCount()));
            }
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "publishWithFilterRequest " + getCursorListInfo(cursorArr[0], j2));
            publishFilterAndClusterResultsFrom(getLocationKeyFromReq(bundle), (Bundle) atomicReference.get(), getRealCount(cursorArr[0][cursorIndex], bundle));
            publishCursorArray(str, cursorArr[0], ArgumentsUtil.getSubscribeKey(bundle));
            return;
        }
        Log.e((CharSequence) this.TAG, "publishWithFilterRequest failed", str, str2);
    }

    private boolean needRefreshFilterResultsForPersonTag(Bundle bundle) {
        if (!needRefreshFilterResults(bundle) || !Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onVisualSearchDestroyed(Object obj, Bundle bundle) {
        String locationKeyFromReq = getLocationKeyFromReq(bundle);
        this.mBlackboard.erase(CommandKey.DATA_REQUEST(locationKeyFromReq));
        checkCategoriesDataClosedOnCacheLoad(locationKeyFromReq);
    }

    private void publishAllScreenShotFiles(Bundle bundle, long j2) {
        QueryParams createQueryParams = createQueryParams(bundle);
        createQueryParams.setSubCategory(ScreenShotFilterType.All.key());
        createQueryParams.setScreenShotFolderId(FileUtils.getBucketId(ScreenShotHelper.getScreenshotFolder(this.mAppContext)));
        createQueryParams.setImageOnly();
        QueryParams dbKey = createQueryParams.setDbKey(DbKey.ALBUM_FILES);
        QueryParams dbKey2 = createQueryParams.clone().setDbKey(DbKey.ALBUM_FILE_DAY);
        QueryParams dbKey3 = createQueryParams.clone().setDbKey(DbKey.ALBUM_FILE_REALRATIO);
        Cursor[] cursorArr = {null, null, null, null, null, null};
        LatchBuilder postExecutor = new LatchBuilder("publishAllScreenShotFiles(T)").addWorker(new C0625e(cursorArr, 10, dbKey2)).setPostExecutor((Runnable) new W(this, dbKey, cursorArr, j2, bundle));
        if (CursorPublisher.SUPPORT_REAL_RATIO) {
            postExecutor.addWorker(new C0625e(cursorArr, 11, dbKey3));
        }
        postExecutor.setCurrent(new C0636p(this, cursorArr, dbKey, 3));
        postExecutor.start();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public void publishCategoriesData(Object obj, Bundle bundle) {
        String str;
        if (BundleWrapper.getBoolean(bundle, "vs_use_cache")) {
            publishCategoriesCache(obj, bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int size = getSupportCategories().size();
        boolean z = BundleWrapper.getBoolean(bundle, "visual_search_partial_ready", false);
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("publishCategoriesData {");
        sb2.append(size);
        if (z) {
            str = ",PR";
        } else {
            str = "";
        }
        sb2.append(str);
        sb2.append('}');
        Log.d(stringCompat, sb2.toString());
        Cursor[] cursorArr = new Cursor[size];
        CountDownLatch countDownLatch = new CountDownLatch(size);
        synchronized (this.LOCK) {
            try {
                String locationKeyFromReq = getLocationKeyFromReq(bundle);
                this.mCategoriesFutureMap.clear();
                VisualSearchCategory.iterate(isPickMode(), new P(this, cursorArr, countDownLatch, currentTimeMillis, locationKeyFromReq, z));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void publishCategoryCursorsIfReady(String str, Cursor[] cursorArr, long j2, boolean z, long j3) {
        if (j2 == ((long) (getSupportCategories().size() - 1))) {
            TranslationManager.getInstance().loadTranslationMap(this.mAppContext);
            if (z) {
                StringCompat stringCompat = this.TAG;
                StringBuilder sb2 = new StringBuilder("publishCategoryCursors(full) #");
                sb2.append(j2);
                sb2.append(',');
                a.y(sb2, getCursorListInfo(cursorArr, j3), stringCompat);
                this.mBlackboard.post("data://user/category/PartialCategoryReady", cursorArr);
            }
        } else if (j2 == 0) {
            this.mBlackboard.erase(DataKey.DATA_CURSOR(str));
            StringCompat stringCompat2 = this.TAG;
            Log.p(stringCompat2, "publishCategoryCursors(full) " + getCursorListInfo(cursorArr, j3));
            TimeTickLog timeTickLog = new TimeTickLog("update VisualSearch cache cursor");
            VisualSearchCategory.iterate(isPickMode(), new C0640u(2, cursorArr));
            timeTickLog.tock(100);
            publishSuggestionKeywords();
            this.mCategoriesFullLoadDone = true;
            publishCursorArray(str, cursorArr);
        }
    }

    /* access modifiers changed from: private */
    public void publishCategoryData(Object obj, Bundle bundle) {
        if (BundleWrapper.getBoolean(bundle, "vs_use_cache")) {
            publishCategoryCache(obj, bundle);
            return;
        }
        String locationKeyFromReq = getLocationKeyFromReq(bundle);
        ThreadPool.getInstance().submit(this.mSearchJobs.get(locationKeyFromReq), new O(this, locationKeyFromReq));
    }

    /* access modifiers changed from: private */
    public void publishKeywordStoriesData(Object obj, Bundle bundle) {
        if (bundle == null || bundle.get("name") == null) {
            Log.e(this.TAG, "publishKeywordStoriesData skip. There is no Id list");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Trace.beginSection("publishKeywordStoriesData");
        Cursor queryStory = this.mKeywordQuery.queryStory(bundle, false);
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "publishKeywordStoriesData " + getCursorInfo(queryStory, currentTimeMillis));
        publishCursorArray("location://search/fileList/KeywordStories", new Cursor[]{queryStory});
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void publishSearchAutoComplete(Object obj, Bundle bundle) {
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_AUTOCOMPLETE)) {
            publishSearchAutoCompleteV2(bundle);
        } else {
            publishSearchAutoCompleteV1(bundle);
        }
    }

    private void publishSearchAutoCompleteV1(Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor[] cursorArr = new Cursor[2];
        new LatchBuilder("SearchAutoComp").addWorker(new X(this, cursorArr, 0)).setCurrent(new X(this, cursorArr, 1)).setTimeout(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS).start();
        MergeCursor mergeCursor = new MergeCursor(cursorArr);
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "publishSearchAutoComplete : merge->" + getCursorInfo(mergeCursor, currentTimeMillis) + ", dir->" + getCursorInfo(cursorArr[0], currentTimeMillis) + ", tag->" + getCursorInfo(cursorArr[1], currentTimeMillis));
        publishCursorArray("location://search/AutoComplete", new Cursor[]{mergeCursor}, ArgumentsUtil.getSubscribeKey(bundle));
    }

    private void publishSearchAutoCompleteV2(Bundle bundle) {
        String string = bundle.getString("keyword");
        SearchFilter createAutoCompleteSearchFilter = createAutoCompleteSearchFilter(string);
        String rawKeyword = createAutoCompleteSearchFilter.getRawKeyword();
        setCancellationSignal(string, createAutoCompleteSearchFilter, "AutoComplete");
        this.mRequestedAutoCompleteKeyword = rawKeyword;
        queryAutoComplete(createAutoCompleteSearchFilter, new O(this, bundle));
    }

    private void publishSuggestionKeywords() {
        if (!this.mSuggestionKeywordRequested) {
            SimpleThreadPool.getInstance().execute(new C0639t(this, 2));
            this.mSuggestionKeywordRequested = true;
        }
    }

    private void publishWithFilterRefresh(String str, Bundle bundle, String str2, String str3, String str4, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        String str5 = str;
        String str6 = str2;
        Cursor[] filterDataCursors = getFilterDataCursors(str5, str6, str3, str4, getExpandedDates(bundle), getAddedCount(bundle));
        int cursorIndex = getCursorIndex(filterDataCursors);
        Cursor cursor = filterDataCursors[cursorIndex];
        if (cursor != null) {
            if (z) {
                int realCount = getRealCount(cursor, bundle);
                if (Features.isEnabled(Features.SUPPORT_MULTI_KEYWORD_SEARCH)) {
                    publishExtraResults(filterDataCursors[cursorIndex], realCount);
                }
                publishFilterAndClusterResultsFrom(str5, filterDataCursors[cursorIndex], realCount, (Object[]) null);
            }
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "publishWithFilterRefresh " + getCursorListInfo(filterDataCursors, currentTimeMillis));
            publishCursorArray(str5, filterDataCursors, ArgumentsUtil.getSubscribeKey(bundle));
            return;
        }
        Log.e((CharSequence) this.TAG, "publishWithFilterRefresh failed", str5, str6);
    }

    private void publishWithFilterRequest(String str, Bundle bundle, String str2, String str3) {
        long currentTimeMillis = System.currentTimeMillis();
        AtomicReference atomicReference = new AtomicReference();
        int[] iArr = new int[2];
        iArr[1] = 1;
        iArr[0] = 1;
        Cursor[][] cursorArr = (Cursor[][]) Array.newInstance(Cursor.class, iArr);
        boolean z = !this.mBlackboard.isEmpty("data://user/category/itemCount");
        AtomicReference atomicReference2 = atomicReference;
        boolean z3 = z;
        new LatchBuilder("publishWithFilterRequest").addWorker(new L(this, z, atomicReference, str, bundle)).setCurrent(new M(this, cursorArr, str, str2, str3, bundle, 0)).setPostExecutor((Runnable) new N(this, cursorArr, z3, bundle, atomicReference2, str, currentTimeMillis, str2)).start();
    }

    public static /* synthetic */ void q0(long j2, SearchDataPublisher searchDataPublisher, Integer num, String str, String str2, CountDownLatch countDownLatch, boolean z, Cursor[] cursorArr) {
        long j3 = j2;
        Cursor[] cursorArr2 = cursorArr;
        long j8 = j3;
        searchDataPublisher.lambda$publishCategoriesData$5(cursorArr2, countDownLatch, j8, str, z, num, str2);
    }

    private void queryAutoComplete(SearchFilter searchFilter, SearchDataBasePublisher.AutoCompleteResult autoCompleteResult) {
        Cursor searchAutoComplete;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        try {
            searchAutoComplete = SearchEngineFactory.create(this.mAppContext).searchAutoComplete(searchFilter);
            arrayList.addAll(new SearchAutoCompleteItemComposer().readAll(searchAutoComplete));
            if (searchAutoComplete != null) {
                searchAutoComplete.close();
            }
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "AutoComplete", (Throwable) e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("queryAutoComplete result");
        sb2.append(Logger.vt("s:" + arrayList.size() + "", Long.valueOf(currentTimeMillis)));
        Log.i(stringCompat, sb2.toString());
        O o2 = (O) autoCompleteResult;
        o2.d.lambda$publishSearchAutoCompleteV2$14((Bundle) o2.e, searchFilter.getRawKeyword(), arrayList);
        return;
        throw th;
    }

    public void addSearchCategorySubscribers(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/MyTag/#"), new J(this, 2)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/ShotMode/#"), new J(this, 4)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/People/#"), new J(this, 5)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Pet/#"), new J(this, 6)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Expressions/#"), new J(this, 7)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Location/#"), new J(this, 8)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Documents/#"), new J(this, 9)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Scene/#"), new J(this, 10)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Things/#"), new J(this, 11)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Scenery/#"), new J(this, 13)));
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/ScreenShot/#"), new J(this, 3)));
        }
    }

    public final void checkHiddenPeopleAndPets(Cursor cursor, String str) {
        int i2;
        if (LocationKey.isSearchCategoryCreatureMatch(str) && cursor != null && cursor.getCount() == 0) {
            Bundle extras = cursor.getExtras();
            if (extras == null || extras == Bundle.EMPTY) {
                extras = new Bundle();
            }
            int hiddenPeopleCount = new MpHelper().getHiddenPeopleCount();
            extras.putInt("hiddenPeopleCount", hiddenPeopleCount);
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                i2 = new MpHelper().getHiddenPetsCount();
                if (i2 > 0) {
                    PreferenceCache preferenceCache = PreferenceCache.SearchPetClusterRecognized;
                    if (!preferenceCache.getBoolean()) {
                        preferenceCache.setBoolean(true);
                    }
                }
                extras.putInt("hiddenPetsCount", i2);
            } else {
                i2 = 0;
            }
            cursor.setExtras(extras);
            if (hiddenPeopleCount == 0 && i2 == 0) {
                invokeFaceServiceIfNeeded();
            }
        }
    }

    public void clearCachedResults() {
        SearchEngineFactory.create(this.mAppContext).clearCache();
    }

    public Cursor clusterListDataQuery(String str, int i2) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        QueryParams fileIds = new QueryParams(DbKey.ALL_PICTURES).setFileIds(str);
        if (i2 > 0) {
            QueryParams limit = fileIds.setLimit(i2);
            if (i2 == 8) {
                z = true;
            } else {
                z = false;
            }
            limit.setUseIdOrder(z);
        }
        Cursor query = DbCompat.query(fileIds);
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "publishClusterListDataQuery " + fileIds + " " + getCursorInfo(query, currentTimeMillis));
        return query;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("lifecycle://onVisualSearchDestroyed", new J(this, 0)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://collection"), new J(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search"), new J(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/KeywordTab"), new J(this, 14)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/#"), new J(this, 15)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/KeywordStories"), new J(this, 16)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Keyword/#"), new J(this, 14)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/pictures_only/Keyword/#"), new J(this, 14)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/AutoComplete"), new J(this, 17)));
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/SelectMeAll"), new J(this, 18)));
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/AiEdited/#"), new J(this, 1)));
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Edited/#"), new J(this, 1)));
        }
        addSearchCategorySubscribers(arrayList);
    }

    public int getAddedCount(Bundle bundle) {
        return Integer.parseInt(bundle.getString("AddedCount", "0"));
    }

    public String getAdditionalInfo(Bundle bundle, String str) {
        return null;
    }

    public int getCursorIndex(Cursor[] cursorArr) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_QOD_SEARCH || cursorArr.length <= 5 || cursorArr[5] == null) {
            return 0;
        }
        return 5;
    }

    public String getExpandedDates(Bundle bundle) {
        return bundle.getString("ExpandedDates", (String) null);
    }

    public Cursor[] getFileIdsCursor(String str, String str2, String str3, String str4, String str5, int i2) {
        boolean z;
        QueryParams createCommonQueryParams = createCommonQueryParams(str5);
        if (str3 != null) {
            z = true;
        } else {
            z = false;
        }
        StorageRetrieval retriever = getRetriever(createCommonQueryParams, z);
        if (!LocationKey.isSearchAiEdited(str) && !LocationKey.isSearchEdited(str)) {
            return new Cursor[1];
        }
        retriever.setSupportedTimeline(!PreferenceFeatures.OneUi8x.IS_ONE_UI_85);
        if (LocationKey.isSearchAiEdited(str)) {
            return retriever.getGeneratedFileIds(str2, i2);
        }
        return retriever.getRecentlyEditedFileIds(str2, i2);
    }

    public Cursor[] getFilesCursor(String str, String str2, String str3, String str4) {
        boolean z;
        QueryParams createCommonQueryParams = createCommonQueryParams();
        if (str3 != null) {
            z = true;
        } else {
            z = false;
        }
        StorageRetrieval retriever = getRetriever(createCommonQueryParams, z);
        if (!LocationKey.isSearchAiEdited(str) && !LocationKey.isSearchEdited(str)) {
            return new Cursor[1];
        }
        retriever.setSupportedTimeline(!PreferenceFeatures.OneUi8x.IS_ONE_UI_85);
        if (LocationKey.isSearchAiEdited(str)) {
            return retriever.getGeneratedFiles(str2);
        }
        return retriever.getRecentlyEdited(str2);
    }

    public Cursor[] getFilterDataCursors(String str, String str2, String str3, String str4, String str5, int i2) {
        if (supportQodSearch()) {
            return getFileIdsCursor(str, str2, str3, str4, str5, i2);
        }
        return getFilesCursor(str, str2, str3, str4);
    }

    public FilterOnlyThem getFilterOnlyThemFromCursor(Cursor cursor) {
        if (!supportOnlyThem()) {
            return null;
        }
        try {
            return (FilterOnlyThem) cursor.getExtras().getParcelable("search_filter_only_them");
        } catch (Exception e) {
            a.r(e, new StringBuilder("getCursorFilterOnlyThem failed!! : "), this.TAG);
            return null;
        }
    }

    public int getRealCount(Cursor cursor, Bundle bundle) {
        return Math.max(cursor.getCount() - getAddedCount(bundle), 0);
    }

    public StorageRetrieval getRetriever(QueryParams queryParams, boolean z) {
        if (z) {
            return new IntelligentSearchRetrieval(this.mAppContext, queryParams, isPickMode());
        }
        return new SQLiteRetrieval(queryParams);
    }

    public String getSubCategoryForRequestFilter(String str, Bundle bundle) {
        String string = bundle.getString("sub", "");
        String string2 = bundle.getString("term");
        if (LocationKey.isSearchCategoryShotMode(str)) {
            return FilterResultsKeySet.getShotModeValue(string);
        }
        if ("Other Documents".equals(string)) {
            return "Documents".toLowerCase();
        }
        if ("persontag".equals(string2)) {
            return bundle.getString("title");
        }
        return string;
    }

    public boolean needKeywordSearchOnSupportMultipleKeyword(Bundle bundle) {
        if ((!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || !SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(bundle.getString("need_keyword_search"))) && !SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(bundle.getString("support_cluster_from_visual_search"))) {
            return false;
        }
        return true;
    }

    public boolean needRefreshFilterResults(Bundle bundle) {
        return BundleWrapper.getBoolean(bundle, "RefreshFilterResults", Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH));
    }

    public void publish(String str, Cursor[] cursorArr, Bundle bundle, String str2, boolean z, boolean z3) {
        Cursor cursor = cursorArr[getCursorIndex(cursorArr)];
        if (cursor != null) {
            if (z) {
                int realCount = getRealCount(cursor, bundle);
                if (z3) {
                    SimpleThreadPool.getInstance().execute(new S(this, str, bundle, realCount));
                } else {
                    publishFilterAndClusterResultsFrom(str, cursor, realCount, (Object[]) null);
                }
            }
            publishCursorArray(str, cursorArr, ArgumentsUtil.getSubscribeKey(bundle));
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.e(stringCompat, "null cursor : " + str + " , " + str2);
    }

    public void publishAnalysisTipCheck() {
        if (PreferenceFeatures.OneUi8x.SUPPORT_SEARCH_ANALYSIS_TIP_HEADER) {
            this.mBlackboard.postEvent(EventMessage.obtain(8032));
        }
    }

    public void publishCategoriesCache(Object obj, Bundle bundle) {
        Cursor query;
        int i2;
        bundle.remove("vs_use_cache");
        long currentTimeMillis = System.currentTimeMillis();
        this.mCategoriesFullLoadDone = false;
        this.mSuggestionKeywordRequested = false;
        List<String> supportCategories = getSupportCategories();
        this.mEntryCacheCursors = new Cursor[supportCategories.size()];
        String locationKeyFromReq = getLocationKeyFromReq(bundle);
        try {
            query = CacheProviderHelper.query((Collection<String>) supportCategories);
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("publishCategoriesCache");
            if (query != null) {
                i2 = query.getCount();
            } else {
                i2 = -1;
            }
            sb2.append(Logger.vt(Integer.valueOf(i2), Long.valueOf(currentTimeMillis)));
            Log.d(stringCompat, sb2.toString());
            if (query == null || query.getCount() <= 0 || !query.moveToFirst()) {
                if (query != null) {
                    query.close();
                }
                publishCategoriesData(obj, bundle);
                return;
            }
            HashMap hashMap = new HashMap();
            VisualSearchCategory.iterate(isPickMode(), new C0640u(1, hashMap));
            CacheProviderHelper.CacheReader cacheReader = new CacheProviderHelper.CacheReader(query);
            do {
                Integer num = (Integer) hashMap.get(Integer.valueOf(cacheReader.getKey()));
                if (num != null) {
                    this.mEntryCacheCursors[num.intValue()] = cacheReader.recoverCursor();
                }
            } while (cacheReader.moveToNext());
            this.mBlackboard.erase(DataKey.DATA_CURSOR(locationKeyFromReq));
            StringCompat stringCompat2 = this.TAG;
            Log.p(stringCompat2, "publishCategoriesCache " + getCursorListInfo(this.mEntryCacheCursors, currentTimeMillis));
            TranslationManager.getInstance().loadTranslationMap(this.mAppContext);
            publishSuggestionKeywords();
            publishCursorArray(locationKeyFromReq, this.mEntryCacheCursors);
            ThreadUtil.postOnBgThreadDelayed(new T(this, locationKeyFromReq, 1), 300);
            query.close();
            return;
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "publishCategoriesCache failed", (Throwable) e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void publishCategoryCache(Object obj, Bundle bundle) {
        Cursor query;
        int i2;
        bundle.remove("vs_use_cache");
        long currentTimeMillis = System.currentTimeMillis();
        String locationKeyFromReq = getLocationKeyFromReq(bundle);
        Cursor[] cursorArr = new Cursor[1];
        try {
            query = CacheProviderHelper.query(locationKeyFromReq);
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("publishCategoryCache");
            if (query != null) {
                i2 = query.getCount();
            } else {
                i2 = -1;
            }
            sb2.append(Logger.vt(Integer.valueOf(i2), Long.valueOf(currentTimeMillis)));
            Log.d(stringCompat, sb2.toString());
            if (query == null || query.getCount() <= 0 || !query.moveToFirst()) {
                if (query != null) {
                    query.close();
                }
                publishCategoryData(obj, bundle);
                return;
            }
            cursorArr[0] = new CacheProviderHelper.CacheReader(query).recoverCursor();
            this.mBlackboard.erase(DataKey.DATA_CURSOR(locationKeyFromReq));
            StringCompat stringCompat2 = this.TAG;
            Log.p(stringCompat2, "publishCategoryCache " + getCursorInfo(cursorArr[0], currentTimeMillis));
            publishCursorArray(locationKeyFromReq, cursorArr);
            ThreadUtil.postOnBgThreadDelayed(new T(this, locationKeyFromReq, 0), 300);
            query.close();
            return;
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "publishCategoryCache failed", (Throwable) e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void publishCategoryFiles(Object obj, Bundle bundle) {
        boolean z;
        if (needKeywordSearchOnSupportMultipleKeyword(bundle)) {
            publishKeywordFiles(obj, bundle);
            return;
        }
        String string = bundle.getString("sub");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string.trim())) {
            Bundle bundle2 = bundle;
            Log.e(this.TAG, "publishCategoryFiles skip. sub is empty");
            releaseRequestKey(bundle2);
            return;
        }
        String string2 = bundle.getString("SelectedFilter", (String) null);
        boolean needRefreshFilterResults = needRefreshFilterResults(bundle);
        if (!needRefreshFilterResults || string2 != null) {
            z = false;
        } else {
            z = true;
        }
        if (needRefreshFilterResults) {
            clearCachedResults();
        }
        String appendArgs = ArgumentsUtil.appendArgs(getLocationKeyFromReq(bundle), "from_instant_search", String.valueOf(BundleWrapper.getBoolean(bundle, "from_instant_search")));
        String additionalInfo = getAdditionalInfo(bundle, appendArgs);
        if (z) {
            publishWithFilterRequest(appendArgs, bundle, string, additionalInfo);
        } else {
            publishWithFilterRefresh(appendArgs, bundle, string, string2, additionalInfo, needRefreshFilterResults);
        }
    }

    public void publishClusterResults(String str, ArrayList<String> arrayList, String str2, String str3) {
        this.mBlackboard.postEvent(EventMessage.obtain(8002, new Object[]{str, arrayList, str3, str2}));
    }

    public void publishCreatureSelectMeAll(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        Cursor[] cursorArr = {DbCompat.query("mp://People")};
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "publishCreatureSelectMeAll " + getCursorInfo(cursorArr[0], currentTimeMillis));
        publishCursorArray("location://search/fileList/SelectMeAll", new Cursor[]{cursorArr[0]});
    }

    public final void publishDocumentFiles(Object obj, Bundle bundle) {
        boolean z;
        if (needKeywordSearchOnSupportMultipleKeyword(bundle)) {
            publishKeywordFiles(obj, bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String string = bundle.getString("sub");
        String string2 = bundle.getString("SelectedFilter", (String) null);
        boolean needRefreshFilterResults = needRefreshFilterResults(bundle);
        boolean z3 = false;
        if (!needRefreshFilterResults || string2 != null) {
            z = false;
        } else {
            z = true;
        }
        if (needRefreshFilterResults) {
            clearCachedResults();
        }
        QueryParams createCommonQueryParams = createCommonQueryParams();
        if (string2 != null) {
            z3 = true;
        }
        Cursor[] documentFiles = getRetriever(createCommonQueryParams, z3).getDocumentFiles(string, new SearchFilter.Builder(string).selectedFilter(string2));
        Log.i(this.TAG, "publishDocumentFiles " + getCursorListInfo(documentFiles, currentTimeMillis));
        publish(getLocationKeyFromReq(bundle), documentFiles, bundle, string, needRefreshFilterResults, z);
    }

    public final void publishExpressionsFiles(Object obj, Bundle bundle) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        String string = bundle.getString("sub");
        String string2 = bundle.getString("SelectedFilter", (String) null);
        boolean needRefreshFilterResults = needRefreshFilterResults(bundle);
        boolean z3 = false;
        if (!needRefreshFilterResults || string2 != null) {
            z = false;
        } else {
            z = true;
        }
        if (needRefreshFilterResults) {
            clearCachedResults();
        }
        QueryParams createCommonQueryParams = createCommonQueryParams();
        if (string2 != null) {
            z3 = true;
        }
        Cursor[] expressionFiles = getRetriever(createCommonQueryParams, z3).getExpressionFiles(string, new SearchFilter.Builder(string).selectedFilter(string2));
        Log.i(this.TAG, "publishExpressionsFiles " + getCursorListInfo(expressionFiles, currentTimeMillis));
        publish(getLocationKeyFromReq(bundle), expressionFiles, bundle, string, needRefreshFilterResults, z);
    }

    public void publishExtraResults(Cursor cursor, int i2) {
        try {
            String cursorStringExtra = getCursorStringExtra(cursor, "FallbackResult");
            Serializable serializable = cursor.getExtras().getSerializable("FallbackResultCollection");
            String cursorStringExtra2 = getCursorStringExtra(cursor, "Relationship");
            String cursorStringExtra3 = getCursorStringExtra(cursor, "NoRelationshipKeywords");
            String cursorStringExtra4 = getCursorStringExtra(cursor, "FuzzySuggestKeyword");
            String cursorStringExtra5 = getCursorStringExtra(cursor, "action");
            if (existFallbackResultCollection(serializable)) {
                publishHierarchicalSuggestionV2(serializable);
            } else if (!TextUtils.isEmpty(cursorStringExtra)) {
                publishHierarchicalSuggestion(cursorStringExtra, getCursorStringExtra(cursor, "FallbackResultTranslated"));
            } else if (!TextUtils.isEmpty(cursorStringExtra2) && ((PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || i2 == 0) && !isPickMode())) {
                publishRelationshipType(cursorStringExtra2, cursorStringExtra3);
            } else if (!TextUtils.isEmpty(cursorStringExtra5)) {
                publishSuggestedAction(cursorStringExtra5);
            } else if (!TextUtils.isEmpty(cursorStringExtra4)) {
                publishFuzzySuggestKeyword(cursorStringExtra4);
            } else if (i2 == 0 && PreferenceFeatures.OneUi8x.SUPPORT_SEARCH_ANALYSIS_TIP_HEADER) {
                publishAnalysisTipCheck();
            }
            String cursorStringExtra6 = getCursorStringExtra(cursor, "scs_disabled_reason");
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("extraResults info: ");
            sb2.append(Logger.getEncodedString("fR: " + cursorStringExtra + ", rT: " + cursorStringExtra2 + ", nRK: " + cursorStringExtra3 + ", fSK: " + cursorStringExtra4 + ", sA: " + cursorStringExtra5 + ",dR: " + cursorStringExtra6));
            Log.s(stringCompat, sb2.toString());
            this.mBlackboard.publish("data://user/ScsDisabledReason", cursorStringExtra6);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "publishExtraResults failed", (Throwable) e);
        }
    }

    public void publishFilterAndClusterResultsFrom(String str, Cursor cursor, int i2, Object[] objArr) {
        String str2;
        ArrayList<String> arrayList;
        try {
            String cursorStringExtra = getCursorStringExtra(cursor, "ClusterResults");
            if (PreferenceFeatures.OneUi7x.SUPPORT_TOP_RESULT_SEARCH) {
                arrayList = getCursorStringArrayListExtra(cursor, "TopResults");
                str2 = Features.isEnabled(Features.SUPPORT_PDC_CLUSTER) ? getCursorStringExtra(cursor, "PDCToken") : null;
            } else {
                arrayList = null;
                str2 = null;
            }
            Log.s(this.TAG, "cr(c): " + Logger.getEncodedString(cursorStringExtra) + "topResults(c): " + Logger.getEncodedString(getTopRecommendResultInfo(arrayList)) + "pdcToken(c): " + Logger.getEncodedString(str2));
            if (i2 < 16) {
                arrayList = null;
            }
            publishClusterResults(cursorStringExtra, arrayList, str2, str);
            if (!Features.isEnabled(Features.SUPPORT_EVENT_FACET) || objArr == null || !SmartSuggestionsSettingApi.getInstance().isAppPdcAvailability()) {
                publishFilterResults(i2, getCursorStringExtra(cursor, "FilterResults"), getFilterOnlyThemFromCursor(cursor), (LinkedHashMap<String, String>) null);
                return;
            }
            Log.s(this.TAG, "start to get a Recommend Info");
            PersonalDataCore.getInstance(this.mBlackboard).getRecommendMap(objArr[0], objArr[1], new K(this, cursor, i2));
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "publishFilterAndClusterResultsFrom failed", (Throwable) e);
        }
    }

    public void publishFilterResults(int i2, String str, FilterOnlyThem filterOnlyThem, LinkedHashMap<String, String> linkedHashMap) {
        this.mBlackboard.postEvent(EventMessage.obtain(8513, new Object[]{buildFilterResultEntry(i2, str, linkedHashMap), filterOnlyThem}));
    }

    public void publishFuzzySuggestKeyword(String str) {
        this.mBlackboard.postEvent(EventMessage.obtain(1078, str));
    }

    public void publishHierarchicalSuggestion(String str, String str2) {
        if (!Features.isEnabled(Features.SUPPORT_SCS_TRANSLATED_KEYWORD) && TextUtils.isEmpty(str2)) {
            str2 = TranslationManager.getInstance().getTranslatedString("location://search/fileList/Category/Scene", str);
        }
        this.mBlackboard.postEvent(EventMessage.obtain(1074, new Object[]{str, str2}));
    }

    public void publishHierarchicalSuggestionV2(Object obj) {
        this.mBlackboard.postEvent(EventMessage.obtain(1099, obj));
    }

    public void publishKeywordFiles(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        if (bundle.getString("sub") == null) {
            Log.i(this.TAG, "publishKeywordFiles failed. sub is null");
            releaseRequestKey(bundle);
            return;
        }
        BaseSearchEngine create = SearchEngineFactory.create(this.mAppContext);
        boolean needRefreshFilterResults = needRefreshFilterResults(bundle);
        if (needRefreshFilterResults) {
            create.clearCache();
        }
        if (!PocFeatures.ASK_SCREENSHOT || !BundleWrapper.getBoolean(bundle, "ask_screenshot", false)) {
            SearchFilter createSearchFilter = createSearchFilter(bundle);
            Cursor[] searchForTimeline = create.searchForTimeline(createSearchFilter);
            if (searchForTimeline != null) {
                Cursor cursor = searchForTimeline[getCursorIndex(searchForTimeline)];
                if (cursor != null) {
                    StringCompat stringCompat = this.TAG;
                    Log.i(stringCompat, "publishKeywordFiles " + getCursorInfo(cursor, currentTimeMillis));
                    TranslationManager.getInstance().loadTranslationMap(this.mAppContext);
                    String locationKeyFromReq = getLocationKeyFromReq(bundle);
                    int realCount = getRealCount(cursor, bundle);
                    if (needRefreshFilterResults) {
                        publishExtraResults(cursor, realCount);
                    }
                    publishCursorArray(locationKeyFromReq, searchForTimeline, ArgumentsUtil.getSubscribeKey(bundle));
                    if (needRefreshFilterResults) {
                        publishFilterAndClusterResultsFrom(locationKeyFromReq, cursor, realCount, getQueryBundleObject(searchForTimeline, createSearchFilter));
                        return;
                    }
                    return;
                }
                return;
            }
            Log.i(this.TAG, "publishKeywordFiles CURSOR{null}");
            return;
        }
        bundle.putString("file_ids", create.getResultIdList(createSearchFilter(bundle)));
        bundle.putString("sub", ScreenShotFilterType.All.key());
        publishScreenShotFiles(obj, bundle);
    }

    public final void publishLocationFiles(Object obj, Bundle bundle) {
        boolean z;
        if (needKeywordSearchOnSupportMultipleKeyword(bundle)) {
            publishKeywordFiles(obj, bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String string = bundle.getString("sub");
        String string2 = bundle.getString("SelectedFilter", (String) null);
        boolean needRefreshFilterResults = needRefreshFilterResults(bundle);
        boolean z3 = false;
        if (!needRefreshFilterResults || string2 != null) {
            z = false;
        } else {
            z = true;
        }
        if (needRefreshFilterResults) {
            clearCachedResults();
        }
        String string3 = bundle.getString("term");
        QueryParams createCommonQueryParams = createCommonQueryParams();
        if (string2 != null) {
            z3 = true;
        }
        Cursor[] locationFiles = getRetriever(createCommonQueryParams, z3).getLocationFiles(string, new SearchFilter.Builder(string).selectedFilter(string2), string3);
        Log.i(this.TAG, "publishLocationFiles " + getCursorListInfo(locationFiles, currentTimeMillis));
        publish(getLocationKeyFromReq(bundle), locationFiles, bundle, string, needRefreshFilterResults, z);
    }

    public final void publishMyTagFiles(Object obj, Bundle bundle) {
        boolean z;
        if (needKeywordSearchOnSupportMultipleKeyword(bundle)) {
            publishKeywordFiles(obj, bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String string = bundle.getString("sub");
        String string2 = bundle.getString("SelectedFilter", (String) null);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string.trim())) {
            Bundle bundle2 = bundle;
            Log.e(this.TAG, "publishMyTagFiles skip. sub is empty");
            releaseRequestKey(bundle2);
            return;
        }
        boolean needRefreshFilterResults = needRefreshFilterResults(bundle);
        boolean z3 = false;
        if (!needRefreshFilterResults || string2 != null) {
            z = false;
        } else {
            z = true;
        }
        if (needRefreshFilterResults) {
            clearCachedResults();
        }
        QueryParams createCommonQueryParams = createCommonQueryParams();
        if (string2 != null) {
            z3 = true;
        }
        Cursor[] myTagsFiles = getRetriever(createCommonQueryParams, z3).getMyTagsFiles(string, new SearchFilter.Builder(string).selectedFilter(string2));
        Log.i(this.TAG, "publishMyTagFiles " + getCursorListInfo(myTagsFiles, currentTimeMillis));
        publish(getLocationKeyFromReq(bundle), myTagsFiles, bundle, string, needRefreshFilterResults, z);
    }

    public final void publishPeopleFiles(Object obj, Bundle bundle) {
        boolean z;
        if (needKeywordSearchOnSupportMultipleKeyword(bundle)) {
            publishKeywordFiles(obj, bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String string = bundle.getString("sub");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("SelectedFilter", (String) null);
        boolean needRefreshFilterResultsForPersonTag = needRefreshFilterResultsForPersonTag(bundle);
        boolean z3 = false;
        if (!needRefreshFilterResultsForPersonTag || string3 != null) {
            z = false;
        } else {
            z = true;
        }
        if (needRefreshFilterResultsForPersonTag) {
            clearCachedResults();
        }
        QueryParams createCommonQueryParams = createCommonQueryParams();
        if (string3 != null) {
            z3 = true;
        }
        Cursor[] peopleFiles = getRetriever(createCommonQueryParams, z3).getPeopleFiles(string, new SearchFilter.Builder(string).selectedFilter(string3), string2);
        Log.i(this.TAG, "publishPeopleFiles " + getCursorListInfo(peopleFiles, currentTimeMillis));
        publish(getLocationKeyFromReq(bundle), peopleFiles, bundle, string, needRefreshFilterResultsForPersonTag, z);
    }

    public final void publishPetsFiles(Object obj, Bundle bundle) {
        boolean z;
        if (needKeywordSearchOnSupportMultipleKeyword(bundle)) {
            publishKeywordFiles(obj, bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String string = bundle.getString("sub");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("SelectedFilter", (String) null);
        boolean needRefreshFilterResults = needRefreshFilterResults(bundle);
        boolean z3 = false;
        if (!needRefreshFilterResults || string3 != null) {
            z = false;
        } else {
            z = true;
        }
        if (needRefreshFilterResults) {
            clearCachedResults();
        }
        QueryParams createCommonQueryParams = createCommonQueryParams();
        if (string3 != null) {
            z3 = true;
        }
        Cursor[] petFiles = getRetriever(createCommonQueryParams, z3).getPetFiles(string, new SearchFilter.Builder(string).selectedFilter(string3), string2);
        Log.i(this.TAG, "publishPetsFiles " + getCursorListInfo(petFiles, currentTimeMillis));
        publish(getLocationKeyFromReq(bundle), petFiles, bundle, string, needRefreshFilterResults, z);
    }

    public void publishRelationshipType(String str, String str2) {
        if (hasNoRelationshipPeople()) {
            this.mBlackboard.postEvent(EventMessage.obtain(1075, new Object[]{str, str2}));
        }
    }

    public final void publishSceneFiles(Object obj, Bundle bundle, String str) {
        boolean z;
        if (needKeywordSearchOnSupportMultipleKeyword(bundle)) {
            publishKeywordFiles(obj, bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String string = bundle.getString("sub");
        String string2 = bundle.getString("SelectedFilter", (String) null);
        boolean needRefreshFilterResults = needRefreshFilterResults(bundle);
        boolean z3 = false;
        if (!needRefreshFilterResults || string2 != null) {
            z = false;
        } else {
            z = true;
        }
        if (needRefreshFilterResults) {
            clearCachedResults();
        }
        QueryParams createCommonQueryParams = createCommonQueryParams();
        if (string2 != null) {
            z3 = true;
        }
        Cursor[] sceneFiles = getRetriever(createCommonQueryParams, z3).getSceneFiles(string, new SearchFilter.Builder(string).selectedFilter(string2), str);
        Log.i(this.TAG, "publishSceneFiles " + getCursorListInfo(sceneFiles, currentTimeMillis));
        publish(getLocationKeyFromReq(bundle), sceneFiles, bundle, string, needRefreshFilterResults, z);
    }

    public final void publishSceneryFiles(Object obj, Bundle bundle) {
        publishSceneFiles(obj, bundle, "Scenery");
    }

    public void publishScreenShotFiles(Object obj, Bundle bundle) {
        Bundle bundle2 = bundle;
        Log.s(this.TAG, "publishScreenShotFiles");
        if (needKeywordSearchOnSupportMultipleKeyword(bundle2) || bundle2.getString("SelectedFilter", (String) null) != null) {
            publishKeywordFiles(obj, bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String string = bundle2.getString("sub_sub", "");
        String lastSegment = StringCompat.getLastSegment(string, GlobalPostProcInternalPPInterface.SPLIT_REGEX, bundle2.getString("sub", ""));
        if (ScreenShotFilterType.All.key().equals(lastSegment)) {
            publishAllScreenShotFiles(bundle2, currentTimeMillis);
            return;
        }
        String beforeLastSegment = StringCompat.getBeforeLastSegment(string, GlobalPostProcInternalPPInterface.SPLIT_REGEX, "");
        Cursor[] cursorArr = {null, null, null, null, null, null, null};
        String str = beforeLastSegment;
        String string2 = bundle2.getString("file_ids", (String) null);
        String str2 = str;
        LatchBuilder current = new LatchBuilder(C0212a.l("publishScreenShotFiles(R)?", string)).setCurrent(new Q(this, str2, string2, lastSegment, cursorArr));
        boolean isEnabled = PocFeatures.isEnabled(PocFeatures.ScreenshotWithSubSubCat);
        if (!ScreenShotFilterType.CAPTURE.equals(str2) && (isEnabled || ScreenShotFilterType.isSubCategory(lastSegment))) {
            QueryParams tag = createCommonQueryParams().setDbKey("mp://ScreenShot/Sub/List").setTag(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, lastSegment);
            if (string2 != null) {
                tag.setFileIds(string2);
            }
            current.addWorker(new C0625e(cursorArr, 9, tag));
        }
        current.setPostExecutor((Runnable) new C0627g(this, cursorArr, currentTimeMillis, bundle, 1));
        current.start();
    }

    public final void publishShotModeFiles(Object obj, Bundle bundle) {
        boolean z;
        if (needKeywordSearchOnSupportMultipleKeyword(bundle)) {
            publishKeywordFiles(obj, bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String string = bundle.getString("sub");
        String string2 = bundle.getString("SelectedFilter", (String) null);
        boolean needRefreshFilterResults = needRefreshFilterResults(bundle);
        boolean z3 = false;
        if (!needRefreshFilterResults || string2 != null) {
            z = false;
        } else {
            z = true;
        }
        if (needRefreshFilterResults) {
            clearCachedResults();
        }
        QueryParams createCommonQueryParams = createCommonQueryParams();
        if (string2 != null) {
            z3 = true;
        }
        Cursor[] shotModeFiles = getRetriever(createCommonQueryParams, z3).getShotModeFiles(string, new SearchFilter.Builder(string).selectedFilter(string2));
        Log.i(this.TAG, "publishShotModeFiles " + getCursorListInfo(shotModeFiles, currentTimeMillis));
        publish(getLocationKeyFromReq(bundle), shotModeFiles, bundle, string, needRefreshFilterResults, z);
    }

    public void publishSuggestedAction(String str) {
        if (!isPickMode()) {
            this.mBlackboard.postEvent(EventMessage.obtain(1098, str));
        }
    }

    public final void publishThingsFiles(Object obj, Bundle bundle) {
        publishSceneFiles(obj, bundle, "Things");
    }

    public final void publishThingsSceneFiles(Object obj, Bundle bundle) {
        publishSceneFiles(obj, bundle, "Things & Scenery");
    }

    public final void releaseCategoriesJob(Cursor[] cursorArr, String str) {
        closeCursors(cursorArr);
        this.mBlackboard.erase(CommandKey.DATA_REQUEST(str));
        synchronized (this.LOCK) {
            try {
                if (!this.mCategoriesFutureMap.isEmpty()) {
                    for (Future<Cursor> removeInQueue : this.mCategoriesFutureMap.values()) {
                        ThreadPool.getInstance().removeInQueue(removeInQueue);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean supportOnlyThem() {
        if (!Features.isEnabled(Features.SUPPORT_SCS_SEARCH) || !Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY) || !PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
            return false;
        }
        return true;
    }

    public Bundle getFilterResults(String str, int i2, String str2, Bundle bundle) {
        String string = bundle.getString("term");
        SearchFilter.Builder term = new SearchFilter.Builder(str2).term(string);
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
            term.selectedFilter(bundle.getString("SelectedFilter", (String) null));
        }
        if (isClusterEnabled(str, string)) {
            term.setClusterEnable(true);
            term.setFullCluster(str.contains("location://search/fileList/Keyword"));
        }
        setPickerSearchFilterIfNeeded((LaunchIntent) getBlackboard().read("data://launch_intent"), term);
        return SearchEngineFactory.create(this.mAppContext).getFilterResultsBundle(term.build(this.mAppContext), i2);
    }

    public void publishFilterAndClusterResultsFrom(String str, Bundle bundle, int i2) {
        String str2;
        ArrayList<String> arrayList;
        ArrayList<String> arrayList2 = null;
        publishFilterResults(i2, bundle != null ? bundle.getString("FilterResults") : null, bundle != null ? (FilterOnlyThem) bundle.getParcelable("search_filter_only_them") : null, (LinkedHashMap<String, String>) null);
        String string = (bundle == null || !PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster)) ? null : bundle.getString("ClusterResults");
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP_RESULT_SEARCH) {
            arrayList = bundle != null ? bundle.getStringArrayList("TopResults") : null;
            Features features = Features.SUPPORT_PDC_CLUSTER;
            str2 = (!Features.isEnabled(features) || bundle == null || !Features.isEnabled(features)) ? null : bundle.getString("PDCToken");
        } else {
            str2 = null;
            arrayList = null;
        }
        Log.s(this.TAG, "cr(b): " + Logger.getEncodedString(string) + "topResults(b): " + Logger.getEncodedString(getTopRecommendResultInfo(arrayList)) + "pdcToken(b): " + Logger.getEncodedString(str2));
        if (i2 >= 16) {
            arrayList2 = arrayList;
        }
        publishClusterResults(string, arrayList2, str2, str);
    }
}
