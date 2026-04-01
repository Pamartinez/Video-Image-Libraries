package com.samsung.android.gallery.app.ui.list.search.pictures.cluster;

import A4.C0366a;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.ISearchClusterResultView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultPresenter;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.noitem.EmptyViewListener;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import java.util.Optional;
import n4.C0489a;
import o5.C0496a;
import v7.w;
import w5.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchClusterResultFragment<V extends ISearchClusterResultView, P extends SearchClusterResultPresenter<V>> extends SearchPicturesFragment<V, P> implements ISearchClusterResultView {
    private View mContainer;
    private ViewGroup mHeaderContainer;
    private boolean mIsFirstLoading = true;
    /* access modifiers changed from: private */
    public boolean mIsPicturesOnlyMode;
    private View mParent;
    private boolean mPendingLayoutChange;
    private TextView mPicturesCount;
    private SearchClusterResultContainer mSearchClusterResultContainer;
    /* access modifiers changed from: private */
    public boolean mShowSearchPictures;

    private void bindClusterResultLayout(View view) {
        this.mContainer = view.findViewById(R.id.cluster_result_container);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.result_header_container);
        this.mHeaderContainer = viewGroup;
        viewGroup.addView(createHeaderView(), -1, -2);
        this.mRecyclerView = (GalleryListView) view.findViewById(R.id.pictures_recycler_view);
        GridHelper gridHelper = getGridHelper();
        this.mGridSpans = this.mRecyclerView.getGridSpans().init(gridHelper.getSpanInfo(), gridHelper.tag(), false);
        View findViewById = view.findViewById(R.id.pictures_title_header);
        this.mPicturesCount = (TextView) findViewById.findViewById(R.id.search_cluster_divider_count);
        findViewById.setOnClickListener(new C0496a(25, this));
        this.mSearchClusterResultContainer = new SearchClusterResultContainer(view.findViewById(R.id.search_clusters_result_container), this.mPresenter);
    }

    private void bindPicturesOnlyLayout(View view) {
        this.mContainer = view.findViewById(R.id.list_container);
        this.mRecyclerView = (GalleryListView) view.findViewById(R.id.my_recycler_view);
        GridHelper gridHelper = getGridHelper();
        this.mGridSpans = this.mRecyclerView.getGridSpans().init(gridHelper.getSpanInfo(), gridHelper.tag(), FEATURE_SUPPORT_REAL_RATIO);
        if (useAdvancedMouseDragAndDrop()) {
            this.mKeyHandler.rebindMouseHandler();
        }
        this.mEmptyView = view.findViewById(R.id.empty_view);
        initializeEmptyView();
        registerEmptyViewListener();
        addAppbarOffsetChangedListener();
    }

    private void bindViewInternal() {
        clearSidePaddingMap();
        if (this.mIsPicturesOnlyMode) {
            bindPicturesOnlyLayout(this.mParent);
        } else {
            bindClusterResultLayout(this.mParent);
        }
    }

    private void changeViewLayout() {
        this.mPendingLayoutChange = false;
        this.mShowSearchPictures = true;
        this.mIsPicturesOnlyMode = true;
        setContainerVisibility(false);
        destroyListView();
        bindViewInternal();
        loadPinchColumns();
        initListView();
        updateMenu();
    }

    private void destroyListView() {
        this.mLayoutManager = null;
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            baseListViewAdapter.destroy();
            this.mListAdapter = null;
        }
        GalleryListView listView = getListView();
        if (listView != null) {
            listView.setAdapter((RecyclerView.Adapter) null);
            listView.setEmptyViewListener((EmptyViewListener) null);
            listView.setRecycledViewPool((RecyclerView.RecycledViewPool) null);
        }
    }

    private GalleryListView getAutoScrollerTargetView() {
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer == null) {
            return this.mRecyclerView;
        }
        GalleryListView autoScrollerTargetView = searchClusterResultContainer.getAutoScrollerTargetView();
        if (autoScrollerTargetView != null) {
            return autoScrollerTargetView;
        }
        return this.mRecyclerView;
    }

    private void initListView() {
        int i2;
        this.mRecyclerView.setColumnCount(getPinchColumn());
        this.mNormalColumnOrder = this.mGridSpans.normalOrder;
        GalleryListView galleryListView = this.mRecyclerView;
        if (isDexMode()) {
            i2 = getStartPinchDepthDex();
        } else {
            i2 = getStartPinchDepth();
        }
        galleryListView.setStartDepth(i2);
        this.mRecyclerView.setLayoutManager(getLayoutManager());
        this.mRecyclerView.setPinchAnimationManager(getPinchAnimationManager());
    }

    private boolean isPeopleCluster() {
        if (getLocationKey() == null || !getLocationKey().startsWith("location://search/fileList/Category/People")) {
            return false;
        }
        return true;
    }

    private boolean isPetCluster() {
        if (getLocationKey() == null || !getLocationKey().startsWith("location://search/fileList/Category/Pet")) {
            return false;
        }
        return true;
    }

    private boolean isPicturesOnlyModeFrom(String str) {
        return ArgumentsUtil.getArgValue(str, "search_keyword_pictures_only", false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindClusterResultLayout$0(View view) {
        ((SearchClusterResultPresenter) this.mPresenter).onPicturesViewAllClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSimpleAutoScroller$3() {
        Optional.ofNullable(getToolbar()).ifPresent(new C0366a(0));
        Optional.ofNullable(getListView()).ifPresent(new C0366a(1));
        Optional.ofNullable(this.mSearchClusterResultContainer).ifPresent(new w(3));
        this.mAutoScroller = null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onBottomSearchToolbarChanged$4(int i2, RecyclerView recyclerView) {
        recyclerView.setPadding(0, 0, 0, i2);
        recyclerView.setClipToPadding(false);
    }

    private void updateEmptyViewVisible() {
        if (getDataCount() != 0 && isEmptyViewShowing()) {
            ViewUtils.setVisibleOrGone(this.mEmptyView, false);
        }
    }

    private void updateMenu() {
        ((SearchClusterResultPresenter) this.mPresenter).updateMenu();
    }

    public void addLayoutListenerForAutoScroll() {
        if (supportShrinkTransition()) {
            int returnPosition = SharedTransition.getReturnPosition(this.mBlackboard);
            if (getAdapter() == null || getAdapter().getItemCount() <= returnPosition) {
                this.mBlackboard.erase("data://shrink_active");
            } else {
                super.addLayoutListenerForAutoScroll();
            }
        }
    }

    public void bindView(View view) {
        this.mParent = view;
        super.bindView(view);
        bindViewInternal();
    }

    public void clearSearchCluster() {
        getMultipleHeaderContainer().clearAll();
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer != null) {
            searchClusterResultContainer.onDestroy();
        }
    }

    public SimpleAutoScroller createSimpleAutoScroller(int i2) {
        return new SimpleAutoScroller(this.mBlackboard, getAutoScrollerTargetView(), i2).setAppbar(this.mAppBarLayout).setThemeColor(getThemeColor()).withStartAction(new c(this, 0)).withFinishAction(new c(this, 1));
    }

    public int getDefaultSidePadding() {
        if (this.mIsPicturesOnlyMode) {
            return super.getDefaultSidePadding();
        }
        return getResources().getDimensionPixelOffset(R.dimen.search_cluster_result_pictures_default_side_spacing);
    }

    public ViewGroup getHeaderTargetView() {
        if (this.mShowSearchPictures) {
            return super.getHeaderTargetView();
        }
        return this.mHeaderContainer;
    }

    public int getLayoutId() {
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return R.layout.fragment_search_cluster_result_71;
        }
        return R.layout.fragment_search_cluster_result;
    }

    public int getStartPinchDepth() {
        if (this.mIsPicturesOnlyMode) {
            return super.getStartPinchDepth();
        }
        return 0;
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer != null) {
            searchClusterResultContainer.handleOrientationChange(i2);
        }
    }

    public boolean hasClusterResult() {
        return ((SearchClusterResultPresenter) this.mPresenter).hasClusterResult();
    }

    public void initArguments(Bundle bundle) {
        super.initArguments(bundle);
        this.mIsPicturesOnlyMode = isPicturesOnlyModeFrom(getLocationKey());
    }

    public boolean isPicturesOnlyMode() {
        return this.mIsPicturesOnlyMode;
    }

    public void loadClusterData(String str, HashMap hashMap) {
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer != null) {
            searchClusterResultContainer.loadClusterData(str, hashMap);
        }
    }

    public void loadClusterDataIncludeCarousel(HashMap hashMap) {
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer != null) {
            searchClusterResultContainer.loadClusterDataIncludeCarousel(hashMap);
        }
    }

    public int[] loadPinchColumnResource() {
        int i2;
        if (this.mIsPicturesOnlyMode) {
            return super.loadPinchColumnResource();
        }
        Resources resources = getResources();
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            i2 = R.array.search_cluster_column_count_71;
        } else {
            i2 = R.array.search_cluster_column_count;
        }
        return resources.getIntArray(i2);
    }

    public boolean onBottomSearchToolbarChanged(View view) {
        View view2;
        if (this.mIsPicturesOnlyMode || (view2 = this.mContainer) == null) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view2.findViewById(R.id.cluster_result_nested);
        if (viewGroup == null) {
            return true;
        }
        int height = ((ViewGroup) view.getParent()).getHeight();
        View findViewWithTag = viewGroup.findViewWithTag("dummy");
        if (findViewWithTag == null) {
            View view3 = new View(view.getContext());
            view3.setTag("dummy");
            view3.setLayoutParams(new ViewGroup.LayoutParams(-1, height));
            viewGroup.addView(view3);
        } else {
            findViewWithTag.getLayoutParams().height = height;
        }
        Optional.ofNullable((RecyclerView) this.mParent.findViewById(R.id.my_recycler_view)).ifPresent(new C0489a(height, 19));
        return true;
    }

    public void onDataChangedOnUi() {
        if (this.mPendingLayoutChange) {
            changeViewLayout();
        }
        super.onDataChangedOnUi();
        if (this.mIsFirstLoading) {
            this.mIsFirstLoading = false;
        }
    }

    public void onDestroy() {
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer != null) {
            searchClusterResultContainer.onDestroy();
            this.mSearchClusterResultContainer = null;
        }
        super.onDestroy();
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        setContainerVisibility(!isEmptyViewShowing());
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            updateEmptyViewVisible();
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 8014) {
            return super.onHandleEvent(eventMessage);
        }
        getMultipleHeaderContainer().resetViewBy();
        return true;
    }

    public void onResume() {
        super.onResume();
        if (!this.mIsFirstLoading && !this.mIsPicturesOnlyMode) {
            ((SearchClusterResultPresenter) this.mPresenter).reopenData(getLocationKey());
        }
    }

    public void replaceHeaderView(View view) {
        if (this.mShowSearchPictures) {
            super.replaceHeaderView(view);
            return;
        }
        ViewUtils.removeAllViews(this.mHeaderContainer);
        this.mHeaderContainer.addView(view, -1, -2);
    }

    public void savePinchDepth(String str, int i2) {
        if (this.mIsPicturesOnlyMode) {
            super.savePinchDepth(str, i2);
        }
    }

    public void setContainerVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mContainer, z);
    }

    public void setPendingLayoutChange() {
        if (!this.mShowSearchPictures) {
            this.mPendingLayoutChange = true;
            this.mShowSearchPictures = true;
        }
    }

    public void startSimpleAutoScroller(int i2) {
        int clusterItemListViewPosition;
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer == null || (clusterItemListViewPosition = searchClusterResultContainer.getClusterItemListViewPosition(i2)) < 0) {
            super.startSimpleAutoScroller(i2);
        } else {
            createSimpleAutoScroller(clusterItemListViewPosition).start();
        }
    }

    public boolean supportMenu() {
        if (this.mShowSearchPictures || this.mIsPicturesOnlyMode || isPeopleCluster() || isPetCluster()) {
            return true;
        }
        return false;
    }

    public boolean supportSelection() {
        return this.mIsPicturesOnlyMode;
    }

    public boolean supportViewPool() {
        return false;
    }

    public void updateItemCounts() {
        if (!this.mIsPicturesOnlyMode) {
            if (supportContentHeader()) {
                super.updateCountVisible(false);
            }
            ViewUtils.setText(this.mPicturesCount, String.valueOf(getDataCount()));
        } else if (this.mShowSearchPictures) {
            super.updateCountVisible(true);
        } else {
            Optional.ofNullable((SearchClusterResultPresenter) this.mPresenter).ifPresent(new w(4));
        }
    }

    public SearchPicturesAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SearchPicturesAdapter<ISearchPicturesView>(this, getLocationKey(), this.mShowSearchPictures ? createHeaderView() : null, this.mIsPicturesOnlyMode && supportRealRatio()) {
            public int getHintStartSpan(int i2, int i7) {
                if (SearchClusterResultFragment.this.mIsPicturesOnlyMode) {
                    return super.getHintStartSpan(i2, i7);
                }
                return i2 % i7;
            }

            public int getItemCount() {
                if (SearchClusterResultFragment.this.mIsPicturesOnlyMode) {
                    return super.getItemCount();
                }
                return Math.min(SearchClusterResultFragment.this.getColumnCount() * SearchClusterResultFragment.this.getColumnCount(), this.mDataCount);
            }

            public int getItemViewType(int i2) {
                if (SearchClusterResultFragment.this.mIsPicturesOnlyMode) {
                    return super.getItemViewType(i2);
                }
                return 0;
            }

            public int getMediaItemPosition(int i2, int i7) {
                return SearchClusterResultFragment.this.mIsPicturesOnlyMode ? super.getMediaItemPosition(i2, i7) : i2;
            }

            public int getSpanSize(int i2) {
                if (SearchClusterResultFragment.this.mIsPicturesOnlyMode) {
                    return super.getSpanSize(i2);
                }
                return 1;
            }

            public int getViewPosition(int i2) {
                if (SearchClusterResultFragment.this.mIsPicturesOnlyMode) {
                    return super.getViewPosition(i2);
                }
                return i2;
            }

            public boolean isDivider(int i2) {
                return SearchClusterResultFragment.this.mIsPicturesOnlyMode && this.mMultiClusterAdapter.isDivider(i2);
            }

            public boolean supportHeader() {
                return SearchClusterResultFragment.this.mShowSearchPictures;
            }

            public int getMediaItemPosition(int i2) {
                return SearchClusterResultFragment.this.mIsPicturesOnlyMode ? super.getMediaItemPosition(i2) : i2;
            }

            public boolean isDivider(int i2, int i7) {
                return SearchClusterResultFragment.this.mIsPicturesOnlyMode && this.mMultiClusterAdapter.isDivider(i2, i7);
            }

            public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
                super.onBindViewHolder(listViewHolder, i2);
                if (!SearchClusterResultFragment.this.mIsPicturesOnlyMode) {
                    listViewHolder.setThumbnailShape(1, SearchClusterResultFragment.this.getResources().getDimension(R.dimen.search_cluster_results_pictures_item_radius));
                }
            }
        };
    }

    public SearchClusterResultPresenter createPresenter(ISearchClusterResultView iSearchClusterResultView) {
        return new SearchClusterResultPresenter(this.mBlackboard, iSearchClusterResultView);
    }
}
