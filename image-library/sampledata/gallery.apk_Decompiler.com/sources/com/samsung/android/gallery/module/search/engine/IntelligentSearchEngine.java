package com.samsung.android.gallery.module.search.engine;

import A.a;
import B5.e;
import N2.j;
import R6.c;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.SearchApi;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.bixbyod.BixbyODKeywordSearch;
import com.samsung.android.gallery.module.search.languagepack.NeuralTranslator;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;
import com.samsung.android.gallery.support.type.IntelligentSearchIndexField;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntelligentSearchEngine extends BaseSearchEngine {
    private static final boolean SUPPORT_EVENT_FACET = Features.isEnabled(Features.SUPPORT_EVENT_FACET);
    private static final boolean SUPPORT_HIERARCHICAL_SUGGESTION_V2 = Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION_V2);
    private static final boolean SUPPORT_HIERARCHICAL_SUGGESTION_V2_AS_LIST = Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION_V2_AS_LIST);
    private final BixbyODKeywordSearch mBixbyODKeywordSearch = new BixbyODKeywordSearch();
    private final CursorResultChecker mCursorResultChecker = new CursorResultChecker();
    private final FuzzyKeywordSearch mFuzzyKeywordSearch = new FuzzyKeywordSearch();
    final IntelligentSearch mIntelligentSearch;
    private TimeTickLog mTimeTickLog;

    public IntelligentSearchEngine(Context context, IntelligentSearch intelligentSearch) {
        super(context);
        this.mIntelligentSearch = intelligentSearch;
    }

    private SearchFilter createMajorFilter(String str, SearchFilter searchFilter) {
        searchFilter.addMajorFilter(new SearchFilter.Builder(str).build(this.mAppContext));
        return searchFilter;
    }

    private int getCount(Cursor cursor, SearchFilter searchFilter) {
        return cursor.getCount() - searchFilter.getAddedCount();
    }

    private void getExtraResultOnly(Cursor cursor, ExtraResults extraResults, SearchFilter searchFilter) {
        setNeuralTranslatorIdentifyLanguage(cursor, searchFilter);
        extraResults.parse(cursor.getExtras(), searchFilter.fromInstantSearch());
        if (supportOnlyThem()) {
            extraResults.initFilterOnlyThem(searchFilter);
            if (extraResults.hasOnlyThemFaces()) {
                extraResults.setFilterOnlyThemAvailable(cursor);
            }
        }
    }

    private String getOcrIds(ExtraResults extraResults) {
        Cursor query;
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        long currentTimeMillis = System.currentTimeMillis();
        ClusterResultsEntry build = new ClusterResultsEntry.Extractor().extract(new Object[]{extraResults.getClusterResult(), null, null, null}, "ocrtext").build();
        if (!build.isEmpty()) {
            try {
                query = this.mIntelligentSearch.query(new SearchFilter.Builder(build.getItem(0).getName()).term("ocrtext").setFilterEnable(false).setSupportTimeline(false).build(this.mAppContext));
                if (query.moveToFirst()) {
                    do {
                        stringJoiner.add(query.getString(0));
                    } while (query.moveToNext());
                }
                query.close();
            } catch (Exception e) {
                String str = this.TAG;
                Log.se(str, "getOcrIds > Couldn't search items : " + e);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        j.m(currentTimeMillis, "", this.TAG, new StringBuilder("getOcrIds : +"));
        return stringJoiner.toString();
        throw th;
    }

    private SearchResult getSearchResult(SearchFilter searchFilter) {
        Cursor cursor;
        String rawKeyword = searchFilter.getRawKeyword();
        stampStartingLog(rawKeyword);
        String str = rawKeyword + searchFilter.getSelectedFilter();
        String cachedResult = this.mIntelligentSearch.getCachedResult(str);
        ExtraResults extraResults = new ExtraResults();
        if (cachedResult == null) {
            ArrayList arrayList = new ArrayList();
            cursor = queryInternal(searchFilter, extraResults);
            if (cursor != null) {
                getIdsNExtraResult(cursor, extraResults, arrayList, searchFilter);
                setScsQueryExtra(cursor, extraResults);
            }
            if (!Features.isEnabled(Features.SUPPORT_DYNAMIC_SEARCH_SUGGESTION)) {
                searchSuggestionKeywords(rawKeyword, searchFilter, arrayList);
            }
            if (!TextUtils.isEmpty(extraResults.getFuzzyKeyword())) {
                str = extraResults.getFuzzyKeyword() + searchFilter.getSelectedFilter();
            }
            String joinedString = getJoinedString(arrayList);
            if (needToCheckFileIdValidation(searchFilter)) {
                cachedResult = checkFileIdValidation(searchFilter, joinedString);
            } else {
                cachedResult = joinedString;
            }
            this.mIntelligentSearch.saveCacheResult(str, cachedResult);
        } else {
            cursor = null;
        }
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
            return new SearchResult(cursor, cachedResult, extraResults);
        }
        Utils.closeSilently(cursor);
        return new SearchResult(cachedResult, extraResults);
    }

    private void joinId(String str, ArrayList<Long> arrayList) {
        joinId(UnsafeCast.toLong(str, -1), arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getIdsNExtraResult$6(Cursor cursor, ArrayList arrayList, ExtraResults extraResults) {
        if (cursor.moveToFirst()) {
            do {
                joinId(cursor.getString(0), (ArrayList<Long>) arrayList);
                extraResults.parseFilterResult(cursor);
                extraResults.parseFrameId(cursor);
                if (supportOnlyThem() && extraResults.hasOnlyThemFaces()) {
                    extraResults.setFilterOnlyThemAvailableForEach(cursor);
                }
            } while (cursor.moveToNext());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getIdsNExtraResult$7(SearchFilter searchFilter, ExtraResults extraResults) {
        if (supportOcrIcon(searchFilter)) {
            String ocrIds = getOcrIds(extraResults);
            if (!TextUtils.isEmpty(ocrIds)) {
                extraResults.setOcrResultIds(ocrIds);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$searchResult$0(AtomicReference atomicReference, SearchFilter searchFilter, SearchResult searchResult, QueryParams queryParams, AtomicReference atomicReference2) {
        Cursor cursor;
        Cursor cursor2 = null;
        if (searchFilter.isForQueryOnDemand()) {
            cursor = null;
        } else {
            cursor = getDataCursor(searchFilter, searchResult, queryParams);
        }
        atomicReference.set(cursor);
        if (searchFilter.isForQueryOnDemand() || searchFilter.supportTimeline()) {
            cursor2 = getDateCursor(searchFilter, searchResult, queryParams);
        }
        atomicReference2.set(cursor2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$searchResult$1(AtomicReference atomicReference, SearchFilter searchFilter, SearchResult searchResult, QueryParams queryParams) {
        atomicReference.set(getRealRatioCursor(searchFilter, searchResult, queryParams));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$searchResult$2(AtomicReference atomicReference, AtomicReference atomicReference2, Cursor[][] cursorArr, SearchFilter searchFilter, AtomicReference atomicReference3, SearchResult searchResult, long j2) {
        Cursor cursor;
        Cursor cursor2;
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
            Cursor cursor3 = (Cursor) atomicReference.get();
            if (searchFilter.supportTimeline()) {
                cursor = (Cursor) atomicReference2.get();
            } else {
                cursor = null;
            }
            Cursor cursor4 = (Cursor) atomicReference3.get();
            if (searchFilter.isForQueryOnDemand()) {
                cursor2 = getFileIdsCursor(searchResult, (Cursor) atomicReference2.get(), searchFilter);
            } else {
                cursor2 = null;
            }
            cursorArr[0] = new Cursor[]{cursor3, cursor, null, cursor4, null, cursor2, searchResult.getIntelligentSearchCursor(), null};
        } else {
            cursorArr[0] = new Cursor[]{(Cursor) atomicReference.get(), (Cursor) atomicReference2.get()};
        }
        setScsExtra(cursorArr[0], searchResult, searchFilter.isForQueryOnDemand());
        Log.s(this.TAG, C0086a.j(j2, Logger.toString(cursorArr[0]), " +", new StringBuilder("search(Normal) ")));
    }

    private Cursor[] searchResult(SearchFilter searchFilter) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth) || !"poitag".equals(searchFilter.getTerm())) {
            long currentTimeMillis = System.currentTimeMillis();
            SearchResult searchResult = getSearchResult(searchFilter);
            QueryParams createQueryParams = createQueryParams(searchFilter);
            AtomicReference atomicReference = new AtomicReference();
            AtomicReference atomicReference2 = new AtomicReference();
            AtomicReference atomicReference3 = atomicReference2;
            SearchFilter searchFilter2 = searchFilter;
            AtomicReference atomicReference4 = new AtomicReference();
            LatchBuilder addWorker = new LatchBuilder("searchResult").setCurrent(new d(this, atomicReference, searchFilter2, searchResult, createQueryParams, atomicReference3)).addWorker(new a(this, atomicReference4, searchFilter2, searchResult, createQueryParams, 3));
            SearchFilter searchFilter3 = searchFilter2;
            SearchResult searchResult2 = searchResult;
            Cursor[][] cursorArr = {new Cursor[0]};
            addWorker.setPostExecutor((Runnable) new e(this, atomicReference, atomicReference3, cursorArr, searchFilter3, atomicReference4, searchResult2, currentTimeMillis)).setTimeout(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS).start();
            return cursorArr[0];
        }
        Log.s(this.TAG, "search dummy");
        return new Cursor[]{createDummyCursor()};
    }

    private void setExtrasResult(Cursor cursor, ExtraResults extraResults, SearchFilter searchFilter) {
        Bundle extras = cursor.getExtras();
        if (SUPPORT_HIERARCHICAL_SUGGESTION_V2 || SUPPORT_HIERARCHICAL_SUGGESTION_V2_AS_LIST) {
            extras.putSerializable("FallbackResultCollection", extraResults.getFallbackResultCollection());
        } else {
            extras.putString("FallbackResult", extraResults.getFallbackResult());
            extras.putString("FallbackResultTranslated", extraResults.getFallbackResultTranslated());
        }
        extras.putString("Relationship", extraResults.getRelationshipResult());
        extras.putString("NoRelationshipKeywords", extraResults.getNoRelationKeywords());
        if (getCount(cursor, searchFilter) > 2) {
            extras.putString("FilterResults", extraResults.getFilterResult());
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster)) {
            extras.putString("ClusterResults", extraResults.getClusterResult());
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP_RESULT_SEARCH) {
            extras.putStringArrayList("TopResults", extraResults.getTopRecommendResult());
        }
        if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
            extras.putString("PDCToken", extraResults.getPdcToken());
        }
        extras.putString("action", extraResults.getActionResult());
        extras.putString("FuzzySuggestKeyword", extraResults.getFuzzyKeyword());
        extras.putParcelable("search_filter_only_them", extraResults.getFilterOnlyThem());
        extras.putString("scs_disabled_reason", extraResults.getScsDisabledReason());
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SseDebug)) {
            extras.putStringArrayList("total_query_elapsed_time_info", extraResults.getQueryTimeInfo());
            extras.putLong("llm_query_elapsed_time", extraResults.getLlmQueryParse());
            extras.putString("llm_parsed_query", extraResults.getLlmParsedQuery());
        }
        cursor.setExtras(extras);
    }

    private void setNeuralTranslatorIdentifyLanguage(Cursor cursor, SearchFilter searchFilter) {
        if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_LANGUAGE_PACK) && cursor.getCount() == 0) {
            NeuralTranslator.getInstance().identifyLanguage(searchFilter.getRawKeyword());
        }
    }

    private void stampFinishingLog(int i2) {
        TimeTickLog timeTickLog = this.mTimeTickLog;
        if (timeTickLog != null) {
            timeTickLog.appendExtra(" [count]: " + i2);
            this.mTimeTickLog.tockWithLog(200);
        }
    }

    private boolean supportOcrIcon(SearchFilter searchFilter) {
        return !searchFilter.isPickMode();
    }

    private boolean supportOnlyThem() {
        if (!Features.isEnabled(Features.SUPPORT_SCS_SEARCH) || !Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY) || !PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
            return false;
        }
        return true;
    }

    public String checkFileIdValidation(SearchFilter searchFilter, String str) {
        Cursor searchItemFileIds;
        long currentTimeMillis = System.currentTimeMillis();
        QueryParams createQueryParams = createQueryParams(searchFilter);
        createQueryParams.mIsForOnDemandQuery = true;
        createQueryParams.mUseFileIdsConcat = true;
        try {
            searchItemFileIds = new SearchHelper(createQueryParams).searchItemFileIds(str, searchFilter);
            if (searchItemFileIds.moveToFirst()) {
                str = searchItemFileIds.getString(searchItemFileIds.getColumnIndex("__fileIds"));
            }
            searchItemFileIds.close();
        } catch (Exception e) {
            j.v("checkFileIdValidation failed : ", e, this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        a.x(new StringBuilder("checkFileIdValidation : +"), currentTimeMillis, this.TAG);
        return str;
        throw th;
    }

    public void clearCache() {
        this.mIntelligentSearch.clearCachedResult();
    }

    public Cursor getDataCursor(SearchFilter searchFilter, SearchResult searchResult, QueryParams queryParams) {
        Cursor searchItems = new SearchHelper(queryParams).searchItems(searchResult.getResultIdList(), searchFilter);
        if (searchItems != null) {
            stampFinishingLog(searchItems.getCount());
            setExtrasResult(searchItems, searchResult.getExtraResults(), searchFilter);
        }
        return searchItems;
    }

    public Cursor getDateCursor(SearchFilter searchFilter, SearchResult searchResult, QueryParams queryParams) {
        return getCursorByPartition(searchResult.getResultIdList(), new W9.a(queryParams, searchFilter, 0));
    }

    public Cursor getFileIdsCursor(SearchResult searchResult, Cursor cursor, SearchFilter searchFilter) {
        Cursor convertFileIdsCollectionCursor = convertFileIdsCollectionCursor(cursor, searchFilter.getOrder());
        stampFinishingLog(convertFileIdsCollectionCursor.getCount());
        setExtrasResult(convertFileIdsCollectionCursor, searchResult.getExtraResults(), searchFilter);
        return convertFileIdsCollectionCursor;
    }

    public Bundle getFilterResultsBundle(SearchFilter searchFilter, int i2) {
        Cursor query;
        if (i2 > 2) {
            try {
                query = this.mIntelligentSearch.query(searchFilter);
                if (query != null) {
                    ExtraResults extraResults = new ExtraResults();
                    getExtraResultOnly(query, extraResults, searchFilter);
                    Bundle bundle = new Bundle();
                    bundle.putString("FilterResults", extraResults.getFilterResult());
                    bundle.putString("action", extraResults.getActionResult());
                    if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster)) {
                        bundle.putString("ClusterResults", extraResults.getClusterResult());
                    }
                    if (PreferenceFeatures.OneUi7x.SUPPORT_TOP_RESULT_SEARCH) {
                        bundle.putStringArrayList("TopResults", extraResults.getTopRecommendResult());
                    }
                    if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
                        bundle.putString("PDCToken", extraResults.getPdcToken());
                    }
                    bundle.putParcelable("search_filter_only_them", extraResults.getFilterOnlyThem());
                    query.close();
                    return bundle;
                } else if (query == null) {
                    return null;
                } else {
                    query.close();
                    return null;
                }
            } catch (Exception e) {
                String str = this.TAG;
                Log.se(str, "getFilterResultsCursor : Couldn't search items : " + e);
                return null;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            Log.sw(this.TAG, "getFilterResultsCursor : dataCount is under MIN_COUNT_FOR_FILTER_RESULTS!!");
            return null;
        }
        throw th;
    }

    public void getIdsNExtraResult(Cursor cursor, ExtraResults extraResults, ArrayList<Long> arrayList, SearchFilter searchFilter) {
        setNeuralTranslatorIdentifyLanguage(cursor, searchFilter);
        extraResults.parse(cursor.getExtras(), searchFilter.fromInstantSearch());
        if (supportOnlyThem()) {
            extraResults.initFilterOnlyThem(searchFilter);
        }
        ExtraResults extraResults2 = extraResults;
        new LatchBuilder("").setCurrent(new A6.a((Object) this, (Object) cursor, (Object) arrayList, (Object) extraResults2, 24)).addWorker(new c(this, searchFilter, extraResults2, 21)).setTimeout(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS).start();
    }

    public String getJoinedString(ArrayList<Long> arrayList) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        arrayList.forEach(new e(stringJoiner, 7));
        return stringJoiner.toString();
    }

    public int[] getMediaTypeCount(Bundle bundle) {
        SearchFilter build = new SearchFilter.Builder(bundle.getString("sub")).term(bundle.getString("term")).setClusterEnable(true).setFullCluster(true).build(this.mAppContext);
        ExtraResults extraResults = new ExtraResults();
        Cursor queryInternal = queryInternal(build, extraResults);
        if (queryInternal == null || queryInternal.getCount() == 0) {
            return null;
        }
        extraResults.parse(queryInternal.getExtras(), build.fromInstantSearch());
        Iterator<ClusterResultsEntity> it = new ClusterResultsEntry.Extractor().extract(new Object[]{extraResults.getClusterResult(), extraResults.getTopRecommendResult(), null, null}).build().getAllItems().iterator();
        int i2 = 0;
        int i7 = 0;
        while (it.hasNext()) {
            ClusterResultsEntity next = it.next();
            if (TextUtils.equals(next.getCategory(), ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE)) {
                if (next.getName().equals("image")) {
                    i2 = next.getCount();
                } else {
                    i7 = next.getCount();
                }
            }
        }
        return new int[]{i2, i7};
    }

    public Cursor getRealRatioCursor(SearchFilter searchFilter, SearchResult searchResult, QueryParams queryParams) {
        return getCursorByPartition(searchResult.getResultIdList(), new W9.a(queryParams, searchFilter, 1));
    }

    public String getResultIdList(SearchFilter searchFilter) {
        return getSearchResult(searchFilter).getResultIdList();
    }

    public boolean needToCheckFileIdValidation(SearchFilter searchFilter) {
        if (!searchFilter.isForQueryOnDemand() || "key_word".equals(searchFilter.getTerm())) {
            return false;
        }
        return true;
    }

    public Cursor queryInternal(SearchFilter searchFilter, ExtraResults extraResults) {
        try {
            Cursor query = this.mIntelligentSearch.query(searchFilter);
            if (this.mCursorResultChecker.isValid(query, searchFilter, extraResults)) {
                return query;
            }
            Utils.closeSilently(query);
            searchFilter.semanticQueryOff();
            if (Features.isEnabled(Features.SUPPORT_SEARCH_KEYWORD_FROM_BIXBY_OD)) {
                Cursor query2 = this.mBixbyODKeywordSearch.query(this.mIntelligentSearch, searchFilter, extraResults);
                if (this.mCursorResultChecker.isValid(query2, searchFilter, extraResults)) {
                    return query2;
                }
                Utils.closeSilently(query2);
            }
            return this.mFuzzyKeywordSearch.query(this.mIntelligentSearch, searchFilter, extraResults);
        } catch (Exception e) {
            String str = this.TAG;
            Log.se(str, "queryInternal > Couldn't search items : " + e);
            return null;
        }
    }

    public Cursor search(SearchFilter searchFilter) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth) || !"poitag".equals(searchFilter.getTerm())) {
            long currentTimeMillis = System.currentTimeMillis();
            SearchResult searchResult = getSearchResult(searchFilter);
            Cursor dataCursor = getDataCursor(searchFilter, searchResult, createQueryParams(searchFilter));
            setScsExtra(dataCursor, searchResult);
            String str = this.TAG;
            Log.s(str, "search " + Logger.toString(dataCursor) + " +" + (System.currentTimeMillis() - currentTimeMillis));
            return dataCursor;
        }
        Log.s(this.TAG, "search dummy");
        return createDummyCursor();
    }

    public Cursor searchAutoComplete(SearchFilter searchFilter) {
        searchFilter.setAutoCompleteQueryEnabled(true);
        searchFilter.replaceTerm(IntelligentSearchIndexField.getAutoCompleteTermSelection(searchFilter.isTagKeyword()));
        try {
            return this.mIntelligentSearch.queryAutoComplete(searchFilter);
        } catch (Exception e) {
            String str = this.TAG;
            Log.se(str, "AutoComplete : " + e);
            return null;
        }
    }

    public Cursor[] searchForTimeline(SearchFilter searchFilter) {
        return searchResult(searchFilter);
    }

    public void searchSuggestionKeywords(String str, SearchFilter searchFilter, ArrayList<Long> arrayList) {
        Cursor searchSuggestionKeyword;
        try {
            searchSuggestionKeyword = new SearchApi(createQueryParams(searchFilter)).searchSuggestionKeyword(createMajorFilter(str, searchFilter));
            if (searchSuggestionKeyword != null) {
                if (searchSuggestionKeyword.moveToFirst()) {
                    do {
                        joinId(searchSuggestionKeyword.getLong(searchSuggestionKeyword.getColumnIndex("__absID")), arrayList);
                    } while (searchSuggestionKeyword.moveToNext());
                }
            }
            if (searchSuggestionKeyword != null) {
                searchSuggestionKeyword.close();
                return;
            }
            return;
        } catch (SQLException e) {
            Log.se(this.TAG, e.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void setScsExtra(Cursor[] cursorArr, SearchResult searchResult, boolean z) {
        if (cursorArr != null && searchResult != null) {
            setScsExtra(z ? cursorArr[5] : cursorArr[0], searchResult);
        }
    }

    public void setScsQueryExtra(Cursor cursor, ExtraResults extraResults) {
        Bundle bundle;
        if (SUPPORT_EVENT_FACET) {
            if (cursor != null) {
                bundle = cursor.getExtras();
            } else {
                bundle = null;
            }
            if (bundle != null) {
                extraResults.setScsQueryExtra(bundle.getBundle("searchScsQueryExtra"));
            }
        }
    }

    public void stampStartingLog(String str) {
        this.mTimeTickLog = new TimeTickLog("intelligent search");
    }

    public String tag() {
        return "IntelSearchEngine";
    }

    private void joinId(long j2, ArrayList<Long> arrayList) {
        if (j2 > 0) {
            arrayList.add(Long.valueOf(j2));
            return;
        }
        String str = this.TAG;
        Log.s(str, "search invalid id=" + j2);
    }

    public void setScsExtra(Cursor cursor, SearchResult searchResult) {
        Bundle extras = cursor != null ? cursor.getExtras() : new Bundle();
        if (extras == null || extras == Bundle.EMPTY) {
            extras = new Bundle();
            if (cursor != null) {
                cursor.setExtras(extras);
            } else {
                return;
            }
        }
        ExtraResults extraResults = searchResult.getExtraResults();
        if (!TextUtils.isEmpty(extraResults.getOcrResultIds())) {
            extras.putString("ocr_ids", extraResults.getOcrResultIds());
        }
        if (!extraResults.getFrameIdMap().isEmpty()) {
            extras.putSerializable("frame_id_map", extraResults.getFrameIdMap());
        }
        if (SUPPORT_EVENT_FACET) {
            extras.putString("searchScsQueryResultIdList", searchResult.getResultIdList());
            extras.putBundle("searchScsQueryExtra", extraResults.getOriginBundle());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SearchResult {
        private final ExtraResults mExtraResults;
        private final String mIdList;
        private final Cursor mIntelligentSearchCursor;

        public SearchResult(Cursor cursor, String str, ExtraResults extraResults) {
            this.mIntelligentSearchCursor = cursor;
            this.mIdList = str;
            this.mExtraResults = extraResults;
        }

        public ExtraResults getExtraResults() {
            return this.mExtraResults;
        }

        public Cursor getIntelligentSearchCursor() {
            return this.mIntelligentSearchCursor;
        }

        public String getResultIdList() {
            return this.mIdList;
        }

        public SearchResult(String str, ExtraResults extraResults) {
            this((Cursor) null, str, extraResults);
        }
    }
}
