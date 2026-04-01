package com.samsung.android.gallery.module.publisher;

import A.a;
import M4.d;
import N2.j;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.database.lockedalbum.LockedAlbum;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.module.search.recommendation.IQuerySuggester;
import com.samsung.android.gallery.module.search.recommendation.IRecommendationItem;
import com.samsung.android.gallery.module.search.recommendation.QuerySuggesterFactory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.Def;
import i.C0212a;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class SearchDataBasePublisher extends CursorPublisher {
    Context mAppContext;
    protected final LinkedBlockingQueue<CancellationSignal> mCancelSignals = new LinkedBlockingQueue<>();
    Boolean mIsPickMode;
    private IQuerySuggester mQuerySuggester;
    final HashMap<String, ThreadPool.Job<Cursor>> mSearchJobs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AutoCompleteResult {
    }

    public SearchDataBasePublisher(Blackboard blackboard) {
        super(blackboard);
        HashMap<String, ThreadPool.Job<Cursor>> hashMap = new HashMap<>();
        this.mSearchJobs = hashMap;
        hashMap.put("location://search/fileList/Category/MyTag", new C(this, 0));
        hashMap.put("location://search/fileList/Category/ShotMode", new C(this, 2));
        hashMap.put("location://search/fileList/Category/Documents", new C(this, 3));
        hashMap.put("location://search/fileList/Category/People", new C(this, 4));
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            hashMap.put("location://search/fileList/Category/PeopleAndPets", new C(this, 5));
            hashMap.put("location://search/fileList/Category/PeopleAndPetsHide", new C(this, 6));
            hashMap.put("location://search/fileList/Category/Pet", new C(this, 7));
        }
        if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
            hashMap.put("location://search/fileList/Category/CreatureSelect", new C(this, 8));
        }
        hashMap.put("location://search/fileList/Category/Expressions", new C(this, 9));
        hashMap.put("location://search/fileList/Category/Scene", new C(this, 10));
        if (Features.isEnabled(Features.SUPPORT_LOCATION)) {
            hashMap.put("location://search/fileList/Category/Location", new C(this, 11));
        }
        if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
            hashMap.put("location://search/fileList/Category/PeopleHide", new C(this, 12));
        }
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            hashMap.put("location://search/fileList/Category/PeopleSelect", new C(this, 13));
        }
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            hashMap.put("location://search/fileList/Category/Activity", new C(this, 14));
            hashMap.put("location://search/fileList/Category/HiddenPeople", new C(this, 15));
            hashMap.put("location://search/fileList/Category/HiddenPeopleAndPets", new C(this, 16));
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY) {
            hashMap.put("location://search/fileList/Category/MyQuery", new Object());
        }
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT) {
            hashMap.put("location://search/fileList/Category/ScreenShot", new C(this, 17));
        }
        if (PocFeatures.isEnabled(PocFeatures.CreatureMultiPick)) {
            hashMap.put("location://search/fileList/Category/CreatureMultiPick", new C(this, 18));
        }
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            hashMap.put("location://search/fileList/Category/Stories", new C(this, 1));
        }
    }

    private String getCreaturePickDbKey() {
        if (PickerUtil.isPeopleMultiplePick(this.mBlackboard)) {
            return "mp://People";
        }
        if (PickerUtil.isPetMultiplePick(this.mBlackboard)) {
            return "mp://Pets";
        }
        return "mp://PeopleAndPets";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$0(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://myTag"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$1(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://ShotMode"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$10(ThreadPool.JobContext jobContext) {
        if (exposeLocationCategory()) {
            return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://Location"));
        }
        return createDummyCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$11(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://PeopleHide"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$12(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://People"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$13(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://Activity"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$14(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://PeopleHidden"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$15(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://PeopleAndPetsHidden"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$17(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://ScreenShot"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$18(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey(getCreaturePickDbKey()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$19() {
        return DbCompat.query(createCommonQueryParams(true).setDbKey(DbKey.STORIES));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$2(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://Document"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$20(String str, ThreadPool.JobContext jobContext) {
        return retrieveCursor(str, new D(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$3(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://People").setEssentialFaceOnly(true));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$4(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://PeopleAndPets").setEssentialFaceOnly(true));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$5(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://PeopleAndPetsHide"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$6(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://Pets"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$7(ThreadPool.JobContext jobContext) {
        String str;
        QueryParams createCommonQueryParams = createCommonQueryParams(true);
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            str = "mp://PeopleAndPets";
        } else {
            str = "mp://People";
        }
        return DbCompat.query(createCommonQueryParams.setDbKey(str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$8(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://Expression"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$new$9(ThreadPool.JobContext jobContext) {
        return DbCompat.query(createCommonQueryParams(true).setDbKey("mp://Scene"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishRecommendationData$22(AtomicReference atomicReference) {
        atomicReference.set(queryHistoryData());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishRecommendationData$23(AtomicReference atomicReference) {
        atomicReference.set(querySuggestionData());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCancellationSignal$21(String str, String str2) {
        StringCompat stringCompat = this.TAG;
        StringBuilder t = C0212a.t(str, "[");
        t.append(Logger.getEncodedString(str2));
        t.append("] : query is canceled");
        Log.w(stringCompat, t.toString());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortCategoryDataIfNeeded$24(MediaItem mediaItem, MediaItem mediaItem2) {
        int compare = Integer.compare(mediaItem.getCount(), mediaItem2.getCount());
        if (compare == 0) {
            return mediaItem.getTitle().compareToIgnoreCase(mediaItem2.getTitle());
        }
        return -compare;
    }

    /* access modifiers changed from: private */
    public void publishBlurryFiles(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = DbCompat.query(new QueryParams("mp://SearchSuggestion/Blurred"));
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "publishBlurryFiles " + getCursorInfo(query, currentTimeMillis));
        publishCursorArray(getLocationKeyFromReq(bundle), new Cursor[]{query}, ArgumentsUtil.getSubscribeKey(bundle));
    }

    /* access modifiers changed from: private */
    public void publishFromTimeFiles(Object obj, Bundle bundle) {
        publishFromTimeFiles(getLocationKeyFromReq(bundle), bundle);
    }

    /* access modifiers changed from: private */
    public void publishLastLocationFiles(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = DbCompat.query(new QueryParams("mp://Location/files").setSubCategory(bundle.getString("sub")));
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "publishLastLocationFiles " + getCursorInfo(query, currentTimeMillis));
        publishCursorArray(getLocationKeyFromReq(bundle), new Cursor[]{query}, ArgumentsUtil.getSubscribeKey(bundle));
    }

    /* access modifiers changed from: private */
    public void publishLatestCategoryFiles(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = DbCompat.query(new QueryParams("mp://Scene/files").setSubCategory(bundle.getString("sub")));
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "publishLatestCategoryFiles " + getCursorInfo(query, currentTimeMillis));
        publishCursorArray(getLocationKeyFromReq(bundle), new Cursor[]{query}, ArgumentsUtil.getSubscribeKey(bundle));
    }

    /* access modifiers changed from: private */
    public void publishLatestItemFiles(Object obj, Bundle bundle) {
        publishFromTimeFiles(getLocationKeyFromReq(bundle), bundle);
    }

    /* access modifiers changed from: private */
    public void publishRecommendationData(Object obj, Bundle bundle) {
        boolean z;
        if (!isPickMode() || PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            long currentTimeMillis = System.currentTimeMillis();
            Object[] objArr = (Object[]) obj;
            boolean z3 = false;
            if (objArr == null || objArr.length <= 0 || !((Boolean) objArr[0]).booleanValue()) {
                z = false;
            } else {
                z = true;
            }
            if (objArr != null && objArr.length > 1 && ((Boolean) objArr[1]).booleanValue()) {
                z3 = true;
            }
            AtomicReference atomicReference = new AtomicReference();
            AtomicReference atomicReference2 = new AtomicReference();
            LatchBuilder current = new LatchBuilder("publishRecommendationData").setTimeout(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS).setCurrent(new G(this, atomicReference2, 0));
            if (!z) {
                current.addWorker(new G(this, atomicReference, 1));
            }
            current.start();
            Log.i(this.TAG, "publishRecommendationData" + Logger.vt(atomicReference2.get(), Long.valueOf(currentTimeMillis)));
            this.mBlackboard.erase(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation"));
            if (!z) {
                this.mBlackboard.publish("data://user/search/RecommendationData/suggestion", atomicReference.get());
            }
            this.mBlackboard.publish("data://user/search/RecommendationData/history", atomicReference2.get());
            if (z3) {
                this.mBlackboard.publish("data://user/search/RecommendationData/shortcut", queryShortcutData());
                this.mBlackboard.publish("data://user/search/RecommendationData/activity", queryCategoryData("mp://Activity"));
                this.mBlackboard.publish("data://user/search/RecommendationData/shotTypes", queryCategoryData("mp://ShotMode"));
                this.mBlackboard.publish("data://user/search/RecommendationData/myTag", queryCategoryData("mp://myTag"));
                return;
            }
            return;
        }
        this.mBlackboard.erase(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation"));
    }

    /* access modifiers changed from: private */
    public void publishSmilesFiles(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = DbCompat.query(new QueryParams("mp://SearchSuggestion/Smile"));
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "publishSmilesFiles " + getCursorInfo(query, currentTimeMillis));
        publishCursorArray(getLocationKeyFromReq(bundle), new Cursor[]{query}, ArgumentsUtil.getSubscribeKey(bundle));
    }

    /* access modifiers changed from: private */
    public void publishVideoFiles(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = DbCompat.query(new QueryParams("mp://SearchSuggestion/Video"));
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "publishVideoFiles " + getCursorInfo(query, currentTimeMillis));
        publishCursorArray(getLocationKeyFromReq(bundle), new Cursor[]{query}, ArgumentsUtil.getSubscribeKey(bundle));
    }

    private ArrayList<MediaItem> queryCategoryData(String str) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        Cursor query = DbCompat.query(createCommonQueryParams(true).setDbKey(str));
        if (query != null) {
            try {
                if (!query.isClosed() && query.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemBuilder.create(query));
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        sortCategoryDataIfNeeded(str, arrayList);
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    private ArrayList<HistoryItem> queryHistoryData() {
        Cursor cursor;
        try {
            cursor = SearchHistory.getInstance().getCursor();
            if (cursor != null) {
                ArrayList<HistoryItem> historyItemList = SearchHistory.getInstance().getHistoryItemList(cursor);
                cursor.close();
                return historyItemList;
            }
            Log.sw(this.TAG, "History cursor is null");
            if (cursor != null) {
                cursor.close();
            }
            return new ArrayList<>();
        } catch (Exception unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private ArrayList<MediaItem> queryShortcutData() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        Cursor myQueryCursor = SearchMyQuery.getInstance().getMyQueryCursor();
        if (myQueryCursor != null) {
            try {
                if (!myQueryCursor.isClosed() && myQueryCursor.moveToFirst()) {
                    do {
                        MediaItem load = MyQueryUtil.load(myQueryCursor);
                        if (load != null) {
                            arrayList.add(load);
                        }
                    } while (myQueryCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (myQueryCursor != null) {
            myQueryCursor.close();
        }
        return arrayList;
        throw th;
    }

    private AbstractQueue<IRecommendationItem> querySuggestionData() {
        return getQuerySuggester().getSuggestion(this.mAppContext, this.mBlackboard, loadLatestContentFileId());
    }

    private Cursor retrieveCursor(String str, Supplier<Cursor> supplier) {
        if (VisualSearchCategory.support(str, isPickMode())) {
            return supplier.get();
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Object, java.util.Comparator] */
    private void sortCategoryDataIfNeeded(String str, ArrayList<MediaItem> arrayList) {
        if (!arrayList.isEmpty() && "mp://ShotMode".equals(str)) {
            arrayList.sort(new Object());
        }
    }

    public QueryParams createCommonQueryParams(String str) {
        return createCommonQueryParams(false, str);
    }

    public Cursor createDummyCursor() {
        return new MatrixCursor(new String[]{"_id"});
    }

    public SearchFilter createSearchFilter(Bundle bundle) {
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        String string = bundle.getString("sub");
        String string2 = bundle.getString("fuzzyKeyword");
        String string3 = bundle.getString("term", (String) null);
        String string4 = bundle.getString("SelectedFilter", (String) null);
        boolean z = BundleWrapper.getBoolean(bundle, "from_bixby", false);
        boolean isClusterEnabled = isClusterEnabled(getLocationKeyFromReq(bundle), string3);
        boolean z3 = BundleWrapper.getBoolean(bundle, "force_sync_SSE");
        String string5 = BundleWrapper.getString(bundle, "requestFacetTerms", (String) null);
        int i2 = BundleWrapper.getInt(bundle, "requestMaxFacetTermsSize", 0);
        boolean z7 = BundleWrapper.getBoolean(bundle, "facetOnly", false);
        SearchFilter.Builder fromInstantSearch = new SearchFilter.Builder(string).selectedFilter(string4).setClusterEnable(isClusterEnabled).setFullCluster(isClusterEnabled).setFuzzyKeyword(string2).setForceSync(z3).fromInstantSearch(BundleWrapper.getBoolean(bundle, "from_instant_search", false));
        if (string3 != null) {
            fromInstantSearch.term(string3);
        }
        if (string5 != null) {
            fromInstantSearch.setFacetTermsFields(new ArrayList(Arrays.asList(string5.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX))));
        }
        if (i2 > 0) {
            fromInstantSearch.setMaxFacetTermsSize(i2);
        }
        if (z7) {
            fromInstantSearch.requestFacetOnly();
        }
        if (launchIntent != null) {
            setPickerSearchFilterIfNeeded(launchIntent, fromInstantSearch);
            if (launchIntent.isFromBixby() && z) {
                fromInstantSearch.period(launchIntent.getBixbySearchKeywordPeriod()).order(launchIntent.getBixbySearchKeywordOrder()).countryInfo(launchIntent.getBixbySearchKeywordCountry());
            }
        }
        if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            fromInstantSearch.setExpandedDates(bundle.getString("ExpandedDates", (String) null)).setAddedCount(Integer.parseInt(bundle.getString("AddedCount", "0")));
        }
        SearchFilter build = fromInstantSearch.build(this.mAppContext);
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CHECK_SUGGESTED_KEYWORD_FEATURE)) {
            build.setSuggestedKeywordFeature(bundle.getString("suggestedKeywordFeature"));
        }
        Features features = Features.SUPPORT_PDC_CLUSTER;
        if (Features.isEnabled(features)) {
            build.setPdcToken(bundle.getString("search_pdc_token"));
        }
        build.setQueryOnDemand(supportQodSearch());
        build.setSupportTimeline(!BundleWrapper.getBoolean(bundle, "disableTimeline"));
        if (Features.isEnabled(features) && bundle.getString("relationship_name") != null) {
            build.setPdcInfo(PersonalDataCore.getInstance(this.mBlackboard).composePdcInfoForKeywordSearch(bundle.getString("relationship_name"), Arrays.asList(bundle.getString("selected_creature").split(GlobalPostProcInternalPPInterface.SPLIT_REGEX))));
        }
        return build;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("data://app_context", new E(this, 0)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation"), new E(this, 1)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation/SuggestionKeyword/VIDEOS"), new E(this, 2)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation/SuggestionKeyword/SMILES"), new E(this, 3)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation/SuggestionKeyword/BLURRY"), new E(this, 4)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation/SuggestionKeyword/TIME"), new E(this, 5)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation/SuggestionKeyword/LOCATION"), new E(this, 6)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation/SuggestionKeyword/CATEGORY"), new E(this, 7)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation/SuggestionKeyword/RECENTLY_ADDED"), new E(this, 8)));
    }

    public final boolean exposeLocationCategory() {
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR) || PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getCursorStringArrayListExtra(Cursor cursor, String str) {
        try {
            return cursor.getExtras().getStringArrayList(str);
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            StringBuilder k = j.k("fail ", str, " :");
            k.append(e.getMessage());
            Log.e(stringCompat, k.toString());
            return new ArrayList<>(Arrays.asList(new String[]{""}));
        }
    }

    public String getCursorStringExtra(Cursor cursor, String str) {
        try {
            return cursor.getExtras().getString(str);
        } catch (Exception e) {
            a.r(e, j.k("fail ", str, " :"), this.TAG);
            return "";
        }
    }

    public final IQuerySuggester getQuerySuggester() {
        if (this.mQuerySuggester == null) {
            this.mQuerySuggester = QuerySuggesterFactory.create();
        }
        return this.mQuerySuggester;
    }

    public boolean isClusterEnabled(String str, String str2) {
        if ("location://search/fileList/KeywordClusterPictures".equals(str) || "location://search/fileList/PdcPictures".equals(str) || !"key_word".equals(str2)) {
            return false;
        }
        return true;
    }

    public final boolean isPickMode() {
        if (this.mIsPickMode == null) {
            this.mIsPickMode = Boolean.valueOf(!PickerUtil.isNormalLaunchMode(this.mBlackboard));
        }
        return this.mIsPickMode.booleanValue();
    }

    public final long loadLatestContentFileId() {
        return PreferenceCache.SearchLatestFileId.getLong();
    }

    public void onContext(Object obj, Bundle bundle) {
        this.mAppContext = BlackboardUtils.readAppContext(this.mBlackboard);
    }

    public void setCancellationSignal(String str, SearchFilter searchFilter, String str2) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new F(this, str2, str));
        this.mCancelSignals.forEach(new d(16));
        this.mCancelSignals.clear();
        this.mCancelSignals.add(cancellationSignal);
        searchFilter.setCancellationSignal(cancellationSignal);
    }

    public void setPickerSearchFilterIfNeeded(LaunchIntent launchIntent, SearchFilter.Builder builder) {
        if (isPickMode()) {
            builder.setPickMode(true);
            MediaFilterType filterMediaType = PickerUtil.getFilterMediaType(launchIntent);
            if (MediaFilterType.IMAGE_ONLY.equals(filterMediaType)) {
                builder.mediaType(String.valueOf(MediaType.Image.toInt()));
            } else if (MediaFilterType.VIDEO_ONLY.equals(filterMediaType)) {
                builder.mediaType(String.valueOf(MediaType.Video.toInt()));
            }
            builder.setLocalOnly(launchIntent.isLocalContentOnly());
        }
    }

    public boolean supportQodSearch() {
        return PreferenceFeatures.OneUi6x.SUPPORT_QOD_SEARCH;
    }

    public QueryParams createCommonQueryParams(boolean z, String str) {
        QueryParams createCommonQueryParams = createCommonQueryParams(z);
        if (!TextUtils.isEmpty(str)) {
            createCommonQueryParams.setExpandedDates(str);
        }
        return createCommonQueryParams;
    }

    private void publishFromTimeFiles(String str, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = DbCompat.query(new QueryParams(DbKey.ALL_PICTURES).setStartTime(UnsafeCast.toLong(bundle.getString("sub"), 0)));
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "publishFromTimeFiles " + getCursorInfo(query, currentTimeMillis));
        publishCursorArray(str, new Cursor[]{query}, ArgumentsUtil.getSubscribeKey(bundle));
    }

    public QueryParams createCommonQueryParams() {
        return createCommonQueryParams(false);
    }

    public QueryParams createCommonQueryParams(boolean z) {
        QueryParams queryParams = new QueryParams();
        if (z) {
            queryParams.setReplaceVolumeName();
        }
        if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
            queryParams.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
        }
        if (isPickMode()) {
            LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
            queryParams.setMediaTypeFilter(PickerUtil.getFilterMediaType(launchIntent).toString());
            queryParams.setStorageTypes((launchIntent == null || !launchIntent.isLocalContentOnly()) ? QueryParams.DbStorageType.All : QueryParams.DbStorageType.LocalOnly);
        }
        return queryParams;
    }
}
