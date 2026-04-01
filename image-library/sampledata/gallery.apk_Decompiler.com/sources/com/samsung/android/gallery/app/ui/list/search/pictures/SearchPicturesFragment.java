package com.samsung.android.gallery.app.ui.list.search.pictures;

import A4.C0385u;
import C3.i;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowInsets;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.appbar.AppBarLayout;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DummyDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ICreatureContactDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.ISearchSuggestionView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.module.search.languagepack.LanguagePackReceiver;
import com.samsung.android.gallery.module.search.languagepack.NeuralTranslator;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.effects.ListProtectionGradient;
import com.samsung.android.gallery.widget.effects.ListProtectionGradientImpl;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.progressbar.SearchProgressCircle;
import com.samsung.android.gallery.widget.search.constants.ForegroundMode;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import n4.C0489a;
import n4.C0491c;
import q6.e;
import v5.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesFragment<V extends ISearchPicturesView, P extends SearchPicturesPresenter> extends PicturesFragment<V, P> implements ISearchPicturesView, ISearchSuggestionView {
    public static final boolean SUPPORT_LOCATION = Features.isEnabled(Features.SUPPORT_LOCATION);
    private final AtomicBoolean mCountOnlyHeaderEnabled = new AtomicBoolean(false);
    private SearchHeaderView.HeaderType mHeaderType;
    private final AtomicBoolean mHistoryInserted = new AtomicBoolean(false);
    private float mInitialY;
    private LanguagePackReceiver mLanguagePackReceiver;
    protected ViewGroup mMainLayout;
    protected SearchPicturesMultipleHeaderContainer mMultipleHeaderContainer;
    private final AtomicBoolean mNeedToResetHeader = new AtomicBoolean(false);
    private SearchPicturesNoItemDelegate mNoItemDelegate;
    private final AtomicBoolean mOnceCreatureHeaderInit = new AtomicBoolean(false);
    private final AtomicBoolean mPendingFuzzyUpdate = new AtomicBoolean(false);
    protected SearchProgressCircle mProgressCircle;

    private MediaItem getRepresentativeItem() {
        if (((SearchPicturesPresenter) this.mPresenter).getHeaderItem() != null) {
            return ((SearchPicturesPresenter) this.mPresenter).getHeaderItem();
        }
        return this.mMediaData.read(0);
    }

    private void handleHeaderClicked() {
        SearchHeaderView headerView;
        if (setInputBlock(this.TAG + "_onHeaderItemClicked") && (headerView = getHeaderView()) != null && headerView.onHeaderItemClicked()) {
            ((SearchPicturesPresenter) this.mPresenter).postAnalyticsLogForHeaderItem();
        }
    }

    private boolean hasNoSelectedFilter() {
        if (ArgumentsUtil.getArgValue(getLocationKey(), "SelectedFilter") == null) {
            return true;
        }
        return false;
    }

    private void insertHistory() {
        MediaItem representativeItem;
        if (!isSkipHistory() && (representativeItem = getRepresentativeItem()) != null && MiscSettingPreference.SearchSaveRecent.isEnabled() && SearchHistory.getInstance().insertHistory(getLocationKey(), representativeItem.getFileId(), !PickerUtil.isNormalLaunchMode(this.mBlackboard))) {
            this.mBlackboard.postEvent(EventMessage.obtain(8515));
        }
    }

    private boolean isForegroundViewAlbumMode() {
        if (ForegroundMode.valueOf(ArgumentsUtil.getArgValue(getLocationKey(), "searchMode", ForegroundMode.NONE.name())) == ForegroundMode.ALBUM) {
            return true;
        }
        return false;
    }

    private boolean isFromViewer() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "from_viewer", false);
    }

    /* access modifiers changed from: private */
    public boolean isSearchProcessing() {
        return ViewUtils.isVisible(this.mProgressCircle);
    }

    private boolean isSkipHistory() {
        if (hasNoData() || this.mHistoryInserted.getAndSet(true) || ArgumentsUtil.getArgValue(getLocationKey(), "search_skip_save_history", false)) {
            return true;
        }
        return false;
    }

    private boolean isVirtualLocationAlbum(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/Location")) {
            return false;
        }
        return SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(ArgumentsUtil.getArgValue(str, "fromVirtualAlbum"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindHeader$6(MediaItem mediaItem) {
        SearchHeaderView headerView = getHeaderView();
        if (headerView != null) {
            Optional.ofNullable((SearchPicturesPresenter) this.mPresenter).ifPresent(new C0491c(18, headerView, mediaItem));
            bindHeaderData(headerView);
            if (needResetViewBy()) {
                getMultipleHeaderContainer().resetViewBy();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createProtectionGradient$0() {
        return !this.mCountOnlyHeaderEnabled.get();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateBackgroundIfNeeded$4(GalleryListView galleryListView) {
        galleryListView.scrollBy(0, getResources().getDimensionPixelSize(R.dimen.search_creature_slideshow_name_card_margin_top) - getResources().getDimensionPixelSize(R.dimen.search_creature_slideshow_name_card_landscape_top_offset));
    }

    private void moveToViewer(View view, int i2, MediaItem mediaItem, Bitmap bitmap) {
        if (mediaItem != null) {
            if (bitmap == null) {
                bitmap = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.XLARGE_NC_KIND);
            }
            ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.USER_EVENT);
            SharedTransition.addSharedElement(this.mBlackboard, view, SharedTransition.getTransitionName(mediaItem), new TransitionInfo(mediaItem, bitmap, i2));
            ((SearchPicturesPresenter) this.mPresenter).onListItemClick((ListViewHolder) null, i2, mediaItem);
        }
    }

    private boolean needDefaultFrameworkBg() {
        if (isEmptyViewShowing()) {
            return true;
        }
        if (getHeaderType() != SearchHeaderView.HeaderType.CREATURE || !PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            return false;
        }
        return true;
    }

    private boolean needResetViewBy() {
        String locationKey = getLocationKey();
        if (locationKey == null || !PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND || !PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD || !LocationKey.isSearchKeyword(locationKey) || !Boolean.parseBoolean(ArgumentsUtil.getArgValue(locationKey, "disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onListVewTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mInitialY = motionEvent.getY();
        }
        if (!((SearchPicturesPresenter) this.mPresenter).hasToolbarFocus() || Math.abs(this.mInitialY - motionEvent.getY()) <= 16.0f) {
            return false;
        }
        ((SearchPicturesPresenter) this.mPresenter).clearToolbarFocus();
        return false;
    }

    private void resetEmptyView() {
        if (isEmptyViewShowing()) {
            this.mNoItemDelegate.reset();
            ViewUtils.setVisibleOrGone(this.mEmptyView, false);
        }
    }

    private void resetHeader() {
        if (this.mNeedToResetHeader.getAndSet(false) && this.mHeaderType != getHeaderType()) {
            getMultipleHeaderContainer().recycle();
            replaceHeaderView(createHeaderView());
        }
    }

    private boolean supportAnalysisTipHeader() {
        return PreferenceFeatures.OneUi8x.SUPPORT_SEARCH_ANALYSIS_TIP_HEADER;
    }

    private void updateBackgroundIfNeeded() {
        int i2;
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            if (!PickerUtil.isPickerMode(this.mBlackboard)) {
                Resources resources = getResources();
                if (needDefaultFrameworkBg()) {
                    i2 = R.color.default_fw_background;
                } else {
                    i2 = R.color.default_background;
                }
                int color = resources.getColor(i2, (Resources.Theme) null);
                Optional.ofNullable(this.mMainLayout).ifPresent(new C0489a(color, 13));
                Optional.ofNullable(this.mAppBarLayout).ifPresent(new C0489a(color, 14));
            }
            if (getHeaderType() == SearchHeaderView.HeaderType.CREATURE) {
                GalleryListView listView = getListView();
                if (listView != null && !this.mOnceCreatureHeaderInit.getAndSet(true) && isLandscape()) {
                    listView.post(new e(20, this, listView));
                }
                updateProtectionGradient();
            }
        }
    }

    private void updateItemCountsInner() {
        getMultipleHeaderContainer().updateItemCount(getDataCount());
        getMultipleHeaderContainer().updateBackground(needDefaultFrameworkBg());
    }

    private void updateMainLayoutPaddingHorizontalIfNeeded() {
        ViewGroup viewGroup;
        P p6 = this.mPresenter;
        if (p6 != null && ((SearchPicturesPresenter) p6).isSearchToolbarEnabled() && (viewGroup = this.mMainLayout) != null) {
            ViewUtils.setMainLayoutFlexibleSideSpacing(viewGroup.findViewById(R.id.search_pictures_contents_area));
            ViewUtils.setMainLayoutFlexibleSideSpacing(this.mMainLayout.findViewById(R.id.bottom_search_toolbar_outer_container));
            ViewUtils.setMainLayoutFlexibleSideSpacing(this.mMainLayout.findViewById(R.id.floating_recommendation_main_layout), this.mMainLayout.findViewById(R.id.floating_recommendation_background_blur_layout));
        }
    }

    public boolean allowEmptyView() {
        return getMultipleHeaderContainer().allowEmptyView();
    }

    public void bindHeader(MediaItem mediaItem) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Log.s(this.TAG, "Can not bind the header item : activity is null");
            return;
        }
        ((SearchPicturesPresenter) this.mPresenter).setHeaderItem(mediaItem);
        activity.runOnUiThread(new e(21, this, mediaItem));
        onHeaderLoaded();
    }

    public void bindHeaderData(SearchHeaderView searchHeaderView) {
        if (searchHeaderView.supportSlideShow()) {
            searchHeaderView.bindData(this.mMediaData);
        }
    }

    public void bindView(View view) {
        GalleryAppBarLayout galleryAppBarLayout;
        super.bindView(view);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.main_layout);
        this.mMainLayout = viewGroup;
        viewGroup.setSaveEnabled(false);
        this.mProgressCircle = (SearchProgressCircle) view.findViewById(R.id.progressCircle);
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.NewSearchBar) && (galleryAppBarLayout = this.mAppBarLayout) != null) {
            galleryAppBarLayout.seslSetAllowStateToHide(true, true);
            this.mAppBarLayout.setCustomHeightProportion(false, 0);
            this.mAppBarLayout.setTitleEnabled(false);
        }
        this.mBlackboard.publish("data://user/SearchProcessing", new a(0, this));
        this.mRecyclerView.setOnTouchListener(new rb.a(1, this));
    }

    public void createDragAndDropDelegate() {
        if (PickerUtil.isPickerMode(this.mBlackboard)) {
            this.mDragAndDropDelegate = new DummyDragAndDropDelegate();
        } else {
            super.createDragAndDropDelegate();
        }
    }

    public View createHeaderView() {
        if (getActivity() == null || isDestroyed()) {
            Log.se(this.TAG, "Couldn't create the header view -> Context is null | destroyed");
            return null;
        }
        if (supportAnalysisTipHeader()) {
            getMultipleHeaderContainer().createAnalysisTipHeader(this);
        }
        if (supportContentHeader()) {
            ((SearchPicturesPresenter) this.mPresenter).resetHeaderItem();
            getMultipleHeaderContainer().createContentHeader(this, getHeaderType());
        }
        getMultipleHeaderContainer().createFilterHeader(this.mBlackboard);
        boolean z = false;
        if (this.mPendingFuzzyUpdate.getAndSet(false)) {
            getMultipleHeaderContainer().updateFuzzyResult(ArgumentsUtil.getArgValue(getLocationKey(), "fuzzyKeyword"));
        }
        if (!isSelectionMode() && !isEmptyViewShowing()) {
            z = true;
        }
        setEnabledHeader(z);
        return getMultipleHeaderContainer().getView();
    }

    public ListProtectionGradient createProtectionGradient() {
        if (LocationKey.isSearchPicturesCreature(getLocationKey())) {
            return new ListProtectionGradientImpl(new C0385u(26, this));
        }
        return super.createProtectionGradient();
    }

    public ICreatureContactDelegate getContactDelegate() {
        return ((SearchPicturesPresenter) this.mPresenter).getContactDelegate();
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new PicturesViewDummyAdapter(getListView(), getColumnCount()) {
            public PicturesViewHolderFactory createViewHolderFactory(Context context) {
                return new SearchPicturesViewHolderFactory(context);
            }
        };
    }

    public View getEmptyView() {
        return this.mEmptyView;
    }

    public ViewGroup getHeaderTargetView() {
        return getListView();
    }

    public SearchHeaderView.HeaderType getHeaderType() {
        if (!PocFeatures.ASK_SCREENSHOT || !LocationKey.isAskScreenshot(getLocationKey())) {
            if (((SearchPicturesPresenter) this.mPresenter).isKeywordSearchedOnSupportMultipleKeyword() || ((SearchPicturesPresenter) this.mPresenter).isCreatureOnMultipleKeyword()) {
                this.mHeaderType = SearchHeaderView.HeaderType.COUNT_ONLY;
            }
            if (LocationKey.isSearchCategoryCreature(getLocationKey())) {
                this.mHeaderType = SearchHeaderView.HeaderType.CREATURE;
            } else if (SUPPORT_LOCATION && LocationKey.isSearchCategoryLocation(getLocationKey())) {
                this.mHeaderType = SearchHeaderView.HeaderType.LOCATION;
            } else if (LocationKey.isSearchKeyword(getLocationKey()) && hasClusterResult()) {
                this.mHeaderType = SearchHeaderView.HeaderType.CLUSTER_RESULT;
            } else if (LocationKey.isSearchCategoryScreenShot(getLocationKey())) {
                this.mHeaderType = SearchHeaderView.HeaderType.SCREENSHOT;
            } else {
                this.mHeaderType = SearchHeaderView.HeaderType.COUNT_ONLY;
            }
            return this.mHeaderType;
        }
        SearchHeaderView.HeaderType headerType = SearchHeaderView.HeaderType.ASK_SCREENSHOT;
        this.mHeaderType = headerType;
        return headerType;
    }

    public SearchHeaderView getHeaderView() {
        return getMultipleHeaderContainer().getContentHeader();
    }

    public int getLayoutId() {
        return R.layout.fragment_search_pictures_layout;
    }

    public String getLocationWithExtraArgs() {
        if (isPicker()) {
            return ArgumentsUtil.removeArg(getLocationKey(), "disableTimeline");
        }
        return super.getLocationWithExtraArgs();
    }

    public SearchPicturesMultipleHeaderContainer getMultipleHeaderContainer() {
        if (this.mMultipleHeaderContainer == null) {
            this.mMultipleHeaderContainer = new SearchPicturesMultipleHeaderContainer(getContext());
        }
        return this.mMultipleHeaderContainer;
    }

    public NoItemView getNoItemView() {
        return this.mNoItemDelegate.getNoItemView();
    }

    public int getPreferenceDefault() {
        return getGridHelper().getDefaultDepth();
    }

    public PreferenceName getPreferenceName() {
        return getGridHelper().getPreferenceName();
    }

    public String getScreenId() {
        String str;
        P p6 = this.mPresenter;
        if (p6 != null) {
            str = ((SearchPicturesPresenter) p6).getRecommendationScreenId();
        } else {
            str = null;
        }
        if (str != null) {
            return str;
        }
        boolean isVirtualLocationAlbum = isVirtualLocationAlbum(getLocationKey());
        if (isSelectionMode()) {
            if (isVirtualLocationAlbum) {
                return AnalyticsScreenId.SCREEN_SMART_ALBUM_LOCATION_DETAIL_EDIT.toString();
            }
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT_EDIT.toString();
        } else if (LocationKey.isSearchCategoryLocation(getLocationKey())) {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_LOCATION_CATEGORY_PICTURES.toString();
        } else {
            if (LocationKey.isSearchCategoryPeople(getLocationKey())) {
                return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_TAG.toString();
            }
            if (isVirtualLocationAlbum) {
                return AnalyticsScreenId.SCREEN_SMART_ALBUM_LOCATION_DETAIL.toString();
            }
            if (ViewUtils.isVisible(this.mEmptyView)) {
                return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_NO_RESULT.toString();
            }
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT.toString();
        }
    }

    public int getStartPinchDepth() {
        return getPinchDepthRecoveredIfAbsent();
    }

    public Object getSuggesterData() {
        return ((SearchPicturesPresenter) this.mPresenter).getSuggesterData();
    }

    public SuggesterType getSuggesterType() {
        return ((SearchPicturesPresenter) this.mPresenter).getSuggesterType();
    }

    public int getUsableHeight(AppBarLayout appBarLayout) {
        int i2;
        if (getView() != null) {
            i2 = WindowUtils.getIMEInsetsBottom(getView().getRootWindowInsets());
        } else {
            i2 = 0;
        }
        return super.getUsableHeight(appBarLayout) - i2;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        replaceHeaderView(createHeaderView());
        updateHeader();
    }

    public void handleNoResultWithoutSelectedFilter() {
        if (LocationKey.supportScopedSearch(getLocationKey())) {
            this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        }
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        getMultipleHeaderContainer().handleOrientationChange(i2);
        this.mBlackboard.postEvent(EventMessage.obtain(8507));
    }

    public boolean handleRelationshipRecommend() {
        return ((SearchPicturesPresenter) this.mPresenter).handleRelationshipRecommend();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        getMultipleHeaderContainer().handleResolutionChanged();
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((SearchPicturesPresenter) p6).handleResolutionChange();
        }
    }

    public boolean hasClusterResult() {
        return false;
    }

    public boolean hasNoData() {
        if (getDataCount() == 0) {
            return true;
        }
        return false;
    }

    public void initForegroundController(View view) {
        Bitmap bitmap;
        P p6;
        if (getArguments() != null && (bitmap = (Bitmap) this.mBlackboard.pop("data:///CapturedBitmap", null)) != null && (p6 = this.mPresenter) != null) {
            ((SearchPicturesPresenter) p6).initForegroundController((ViewStub) view.findViewById(R.id.foreground_view_stub), bitmap);
        }
    }

    public void initView(View view) {
        super.initView(view);
        initForegroundController(view);
    }

    public void initializeEmptyView() {
        getListView().setEmptyView(this.mEmptyView);
    }

    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
        this.mBlackboard.postEvent(EventMessage.obtain(8512));
    }

    public boolean isRelationshipEnabled(String str) {
        return ((SearchPicturesPresenter) this.mPresenter).isRelationshipEnabled(str);
    }

    public boolean isSupportRealRatio() {
        return FEATURE_SUPPORT_REAL_RATIO;
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent, int i2) {
        if (getMultipleHeaderContainer().isVirtualCtrlKeyPressedAllowablePoint(motionEvent)) {
            return true;
        }
        return super.isVirtualCtrlKeyPressedAllowablePoint(motionEvent, i2);
    }

    public boolean isVolatileFragment() {
        return ((SearchPicturesPresenter) this.mPresenter).isVolatile();
    }

    public int[] loadPinchColumnResource() {
        return getGridHelper().getGridArray(getContext());
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((SearchPicturesPresenter) p6).adjustForegroundView(view.getRootWindowInsets());
        }
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(view, windowInsets);
        updateMainLayoutPaddingHorizontalIfNeeded();
        return onApplyWindowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_LANGUAGE_PACK)) {
            LanguagePackReceiver languagePackReceiver = new LanguagePackReceiver(new t8.e(7, this));
            this.mLanguagePackReceiver = languagePackReceiver;
            AndroidCompat.registerReceiver(context, languagePackReceiver, languagePackReceiver.getIntentFilter());
        }
    }

    public boolean onBackPressed() {
        if (((SearchPicturesPresenter) this.mPresenter).onBackPressed() || super.onBackPressed() || getMultipleHeaderContainer().onBackPressed()) {
            return true;
        }
        return false;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setBackgroundAndSystemUiBarColor(hasNoData());
        if (isEmptyViewShowing()) {
            this.mNoItemDelegate.onConfigurationChanged();
        }
    }

    public void onDataChangedOnUi() {
        boolean z;
        super.onDataChangedOnUi();
        if (!isDestroyed()) {
            boolean hasNoData = hasNoData();
            if (hasNoData && hasNoSelectedFilter()) {
                handleNoResultWithoutSelectedFilter();
            }
            updateHeader();
            if (isViewActive()) {
                setBackgroundAndSystemUiBarColor(hasNoData);
                if (hasNoData || isSelectionMode()) {
                    z = false;
                } else {
                    z = true;
                }
                setEnabledHeader(z);
                if (!hasNoData) {
                    this.mNoItemDelegate.hideSuggesterView();
                }
            }
            if (!isEmptyViewShowing()) {
                getMultipleHeaderContainer().dataChanged();
            }
        }
    }

    public void onDataInserted(int i2, int i7) {
        super.onDataInserted(i2, i7);
        updateItemCounts();
    }

    public void onDestroy() {
        getMultipleHeaderContainer().recycle();
        ((SearchPicturesPresenter) this.mPresenter).collectSearchResult();
        this.mBlackboard.pop("data://user/ScsDisabledReason");
        this.mBlackboard.erase("data://user/SearchProcessing");
        if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_LANGUAGE_PACK)) {
            AndroidCompat.unregisterReceiver(getContext(), this.mLanguagePackReceiver);
            this.mLanguagePackReceiver = null;
            NeuralTranslator.getInstance().clearValues();
        }
        super.onDestroy();
    }

    public void onDestroyView() {
        getMultipleHeaderContainer().onDestroyView();
        super.onDestroyView();
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
    }

    public void onEmptyViewLayoutChecked() {
        updateEmptyViews();
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((SearchPicturesPresenter) p6).onEmptyViewVisibilityChanged(view);
        }
        updateEmptyViews();
        updateProtectionGradient();
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
            super.onGridChanged(i2, i7);
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 8514) {
            this.mHistoryInserted.set(false);
        } else if (i2 == 8030) {
            insertHistory();
        } else if (i2 == 8518) {
            if (this.mListAdapter == null) {
                this.mPendingFuzzyUpdate.set(true);
            } else {
                Object[] objArr = (Object[]) eventMessage.obj;
                getMultipleHeaderContainer().updateFuzzyResult((String) objArr[0]);
                if (((Boolean) objArr[1]).booleanValue()) {
                    getListView().checkIfEmpty();
                }
            }
        }
        return super.onHandleEvent(eventMessage);
    }

    public void onHeaderItemClicked(View view, int i2, MediaItem mediaItem, Bitmap bitmap) {
        if (!isSelectionMode()) {
            if (i2 < 0) {
                handleHeaderClicked();
            } else {
                moveToViewer(view, i2, mediaItem, bitmap);
            }
        }
    }

    public void onHeaderLoaded() {
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            insertHistory();
        }
    }

    public void onPause() {
        super.onPause();
        getMultipleHeaderContainer().onPause();
    }

    public void onPrePause(boolean z) {
        super.onPrePause(z);
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((SearchPicturesPresenter) p6).reloadSuggestionData();
        }
    }

    public void onReopenData() {
        resetEmptyView();
        this.mNeedToResetHeader.set(true);
    }

    public void onResume() {
        if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_LANGUAGE_PACK)) {
            ThreadUtil.postOnBgThread(new i(26));
        }
        super.onResume();
        getMultipleHeaderContainer().onResume();
        if (isEmptyViewShowing()) {
            requestFocusToSearchToolbarCloseButton();
            if (getSuggesterType() == SuggesterType.SCS_SETTING_ACTION) {
                ((SearchPicturesPresenter) this.mPresenter).requestUpdateConfigStatus();
            }
        }
    }

    public void onStop() {
        super.onStop();
        restoreBackgroundAndSystemUiBarColor();
    }

    public void onViewCreated(View view, Bundle bundle) {
        setOptionsMenu(false);
        super.onViewCreated(view, bundle);
        this.mNoItemDelegate = new SearchPicturesNoItemDelegate(this, this);
        ((SearchPicturesPresenter) this.mPresenter).initForegroundViewFloatingToolbarLayout();
    }

    public void refreshLocationKey(String str) {
        ((SearchPicturesPresenter) this.mPresenter).refreshLocationKey(str);
    }

    public void reopenData(String str) {
        if (!TextUtils.equals(ArgumentsUtil.getArgValue(getLocationKey(), "sub"), ArgumentsUtil.getArgValue(str, "sub"))) {
            this.mHistoryInserted.set(false);
        }
        setLocationKey(str);
        if (ArgumentsUtil.getArgValue(str, "sub") == null && getAdapter() != null) {
            getAdapter().hideAllItems();
        }
    }

    public void replaceHeaderView(View view) {
        BaseListViewAdapter baseListViewAdapter;
        if (view != null && (baseListViewAdapter = this.mListAdapter) != null) {
            baseListViewAdapter.setHeaderView(view);
        }
    }

    public void requestFocusToSearchToolbarCloseButton() {
        this.mBlackboard.postEvent(EventMessage.obtain(8505));
    }

    public void restoreBackgroundAndSystemUiBarColor() {
        setBackgroundAndSystemUiBarColor(false);
    }

    public void setCustomAnimations(FragmentTransaction fragmentTransaction, IBaseFragment iBaseFragment) {
        if (isForegroundViewAlbumMode() || isFromViewer()) {
            super.setCustomAnimations(fragmentTransaction, iBaseFragment);
        } else {
            fragmentTransaction.setCustomAnimations(R.animator.fade_in_150, R.animator.fade_out_150, R.animator.fade_in_150, R.animator.fade_out_150);
        }
    }

    public void setEnabledHeader(boolean z) {
        getMultipleHeaderContainer().setEnabled(z, z);
    }

    public void setLocationKey(String str) {
        super.setLocationKey(str);
    }

    public void setProgressBarVisibility(boolean z) {
        SearchProgressCircle searchProgressCircle;
        if (getLocationKey().startsWith("location://search/fileList/Keyword") && (searchProgressCircle = this.mProgressCircle) != null) {
            searchProgressCircle.updateVisibility(z);
        }
    }

    public void setSuggesterForSettingAction(Object obj) {
        ((SearchPicturesPresenter) this.mPresenter).setSuggesterForSettingAction(obj);
    }

    public boolean supportContentHeader() {
        return true;
    }

    public boolean supportExitPredictiveBack() {
        return false;
    }

    public boolean supportExpand() {
        return false;
    }

    public boolean supportFooter() {
        return true;
    }

    public boolean supportTimeline() {
        return true;
    }

    public String toString() {
        return this.HASH_TAG;
    }

    public void updateCountVisible(boolean z) {
        getMultipleHeaderContainer().updateCountHeader(z);
        if (z) {
            updateItemCountsInner();
        }
    }

    public void updateEmptyViews() {
        if (isEmptyViewShowing()) {
            this.mNoItemDelegate.setNoItemCustomView(this.mEmptyView);
            requestFocusToSearchToolbarCloseButton();
        } else if (((SearchPicturesPresenter) this.mPresenter).getSuggesterData() != null) {
            ((SearchPicturesPresenter) this.mPresenter).initializeSuggesterData();
        }
    }

    public void updateHeader() {
        boolean z;
        resetHeader();
        AtomicBoolean atomicBoolean = this.mCountOnlyHeaderEnabled;
        if (((SearchPicturesPresenter) this.mPresenter).isKeywordSearchedOnSupportMultipleKeyword() || ((SearchPicturesPresenter) this.mPresenter).isCreatureOnMultipleKeyword() || (LocationKey.isSearchKeyword(getLocationKey()) && !hasClusterResult())) {
            z = true;
        } else {
            z = false;
        }
        atomicBoolean.set(z);
        getMultipleHeaderContainer().showCountHeaderOnly(this.mCountOnlyHeaderEnabled.get());
        updateBackgroundIfNeeded();
        ((SearchPicturesPresenter) this.mPresenter).publishHeaderItem();
        updateItemCounts();
    }

    public void updateItemCounts() {
        updateItemCountsInner();
    }

    public PicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        View createHeaderView = createHeaderView();
        if (createHeaderView != null) {
            return new SearchPicturesAdapter(this, getLocationKey(), createHeaderView, isSupportRealRatio());
        }
        return super.createListViewAdapter(galleryListView);
    }

    public SearchPicturesPresenter createPresenter(ISearchPicturesView iSearchPicturesView) {
        return new SearchPicturesPresenter(this.mBlackboard, iSearchPicturesView);
    }

    public SearchPicturesAdapter getAdapter() {
        return (SearchPicturesAdapter) this.mListAdapter;
    }

    public void postAnalyticsLog() {
    }

    public void onScopedSearchVisibilityChanged(boolean z) {
    }

    public void setBackgroundAndSystemUiBarColor(boolean z) {
    }
}
