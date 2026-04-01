package com.samsung.android.gallery.app.ui.list.search;

import A4.J;
import E3.c;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListDelegate;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.CollectionPresenter;
import com.samsung.android.gallery.app.ui.list.search.ICollectionView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryHeaderContainer;
import com.samsung.android.gallery.app.ui.list.search.category.ISearchHeaderView;
import com.samsung.android.gallery.app.ui.list.search.transition.ITransition;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.settings.ui.delegate.NavigateAppCmd;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
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
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.abstraction.ViewPoolInjector;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.SynchronizedViewPool;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollectionFragment<V extends ICollectionView, P extends CollectionPresenter<V>> extends BaseListFragment<V, P> implements ICollectionView {
    private static final boolean SUPPORT_HEADER = PreferenceFeatures.OneUi8x.COLLECTION_TAB;
    /* access modifiers changed from: private */
    public CategoryCardListAdapterV2 mCardListViewAdapter;
    private View mDecorView;
    private ISearchHeaderView mHeaderViewDelegate;
    private ViewGroup mMainLayout;
    private final ITransition mTransition;
    private RecyclerView.RecycledViewPool mViewPool;

    public CollectionFragment() {
        setLocationKey("location://collection");
        if (SUPPORT_HEADER) {
            createHeaderView();
        }
        this.mTransition = ITransition.create(this);
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
    public /* synthetic */ void lambda$adjustEmptyViewMargin$4(FloatingToolbarLayout floatingToolbarLayout, WindowInsets windowInsets) {
        View view = this.mEmptyView;
        if (view != null) {
            ViewMarginUtils.setBottomMargin(view, WindowUtils.getSystemInsetsBottom(windowInsets) + Math.max(getBottomTabHeight(), floatingToolbarLayout.getHeight()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$adjustEmptyViewMargin$5(WindowInsets windowInsets, FloatingToolbarLayout floatingToolbarLayout) {
        ViewUtils.post(floatingToolbarLayout, new e(this, floatingToolbarLayout, windowInsets, 1));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onCreate$0() {
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preloadViewPool$2(long j2) {
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

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startSimpleAutoScroller$7(int i2, Boolean bool) {
        if (!bool.booleanValue()) {
            super.startSimpleAutoScroller(i2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateEmptyViewLayout$3(View view) {
        new NavigateAppCmd(getScreenId()).startSearchCustom(getContext());
    }

    private void loadAndInjectVH(int i2) {
        ListViewHolder create = CategoryCardHolderFactory.create(this.mRecyclerView, i2);
        ViewPoolInjector.inject(this.mViewPool, create, create.getViewType());
    }

    private boolean needToShowOnDescription() {
        return VisualSearchCategory.listOf().stream().anyMatch(new a(2));
    }

    private void notifyItemRangeChangedWithPayload() {
        CategoryCardListAdapterV2 categoryCardListAdapterV2 = this.mCardListViewAdapter;
        if (categoryCardListAdapterV2 != null) {
            categoryCardListAdapterV2.notifyItemRangeChanged(0, categoryCardListAdapterV2.getDataCount(), "onConfigurationChanged");
        }
    }

    private void setNoItemViewDescription(int i2) {
        NoItemView noItemView = (NoItemView) this.mEmptyView.findViewById(R.id.no_item_view);
        if (noItemView != null) {
            noItemView.setDescription(i2);
        }
    }

    private void updateMainLayoutPaddingHorizontal() {
        if (!isDrawerMode()) {
            ViewUtils.setMainLayoutFlexibleSideSpacing(this.mMainLayout);
        }
    }

    public void adjustEmptyViewMargin(WindowInsets windowInsets) {
        Optional.ofNullable(this.mFloatingToolbarLayout).ifPresent(new f(1, this, windowInsets));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMainLayout = (ViewGroup) view.findViewById(R.id.coordinator_layout);
    }

    public BaseListDelegate createBaseListDelegate() {
        return new BaseListDelegate(this) {
            public void onEnterTransitionEnd(boolean z) {
                super.onEnterTransitionEnd(z);
                if (CollectionFragment.this.mCardListViewAdapter != null) {
                    CollectionFragment.this.mCardListViewAdapter.onEnterTransitionEnd();
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
        return R.layout.fragment_collection_layout;
    }

    public String getLocationWithExtraArgs() {
        return ArgumentsUtil.appendArgs(getLocationKey(), "vs_use_cache", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
    }

    public MediaData getMediaData(String str) {
        if (LocationKey.isCollection(ArgumentsUtil.removeArgs(str))) {
            return this.mMediaData;
        }
        return this.mMediaData.getChildMediaData(str);
    }

    public int[] getPinchColumn() {
        return super.getPinchColumn();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_COLLECTION.toString();
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
            ThreadUtil.postOnBgThread(new p(1));
        }
    }

    public void onCategoryExpansionClick(String str) {
        ((CollectionPresenter) this.mPresenter).onCategoryExpansionClick(str);
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, java.util.function.BooleanSupplier] */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSystemUi.setCustomHasNoStatusBar(new Object());
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
        CollectionPresenter collectionPresenter = (CollectionPresenter) this.mPresenter;
        Objects.requireNonNull(collectionPresenter);
        galleryListView.post(new m(3, collectionPresenter));
    }

    public void onDataRangeChangedOnUi(int i2, int i7) {
        CategoryCardListAdapterV2 categoryCardListAdapterV2 = this.mCardListViewAdapter;
        if (categoryCardListAdapterV2 != null) {
            categoryCardListAdapterV2.onDataRangeChangedOnUi(i2, i7);
        } else {
            super.onDataRangeChangedOnUi(i2, i7);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBlackboard.post("lifecycle://onVisualSearchDestroyed", (Object) null);
        CategoryCardListAdapterV2 categoryCardListAdapterV2 = this.mCardListViewAdapter;
        if (categoryCardListAdapterV2 != null) {
            categoryCardListAdapterV2.destroy();
        }
        if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_LANGUAGE_PACK)) {
            ThreadUtil.postOnBgThread(new p(0));
        }
        this.mBlackboard.eraseWildNum("data:///SearchMapCacheBitmap");
        this.mBlackboard.erase("data://user/SearchClusterAllEntry");
        RecyclerView.RecycledViewPool recycledViewPool = this.mViewPool;
        if (recycledViewPool != null) {
            recycledViewPool.clear();
            this.mViewPool = null;
        }
        this.mTransition.destroy();
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

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.mTransition.reset();
        if (getBlackboard() != null) {
            getBlackboard().publish("command:///OnHiddenChanged", Boolean.valueOf(z));
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
        ((CollectionPresenter) this.mPresenter).checkLocationAuth();
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
        SimpleThreadPool.getInstance().submit(new c(this, System.currentTimeMillis(), 2));
    }

    public final void startShrinkAnimation() {
        this.mTransition.startShrinkAnimation();
        this.mBlackboard.post("command://UiEventStartShrinkAnimation", (Object) null);
    }

    public void startSimpleAutoScroller(int i2) {
        this.mTransition.startSimpleAutoScroller(i2, new J((Object) this, i2, 7));
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportFooter() {
        return PickerUtil.isNormalLaunchMode(this.mBlackboard);
    }

    public boolean supportSelection() {
        return false;
    }

    public boolean supportShrinkTransition() {
        return this.mTransition.requireShrinkTransition();
    }

    public boolean supportTabLayout() {
        return true;
    }

    public void updateEmptyViewLayout(AppBarLayout appBarLayout, View view, int i2) {
        super.updateEmptyViewLayout(appBarLayout, view, i2);
        if (ViewUtils.isVisible(this.mEmptyView)) {
            if (needToShowOnDescription()) {
                setNoItemViewDescription(R.string.empty_set_description_no_collections);
            } else {
                setNoItemViewDescription(R.string.empty_set_description_no_collections_off);
            }
            TextView textView = (TextView) this.mEmptyView.findViewById(R.id.edit_collection_button);
            if (textView != null) {
                textView.setContentDescription(textView.getText() + ArcCommonLog.TAG_COMMA + textView.getContext().getString(R.string.speak_button));
                textView.setOnClickListener(new c(2, this));
            }
        }
    }

    public void updateExtraStartPadding(float f) {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.publish("command:///event_command", new Object[]{"UPDATE_EXTRA_PADDING", Float.valueOf(f)});
        }
    }

    public LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext()) {
            public boolean canScrollVertically() {
                if (!CollectionFragment.this.canScrollVertically() || !super.canScrollVertically()) {
                    return false;
                }
                return true;
            }
        };
    }

    public CollectionPresenter createPresenter(ICollectionView iCollectionView) {
        return new CollectionPresenter(this.mBlackboard, iCollectionView);
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
    }
}
