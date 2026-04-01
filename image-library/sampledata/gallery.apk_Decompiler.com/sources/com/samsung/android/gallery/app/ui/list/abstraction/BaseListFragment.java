package com.samsung.android.gallery.app.ui.list.abstraction;

import A2.d;
import A4.C0366a;
import A4.C0375j;
import A4.C0376k;
import A4.C0377l;
import A4.C0378m;
import A4.C0379n;
import A4.C0380o;
import A4.C0381p;
import A4.C0382q;
import A4.C0383s;
import A4.C0384t;
import A4.C0385u;
import A4.C0386v;
import A4.C0387w;
import A4.r;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DummyDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardDelegate;
import com.samsung.android.gallery.module.abstraction.GridSpans;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RandomNumber;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.ListAutoDragHandler;
import com.samsung.android.gallery.widget.listview.noitem.EmptyViewListener;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager;
import com.samsung.android.gallery.widget.listview.selection.OnItemCheckChangeListener;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.smartalbum.SmartAlbumHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.toolbar.OnSelectAllListener;
import com.samsung.android.gallery.widget.utils.BackPressUtils;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import q2.C0267a;
import q2.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseListFragment<V extends IBaseListView, P extends BaseListPresenter> extends AbsListFragment<V, P> implements IBaseListView, OnSelectAllListener {
    protected final BaseListBottomHandler mBottomHandler = new BaseListBottomHandler(this);
    protected final BaseListDelegate mDelegate = createBaseListDelegate();
    protected DragAndDropDelegate mDragAndDropDelegate = new DummyDragAndDropDelegate();
    /* access modifiers changed from: private */
    public boolean mFinishLayout;
    protected boolean mFloatingMode = false;
    protected final BaseListKeyHandler mKeyHandler = new BaseListKeyHandler(this);
    protected RecyclerView.LayoutManager mLayoutManager;
    /* access modifiers changed from: protected */
    public BaseListViewAdapter mListAdapter;
    private OnItemCheckChangeListener mOnItemCheckChangeListener;
    private boolean mPendingDataAvailable;
    protected final TipCardDelegate mTipCardDelegate = createTipCardDelegate();

    private void adjustBottomMoveBarMargin(View view, int i2, int i7, int i8) {
        if (view != null) {
            View view2 = view;
            view2.post(new C0380o(view2, i2, i7, i8, 0));
        }
    }

    private boolean checkConsumeTouchEventOnAdvancedMouseAction(MotionEvent motionEvent) {
        if (ViewUtils.getGlobalVisileRect((View) Optional.ofNullable(getActivity()).map(new C0384t(0)).orElse((Object) null)).contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return true;
        }
        return false;
    }

    private OnItemCheckChangeListener getOnItemCheckChangeListener() {
        if (this.mOnItemCheckChangeListener == null) {
            this.mOnItemCheckChangeListener = new BaseListOnItemCheckChangeListener(this, this.mDragAndDropDelegate, this.mBottomHandler);
        }
        return this.mOnItemCheckChangeListener;
    }

    private boolean isLocationKeyValid() {
        String locationKey = getLocationKey();
        if (locationKey == null || !locationKey.equals(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$adjustAppbarHeightOffset$2(WindowInsets windowInsets, GalleryAppBarLayout galleryAppBarLayout) {
        int systemInsetsTop = WindowUtils.getSystemInsetsTop(windowInsets);
        galleryAppBarLayout.seslSetCollapsedHeight(galleryAppBarLayout.seslGetDefaultCollapsedHeight() + ((float) systemInsetsTop));
        galleryAppBarLayout.seslSetProportionExtraHeight(systemInsetsTop);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$adjustBottomBarMargin$3(View view, int i2, int i7, int i8) {
        ViewMarginUtils.setMarginRelative(view, Integer.valueOf(i2), 0, Integer.valueOf(i7), 0);
        ViewMarginUtils.setBottomMargin(view, i8);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$adjustBottomMoveBarMargin$4(View view, int i2, int i7, int i8) {
        ViewMarginUtils.setMarginRelative(view, Integer.valueOf(i2), 0, Integer.valueOf(i7), 0);
        ViewMarginUtils.setBottomMargin(view, i8);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$clearAdvancedMouseFocus$10(AdvancedMouseFocusManager advancedMouseFocusManager) {
        advancedMouseFocusManager.clearViewPosition();
        advancedMouseFocusManager.clearLastSelectedViewPosition();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executePendingDataChange$5() {
        if (!isDestroyed()) {
            onDataChangedOnUi();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getAllItems$9(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeAdapter$0(BaseListViewAdapter baseListViewAdapter) {
        try {
            if (baseListViewAdapter == this.mListAdapter) {
                saLoggingSendCount();
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isDialogVisible$11(Fragment fragment) {
        if (!(fragment instanceof AppCompatDialogFragment) || !fragment.isVisible()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onViewCreated$7(ThreadPool.JobContext jobContext) {
        boolean z;
        if (isDestroyed() || !onPostViewCreated()) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$8() {
        if (!isDestroyed()) {
            ThreadPool.getInstance().submit(new C0381p(0, this));
        }
    }

    /* access modifiers changed from: private */
    public void onAutoDragReady(int i2) {
        if (isAutoDragPossible()) {
            Optional.ofNullable(getAdapter()).ifPresent(new C0366a(7));
            if (startAutoDrag(i2)) {
                this.mDelegate.performDragAutoHaptic();
                return;
            }
            return;
        }
        Log.w(this.TAG, "onAutoDragReady skip");
    }

    private void prepareThumbnailLoadDone() {
        if (!this.mBlackboard.isEmpty("lifecycle://on_thumbnail_load_done")) {
            Log.d(this.TAG, "prepareThumbnailLoadDone > skip");
            return;
        }
        Log.p(this.TAG, "prepareThumbnailLoadDone");
        this.mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                GalleryListView galleryListView = BaseListFragment.this.mRecyclerView;
                if (galleryListView != null) {
                    galleryListView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    BaseListFragment.this.mFinishLayout = true;
                    BaseListFragment.this.publishThumbnailLoadDone();
                }
            }
        });
    }

    private void updateToolbarConfigurations() {
        if (getContext() != null) {
            GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
            if (galleryAppBarLayout != null) {
                galleryAppBarLayout.dispatchConfigurationChanged(true, getResources().getConfiguration());
                updateAppbarScroll(getResources().getConfiguration());
                return;
            }
            GalleryToolbar galleryToolbar = this.mToolbar;
            if (galleryToolbar != null) {
                galleryToolbar.dispatchConfigurationChanged(true, getResources().getConfiguration());
            }
        }
    }

    public /* bridge */ /* synthetic */ void addAppbarOffsetChangedListener() {
        super.addAppbarOffsetChangedListener();
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
        ImageView image = listViewHolder.getImage();
        if (image != null) {
            ViewUtils.setVisibility(image, 0);
            this.mDelegate.bindImage(listViewHolder, mediaItem, false);
            SharedTransition.addSharedElement(this.mBlackboard, image, SharedTransition.getTransitionName(mediaItem), new TransitionInfo(mediaItem, listViewHolder.getBitmap(), i2));
        }
    }

    public void adjustAppbarHeightOffset(WindowInsets windowInsets) {
        Optional.ofNullable(this.mAppBarLayout).ifPresent(new C0382q(windowInsets, 0));
    }

    public void adjustBottomBarMargin(View view) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            int drawerWidth = getDrawerWidth();
            int systemInsetsStart = WindowUtils.getSystemInsetsStart(rootWindowInsets);
            int systemInsetsEnd = WindowUtils.getSystemInsetsEnd(rootWindowInsets);
            int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(rootWindowInsets);
            int i2 = systemInsetsStart + drawerWidth;
            adjustBottomBarMargin(activity.findViewById(R.id.bottom_bar_layout_container), i2, systemInsetsEnd, systemInsetsBottom);
            adjustBottomMoveBarMargin(activity.findViewById(R.id.bottom_move_bar_layout_container), i2, systemInsetsEnd, systemInsetsBottom);
        }
    }

    public void adjustEmptyViewMargin(WindowInsets windowInsets) {
        ViewMarginUtils.setBottomMargin(this.mEmptyView, getBottomTabHeight());
    }

    public void adjustRecyclerViewPadding() {
        this.mBottomHandler.updateListViewBottomPadding();
        this.mRecyclerView.setClipToPadding(false);
    }

    public void adjustToolbarLayout(WindowInsets windowInsets) {
        adjustAppbarHeightOffset(windowInsets);
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            ViewParent parent = galleryToolbar.getParent();
            if (parent instanceof FloatingToolbarLayout) {
                FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) parent;
                ViewMarginUtils.setTopPadding(floatingToolbarLayout, WindowUtils.getSystemInsetsTop(windowInsets));
                floatingToolbarLayout.setWindowBottomInset(WindowUtils.getSystemInsetsBottom(windowInsets));
                return;
            }
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity != null && PickerUtil.isPickerMode(this.mBlackboard)) {
            View findViewById = activity.findViewById(R.id.tab_layout_floating_container);
            if (findViewById instanceof FloatingBottomLayout) {
                ((FloatingBottomLayout) findViewById).setWindowBottomInset(WindowUtils.getSystemInsetsBottom(windowInsets));
            }
        }
    }

    public void afterBindViewHolder(ListViewHolder listViewHolder, int i2) {
        this.mKeyHandler.bindViewHolder(listViewHolder, i2);
    }

    public void changeViewDepthByWheelScroll(int i2) {
        ((BaseListPresenter) this.mPresenter).changeViewDepth(getListView(), i2);
        ((BaseListPresenter) this.mPresenter).updateViewHolderMargin();
    }

    public boolean checkTabSelectable() {
        char c5;
        char c6;
        char c8;
        GalleryListView listView = getListView();
        if (listView == null) {
            return true;
        }
        if (!listView.isSelectionMode() && !listView.isTouchOngoing() && !listView.isOngoingPinchAnimation()) {
            return true;
        }
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("checkTabSelectable {false,");
        if (listView.isSelectionMode()) {
            c5 = 'S';
        } else {
            c5 = 's';
        }
        sb2.append(c5);
        if (listView.isTouchOngoing()) {
            c6 = 'T';
        } else {
            c6 = 't';
        }
        sb2.append(c6);
        if (listView.isOngoingPinchAnimation()) {
            c8 = 'P';
        } else {
            c8 = 'p';
        }
        sb2.append(c8);
        sb2.append("}");
        Log.d(stringCompat, sb2.toString());
        return false;
    }

    public boolean clearAdvancedMouseFocus() {
        BaseListViewAdapter adapter;
        if (!isOnAdvancedMouseFocused() || (adapter = getAdapter()) == null) {
            return false;
        }
        Optional.ofNullable(adapter.getAdvancedMouseFocusManager()).ifPresent(new C0366a(5));
        adapter.notifyAdvancedMouseFocusedItemChanged();
        return true;
    }

    public /* bridge */ /* synthetic */ void closeMediaData() {
        super.closeMediaData();
    }

    public BaseListDelegate createBaseListDelegate() {
        return new BaseListDelegate(this);
    }

    public Animation createDefaultEnterAnimation(Context context, int i2) {
        return this.mDelegate.createAnimation(context, i2);
    }

    public y createFloatingToolbarAware(final FloatingToolbarLayout floatingToolbarLayout) {
        return new y(floatingToolbarLayout) {
            public View getReferenceView(C0267a aVar) {
                if (!BaseListFragment.this.isViewActive() || !BaseListFragment.this.isSelectionMode()) {
                    return super.getReferenceView(aVar);
                }
                if (aVar == C0267a.END_FIRST) {
                    return super.getReferenceView(aVar);
                }
                return null;
            }

            public List<View> getReferenceViews(C0267a aVar) {
                if (!BaseListFragment.this.isViewActive() || !BaseListFragment.this.isSelectionMode() || aVar != C0267a.START_FIRST) {
                    return null;
                }
                View findViewById = floatingToolbarLayout.findViewById(R.id.select_all_layout);
                View findViewById2 = floatingToolbarLayout.findViewById(R.id.select_info_layout);
                if (ViewUtils.isVisible(findViewById)) {
                    return List.of(findViewById);
                }
                if (findViewById2 != null) {
                    return List.of(findViewById2);
                }
                return null;
            }

            public void onStartHideFloatingBackground() {
                BaseListFragment baseListFragment = BaseListFragment.this;
                baseListFragment.mFloatingMode = false;
                if (baseListFragment.isViewActive()) {
                    Optional.ofNullable(BaseListFragment.this.mToolbar).ifPresent(new n(0));
                }
            }

            public void onStartShowFloatingBackground() {
                BaseListFragment baseListFragment = BaseListFragment.this;
                baseListFragment.mFloatingMode = true;
                if (baseListFragment.isViewActive()) {
                    Optional.ofNullable(BaseListFragment.this.mToolbar).ifPresent(new n(1));
                }
            }
        };
    }

    public abstract RecyclerView.LayoutManager createLayoutManager();

    public abstract BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView);

    public void createPopupMenu(ViewGroup viewGroup, PointF pointF, MediaItem mediaItem) {
        this.mDelegate.createPopupMenu(viewGroup, pointF, mediaItem);
    }

    public void enterSelectionMode(int i2) {
        if (isNormalLaunchMode()) {
            Clipboard.getInstance(this.mBlackboard).clear();
        }
        getListView().enterSelectionMode(i2);
        setSmartAlbumEnabled(false);
    }

    public void executePendingDataChange() {
        Log.i(this.TAG, "executePendingDataChange");
        ThreadUtil.postOnUiThread(new C0379n(this, 2));
    }

    public void exitSelectionMode(boolean z) {
        GalleryListView listView = getListView();
        if (listView != null) {
            listView.exitSelectionMode(z);
            setSmartAlbumEnabled(true);
            ClipDataManager.getInstance().clear();
        }
    }

    public BaseListViewAdapter getAdapter() {
        return this.mListAdapter;
    }

    public MediaItem[] getAllItems() {
        int itemCount;
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter == null || getListView() == null || (itemCount = getListView().getItemCount()) <= 0) {
            return new MediaItem[0];
        }
        String str = hashTag() + ".getAllItems";
        try {
            this.mMediaData.acquireReadLock(str);
            return (MediaItem[]) IntStream.range(0, itemCount).mapToObj(new C0386v(0, baseListViewAdapter)).toArray(new C0387w(0));
        } finally {
            this.mMediaData.releaseReadLock(str);
        }
    }

    public /* bridge */ /* synthetic */ int getAppBarVisibleHeight() {
        return super.getAppBarVisibleHeight();
    }

    public String getAppState() {
        ListViewServiceBixby listViewServiceBixby;
        P p6 = this.mPresenter;
        if (p6 != null) {
            listViewServiceBixby = ((BaseListPresenter) p6).getBixbyProxy();
        } else {
            listViewServiceBixby = null;
        }
        if (listViewServiceBixby != null) {
            return listViewServiceBixby.getAppState();
        }
        return null;
    }

    public /* bridge */ /* synthetic */ GalleryAppBarLayout getAppbarLayout() {
        return super.getAppbarLayout();
    }

    public View getBottomBar() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((BaseListPresenter) p6).getBottomBar();
        }
        return null;
    }

    public int getBottomBarHeight() {
        return getResources().getDimensionPixelOffset(R.dimen.bottom_bar_floating_height);
    }

    public View getBottomMoveBar() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((BaseListPresenter) p6).getBottomMoveBar();
        }
        return null;
    }

    public int getBottomTabHeight() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((BaseListPresenter) p6).getBottomTabHeight();
        }
        return 0;
    }

    public int getDataCount() {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            return baseListViewAdapter.getDataCount();
        }
        return 0;
    }

    public /* bridge */ /* synthetic */ int getDepthFromGridSize(int i2) {
        return super.getDepthFromGridSize(i2);
    }

    public int getDrawerWidth() {
        FragmentActivity activity = getActivity();
        if (activity == null || !DrawerUtil.supportDrawerLayout((Context) activity) || !isDrawerMode() || !isDisplayWithDrawer()) {
            return 0;
        }
        if (((Boolean) this.mBlackboard.read("data://drawer_opened", Boolean.valueOf(DrawerUtil.isDrawerDefaultOpen(getContext())))).booleanValue()) {
            return ViewUtils.getWidth(activity.findViewById(R.id.drawer_tab_container));
        }
        return getResources().getDimensionPixelOffset(R.dimen.drawer_collapsed_width);
    }

    public int getFirstItemDataPositionFromSelected() {
        ArrayList<Integer> selectedItemList;
        GalleryListView listView = getListView();
        if (listView == null || !listView.isSelectionMode() || (selectedItemList = listView.getSelectedItemList()) == null || selectedItemList.size() <= 0) {
            return -1;
        }
        return getMediaItemPosition(selectedItemList.get(0).intValue());
    }

    public /* bridge */ /* synthetic */ GridHelper getGridHelper() {
        return super.getGridHelper();
    }

    public /* bridge */ /* synthetic */ GridSpans getGridSpans() {
        return super.getGridSpans();
    }

    public int getItemCount() {
        GalleryListView listView = getListView();
        if (listView != null) {
            return listView.getItemCount();
        }
        return 0;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        if (this.mLayoutManager == null) {
            this.mLayoutManager = createLayoutManager();
        }
        return this.mLayoutManager;
    }

    public /* bridge */ /* synthetic */ GalleryListView getListView() {
        return super.getListView();
    }

    public int getListViewPosition(int i2) {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            return baseListViewAdapter.getViewPosition(i2);
        }
        return -1;
    }

    public /* bridge */ /* synthetic */ MediaData getMediaData(String str) {
        return super.getMediaData(str);
    }

    public int getMediaItemPosition(int i2) {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter == null) {
            return i2;
        }
        return baseListViewAdapter.getMediaItemPosition(i2);
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return null;
    }

    public /* bridge */ /* synthetic */ int[] getPinchColumn() {
        return super.getPinchColumn();
    }

    public BaseListPresenter getPresenter() {
        return (BaseListPresenter) this.mPresenter;
    }

    public int getSelectedCountForToolbar(boolean z) {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (!z || (baseListViewAdapter != null && !baseListViewAdapter.useClipBoardForNormalSelectionMode())) {
            return Clipboard.getInstance(this.mBlackboard).getTotalCount();
        }
        return getSelectedItemCount();
    }

    public int getSelectedItemCount() {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            return baseListViewAdapter.getSelectedItemCountForView();
        }
        return 0;
    }

    public MediaItem[] getSelectedItems() {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            return baseListViewAdapter.getSelectedItems();
        }
        return new MediaItem[0];
    }

    public int getSelectionToolbarTitle() {
        return -1;
    }

    public /* bridge */ /* synthetic */ GalleryToolbar getToolbar() {
        return super.getToolbar();
    }

    public int getTotalSelectableCount() {
        return getDataCount();
    }

    public int getUsableHeight(AppBarLayout appBarLayout) {
        int i2;
        Rect rect = new Rect();
        appBarLayout.getWindowVisibleDisplayFrame(rect);
        int i7 = rect.bottom - rect.top;
        if (getToolbar() != null) {
            i2 = getToolbar().getHeight();
        } else {
            i2 = 0;
        }
        return i7 - i2;
    }

    public void handleDensityChange(int i2) {
        getBoosterCompat().acquireFull();
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            baseListViewAdapter.onDensityChange();
        }
        this.mRecyclerView.handleDensityChange();
        preloadViewPool();
        updateListColumn();
        updateToolbar();
        Optional.ofNullable(getToolbar()).ifPresent(new C0366a(8));
        updateSelectionToolBar();
        this.mBottomHandler.updateBottomBar();
        BlackboardUtils.removeOtherTabs(this.mBlackboard, getLocationKey());
        SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
        if (smartAlbumHolder != null) {
            smartAlbumHolder.restore();
        }
        this.mDelegate.updateEmptyViewDensity(this.mEmptyView);
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((BaseListPresenter) p6).handleDensityChange();
        }
    }

    public boolean handleDrag(View view, DragEvent dragEvent) {
        if (BlackboardUtils.isViewerDragShrink(getBlackboard())) {
            return false;
        }
        if ((isViewActive() || dragEvent.getAction() == 6) && this.mDragAndDropDelegate.handleDrag(view, dragEvent)) {
            return true;
        }
        return false;
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        updateListColumn();
    }

    public void handleResolutionChange(int i2) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "handleResolutionChange " + i2);
        updateListColumn();
        this.mBottomHandler.updateBottomBar();
    }

    public boolean hasLayoutManager() {
        if (this.mLayoutManager != null) {
            return true;
        }
        return false;
    }

    public void initView(View view) {
        int i2;
        GalleryListView listView = getListView();
        listView.setColumnCount(getPinchColumn());
        this.mNormalColumnOrder = this.mGridSpans.normalOrder;
        if (isDexMode()) {
            i2 = getStartPinchDepthDex();
        } else {
            i2 = getStartPinchDepth();
        }
        listView.setStartDepth(i2);
        listView.setLayoutManager(getLayoutManager());
        listView.setPinchAnimationManager(getPinchAnimationManager());
        listView.setFadingEdgeExtendSupplier(new C0385u(0, this));
        initializeEmptyView();
        super.registerEmptyViewListener();
        addAppbarOffsetChangedListener();
        if (this.mPendingDataAvailable) {
            Log.i(this.TAG, "bindView process pending data");
            this.mPendingDataAvailable = false;
            executePendingDataChange();
        }
    }

    public void initializeAdapter() {
        this.mListAdapter = createListViewAdapter(getListView());
        getListView().setAdapter(this.mListAdapter);
        prepareThumbnailLoadDone();
        if (supportAutoDrag()) {
            this.mListAdapter.setOnAutoDragHandler(new ListAutoDragHandler(new C0383s(0, this)));
        }
        if (RandomNumber.nextInt(10) == 1) {
            ThreadUtil.postOnBgThreadDelayed(new d(3, this, this.mListAdapter), 1000);
        }
    }

    public void invalidateToolbar() {
        if (isTouchOngoing()) {
            getListView().addOnTouchUpListener(new C0376k(0, this));
            getListView().addOnTouchCancelListener(new C0377l(this));
        } else {
            invalidateOptionsMenu();
        }
        updateToolbarSelectionCount(getToolbar());
    }

    public boolean isActive() {
        if (getUserVisibleHint() || isLocationKeyValid()) {
            return true;
        }
        return false;
    }

    public boolean isAdapterReady() {
        if (this.mListAdapter != null) {
            return true;
        }
        return false;
    }

    public /* bridge */ /* synthetic */ boolean isAppBarPartiallyVisible() {
        return super.isAppBarPartiallyVisible();
    }

    public boolean isAutoDragPossible() {
        this.mDragAndDropDelegate.resetCurrentMode();
        return this.mDragAndDropDelegate.isAutoDragPossible();
    }

    public boolean isDataPrepared() {
        if (getListView().getAdapter() != null) {
            return true;
        }
        return false;
    }

    public boolean isDialogVisible() {
        try {
            return getParentFragmentManager().getFragments().stream().anyMatch(new C0375j(0));
        } catch (IllegalStateException e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "getParentFragmentManager is null. e=" + e.getMessage());
            return false;
        }
    }

    public /* bridge */ /* synthetic */ boolean isDrawerOpen() {
        return super.isDrawerOpen();
    }

    public /* bridge */ /* synthetic */ boolean isEmptyViewShowing() {
        return super.isEmptyViewShowing();
    }

    public boolean isEnterTransitionOnGoing() {
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            return this.mDelegate.isEnterTransitionState();
        }
        if (this.mCurrentTransitioningAnim == R.animator.sesl_fragment_open_enter) {
            return true;
        }
        return false;
    }

    public boolean isFadingEdgeExtended() {
        return isSelectionMode();
    }

    public boolean isHideScroller() {
        return isAppBarPartiallyVisible();
    }

    public boolean isOnAdvancedMouseFocused() {
        P p6 = this.mPresenter;
        if (p6 == null || !((BaseListPresenter) p6).isOnAdvancedMouseFocused()) {
            return false;
        }
        return true;
    }

    public boolean isOngoingPinchAnimation() {
        return ((Boolean) Optional.ofNullable(getListView()).map(new C0384t(2)).orElse(Boolean.FALSE)).booleanValue();
    }

    public boolean isPopupMenuShowing() {
        return this.mDragAndDropDelegate.isPopupMenuShowing();
    }

    public boolean isSearchToolbarFocused() {
        Boolean bool;
        if (getEventContext() != null) {
            bool = (Boolean) getEventContext().getEventContextData("searchToolbarFocus");
        } else {
            bool = null;
        }
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    public boolean isSelectionMode() {
        GalleryListView listView = getListView();
        if (listView == null || !listView.isSelectionMode()) {
            return false;
        }
        return true;
    }

    public boolean isSimilarModeAnimating() {
        return ((Boolean) Optional.ofNullable(getListView()).map(new C0384t(1)).orElse(Boolean.FALSE)).booleanValue();
    }

    public boolean isTouchOngoing() {
        GalleryListView listView = getListView();
        if (listView == null || !listView.isTouchOngoing()) {
            return false;
        }
        return true;
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent, int i2) {
        GalleryListView listView = getListView();
        if (listView == null || listView.isTouchedOnViewRectRangeOfScroller(motionEvent) || !motionEvent.isButtonPressed(1) || (i2 != -1 && listView.isData(i2))) {
            return false;
        }
        return true;
    }

    public boolean needToRegisterInsetListener() {
        return true;
    }

    public boolean onAdvancedMouseEvent(MotionEvent motionEvent) {
        if (checkConsumeTouchEventOnAdvancedMouseAction(motionEvent)) {
            return true;
        }
        return this.mKeyHandler.onMouseEvent(motionEvent);
    }

    public final void onAppbarOffsetChanged(AppBarLayout appBarLayout, int i2) {
        BaseListViewAdapter baseListViewAdapter;
        float f;
        if (this.mAppBarLayout != null) {
            View view = this.mEmptyView;
            if (view != null) {
                updateEmptyViewLayout(appBarLayout, view, i2);
            }
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            if (totalScrollRange == 0) {
                f = 0.0f;
            } else {
                f = 1.0f - Math.min(1.0f, Math.abs(((float) i2) / ((float) totalScrollRange)));
            }
            onSmartAlbumVisibleRatio(f);
            onAppbarVisibleRatio(f);
            onAppbarVerticalOffset(appBarLayout, i2);
        }
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && (baseListViewAdapter = this.mListAdapter) != null) {
            baseListViewAdapter.onAppbarOffsetChanged(i2);
        }
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (SdkConfig.atLeast(SdkConfig.GED.P)) {
            if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                adjustContentAreaMargin(view, windowInsets, isApplyWindowInsets(windowInsets));
                adjustToolbarLayout(windowInsets);
            }
            adjustRecyclerViewPadding();
            adjustBottomBarMargin(view);
            adjustEmptyViewMargin(windowInsets);
        }
        return windowInsets;
    }

    public /* bridge */ /* synthetic */ void onAttach(Context context) {
        super.onAttach(context);
    }

    public boolean onBackPressed() {
        if (isEnterTransitionOnGoing()) {
            Log.d(this.TAG, "onBackPressed: during enter animation");
            return true;
        }
        SimpleAutoScroller simpleAutoScroller = this.mAutoScroller;
        if (simpleAutoScroller != null) {
            simpleAutoScroller.cancel();
            this.mAutoScroller = null;
        }
        GalleryListView galleryListView = this.mRecyclerView;
        if (galleryListView == null) {
            return false;
        }
        galleryListView.resetTouch();
        if (!this.mRecyclerView.isSelectionMode()) {
            return false;
        }
        ((BaseListPresenter) this.mPresenter).exitSelectionMode(true);
        return true;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        createDragAndDropDelegate();
        if (view != null && !this.mDragAndDropDelegate.isDummy()) {
            view.setOnDragListener(new C0378m(0, this));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mPresenter != null && !isViewHidden()) {
            ((BaseListPresenter) this.mPresenter).checkPreviewCandidate();
        }
        this.mDragAndDropDelegate.cancelAnimation();
        this.mDragAndDropDelegate.release();
        updateAppbarScroll(configuration);
    }

    public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDataChangedOnUi() {
        if (this.mListAdapter == null) {
            initializeAdapter();
            initializeOptionalUi();
        } else {
            this.mRecyclerView.resetScroller();
            this.mDelegate.dismissPopupView();
            this.mListAdapter.notifyDataChanged(new C0379n(this, 3));
            invalidateOptionsMenu();
        }
        clearAdvancedMouseFocus();
        ((BaseListPresenter) this.mPresenter).notifyDataChanged(this.mMediaData);
        GalleryListView galleryListView = this.mRecyclerView;
        BaseListPresenter baseListPresenter = (BaseListPresenter) this.mPresenter;
        Objects.requireNonNull(baseListPresenter);
        galleryListView.post(new r(0, baseListPresenter));
    }

    public void onDataInserted(int i2, int i7) {
        initializeScroll();
    }

    public void onDataRangeChangedOnUi(int i2, int i7) {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            baseListViewAdapter.notifyItemRangeChanged(i2, i7);
        }
    }

    public void onDataRangeInserted(int i2, int i7) {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            baseListViewAdapter.notifyDataRangeInserted(i2, i7);
            onDataInserted(i2, i7);
            if (isSelectionMode()) {
                updateToolbarSelectionCount(getToolbar());
            }
        }
        if (i2 == 3000) {
            this.mBlackboard.publish("lifecycle://onTimeLineFullSwapDone", Long.valueOf(System.currentTimeMillis()));
        }
    }

    public void onDestroy() {
        GalleryListView listView = getListView();
        if (listView != null) {
            listView.setAdapter((RecyclerView.Adapter) null);
            listView.setEmptyViewListener((EmptyViewListener) null);
            listView.setRecycledViewPool((RecyclerView.RecycledViewPool) null);
        }
        this.mLayoutManager = null;
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            baseListViewAdapter.destroy();
            this.mListAdapter = null;
        }
        this.mSmartAlbumHolder = null;
        this.mKeyHandler.unbindMouseHandler();
        this.mDragAndDropDelegate.destroy();
        removeAppbarOffsetChangedListener();
        ClipDataManager.getInstance().clear();
        super.onDestroy();
    }

    public void onDetach() {
        this.mKeyHandler.setVirtualCtrlKeyPressed(false);
        super.onDetach();
    }

    public /* bridge */ /* synthetic */ void onDisplayedWithDrawer() {
        super.onDisplayedWithDrawer();
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        StringBuilder t = C0212a.t(str, "mMediaData : ");
        t.append(this.mMediaData);
        Logger.dumpLog(printWriter, t.toString());
        Logger.dumpLog(printWriter, str + "mListAdapter : " + this.mListAdapter);
        Logger.dumpLog(printWriter, str + "mRecyclerView : " + this.mRecyclerView);
        GalleryListView galleryListView = this.mRecyclerView;
        if (galleryListView != null) {
            galleryListView.onDump(printWriter, str);
        }
    }

    public void onEmptySpaceSecondaryClick(PointF pointF) {
        if (setInputBlock(this.TAG + "_onEmptySpaceSecondaryClick")) {
            createPopupMenu((ViewGroup) getListView().getRootView(), pointF, (MediaItem) null);
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return this.mKeyHandler.onGenericMotionEvent(motionEvent);
    }

    public void onHiddenChanged(boolean z) {
        if (z) {
            stopScroll();
            P p6 = this.mPresenter;
            if (p6 != null) {
                ((BaseListPresenter) p6).stopAllPreview(false);
            }
        } else {
            updateToolbarConfigurations();
            P p8 = this.mPresenter;
            if (p8 != null) {
                ((BaseListPresenter) p8).checkPreviewCandidate();
            }
        }
        super.onHiddenChanged(z);
        if (!z) {
            if (this.mDisplayWithDrawer) {
                this.mBlackboard.post("command://CreateChildFragmentView", (Object) null);
            }
            if (supportTabLayout()) {
                this.mBlackboard.post("command://UpdateBottomTabFloatingView", this.mRecyclerView);
            }
            Optional.ofNullable(getView()).ifPresent(new C0366a(4));
        }
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return this.mKeyHandler.onKeyDown(i2, keyEvent);
    }

    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        return this.mKeyHandler.onKeyLongPress(i2);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        return this.mKeyHandler.onKeyUp(i2);
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        int mediaItemPosition = getMediaItemPosition(i2);
        if (((BaseListPresenter) this.mPresenter).onListItemClick(listViewHolder, mediaItemPosition, mediaItem)) {
            addSharedTransition(listViewHolder, mediaItem, mediaItemPosition, false);
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        clearAdvancedMouseFocus();
        return false;
    }

    public void onListItemSecondaryClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, PointF pointF) {
        if (setInputBlock(this.TAG + "_onListItemSecondaryClick")) {
            createPopupMenu((ViewGroup) getListView().getRootView(), pointF, mediaItem);
        }
    }

    public /* bridge */ /* synthetic */ void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
    }

    public void onPause() {
        if (isSelectionMode() && isActivityResumed()) {
            this.mBlackboard.post("command://HideBottomBar", Boolean.FALSE);
        }
        if (isOnAdvancedMouseFocused()) {
            clearAdvancedMouseFocus();
        }
        stopAutoDrag();
        this.mDragAndDropDelegate.cancelAnimation();
        super.onPause();
    }

    public boolean onPenEvent(MotionEvent motionEvent) {
        return this.mKeyHandler.onPenEvent(motionEvent);
    }

    public boolean onPostViewCreated() {
        return false;
    }

    public void onPrePause(boolean z) {
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((BaseListPresenter) p6).stopAllPreview(!z);
        }
    }

    public void onResume() {
        if (isSelectionMode() && getSelectedItemCount() > 0) {
            this.mBlackboard.post("command://ShowBottomBar", (Object) null);
        }
        super.onResume();
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            baseListViewAdapter.resume();
        }
        if (isSelectionMode()) {
            invalidateToolbar();
        }
    }

    public void onSelectAll() {
        GalleryListView listView = getListView();
        if (listView != null) {
            postAnalyticsLogForSelection(true);
            if (listView.selectAll()) {
                updateSelectionToolBar();
            }
        }
    }

    public /* bridge */ /* synthetic */ void onStart() {
        super.onStart();
    }

    public void onUnSelectAll() {
        GalleryListView listView = getListView();
        if (listView != null) {
            postAnalyticsLogForSelection(false);
            listView.unSelectAll();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        BaseListFragment baseListFragment;
        boolean isViewReady = isViewReady();
        super.onViewCreated(view, bundle);
        if (!supportSelection()) {
            this.mRecyclerView.disableSelectMode(true);
        }
        if (isViewReady) {
            updateToolbarConfigurations();
            if (!isHidden()) {
                addLayoutListenerForAutoScroll();
            }
        } else if (supportPostViewCreated()) {
            ThreadUtil.postOnBgThreadDelayed(new C0379n(this, 0), 240);
        }
        prepareSmartAlbumHolder();
        updateAppbarScroll(getResources().getConfiguration());
        if (useAdvancedMouseDragAndDrop()) {
            this.mKeyHandler.bindMouseHandler();
        }
        if (this.mDisplayWithDrawer) {
            Blackboard blackboard = this.mBlackboard;
            if (BackPressUtils.supportPredictiveBack(getContext())) {
                baseListFragment = this;
            } else {
                baseListFragment = null;
            }
            blackboard.post("command://CreateChildFragmentView", baseListFragment);
        }
        if (supportTabLayout()) {
            this.mBlackboard.post("command://UpdateBottomTabFloatingView", this.mRecyclerView);
        }
    }

    public boolean pendingIfViewNotReady() {
        if (isViewReady()) {
            BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
            if (baseListViewAdapter == null) {
                return false;
            }
            baseListViewAdapter.removePendingJobs();
            return false;
        }
        Log.w(this.TAG, "onDataChanged, but view is not available");
        this.mPendingDataAvailable = true;
        return true;
    }

    public void preloadViewPool() {
        this.mBlackboard.publishIfEmpty("debug://TimeInflateStart", Long.valueOf(System.currentTimeMillis()));
    }

    public void prepareExtendedShare() {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            baseListViewAdapter.prepareExtendedShare();
        }
    }

    public void publishThumbnailLoadDone() {
        if (this.mFinishLayout && getListView() != null && getListView().isThumbLoaded()) {
            this.mFinishLayout = false;
            if (this.mBlackboard.isEmpty("lifecycle://on_thumbnail_load_done")) {
                Log.p(this.TAG, "publishThumbnailLoadDone > publish");
                Trace.mark("ThumbDone");
                this.mBlackboard.publish("debug://thumbLoadDoneTime", Long.valueOf(System.currentTimeMillis()));
                this.mBlackboard.publish("lifecycle://on_thumbnail_load_done", Long.valueOf(ThumbnailLoader.getInstance().getElapsedTime()));
                return;
            }
            Log.d(this.TAG, "publishThumbnailLoadDone > skip");
        }
    }

    public void registerWindowInsetListener(List<View> list) {
        if (needToRegisterInsetListener()) {
            list.add(getView());
        }
    }

    public void selectAll() {
        this.mRecyclerView.selectAll();
    }

    public void setFloatingToolbarScrollTransition(boolean z) {
        FloatingToolbarLayout floatingToolbarLayout = this.mFloatingToolbarLayout;
        if (floatingToolbarLayout != null) {
            floatingToolbarLayout.f(z);
        }
    }

    public /* bridge */ /* synthetic */ void setSmartAlbumEnabled(boolean z) {
        super.setSmartAlbumEnabled(z);
    }

    public void showSingleContent(ListViewHolder listViewHolder) {
        onListItemClick(listViewHolder, listViewHolder.getViewPosition(), listViewHolder.getMediaItem(), listViewHolder.getViewType());
    }

    public boolean startAutoDrag(int i2) {
        return this.mDragAndDropDelegate.startAutoDrag(i2);
    }

    public void startDragAndDropOnAdvancedMouseAction(int i2, ListViewHolder listViewHolder) {
        this.mDragAndDropDelegate.startDragAndDropOnAdvancedMouseAction(i2, listViewHolder, new C0379n(this, 1));
    }

    public /* bridge */ /* synthetic */ void startShrinkAnimation() {
        super.startShrinkAnimation();
    }

    public final void stopAutoDrag() {
        ListAutoDragHandler.stopTimer();
    }

    public void stopScroll() {
        GalleryListView galleryListView = this.mRecyclerView;
        if (galleryListView != null && !galleryListView.isScrollIdle()) {
            this.mRecyclerView.stopScroll();
        }
    }

    public boolean supportAutoDrag() {
        return true;
    }

    public boolean supportExitPredictiveBack() {
        boolean z;
        if (!PickerUtil.isPickerMode(getBlackboard()) || ((Integer) getBlackboard().read("data://picker_mode/stack_size", 0)).intValue() <= 1) {
            z = false;
        } else {
            z = true;
        }
        if (!super.supportExitPredictiveBack() || isSelectionMode() || isMoveMode() || isDrawerOpen() || z || isSearchToolbarFocused()) {
            return false;
        }
        return true;
    }

    public boolean supportNaviBackInSelectionMode() {
        if (!isDrawerMode() || !this.mDisplayWithDrawer) {
            return false;
        }
        return true;
    }

    public boolean supportPostViewCreated() {
        return false;
    }

    public boolean supportSelection() {
        return true;
    }

    public /* bridge */ /* synthetic */ boolean supportSmartAlbum() {
        return super.supportSmartAlbum();
    }

    public /* bridge */ /* synthetic */ void updateDrawerStateToChildFragment(boolean z) {
        super.updateDrawerStateToChildFragment(z);
    }

    public void updateEmptyViewLayout(AppBarLayout appBarLayout, View view, int i2) {
        int i7;
        if (this.mBottomHandler.hasBottomTab()) {
            i7 = getResources().getDimensionPixelOffset(R.dimen.bottom_tab_floating_height);
        } else {
            i7 = 0;
        }
        this.mSystemUi.updateEmptyViewLayout(appBarLayout, view, getUsableHeight(appBarLayout), i2, -i7);
        adjustEmptyViewButtonLayout(false);
    }

    public void updateFadingEdge() {
        GalleryListView galleryListView = this.mRecyclerView;
        if (galleryListView != null) {
            galleryListView.setFadingEdge(galleryListView.getFadingEdgeDirection());
        }
    }

    public void updateListColumn() {
        loadPinchColumns();
        int[] pinchColumn = getPinchColumn();
        getListView().setColumnCount(pinchColumn);
        updateGridSize();
        int depth = this.mRecyclerView.getDepth();
        if (depth >= pinchColumn.length) {
            depth = pinchColumn.length - 1;
        }
        ((BaseListPresenter) this.mPresenter).changeViewDepth(this.mRecyclerView, depth);
    }

    public void updateListViewBottomPadding(int i2) {
        adjustBottomBarMargin(getView());
        this.mBottomHandler.setBottomBarHeight(i2);
    }

    public void updateSelectionToolBar() {
        BaseListFragment baseListFragment;
        if (this.mListAdapter == null || isDestroyed() || (isNormalLaunchMode() && !isViewActive())) {
            Log.w(this.TAG, "ignore update selection toolbar. this is not active");
            return;
        }
        GalleryToolbar toolbar = getToolbar();
        if (toolbar == null) {
            Log.w(this.TAG, "ignore update selection toolbar. toolbar is null");
            return;
        }
        boolean supportNaviBackInSelectionMode = supportNaviBackInSelectionMode();
        if (isSelectionMode()) {
            int selectableMaxCount = this.mListAdapter.getSelectableMaxCount();
            if (selectableMaxCount != 1 || !isSinglePickLaunchMode()) {
                getListView().setOnCheckChangeRunnable(new C0379n(this, 3));
                getListView().setOnItemCheckChangeListener(getOnItemCheckChangeListener());
                if (selectableMaxCount <= 0) {
                    baseListFragment = this;
                } else {
                    baseListFragment = null;
                }
                toolbar.enterSelectionMode(baseListFragment, getSelectionToolbarTitle(), false, supportNaviBackInSelectionMode);
                updateToolbarSelectionCount(toolbar);
            }
        } else {
            toolbar.exitSelectionMode(supportNaviBackInSelectionMode);
        }
        updateToolbarStartInset(toolbar);
        invalidateToolbar();
    }

    public void updateTabMode(boolean z) {
        Optional.ofNullable(getToolbar()).ifPresent(new C0366a(6));
    }

    public void updateToolbarSelectionCount(GalleryToolbar galleryToolbar) {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (galleryToolbar != null && !isDestroyed() && baseListViewAdapter != null) {
            if (baseListViewAdapter.isMultipleSelectionMode() || PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
                boolean isNormalLaunchMode = isNormalLaunchMode();
                int selectedCountForToolbar = getSelectedCountForToolbar(isNormalLaunchMode);
                if (baseListViewAdapter.useClipBoardForNormalSelectionMode()) {
                    galleryToolbar.setSelectedCountInfo(baseListViewAdapter.getSelectedCountOnCurrentView(), selectedCountForToolbar, getTotalSelectableCount(), baseListViewAdapter.getSelectableMaxCount());
                } else {
                    galleryToolbar.setSelectedCountInfo(selectedCountForToolbar, getTotalSelectableCount(), baseListViewAdapter.getSelectableMaxCount());
                }
                if (isNormalLaunchMode) {
                    updateAppbarSelectionCount(selectedCountForToolbar, baseListViewAdapter.getSelectableMaxCount());
                }
            }
        }
    }

    public /* bridge */ /* synthetic */ int[] getPinchColumn(boolean z) {
        return super.getPinchColumn(z);
    }

    public void onDataRangeChangedOnUi(int i2, int i7, Object obj) {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            baseListViewAdapter.notifyItemRangeChanged(i2, i7, obj);
        }
    }

    private void adjustBottomBarMargin(View view, int i2, int i7, int i8) {
        if (view != null) {
            View view2 = view;
            view2.post(new C0380o(view2, i2, i7, i8, 1));
        }
    }

    public void createDragAndDropDelegate() {
    }

    public void updateGridSize() {
    }

    public void updateToolbarStartInset(GalleryToolbar galleryToolbar) {
    }
}
