package com.samsung.android.gallery.app.ui.list.search.category;

import a6.C0419b;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bc.d;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.ICategoryView;
import com.samsung.android.gallery.app.ui.tipcard.CategoryTipCardDelegate;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardDelegate;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.flexboxlist.FlexBoxListView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;
import com.samsung.android.gallery.widget.utils.PickerViewUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.android.sum.core.functional.g;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryFragment<V extends ICategoryView, P extends CategoryPresenter> extends BaseListFragment<V, P> implements ICategoryView {
    protected FlexBoxListView mFlexBoxListView;
    private boolean mIsFromTabMenu;
    private ViewGroup mMainLayout;
    protected NoItemView mNoItemView;
    protected boolean mPickerMode;
    protected CategoryPropertyHelper mPropertyHelper;
    protected LinearLayout mSettingButton;

    private void createCategoryPropertyHelper(String str) {
        if (this.mPropertyHelper == null) {
            this.mPropertyHelper = CategoryPropertyHelper.getInstance(str, true);
        }
    }

    private View createTipCard() {
        TipCardDelegate tipCardDelegate = this.mTipCardDelegate;
        if (tipCardDelegate != null) {
            return (View) Optional.ofNullable(tipCardDelegate.getAndCheckTipCard(getContext())).map(new b(25, this)).orElse((Object) null);
        }
        return null;
    }

    private int getCategoryCount(String str) {
        int argValue = ArgumentsUtil.getArgValue(str, "count", -1);
        if (argValue < 0) {
            return getDataCount();
        }
        updateLocationKey(ArgumentsUtil.removeArg(str, "count"));
        return argValue;
    }

    private int getContentViewTopPadding(boolean z) {
        return PickerUtil.getContentViewTopPadding(this.mBlackboard, z);
    }

    private void handleCommonConfigurationChanged() {
        this.mRecyclerView.clearChildViews();
        updateMainLayoutPaddingHorizontalIfNeeded();
        if (useActivityToolbar() && !isConfigStateChanged(2)) {
            updateContentViewPadding(isClipboardOpened(), false);
        }
        notifyItemRangeChanged();
    }

    private boolean isClipboardOpened() {
        return ((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue();
    }

    private boolean isCreatureCategory() {
        String removeArgs = ArgumentsUtil.removeArgs(getLocationKey());
        if (this.mPropertyHelper.getItemViewType() != 2 || !LocationKey.isSearchCategoryCreatureMatch(removeArgs)) {
            return false;
        }
        return true;
    }

    private boolean isListCategory() {
        return LocationKey.isCategoryList(getLocationKey());
    }

    private boolean isLocationCategory() {
        if (this.mPropertyHelper.getItemViewType() == 3) {
            return true;
        }
        return false;
    }

    private boolean isTagCategory() {
        if (this.mPropertyHelper.getItemViewType() == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$adjustEmptyViewMargin$2(FloatingToolbarLayout floatingToolbarLayout, WindowInsets windowInsets) {
        int i2;
        View view = this.mEmptyView;
        if (view != null) {
            int max = Math.max(getBottomTabHeight(), floatingToolbarLayout.getHeight());
            if (isCreatureCategory()) {
                i2 = WindowUtils.getSystemInsetsBottom(windowInsets);
            } else {
                i2 = 0;
            }
            ViewMarginUtils.setBottomMargin(view, max + i2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$adjustEmptyViewMargin$3(WindowInsets windowInsets, FloatingToolbarLayout floatingToolbarLayout) {
        ViewUtils.post(floatingToolbarLayout, new d((Object) this, (Object) floatingToolbarLayout, (Object) windowInsets, 23));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$adjustPickerViewAreaMargin$1(WindowInsets windowInsets, View view, WindowInsets windowInsets2) {
        PickerViewUtil.adjustViewAreaMargin(windowInsets, ((CategoryPresenter) this.mPresenter).getActivityToolBar(), ((CategoryPresenter) this.mPresenter).getClipboardView(), view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ View lambda$createTipCard$4(TipCardView tipCardView) {
        return tipCardView.createTipCardView(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCreatureCategoryEmptyView$0(View view) {
        ((CategoryPresenter) this.mPresenter).launchHidePeopleAndPetsView();
    }

    private void notifyItemRangeChanged() {
        if (getAdapter() != null && !CategoryItemViewType.isFlexboxItemType(this.mPropertyHelper.getItemViewType())) {
            ((CategoryItemAdapterV2) getAdapter()).onConfigurationChanged();
            getAdapter().notifyDataSetChanged();
        }
    }

    private void setListViewProperty() {
        boolean z;
        boolean useFlexBoxListView = useFlexBoxListView();
        ViewUtils.setVisibleOrGone(this.mFlexBoxListView, useFlexBoxListView);
        GalleryListView galleryListView = this.mRecyclerView;
        if (useFlexBoxListView || isEmptyViewShowing()) {
            z = false;
        } else {
            z = true;
        }
        ViewUtils.setVisibleOrGone(galleryListView, z);
        if (useFlexBoxListView) {
            this.mRecyclerView.setFocusable(false);
        }
    }

    private void setLocationCategoryEmptyView() {
        View view;
        if (this.mNoItemView == null && (view = this.mEmptyView) != null) {
            this.mNoItemView = (NoItemView) view.findViewById(R.id.no_item_view);
        }
        NoItemView noItemView = this.mNoItemView;
        if (noItemView != null) {
            noItemView.setLabel(getString(R.string.empty_set_description_no_locations));
            this.mNoItemView.setDescription(getString(R.string.empty_set_description_location_album_no_item));
        }
    }

    private void updateMainLayoutPaddingHorizontalIfNeeded() {
        if ((!PreferenceFeatures.OneUi8x.COLLECTION_TAB && !this.mIsFromTabMenu) || !isDrawerMode()) {
            if (isListCategory()) {
                ViewMarginUtils.setHorizontalPadding(this.mRecyclerView, 0);
            }
            ViewUtils.setMainLayoutFlexibleSideSpacing(this.mMainLayout);
            ((CategoryPresenter) this.mPresenter).updateSearchToolbarHorizontalPadding(this.mMainLayout.getPaddingStart());
        }
    }

    private boolean useVisualSearchCache() {
        if (LocationKey.isSearchCategoryPeopleHideMatch(getLocationKey()) || LocationKey.isSearchCategoryPeopleAndPetsHideMatch(getLocationKey()) || LocationKey.isSearchCategoryHiddenPeopleMatch(getLocationKey()) || LocationKey.isSearchCategoryHiddenPeopleAndPetsMatch(getLocationKey()) || LocationKey.isSearchCategoryPeopleSelectForRelation(getLocationKey())) {
            return false;
        }
        if (!PreferenceFeatures.OneUi8x.COLLECTION_TAB || !LocationKey.isSearchCategoryMyQuery(getLocationKey())) {
            return true;
        }
        return false;
    }

    public void adjustEmptyViewMargin(WindowInsets windowInsets) {
        Optional.ofNullable(this.mFloatingToolbarLayout).ifPresent(new e(22, this, windowInsets));
    }

    public void adjustPickerViewAreaMargin(View view, WindowInsets windowInsets) {
        Optional.ofNullable(view.getRootWindowInsets()).ifPresent(new g(this, windowInsets, view, 6));
        updateContentViewPadding(((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue(), false);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMainLayout = (ViewGroup) view.findViewById(R.id.main_layout);
        this.mFlexBoxListView = (FlexBoxListView) view.findViewById(R.id.flex_box_list);
        if (useActivityToolbar()) {
            this.mToolbar = GalleryToolbar.findActivityToolbar(view);
        }
        if (this.mPickerMode) {
            this.mRecyclerView.setFadingEdge(GalleryRecyclerView.FadingEdgeDirection.BOTTOM);
        }
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        if (isListCategory()) {
            return new LinearLayoutManager(getContext());
        }
        return new GridLayoutManager(getContext(), getMaxColumnSize());
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new CategoryItemHeaderAdapter(this, getLocationKey(), createTipCard(), galleryListView, this.mPropertyHelper, true);
    }

    public TipCardDelegate createTipCardDelegate() {
        return new CategoryTipCardDelegate(this);
    }

    public RecyclerView getFloatingToolbarTargetRecyclerView() {
        if (useFlexBoxListView()) {
            return this.mFlexBoxListView;
        }
        return this.mRecyclerView;
    }

    public int getLayoutId() {
        if (useActivityToolbar()) {
            return R.layout.fragment_category_picker_layout;
        }
        return R.layout.fragment_category_layout;
    }

    public String getLocationWithExtraArgs() {
        String str;
        String locationKey = getLocationKey();
        if (useVisualSearchCache()) {
            str = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE;
        } else {
            str = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE;
        }
        return ArgumentsUtil.appendArgs(locationKey, "vs_use_cache", str);
    }

    public int[] getPinchColumn() {
        return this.mPropertyHelper.getColumnCount(getContext());
    }

    public CategoryPropertyHelper getPropertyHelper() {
        return this.mPropertyHelper;
    }

    public String getScreenId() {
        CategoryPropertyHelper categoryPropertyHelper = this.mPropertyHelper;
        if (categoryPropertyHelper != null) {
            return categoryPropertyHelper.getScreenId();
        }
        return null;
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        handleCommonConfigurationChanged();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        handleCommonConfigurationChanged();
    }

    public void inflateEmptyView(View view) {
        if (isCreatureCategory()) {
            this.mEmptyView = view.findViewById(R.id.empty_view_by_hidden);
        } else {
            super.inflateEmptyView(view);
        }
    }

    public void initializeAdapter() {
        if (isTagCategory()) {
            setFlexBoxListItemAdapter(new CategoryTagItemAdapterV2(this, getLocationKey(), this.mPropertyHelper, true));
        } else {
            super.initializeAdapter();
        }
    }

    public void initializeEmptyView() {
        GalleryListView listView = getListView();
        if (listView != null) {
            listView.setEmptyView(this.mEmptyView);
        }
    }

    public boolean isEditMode() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "editMode", false);
    }

    public boolean isEnableOptionsMenu() {
        String locationKey = getLocationKey();
        if ((LocationKey.isSearchCategoryPeople(locationKey) || LocationKey.isSearchCategoryPeopleAndPets(locationKey)) && getCategoryCount(locationKey) > 0) {
            return true;
        }
        return false;
    }

    public boolean isEnterTransitionRunning() {
        return isEnterTransitionOnGoing();
    }

    public boolean isVolatileFragment() {
        return ((CategoryPresenter) this.mPresenter).isVolatile();
    }

    public int[] loadPinchColumnResource() {
        return this.mPropertyHelper.getColumnCount(getContext());
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        if (this.mPickerMode) {
            adjustPickerViewAreaMargin(view, windowInsets);
            getListView().updateGoToTopBottomPadding((float) getResources().getDimensionPixelOffset(R.dimen.gototop_adjust_bottom_padding));
        }
        return windowInsets;
    }

    public void onAttach(Context context) {
        boolean z;
        super.onAttach(context);
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH || !PickerUtil.isPickerMode(this.mBlackboard)) {
            z = false;
        } else {
            z = true;
        }
        this.mPickerMode = z;
        this.mIsFromTabMenu = ArgumentsUtil.getArgValue(getLocationKey(), "searchFromTabMenu", false);
    }

    public boolean onBottomSearchToolbarChanged(View view) {
        if (this.mFlexBoxListView != null && isTagCategory()) {
            this.mFlexBoxListView.setPadding(0, 0, 0, view.getHeight());
            this.mFlexBoxListView.setClipToPadding(false);
        }
        return false;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        createCategoryPropertyHelper(getLocationKey());
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (!isDestroyed() && isCreatureCategory()) {
            ((CategoryPresenter) this.mPresenter).postAnalyticsLogCreatureCount();
        }
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((CategoryPresenter) p6).onEmptyViewVisibilityChanged(view);
        }
        if (!isEmptyViewShowing()) {
            return;
        }
        if (isLocationCategory()) {
            setLocationCategoryEmptyView();
        } else if (isCreatureCategory()) {
            setCreatureCategoryEmptyView();
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        if (!showDebugInfo()) {
            return super.onListItemLongClick(listViewHolder, i2, mediaItem);
        }
        DebugSmartCropRectInfo.getInstance().showCropRectForThumbnail(getActivity(), mediaItem, listViewHolder.getBitmap());
        return true;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updateMainLayoutPaddingHorizontalIfNeeded();
        setListViewProperty();
        if (this.mPickerMode) {
            updateContentViewPadding(isClipboardOpened(), false);
        }
    }

    public void operateClipboard(boolean z) {
        updateContentViewPadding(z, true);
    }

    public void setCreatureCategoryEmptyView() {
        View view;
        if (this.mSettingButton == null && (view = this.mEmptyView) != null) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.setting_button_layout);
            this.mSettingButton = linearLayout;
            ViewUtils.setOnClickListener(linearLayout, new C0419b(17, this));
        }
        ((CategoryPresenter) this.mPresenter).updateToolbar(getToolbar());
    }

    public void setFlexBoxListItemAdapter(BaseListViewAdapter baseListViewAdapter) {
        this.mFlexBoxListView.setAdapter(baseListViewAdapter);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getActivity());
        flexboxLayoutManager.setFlexWrap(1);
        flexboxLayoutManager.setFlexDirection(0);
        flexboxLayoutManager.setAlignItems(4);
        this.mFlexBoxListView.setLayoutManager(flexboxLayoutManager);
    }

    public boolean showDebugInfo() {
        return PocFeatures.isEnabled(PocFeatures.DebugSmartCropRectInfo);
    }

    public boolean supportActivityToolbar() {
        return true;
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportExitPredictiveBack() {
        return false;
    }

    public boolean supportFastScroll() {
        return !isTagCategory();
    }

    public boolean supportSelection() {
        return false;
    }

    public void updateContentViewPadding(boolean z, boolean z3) {
        if (this.mMainLayout != null) {
            int contentViewTopPadding = getContentViewTopPadding(z);
            if (z3) {
                PickerViewUtil.setContentViewTopPaddingWithAnimation(this.mMainLayout, contentViewTopPadding);
            } else {
                PickerViewUtil.setContentViewTopPadding(this.mMainLayout, contentViewTopPadding);
            }
            this.mMainLayout.invalidate();
        }
    }

    public void updateLocationKey(String str) {
        setLocationKey(str);
        ((CategoryPresenter) this.mPresenter).setLocationKeyOnly(str);
    }

    public boolean useActivityToolbar() {
        return this.mPickerMode;
    }

    public boolean useFlexBoxListView() {
        if (LocationKey.isSearchCategoryMyTagMatch(getLocationKey()) || LocationKey.isSearchCategoryMyQuery(getLocationKey())) {
            return true;
        }
        return false;
    }

    public CategoryPresenter createPresenter(ICategoryView iCategoryView) {
        return new CategoryPresenter(this.mBlackboard, iCategoryView);
    }

    public void onCategoryExpansionClick(String str) {
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
    }
}
