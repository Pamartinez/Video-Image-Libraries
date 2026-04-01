package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import A.a;
import A4.C0384t;
import Bb.l;
import C4.e;
import C4.f;
import N2.j;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.IAlbumsBaseView;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.SynchronizedViewPool;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AlbumsBaseFragment<V extends IAlbumsBaseView, P extends AlbumsBasePresenter> extends BaseListFragment<V, P> implements IAlbumsBaseView {
    private boolean mIsFragmentInMoveMode = false;
    private final List<RecyclerView.ViewHolder> mPreparedHolders = new ArrayList();
    protected RecyclerView.RecycledViewPool mViewPool;

    public AlbumsBaseFragment() {
        setLocationKey("location://albums");
    }

    private void createViewPool() {
        this.mViewPool = new SynchronizedViewPool();
    }

    private int getGridCountEstimated() {
        try {
            int columnSize = GridHelper.getInstance("location://albums").getColumnSize(getContext());
            if (columnSize == 3) {
                return 12;
            }
            if (columnSize == 2) {
                return 6;
            }
            if (columnSize == 1) {
                return 9;
            }
            return 15;
        } catch (Exception e) {
            a.r(e, new StringBuilder("getGridCountEstimated failed e="), this.TAG);
            return 15;
        }
    }

    private int getRecycledViewCount(RecyclerView.RecycledViewPool recycledViewPool, int i2) {
        if (recycledViewPool != null) {
            return recycledViewPool.getRecycledViewCount(i2);
        }
        return -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0033 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.lang.Boolean lambda$createViewPool$0(java.lang.String r7, int r8, com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter r9, int r10, com.samsung.android.gallery.support.threadpool.ThreadPool.JobContext r11) {
        /*
            r6 = this;
            com.samsung.android.gallery.support.trace.Trace.beginSection(r7)     // Catch:{ all -> 0x00db }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00db }
            r11 = 0
        L_0x0008:
            if (r11 >= r8) goto L_0x0097
            com.samsung.android.gallery.widget.listview.GalleryListView r2 = r6.mRecyclerView     // Catch:{ NullPointerException -> 0x0031, Exception -> 0x0033 }
            androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r9.createViewHolder(r2, r10)     // Catch:{ NullPointerException -> 0x0031, Exception -> 0x0033 }
            java.util.List<androidx.recyclerview.widget.RecyclerView$ViewHolder> r3 = r6.mPreparedHolders     // Catch:{ NullPointerException -> 0x0031, Exception -> 0x0033 }
            monitor-enter(r3)     // Catch:{ NullPointerException -> 0x0031, Exception -> 0x0033 }
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter r4 = r6.mListAdapter     // Catch:{ all -> 0x001f }
            if (r4 != 0) goto L_0x0021
            java.util.List<androidx.recyclerview.widget.RecyclerView$ViewHolder> r4 = r6.mPreparedHolders     // Catch:{ all -> 0x001f }
            r4.add(r2)     // Catch:{ all -> 0x001f }
            monitor-exit(r3)     // Catch:{ all -> 0x001f }
            goto L_0x0093
        L_0x001f:
            r2 = move-exception
            goto L_0x002f
        L_0x0021:
            androidx.recyclerview.widget.RecyclerView$RecycledViewPool r4 = r6.mViewPool     // Catch:{ all -> 0x001f }
            r4.putRecycledView(r2)     // Catch:{ all -> 0x001f }
            com.samsung.android.gallery.support.utils.StringCompat r2 = r6.TAG     // Catch:{ all -> 0x001f }
            java.lang.String r4 = "adapter ready. stop creating"
            com.samsung.android.gallery.support.utils.Log.d(r2, r4)     // Catch:{ all -> 0x001f }
            monitor-exit(r3)     // Catch:{ all -> 0x001f }
            goto L_0x0097
        L_0x002f:
            monitor-exit(r3)     // Catch:{ all -> 0x001f }
            throw r2     // Catch:{ NullPointerException -> 0x0031, Exception -> 0x0033 }
        L_0x0031:
            r2 = move-exception
            goto L_0x0065
        L_0x0033:
            com.samsung.android.gallery.support.utils.StringCompat r2 = r6.TAG     // Catch:{ all -> 0x00db }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00db }
            r3.<init>()     // Catch:{ all -> 0x00db }
            java.lang.String r4 = "createViewPool#"
            r3.append(r4)     // Catch:{ all -> 0x00db }
            r3.append(r7)     // Catch:{ all -> 0x00db }
            java.lang.String r4 = " failed. {"
            r3.append(r4)     // Catch:{ all -> 0x00db }
            r3.append(r11)     // Catch:{ all -> 0x00db }
            java.lang.String r4 = ","
            r3.append(r4)     // Catch:{ all -> 0x00db }
            androidx.recyclerview.widget.RecyclerView$RecycledViewPool r4 = r6.mViewPool     // Catch:{ all -> 0x00db }
            int r4 = r6.getRecycledViewCount(r4, r10)     // Catch:{ all -> 0x00db }
            r3.append(r4)     // Catch:{ all -> 0x00db }
            java.lang.String r4 = "}"
            r3.append(r4)     // Catch:{ all -> 0x00db }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00db }
            com.samsung.android.gallery.support.utils.Log.e(r2, r3)     // Catch:{ all -> 0x00db }
            goto L_0x0093
        L_0x0065:
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter r3 = r6.mListAdapter     // Catch:{ all -> 0x00db }
            if (r3 != 0) goto L_0x0071
            com.samsung.android.gallery.support.utils.StringCompat r8 = r6.TAG     // Catch:{ all -> 0x00db }
            java.lang.String r9 = "adapter destroyed. stop creating"
            com.samsung.android.gallery.support.utils.Log.w(r8, r9)     // Catch:{ all -> 0x00db }
            goto L_0x0097
        L_0x0071:
            com.samsung.android.gallery.support.utils.StringCompat r3 = r6.TAG     // Catch:{ all -> 0x00db }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00db }
            r4.<init>()     // Catch:{ all -> 0x00db }
            java.lang.String r5 = "createViewPool#"
            r4.append(r5)     // Catch:{ all -> 0x00db }
            r4.append(r7)     // Catch:{ all -> 0x00db }
            java.lang.String r5 = " failed. e="
            r4.append(r5)     // Catch:{ all -> 0x00db }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x00db }
            r4.append(r2)     // Catch:{ all -> 0x00db }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x00db }
            com.samsung.android.gallery.support.utils.Log.e(r3, r2)     // Catch:{ all -> 0x00db }
        L_0x0093:
            int r11 = r11 + 1
            goto L_0x0008
        L_0x0097:
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00db }
            com.samsung.android.gallery.support.blackboard.Blackboard r10 = r6.mBlackboard     // Catch:{ all -> 0x00db }
            if (r10 == 0) goto L_0x00a8
            java.lang.String r11 = "debug://TimeInflateQuery"
            java.lang.Long r2 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x00db }
            r10.publish(r11, r2)     // Catch:{ all -> 0x00db }
        L_0x00a8:
            com.samsung.android.gallery.support.utils.StringCompat r10 = r6.TAG     // Catch:{ all -> 0x00db }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00db }
            r11.<init>()     // Catch:{ all -> 0x00db }
            java.lang.String r2 = "createViewPool#"
            r11.append(r2)     // Catch:{ all -> 0x00db }
            r11.append(r7)     // Catch:{ all -> 0x00db }
            java.lang.String r7 = " {"
            r11.append(r7)     // Catch:{ all -> 0x00db }
            java.util.List<androidx.recyclerview.widget.RecyclerView$ViewHolder> r6 = r6.mPreparedHolders     // Catch:{ all -> 0x00db }
            int r6 = r6.size()     // Catch:{ all -> 0x00db }
            r11.append(r6)     // Catch:{ all -> 0x00db }
            java.lang.String r6 = "} +"
            r11.append(r6)     // Catch:{ all -> 0x00db }
            long r8 = r8 - r0
            r11.append(r8)     // Catch:{ all -> 0x00db }
            java.lang.String r6 = r11.toString()     // Catch:{ all -> 0x00db }
            com.samsung.android.gallery.support.utils.Log.d(r10, r6)     // Catch:{ all -> 0x00db }
            java.lang.Boolean r6 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00db }
            com.samsung.android.gallery.support.trace.Trace.endSection()
            return r6
        L_0x00db:
            r6 = move-exception
            com.samsung.android.gallery.support.trace.Trace.endSection()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseFragment.lambda$createViewPool$0(java.lang.String, int, com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter, int, com.samsung.android.gallery.support.threadpool.ThreadPool$JobContext):java.lang.Boolean");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$isMoveMode$1(Blackboard blackboard) {
        boolean z;
        if (blackboard.read("data://album_move") != null) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onNewItemCreated$3() {
        Optional.ofNullable((AlbumsBasePresenter) this.mPresenter).ifPresent(new l(5));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPostViewCreated$4() {
        Optional.ofNullable(getAdapter()).ifPresent(new l(6));
    }

    private void notifyMoveMode() {
        Optional.ofNullable(getAdapter()).ifPresent(new l(4));
    }

    private void onRefreshMoveMode() {
        if (this.mIsFragmentInMoveMode) {
            ((AlbumsBasePresenter) this.mPresenter).showMoveBottomBar(false);
        } else {
            onEnterMoveMode();
        }
    }

    public int calculateViewPoolExtra(AlbumsViewDummyAdapter albumsViewDummyAdapter, boolean z, int i2, int i7) {
        int i8;
        AlbumsViewHolderFactory albumsViewHolderFactory = albumsViewDummyAdapter.mViewHolderFactory;
        if (z) {
            i8 = albumsViewHolderFactory.mGridLayoutId;
        } else {
            i8 = albumsViewHolderFactory.mListLayoutId;
        }
        int cachedItemViewCount = LayoutCache.getInstance().getCachedItemViewCount(i8);
        Log.d(this.TAG, "preloadViewPool#calc", C0086a.i(i2, "max="), C0086a.i(i7, "data="), C0086a.i(cachedItemViewCount, "cached="));
        return Math.min(i2, i7) - cachedItemViewCount;
    }

    public void exitSelectionMode(boolean z) {
        super.exitSelectionMode(z);
        setSmartAlbumEnabled(!isMoveMode());
    }

    public int getDepthFromGridSize(int i2) {
        return super.getDepthFromGridSize(i2);
    }

    public AlbumsViewDummyAdapter getDummyAdapter() {
        return new AlbumsViewDummyAdapter(getListView());
    }

    public int getGridSpacing() {
        return GridMarginHelper.getAlbumSideGap(this.mRecyclerView) - GridMarginHelper.getAlbumHorizontalPadding(this.mRecyclerView);
    }

    public int getLayoutId() {
        return R.layout.fragment_albums_base_layout;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return new AlbumsBaseBlurPinchAnimationManager((PinchLayoutManager) this.mLayoutManager);
        }
        return new AlbumsBasePinchAnimationManager((PinchLayoutManager) this.mLayoutManager);
    }

    public int getPreferenceDefault() {
        return 1;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.ALBUMS_GRID_SIZE;
    }

    public int getSelectionToolbarTitle() {
        return R.string.select_albums;
    }

    public int getStartPinchDepth() {
        return loadPinchDepth();
    }

    public boolean handleConfigurationChange(Configuration configuration) {
        boolean isTabletDpi = isTabletDpi();
        boolean handleConfigurationChange = super.handleConfigurationChange(configuration);
        if (!(isTabletDpi == isTabletDpi() || getAdapter() == null)) {
            GridMarginHelper.clearAlbumPadding();
            getAdapter().initResourceValues();
        }
        return handleConfigurationChange;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        getLayoutManager().handleDensityChange(getContext());
        if (getAdapter() != null) {
            getAdapter().initResourceValues();
        }
        ((AlbumsBasePresenter) this.mPresenter).restoreMoveBottomBar();
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        if (this.mListAdapter != null) {
            getLayoutManager().setSpanCount(this.mListAdapter.getGridSize());
        } else {
            Log.w(this.TAG, "fail to change span count");
        }
    }

    public void initializeAdapter() {
        super.initializeAdapter();
        getListView().setRecycledViewPool(this.mViewPool);
        synchronized (this.mPreparedHolders) {
            try {
                for (RecyclerView.ViewHolder putRecycledView : this.mPreparedHolders) {
                    this.mViewPool.putRecycledView(putRecycledView);
                }
                this.mPreparedHolders.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean isFadingEdgeExtended() {
        if (super.isFadingEdgeExtended() || isMoveMode()) {
            return true;
        }
        return false;
    }

    public boolean isGridView() {
        if (getListView().getColumnCount() != 1) {
            return true;
        }
        return false;
    }

    public boolean isMoveMode() {
        return ((Boolean) Optional.ofNullable(this.mBlackboard).map(new C0384t(23)).orElse(Boolean.FALSE)).booleanValue();
    }

    public void onEnterMoveMode() {
        this.mIsFragmentInMoveMode = true;
        notifyMoveMode();
        ((AlbumsBasePresenter) this.mPresenter).onEnterMoveMode();
        ((AlbumsBasePresenter) this.mPresenter).showMoveBottomBar(false);
        setFloatingToolbarScrollTransition(false);
        setSmartAlbumEnabled(false);
        updateFadingEdge();
        invalidateOptionsMenu();
    }

    public void onExitMoveMode() {
        this.mIsFragmentInMoveMode = false;
        notifyMoveMode();
        ((AlbumsBasePresenter) this.mPresenter).onExitMoveMode();
        setFloatingToolbarScrollTransition(true);
        setSmartAlbumEnabled(true ^ isSelectionMode());
        updateFadingEdge();
        clearAdvancedMouseFocus();
        invalidateOptionsMenu();
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
            if (getAdapter() != null) {
                getAdapter().checkVisibleViewHoldersForXLarge();
                if (TextUtils.equals(getLocationKey(), (String) this.mBlackboard.read("location://variable/currentv1"))) {
                    SeApiCompat.announceVoiceAssistant(getContext(), getAdapter().getGridInfoTextForVoiceAssistant(i2));
                }
            }
        }
        if (i7 != -1 && i2 != i7 && isResumed()) {
            String zoomLevel = AnalyticsDetail.getZoomLevel(i2);
            postAnalyticsLog(AnalyticsEventId.EVENT_PINCH_ZOOM, zoomLevel);
            Log.majorEvent("PinchZoom : " + zoomLevel);
            adjustRecyclerViewPadding();
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 2004) {
            if (i2 != 2006) {
                switch (i2) {
                    case 1057:
                        if (!isViewActive() || getAdapter() == null) {
                            return true;
                        }
                        getAdapter().onPrepareDelete(getLayoutManager());
                        return true;
                    case 1058:
                        if (getAdapter() == null) {
                            return true;
                        }
                        getAdapter().onAbortDelete();
                        return true;
                    case 1059:
                        if (getAdapter() == null) {
                            return true;
                        }
                        getAdapter().onStartDelete();
                        ((AlbumsBasePresenter) this.mPresenter).notifyDataChanged(this.mMediaData);
                        return true;
                    default:
                        return super.onHandleEvent(eventMessage);
                }
            } else if (getAdapter() == null) {
                return true;
            } else {
                getAdapter().notifyAlbumSyncChanged();
                return true;
            }
        } else if (getAdapter() == null) {
            return false;
        } else {
            getAdapter().refreshAlbumNewLabel(eventMessage);
            return false;
        }
    }

    public void onInitMoveMode() {
        if (isMoveMode()) {
            onRefreshMoveMode();
        } else if (this.mIsFragmentInMoveMode) {
            onExitMoveMode();
        }
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (!isMoveMode() || getAdapter() == null || getAdapter().isMoveHerePossible(listViewHolder)) {
            super.onListItemClick(listViewHolder, i2, mediaItem, i7);
        } else {
            Toast.makeText(getContext(), R.string.cannot_move_to_album, 0).show();
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        if (isMoveMode()) {
            return false;
        }
        return super.onListItemLongClick(listViewHolder, i2, mediaItem);
    }

    public boolean onNewItemCreated(String str, String str2, int i2, String str3, int i7) {
        if (getAdapter() == null) {
            return false;
        }
        getAdapter().onNewItemCreated(str, str2, i2, str3, i7);
        ThreadUtil.postOnUiThreadDelayed(new f(this, 0), 600);
        return true;
    }

    public boolean onPostViewCreated() {
        ThreadUtil.postOnUiThreadDelayed(new f(this, 1), 300);
        return true;
    }

    public void onResume() {
        onInitMoveMode();
        super.onResume();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mViewPool == null) {
            Log.d(this.TAG, "onViewCreated#initViewPool");
            createViewPool();
            setViewPoolSize(this.mViewPool);
            preloadViewPool();
        }
        onInitMoveMode();
    }

    public void preloadViewPool() {
        int i2;
        int i7;
        String str;
        String str2;
        super.preloadViewPool();
        AlbumsViewDummyAdapter dummyAdapter = getDummyAdapter();
        boolean isGridView = isGridView();
        if (isGridView) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        int recycledViewCount = this.mViewPool.getRecycledViewCount(i2);
        Integer num = (Integer) this.mBlackboard.pop("data://preload_count");
        if (num != null) {
            i7 = num.intValue();
        } else {
            i7 = getGridCountEstimated();
        }
        int calculateViewPoolExtra = calculateViewPoolExtra(dummyAdapter, isGridView, i7, ((Integer) Optional.ofNullable(this.mMediaData).map(new C0384t(22)).orElse(Integer.valueOf(i7))).intValue());
        int i8 = calculateViewPoolExtra - recycledViewCount;
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("preloadViewPool#");
        if (isGridView) {
            str = "grid";
        } else {
            str = "list";
        }
        sb2.append(str);
        sb2.append(Logger.v("extra=" + calculateViewPoolExtra, "pre=" + num, C0086a.i(i7, "est="), C0086a.i(recycledViewCount, "rec="), C0086a.i(i8, "add=")));
        if (i8 > 0) {
            str2 = "";
        } else {
            str2 = " skip";
        }
        sb2.append(str2);
        Log.d(stringCompat, sb2.toString());
        if (i8 > 0) {
            createViewPool(dummyAdapter, i8, i2);
        }
    }

    public void setViewPoolSize(RecyclerView.RecycledViewPool recycledViewPool) {
        recycledViewPool.setMaxRecycledViews(2, 15);
        recycledViewPool.setMaxRecycledViews(1, 15);
    }

    public boolean supportFastScroll() {
        return true;
    }

    public boolean supportPostViewCreated() {
        return true;
    }

    public boolean supportShareSheet() {
        return false;
    }

    private void createViewPool(AlbumsViewDummyAdapter albumsViewDummyAdapter, int i2, int i7) {
        ThreadPool.getInstance().submit(new e(this, j.b(i7, i2, "ViewHolder-", "#"), i2, albumsViewDummyAdapter, i7));
    }

    public AlbumsBaseLayoutManager createLayoutManager() {
        return new AlbumsBaseLayoutManager(getContext(), getListView().getColumnCount()) {
            public boolean isAppbarVisible() {
                return AlbumsBaseFragment.this.isAppBarPartiallyVisible();
            }

            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                super.setSpanCount(i2);
                AlbumsBaseFragment.this.onGridChanged(i2, spanCount);
            }
        };
    }

    public AlbumsBaseViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new AlbumsBaseViewAdapter(this, getLocationKey());
    }

    public AlbumsBasePresenter createPresenter(IAlbumsBaseView iAlbumsBaseView) {
        return new AlbumsBasePresenter(this.mBlackboard, iAlbumsBaseView);
    }

    public AlbumsBaseViewAdapter getAdapter() {
        return (AlbumsBaseViewAdapter) super.getAdapter();
    }

    public AlbumsBaseLayoutManager getLayoutManager() {
        return (AlbumsBaseLayoutManager) super.getLayoutManager();
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
    }
}
