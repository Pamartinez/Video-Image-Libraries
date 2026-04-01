package com.samsung.android.gallery.app.ui.list.search.pictures.cluster;

import E7.c;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.ISearchClusterResultView;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.RelationshipPickerLauncher;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterType;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.module.search.engine.PersonalLinkCore;
import com.samsung.android.gallery.module.search.engine.SearchResultCache;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Supplier;
import v5.a;
import v7.w;
import w4.C0533c;
import w5.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchClusterResultPresenter<V extends ISearchClusterResultView> extends SearchPicturesPresenter<V> {
    private final boolean SUPPORT_CAROUSEL_CLUSTER = PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
    private ClusterResultsEntry mClusterResultEntry;
    private Supplier<Object> mEnableMapAllSupplier = new a(3, this);
    protected int mMediaTypeImageCnt = 0;
    protected int mMediaTypeVideoCnt = 0;

    public SearchClusterResultPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void changeViewBy(boolean z) {
        Optional.ofNullable(getLocationKey()).ifPresent(new c(this, z, 19));
    }

    private void clearSearchCluster() {
        ((ISearchClusterResultView) this.mView).clearSearchCluster();
    }

    private MediaItem getLatestLocationItem() {
        try {
            ArrayList<MediaItem> extraData = getMediaData().getExtraData();
            if (!extraData.isEmpty()) {
                return extraData.get(0);
            }
            return null;
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "getLatestLocationItem", e.getMessage());
            return null;
        }
    }

    private boolean hasOcrEntity() {
        ClusterResultsEntry clusterResultsEntry = this.mClusterResultEntry;
        if (clusterResultsEntry == null) {
            return false;
        }
        return clusterResultsEntry.hasOcrEntity();
    }

    private boolean hasOcrEntityOnly() {
        ClusterResultsEntry clusterResultsEntry = this.mClusterResultEntry;
        if (clusterResultsEntry == null || !clusterResultsEntry.hasOcrEntityOnly()) {
            return false;
        }
        return true;
    }

    private boolean hasRelationshipData() {
        boolean z = !RelationshipKeySet.parseJsonRelationship((String) ((Object[]) getSuggesterData())[0]).isEmpty();
        Log.d(this.TAG, "hasRelationshipData : " + z);
        return z;
    }

    private boolean hasVisibleMenuItems() {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding == null || !menuDataBinding.hasVisibleItems()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeViewBy$0(boolean z, String str) {
        String str2;
        if (z) {
            str2 = ArgumentsUtil.removeArg(str, "disableTimeline");
        } else {
            str2 = ArgumentsUtil.appendArgs(str, "disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
            if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
                str2 = ArgumentsUtil.removeArg(ArgumentsUtil.removeArg(str2, "ExpandedDates"), "AddedCount");
                getBlackboard().post("command://event/ClearSearchExpansion", (Object) null);
            }
        }
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT.toString(), AnalyticsEventId.EVENT_SEARCH_RESULT_GROUP_BY_DATE.toString());
        reInitData(str2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$new$1() {
        return Boolean.FALSE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSubtitle$4(Toolbar toolbar, String str) {
        try {
            toolbar.setSubtitle((CharSequence) str);
            if (((ISearchClusterResultView) this.mView).getAppbarLayout() != null) {
                ((ISearchClusterResultView) this.mView).getAppbarLayout().setSubtitle(str);
            }
        } catch (Exception e) {
            A.a.r(e, new StringBuilder("updateSubtitle failed e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateSubTitle$3(Toolbar toolbar, ThreadPool.JobContext jobContext) {
        return updateSubTitle(toolbar);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateToolbar$2(Toolbar toolbar, ThreadPool.JobContext jobContext) {
        return updateSubTitle(toolbar);
    }

    private void launchClusterAllPictures(String str) {
        String str2;
        boolean startsWith = str.startsWith("location://search/fileList/Category/People");
        if (startsWith) {
            str2 = "location://search/fileList/PeopleAllPictures";
        } else {
            str2 = "location://search/fileList/LocationAllPictures";
        }
        UriBuilder appendArg = new UriBuilder(str2).appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, ArgumentsUtil.getArgValue(str, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, "")).appendArg("sub", ArgumentsUtil.getArgValue(str, "sub")).appendArg("title", ArgumentsUtil.getArgValue(str, "title")).appendArg("search_keyword_pictures_only", true).appendArg("searchToolbar", false);
        if (startsWith) {
            if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
                this.mBlackboard.publish("data:///CreatureHeaderItem", this.mHeaderItem);
            }
            appendArg.appendArg("isNamed", ArgumentsUtil.getArgValue(str, "isNamed"));
        }
        appendArg.appendArg("collect_keyword", ArgumentsUtil.getArgValue(str, "collect_keyword")).appendArg("collect_type", ArgumentsUtil.getArgValue(str, "collect_type"));
        this.mBlackboard.post("command://MoveURL", appendArg.build());
    }

    private void launchSearchResultOnMapView() {
        if (!setInputBlock(this.TAG + "_mapAll")) {
            return;
        }
        if (publishInitialLocation()) {
            this.mBlackboard.post("command://MoveURL", new UriBuilder(getLocationKey()).appendArg("searchResultOnMapView", true).build());
            return;
        }
        Log.w(this.TAG, "launchSearchResultMapView failed : no location item existed");
        Utils.showShortToast(getContext(), (int) R.string.no_location);
    }

    private void loadClusterHeaderData(boolean z, String str, HashMap<ClusterResultType, ArrayList<HashMap<ClusterResultType, ArrayList<String>>>> hashMap, HashMap<ClusterResultType, ArrayList<String>> hashMap2) {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || !z || !handleRelationshipRecommend()) {
            this.mBlackboard.publish("data://user/SearchClusterAllEntry", this.mClusterResultEntry);
            if (this.SUPPORT_CAROUSEL_CLUSTER) {
                ((ISearchClusterResultView) this.mView).loadClusterDataIncludeCarousel(hashMap);
            } else {
                ((ISearchClusterResultView) this.mView).loadClusterData(str, hashMap2);
            }
        }
    }

    private boolean needUpdate(String str) {
        if (str == null) {
            return false;
        }
        if (str.contains("location://search/fileList/pictures_only/Keyword") || str.contains("location://search/fileList/KeywordClusterPictures") || ArgumentsUtil.getArgValue(str, "search_keyword_pictures_only", false)) {
            return true;
        }
        return false;
    }

    private boolean publishInitialLocation() {
        MediaItem latestLocationItem = getLatestLocationItem();
        if (latestLocationItem == null) {
            return false;
        }
        ((ISearchClusterResultView) this.mView).getAdapter().publishInitialLocation(this.mBlackboard, latestLocationItem);
        return true;
    }

    private void saveMediaTypeCount(ClusterResultsEntity clusterResultsEntity) {
        if ("image".equals(clusterResultsEntity.getName())) {
            this.mMediaTypeImageCnt = clusterResultsEntity.getCount();
        } else {
            this.mMediaTypeVideoCnt = clusterResultsEntity.getCount();
        }
    }

    private void setClusterResultEntryInfo(Object obj) {
        this.mClusterResultEntry = new ClusterResultsEntry.Extractor().extract(obj).build();
        PersonalDataCore.getInstance(this.mBlackboard).setPdcToken(this.mClusterResultEntry.getPdcToken());
    }

    private void setMediaTypeCountToBlackboard() {
        this.mBlackboard.publish("data://user/SearchClusterMediaTypeCount", new int[]{this.mMediaTypeImageCnt, this.mMediaTypeVideoCnt});
    }

    private void setSubtitle(Toolbar toolbar, String str) {
        ThreadUtil.postOnUiThread(new C0533c(this, toolbar, str, 1));
    }

    private boolean skipCluster(ClusterResultType clusterResultType) {
        if (LocationKey.isSearchCategoryLocation(getLocationKey()) && clusterResultType == ClusterResultType.LOCATIONS) {
            return true;
        }
        if (!LocationKey.isSearchCategoryPeople(getLocationKey()) || clusterResultType != ClusterResultType.PEOPLE) {
            return false;
        }
        return true;
    }

    private boolean skipUpdateOcrData() {
        if (getLocationKey() != null && getLocationKey().startsWith("location://search/fileList/KeywordClusterPictures") && hasOcrEntity()) {
            return true;
        }
        return false;
    }

    private boolean supportMultipleRelationshipPick() {
        if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || getSuggesterType() != SuggesterType.RELATIONSHIP_SUGGESTION) {
            return false;
        }
        return true;
    }

    public void addHeaderUpdateSupplier(HashMap<String, Supplier<Object>> hashMap) {
        hashMap.putIfAbsent("enable_map_all", this.mEnableMapAllSupplier);
    }

    public boolean checkOptionMenuEnabled() {
        if (!super.checkOptionMenuEnabled() || !((ISearchClusterResultView) this.mView).supportMenu() || !hasVisibleMenuItems()) {
            return false;
        }
        return true;
    }

    public void clearCacheResult() {
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || !((ISearchClusterResultView) this.mView).isPicturesOnlyMode()) {
            SearchResultCache.getInstance().clear();
            super.clearCacheResult();
        }
    }

    public void clearMenu() {
        super.clearMenu();
        getActivity().removeMenuProvider(this);
    }

    public void clusterKeyDataLoaded() {
        ClusterResultsEntry clusterResultsEntry = this.mClusterResultEntry;
        if (clusterResultsEntry == null || clusterResultsEntry.isEmpty()) {
            Log.e(this.TAG, "mClusterResultEntry is null or empty");
            return;
        }
        HashMap hashMap = new HashMap();
        ArrayList<ClusterResultsEntity> allItems = this.mClusterResultEntry.getAllItems();
        HashMap hashMap2 = new HashMap();
        Iterator<ClusterResultsEntity> it = allItems.iterator();
        String str = "";
        while (true) {
            boolean z = false;
            while (it.hasNext()) {
                ClusterResultsEntity next = it.next();
                ClusterResultType of2 = ClusterResultType.of(next.getCategory());
                if (of2 == ClusterResultType.MEDIA_TYPE) {
                    saveMediaTypeCount(next);
                } else if (PreferenceFeatures.OneUi7x.SUPPORT_TOP_RESULT_SEARCH && ClusterResultType.TOP_RESULT.equals(of2)) {
                    ArrayList arrayList = (ArrayList) hashMap.getOrDefault(of2, new ArrayList());
                    arrayList.addAll(next.getRawLabels());
                    hashMap.put(of2, arrayList);
                    if (this.SUPPORT_CAROUSEL_CLUSTER) {
                        ArrayList arrayList2 = (ArrayList) hashMap2.getOrDefault(of2, new ArrayList());
                        arrayList2.add(hashMap);
                        hashMap2.put(of2, arrayList2);
                        Log.s(this.TAG, "topResult clusterKeyList: " + arrayList2);
                    }
                } else if (!skipCluster(of2)) {
                    ArrayList arrayList3 = (ArrayList) hashMap.getOrDefault(of2, new ArrayList());
                    if (!ClusterResultType.PDCS.equals(of2)) {
                        arrayList3.add(next.getName());
                        hashMap.put(of2, arrayList3);
                    }
                    if (this.SUPPORT_CAROUSEL_CLUSTER) {
                        ArrayList arrayList4 = (ArrayList) hashMap2.getOrDefault(of2, new ArrayList());
                        arrayList4.add(hashMap);
                        hashMap2.put(ClusterResultType.CAROUSELS, arrayList4);
                    }
                    if (ClusterResultType.OCRS.equals(of2)) {
                        str = next.getId();
                        this.mBlackboard.publish("data://user/SearchClusterOCRCount", Integer.valueOf(next.getCount()));
                        if (hasOcrEntityOnly() && next.getCount() == getDataCount()) {
                            z = true;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            loadClusterHeaderData(z, str, hashMap2, hashMap);
            setMediaTypeCountToBlackboard();
            updateToolbar(getToolbar());
            return;
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        if (((ISearchClusterResultView) this.mView).supportMenu()) {
            return super.createMenuDataBinding();
        }
        return null;
    }

    public int getFirstItemPositionForBixby() {
        return PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 ? 1 : 0;
    }

    public void getMediaTypeCountFromBlackboard() {
        if (LocationKey.isSearchKeywordClusterPictures(getLocationKey())) {
            this.mMediaTypeImageCnt = ((Integer) this.mBlackboard.read("data://user/SearchClusterOCRCount", 0)).intValue();
            return;
        }
        Object read = this.mBlackboard.read("data://user/SearchClusterMediaTypeCount");
        if (read != null) {
            int[] iArr = (int[]) read;
            this.mMediaTypeImageCnt = iArr[0];
            this.mMediaTypeVideoCnt = iArr[1];
        }
    }

    public String getOcrKeyword() {
        ClusterResultsEntity ocrEntity;
        ClusterResultsEntry clusterResultsEntry = this.mClusterResultEntry;
        if (clusterResultsEntry == null || (ocrEntity = clusterResultsEntry.getOcrEntity()) == null) {
            return null;
        }
        return ocrEntity.getName();
    }

    public String getViewerLocationKey() {
        return ArgumentsUtil.appendArgs(super.getViewerLocationKey(), "ocr_keyword", getOcrKeyword());
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 8007) {
            changeViewBy(((Boolean) eventMessage.obj).booleanValue());
            return true;
        } else if (i2 != 8019) {
            switch (i2) {
                case 8002:
                    String str = (String) ((Object[]) eventMessage.obj)[2];
                    if (!str.equals(ArgumentsUtil.removeArgs(getLocationKey()))) {
                        return true;
                    }
                    if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                        onClusterResultsPublished(eventMessage.obj);
                        return true;
                    } else if (!LocationKey.isSearchKeyword(str)) {
                        return true;
                    } else {
                        onClusterResultsPublished(eventMessage.obj);
                        return true;
                    }
                case 8003:
                    if (!LocationKey.isSearchCategoryPeople(getLocationKey())) {
                        return true;
                    }
                    clearSearchCluster();
                    return true;
                case 8004:
                    handleFilterChanged();
                    return true;
                default:
                    return super.handleEvent(eventMessage);
            }
        } else {
            launchSearchResultOnMapView();
            return true;
        }
    }

    public void handleFilterChanged() {
        if (((ISearchClusterResultView) this.mView).isViewActive()) {
            ((ISearchClusterResultView) this.mView).setPendingLayoutChange();
            Optional.ofNullable(this.mCreaturePicturesDelegate).ifPresent(new w(5));
        }
    }

    public boolean handleRelationshipRecommend() {
        V v;
        if (!supportMultipleRelationshipPick() || (v = this.mView) == null || !((ISearchClusterResultView) v).isViewResumed()) {
            return false;
        }
        if (!hasRelationshipData()) {
            return true;
        }
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            return false;
        }
        new RelationshipPickerLauncher().launch((ISearchPicturesView) this.mView, (Object[]) getSuggesterData());
        return true;
    }

    public boolean hasClusterResult() {
        ClusterResultsEntry clusterResultsEntry = this.mClusterResultEntry;
        if (clusterResultsEntry == null || clusterResultsEntry.isEmpty() || this.mClusterResultEntry.isOnlyMediaTypeEntity()) {
            return false;
        }
        return true;
    }

    public void onClusterResultsPublished(Object obj) {
        setClusterResultEntryInfo(obj);
        if (!this.mClusterResultEntry.isEmpty()) {
            if (this.mClusterResultEntry.isOnlyMediaTypeEntity()) {
                ((ISearchClusterResultView) this.mView).setPendingLayoutChange();
            } else if (!skipUpdateOcrData() && !PreferenceFeatures.isEnabled(PreferenceFeatures.SearchClusterResultOnHeader)) {
                clusterKeyDataLoaded();
            }
        }
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        V v = this.mView;
        ((ISearchClusterResultView) v).setContainerVisibility(!((ISearchClusterResultView) v).isEmptyViewShowing());
    }

    public void onDestroy() {
        super.onDestroy();
        if (Features.isEnabled(Features.SUPPORT_PDC_SEARCH)) {
            PersonalDataCore.getInstance(getBlackboard()).release();
        }
        if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK)) {
            PersonalLinkCore.getInstance().release();
        }
    }

    public void onPicturesViewAllClicked() {
        String locationKey = ((ISearchClusterResultView) this.mView).getLocationKey();
        if (locationKey.startsWith("location://search/fileList/Category/People") || locationKey.startsWith("location://search/fileList/Category/Location")) {
            launchClusterAllPictures(locationKey);
        } else {
            getBlackboard().post("command://MoveURL", new UriBuilder(locationKey).appendArg("search_keyword_pictures_only", true).appendArg("searchToolbar", false).appendArg("title", getContext().getString(R.string.search_results)).build());
        }
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        getMediaTypeCountFromBlackboard();
    }

    public void onViewDestroy() {
        if (!((ISearchClusterResultView) this.mView).isPicturesOnlyMode()) {
            this.mBlackboard.erase("data://user/SearchClusterMediaTypeCount");
        }
        super.onViewDestroy();
    }

    public boolean reInitData(String str) {
        boolean reInitData = super.reInitData(str);
        if (!ArgumentsUtil.getArgValue(str, "from_instant_search", false)) {
            ((ISearchClusterResultView) this.mView).setProgressBarVisibility(reInitData);
        }
        return reInitData;
    }

    public boolean reopenData(String str) {
        boolean reopenData = super.reopenData(str);
        if (!ArgumentsUtil.getArgValue(str, "from_instant_search", false)) {
            ((ISearchClusterResultView) this.mView).setProgressBarVisibility(reopenData);
        }
        return reopenData;
    }

    public boolean supportViewByCategory() {
        ClusterResultsEntry clusterResultsEntry;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.ViewClusterResultMenuOption) || (clusterResultsEntry = this.mClusterResultEntry) == null || !clusterResultsEntry.hasCarouselData()) {
            return false;
        }
        return true;
    }

    public boolean supportViewOnMapView() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.SearchResultOnMapView) || getLatestLocationItem() == null) {
            return false;
        }
        return true;
    }

    public void updateMenu() {
        clearMenu();
        if (((ISearchClusterResultView) this.mView).supportMenu()) {
            initMenu();
        }
    }

    public void updateSubTitle() {
        if (needUpdate(getLocationKey())) {
            ThreadPool.getInstance().submit(new d(this, ((ISearchClusterResultView) this.mView).getToolbar(), 1));
        }
    }

    public void updateToolbar(Toolbar toolbar) {
        super.updateToolbar(toolbar);
        if (!needUpdate(getLocationKey())) {
            return;
        }
        if (((ISearchClusterResultView) this.mView).isEmptyViewShowing()) {
            toolbar.setSubtitle((CharSequence) "");
            return;
        }
        CharSequence subtitle = toolbar.getSubtitle();
        if (TextUtils.isEmpty(subtitle)) {
            int i2 = this.mMediaTypeImageCnt;
            if (i2 > 0 || this.mMediaTypeVideoCnt > 0) {
                subtitle = makeSubtitle(i2, this.mMediaTypeVideoCnt);
            } else {
                ThreadPool.getInstance().submit(new d(this, toolbar, 0));
                subtitle = " ";
            }
        }
        toolbar.setSubtitle(subtitle);
    }

    private Object updateSubTitle(Toolbar toolbar) {
        String cachedResult = IntelligentSearch.getInstance().getCachedResult(ArgumentsUtil.getArgValue(getLocationKey(), "sub", "") + ArgumentsUtil.getArgValue(getLocationKey(), "SelectedFilter", (String) null));
        if (TextUtils.isEmpty(cachedResult)) {
            Log.w(this.TAG, "updateSubTitle : cached ids are null!");
            return null;
        }
        int[] searchClusterPicturesCount = new MpHelper(new QueryParams(GroupType.BURST).setFileIds(cachedResult)).getSearchClusterPicturesCount();
        if (searchClusterPicturesCount != null) {
            String makeSubtitle = makeSubtitle(searchClusterPicturesCount[0], searchClusterPicturesCount[1]);
            if (TextUtils.equals(toolbar.getSubtitle(), makeSubtitle)) {
                return null;
            }
            setSubtitle(toolbar, makeSubtitle);
        }
        return null;
    }
}
