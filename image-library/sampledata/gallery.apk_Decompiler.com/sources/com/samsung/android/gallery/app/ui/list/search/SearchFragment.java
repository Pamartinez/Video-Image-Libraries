package com.samsung.android.gallery.app.ui.list.search;

import E3.c;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListDelegate;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.app.ui.list.search.SearchPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryHeaderContainer;
import com.samsung.android.gallery.app.ui.list.search.category.ISearchHeaderView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.ViewPoolInjector;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.SynchronizedViewPool;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.PrintWriter;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchFragment<V extends ISearchView, P extends SearchPresenter<V>> extends BaseListFragment<V, P> implements ISearchView {
    private static final boolean SUPPORT_HEADER = PreferenceFeatures.OneUi8x.COLLECTION_TAB;
    /* access modifiers changed from: private */
    public CategoryCardListAdapterV2 mCardListViewAdapter;
    private View mDecorView;
    private ISearchHeaderView mHeaderViewDelegate;
    private ViewGroup mMainLayout;
    private RecyclerView.RecycledViewPool mViewPool;

    public SearchFragment() {
        setLocationKey("location://search");
        if (SUPPORT_HEADER) {
            createHeaderView();
        }
    }

    /* access modifiers changed from: private */
    public boolean canScrollVertically() {
        CategoryCardListAdapterV2 categoryCardListAdapterV2 = this.mCardListViewAdapter;
        if (categoryCardListAdapterV2 == null || categoryCardListAdapterV2.canScrollVertically()) {
            return true;
        }
        return false;
    }

    private void createHeaderView() {
        this.mHeaderViewDelegate = new CategoryHeaderContainer(this);
    }

    private void createViewPool() {
        if (this.mViewPool == null) {
            this.mViewPool = new SynchronizedViewPool();
            preloadViewPool();
        }
    }

    private void initHeaderView() {
        this.mHeaderViewDelegate.initView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preloadViewPool$1(long j2) {
        Trace.beginSection("Search:preloadViewPool");
        loadAndInjectVH(1);
        loadAndInjectVH(3);
        loadAndInjectVH(4);
        loadAndInjectVH(5);
        loadAndInjectVH(6);
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "preloadViewPool + " + Logger.vt(j2));
        Trace.endSection();
    }

    private void loadAndInjectVH(int i2) {
        ListViewHolder create = CategoryCardHolderFactory.create(this.mRecyclerView, i2);
        ViewPoolInjector.inject(this.mViewPool, create, create.getViewType());
    }

    private void notifyItemRangeChangedWithPayload() {
        CategoryCardListAdapterV2 categoryCardListAdapterV2 = this.mCardListViewAdapter;
        if (categoryCardListAdapterV2 != null) {
            categoryCardListAdapterV2.notifyItemRangeChanged(0, categoryCardListAdapterV2.getDataCount(), "onConfigurationChanged");
        }
    }

    private void updateMainLayoutPaddingHorizontal() {
        ViewUtils.setMainLayoutFlexibleSideSpacing(this.mMainLayout);
        ((SearchPresenter) this.mPresenter).updateSearchToolbarHorizontalPadding(this.mMainLayout.getPaddingStart());
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMainLayout = (ViewGroup) view.findViewById(R.id.main_layout);
    }

    public BaseListDelegate createBaseListDelegate() {
        return new BaseListDelegate(this) {
            public void onEnterTransitionEnd(boolean z) {
                super.onEnterTransitionEnd(z);
                if (SearchFragment.this.mCardListViewAdapter != null) {
                    SearchFragment.this.mCardListViewAdapter.onEnterTransitionEnd();
                }
            }
        };
    }

    public GridHelper createGridHelper(String str) {
        return new GridHelper.Builder(str).setResources(R.array.search_column_count).build();
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return null;
    }

    public int getLayoutId() {
        return R.layout.fragment_search_layout;
    }

    public String getLocationWithExtraArgs() {
        return ArgumentsUtil.appendArgs(getLocationKey(), "vs_use_cache", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
    }

    public MediaData getMediaData(String str) {
        if ("location://search".equals(ArgumentsUtil.removeArgs(str))) {
            return this.mMediaData;
        }
        return this.mMediaData.getChildMediaData(str);
    }

    public int[] getPinchColumn() {
        return super.getPinchColumn();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_CATEGORY.toString();
    }

    public ISearchHeaderView getSearchHeaderView() {
        return this.mHeaderViewDelegate;
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        updateMainLayoutPaddingHorizontal();
        if (SUPPORT_HEADER) {
            this.mHeaderViewDelegate.handleOrientationChange(i2);
        }
        notifyItemRangeChangedWithPayload();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        updateMainLayoutPaddingHorizontal();
        if (SUPPORT_HEADER) {
            this.mHeaderViewDelegate.handleResolutionChange(i2);
        }
        notifyItemRangeChangedWithPayload();
    }

    public void initView(View view) {
        super.initView(view);
        this.mRecyclerView.drawBottomColor();
        if (this.mDecorView == null && getActivity() != null) {
            this.mDecorView = getActivity().getWindow().getDecorView();
        }
        if (SUPPORT_HEADER) {
            initHeaderView();
        }
    }

    public void initializeEmptyView() {
        GalleryListView listView = getListView();
        if (listView != null) {
            listView.setEmptyView(this.mEmptyView);
        }
    }

    public boolean isEnterTransitionRunning() {
        return isEnterTransitionOnGoing();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_LANGUAGE_PACK)) {
            ThreadUtil.postOnBgThread(new p(2));
        }
    }

    public void onCategoryExpansionClick(String str) {
        ((SearchPresenter) this.mPresenter).onCategoryExpansionClick(str);
    }

    public void onDataChangedOnUi() {
        View view;
        if (isDestroyed()) {
            Log.se(this.TAG, "Fragment destroyed");
            return;
        }
        if (this.mCardListViewAdapter == null) {
            if (SUPPORT_HEADER) {
                view = this.mHeaderViewDelegate.get();
            } else {
                view = null;
            }
            CategoryCardListAdapterV2 categoryCardListAdapterV2 = new CategoryCardListAdapterV2(this, view);
            this.mCardListViewAdapter = categoryCardListAdapterV2;
            this.mRecyclerView.setAdapter(categoryCardListAdapterV2);
            this.mRecyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
            this.mRecyclerView.setRecycledViewPool(this.mViewPool);
        } else {
            if (SUPPORT_HEADER && isViewActive()) {
                this.mHeaderViewDelegate.dataChanged();
            }
            this.mCardListViewAdapter.notifyDataChanged((Runnable) null);
        }
        invalidateOptionsMenu();
        GalleryListView galleryListView = this.mRecyclerView;
        SearchPresenter searchPresenter = (SearchPresenter) this.mPresenter;
        Objects.requireNonNull(searchPresenter);
        galleryListView.post(new m(4, searchPresenter));
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBlackboard.post("lifecycle://onVisualSearchDestroyed", (Object) null);
        CategoryCardListAdapterV2 categoryCardListAdapterV2 = this.mCardListViewAdapter;
        if (categoryCardListAdapterV2 != null) {
            categoryCardListAdapterV2.destroy();
        }
        if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_LANGUAGE_PACK)) {
            ThreadUtil.postOnBgThread(new p(3));
        }
        this.mBlackboard.eraseWildNum("data:///SearchMapCacheBitmap");
        this.mBlackboard.erase("data://user/SearchClusterAllEntry");
        RecyclerView.RecycledViewPool recycledViewPool = this.mViewPool;
        if (recycledViewPool != null) {
            recycledViewPool.clear();
            this.mViewPool = null;
        }
        if (SUPPORT_HEADER) {
            this.mHeaderViewDelegate.destroy();
        }
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        StringBuilder t = C0212a.t(str, " search feature : [");
        t.append(Features.isEnabled(Features.SUPPORT_SCS_SEARCH));
        t.append("|");
        t.append(Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH));
        t.append("|");
        t.append(Features.isEnabled(Features.SUPPORT_HASHTAG_SEARCH));
        t.append("|");
        t.append(Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION));
        t.append("|");
        t.append(Features.isEnabled(Features.SUPPORT_RELATIONSHIP_SEARCH));
        t.append("|");
        t.append(Features.isEnabled(Features.SUPPORT_DYNAMIC_SEARCH_SUGGESTION));
        t.append("|");
        t.append(Features.isEnabled(Features.SUPPORT_RECOMMEND_SIMILAR_CONTACT));
        t.append("|");
        t.append(Features.isEnabled(Features.SUPPORT_RUBIN_COLLECT_SEARCH));
        t.append("]");
        Logger.dumpLog(printWriter, t.toString());
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        if (isEmptyViewShowing()) {
            this.mBlackboard.postEvent(EventMessage.obtain(8504));
            P p6 = this.mPresenter;
            if (p6 != null) {
                ((SearchPresenter) p6).onEmptyViewVisibilityChanged(view);
            }
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        if (!PocFeatures.isEnabled(PocFeatures.DebugSmartCropRectInfo)) {
            return super.onListItemLongClick(listViewHolder, i2, mediaItem);
        }
        DebugSmartCropRectInfo.getInstance().showCropRectForThumbnail(getActivity(), mediaItem, listViewHolder.getBitmap());
        return true;
    }

    public void onPrePause(boolean z) {
        super.onPrePause(z);
        CategoryCardListAdapterV2 categoryCardListAdapterV2 = this.mCardListViewAdapter;
        if (categoryCardListAdapterV2 != null) {
            categoryCardListAdapterV2.removePendingRunnable();
        }
    }

    public void onResume() {
        super.onResume();
        ((SearchPresenter) this.mPresenter).checkLocationAuth();
        if (isEmptyViewShowing()) {
            this.mBlackboard.postEvent(EventMessage.obtain(8504));
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updateMainLayoutPaddingHorizontal();
        createViewPool();
    }

    public void preloadViewPool() {
        super.preloadViewPool();
        SimpleThreadPool.getInstance().submit(new c(this, System.currentTimeMillis(), 3));
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportSelection() {
        return false;
    }

    public LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext()) {
            public boolean canScrollVertically() {
                if (!SearchFragment.this.canScrollVertically() || !super.canScrollVertically()) {
                    return false;
                }
                return true;
            }
        };
    }

    public SearchPresenter createPresenter(ISearchView iSearchView) {
        return new SearchPresenter(this.mBlackboard, iSearchView);
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
    }
}
