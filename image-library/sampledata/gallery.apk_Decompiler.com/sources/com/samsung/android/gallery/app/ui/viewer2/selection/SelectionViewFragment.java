package com.samsung.android.gallery.app.ui.viewer2.selection;

import Ad.C0720a;
import Ba.h;
import Fa.C0571z;
import R6.c;
import T3.e;
import U9.b;
import V7.a;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.viewer2.selection.ISelectionView;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.SharedViewElement;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.OnFilmStripItemClickListener;
import com.samsung.android.gallery.widget.filmstrip3.selection.SelectionFilmStripAdapter;
import com.samsung.android.gallery.widget.filmstrip3.selection.SelectionFilmStripView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.toolbar.OnSelectAllListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionViewFragment<V extends ISelectionView, P extends SelectionViewPresenter> extends MvpBaseFragment<V, P> implements ISelectionView, OnFilmStripItemClickListener {
    private final Object LOCK = new Object();
    private int mBackPressedTry = 0;
    private MediaItem mBestItem = null;
    protected View mBottomLayout;
    private boolean mEnableResetPosition = true;
    private SelectionFastOptionHandler mFastOptionHandler;
    protected FastOptionView mFastOptionView;
    protected SelectionFilmStripView mFilmStripView;
    protected ViewStub mFilmStripViewStub;
    private final Runnable mFocusNext = new a(this, 3);
    private int mLastFocusedPosition = -1;
    private boolean mLocalOnly = true;
    protected LinearLayout mMainLayout;
    private MediaData mMediaData;
    private final MediaData.OnDataChangeListener mMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            if (SelectionViewFragment.this.isViewReady()) {
                SelectionViewFragment.this.onDataChangedOnUi();
            } else {
                SelectionViewFragment.this.mPendingDataChanged = true;
            }
        }

        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new m(0, this));
        }
    };
    private final OnSelectAllListener mOnSelectAllListener = new OnSelectAllListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$selectAll$2() {
            SelectionViewFragment selectionViewFragment = SelectionViewFragment.this;
            selectionViewFragment.invalidateToolbar(selectionViewFragment.getToolbar());
        }

        private void selectAll(boolean z) {
            SelectionViewAdapter selectionViewAdapter;
            ViewPager2 viewPager2 = SelectionViewFragment.this.mViewPager;
            if (viewPager2 != null) {
                selectionViewAdapter = (SelectionViewAdapter) viewPager2.getAdapter();
            } else {
                selectionViewAdapter = null;
            }
            Optional.ofNullable(selectionViewAdapter).ifPresent(new n(z, 0));
            Optional.ofNullable(SelectionViewFragment.this.mFilmStripView.getAdapter()).ifPresent(new n(z, 1));
            SelectionViewFragment.this.mFilmStripView.post(new m(1, this));
        }

        public void onSelectAll() {
            selectAll(true);
            SelectionViewFragment.this.setDimFastOptionView(UpdateType.SELECTED, -1);
        }

        public void onUnSelectAll() {
            selectAll(false);
            SelectionViewFragment.this.setDimFastOptionView(UpdateType.UNSELECTED, -1);
        }
    };
    /* access modifiers changed from: private */
    public boolean mPendingDataChanged;
    private RecyclerView mRecyclerView;
    protected GalleryToolbar mToolbar;
    protected ViewPager2 mViewPager;
    private SelectionPageChangeDelegate mViewPagerDelegate;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum UpdateType {
        NORMAL,
        SELECTED,
        UNSELECTED
    }

    private void clearFilmStripView() {
        this.mFilmStripView.removeFilmStripItemListener(this);
        this.mFilmStripView.clearOnScrollListeners();
    }

    private void clearToolbar(GalleryToolbar galleryToolbar) {
        if (galleryToolbar != null) {
            setToolbarStyle(galleryToolbar);
            galleryToolbar.setTitle("");
            galleryToolbar.setSubtitle((CharSequence) null);
        }
    }

    private SelectionFilmStripView inflateFilmStripView() {
        View inflate = this.mFilmStripViewStub.inflate();
        this.mBottomLayout = inflate;
        SelectionFilmStripView selectionFilmStripView = (SelectionFilmStripView) inflate.findViewById(R.id.film_strip);
        SelectionFilmStripView selectionFilmStripView2 = this.mFilmStripView;
        if (selectionFilmStripView2 != null) {
            selectionFilmStripView.setAdapter(selectionFilmStripView2.getAdapter());
        } else {
            selectionFilmStripView.setFilmStripData(this.mMediaData);
        }
        selectionFilmStripView.addFilmStripItemListener(this);
        selectionFilmStripView.addOnScrollListener(new SelectionFilmStripScrollDelegate(this.mViewPager, selectionFilmStripView));
        return selectionFilmStripView;
    }

    /* access modifiers changed from: private */
    public void invalidateToolbar(GalleryToolbar galleryToolbar) {
        SelectionFilmStripAdapter selectionFilmStripAdapter;
        if (galleryToolbar != null) {
            SelectionFilmStripView selectionFilmStripView = this.mFilmStripView;
            if (selectionFilmStripView != null) {
                selectionFilmStripAdapter = selectionFilmStripView.getAdapter();
            } else {
                selectionFilmStripAdapter = null;
            }
            if (selectionFilmStripAdapter != null) {
                galleryToolbar.setSelectedCountInfo(selectionFilmStripAdapter.getSelectedCount(), selectionFilmStripAdapter.getItemCount(), -1);
            }
        }
    }

    private boolean isEnableSave() {
        if (!LocationKey.isSimilarShotSelection(getLocationKey()) && !isUpsm() && this.mLocalOnly) {
            return true;
        }
        return false;
    }

    private boolean isNeedRetryBackPressed(ListViewHolder listViewHolder, int i2) {
        SelectionFilmStripView selectionFilmStripView = this.mFilmStripView;
        if (selectionFilmStripView != null && selectionFilmStripView.isScrolling()) {
            this.mFilmStripView.stopScroll();
            Log.d(this.TAG, "onBackPressed postponed : filmStrip is scrolling", Integer.valueOf(i2), Integer.valueOf(this.mBackPressedTry));
            ThreadUtil.postOnUiThreadDelayed(new a(this, 4), 100);
            return true;
        } else if (listViewHolder == null || listViewHolder.getThumbKind() != ThumbKind.MEDIUM_KIND) {
            return false;
        } else {
            Log.d(this.TAG, "onBackPressed postponed : decoding", Integer.valueOf(i2), Integer.valueOf(this.mBackPressedTry));
            ThreadUtil.postOnUiThreadDelayed(new a(this, 5), 100);
            return true;
        }
    }

    private void iterateChildViewHolders(Consumer<ListViewHolder> consumer) {
        for (int i2 = 0; i2 < this.mRecyclerView.getChildCount(); i2++) {
            RecyclerView recyclerView = this.mRecyclerView;
            ListViewHolder listViewHolder = (ListViewHolder) recyclerView.getChildViewHolder(recyclerView.getChildAt(i2));
            if (listViewHolder != null) {
                consumer.accept(listViewHolder);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getSelectedItems$11() {
        return new MediaItem[0];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer[] lambda$getSelectedPositions$12() {
        return new Integer[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$isNeedRetryBackPressed$5() {
        BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$isNeedRetryBackPressed$6() {
        BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        synchronized (this.LOCK) {
            try {
                ViewPager2 viewPager2 = this.mViewPager;
                if (viewPager2 != null) {
                    viewPager2.setCurrentItem(this.mLastFocusedPosition, false);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onApplyWindowInsets$2(Rect rect, WindowInsets windowInsets) {
        Rect rect2 = new Rect();
        this.mFilmStripView.getGlobalVisibleRect(rect2);
        if (isIntersectWithCutout(rect2, rect)) {
            setBottomLayoutRightMargin(WindowUtils.getSystemInsetsRight(windowInsets));
            resetBurstShotViewCenterAlign();
            return;
        }
        resetBottomLayoutRightMargin();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onItemClick$0(int i2, SelectionViewAdapter selectionViewAdapter) {
        selectionViewAdapter.onPositionEstimated(this.mLastFocusedPosition, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSelected$8() {
        invalidateToolbar(getToolbar());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshFilmStripView$10() {
        Optional.ofNullable(this.mFilmStripView).ifPresent(new b(2, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshFilmStripView$9(SelectionFilmStripView selectionFilmStripView) {
        selectionFilmStripView.scrollToPosition(this.mLastFocusedPosition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetBurstShotViewCenterAlign$4() {
        if (this.mLastFocusedPosition != -1 && !isDestroyed()) {
            this.mFilmStripView.scrollToPosition(this.mLastFocusedPosition);
        }
    }

    private void onBindViewInternal(View view) {
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mFilmStripViewStub = (ViewStub) view.findViewById(R.id.film_strip_view_stub);
        this.mFastOptionView = (FastOptionView) view.findViewById(R.id.fast_option_view);
        this.mMainLayout = (LinearLayout) view.findViewById(R.id.main_layout);
        this.mViewPager = (ViewPager2) view.findViewById(R.id.pager);
    }

    /* access modifiers changed from: private */
    public void onDataChangedOnUi() {
        if (this.mMediaData.getCount() == 0) {
            ThreadUtil.postOnUiThread(new a(this, 0));
            Log.majorEvent("onDataChangedOnUi : count=0. move back" + Logger.getEncodedString(ThreadUtil.getCallStack()));
            return;
        }
        ArrayList<MediaItem> allData = this.mMediaData.getAllData();
        this.mLocalOnly = allData.stream().noneMatch(new C0571z(5));
        updateBestItem(allData);
        Optional.ofNullable(this.mFilmStripView).ifPresent(new e(23));
        updateFastOptionButtonEnabled();
    }

    /* access modifiers changed from: private */
    public void onSelected(int i2, boolean z) {
        UpdateType updateType;
        SelectionFilmStripAdapter adapter = this.mFilmStripView.getAdapter();
        if (adapter != null) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "onSelected {" + i2 + ',' + z + '}');
            StringBuilder sb2 = new StringBuilder("select=");
            sb2.append(z);
            adapter.notifyItemChanged(i2, sb2.toString());
            if (z) {
                updateType = UpdateType.SELECTED;
            } else {
                updateType = UpdateType.UNSELECTED;
            }
            setDimFastOptionView(updateType, i2);
            this.mFilmStripView.post(new a(this, 1));
        }
    }

    private void prepareReturnTransition(ListViewHolder listViewHolder, int i2) {
        if (listViewHolder != null) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "onBackPressed prepareReturnTransition {" + i2 + "} " + listViewHolder + " " + Logger.toSimpleString(listViewHolder.getBitmap(false)));
            iterateChildViewHolders(new e(22));
            TransitionInfo transitionInfo = new TransitionInfo(listViewHolder.getMediaItem(), listViewHolder.getBitmap(), "burstShotSelection/");
            SharedTransition.setTransitionName(listViewHolder.getImage(), "burstShotSelection/");
            SharedTransition.setReturnPosition(this.mBlackboard, i2, transitionInfo);
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.e(stringCompat2, "prepareReturnTransition {" + i2 + "} skip transition");
        SharedTransition.setReturnPosition(this.mBlackboard, i2);
    }

    private void refreshActionBar() {
        GalleryToolbar toolbar = getToolbar();
        if (toolbar != null) {
            toolbar.handleDensityChanged();
            invalidateToolbar(toolbar);
        }
    }

    private void refreshFilmStripView() {
        ViewUtils.replaceView(this.mBottomLayout, this.mFilmStripViewStub);
        clearFilmStripView();
        SelectionFilmStripView inflateFilmStripView = inflateFilmStripView();
        this.mFilmStripView = inflateFilmStripView;
        this.mViewPagerDelegate.setFilmStripView(inflateFilmStripView);
        ThreadUtil.postOnUiThreadDelayed(new a(this, 6), 50);
    }

    private void refreshView() {
        refreshActionBar();
        viewReset();
    }

    private void refreshViewPager() {
        int currentItem = this.mViewPager.getCurrentItem();
        RecyclerView.Adapter adapter = this.mViewPager.getAdapter();
        this.mViewPager.setAdapter((RecyclerView.Adapter) null);
        this.mViewPager.setAdapter(adapter);
        this.mViewPager.setCurrentItem(currentItem, false);
    }

    /* access modifiers changed from: private */
    public void setDimFastOptionView(UpdateType updateType, int i2) {
        int i7;
        MediaItem mediaItem;
        int i8;
        SelectionFilmStripAdapter adapter = this.mFilmStripView.getAdapter();
        if (adapter != null) {
            int selectedCount = adapter.getSelectedCount();
            UpdateType updateType2 = UpdateType.SELECTED;
            boolean z = false;
            if (updateType == updateType2) {
                i7 = 1;
            } else if (updateType != UpdateType.UNSELECTED || selectedCount <= 0) {
                i7 = 0;
            } else {
                i7 = -1;
            }
            int i10 = selectedCount + i7;
            if (supportChangeBestItem() && (mediaItem = this.mBestItem) != null && mediaItem.getBestImage() == 1 && i2 >= 0) {
                long fileId = this.mBestItem.getFileId();
                if (updateType == updateType2) {
                    i8 = i2;
                } else {
                    i8 = -1;
                }
                if (updateType != UpdateType.UNSELECTED) {
                    i2 = -1;
                }
                z = adapter.isBestItemSelected(fileId, i8, i2);
            }
            this.mFastOptionHandler.updateDim(z, i10);
        }
    }

    private void setToolbarStyle(GalleryToolbar galleryToolbar) {
        Context context = getContext();
        if (context != null) {
            galleryToolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.viewer_tool_bar_background_color));
            galleryToolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_color));
        }
    }

    private boolean supportChangeBestItem() {
        if (!Features.isEnabled(Features.SUPPORT_CHANGE_BEST_ITEM) || !this.mLocalOnly) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateBestItem(java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r7) {
        /*
            r6 = this;
            boolean r0 = r7.isEmpty()
            if (r0 == 0) goto L_0x0007
            goto L_0x004f
        L_0x0007:
            r0 = 0
            r6.mBestItem = r0
            java.lang.String r0 = r6.getLocationKey()
            r1 = -1
            java.lang.String r3 = "bestId"
            long r0 = com.samsung.android.gallery.support.utils.ArgumentsUtil.getArgValue((java.lang.String) r0, (java.lang.String) r3, (long) r1)
            java.util.Iterator r7 = r7.iterator()
        L_0x001a:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L_0x004f
            java.lang.Object r2 = r7.next()
            com.samsung.android.gallery.module.data.MediaItem r2 = (com.samsung.android.gallery.module.data.MediaItem) r2
            long r4 = r2.getFileId()
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 == 0) goto L_0x0035
            int r4 = r2.getBestImage()
            r5 = 1
            if (r4 != r5) goto L_0x001a
        L_0x0035:
            r6.mBestItem = r2
            r2.setBestImage()
            java.lang.String r7 = r6.getLocationKey()
            com.samsung.android.gallery.module.data.MediaItem r0 = r6.mBestItem
            long r0 = r0.getFileId()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r7 = com.samsung.android.gallery.support.utils.ArgumentsUtil.appendArgs(r7, r3, r0)
            r6.setLocationKey(r7)
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewFragment.updateBestItem(java.util.ArrayList):void");
    }

    private void updateToolBar(GalleryToolbar galleryToolbar) {
        StringCompat stringCompat = this.TAG;
        Log.e(stringCompat, "updateSelectionToolBar " + galleryToolbar);
        if (galleryToolbar != null) {
            galleryToolbar.enterSelectionMode(this.mOnSelectAllListener, -1, false, false);
            galleryToolbar.showSelectAll(true);
            setToolbarStyle(galleryToolbar);
            invalidateToolbar(galleryToolbar);
        }
    }

    private void viewReset() {
        getBoosterCompat().acquireFull();
        refreshViewPager();
        refreshFilmStripView();
    }

    public SelectionViewHolder findViewHolder(int i2) {
        RecyclerView recyclerView;
        if (this.mRecyclerView == null) {
            this.mRecyclerView = (RecyclerView) this.mViewPager.getChildAt(0);
        }
        if (i2 < 0 || i2 >= getItemCount() || (recyclerView = this.mRecyclerView) == null) {
            return null;
        }
        return (SelectionViewHolder) recyclerView.findViewHolderForAdapterPosition(i2);
    }

    public MediaItem[] getAllItems() {
        return (MediaItem[]) this.mMediaData.getAllData().toArray(new MediaItem[0]);
    }

    public MediaItem getBestItem() {
        return this.mBestItem;
    }

    public Rect getDisplayCutoutRect(WindowInsets windowInsets) {
        DisplayCutout displayCutout;
        if (windowInsets != null) {
            displayCutout = windowInsets.getDisplayCutout();
        } else {
            displayCutout = null;
        }
        if (displayCutout == null) {
            return null;
        }
        List<Rect> boundingRects = displayCutout.getBoundingRects();
        if (boundingRects.isEmpty()) {
            return null;
        }
        return boundingRects.get(0);
    }

    public int getItemCount() {
        return this.mMediaData.getCount();
    }

    public int getLastFocusedPosition() {
        return this.mLastFocusedPosition;
    }

    public int getLayoutId() {
        return R.layout.selection_view_fragment_layout;
    }

    public String getScreenId() {
        if (LocationKey.isSimilarShotSelection(getLocationKey())) {
            return AnalyticsScreenId.SCREEN_DETAIL_VIEW_SIMILAR_SHOT_SELECT.toString();
        }
        return AnalyticsScreenId.SCREEN_DETAIL_VIEW_BURST_SHOT_SELECT.toString();
    }

    public MediaItem[] getSelectedItems() {
        return (MediaItem[]) Optional.ofNullable((SelectionViewAdapter) this.mViewPager.getAdapter()).map(new k(1)).orElseGet(new C0720a(9));
    }

    public Integer[] getSelectedPositions() {
        return (Integer[]) Optional.ofNullable((SelectionViewAdapter) this.mViewPager.getAdapter()).map(new k(0)).orElseGet(new C0720a(8));
    }

    public ArrayList<View> getSharedViewList(Blackboard blackboard) {
        ArrayList<View> arrayList = new ArrayList<>();
        SharedViewElement sharedViewElement = (SharedViewElement) blackboard.pop("data://shared_view_element");
        if (sharedViewElement != null) {
            arrayList.add(sharedViewElement.getImage());
            StringCompat stringCompat = this.TAG;
            Log.st(stringCompat, "getSharedViewList: " + sharedViewElement.getImage());
        }
        return arrayList;
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public void handleDensityChange(int i2) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "handleDensityChange {" + this.mLastFocusedPosition + '}');
        refreshView();
    }

    public void handleOrientationChange(int i2) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "handleOrientationChange {" + this.mLastFocusedPosition + '}');
        viewReset();
    }

    public void handleResolutionChange(int i2) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "handleResolutionChange {" + this.mLastFocusedPosition + '}');
        refreshView();
    }

    public void handleShareInternal() {
        if (this.mLastFocusedPosition != -1) {
            Log.d(this.TAG, "prepareReturnTransition for share event");
            prepareReturnTransition(findViewHolder(this.mLastFocusedPosition), this.mLastFocusedPosition);
            return;
        }
        Log.w(this.TAG, "prepareReturnTransition for share event skip");
    }

    public void initView(View view) {
        if (this.mPendingDataChanged) {
            this.mPendingDataChanged = false;
            onDataChangedOnUi();
        }
        updateToolBar(getToolbar());
    }

    public boolean isIntersectWithCutout(Rect rect, Rect rect2) {
        return rect.intersect(rect2);
    }

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        view.onApplyWindowInsets(windowInsets);
        if (SdkConfig.atLeast(SdkConfig.GED.P)) {
            updatePaddingForDisplayCutOut(view, windowInsets, false);
            Rect displayCutoutRect = getDisplayCutoutRect(windowInsets);
            if (displayCutoutRect == null || displayCutoutRect.left <= 0) {
                resetBottomLayoutRightMargin();
            } else {
                ViewUtils.requestCancelDraw(this.mFilmStripView, 1, new c(this, displayCutoutRect, windowInsets, 17));
                return windowInsets;
            }
        }
        return windowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(getLocationKey());
        this.mMediaData = open;
        open.register(this.mMediaDataChangeListener);
    }

    public boolean onBackPressed() {
        if (!SharedTransition.isEnterTransitionFinished(this.mBlackboard)) {
            Log.e(this.TAG, "onBackPressed skip. transition not finished");
            return true;
        }
        int currentItem = this.mViewPager.getCurrentItem();
        SelectionViewHolder findViewHolder = findViewHolder(currentItem);
        if (this.mBackPressedTry >= 3 || !isNeedRetryBackPressed(findViewHolder, currentItem)) {
            if (!Utils.isAnimationDuration0x(getContext())) {
                prepareReturnTransition(findViewHolder, currentItem);
            }
            this.mBlackboard.postEvent(EventMessage.obtain(20001, currentItem, Boolean.TRUE));
            return false;
        }
        this.mBackPressedTry++;
        return true;
    }

    public void onBindView(View view) {
        int argValue = ArgumentsUtil.getArgValue(getLocationKey(), Message.KEY_POSITION, 0);
        this.mLastFocusedPosition = argValue;
        onBindViewInternal(view);
        SelectionFilmStripView inflateFilmStripView = inflateFilmStripView();
        this.mFilmStripView = inflateFilmStripView;
        inflateFilmStripView.scrollToPosition(argValue);
        this.mViewPagerDelegate = new SelectionPageChangeDelegate(this, this.mViewPager, this.mFilmStripView);
        this.mViewPager.setAdapter(new SelectionViewAdapter(this, this.mMediaData, getLocationKey()).setSharedTransitionPosition(argValue).setSelectionListener(new h(17, this)));
        this.mViewPager.setOffscreenPageLimit(1);
        this.mViewPager.registerOnPageChangeCallback(this.mViewPagerDelegate);
        this.mViewPager.setCurrentItem(argValue, false);
        this.mFastOptionView.setItemSelectedListener(((SelectionViewPresenter) this.mPresenter).getFastOptionViewListener());
        this.mFastOptionHandler = new SelectionFastOptionHandler(this.mFastOptionView, this.mCallerKey);
        updateFastOptionButtonEnabled();
        setDimFastOptionView(UpdateType.NORMAL, -1);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setScreenMode();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.function.Consumer, java.lang.Object] */
    public void onDestroy() {
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 != null) {
            Optional.ofNullable((SelectionViewAdapter) viewPager2.getAdapter()).ifPresent(new Object());
            this.mViewPager.unregisterOnPageChangeCallback(this.mViewPagerDelegate);
            this.mViewPager.setAdapter((RecyclerView.Adapter) null);
        }
        clearFilmStripView();
        this.mFilmStripView = null;
        this.mBlackboard.postEvent(EventMessage.obtain(3021));
        super.onDestroy();
    }

    public void onDetach() {
        super.onDetach();
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mMediaDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
        }
        this.mViewPagerDelegate = null;
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        StringBuilder t = C0212a.t(str, "mLastFocusedPosition : ");
        t.append(this.mLastFocusedPosition);
        Logger.dumpLog(printWriter, t.toString());
    }

    public void onItemClick(int i2, FilmStripViewHolder filmStripViewHolder) {
        if (filmStripViewHolder != null) {
            StringCompat stringCompat = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "onFilmStripItemClicked {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            o2.append(this.mLastFocusedPosition);
            o2.append("}");
            Log.d(stringCompat, o2.toString());
            this.mFilmStripView.smoothScrollToPosition(i2);
            Optional.ofNullable((SelectionViewAdapter) this.mViewPager.getAdapter()).ifPresent(new j(this, i2));
            this.mLastFocusedPosition = i2;
            if (!this.mViewPagerDelegate.isScrolling()) {
                StringCompat stringCompat2 = this.TAG;
                Log.d(stringCompat2, "onFilmStripFocusChanged {" + this.mViewPager.getCurrentItem() + ',' + i2 + '}');
                this.mViewPager.setCurrentItem(i2, false);
            }
        }
    }

    public void onPause() {
        super.onPause();
        this.mEnableResetPosition = true;
    }

    public void onPrepareSharedTransitionV2() {
        SharedTransition.onPreparePictures(this.mBlackboard, this, false);
        clearToolbar(getToolbar());
    }

    public void onResume() {
        super.onResume();
        if (this.mEnableResetPosition) {
            this.mFilmStripView.scrollToPosition(this.mLastFocusedPosition);
        }
    }

    public void onViewHolderBound(ListViewHolder listViewHolder) {
        if (listViewHolder != null) {
            TransitionInfo popTransitionInfo = SharedTransition.popTransitionInfo(this.mBlackboard);
            ImageView image = listViewHolder.getImage();
            if (image != null && popTransitionInfo != null) {
                listViewHolder.setThumbKind(ThumbKind.XLARGE_NC_KIND);
                listViewHolder.bindImage(popTransitionInfo.bitmap);
                SharedTransition.setTransitionName(image, "burstShotSelection/");
                SharedTransition.startPostponedEnterTransition(this, this.mBlackboard);
            }
        }
    }

    public void registerWindowInsetListener(List<View> list) {
        list.add(this.mMainLayout);
    }

    public void resetBottomLayoutRightMargin() {
        setBottomLayoutRightMargin(0);
        resetBurstShotViewCenterAlign();
    }

    public void resetBurstShotViewCenterAlign() {
        this.mFilmStripView.post(new a(this, 2));
    }

    public void setBottomLayoutRightMargin(int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mBottomLayout.getLayoutParams();
        marginLayoutParams.rightMargin = i2;
        this.mBottomLayout.setLayoutParams(marginLayoutParams);
    }

    public void setLastFocusedPosition(int i2) {
        this.mLastFocusedPosition = i2;
    }

    public void setScreenMode() {
        setScreenViewerMode();
    }

    public void updateFastOptionButtonEnabled() {
        this.mFastOptionHandler.enableBestItem(supportChangeBestItem());
        this.mFastOptionHandler.enableSave(isEnableSave());
    }

    public void updatePaddingForDisplayCutOut(View view, WindowInsets windowInsets, boolean z) {
        DisplayCutout displayCutout;
        if (windowInsets != null) {
            displayCutout = windowInsets.getDisplayCutout();
        } else {
            displayCutout = null;
        }
        updatePaddingForDisplayCutoutInternal(view, displayCutout);
        Optional.ofNullable(getToolbar()).ifPresent(new E7.c(displayCutout, z, 5));
    }

    public void updatePaddingForDisplayCutoutInternal(View view, DisplayCutout displayCutout) {
        if (displayCutout != null) {
            if (displayCutout.getSafeInsetLeft() == view.getPaddingLeft()) {
                view.setPadding(0, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
            }
            if (displayCutout.getSafeInsetRight() == view.getPaddingRight()) {
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), 0, view.getPaddingBottom());
            }
        }
    }

    public SelectionViewPresenter createPresenter(ISelectionView iSelectionView) {
        return new SelectionViewPresenter(this.mBlackboard, this);
    }
}
