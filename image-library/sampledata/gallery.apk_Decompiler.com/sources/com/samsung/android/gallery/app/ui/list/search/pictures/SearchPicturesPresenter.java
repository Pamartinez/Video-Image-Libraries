package com.samsung.android.gallery.app.ui.list.search.pictures;

import A4.C0381p;
import A4.C0382q;
import C3.i;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowInsets;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.internals.CreateMyQueryCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ICreatureContactDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreaturePicturesDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.RelationshipPickerLauncher;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.ForegroundViewController;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterType;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.search.abstraction.FragmentVolatileStatus;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarDataKey;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import o6.p;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import r6.e;
import v5.d;
import v5.f;
import v5.g;
import v5.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesPresenter<V extends ISearchPicturesView> extends PicturesPresenter<V> implements SearchToolbar.OptionMenuListener {
    protected CreaturePicturesDelegate mCreaturePicturesDelegate = new CreaturePicturesDelegate((ISearchPicturesView) this.mView);
    private ForegroundViewController mForegroundViewController;
    protected MediaItem mHeaderItem;
    private boolean mIsSearchResultCollected;
    private Runnable mPendingUpdateRunnable;
    private final SubscriberListener mPermissionListener = new h(this, 0);
    private boolean mRestoreSearchToolbarVisibility;
    private boolean mResultItemClicked;
    private String mScreenId;
    protected SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();
    private Object mSuggesterData;
    private SuggesterType mSuggesterType = SuggesterType.NORMAL;

    public SearchPicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public void clearSearchExpansion(Object obj, Bundle bundle) {
        Optional.ofNullable((SearchPicturesAdapter) ((ISearchPicturesView) this.mView).getAdapter()).ifPresent(new e(23));
    }

    private void collectExtraSearchResult() {
        boolean z;
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "fuzzyKeyword");
        if (!TextUtils.isEmpty(argValue)) {
            SearchWordCollector instance = SearchWordCollector.getInstance();
            SearchWordCollector.Type type = SearchWordCollector.Type.KEYWORD_SUGGESTION;
            if (((ISearchPicturesView) this.mView).getDataCount() > 0) {
                z = true;
            } else {
                z = false;
            }
            instance.collect(argValue, type, z, this.mResultItemClicked);
        }
        collectSelectedFilterKeyword();
    }

    private void collectSelectedEachFilterKeyword(JSONObject jSONObject) {
        boolean z;
        String string = jSONObject.getString("name");
        SearchWordCollector instance = SearchWordCollector.getInstance();
        SearchWordCollector.Type type = SearchWordCollector.Type.FACET;
        if (((ISearchPicturesView) this.mView).getDataCount() > 0) {
            z = true;
        } else {
            z = false;
        }
        instance.collect(string, type, z, this.mResultItemClicked);
    }

    private void collectSelectedFilterKeyword() {
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "SelectedFilter", (String) null);
        if (!TextUtils.isEmpty(argValue)) {
            try {
                if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
                    JSONArray jSONArray = new JSONArray(argValue);
                    int length = jSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        collectSelectedEachFilterKeyword(jSONArray.getJSONObject(i2));
                    }
                    return;
                }
                collectSelectedEachFilterKeyword(new JSONObject(argValue));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String getSearchToolbarTitle() {
        if (isTextSearch()) {
            return getTagName();
        }
        return " ";
    }

    private String getTargetLocationKey(String str) {
        if (str == null) {
            Log.se(this.TAG, "New location key is null");
            return null;
        }
        this.mSuggesterType = SuggesterType.NORMAL;
        this.mSuggesterData = null;
        ((ISearchPicturesView) this.mView).onReopenData();
        this.mCreaturePicturesDelegate.resetCreatureCount();
        return ArgumentsUtil.removeArg(str, "force_sync_SSE");
    }

    private boolean getToolbarDimVisibility() {
        if (((ISearchPicturesView) this.mView).isEmptyViewShowing()) {
            return false;
        }
        ForegroundViewController foregroundViewController = this.mForegroundViewController;
        if (foregroundViewController == null || !foregroundViewController.isVisible()) {
            return true;
        }
        return false;
    }

    private String getToolbarTitle() {
        if (isCreaturePictures()) {
            return null;
        }
        return getTagName();
    }

    private boolean handleForegroundEvent(EventMessage eventMessage) {
        ForegroundViewController foregroundViewController = this.mForegroundViewController;
        if (foregroundViewController == null || !foregroundViewController.handleEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    private boolean isCreaturePictures() {
        if (LocationKey.isSearchCategoryPeople(getLocationKey()) || LocationKey.isSearchCategoryPet(getLocationKey())) {
            return true;
        }
        return false;
    }

    private boolean isOnGoingFaceClusterMerge() {
        return this.mCreaturePicturesDelegate.isOnGoingFaceClusterMerge();
    }

    private boolean isTextSearch() {
        if (getLocationKey() != null) {
            String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "SelectedFilter", (String) null);
            String argValue2 = ArgumentsUtil.getArgValue(getLocationKey(), "term", (String) null);
            if (!TextUtils.isEmpty(argValue) || !"key_word".equals(argValue2) || isKeywordSearchedOnSupportMultipleKeyword()) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$2(Object obj, Bundle bundle) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(21));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$3(SearchHeaderView searchHeaderView) {
        searchHeaderView.bind(this.mHeaderItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$4(Object obj, Bundle bundle) {
        Optional.ofNullable(((ISearchPicturesView) this.mView).getHeaderView()).ifPresent(new v5.e(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToViewer$11(MediaItem mediaItem) {
        onListItemClickInternal(0, mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        onRequestPermissionResult(obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSearchShortcutCreated$5() {
        new CreateMyQueryCmd().execute(this, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(ForegroundViewController foregroundViewController) {
        foregroundViewController.onViewCreated(this.mSearchToolbarDelegate);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewDestroy$6(ForegroundViewController foregroundViewController) {
        foregroundViewController.onDestroy(getActivity());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaItem lambda$publishLocationHeaderItem$12(ThreadPool.JobContext jobContext) {
        MediaItem mediaItem;
        try {
            mediaItem = ((ISearchPicturesView) this.mView).getMediaData(getLocationKey()).read(0);
        } catch (Exception e) {
            e.printStackTrace();
            mediaItem = null;
        }
        if (mediaItem != null) {
            return mediaItem;
        }
        Log.se(this.TAG, "publish header item : null");
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishLocationHeaderItem$13(Future future) {
        MediaItem mediaItem = (MediaItem) future.get();
        if (mediaItem != null) {
            ((ISearchPicturesView) this.mView).bindHeader(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerScsContentObserver$7() {
        if (((ISearchPicturesView) this.mView).isViewResumed()) {
            reloadSearchFilter();
        } else {
            this.mPendingUpdateRunnable = new g(this, 0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerScsContentObserver$8() {
        if (((ISearchPicturesView) this.mView).isViewResumed()) {
            forceReloadData();
        } else {
            this.mPendingUpdateRunnable = new g(this, 1);
        }
    }

    /* JADX WARNING: type inference failed for: r8v11, types: [android.os.Parcelable] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$reloadSearchFilter$9(android.content.Context r8, android.os.Bundle r9, java.lang.String r10) {
        /*
            r7 = this;
            r7.clearCacheResult()
            V r0 = r7.mView
            com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView r0 = (com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView) r0
            int r4 = r0.getDataCount()
            com.samsung.android.gallery.module.search.engine.BaseSearchEngine r0 = com.samsung.android.gallery.module.search.engine.SearchEngineFactory.create(r8)
            r0.clearCache()
            com.samsung.android.gallery.database.dbtype.SearchFilter$Builder r0 = new com.samsung.android.gallery.database.dbtype.SearchFilter$Builder
            java.lang.String r1 = "sub"
            java.lang.String r1 = r9.getString(r1)
            r0.<init>(r1)
            java.lang.String r1 = "term"
            java.lang.String r1 = r9.getString(r1)
            com.samsung.android.gallery.database.dbtype.SearchFilter$Builder r0 = r0.term(r1)
            boolean r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD
            if (r1 == 0) goto L_0x0034
            java.lang.String r1 = "SelectedFilter"
            java.lang.String r9 = r9.getString(r1)
            r0.selectedFilter(r9)
        L_0x0034:
            com.samsung.android.gallery.module.search.engine.BaseSearchEngine r9 = com.samsung.android.gallery.module.search.engine.SearchEngineFactory.create(r8)
            com.samsung.android.gallery.database.dbtype.SearchFilter r8 = r0.build(r8)
            android.os.Bundle r8 = r9.getFilterResultsBundle(r8, r4)
            r9 = 0
            if (r8 == 0) goto L_0x004b
            java.lang.String r0 = "FilterResults"
            java.lang.String r0 = r8.getString(r0)
            r2 = r0
            goto L_0x004c
        L_0x004b:
            r2 = r9
        L_0x004c:
            if (r8 == 0) goto L_0x0057
            java.lang.String r9 = "search_filter_only_them"
            android.os.Parcelable r8 = r8.getParcelable(r9)
            r9 = r8
            com.samsung.android.gallery.module.search.root.FilterOnlyThem r9 = (com.samsung.android.gallery.module.search.root.FilterOnlyThem) r9
        L_0x0057:
            java.lang.String r8 = "title"
            java.lang.String r0 = ""
            java.lang.String r3 = com.samsung.android.gallery.support.utils.ArgumentsUtil.getArgValue((java.lang.String) r10, (java.lang.String) r8, (java.lang.String) r0)
            com.samsung.android.gallery.module.search.root.FilterResultsEntry$Builder r1 = new com.samsung.android.gallery.module.search.root.FilterResultsEntry$Builder
            com.samsung.android.gallery.support.blackboard.Blackboard r8 = r7.mBlackboard
            r1.<init>(r8)
            boolean r5 = r7.isSearchToolbarEnabled()
            r6 = 0
            com.samsung.android.gallery.module.search.root.FilterResultsEntry$Builder r8 = r1.extract(r2, r3, r4, r5, r6)
            r10 = 20
            com.samsung.android.gallery.module.search.root.FilterResultsEntry$Builder r8 = r8.setMaxFilterNum(r10)
            r10 = 1
            com.samsung.android.gallery.module.search.root.FilterResultsEntry$Builder r8 = r8.setSortByCount(r10)
            com.samsung.android.gallery.module.search.root.FilterResultsEntry r8 = r8.build()
            com.samsung.android.gallery.support.utils.StringCompat r10 = r7.TAG
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r0 = new java.lang.Object[]{r2, r9, r0}
            java.lang.String r1 = "reloadSearchFilter"
            com.samsung.android.gallery.support.utils.Log.d(r10, r1, r0)
            com.samsung.android.gallery.support.blackboard.Blackboard r7 = r7.mBlackboard
            r10 = 8513(0x2141, float:1.1929E-41)
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r9}
            com.samsung.android.gallery.support.blackboard.key.EventMessage r8 = com.samsung.android.gallery.support.blackboard.key.EventMessage.obtain(r10, r8)
            r7.postEvent(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter.lambda$reloadSearchFilter$9(android.content.Context, android.os.Bundle, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void moveToViewer() {
        if (!Features.isEnabled(Features.SUPPORT_BIXBY_UTT_KEYWORD_SEARCH)) {
            LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
            PicturesViewAdapter adapter = ((ISearchPicturesView) this.mView).getAdapter();
            if (launchIntent != null && launchIntent.getBixbySearchKeywordOrderIsSet() && adapter != null && adapter.getDataCount() > 0) {
                Optional.ofNullable(adapter.getMediaItemFromCache(getFirstItemPositionForBixby())).ifPresent(new v5.e(this, 2));
            }
        }
    }

    private void onFuzzySuggestKeywordPublished(String str) {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || ArgumentsUtil.getArgValue(getLocationKey(), "from_instant_search", false)) {
            this.mSuggesterData = str;
            this.mSuggesterType = SuggesterType.FUZZY_KEYWORD;
            return;
        }
        refreshLocationKey(ArgumentsUtil.appendArgs(getLocationKey(), "fuzzyKeyword", str));
    }

    private void onHierarchicalSuggestionPublished(Object obj) {
        this.mSuggesterData = obj;
        this.mSuggesterType = SuggesterType.HIERARCHICAL_SUGGESTION;
    }

    private void onRelationshipSuggestionPublished(Object obj) {
        this.mSuggesterData = obj;
        this.mSuggesterType = SuggesterType.RELATIONSHIP_SUGGESTION;
    }

    private void onRequestPermissionResult(Object obj) {
        if (((ISearchPicturesView) this.mView).isViewActive()) {
            Object[] objArr = (Object[]) obj;
            int i2 = 0;
            int intValue = ((Integer) objArr[0]).intValue();
            String[] strArr = (String[]) objArr[1];
            int[] iArr = (int[]) objArr[2];
            while (i2 < strArr.length) {
                if (iArr[i2] != -1) {
                    i2++;
                } else {
                    return;
                }
            }
            if (((ISearchPicturesView) this.mView).getHeaderView() != null) {
                ((ISearchPicturesView) this.mView).getHeaderView().onRequestPermissionResult(intValue);
            }
        }
    }

    private void onSearchShortcutCreated() {
        this.mBlackboard.postEvent(EventMessage.obtain(8506, Boolean.FALSE));
        this.mBlackboard.publish("data://user/SearchToolbarSelectedFilters", this.mSearchToolbarDelegate.getData(SearchToolbarDataKey.SELECTED_FILTERS));
        ThreadUtil.postOnUiThreadDelayed(new g(this, 3), 100);
    }

    private void onSuggestedActionPublished(Object obj) {
        this.mSuggesterData = obj;
        this.mSuggesterType = SuggesterType.SUGGESTED_ACTION;
    }

    private void publishLocationHeaderItem() {
        ThreadPool.getInstance().submit(new C0381p(16, this), new p(17, this));
    }

    private void registerScsContentObserver() {
        if (!Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH)) {
            return;
        }
        if (isInMotionPhotoCategory(getLocationKey())) {
            IntelligentSearch.getInstance().registerContentObserver(hashCode(), new d(this, 0));
        } else {
            IntelligentSearch.getInstance().registerContentObserver(hashCode(), new d(this, 1));
        }
    }

    /* access modifiers changed from: private */
    public void reloadSearchFilter() {
        boolean z;
        String locationKey = getLocationKey();
        if (locationKey != null) {
            Bundle args = ArgumentsUtil.getArgs(locationKey);
            boolean z3 = false;
            if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || !SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(args.getString("need_keyword_search"))) {
                z = false;
            } else {
                z = true;
            }
            if (BundleWrapper.getBoolean(args, "RefreshFilterResults", Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH)) && args.getString("SelectedFilter", (String) null) == null) {
                z3 = true;
            }
            if (!z && z3) {
                SimpleThreadPool.getInstance().execute(new a8.d((Object) this, (Object) getApplicationContext(), (Object) args, (Object) locationKey, 22));
            }
        }
    }

    private void resetPreview() {
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW) {
            stopAllPreview(false);
            checkPreviewCandidate();
        }
    }

    /* access modifiers changed from: private */
    public void setEnableOptionsMenu() {
        boolean z;
        if (isSearchToolbarEnabled()) {
            this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(13, Boolean.valueOf(hasOptionsMenu())));
            return;
        }
        ISearchPicturesView iSearchPicturesView = (ISearchPicturesView) this.mView;
        if (isSelectionMode() || hasOptionsMenu()) {
            z = true;
        } else {
            z = false;
        }
        iSearchPicturesView.setOptionsMenu(z);
    }

    private void unregisterScsContentObserver() {
        if (Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH)) {
            IntelligentSearch.getInstance().unregisterContentObserver(hashCode());
        }
    }

    public void adjustForegroundView(WindowInsets windowInsets) {
        Optional.ofNullable(this.mForegroundViewController).ifPresent(new C0382q(windowInsets, 3));
    }

    public boolean checkOptionMenuEnabled() {
        V v = this.mView;
        if (v == null || ((ISearchPicturesView) v).getDataCount() <= 0) {
            return false;
        }
        return true;
    }

    public void clearCacheResult() {
        IntelligentSearch.getInstance().clearCachedResult();
    }

    public void clearToolbarFocus() {
        this.mSearchToolbarDelegate.clearFocus();
    }

    public void collectSearchResult() {
        boolean z;
        if (!this.mIsSearchResultCollected) {
            String locationKey = getLocationKey();
            String argValue = ArgumentsUtil.getArgValue(locationKey, "collect_type");
            if (!TextUtils.isEmpty(argValue)) {
                String argValue2 = ArgumentsUtil.getArgValue(locationKey, "collect_keyword");
                SearchWordCollector instance = SearchWordCollector.getInstance();
                SearchWordCollector.Type valueOf = SearchWordCollector.Type.valueOf(argValue);
                if (((ISearchPicturesView) this.mView).getDataCount() > 0) {
                    z = true;
                } else {
                    z = false;
                }
                instance.collect(argValue2, valueOf, z, this.mResultItemClicked);
                collectExtraSearchResult();
                this.mIsSearchResultCollected = true;
            }
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("global://setting/system/reduce_transparency_and_blur", new h(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("event/personalDataStateChanged", new h(this, 3)).setWorkingOnUI());
    }

    public SearchToolbarDelegate createSearchToolbarDelegate() {
        return SearchToolbarDelegateFactory.build(this);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://event/ClearSearchExpansion", new h(this, 1)).setWorkingCurrent());
    }

    public void doPendingUpdate() {
        Runnable runnable = this.mPendingUpdateRunnable;
        if (runnable != null) {
            runnable.run();
            this.mPendingUpdateRunnable = null;
        }
        this.mCreaturePicturesDelegate.doPendingUpdate();
    }

    public void forceReloadData() {
        clearCacheResult();
        super.forceReloadData();
        Optional.ofNullable(this.mForegroundViewController).ifPresent(new e(24));
    }

    public ICreatureContactDelegate getContactDelegate() {
        return this.mCreaturePicturesDelegate.getContactDelegate();
    }

    public int getFirstItemPositionForBixby() {
        return 1;
    }

    public MediaItem getHeaderItem() {
        return this.mHeaderItem;
    }

    public String getLabelForAccessibility(Context context) {
        if (isSearchToolbarEnabled()) {
            return null;
        }
        return getTagName();
    }

    public String getRecommendationScreenId() {
        ForegroundViewController foregroundViewController = this.mForegroundViewController;
        if (foregroundViewController != null) {
            return foregroundViewController.getScreenId();
        }
        return null;
    }

    public int getSearchToolbarHeight() {
        if (this.mSearchToolbarDelegate.isVisible()) {
            return this.mSearchToolbarDelegate.getDimAreaHeight();
        }
        return 0;
    }

    public Object getSuggesterData() {
        return this.mSuggesterData;
    }

    public SuggesterType getSuggesterType() {
        return this.mSuggesterType;
    }

    public String getTagName() {
        if (LocationKey.isSearchRelationshipPreview(getLocationKey())) {
            return "";
        }
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "title");
        if (!TextUtils.isEmpty(argValue)) {
            argValue = argValue.replaceAll("-\n", "");
        }
        if (argValue == null) {
            return "";
        }
        return argValue;
    }

    public int getTotalCount() {
        return getDataCount();
    }

    public void handleDensityChange() {
        this.mSearchToolbarDelegate.handleDensityChange();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 103) {
            if (i2 == 1066) {
                this.mIsSearchResultCollected = false;
                reopenData((String) eventMessage.obj);
                resetPreview();
                this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(16));
            } else if (i2 == 1078) {
                onFuzzySuggestKeywordPublished((String) eventMessage.obj);
            } else if (i2 == 3001) {
                this.mCreaturePicturesDelegate.handleUpdateCreatureTagEvent(eventMessage);
            } else if (i2 != 8024) {
                if (i2 != 1074) {
                    if (i2 == 1075) {
                        onRelationshipSuggestionPublished(eventMessage.obj);
                    } else if (i2 == 1098) {
                        onSuggestedActionPublished(eventMessage.obj);
                    } else if (i2 != 1099) {
                        if (i2 == 8005) {
                            ((ISearchPicturesView) this.mView).updateEmptyViews();
                        } else if (i2 == 8006) {
                            reloadSearchFilter();
                        } else if (i2 == 8016 || i2 == 8017) {
                            if (((ISearchPicturesView) this.mView).isViewResumed()) {
                                if (eventMessage.what == 8017) {
                                    postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_KEYWORD_ITEM);
                                }
                                this.mSearchToolbarDelegate.handleEvent(eventMessage);
                            }
                            handleFilterChanged();
                        } else if (i2 == 8031) {
                            new RelationshipPickerLauncher().launch((ISearchPicturesView) this.mView, (Object[]) getSuggesterData());
                        } else if (i2 == 8032) {
                            onSuggestedAnalysisTipCheckPublished();
                        } else if (i2 == 8034) {
                            onSearchShortcutCreated();
                        } else if (i2 != 8035) {
                            if (handleForegroundEvent(eventMessage) || this.mSearchToolbarDelegate.handleEvent(eventMessage) || super.handleEvent(eventMessage)) {
                                return true;
                            }
                            return false;
                        } else if (!((ISearchPicturesView) this.mView).isDestroyed() && getLocationKey() != null && getLocationKey().equals(eventMessage.obj)) {
                            this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
                        }
                    }
                }
                onHierarchicalSuggestionPublished(eventMessage.obj);
            } else {
                this.mCreaturePicturesDelegate.launchContactDetail();
            }
        } else if (eventMessage.arg1 != 10 || !((ISearchPicturesView) this.mView).isActive()) {
            if (eventMessage.arg1 == 11) {
                setLocationKey(ArgumentsUtil.appendArgs(getLocationKey(), "force_sync_SSE", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE));
            }
            forceReloadData();
        }
        return true;
    }

    public boolean handleRelationshipRecommend() {
        return false;
    }

    public void handleResolutionChange() {
        ForegroundViewController foregroundViewController = this.mForegroundViewController;
        if (foregroundViewController != null) {
            foregroundViewController.handleResolutionChange();
        }
    }

    public boolean hasClusterResult() {
        return false;
    }

    public boolean hasToolbarFocus() {
        return this.mSearchToolbarDelegate.hasFocus();
    }

    public void hideProgressbarOnDataPrepared() {
        ((ISearchPicturesView) this.mView).setProgressBarVisibility(false);
    }

    public void hideSearchToolbarOnEnterSelectionMode() {
        this.mRestoreSearchToolbarVisibility = this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(8));
    }

    public void initForegroundController(ViewStub viewStub, Bitmap bitmap) {
        this.mForegroundViewController = new ForegroundViewController(this.mView, viewStub, bitmap);
    }

    public void initForegroundViewFloatingToolbarLayout() {
        Optional.ofNullable(this.mForegroundViewController).ifPresent(new e(22));
    }

    public void initMenu() {
        super.initMenu();
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(4));
        setEnableOptionsMenu();
    }

    public void initializeSuggesterData() {
        this.mSuggesterData = null;
    }

    public boolean isCreatureOnMultipleKeyword() {
        return this.mCreaturePicturesDelegate.isOnMultipleKeyword();
    }

    public boolean isInMotionPhotoCategory(String str) {
        if (!LocationKey.isSearchCategoryShotMode(str) || !"Camera mode".equals(ArgumentsUtil.getArgValue(str, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY)) || !"motion_photo".equals(ArgumentsUtil.getArgValue(str, "sub"))) {
            return false;
        }
        return true;
    }

    public boolean isKeywordSearchedOnSupportMultipleKeyword() {
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || !ArgumentsUtil.getArgValue(getLocationKey(), "need_keyword_search", false)) {
            return false;
        }
        return true;
    }

    public boolean isRelationshipEnabled(String str) {
        return this.mCreaturePicturesDelegate.isRelationshipEnabled(str);
    }

    public boolean isSearchToolbarEnabled() {
        return !this.mSearchToolbarDelegate.isEmpty();
    }

    public boolean isSearchToolbarVisible() {
        return this.mSearchToolbarDelegate.isVisible();
    }

    public boolean isVolatile() {
        return FragmentVolatileStatus.isVolatile();
    }

    public void moveToSearch() {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(14, Boolean.TRUE));
    }

    public void notifyDataChanged(MediaData mediaData) {
        updateToolbar(((ISearchPicturesView) this.mView).getToolbar());
    }

    public boolean onBackPressed() {
        if (!LocationKey.supportScopedSearch(getLocationKey()) || !this.mSearchToolbarDelegate.isVisible()) {
            if (!isSelectionMode()) {
                ForegroundViewController foregroundViewController = this.mForegroundViewController;
                if (foregroundViewController != null && foregroundViewController.isSearchForegroundMode() && !this.mForegroundViewController.isVisible()) {
                    this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(28));
                    return true;
                } else if (!isOnGoingFaceClusterMerge()) {
                    this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(29));
                }
            }
            return false;
        }
        this.mBlackboard.postEvent(EventMessage.obtain(8526, Boolean.FALSE));
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(32));
        ((ISearchPicturesView) this.mView).onScopedSearchVisibilityChanged(false);
        ISearchPicturesView iSearchPicturesView = (ISearchPicturesView) this.mView;
        Objects.requireNonNull(iSearchPicturesView);
        ThreadUtil.postOnUiThread(new t8.e(8, iSearchPicturesView));
        return true;
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        if (!PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V3) {
            this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(10, Boolean.valueOf(checkOptionMenuEnabled())));
        }
        hideProgressbarOnDataPrepared();
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(23, Boolean.valueOf(getToolbarDimVisibility())));
        ThreadUtil.postOnUiThreadDelayed(new g(this, 2), 500);
        if (this.mScreenId == null) {
            this.mScreenId = getScreenId();
            AnalyticsLogger.getInstance().postLog(this.mScreenId);
        }
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(24));
    }

    public void onEmptyViewVisibilityChanged(View view) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(17, view));
    }

    public void onEnterSelectionMode(Object obj, Bundle bundle) {
        super.onEnterSelectionMode(obj, bundle);
        if (((ISearchPicturesView) this.mView).getListView().isTouchOngoing()) {
            ((ISearchPicturesView) this.mView).getListView().addOnTouchUpListener(new f(this, 0));
            ((ISearchPicturesView) this.mView).getListView().addOnTouchUpListener(new f(this, 1));
        } else {
            setEnableOptionsMenu();
            hideSearchToolbarOnEnterSelectionMode();
        }
        ((ISearchPicturesView) this.mView).setEnabledHeader(false);
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            this.mBlackboard.postEvent(EventMessage.obtain(8030));
        }
    }

    public void onExitSelectionMode(Object obj, Bundle bundle) {
        super.onExitSelectionMode(obj, bundle);
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(33, Boolean.valueOf(this.mRestoreSearchToolbarVisibility)));
        setEnableOptionsMenu();
        ((ISearchPicturesView) this.mView).setEnabledHeader(true);
    }

    public void onHiddenChanged(boolean z) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(9, Boolean.valueOf(z)));
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        super.onListItemClickInternal(i2, mediaItem);
        this.mResultItemClicked = true;
        collectSearchResult();
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
            this.mBlackboard.post("command://event/FeedbackSearchedItem", new Object[]{Integer.valueOf(i2), Long.valueOf(mediaItem.getFileId())});
        }
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            this.mBlackboard.postEvent(EventMessage.obtain(8030));
        }
    }

    public void onLocationItemClickInternal(String str) {
        super.onLocationItemClickInternal(str);
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(6, Boolean.FALSE));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_scoped_search) {
            ((ISearchPicturesView) this.mView).onScopedSearchVisibilityChanged(true);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onSuggestedAnalysisTipCheckPublished() {
        this.mSuggesterData = this.mView;
        this.mSuggesterType = SuggesterType.ANALYSIS_TIP_CARD;
    }

    public void onViewCreate() {
        super.onViewCreate();
        registerScsContentObserver();
    }

    public void onViewCreated(View view) {
        boolean z;
        super.onViewCreated(view);
        ISearchPicturesView iSearchPicturesView = (ISearchPicturesView) this.mView;
        if (this.mForegroundViewController == null) {
            z = true;
        } else {
            z = false;
        }
        iSearchPicturesView.setProgressBarVisibility(z);
        this.mBlackboard.subscribeOnUi("lifecycle://on_request_permission_result", this.mPermissionListener);
        if (UnsafeCast.toBoolean(ArgumentsUtil.getArgValue(getLocationKey(), "searchToolbar", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE))) {
            this.mSearchToolbarDelegate = createSearchToolbarDelegate();
        }
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(23, Boolean.FALSE));
        setSearchToolbarTitle();
        Optional.ofNullable(this.mForegroundViewController).ifPresent(new v5.e(this, 3));
    }

    public void onViewDestroy() {
        unregisterScsContentObserver();
        this.mSearchToolbarDelegate.onDestroy();
        this.mBlackboard.unsubscribe("lifecycle://on_request_permission_result", this.mPermissionListener);
        clearCacheResult();
        super.onViewDestroy();
        Optional.ofNullable(this.mForegroundViewController).ifPresent(new v5.e(this, 0));
    }

    public void onViewPause() {
        super.onViewPause();
        ForegroundViewController foregroundViewController = this.mForegroundViewController;
        if (foregroundViewController != null) {
            foregroundViewController.onPause();
        }
    }

    public void onViewResume() {
        super.onViewResume();
        doPendingUpdate();
        this.mSearchToolbarDelegate.onResume();
        Optional.ofNullable(this.mForegroundViewController).ifPresent(new e(25));
    }

    public void postAnalyticsLogForHeaderItem() {
        AnalyticsEventId analyticsEventId;
        if (LocationKey.isSearchCategoryPeople(getLocationKey())) {
            if (CreatureData.hasName(this.mHeaderItem)) {
                analyticsEventId = AnalyticsEventId.EVENT_SEARCH_VIEW_EDIT_PERSON;
            } else {
                analyticsEventId = AnalyticsEventId.EVENT_SEARCH_WHO_IS_THIS;
            }
            postAnalyticsLog(analyticsEventId);
        } else if (LocationKey.isSearchCategoryLocation(getLocationKey())) {
            postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_VIEW_LOCATION_SELECT_MAP);
        }
    }

    public void prepareOptionsMenu(Menu menu) {
        super.prepareOptionsMenu(menu);
        this.mCreaturePicturesDelegate.updateCreatureMenu(menu);
    }

    public void publishHeaderItem() {
        if (isKeywordSearchedOnSupportMultipleKeyword() || (LocationKey.isSearchKeyword(getLocationKey()) && hasClusterResult())) {
            ((ISearchPicturesView) this.mView).bindHeader((MediaItem) null);
        } else if (LocationKey.isSearchCategoryCreature(getLocationKey()) || LocationKey.isSearchPeopleAllPictures(getLocationKey())) {
            this.mCreaturePicturesDelegate.publishCreatureHeaderItem();
        } else if (LocationKey.isSearchCategoryLocation(getLocationKey())) {
            publishLocationHeaderItem();
        } else {
            ((ISearchPicturesView) this.mView).bindHeader((MediaItem) null);
        }
    }

    public boolean reInitData(String str) {
        String targetLocationKey = getTargetLocationKey(str);
        if (TextUtils.isEmpty(targetLocationKey)) {
            return false;
        }
        refreshLocationKey(targetLocationKey);
        ((ISearchPicturesView) this.mView).getMediaData(getLocationKey()).reInit(targetLocationKey);
        return true;
    }

    public void refreshLocationKey(String str) {
        this.mBlackboard.publish("location://variable/currentv1", str);
        ((ISearchPicturesView) this.mView).reopenData(str);
        setLocationKey(str);
        setSearchToolbarTitle();
    }

    public void reloadSuggestionData() {
        Optional.ofNullable(this.mForegroundViewController).ifPresent(new e(21));
    }

    public boolean reopenData(String str) {
        String targetLocationKey = getTargetLocationKey(str);
        if (TextUtils.isEmpty(targetLocationKey)) {
            return false;
        }
        refreshLocationKey(targetLocationKey);
        ((ISearchPicturesView) this.mView).getMediaData(getLocationKey()).reopen(targetLocationKey);
        return true;
    }

    public void requestUpdateConfigStatus() {
        if (Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH)) {
            ThreadUtil.postOnBgThread(new i(27));
        }
    }

    public void resetHeaderItem() {
        this.mHeaderItem = null;
    }

    public void setHeaderItem(MediaItem mediaItem) {
        this.mHeaderItem = mediaItem;
    }

    public void setSearchToolbarTitle() {
        if (isSearchToolbarEnabled()) {
            this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(3, getTagName()));
        } else {
            ((ISearchPicturesView) this.mView).getToolbar().setTitle(getToolbarTitle());
        }
    }

    public void setSuggesterForSettingAction(Object obj) {
        Object[] objArr = (Object[]) obj;
        this.mSuggesterData = objArr[0];
        this.mSuggesterType = (SuggesterType) objArr[1];
    }

    public boolean supportViewByCategory() {
        return false;
    }

    public boolean supportViewOnMapView() {
        return false;
    }

    public void updateToolbar(Toolbar toolbar) {
        if (toolbar == null) {
            Log.w(this.TAG, "updateToolbar : toolbar is null!");
        } else if (isSearchToolbarEnabled()) {
            this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(2, getSearchToolbarTitle()));
        } else {
            if (isSelectionMode()) {
                toolbar.setNavigationIcon((Drawable) null);
            } else {
                setNavigationUpButton(toolbar);
            }
            String toolbarTitle = getToolbarTitle();
            toolbar.setTitle((CharSequence) toolbarTitle);
            GalleryAppBarLayout appbarLayout = ((ISearchPicturesView) this.mView).getAppbarLayout();
            if (appbarLayout != null) {
                appbarLayout.setTitle((CharSequence) toolbarTitle);
            }
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new SearchPicturesMenuUpdater(v, this);
    }

    public void handleFilterChanged() {
    }
}
