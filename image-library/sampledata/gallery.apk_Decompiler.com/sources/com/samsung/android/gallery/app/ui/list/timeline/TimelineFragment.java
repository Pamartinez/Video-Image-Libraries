package com.samsung.android.gallery.app.ui.list.timeline;

import A.a;
import A4.C0366a;
import A4.L;
import A4.M;
import O3.l;
import O3.y;
import R6.b;
import R6.c;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewStub;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.dragdrop.SplitDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.TimelineLayoutManager;
import com.samsung.android.gallery.app.ui.list.timeline.ITimelineView;
import com.samsung.android.gallery.app.ui.list.timeline.TimelinePresenter;
import com.samsung.android.gallery.app.ui.tipcard.TimelineTipCardDelegate;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardDelegate;
import com.samsung.android.gallery.database.dal.DbDump;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.cache.ViewPoolCache;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.smartalbum.SmartAlbumHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimelineFragment<V extends ITimelineView, P extends TimelinePresenter> extends PicturesFragment<V, P> implements ITimelineView {
    private Runnable mCreateForTimelineViewPoolRunnable;
    private final GridHelper mGridHelper = GridHelper.getTimeline();
    ViewStub mSmartAlbumLayoutStub;
    private SmartSwitchUpdateManager mSmartSwitchManager;
    private boolean mSupportSmartAlbum;
    private SyncViewUpdateManager mSyncManager;
    protected CollapsingToolbarLayout mToolbarLayout;

    public TimelineFragment() {
        setLocationKey("location://timeline");
        this.mUesDataCollector = true;
    }

    /* access modifiers changed from: private */
    public void createForTimelineViewPoolAsync() {
        this.mCreateForTimelineViewPoolRunnable = null;
        GalleryListView listView = getListView();
        if (!isDestroyed() && listView != null) {
            if (supportYearTimeline()) {
                int maxDividerPoolSize = getMaxDividerPoolSize() - this.mViewPool.getRecycledViewCount(-2);
                int maxYearDataPoolSize = getMaxYearDataPoolSize() - this.mViewPool.getRecycledViewCount(4);
                Log.d(this.TAG, "createViewPoolYear", Integer.valueOf(maxDividerPoolSize), Integer.valueOf(maxYearDataPoolSize));
                if (maxDividerPoolSize > 0) {
                    createViewPool(maxDividerPoolSize, -2);
                }
                if (maxYearDataPoolSize > 0) {
                    createViewPool(maxYearDataPoolSize, 4);
                }
            }
            if (supportMonthForViewing()) {
                int maxMonthXsDataPoolSize = getMaxMonthXsDataPoolSize() - this.mViewPool.getRecycledViewCount(3);
                Log.d(this.TAG, "createViewPoolMonthEx", Integer.valueOf(maxMonthXsDataPoolSize));
                if (maxMonthXsDataPoolSize > 0) {
                    createViewPool(maxMonthXsDataPoolSize, 3);
                }
            }
        }
    }

    private void dismissSyncView() {
        ThreadUtil.postOnUiThreadDelayed(new b(this, 0), 2000);
    }

    private int getMaxYearDataPoolSize() {
        return 10;
    }

    private boolean isMonthForViewing(int i2) {
        int[] spanMonths = this.mGridSpans.spanMonths();
        if (spanMonths.length <= 1 || spanMonths[1] != i2) {
            return false;
        }
        return true;
    }

    private boolean isSecMpPermissionTipCardShowing() {
        View view;
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        View view2 = null;
        if (baseListViewAdapter != null) {
            view = baseListViewAdapter.getHeaderView();
        } else {
            view = null;
        }
        BaseListViewAdapter baseListViewAdapter2 = this.mListAdapter;
        if (baseListViewAdapter2 != null) {
            view2 = baseListViewAdapter2.getTipCardView();
        }
        if (view == null || view2 == null || !view2.getTag().equals("SecMPPermissionTipCard")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isYearView(int i2) {
        if (!supportYearTimeline() || i2 != getMaxColumnSize()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissSyncView$9() {
        SyncViewUpdateManager syncViewUpdateManager;
        View headerView;
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null && (syncViewUpdateManager = this.mSyncManager) != null && (headerView = baseListViewAdapter.getHeaderView(syncViewUpdateManager.getHeaderPosition())) != null && this.mSyncManager.isSyncSuccessTag((String) headerView.getTag())) {
            this.mListAdapter.setHeaderView((View) null, this.mSyncManager.getHeaderPosition());
            this.mListAdapter.notifyItemChanged(0);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleResolutionChange$10(PicturesViewAdapter picturesViewAdapter) {
        if (picturesViewAdapter.isYear()) {
            picturesViewAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startScrollAndShrink$3(int i2, int i7, Object obj) {
        Log.d(this.TAG, "startScrollAndShrink", Integer.valueOf(i2), Integer.valueOf(i7), obj);
        new SimpleAutoScroller(this.mBlackboard, getListView(), i7).setShrinkView(obj).setShrinkType(DataKey.ShrinkType.HIGHLIGHT_JUMP).findAndShrink();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$syncIfYearDataChanged$7() {
        if (!isDestroyed()) {
            Optional.ofNullable(this.mMediaData).ifPresent(new l(21));
        }
    }

    private boolean needUpdateOptionMenu(int i2, int i7) {
        if (isYearView(i2) || isYearView(i7) || isMonthForViewing(i2) || isMonthForViewing(i7)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onUpdateSmartSwitch(View view) {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null && this.mSmartSwitchManager != null) {
            if (baseListViewAdapter.getHeaderView() != null || view != null) {
                this.mListAdapter.setHeaderView(view, this.mSmartSwitchManager.getHeaderPosition());
                this.mListAdapter.notifyItemChanged(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateSyncView(View view) {
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null && this.mSyncManager != null) {
            if (baseListViewAdapter.getHeaderView() != null || view != null) {
                this.mListAdapter.setHeaderView(view, this.mSyncManager.getHeaderPosition());
                this.mListAdapter.notifyItemChanged(0);
                dismissSyncView();
            }
        }
    }

    private void showSimilarPhotoToast() {
        int i2;
        int i7;
        if (getToolbar() == null || getAppbarLayout() == null) {
            i2 = 0;
        } else {
            i2 = getToolbar().getHeight() + getAppBarVisibleHeight();
        }
        FragmentActivity activity = getActivity();
        if (SimilarPhotoHelper.isSimilarPhotoMode()) {
            i7 = R.string.similar_images_grouped;
        } else {
            i7 = R.string.similar_images_ungrouped;
        }
        Utils.showToast(activity, i7, 49, 0, i2);
    }

    private boolean supportSpannableGrid(int i2) {
        return false;
    }

    private void switchDragAndDropDelegate() {
        this.mDragAndDropDelegate.cancelAnimation();
        createDragAndDropDelegate();
    }

    /* access modifiers changed from: private */
    public void syncIfYearDataChanged() {
        SimpleThreadPool.getInstance().execute(new b(this, 3));
    }

    /* access modifiers changed from: private */
    public void updateSpannable(int i2) {
        if (hasLayoutManager()) {
            getLayoutManager().setSpannable(supportSpannableGrid(i2));
        }
    }

    /* access modifiers changed from: private */
    public void updateTipCard(View view) {
        BaseListViewAdapter baseListViewAdapter;
        if (!Features.isEnabled(Features.IS_RDU_MODE) && (baseListViewAdapter = this.mListAdapter) != null) {
            if (baseListViewAdapter.getHeaderView() != null || view != null) {
                this.mListAdapter.setHeaderView(view, 0);
                this.mListAdapter.notifyItemChanged(0);
                Optional.ofNullable(getListView()).ifPresent(new l(20));
            }
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mSmartAlbumLayoutStub = (ViewStub) view.findViewById(R.id.smart_album_layout_stub);
        this.mToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkAndUpdateTipCard(boolean r4) {
        /*
            r3 = this;
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter r0 = r3.mListAdapter
            r1 = 0
            if (r0 == 0) goto L_0x000a
            android.view.View r0 = r0.getHeaderView()
            goto L_0x000b
        L_0x000a:
            r0 = r1
        L_0x000b:
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter r2 = r3.mListAdapter
            if (r2 == 0) goto L_0x0014
            android.view.View r2 = r2.getTipCardView()
            goto L_0x0015
        L_0x0014:
            r2 = r1
        L_0x0015:
            if (r4 != 0) goto L_0x0023
            if (r0 == 0) goto L_0x0023
            if (r2 != 0) goto L_0x001c
            goto L_0x0023
        L_0x001c:
            java.lang.Object r4 = r2.getTag()
            r1 = r4
            java.lang.String r1 = (java.lang.String) r1
        L_0x0023:
            com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardDelegate r4 = r3.mTipCardDelegate
            if (r4 == 0) goto L_0x002e
            android.content.Context r3 = r3.getContext()
            r4.getAndCheckTipCard(r3, r1)
        L_0x002e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.timeline.TimelineFragment.checkAndUpdateTipCard(boolean):void");
    }

    public void createDragAndDropDelegate() {
        if (isDrawerMode()) {
            this.mDragAndDropDelegate = new SplitDragAndDropDelegate(this).setSplitHandler(new SplitDragAndDropDelegate.SplitHandler() {
                public MediaItem[] getSelectedItemsForSplitDrag() {
                    return TimelineFragment.this.getSelectedItems();
                }

                public void hideSplitAnimation() {
                    TimelineFragment.this.mBlackboard.post("command://CloseDrawer", (Object) null);
                }

                public boolean isSplitClosed() {
                    return !TimelineFragment.this.isDrawerOpen();
                }

                public void showSplitAnimation() {
                    TimelineFragment.this.mBlackboard.post("command://OpenDrawer", (Object) null);
                }
            });
        } else {
            super.createDragAndDropDelegate();
        }
    }

    public SmartAlbumHolder createSmartAlbumHolder() {
        return super.createSmartAlbumHolder();
    }

    public TipCardDelegate createTipCardDelegate() {
        return new TimelineTipCardDelegate(this, new y(13, this));
    }

    public void exitSelectionMode(boolean z) {
        super.exitSelectionMode(z);
        SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
        if (smartAlbumHolder != null && !smartAlbumHolder.exitSelectionMode()) {
            Optional.ofNullable(this.mAppBarLayout).ifPresent(new l(23));
        }
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new TimelineViewDummyAdapter(getListView(), getColumnCount());
    }

    public int getLayoutId() {
        if (supportSmartAlbum()) {
            return R.layout.fragment_timeline_custom_layout;
        }
        return R.layout.fragment_timeline_layout;
    }

    public String getLocationWithExtraArgs() {
        if (!PreferenceFeatures.OneUi30.YEAR_CLUSTERING || this.mGridHelper.getGridDepth() != 0) {
            return super.getLocationWithExtraArgs();
        }
        return ArgumentsUtil.appendArgs(getLocationKey(), "fakeLoadingCount", String.valueOf(3000));
    }

    public int getMaxDividerPoolSize() {
        return 15;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return super.getPinchAnimationManager();
    }

    public int getPreferenceDefault() {
        return this.mGridHelper.getDefaultDepth();
    }

    public PreferenceName getPreferenceName() {
        return this.mGridHelper.getPreferenceName();
    }

    public String getScreenId() {
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_TIME_VIEW_EDIT.toString();
        }
        return AnalyticsScreenId.SCREEN_TIME_VIEW_NORMAL.toString();
    }

    public int getStartPinchDepth() {
        int gridDepth = this.mGridHelper.getGridDepth();
        int[] pinchColumn = getPinchColumn();
        if (gridDepth >= 0 && gridDepth < pinchColumn.length) {
            return gridDepth;
        }
        StringCompat stringCompat = this.TAG;
        Log.e(stringCompat, "getStartPinchDepth wrong {" + gridDepth + "}");
        this.mGridHelper.saveGridDepth(getPreferenceDefault());
        return gridDepth;
    }

    public void handleDensityChange(int i2) {
        ViewPoolCache.getInstance().clearViewPool();
        super.handleDensityChange(i2);
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null && baseListViewAdapter.getHeaderView() != null) {
            checkAndUpdateTipCard(true);
        }
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        Optional.ofNullable(this.mSmartAlbumHolder).ifPresent(new C0366a(20));
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        Optional.ofNullable(this.mSmartAlbumHolder).ifPresent(new C0366a(20));
        Optional.ofNullable(getAdapter()).ifPresent(new l(24));
    }

    public void initView(View view) {
        super.initView(view);
        getListView().enableTabFocusBlock(true);
    }

    public void initializeAdapter() {
        super.initializeAdapter();
        getListView().seslSetIndexTipEnabled(true);
        getListView().updateIndexTipPosition();
    }

    public boolean isAllowAdvancedMouseEvent() {
        return getGridSpans().selectable(getListView().getDepth());
    }

    public boolean isSelectableViewDepth(int i2) {
        return this.mGridHelper.isSelectable(i2);
    }

    public int[] loadPinchColumnResource() {
        return this.mGridHelper.getGridArray(getContext());
    }

    public void onCloudMediaSyncStatusChanged() {
        SyncViewUpdateManager syncViewUpdateManager = this.mSyncManager;
        if (syncViewUpdateManager != null) {
            syncViewUpdateManager.onMediaSyncStatusChanged();
        }
    }

    public void onCloudSyncOnOffChanged() {
        boolean isCloudSyncOnCached = SamsungCloudCompat.isCloudSyncOnCached(getContext());
        SyncViewUpdateManager syncViewUpdateManager = this.mSyncManager;
        if (syncViewUpdateManager != null && !isCloudSyncOnCached) {
            syncViewUpdateManager.onMediaContentSyncOff();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getListView().updateIndexTipPosition();
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (isSecMpPermissionTipCardShowing()) {
            checkAndUpdateTipCard(true);
        }
    }

    public void onDataRangeInserted(int i2, int i7) {
        PicturesViewAdapter adapter = getAdapter();
        if (adapter == null || !adapter.useConcatThumbnail()) {
            super.onDataRangeInserted(i2, i7);
            return;
        }
        Log.d(this.TAG, a.d(i2, i7, "onDataRangeInserted {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        adapter.onDataRangeInserted(i2, i7);
        adapter.onUpdateViewHolder(i2);
        onDataInserted(i2, i7);
    }

    public void onDestroy() {
        TipCardDelegate tipCardDelegate = this.mTipCardDelegate;
        if (tipCardDelegate != null) {
            tipCardDelegate.release();
        }
        SyncViewUpdateManager syncViewUpdateManager = this.mSyncManager;
        if (syncViewUpdateManager != null) {
            syncViewUpdateManager.stop();
            this.mSyncManager = null;
        }
        SmartSwitchUpdateManager smartSwitchUpdateManager = this.mSmartSwitchManager;
        if (smartSwitchUpdateManager != null) {
            smartSwitchUpdateManager.stop();
            this.mSmartSwitchManager = null;
        }
        super.onDestroy();
    }

    public void onGridChanged(int i2, int i7) {
        long j2;
        if (i2 != i7) {
            savePinchDepth(i2);
            boolean isYearView = isYearView(i2);
            if (needUpdateOptionMenu(i2, i7)) {
                invalidateOptionsMenu();
                Optional.ofNullable(this.mRecyclerView).ifPresent(new L(isYearView, 19));
            }
            if (isYearView) {
                b bVar = new b(this, 1);
                if (i7 == -1) {
                    j2 = 2000;
                } else {
                    j2 = 0;
                }
                ThreadUtil.postOnBgThreadDelayed(bVar, j2);
            }
            super.onGridChanged(i2, i7);
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            this.mDragAndDropDelegate.cancelAnimation();
        }
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onListItemClick(listViewHolder, i2, mediaItem, i7);
    }

    public void onResume() {
        super.onResume();
        if (isSecMpPermissionTipCardShowing()) {
            ((TimelinePresenter) this.mPresenter).forceReloadData();
        }
    }

    public void onSelectedFromBottomTab() {
        updateSmartMemory();
    }

    public void operateSimilarPhoto() {
        updateSpannable(getColumnCount());
        invalidateOptionsMenu();
        showSimilarPhotoToast();
    }

    public void preloadViewPool() {
        if (PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL && supportViewPool() && GridValue.isMonthXs(getGridSpans().valueOf())) {
            createViewPool(10, 3);
        }
        super.preloadViewPool();
        if (supportViewPool()) {
            if (supportYearTimeline() || supportMonthForViewing()) {
                b bVar = new b(this, 2);
                this.mCreateForTimelineViewPoolRunnable = bVar;
                ThreadUtil.postOnBgThreadDelayed(bVar, 1000);
            }
        }
    }

    public void prepareSmartAlbumHolder() {
        super.prepareSmartAlbumHolder();
    }

    public void releaseRunnable() {
        super.releaseRunnable();
        Runnable runnable = this.mCreateForTimelineViewPoolRunnable;
        if (runnable != null) {
            ThreadUtil.removeCallbackOnBgThread(runnable);
            this.mCreateForTimelineViewPoolRunnable = null;
        }
    }

    public void saLoggingSendCount() {
        postAnalyticsLog(AnalyticsEventId.EVENT_TAB_PICTURES, (Pair<String, String>[]) DbDump.buildTimelineCountDetail());
    }

    public void savePinchDepth(String str, int i2) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "savePinchDepth {" + str + "=" + i2 + "}");
        this.mGridHelper.saveGridDepth(getDepthFromGridSize(i2));
        this.mGridHelper.saveGridCount(i2);
    }

    public void setViewPoolSize(RecyclerView.RecycledViewPool recycledViewPool) {
        super.setViewPoolSize(recycledViewPool);
        if (supportYearTimeline()) {
            recycledViewPool.setMaxRecycledViews(4, 15);
        }
    }

    public void startDragAndDropOnAdvancedMouseAction(int i2, ListViewHolder listViewHolder) {
        if (isSelectableViewDepth(this.mGridHelper.getGridDepth())) {
            super.startDragAndDropOnAdvancedMouseAction(i2, listViewHolder);
        }
    }

    public void startPostponedEnterTransition() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            parentFragment.startPostponedEnterTransition();
        } else {
            super.startPostponedEnterTransition();
        }
    }

    public void startPostponedEnterTransitionV2() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof MvpBaseFragment) {
            ((MvpBaseFragment) parentFragment).startPostponedEnterTransitionV2();
        } else {
            super.startPostponedEnterTransitionV2();
        }
    }

    /* renamed from: startScrollAndShrink */
    public void lambda$startScrollAndShrink$2(Object obj, Bundle bundle) {
        PicturesViewAdapter adapter = getAdapter();
        if (adapter != null) {
            if (!getGridSpans().selectable(getListView().getDepth())) {
                ((TimelinePresenter) this.mPresenter).changeViewDepth(getListView(), getGridSpans().selectableRange()[1]);
            }
            int i2 = BundleWrapper.getInt(bundle, "dataPosition", 0);
            ViewUtils.post(getListView(), new M((Object) this, i2, adapter.getViewPosition(i2), obj, 6));
        } else if (!isDestroyed()) {
            Log.e(this.TAG, "startScrollAndShrink delayed");
            ThreadUtil.postOnUiThreadDelayed(new c(this, obj, bundle, 0), 50);
        }
    }

    public void startShrinkAnimation() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new l(22));
    }

    public boolean supportLayoutCache() {
        return true;
    }

    public boolean supportMonthForViewing() {
        if (this.mGridSpans.spanMonths().length > 1) {
            return true;
        }
        return false;
    }

    public boolean supportSmartAlbum() {
        return this.mSupportSmartAlbum;
    }

    public boolean supportTabLayout() {
        return true;
    }

    public boolean supportTimeline() {
        return true;
    }

    public boolean supportYearTimeline() {
        return this.mGridSpans.hasYear;
    }

    public void updateAppbarSelectionCount(int i2, int i7) {
        SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
        if (smartAlbumHolder == null || !smartAlbumHolder.updateSelectionCount(getAppBarSelectionCountString(i2, i7))) {
            super.updateAppbarSelectionCount(i2, i7);
        }
    }

    public void updateSmartSwitchView() {
        if (Features.isEnabled(Features.SUPPORT_SMARTSWITCH_RESTORE_STATE)) {
            if (this.mSmartSwitchManager == null) {
                this.mSmartSwitchManager = new SmartSwitchUpdateManager(getContext()).setHeaderPosition(1).setListener(new R6.a(this, 1));
            }
            this.mSmartSwitchManager.getSmartSwitchState();
        }
    }

    public void updateSyncView() {
        if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            Log.d(this.TAG, "Cloud syncView is not supported in this device");
        } else if (!Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_STATUS) || Features.isEnabled(Features.IS_UPSM)) {
            Log.d(this.TAG, "cloud state not support or upsm");
        } else {
            if (this.mSyncManager == null) {
                this.mSyncManager = new SyncViewUpdateManager(getContext()).setHeaderPosition(2).setListener(new R6.a(this, 0));
            }
            this.mSyncManager.getSyncState();
        }
    }

    public void updateTabMode(boolean z) {
        super.updateTabMode(z);
        switchDragAndDropDelegate();
    }

    public void updateToolbarStartInset(GalleryToolbar galleryToolbar) {
        galleryToolbar.setContentInset();
    }

    public PicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        TimelineViewAdapter timelineViewAdapter = new TimelineViewAdapter(this, getLocationKey(), (View) null, isRealRatioSupported());
        checkAndUpdateTipCard(false);
        updateSyncView();
        updateSmartSwitchView();
        return timelineViewAdapter;
    }

    public TimelineLayoutManager createLayoutManager() {
        AnonymousClass2 r0 = new TimelineLayoutManager(getContext(), getColumnCount()) {
            public int getExtraLayoutSpace(RecyclerView.State state) {
                return TimelineFragment.this.getExtraLayoutSpace();
            }

            public int getSpacing(int i2) {
                return TimelineFragment.this.getGridSpacing(i2);
            }

            public boolean isAppbarCollapsed() {
                if (!(TimelineFragment.this.mAppBarLayout == null || TimelineFragment.this.mToolbar == null)) {
                    int visibleHeight = TimelineFragment.this.mAppBarLayout.getVisibleHeight();
                    int top = TimelineFragment.this.mToolbar.getTop() + TimelineFragment.this.mToolbar.getHeight();
                    if (visibleHeight > 0 || Math.abs(visibleHeight) >= top) {
                        return false;
                    }
                    return true;
                }
                return false;
            }

            public boolean isAppbarVisible() {
                return TimelineFragment.this.isAppBarPartiallyVisible();
            }

            public boolean isYear(int i2) {
                return TimelineFragment.this.isYearView(i2);
            }

            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                TimelineFragment.this.updateSpannable(i2);
                super.setSpanCount(i2);
                Log.d(TimelineFragment.this.tag(), a.d(spanCount, i2, "setSpanCount {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
                int access$200 = TimelineFragment.this.getOldGridSize(i2, spanCount);
                TimelineFragment.this.updateAdapter(i2, access$200);
                TimelineFragment.this.onGridChanged(i2, access$200);
            }

            public boolean updateExtraStartPadding(int i2, int i7, float f, boolean z, boolean z3) {
                TimelineFragment.this.getListView().updateIndexTipPosition();
                return super.updateExtraStartPadding(i2, i7, f, z, z3);
            }
        };
        r0.setSpanSizeLookup(createSpanSizeLookUp(r0));
        return r0;
    }

    public TimelinePresenter createPresenter(ITimelineView iTimelineView) {
        return new TimelinePresenter(this.mBlackboard, iTimelineView);
    }

    public TimelineLayoutManager getLayoutManager() {
        return (TimelineLayoutManager) super.getLayoutManager();
    }

    public void setScreenMode() {
    }

    public void updateSmartMemory() {
    }
}
