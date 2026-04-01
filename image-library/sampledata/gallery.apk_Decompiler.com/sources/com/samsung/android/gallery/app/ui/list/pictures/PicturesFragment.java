package com.samsung.android.gallery.app.ui.list.pictures;

import A.a;
import A4.C0368c;
import N2.j;
import X3.c;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c4.C0438h;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.dragdrop.PicturesDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.DataCollectorOnScroll;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.appbar.CustomCover;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.SynchronizedViewPool;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.o3dp.lib3dphotography.i;
import com.samsung.scsp.media.api.d;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import e5.C0452b;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesFragment<V extends IPicturesView, P extends PicturesPresenter> extends BaseListFragment<V, P> implements IPicturesView {
    protected static final boolean SUPPORT_DC_HAPTIC = Features.isEnabled(Features.SUPPORT_DC_HAPTIC);
    private Runnable mCreatePostDataPoolRunnable;
    protected CustomCover mCustomCoverHolder;
    private DataCollectorOnScroll mDataCollector;
    public boolean mDrawerStateChanged = false;
    private View mScrollPopUp;
    protected ViewStub mScrollPopUpViewStub;
    private final HashMap<Integer, Integer> mSidePaddingMap = new HashMap<>();
    protected boolean mUesDataCollector = false;
    protected RecyclerView.RecycledViewPool mViewPool;

    private boolean IsScrollStateIdle() {
        if (getListView().getScrollState() == 0) {
            return true;
        }
        return false;
    }

    private boolean canChangeViewDepth(int i2) {
        if ((isOnAdvancedMouseFocused() || isSelectionMode()) && supportYearTimeline() && !isSelectableViewDepth(i2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: createDataViewPoolAsync */
    public void lambda$preloadViewPool$3(int i2) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "createDataViewPoolAsync #" + i2);
        this.mCreatePostDataPoolRunnable = null;
        if (!isDestroyed() && getListView() != null) {
            createViewPool(i2, 0);
        }
    }

    private void createViewHolder(GalleryListView galleryListView, PicturesViewDummyAdapter picturesViewDummyAdapter, int i2) {
        if (galleryListView != null) {
            try {
                RecyclerView.ViewHolder createViewHolder = picturesViewDummyAdapter.createViewHolder(galleryListView, i2);
                try {
                    RecyclerView.RecycledViewPool recycledViewPool = this.mViewPool;
                    if (recycledViewPool != null) {
                        recycledViewPool.putRecycledView(createViewHolder);
                    }
                } catch (Exception e) {
                    StringCompat stringCompat = this.TAG;
                    Log.e(stringCompat, "createViewPool#createViewHolder: view pool add failed. e=" + e.getMessage());
                    ThreadUtil.postOnBgThread(new i(8, this, createViewHolder));
                }
            } catch (NullPointerException e7) {
                e7.printStackTrace();
            }
        }
    }

    private void destroyCustomCover() {
        CustomCover customCover = this.mCustomCoverHolder;
        if (customCover != null) {
            customCover.destroy();
            this.mCustomCoverHolder = null;
        }
    }

    /* access modifiers changed from: private */
    public int getExtensionFromGrid() {
        return this.mGridSpans.spanMonthMax();
    }

    /* access modifiers changed from: private */
    public int getExtensionToGrid() {
        return this.mGridSpans.spanMonthMin();
    }

    private int getMaxDataPoolSize() {
        return 98;
    }

    /* access modifiers changed from: private */
    public int getMonthGrid() {
        return this.mGridSpans.spanMonthMax();
    }

    private int getRequiredDataPoolSize(Integer num) {
        if (num != null) {
            return num.intValue();
        }
        return Math.min(getColumnCount() * getColumnCount() * 2, getMaxDataPoolSize());
    }

    /* access modifiers changed from: private */
    public int getYearGrid() {
        return this.mGridSpans.spanYear();
    }

    private boolean hasInValidOldGridSize(int i2) {
        return Arrays.stream(getPinchColumn()).noneMatch(new c(i2, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createViewHolder$6(RecyclerView.ViewHolder viewHolder) {
        try {
            RecyclerView.RecycledViewPool recycledViewPool = this.mViewPool;
            if (recycledViewPool != null) {
                recycledViewPool.putRecycledView(viewHolder);
            }
        } catch (Exception unused) {
            Log.w(this.TAG, "createViewPool#createViewHolder: view pool add failed. ignored");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$createViewPool$5(String str, int i2, PicturesViewDummyAdapter picturesViewDummyAdapter, int i7, long j2, ThreadPool.JobContext jobContext) {
        Trace.beginSection(str);
        GalleryListView galleryListView = this.mRecyclerView;
        if (galleryListView != null) {
            if (i2 == -2) {
                createViewHolder(galleryListView, picturesViewDummyAdapter, getViewTypeFirstTimeline());
            }
            for (int i8 = 0; i8 < i7; i8++) {
                createViewHolder(this.mRecyclerView, picturesViewDummyAdapter, i2);
            }
        }
        Trace.endSection();
        Optional.ofNullable(this.mBlackboard).ifPresent(new C0438h(23));
        StringCompat stringCompat = this.TAG;
        StringBuilder k = j.k("createViewPool#", str, " (");
        k.append(Thread.currentThread().getName());
        k.append(") +");
        k.append(System.currentTimeMillis() - j2);
        Log.d(stringCompat, k.toString());
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleResolutionChange$8(PicturesViewAdapter picturesViewAdapter) {
        if (picturesViewAdapter.isMonthForViewing()) {
            picturesViewAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$hasInValidOldGridSize$7(int i2, int i7) {
        if (i7 == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$onBindView$0(GalleryAppBarLayout galleryAppBarLayout) {
        boolean z;
        if (galleryAppBarLayout.seslGetHeightProPortion() > 0.0f) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onBindView$1() {
        boolean z;
        if (isPortrait() || isTabletDpi() || ((Boolean) Optional.ofNullable(getAppbarLayout()).map(new d(11)).orElse(Boolean.FALSE)).booleanValue()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private int preloadDataViewPool(int i2, boolean z) {
        if (i2 > 42) {
            if (z) {
                int i7 = i2 / 2;
                createViewPool(i7, 0);
                createViewPool(i7, 0);
                return i7;
            }
            createViewPool(42, 0);
            return i2 - 42;
        } else if (i2 <= 0) {
            return i2;
        } else {
            createViewPool(i2, 0);
            return 0;
        }
    }

    private void preloadDividerViewPool() {
        int recycledViewCount;
        if (supportTimeline() && (recycledViewCount = 6 - this.mViewPool.getRecycledViewCount(-2)) > 0) {
            createViewPool(recycledViewCount, -2);
        }
    }

    private void preloadFooterViewPool() {
        if (supportFooter() && this.mViewPool.getRecycledViewCount(-4) == 0) {
            createViewPool(1, -4);
        }
    }

    private void preloadHeaderViewPool() {
        if (supportHeader() && this.mViewPool.getRecycledViewCount(-3) == 0) {
            createViewPool(1, -3);
        }
    }

    private void setMaxThumbnailQueueSize(int i2) {
        ThumbnailLoader.getInstance().setMaxOriginalDecodeQueueSize((int) (((float) (i2 * i2)) * 2.6f));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mScrollPopUpViewStub = (ViewStub) view.findViewById(R.id.fastScrollViewStub);
        if (this.mUesDataCollector) {
            DataCollectorOnScroll dataCollectorOnScroll = new DataCollectorOnScroll(this.mBlackboard);
            this.mDataCollector = dataCollectorOnScroll;
            dataCollectorOnScroll.attach(this.mRecyclerView);
        }
    }

    public boolean canScrollVertically() {
        return true;
    }

    public void changeViewDepthByWheelScroll(int i2) {
        if (canChangeViewDepth(i2)) {
            super.changeViewDepthByWheelScroll(i2);
        }
    }

    public void clearSidePaddingMap() {
        this.mSidePaddingMap.clear();
    }

    public GridInfo.ClusterInfo createClusterInfo() {
        return new GridInfo.ClusterInfo() {
            public int getMinMonthGridSize() {
                return PicturesFragment.this.mGridSpans.spanMonthMin();
            }

            public int getMonthForViewingFromGridSize() {
                if (PicturesFragment.this.supportMonthForViewing()) {
                    return PicturesFragment.this.getExtensionFromGrid();
                }
                return -1;
            }

            public int getMonthForViewingToGridSize() {
                if (PicturesFragment.this.supportMonthForViewing()) {
                    return PicturesFragment.this.getExtensionToGrid();
                }
                return -1;
            }

            public int getMonthGridSize() {
                return PicturesFragment.this.getMonthGrid();
            }

            public int getYearGridSize() {
                return PicturesFragment.this.getYearGrid();
            }

            public boolean isMonth(int i2) {
                return GridValue.isMonth(PicturesFragment.this.getGridSpans().valueOf(i2));
            }
        };
    }

    public CustomCover createCustomCover(View view) {
        return null;
    }

    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new PicturesDragAndDropDelegate(this);
    }

    public GridLayoutManager.SpanSizeLookup createSpanSizeLookUp(final GridLayoutManager gridLayoutManager) {
        return new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                PicturesViewAdapter adapter = PicturesFragment.this.getAdapter();
                if (adapter != null) {
                    return adapter.getHintStartSpan(i2, adapter.getGridSize());
                }
                Log.w(PicturesFragment.this.TAG, "span index 0 (null adapter)");
                return 0;
            }

            public int getSpanSize(int i2) {
                PicturesViewAdapter adapter = PicturesFragment.this.getAdapter();
                if (adapter == null) {
                    return 1;
                }
                if (PicturesFragment.this.useFullSpan(i2, adapter)) {
                    return gridLayoutManager.getSpanCount();
                }
                if (adapter.isRealRatio()) {
                    return Math.min(adapter.getSpanSize(i2), gridLayoutManager.getSpanCount());
                }
                return 1;
            }
        };
    }

    public void createViewPool() {
        if (this.mViewPool == null) {
            SynchronizedViewPool synchronizedViewPool = new SynchronizedViewPool();
            this.mViewPool = synchronizedViewPool;
            setViewPoolSize(synchronizedViewPool);
            preloadViewPool();
        }
    }

    public int getCluster(int i2) {
        return this.mGridSpans.clusterOf(i2);
    }

    public int getColumnCount() {
        GalleryListView listView = getListView();
        if (listView != null) {
            return listView.getColumnCount();
        }
        return 1;
    }

    public int getDefaultSidePadding() {
        return getResources().getDimensionPixelOffset(R.dimen.pictures_default_side_spacing);
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new PicturesViewDummyAdapter(getListView(), getColumnCount());
    }

    public int getExtraLayoutSpace() {
        if (IsScrollStateIdle()) {
            return 0;
        }
        return getListView().getHeight() / 5;
    }

    public View getFastScrollPopupView() {
        ViewStub viewStub;
        if (this.mScrollPopUp == null && (viewStub = this.mScrollPopUpViewStub) != null) {
            this.mScrollPopUp = viewStub.inflate();
        }
        return this.mScrollPopUp;
    }

    public int getGridSpacing(int i2) {
        Integer num = this.mSidePaddingMap.get(Integer.valueOf(i2));
        if (num == null) {
            num = Integer.valueOf(GridMarginHelper.getMargin(this.mRecyclerView, i2) - getDefaultSidePadding());
            this.mSidePaddingMap.put(Integer.valueOf(i2), num);
        }
        return num.intValue();
    }

    public int getLayoutId() {
        return R.layout.fragment_pictures_layout;
    }

    public int getMaxDividerPoolSize() {
        return 9;
    }

    public final int getMaxMonthXsDataPoolSize() {
        if (PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL) {
            return 24;
        }
        return 240;
    }

    public int getOldGridSize(int i2, int i7) {
        if (supportYearTimeline() && i7 == 1 && i2 == getPinchColumn()[1]) {
            return getPinchColumn()[0];
        }
        if (i7 > getPinchColumn()[0] + 50) {
            return 1;
        }
        return i7;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return new PicturesPinchAnimationManagerV3((PinchLayoutManager) this.mLayoutManager, createClusterInfo(), supportPivotOnFingerPos(), isFromFlipCover());
    }

    public final int getPinchDepthRecoveredIfAbsent() {
        int gridDepth = getGridHelper().getGridDepth();
        int[] pinchColumn = getPinchColumn();
        if (gridDepth >= 0 && gridDepth < pinchColumn.length) {
            return gridDepth;
        }
        int preferenceDefault = getPreferenceDefault();
        getGridHelper().saveGridDepth(preferenceDefault);
        Log.e((CharSequence) this.TAG, "recover start pinch depth failed. set default", Integer.valueOf(preferenceDefault), Integer.valueOf(preferenceDefault));
        return preferenceDefault;
    }

    public int getStartPinchDepth() {
        return 1;
    }

    public int getViewTypeFirstTimeline() {
        return -1;
    }

    public void handleDensityChange(int i2) {
        GridMarginHelper.clear();
        this.mSidePaddingMap.clear();
        this.mGridSpans.clear();
        getListView().pendingUpdateScrollPosition(getLayoutManager().findFirstVisibleItemPosition());
        super.handleDensityChange(i2);
        CustomCover customCover = this.mCustomCoverHolder;
        if (customCover != null) {
            customCover.handleDensityChange(i2);
        }
    }

    public void handleInitializeScroll() {
        super.initializeScroll();
    }

    public void handleLongClick(ListViewHolder listViewHolder, int i2) {
        GalleryListView listView = getListView();
        if (!listView.isSelected(i2)) {
            listView.select(i2, true);
            listViewHolder.setChecked(true);
            if (listView.getSelectedItemCount() == 1) {
                ThreadUtil.postOnUiThreadDelayed(new C0451a(0, this), 100);
            }
        }
        if (getAdapter() != null) {
            getAdapter().syncClusterDividerOnLongClick(i2, listViewHolder.getViewType());
        }
        stopAutoDrag();
    }

    public void handleOrientationChange(int i2) {
        if (!BlackboardUtils.isViewerShrink(this.mBlackboard)) {
            getListView().pendingUpdateScrollPosition(getLayoutManager().findFirstVisibleItemPosition());
        }
        super.handleOrientationChange(i2);
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        Optional.ofNullable(getAdapter()).ifPresent(new C0438h(21));
    }

    public void initView(View view) {
        super.initView(view);
    }

    public void initializeAdapter() {
        super.initializeAdapter();
        getListView().setRecycledViewPool(this.mViewPool);
        if (getAdapter() != null && getAdapter().checkLocationAuthChanged()) {
            getAdapter().notifyDataSetChanged();
        }
    }

    public boolean isAllowAdvancedMouseEvent() {
        return true;
    }

    public boolean isClusteringChanged(int i2, int i7) {
        if (i7 == i2 || hasInValidOldGridSize(i7)) {
            return false;
        }
        if (Math.min(i7, i2) == 1 || getCluster(i7) != getCluster(i2)) {
            return true;
        }
        return false;
    }

    public boolean isDrawerStateChanged() {
        return this.mDrawerStateChanged;
    }

    public boolean isFromFlipCover() {
        return false;
    }

    public final boolean isRealRatioSupported() {
        return this.mGridSpans.hasRealRatio;
    }

    public boolean isSelectableViewDepth(int i2) {
        return true;
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent, int i2) {
        if (!super.isVirtualCtrlKeyPressedAllowablePoint(motionEvent, i2)) {
            return false;
        }
        ListViewHolder listViewHolder = (ListViewHolder) getListView().findViewHolderForLayoutPosition(i2);
        if (listViewHolder == null || listViewHolder.isVirtualCtrlKeyPressedAllowablePoint(motionEvent)) {
            return true;
        }
        return false;
    }

    public void loadArguments(Bundle bundle) {
        super.loadArguments(bundle);
    }

    public final View loadOrCreateView(int i2) {
        View view = LayoutCache.getInstance().getView(i2);
        if (view == null) {
            return LayoutInflater.from(getActivity()).inflate(i2, (ViewGroup) null, false);
        }
        return view;
    }

    public void onAppbarVisibleRatio(float f) {
        super.onAppbarVisibleRatio(f);
        CustomCover customCover = this.mCustomCoverHolder;
        if (customCover != null) {
            customCover.setAlphaOnCoverView(f);
        }
    }

    public void onBindView(View view) {
        super.onBindView(view);
        CustomCover createCustomCover = createCustomCover(view);
        this.mCustomCoverHolder = createCustomCover;
        if (createCustomCover != null) {
            createCustomCover.setCoverVisibilitySupplier(new J5.c(26, this));
        }
    }

    public void onDestroy() {
        if (this.mUesDataCollector) {
            this.mDataCollector.destroy();
        }
        destroyCustomCover();
        releaseRunnable();
        super.onDestroy();
        RecyclerView.RecycledViewPool recycledViewPool = this.mViewPool;
        if (recycledViewPool != null) {
            recycledViewPool.clear();
            this.mViewPool = null;
        }
    }

    public void onExpandItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        int mediaItemPosition = getMediaItemPosition(i2);
        addSharedTransition(listViewHolder, mediaItem, mediaItemPosition, true);
        ((PicturesPresenter) this.mPresenter).onExpandItemClick(mediaItemPosition, mediaItem);
    }

    public void onGridChanged(int i2, int i7) {
        if (i7 != -1 && i7 != i2) {
            Optional.ofNullable(getAdapter()).ifPresent(new C0438h(20));
            if (isResumed()) {
                String zoomLevel = AnalyticsDetail.getZoomLevel(i2);
                postAnalyticsLog(AnalyticsEventId.EVENT_PINCH_ZOOM, zoomLevel);
                Log.majorEvent("PinchZoom : " + zoomLevel);
                adjustRecyclerViewPadding();
            }
            setMaxThumbnailQueueSize(i2);
        }
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.USER_EVENT);
        super.onListItemClick(listViewHolder, i2, mediaItem, i7);
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        if (isAutoDragPossible()) {
            handleLongClick(listViewHolder, i2);
            MediaItem[] selectedItems = getSelectedItems();
            if (selectedItems != null) {
                this.mDragAndDropDelegate.startDrag(selectedItems, listViewHolder);
                performHaptic(14);
                return false;
            }
        }
        performHaptic(100);
        return super.onListItemLongClick(listViewHolder, i2, mediaItem);
    }

    public void onLocationItemClick(String str) {
        ((PicturesPresenter) this.mPresenter).onLocationItemClick(str);
    }

    public void onPrepareSharedTransitionV2() {
        SharedTransition.onPreparePictures(this.mBlackboard, this, true);
    }

    public void onSelectionModeChanged(boolean z) {
        CustomCover customCover = this.mCustomCoverHolder;
        if (customCover != null) {
            customCover.setEnabled(!z);
            if (!z) {
                Optional.ofNullable(this.mAppBarLayout).ifPresent(new C0438h(22));
            }
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        int columnCount = getListView().getColumnCount();
        getLayoutManager().setSpanCount(columnCount);
        setMaxThumbnailQueueSize(columnCount);
        createViewPool();
    }

    public void performHaptic(int i2) {
        if (SUPPORT_DC_HAPTIC) {
            SeApiCompat.performHapticFeedback(getApplicationContext(), i2);
        }
    }

    public void preloadViewPool() {
        super.preloadViewPool();
        if (!supportViewPool()) {
            Log.d(this.TAG, "preloadViewPool : skip");
            Optional.ofNullable(this.mBlackboard).ifPresent(new C0438h(19));
            return;
        }
        Integer num = (Integer) this.mBlackboard.pop("data://preload_count");
        int requiredDataPoolSize = getRequiredDataPoolSize(num);
        int recycledViewCount = this.mViewPool.getRecycledViewCount(0);
        int preloadDataViewPool = preloadDataViewPool(requiredDataPoolSize - recycledViewCount, false);
        preloadHeaderViewPool();
        preloadDividerViewPool();
        preloadFooterViewPool();
        preloadDataViewPool(preloadDataViewPool, true);
        int i2 = preloadDataViewPool - requiredDataPoolSize;
        if (i2 > 0) {
            C0368c cVar = new C0368c(this, i2, 26);
            this.mCreatePostDataPoolRunnable = cVar;
            ThreadUtil.postOnBgThreadDelayed(cVar, 800);
        }
        StringCompat stringCompat = this.TAG;
        StringBuilder h5 = a.h(recycledViewCount, requiredDataPoolSize, "preloadViewPool : cache (", ") req=", ", preload=");
        h5.append(num);
        Log.d(stringCompat, h5.toString());
    }

    public void releaseRunnable() {
        this.mBlackboard.erase("data://preload_count");
        Runnable runnable = this.mCreatePostDataPoolRunnable;
        if (runnable != null) {
            ThreadUtil.removeCallbackOnBgThread(runnable);
            this.mCreatePostDataPoolRunnable = null;
        }
    }

    public int[] removeRealRatioColumn(int[] iArr) {
        if (!supportRealRatio()) {
            if (iArr[0] == 1) {
                return Arrays.copyOfRange(iArr, 1, iArr.length);
            }
            if (iArr[iArr.length - 1] == 1) {
                return Arrays.copyOf(iArr, iArr.length - 1);
            }
        }
        return iArr;
    }

    public final void restoreLayout(int i2, View view) {
        if (view != null && !LayoutCache.getInstance().hasView(i2)) {
            if (view.getParent() != null) {
                ViewUtils.removeSelf(view);
            }
            LayoutCache.getInstance().putView(i2, view);
        }
    }

    public void scrollListToPositionByDepth(int i2) {
        int columnCount = getListView().getColumnCount();
        int columnCountByDepth = getListView().getColumnCountByDepth(i2);
        if (columnCountByDepth > 0 && isClusteringChanged(columnCountByDepth, columnCount)) {
            getListView().scrollToPosition(getLayoutManager().getHintViewPosition(getLayoutManager().getHintDataPosition(getListView().findFirstVisibleItemPositionCompat(), columnCount), columnCountByDepth));
        }
    }

    public void setDrawerStateChanged(boolean z) {
        boolean z3;
        if (!isSelectionMode() || !z) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.mDrawerStateChanged = z3;
    }

    public final void setPinchColumnArray() {
        this.mPinchColumnArray = removeRealRatioColumn(loadPinchColumnResource());
        this.mSplitPinchColumnArray = removeRealRatioColumn(loadSplitPinchColumnResource());
    }

    public void setViewPoolSize(RecyclerView.RecycledViewPool recycledViewPool) {
        recycledViewPool.setMaxRecycledViews(-2, getMaxDividerPoolSize());
        recycledViewPool.setMaxRecycledViews(-3, 1);
        recycledViewPool.setMaxRecycledViews(0, getMaxDataPoolSize());
        recycledViewPool.setMaxRecycledViews(-4, 1);
        if (supportMonthForViewing()) {
            recycledViewPool.setMaxRecycledViews(3, getMaxMonthXsDataPoolSize());
        }
    }

    public void showSingleContent(ListViewHolder listViewHolder) {
        if (isSelectionMode()) {
            onExpandItemClick(listViewHolder, listViewHolder.getViewPosition(), listViewHolder.getMediaItem());
        } else {
            super.showSingleContent(listViewHolder);
        }
    }

    public boolean supportDeleteAnimation() {
        return true;
    }

    public boolean supportFastScroll() {
        return true;
    }

    public boolean supportFooter() {
        return false;
    }

    public boolean supportHeader() {
        return true;
    }

    public boolean supportMonthForViewing() {
        if (isPicker() || this.mGridSpans.spanMonths().length <= 1) {
            return false;
        }
        return true;
    }

    public boolean supportPivotOnFingerPos() {
        return true;
    }

    public boolean supportRealRatio() {
        return FEATURE_SUPPORT_REAL_RATIO;
    }

    public boolean supportShrinkTransition() {
        return true;
    }

    public boolean supportTimeline() {
        return false;
    }

    public boolean supportViewPool() {
        return true;
    }

    public void updateAdapter(int i2, int i7) {
        Integer num;
        if (this.mListAdapter != null) {
            if (isClusteringChanged(i2, i7)) {
                CharSequence tag = tag();
                StringBuilder h5 = a.h(i7, i2, "setSpanCount#isClusteringChanged {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, ",[");
                h5.append(getPinchColumn()[0]);
                h5.append("|");
                if (getPinchColumn().length > 1) {
                    num = Integer.valueOf(getPinchColumn()[1]);
                } else {
                    num = null;
                }
                h5.append(num);
                h5.append("]}");
                Log.i(tag, h5.toString());
                this.mListAdapter.onClusterChanged(i7);
            }
            if (i2 != i7) {
                this.mListAdapter.onGridSizeChanged();
            }
        }
    }

    public void updateGridSize() {
        PicturesViewAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.updateGridSize(getMonthGrid(), getMaxColumnSize());
            int gridSize = adapter.getGridSize();
            if (gridSize != 1) {
                getLayoutManager().setSpanCount(gridSize);
            }
        }
    }

    public boolean useFullSpan(int i2, BaseListViewAdapter baseListViewAdapter) {
        if (!baseListViewAdapter.isData(i2)) {
            return true;
        }
        if (!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL || !baseListViewAdapter.isMonthForViewing()) {
            return false;
        }
        return true;
    }

    public PicturesLayoutManager createLayoutManager() {
        AnonymousClass1 r0 = new PicturesLayoutManager(getContext(), getColumnCount()) {
            public boolean canScrollVertically() {
                if (!PicturesFragment.this.canScrollVertically() || !super.canScrollVertically()) {
                    return false;
                }
                return true;
            }

            public int getExtraLayoutSpace(RecyclerView.State state) {
                return PicturesFragment.this.getExtraLayoutSpace();
            }

            public int getSpacing(int i2) {
                return PicturesFragment.this.getGridSpacing(i2);
            }

            public boolean isAppbarCollapsed() {
                if (!(PicturesFragment.this.mAppBarLayout == null || PicturesFragment.this.mToolbar == null)) {
                    int visibleHeight = PicturesFragment.this.mAppBarLayout.getVisibleHeight();
                    int top = PicturesFragment.this.mToolbar.getTop() + PicturesFragment.this.mToolbar.getHeight();
                    if (visibleHeight > 0 || Math.abs(visibleHeight) >= top) {
                        return false;
                    }
                    return true;
                }
                return false;
            }

            public boolean isAppbarVisible() {
                return PicturesFragment.this.isAppBarPartiallyVisible();
            }

            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                super.setSpanCount(i2);
                Log.d(PicturesFragment.this.tag(), a.d(spanCount, i2, "setSpanCount {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
                int oldGridSize = PicturesFragment.this.getOldGridSize(i2, spanCount);
                PicturesFragment.this.updateAdapter(i2, oldGridSize);
                PicturesFragment.this.onGridChanged(i2, oldGridSize);
            }
        };
        r0.setSpanSizeLookup(createSpanSizeLookUp(r0));
        return r0;
    }

    public PicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new PicturesViewAdapter(this, getLocationKey(), isRealRatioSupported());
    }

    public P createPresenter(V v) {
        return new PicturesPresenter(this.mBlackboard, v);
    }

    public PicturesViewAdapter getAdapter() {
        return (PicturesViewAdapter) this.mListAdapter;
    }

    public PicturesLayoutManager getLayoutManager() {
        return (PicturesLayoutManager) super.getLayoutManager();
    }

    public void createViewPool(int i2, int i7) {
        try {
            ThreadPool.getInstance().submit(new C0452b(this, "ViewHolder-" + (-i7) + "-" + i2, i7, getDummyAdapter(), i2, System.currentTimeMillis()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void onFooterItemClick() {
    }
}
