package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.FloatingAutoCompleteDelegate;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteUriBuilder;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.search.constants.ForegroundMode;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersViewContainer;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarDataKey;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;
import com.samsung.android.gallery.widget.search.searchbar.autocomplete.FloatingAutoCompleteView;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BottomSearchPicturesBehavior extends BaseSearchToolbarBehavior {
    private final boolean SUPPORT_INSTANT_SEARCH = PreferenceFeatures.OneUi8x.INSTANT_SEARCH;
    private Runnable mBackToFloatingRecommendation;
    private final SearchFiltersDelegate mFiltersDelegate;
    private FloatingAutoCompleteView mFloatingAutoCompleteView;
    private ForegroundMode mForegroundMode = ForegroundMode.NONE;
    private boolean mIsEmptyViewVisible;
    private boolean mIsForegroundViewVisible;
    private final boolean mIsPickMode;
    private boolean mIsSoftKeyboardVisible;
    private boolean mKeepPictures;
    protected FloatingAutoCompleteDelegate mKeywordSearchAutoComplete;
    private final ListViewBottomPaddingControl mListViewBottomPaddingControl;
    private SearchSelectedFiltersDelegate mSelectedFiltersDelegate;
    private final IBaseListView mView;

    public BottomSearchPicturesBehavior(IBaseListView iBaseListView, boolean z) {
        super(iBaseListView);
        this.mView = iBaseListView;
        this.mIsPickMode = z;
        this.mFiltersDelegate = new SearchFiltersDelegate(iBaseListView, false);
        this.mListViewBottomPaddingControl = new ListViewBottomPaddingControl(iBaseListView, z);
        this.mBackToFloatingRecommendation = new b(0, this);
    }

    private void backToFloatingRecommendation(SearchToolbar searchToolbar) {
        if (this.mForegroundMode == ForegroundMode.SEARCH) {
            this.mFiltersDelegate.resetOnlyThemView();
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1066, initializeLocationKey()));
            setForegroundViewVisibility(searchToolbar, true);
            return;
        }
        Runnable runnable = this.mBackToFloatingRecommendation;
        if (runnable != null) {
            runnable.run();
            this.mBackToFloatingRecommendation = null;
        }
    }

    private String changeLocationKeyUsingQuery(String str, String str2, String str3) {
        String appendArgs = ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(str, "sub", str2), "title", str2), "collect_keyword", str2);
        if (PreferenceFeatures.OneUi8x.INSTANT_SEARCH) {
            appendArgs = ArgumentsUtil.appendArgs(appendArgs, "from_instant_search", str3);
        }
        return ArgumentsUtil.removeArg(appendArgs, "fuzzyKeyword");
    }

    private boolean disableNaviUp() {
        LaunchIntent launchIntent;
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || (launchIntent = (LaunchIntent) this.mView.getBlackboard().read("data://launch_intent")) == null || !"com.android.gallery.action.SHORTCUT_VIEW".equals(launchIntent.getAction()) || launchIntent.getCreatureId() <= 0) {
            return false;
        }
        return true;
    }

    private void dismissAutoCompleteView() {
        FloatingAutoCompleteView floatingAutoCompleteView = this.mFloatingAutoCompleteView;
        if (floatingAutoCompleteView != null) {
            floatingAutoCompleteView.dismiss();
        }
    }

    private void dismissKeywordSearchAutoCompleteView() {
        FloatingAutoCompleteDelegate floatingAutoCompleteDelegate = this.mKeywordSearchAutoComplete;
        if (floatingAutoCompleteDelegate != null) {
            floatingAutoCompleteDelegate.dismissAutoCompleteView();
        }
        onAutoCompleteVisibilityChanged(false);
    }

    private boolean fromInstantSearch() {
        return ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "from_instant_search", false);
    }

    private boolean getDimViewVisibility() {
        if (this.mIsEmptyViewVisible || this.mIsForegroundViewVisible) {
            return false;
        }
        return true;
    }

    private boolean getFilterViewVisibility() {
        FloatingAutoCompleteView floatingAutoCompleteView = this.mFloatingAutoCompleteView;
        if ((floatingAutoCompleteView == null || !floatingAutoCompleteView.isVisible()) && !this.mIsForegroundViewVisible) {
            return true;
        }
        return false;
    }

    private int getPickerModeContentTopPadding(Blackboard blackboard) {
        if (this.mIsPickMode) {
            return PickerUtil.getContentViewTopPadding(blackboard, ((Boolean) blackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue());
        }
        return 0;
    }

    private String getTagName() {
        String argValue = ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "title");
        if (!TextUtils.isEmpty(argValue)) {
            argValue = argValue.replaceAll("-\n", "");
        }
        if (argValue == null) {
            return "";
        }
        return argValue;
    }

    private boolean hasNoInstantSearchData() {
        return false;
    }

    private boolean hideSearchToolbarOnEnterSelectionMode(SearchToolbar searchToolbar) {
        if (this.mIsPickMode) {
            return false;
        }
        searchToolbar.setAutoGoToTopOffsetMove(false);
        boolean isVisible = ViewUtils.isVisible(searchToolbar.getView());
        searchToolbar.clearFocus();
        searchToolbar.setVisibility(8);
        this.mListViewBottomPaddingControl.removePaddingWithAnim();
        return isVisible;
    }

    private SearchFiltersViewContainer inflateFilterLayout(ViewGroup viewGroup) {
        SearchFiltersViewContainer searchFiltersViewContainer = new SearchFiltersViewContainer(this.mView.getContext(), viewGroup, this.mView.getBlackboard(), 0);
        viewGroup.addView(searchFiltersViewContainer.getView(), 0);
        return searchFiltersViewContainer;
    }

    private String initializeLocationKey() {
        boolean z;
        UriBuilder appendArg = new UriBuilder(ArgumentsUtil.removeArgs(this.mView.getLocationKey())).appendArg("term", "key_word").appendArg("collect_type", SearchWordCollector.Type.KEYWORD_INPUT.toString());
        if (!PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD || this.mIsPickMode) {
            z = false;
        } else {
            z = true;
        }
        return appendArg.appendArg("disableTimeline", z).appendArg("searchMode", ForegroundMode.SEARCH.name()).appendArg("ask_screenshot", false).build();
    }

    private boolean isEmptyQuery(SearchToolbar searchToolbar) {
        if (this.mForegroundMode != ForegroundMode.ALBUM) {
            return TextUtils.isEmpty(searchToolbar.getQuery());
        }
        if (!TextUtils.isEmpty(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "SelectedFilter")) || TextUtils.equals(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "term"), "key_word")) {
            return false;
        }
        return true;
    }

    private boolean keepCurrentVisibility() {
        SearchSelectedFiltersDelegate searchSelectedFiltersDelegate;
        if (this.mIsForegroundViewVisible) {
            return hasNoInstantSearchData();
        }
        if (this.mKeepPictures) {
            return true;
        }
        if (this.mForegroundMode != ForegroundMode.SEARCH || (searchSelectedFiltersDelegate = this.mSelectedFiltersDelegate) == null || !searchSelectedFiltersDelegate.hasFilter()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEvent$1() {
        onSearchToolbarVisibilityChanged(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        this.mView.getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$resetListViewScroll$5(GalleryListView galleryListView) {
        galleryListView.stopScroll();
        galleryListView.scrollToPosition(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateBottomPaddingWithFilter$2(View view) {
        this.mListViewBottomPaddingControl.onFirstDrawWithFilter(view);
    }

    private void onSelectedFilterViewInflated(SearchSelectedFiltersView searchSelectedFiltersView, View view) {
        this.mSelectedFiltersDelegate = new SearchSelectedFiltersDelegate(this.mView, searchSelectedFiltersView, view);
    }

    private void onTextChangeForKeywordAutoComplete(String str, boolean z) {
        FloatingAutoCompleteDelegate floatingAutoCompleteDelegate = this.mKeywordSearchAutoComplete;
        if (floatingAutoCompleteDelegate != null) {
            floatingAutoCompleteDelegate.onTextChanged((CharSequence) str, z);
        }
    }

    private String removeArgsForAutoComplete() {
        if (!PocFeatures.ASK_SCREENSHOT || !LocationKey.isAskScreenshot(this.mView.getLocationKey())) {
            return ArgumentsUtil.removeArgs(this.mView.getLocationKey());
        }
        return ArgumentsUtil.appendArgs(ArgumentsUtil.removeArgs(this.mView.getLocationKey()), "ask_screenshot", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
    }

    private void resetListViewScroll() {
        Optional.ofNullable(this.mView.getAppbarLayout()).ifPresent(new d(0));
        Optional.ofNullable(this.mView.getListView()).ifPresent(new d(1));
    }

    private void setDecorViewsHeight(SearchToolbar searchToolbar, int i2) {
        if (this.mView.getView() != null) {
            int height = (((this.mView.getView().getHeight() - i2) - searchToolbar.getGradientAreaHeight()) - getPickerModeContentTopPadding(this.mView.getBlackboard())) - this.mView.getResources().getDimensionPixelOffset(R.dimen.bottom_search_toolbar_floating_auto_complete_margin_bottom);
            FloatingAutoCompleteView floatingAutoCompleteView = this.mFloatingAutoCompleteView;
            if (floatingAutoCompleteView != null) {
                if (this.mView.isLandscape()) {
                    height = 0;
                }
                floatingAutoCompleteView.setHeight(height);
            }
        }
    }

    private void setForegroundViewVisibility(SearchToolbar searchToolbar, boolean z) {
        if (useForegroundView() && !keepCurrentVisibility()) {
            this.mIsForegroundViewVisible = z;
            this.mView.onHandleEvent(EventMessage.obtain(8021, new Object[]{Boolean.valueOf(z), searchToolbar}));
            searchToolbar.setGradientVisibility(getDimViewVisibility());
            this.mFiltersDelegate.setVisibility(getFilterViewVisibility());
            updateImmersiveScrollTarget(searchToolbar, z);
            if (z) {
                resetListViewScroll();
                updateFilterButtonVisibility(false);
            }
        }
    }

    private void setSearchToolbarImmersiveMode(SearchToolbar searchToolbar) {
        ViewGroup findParentViewById = ViewUtils.findParentViewById(searchToolbar, R.id.bottom_search_toolbar_floating_container);
        if (findParentViewById instanceof FloatingBottomLayout) {
            FloatingBottomLayout floatingBottomLayout = (FloatingBottomLayout) findParentViewById;
            if (this.mView.getListView() != null) {
                floatingBottomLayout.setRecyclerView(this.mView.getListView());
            }
            if (this.mView.getContext() != null) {
                floatingBottomLayout.clearBlurInfo(this.mView.getContext());
            }
        }
    }

    private void showSearchToolbarOnExitSelectionMode(final SearchToolbar searchToolbar) {
        searchToolbar.setAutoGoToTopOffsetMove(false);
        ViewGroup view = searchToolbar.getView();
        view.setAlpha(0.0f);
        view.setVisibility(0);
        view.animate().alpha(1.0f).setDuration(300).setListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                searchToolbar.setAutoGoToTopOffsetMove(true);
            }
        }).start();
    }

    private void updateBottomPaddingWithFilter() {
        View view = this.mFiltersDelegate.getView();
        if (view != null) {
            view.post(new e(this, view, 0));
        }
    }

    private void updateFilterButtonVisibility(boolean z) {
        FloatingAutoCompleteView floatingAutoCompleteView = this.mFloatingAutoCompleteView;
        if (floatingAutoCompleteView != null) {
            floatingAutoCompleteView.setFilterButtonVisibility(z);
        }
    }

    private void updateImmersiveScrollTarget(SearchToolbar searchToolbar, boolean z) {
        ViewGroup findParentViewById = ViewUtils.findParentViewById(searchToolbar, R.id.bottom_search_toolbar_floating_container);
        if (findParentViewById instanceof FloatingBottomLayout) {
            FloatingBottomLayout floatingBottomLayout = (FloatingBottomLayout) findParentViewById;
            if (z) {
                if (this.mView.getView() != null) {
                    View findViewById = this.mView.getView().findViewById(R.id.floating_recommendation_view);
                    if (findViewById instanceof NestedScrollView) {
                        floatingBottomLayout.setNestedScrollView((NestedScrollView) findViewById);
                    }
                }
            } else if (this.mView.getListView() != null) {
                floatingBottomLayout.setRecyclerView(this.mView.getListView());
            }
        }
    }

    private void updateToolbarTitle(SearchToolbar searchToolbar, String str) {
        if (!searchToolbar.isEditableFilterViewVisible()) {
            searchToolbar.setQuery(str, false);
        }
    }

    private boolean useForegroundView() {
        if (this.mForegroundMode != ForegroundMode.NONE) {
            return true;
        }
        return false;
    }

    private boolean useLocationKeyTitleCategory(String str) {
        if (LocationKey.isSearchCategoryDocuments(str)) {
            return true;
        }
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            return false;
        }
        if (LocationKey.isSearchCategoryShotMode(str) || LocationKey.isSearchCategoryScreenShot(str)) {
            return true;
        }
        return false;
    }

    public void applyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        boolean z;
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView != null && !iBaseListView.isDialogVisible()) {
            int bottomTabHeight = this.mView.getBottomTabHeight();
            int iMEInsetsBottom = WindowUtils.getIMEInsetsBottom(windowInsets);
            int i2 = 0;
            if (iMEInsetsBottom > 0) {
                z = true;
            } else {
                z = false;
            }
            this.mIsSoftKeyboardVisible = z;
            if (z) {
                i2 = Math.max(0, iMEInsetsBottom - bottomTabHeight);
            }
            int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(windowInsets) + bottomTabHeight + i2;
            ViewMarginUtils.setBottomMargin(searchToolbar.getView(), systemInsetsBottom);
            setDecorViewsHeight(searchToolbar, systemInsetsBottom);
            this.mView.onHandleEvent(EventMessage.obtain(8529, windowInsets));
        }
    }

    public Object getData(SearchToolbar searchToolbar, SearchToolbarDataKey searchToolbarDataKey) {
        SearchSelectedFiltersDelegate searchSelectedFiltersDelegate;
        if (searchToolbarDataKey != SearchToolbarDataKey.SELECTED_FILTERS || (searchSelectedFiltersDelegate = this.mSelectedFiltersDelegate) == null) {
            return null;
        }
        return searchSelectedFiltersDelegate.getFilterResult();
    }

    public boolean handleEvent(SearchToolbar searchToolbar, EventMessage eventMessage) {
        boolean z;
        int i2 = eventMessage.what;
        if (i2 != 1064) {
            if (i2 == 8013) {
                this.mFiltersDelegate.resetOnlyThemView();
            } else if (i2 != 8513) {
                boolean z3 = false;
                if (i2 != 8016) {
                    if (i2 != 8017) {
                        if (i2 == 8522) {
                            this.mFiltersDelegate.updateView();
                            ThreadUtil.postOnUiThread(new b(1, this));
                            return true;
                        } else if (i2 != 8523) {
                            return false;
                        } else {
                            if (this.mSelectedFiltersDelegate != null && !fromInstantSearch()) {
                                Object[] objArr = (Object[]) eventMessage.obj;
                                this.mSelectedFiltersDelegate.addMainFilter((Bitmap) objArr[0], (String) objArr[1]);
                                return true;
                            }
                        }
                    } else if (this.mView.isViewResumed()) {
                        if (this.mForegroundMode == ForegroundMode.ALBUM) {
                            z = true;
                        } else {
                            z = false;
                        }
                        this.mKeepPictures = z;
                        if (!searchToolbar.isEditableFilterViewVisible()) {
                            Blackboard blackboard = this.mView.getBlackboard();
                            String build = AutoCompleteUriBuilder.build(blackboard, (FilterResultsEntity) eventMessage.obj, (String) null, removeArgsForAutoComplete());
                            if (fromInstantSearch()) {
                                updateToolbarTitle(searchToolbar, ((FilterResultsEntity) eventMessage.obj).getName());
                            }
                            blackboard.postEvent(EventMessage.obtain(1066, build));
                            return true;
                        }
                    }
                }
                if (this.mView.isViewResumed()) {
                    searchToolbar.updateFilterView((FilterResultsEntity) eventMessage.obj, ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "sub"));
                    if (this.mForegroundMode == ForegroundMode.ALBUM) {
                        z3 = true;
                    }
                    this.mKeepPictures = z3;
                    return true;
                }
            } else if (this.mView.isViewResumed() || TextUtils.equals(this.mView.getLocationKey(), BlackboardUtils.readLocationKeyCurrent(this.mView.getBlackboard()))) {
                if (!this.mFiltersDelegate.hasView()) {
                    this.mFiltersDelegate.setView(inflateFilterLayout(ViewUtils.findParentViewById(searchToolbar, R.id.bottom_search_toolbar_inner_container)));
                }
                updateFilterButtonVisibility(this.mFiltersDelegate.updateData(eventMessage.obj, getFilterViewVisibility()));
                updateBottomPaddingWithFilter();
                return true;
            }
        }
        return true;
    }

    public boolean handleInternalEvent(SearchToolbar searchToolbar, SearchToolbarEvent searchToolbarEvent) {
        int i2 = searchToolbarEvent.what;
        if (i2 == 3) {
            updateToolbarTitle(searchToolbar, (String) searchToolbarEvent.obj);
            return true;
        } else if (i2 == 8) {
            return hideSearchToolbarOnEnterSelectionMode(searchToolbar);
        } else {
            boolean z = false;
            if (i2 == 13) {
                boolean booleanValue = ((Boolean) searchToolbarEvent.obj).booleanValue();
                IBaseListView iBaseListView = this.mView;
                if (iBaseListView.isSelectionMode() || booleanValue) {
                    z = true;
                }
                iBaseListView.setOptionsMenu(z);
                return true;
            } else if (i2 == 33) {
                if (((Boolean) searchToolbarEvent.obj).booleanValue()) {
                    showSearchToolbarOnExitSelectionMode(searchToolbar);
                } else {
                    searchToolbar.setVisibility(8);
                }
                return true;
            } else if (i2 == 16) {
                if (!fromInstantSearch()) {
                    if (searchToolbar.isEditableFilterViewVisible()) {
                        dismissAutoCompleteView();
                    } else {
                        dismissKeywordSearchAutoCompleteView();
                    }
                    if (useForegroundView()) {
                        setForegroundViewVisibility(searchToolbar, isEmptyQuery(searchToolbar));
                    }
                }
                return true;
            } else if (i2 == 17) {
                this.mIsEmptyViewVisible = ViewUtils.isVisible((View) searchToolbarEvent.obj);
                searchToolbar.setGradientVisibility(getDimViewVisibility());
                return true;
            } else if (i2 == 20) {
                SearchSelectedFiltersDelegate searchSelectedFiltersDelegate = this.mSelectedFiltersDelegate;
                if (searchSelectedFiltersDelegate != null) {
                    searchSelectedFiltersDelegate.replaceMainFilter((String) searchToolbarEvent.obj);
                }
                return true;
            } else if (i2 == 21) {
                SearchSelectedFiltersDelegate searchSelectedFiltersDelegate2 = this.mSelectedFiltersDelegate;
                if (searchSelectedFiltersDelegate2 != null) {
                    searchSelectedFiltersDelegate2.updateFadingEdge();
                }
                return true;
            } else if (i2 == 24) {
                if (fromInstantSearch()) {
                    if (useForegroundView()) {
                        setForegroundViewVisibility(searchToolbar, isEmptyQuery(searchToolbar));
                    }
                    SearchSelectedFiltersDelegate searchSelectedFiltersDelegate3 = this.mSelectedFiltersDelegate;
                    if (searchSelectedFiltersDelegate3 != null) {
                        searchSelectedFiltersDelegate3.notifyDataPrepared();
                    }
                }
                return true;
            } else if (i2 != 25) {
                return false;
            } else {
                this.mForegroundMode = (ForegroundMode) searchToolbarEvent.obj;
                return true;
            }
        }
    }

    public void handleQuery(String str, boolean z) {
        this.mView.getBlackboard().postEvent(EventMessage.obtain(1066, changeLocationKeyUsingQuery(BlackboardUtils.readLocationKeyCurrent(this.mView.getBlackboard()), str, String.valueOf(z))));
    }

    public void onApplyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        applyInsets(searchToolbar, windowInsets);
    }

    public void onAttached(SearchToolbar searchToolbar, SearchToolbarOptions searchToolbarOptions) {
        SearchSelectedFiltersView searchSelectedFiltersView;
        if (searchToolbarOptions.isEnableFiltersView()) {
            if (searchToolbarOptions.supportMultiKeywordSearch()) {
                FloatingAutoCompleteView floatingAutoCompleteView = new FloatingAutoCompleteView(this.mView.getBlackboard(), searchToolbar.getView(), new c(0, this));
                this.mFloatingAutoCompleteView = floatingAutoCompleteView;
                searchSelectedFiltersView = searchToolbar.inflateSelectedFiltersEditableView(floatingAutoCompleteView);
            } else {
                searchSelectedFiltersView = searchToolbar.inflateSelectedFiltersView(this.mView.getBlackboard());
            }
            onSelectedFilterViewInflated(searchSelectedFiltersView, searchToolbar.getSearchTextView());
        }
        if (this.mFloatingAutoCompleteView != null) {
            this.mKeywordSearchAutoComplete = new FloatingAutoCompleteDelegate(this.mView.getBlackboard(), this.mFloatingAutoCompleteView);
        }
        setSearchToolbarImmersiveMode(searchToolbar);
    }

    public void onAutoCompleteVisibilityChanged(boolean z) {
        boolean z3;
        View view = this.mFiltersDelegate.getView();
        if (this.mIsForegroundViewVisible || z) {
            z3 = false;
        } else {
            z3 = true;
        }
        ViewUtils.setVisibleOrGone(view, z3);
    }

    public void onClickCloseButton(SearchToolbar searchToolbar) {
        if (searchToolbar.isEditableFilterViewVisible() || !this.mIsSoftKeyboardVisible) {
            this.mView.postAnalyticsLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_KEYWORDS_LIST.toString(), AnalyticsEventId.EVENT_SEARCH_ACTIONBAR_DELETE_BTN);
            backToFloatingRecommendation(searchToolbar);
        }
    }

    public void onDestroy(SearchToolbar searchToolbar) {
        FloatingAutoCompleteDelegate floatingAutoCompleteDelegate = this.mKeywordSearchAutoComplete;
        if (floatingAutoCompleteDelegate != null) {
            floatingAutoCompleteDelegate.onDestroy();
        }
        if (this.mFiltersDelegate.hasView()) {
            this.mFiltersDelegate.onDestroy();
        }
        SearchSelectedFiltersDelegate searchSelectedFiltersDelegate = this.mSelectedFiltersDelegate;
        if (searchSelectedFiltersDelegate != null) {
            searchSelectedFiltersDelegate.onDestroy();
        }
    }

    public void onFirstDraw(SearchToolbar searchToolbar) {
        ForegroundMode foregroundMode = this.mForegroundMode;
        if (foregroundMode == ForegroundMode.SEARCH) {
            searchToolbar.setFocusable(true);
            searchToolbar.requestFocus();
            ThreadUtil.postOnUiThreadDelayed(new b(2, searchToolbar), 250);
        } else if (foregroundMode == ForegroundMode.ALBUM && this.mSelectedFiltersDelegate != null) {
            if (ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "initShowIme", true)) {
                this.mSelectedFiltersDelegate.showIme();
            }
            setForegroundViewVisibility(searchToolbar, true);
        }
        this.mListViewBottomPaddingControl.onFirstDraw(searchToolbar);
    }

    public void onFocusChanged(SearchToolbar searchToolbar, boolean z) {
        if (!z || this.mIsForegroundViewVisible) {
            dismissKeywordSearchAutoCompleteView();
        } else if (isEmptyQuery(searchToolbar)) {
            dismissKeywordSearchAutoCompleteView();
            setForegroundViewVisibility(searchToolbar, true);
        } else {
            onTextChangeForKeywordAutoComplete(searchToolbar.getQuery().toString(), searchToolbar.isEditableFilterViewVisible());
            setForegroundViewVisibility(searchToolbar, false);
        }
    }

    public void onHiddenChange(SearchToolbar searchToolbar, boolean z) {
        ViewUtils.setVisibleOrGone(searchToolbar, !z);
    }

    public void onInsetsProgress(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        applyInsets(searchToolbar, windowInsets);
    }

    public void onQueryTextChanged(SearchToolbar searchToolbar, String str) {
        if (searchToolbar.hasFocus()) {
            if (TextUtils.isEmpty(str)) {
                dismissKeywordSearchAutoCompleteView();
                setForegroundViewVisibility(searchToolbar, true);
                return;
            }
            onTextChangeForKeywordAutoComplete(str, searchToolbar.isEditableFilterViewVisible());
        }
    }

    public void onResume(SearchToolbar searchToolbar) {
        updateImmersiveScrollTarget(searchToolbar, this.mIsForegroundViewVisible);
    }

    public void onSearchToolbarVisibilityChanged(boolean z) {
        this.mListViewBottomPaddingControl.updateBottomPadding(z);
    }

    public void updateToolbar(SearchToolbar searchToolbar, String str) {
        String str2;
        GalleryToolbar toolbar = this.mView.getToolbar();
        if (toolbar != null) {
            if (LocationKey.isSearchCategoryCreature(this.mView.getLocationKey()) || LocationKey.isSearchRelationshipPreview(this.mView.getLocationKey())) {
                str2 = "";
            } else if (useLocationKeyTitleCategory(this.mView.getLocationKey())) {
                str2 = getTagName();
            } else {
                str2 = AppResources.getString(R.string.search_results);
            }
            this.mView.getPresenter().setNavigationUpButton(toolbar);
            toolbar.setTitle(str2);
            if (disableNaviUp()) {
                toolbar.setNavigationIcon((Drawable) null);
            }
            GalleryAppBarLayout appbarLayout = this.mView.getAppbarLayout();
            if (appbarLayout != null) {
                appbarLayout.setTitle((CharSequence) str2);
            }
        }
    }
}
