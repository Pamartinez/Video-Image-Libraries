package com.samsung.android.gallery.app.ui.list.abstraction;

import A4.C0366a;
import S1.e;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.module.abstraction.GridSpans;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.logger.Analytics;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsDetailKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceAnalytics;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.animations.IThemeColor;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.effects.DummyListProtectionGradient;
import com.samsung.android.gallery.widget.effects.ListProtectionGradient;
import com.samsung.android.gallery.widget.floatingui.FloatingViewsInvalidator;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.noitem.EmptyViewListener;
import com.samsung.android.gallery.widget.smartalbum.SmartAlbumHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import q2.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsListFragment<V extends IMvpBaseView, P extends MvpBasePresenter> extends MvpBaseFragment<V, P> {
    protected static final boolean FEATURE_SUPPORT_REAL_RATIO;
    protected static final int PICTURES_MONTH_INDEX;
    /* access modifiers changed from: protected */
    public GalleryAppBarLayout mAppBarLayout;
    private final e mAppbarOffsetChange = new c(this);
    protected SimpleAutoScroller mAutoScroller;
    protected View mEmptyView;
    protected FloatingToolbarLayout mFloatingToolbarLayout;
    protected volatile GridHelper mGridHelper;
    /* access modifiers changed from: protected */
    public GridSpans mGridSpans;
    private boolean mIsDrawerOpened;
    /* access modifiers changed from: protected */
    public MediaData mMediaData;
    final MediaData.OnDataChangeListener mMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataRangeChanged$0(int i2, int i7) {
            AbsListFragment.this.onDataRangeChangedOnUi(i2, i7);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataRangeChanged$1(int i2, int i7, Object obj) {
            AbsListFragment.this.onDataRangeChangedOnUi(i2, i7, obj);
        }

        public void onDataChanged() {
            if (AbsListFragment.this.mBlackboard == null || AbsListFragment.this.mBlackboard.read("shrink_animation_started") == null) {
                ThreadUtil.runOnUiThread(new b(4, this));
                return;
            }
            Log.st(AbsListFragment.this.TAG, "onDataChange during shrink animation, give delay.");
            ThreadUtil.postOnUiThreadDelayed(new b(4, this), 500);
        }

        public void onDataChangedInternal() {
            int i2;
            if (AbsListFragment.this.pendingIfViewNotReady()) {
                return;
            }
            if (AbsListFragment.this.isDestroyed()) {
                Log.e(AbsListFragment.this.TAG, "onDataChangedOnUi but destroyed");
                return;
            }
            StringCompat access$400 = AbsListFragment.this.TAG;
            StringBuilder sb2 = new StringBuilder("onDataChangedOnUi");
            Boolean valueOf = Boolean.valueOf(AbsListFragment.this.isAdapterReady());
            MediaData mediaData = AbsListFragment.this.mMediaData;
            if (mediaData != null) {
                i2 = mediaData.getCount();
            } else {
                i2 = -1;
            }
            sb2.append(Logger.v(valueOf, Integer.valueOf(i2)));
            Log.d(access$400, sb2.toString());
            AbsListFragment.this.onDataChangedOnUi();
        }

        public void onDataRangeChanged(int i2, int i7) {
            StringCompat access$600 = AbsListFragment.this.TAG;
            Log.d(access$600, "onDataRangeChanged" + Logger.v(Boolean.valueOf(AbsListFragment.this.isAdapterReady()), Integer.valueOf(i2), Integer.valueOf(i7)));
            ThreadUtil.runOnUiThread(new i(this, i2, i7));
        }

        public void onDataRangeInserted(int i2, int i7) {
            StringCompat access$500 = AbsListFragment.this.TAG;
            Log.d(access$500, "onDataRangeInserted" + Logger.v(Boolean.valueOf(AbsListFragment.this.isAdapterReady()), Integer.valueOf(i2), Integer.valueOf(i7)));
            AbsListFragment.this.onDataRangeInserted(i2, i7);
        }

        public void onDataRangeChanged(int i2, int i7, Object obj) {
            StringCompat access$700 = AbsListFragment.this.TAG;
            Log.d(access$700, "onDataRangeChanged" + Logger.v(Boolean.valueOf(AbsListFragment.this.isAdapterReady()), Integer.valueOf(i2), Integer.valueOf(i7), obj));
            ThreadUtil.runOnUiThread(new j(this, i2, i7, obj));
        }
    };
    protected boolean mNormalColumnOrder;
    protected int[] mPinchColumnArray;
    private ListProtectionGradient mProtectionGradient = new DummyListProtectionGradient();
    /* access modifiers changed from: protected */
    public GalleryListView mRecyclerView;
    protected SmartAlbumHolder mSmartAlbumHolder;
    protected int[] mSplitPinchColumnArray;
    /* access modifiers changed from: protected */
    public GalleryToolbar mToolbar;

    static {
        int i2;
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_REAL_RATIO);
        FEATURE_SUPPORT_REAL_RATIO = isEnabled;
        if (isEnabled) {
            i2 = 3;
        } else {
            i2 = 2;
        }
        PICTURES_MONTH_INDEX = i2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$adjustEmptyViewButtonLayout$4(TextView textView, View view, View view2, boolean z) {
        if (!isDestroyed()) {
            if (!ViewUtils.isVisible(textView)) {
                view.setY(((float) (view2.getHeight() - view.getHeight())) * 0.47f);
            }
            ViewUtils.setWidth(view, Math.min(this.mEmptyView.getWidth() - (getResources().getDimensionPixelOffset(R.dimen.noitem_side_padding) * 2), getResources().getDimensionPixelOffset(R.dimen.create_shared_album_button_layout_width)));
            if (z) {
                view.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSimpleAutoScroller$5() {
        Optional.ofNullable(getToolbar()).ifPresent(new C0366a(0));
        Optional.ofNullable(getListView()).ifPresent(new C0366a(1));
        this.mAutoScroller = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initFloatingToolbarLayout$0(FloatingToolbarLayout floatingToolbarLayout) {
        floatingToolbarLayout.setRecyclerView(getFloatingToolbarTargetRecyclerView());
        floatingToolbarLayout.setFloatingAware(createFloatingToolbarAware(floatingToolbarLayout));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeOptionalUi$6() {
        invalidateOptionsMenu();
        initializeScroll();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onAppbarVisibleRatio$3(float f, GalleryAppBarLayout galleryAppBarLayout) {
        if (galleryAppBarLayout.canExpandAppbar() && isNormalLaunchMode() && getToolbar() != null) {
            getToolbar().onAppbarVisibleRatio(f);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$savePinchDepth$1(String str, int i2, ThreadPool.JobContext jobContext) {
        if (PreferenceName.ALBUMS_GRID_SIZE.key().equals(str)) {
            PreferenceAnalytics.AlbumsColumnCount.setInteger(i2);
        } else if (str.startsWith(PreferenceName.ALBUM_PICTURES_GRID_SIZE.key())) {
            PreferenceAnalytics.AlbumPicturesColumnCount.setInteger(i2);
        }
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$updateAppbarScroll$2(Configuration configuration) {
        if (supportSmartAlbum()) {
            return true;
        }
        if (configuration.orientation == 2 || isInMultiWindowMode()) {
            return false;
        }
        return true;
    }

    private void resetColorMode() {
        AtomicInteger atomicInteger = (AtomicInteger) this.mBlackboard.read("data://user/WindowColorMode", null);
        if (atomicInteger != null) {
            SystemUi.changeColorMode(getActivity(), atomicInteger.get());
        }
    }

    public void addAppbarOffsetChangedListener() {
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.addOnOffsetChangedListener(this.mAppbarOffsetChange);
        }
    }

    public void addLayoutListenerForAutoScroll() {
        if (supportShrinkTransition()) {
            int returnPosition = SharedTransition.getReturnPosition(this.mBlackboard);
            if (this.mRecyclerView != null) {
                addLayoutListenerForAutoScroll(returnPosition);
            }
        }
    }

    public void adjustEmptyViewButtonLayout(boolean z) {
        if (ViewUtils.isVisible(this.mEmptyView)) {
            View findViewById = this.mEmptyView.findViewById(R.id.no_item_view_container);
            View findViewById2 = this.mEmptyView.findViewById(R.id.create_button_layout);
            TextView textView = (TextView) this.mEmptyView.findViewById(R.id.description);
            if (findViewById == null) {
                return;
            }
            if (ViewUtils.isVisible(findViewById2) || z) {
                findViewById2.post(new h(this, textView, findViewById2, findViewById, z));
            }
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mRecyclerView = (GalleryListView) view.findViewById(R.id.my_recycler_view);
        this.mFloatingToolbarLayout = (FloatingToolbarLayout) view.findViewById(R.id.sesl_floating_toolbar_layout);
        GridHelper gridHelper = getGridHelper();
        this.mGridSpans = this.mRecyclerView.getGridSpans().init(gridHelper.getSpanInfo(), gridHelper.tag(), FEATURE_SUPPORT_REAL_RATIO);
        this.mAppBarLayout = (GalleryAppBarLayout) view.findViewById(R.id.appbar);
        inflateEmptyView(view);
    }

    public void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mMediaDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
        }
    }

    public abstract y createFloatingToolbarAware(FloatingToolbarLayout floatingToolbarLayout);

    public GridHelper createGridHelper(String str) {
        return GridHelper.getInstance(str);
    }

    public ListProtectionGradient createProtectionGradient() {
        return new DummyListProtectionGradient();
    }

    public SimpleAutoScroller createSimpleAutoScroller(int i2) {
        return new SimpleAutoScroller(this.mBlackboard, this.mRecyclerView, i2).setAppbar(this.mAppBarLayout).setThemeColor(getThemeColor()).withStartAction(new b(1, this)).withUpdateAction(new FloatingViewsInvalidator(getActivity())).withFinishAction(new b(2, this));
    }

    public SmartAlbumHolder createSmartAlbumHolder() {
        return null;
    }

    public CharSequence getAppBarSelectionCountString(int i2, int i7) {
        if (i2 != 0 && i7 == -1) {
            return getResources().getQuantityString(R.plurals.n_selected, i2, new Object[]{Integer.valueOf(i2)});
        }
        GalleryToolbar toolbar = getToolbar();
        if (toolbar == null) {
            return "";
        }
        return toolbar.getSelectedCountString(i2, i7);
    }

    public int getAppBarVisibleHeight() {
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout == null) {
            return 0;
        }
        return galleryAppBarLayout.getVisibleHeight();
    }

    public GalleryAppBarLayout getAppbarLayout() {
        return this.mAppBarLayout;
    }

    public abstract int getDataCount();

    public int getDepthFromGridSize(int i2) {
        int[] pinchColumn = getPinchColumn();
        for (int i7 = 0; i7 < pinchColumn.length; i7++) {
            if (i2 == pinchColumn[i7]) {
                return i7;
            }
        }
        if (pinchColumn.length > 2) {
            return 1;
        }
        return 0;
    }

    public View getFastScrollPopupView() {
        return null;
    }

    public RecyclerView getFloatingToolbarTargetRecyclerView() {
        return this.mRecyclerView;
    }

    public GridHelper getGridHelper() {
        if (this.mGridHelper == null) {
            this.mGridHelper = createGridHelper(getLocationKey());
        }
        return this.mGridHelper;
    }

    public GridSpans getGridSpans() {
        return this.mGridSpans;
    }

    public GalleryListView getListView() {
        return this.mRecyclerView;
    }

    public abstract int getListViewPosition(int i2);

    public String getLocationWithExtraArgs() {
        return getLocationKey();
    }

    public int getMaxColumnSize() {
        return this.mGridSpans.spanMax();
    }

    public MediaData getMediaData(String str) {
        return this.mMediaData;
    }

    public int[] getPinchColumn() {
        return getPinchColumn(isDrawerOpen());
    }

    public int getPreferenceDefault() {
        return 2;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.ALBUM_PICTURES_GRID_SIZE;
    }

    public int getStartPinchDepth() {
        return 1;
    }

    public int getStartPinchDepthDex() {
        return getStartPinchDepth();
    }

    public IThemeColor getThemeColor() {
        return null;
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public void inflateEmptyView(View view) {
        this.mEmptyView = view.findViewById(R.id.empty_view);
    }

    public void initFloatingToolbarLayout() {
        Optional.ofNullable(this.mFloatingToolbarLayout).ifPresent(new f(this));
    }

    public void initializeEmptyView() {
        if (this.mEmptyView != null) {
            getListView().setEmptyViewWithVI(this.mEmptyView);
        }
    }

    public void initializeOptionalUi() {
        if (isActivityBusy()) {
            Log.d(this.TAG, "initializeOptionalUi postponed");
            ThreadUtil.postponeOnUiThread(new b(3, this));
            return;
        }
        Log.d(this.TAG, "initializeOptionalUi");
        invalidateOptionsMenu();
        this.mRecyclerView.post(new b(0, this));
    }

    public void initializeScroll() {
        GalleryListView listView = getListView();
        if (listView == null) {
            Log.e(this.TAG, "fail to initializeScroll");
        } else if (supportFastScroll()) {
            listView.initializeFastScroll(getFastScrollPopupView(), useCustomScrollbar(), getAnalyticsScreenId(getScreenId()));
        } else {
            listView.initializeScroll();
        }
    }

    public abstract boolean isAdapterReady();

    public boolean isAppBarPartiallyVisible() {
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout == null || !galleryAppBarLayout.isPartiallyVisible()) {
            return false;
        }
        return true;
    }

    public boolean isDrawerOpen() {
        return this.mIsDrawerOpened;
    }

    public boolean isEmptyViewShowing() {
        return ViewUtils.isVisible(this.mEmptyView);
    }

    public boolean isImmediateTransitionRequired() {
        MediaData mediaData = this.mMediaData;
        if (mediaData == null || mediaData.getCount() != 0 || isResumed()) {
            return false;
        }
        return true;
    }

    public boolean isTablet() {
        return Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES);
    }

    public int[] loadPinchColumnResource() {
        return getGridHelper().getGridArray(getContext());
    }

    public void loadPinchColumns() {
        setPinchColumnArray();
        this.mGridSpans.load(this.mPinchColumnArray, this.mSplitPinchColumnArray);
    }

    public int loadPinchDepth() {
        PreferenceName preferenceName = getPreferenceName();
        if (preferenceName != null) {
            return loadPinchDepth(preferenceName.key(), new e(0, this));
        }
        return getPreferenceDefault();
    }

    public int[] loadSplitPinchColumnResource() {
        return getGridHelper().getSplitGridArray(getContext());
    }

    public void observeMediaData(boolean z) {
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "observeMediaData " + z);
        if (z) {
            this.mMediaData.register(this.mMediaDataChangeListener);
        } else {
            this.mMediaData.unregister(this.mMediaDataChangeListener);
        }
    }

    public abstract void onAppbarOffsetChanged(AppBarLayout appBarLayout, int i2);

    public void onAppbarVisibleRatio(float f) {
        Optional.ofNullable(this.mAppBarLayout).ifPresent(new g(this, f));
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        openMediaData();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateProtectionGradient();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        loadPinchColumns();
        return onCreateView;
    }

    public abstract void onDataChangedOnUi();

    public abstract void onDataRangeChangedOnUi(int i2, int i7);

    public abstract void onDataRangeChangedOnUi(int i2, int i7, Object obj);

    public abstract void onDataRangeInserted(int i2, int i7);

    public void onDestroy() {
        super.onDestroy();
        closeMediaData();
    }

    public void onDisplayedWithDrawer() {
        this.mDisplayWithDrawer = true;
    }

    public void onEmptyViewVisibilityChanged(View view) {
        this.mEmptyView = view;
        if (view != null) {
            invalidateOptionsMenu();
            if (this.mDisplayWithDrawer) {
                this.mBlackboard.post("command://CreateChildFragmentView", (Object) null);
            }
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.onMultiWindowModeChanged(z);
        }
        updateAppbarScroll(getResources().getConfiguration());
    }

    public void onResume() {
        super.onResume();
        resetColorMode();
    }

    public void onSmartAlbumVisibleRatio(float f) {
        SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
        if (smartAlbumHolder != null) {
            smartAlbumHolder.setAlpha(f);
        }
    }

    public void onStart() {
        super.onStart();
        this.mProtectionGradient = createProtectionGradient();
        updateProtectionGradient();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initFloatingToolbarLayout();
        resetColorMode();
    }

    public void openMediaData() {
        if (this.mMediaData == null) {
            MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(getLocationWithExtraArgs());
            this.mMediaData = open;
            open.register(this.mMediaDataChangeListener);
        }
    }

    public abstract boolean pendingIfViewNotReady();

    public void postAnalyticsLogForSelection(boolean z) {
        postAnalyticsLog(AnalyticsEventId.EVENT_MENU_SELECT_ALL, AnalyticsDetail.getOnOff(z));
    }

    public void prepareSmartAlbumHolder() {
        if (supportSmartAlbum() && this.mSmartAlbumHolder == null) {
            this.mSmartAlbumHolder = createSmartAlbumHolder();
        }
    }

    public void registerEmptyViewListener() {
        if (this.mEmptyView != null) {
            getListView().setEmptyViewListener(new EmptyViewListener() {
                public void onLayoutChecked() {
                    AbsListFragment.this.onEmptyViewLayoutChecked();
                }

                public void onVisibilityChanged(View view) {
                    AbsListFragment.this.onEmptyViewVisibilityChanged(view);
                }
            });
        }
    }

    public void removeAppbarOffsetChangedListener() {
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.removeOnOffsetChangedListener(this.mAppbarOffsetChange);
        }
    }

    public void saLoggingSendCount() {
        int dataCount;
        AnalyticsEventId eventId = Analytics.getEventId(getLocationKey());
        if (eventId != null && (dataCount = getDataCount()) > 0) {
            postAnalyticsLog(eventId, (Pair<String, String>[]) Analytics.buildCountDetail(AnalyticsDetailKey.ITEM_COUNT, (long) dataCount));
        }
    }

    public void savePinchDepth(String str, int i2) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "savePinchDepth {" + str + "=" + i2 + "}");
        GalleryPreference.getInstance().saveState(str, getDepthFromGridSize(i2));
        ThreadPool.getInstance().submit(new a(str, i2));
    }

    public void setPinchColumnArray() {
        this.mPinchColumnArray = loadPinchColumnResource();
        this.mSplitPinchColumnArray = loadSplitPinchColumnResource();
    }

    public void setSmartAlbumEnabled(boolean z) {
        SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
        if (smartAlbumHolder != null && smartAlbumHolder.viewInflated()) {
            this.mSmartAlbumHolder.setEnabled(z);
        }
    }

    public void startShrinkAnimation() {
        SharedTransition.startPostponedEnterTransition(this, this.mBlackboard);
    }

    public void startSimpleAutoScroller(int i2) {
        SimpleAutoScroller createSimpleAutoScroller = createSimpleAutoScroller(getListViewPosition(i2));
        this.mAutoScroller = createSimpleAutoScroller;
        createSimpleAutoScroller.start();
    }

    public boolean supportFastScroll() {
        return false;
    }

    public boolean supportShrinkTransition() {
        return false;
    }

    public boolean supportSmartAlbum() {
        return false;
    }

    public void updateAppbarScroll(Configuration configuration) {
        if (this.mAppBarLayout != null && !isDexMode()) {
            this.mAppBarLayout.updateAppbarScroll(getListView(), new d(this, configuration));
        }
    }

    public void updateAppbarSelectionCount(int i2, int i7) {
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            try {
                galleryAppBarLayout.setTitle(getAppBarSelectionCountString(i2, i7));
                this.mAppBarLayout.setSubtitle((CharSequence) null);
            } catch (NullPointerException e) {
                StringCompat stringCompat = this.TAG;
                Log.e(stringCompat, "updateAppbarSelectionCount failed. e=" + e.getMessage());
            }
        }
    }

    public void updateDrawerStateToChildFragment(boolean z) {
        this.mIsDrawerOpened = z;
    }

    public void updateProtectionGradient() {
        this.mProtectionGradient.update(getActivity(), getView(), this.mRecyclerView);
    }

    public boolean useCustomScrollbar() {
        return true;
    }

    public int[] getPinchColumn(boolean z) {
        return z ? this.mSplitPinchColumnArray : this.mPinchColumnArray;
    }

    public final int loadPinchDepth(String str, Supplier<Integer> supplier) {
        int loadInt = GalleryPreference.getInstance().loadInt(str, -1);
        if (loadInt < 0) {
            loadInt = supplier.get().intValue();
        }
        if (loadInt < 0) {
            return 0;
        }
        return Math.min(loadInt, getPinchColumn().length - 1);
    }

    private void addLayoutListenerForAutoScroll(int i2) {
        if (isImmediateTransitionRequired()) {
            Log.st(this.TAG, "addLayoutListenerForAutoScroll[immediate]");
            startShrinkAnimation();
        } else if (i2 >= 0) {
            startSimpleAutoScroller(i2);
        } else if (!isResumed()) {
            StringCompat stringCompat = this.TAG;
            Log.st(stringCompat, "addLayoutListenerForAutoScroll[invalid] " + getDataCount());
            startShrinkAnimation();
        } else {
            Log.st(this.TAG, "addLayoutListenerForAutoScroll[assertion]");
        }
    }

    public void savePinchDepth(int i2) {
        PreferenceName preferenceName = getPreferenceName();
        if (preferenceName != null) {
            savePinchDepth(preferenceName.key(), i2);
        }
    }

    public void onEmptyViewLayoutChecked() {
    }

    public void onAppbarVerticalOffset(AppBarLayout appBarLayout, int i2) {
    }
}
