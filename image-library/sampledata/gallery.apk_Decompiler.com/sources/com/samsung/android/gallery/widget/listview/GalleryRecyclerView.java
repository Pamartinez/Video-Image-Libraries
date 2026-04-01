package com.samsung.android.gallery.widget.listview;

import A.a;
import D3.i;
import Fa.F;
import Fb.f;
import Fb.g;
import Fb.h;
import Fb.j;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import androidx.appcompat.util.SeslRoundedCorner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.module.abstraction.GridSpans;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.BoosterCompat;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$array;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$integer;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.gallery.widget.animations.IAutoScroller;
import com.samsung.android.gallery.widget.listview.noitem.EmptyViewListener;
import com.samsung.android.gallery.widget.listview.noitem.NoItemVI;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listview.scroller.FastScroller;
import com.samsung.android.gallery.widget.listview.scroller.FastScrollerV2;
import com.samsung.android.gallery.widget.listview.scroller.GoToTopDelegate;
import com.samsung.android.gallery.widget.listview.scroller.Scroller;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryRecyclerView extends RecyclerView implements IPinchRecyclerView, IAutoScroller {
    protected final String TAG = getClass().getSimpleName();
    private final RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            GalleryRecyclerView.this.checkIfEmpty();
        }

        public void onItemRangeInserted(int i2, int i7) {
            GalleryRecyclerView.this.checkIfEmpty();
        }

        public void onItemRangeRemoved(int i2, int i7) {
            GalleryRecyclerView.this.checkIfEmpty();
        }
    };
    private BoosterCompat mBooster;
    protected int[] mColumnCount = {4};
    /* access modifiers changed from: private */
    public int mDefaultBGColor;
    protected int mDepth;
    private View mEmptyView;
    private EmptyViewListener mEmptyViewListener;
    private boolean mEnableGoToTop;
    private View.OnTouchListener mExtraOnTouchListener = null;
    private FadingEdgeDirection mFadingEdgeDirection = FadingEdgeDirection.NONE;
    private double mFlingRate = 1.3d;
    private final GoToTopDelegate mGotoTopDelegate = new GoToTopDelegate(this);
    private boolean mIsEnabledLastOutlineStroke;
    protected RecyclerView.ItemAnimator mItemAnimator;
    private RecyclerView.ItemDecoration mItemDecoration;
    /* access modifiers changed from: private */
    public SeslRoundedCorner mListRoundedCorner;
    private NoItemVI mNoItemVI;
    private boolean mPreparingItemAnimation = false;
    private boolean mQuickScrollRunning = false;
    /* access modifiers changed from: private */
    public SeslRoundedCorner mRoundedCorner;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                BoosterCompat p6 = GalleryRecyclerView.this.getBooster();
                Objects.requireNonNull(p6);
                ThreadUtil.postOnBgThread(new j(p6, 0));
            } else if (i2 == 1) {
                BoosterCompat p8 = GalleryRecyclerView.this.getBooster();
                Objects.requireNonNull(p8);
                ThreadUtil.postOnBgThread(new j(p8, 1));
            }
        }
    };
    private int mScrollTabColor;
    private int mScrollThumbColor;
    private Scroller mScroller;
    private boolean mUseDarkThemeGoToTop;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum FadingEdgeDirection {
        NONE,
        TOP,
        BOTTOM,
        ALL
    }

    public GalleryRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        onConstructor(context, attributeSet);
    }

    private void enableRoundedCorner(boolean z) {
        this.mRoundedCorner = new SeslRoundedCorner(getContext(), z);
        SeslRoundedCorner seslRoundedCorner = new SeslRoundedCorner(getContext(), false);
        this.mListRoundedCorner = seslRoundedCorner;
        seslRoundedCorner.setRoundedCornerColor(15, this.mDefaultBGColor);
        this.mListRoundedCorner.setRoundedCorners(15);
        addItemDecoration(new RecyclerView.ItemDecoration() {
            public void seslOnDispatchDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
                super.seslOnDispatchDraw(canvas, recyclerView, state);
                int childCount = recyclerView.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = recyclerView.getChildAt(i2);
                    RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
                    if (childViewHolder instanceof ListViewHolder) {
                        int roundMode = ((ListViewHolder) childViewHolder).getRoundMode();
                        if (roundMode > 0) {
                            GalleryRecyclerView.this.mRoundedCorner.setRoundedCornerColor(roundMode, GalleryRecyclerView.this.mDefaultBGColor);
                        }
                        GalleryRecyclerView.this.mRoundedCorner.setRoundedCorners(roundMode);
                        GalleryRecyclerView.this.mRoundedCorner.drawRoundedCorner(childAt, canvas);
                    }
                }
                GalleryRecyclerView.this.mListRoundedCorner.drawRoundedCorner(canvas);
            }
        });
    }

    /* access modifiers changed from: private */
    public BoosterCompat getBooster() {
        if (this.mBooster == null) {
            this.mBooster = SeApiCompat.getBoosterCompat(getContext().getApplicationContext());
        }
        return this.mBooster;
    }

    @Deprecated
    private int[] getRealRatioHeightSpec() {
        Resources resources = getResources();
        return new int[]{resources.getDimensionPixelOffset(R$dimen.real_ratio_default_height), resources.getDimensionPixelOffset(R$dimen.real_ratio_min_height), resources.getDimensionPixelOffset(R$dimen.real_ratio_max_height)};
    }

    @Deprecated
    private int[] getRealRatioWidthSpec(int i2) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (!(layoutManager instanceof GalleryGridLayoutManager)) {
            return new int[]{0, 0, 0};
        }
        return new int[]{0, 0, getListWidth() - ((GalleryGridLayoutManager) layoutManager).getHintHorizontalPadding(i2)};
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEmptyViewVisible$0() {
        View view = this.mEmptyView;
        if (view != null && view.getVisibility() == 0) {
            this.mEmptyView.setAlpha(1.0f);
            NoItemVI noItemVI = this.mNoItemVI;
            if (noItemVI != null) {
                noItemVI.setRootView(this.mEmptyView);
                this.mNoItemVI.startAnimation();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateIndexTipPosition$6(RecyclerView.LayoutManager layoutManager) {
        seslSetIndexTipPaddingHorizontal(layoutManager.getPaddingLeft() - layoutManager.getPaddingRight(), 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateScrollOffset$4() {
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            scroller.updateScrollOffset(super.computeVerticalScrollOffset());
        }
    }

    /* access modifiers changed from: private */
    public boolean onEmptySpaceSecondaryClick(View view, MotionEvent motionEvent) {
        if (motionEvent.getButtonState() == 2 && motionEvent.getAction() == 0) {
            if (getAdapter() != null) {
                getAdapter().onEmptySpaceSecondaryClick(new PointF(motionEvent.getRawX(), motionEvent.getRawY()));
            }
            return true;
        }
        View.OnTouchListener onTouchListener = this.mExtraOnTouchListener;
        if (onTouchListener == null || !onTouchListener.onTouch(view, motionEvent)) {
            return false;
        }
        return true;
    }

    private void setEmptyViewVisible() {
        EmptyViewListener emptyViewListener;
        View view = this.mEmptyView;
        if (view != null && view.getVisibility() == 8) {
            View view2 = this.mEmptyView;
            if (view2 instanceof ViewStub) {
                this.mEmptyView = ((ViewStub) view2).inflate();
            }
            this.mEmptyView.setAlpha(0.0f);
            this.mEmptyView.setVisibility(0);
            setVisibility(4);
            EmptyViewListener emptyViewListener2 = this.mEmptyViewListener;
            if (emptyViewListener2 != null) {
                emptyViewListener2.onVisibilityChanged(this.mEmptyView);
            }
            this.mEmptyView.postDelayed(new g(this, 0), 50);
        } else if (this.mEmptyView != null && (emptyViewListener = this.mEmptyViewListener) != null) {
            emptyViewListener.onLayoutChecked();
        }
    }

    private void setListViewVisible() {
        int i2;
        if (getVisibility() != 0) {
            GalleryListAdapter adapter = getAdapter();
            if (adapter == null || !adapter.isCheckingTargetCluster()) {
                i2 = 0;
            } else {
                i2 = 4;
            }
            setVisibility(i2);
            View view = this.mEmptyView;
            if (view != null) {
                view.setVisibility(8);
            }
            EmptyViewListener emptyViewListener = this.mEmptyViewListener;
            if (emptyViewListener != null) {
                emptyViewListener.onVisibilityChanged(this.mEmptyView);
            }
        }
    }

    public void addDefaultItemAnimator() {
        setItemAnimator(this.mItemAnimator);
    }

    public void addExtraOnTouchListener(View.OnTouchListener onTouchListener) {
        this.mExtraOnTouchListener = onTouchListener;
    }

    public void assertGalleryListAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null && !(adapter instanceof GalleryListAdapter)) {
            throw new AssertionError("have to use child of GalleryListAdapter");
        }
    }

    public void assertLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof LinearLayoutManager) && !(layoutManager instanceof StaggeredGridLayoutManager) && !(layoutManager instanceof FlexboxLayoutManager)) {
            throw new AssertionError("layout manager should be LinearLayoutManager, StaggeredGridLayoutManager or FlexboxLayoutManager");
        }
    }

    public void checkIfEmpty() {
        boolean z;
        boolean z3;
        if (this.mEmptyView != null) {
            GalleryListAdapter adapter = getAdapter();
            if (adapter != null) {
                z3 = adapter.checkIfEmpty();
                z = adapter.skipEmptyView(z3);
                if (!adapter.supportEmptyDescription() && z3) {
                    Log.d(this.TAG, "checkIfEmpty skip for description not supported");
                    return;
                }
            } else {
                z3 = false;
                z = false;
            }
            if (!z3 || z) {
                setListViewVisible();
            } else {
                setEmptyViewVisible();
            }
        }
    }

    public int computeVerticalScrollOffset() {
        int i2;
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            i2 = scroller.getScrollOffset();
        } else {
            i2 = -1;
        }
        if (i2 == -1) {
            return super.computeVerticalScrollOffset();
        }
        return i2;
    }

    public void destroy() {
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            scroller.clearAnimation();
            this.mScroller.destroy();
            this.mScroller = null;
        }
    }

    public void drawBottomColor() {
        seslSetFillBottomEnabled(true);
        seslSetFillBottomColor(this.mDefaultBGColor);
    }

    public int findFirstCompletelyVisibleItemPositionCompat() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        if (layoutManager instanceof FlexboxLayoutManager) {
            return ((FlexboxLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        int i2 = -1;
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            for (int i7 : ((StaggeredGridLayoutManager) layoutManager).findLastCompletelyVisibleItemPositions((int[]) null)) {
                if (i2 < i7) {
                    i2 = i7;
                }
            }
        }
        return i2;
    }

    public int findFirstVisibleItemPositionCompat() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        if (layoutManager instanceof FlexboxLayoutManager) {
            return ((FlexboxLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions((int[]) null)[0];
        }
        return -1;
    }

    public int findLastCompletelyVisibleItemPositionCompat() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        if (layoutManager instanceof FlexboxLayoutManager) {
            return ((FlexboxLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        return findLastVisibleItemPositionCompat();
    }

    public int findLastVisibleItemPositionCompat() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        if (layoutManager instanceof FlexboxLayoutManager) {
            return ((FlexboxLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        int i2 = -1;
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            for (int i7 : ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions((int[]) null)) {
                if (i2 < i7) {
                    i2 = i7;
                }
            }
        }
        return i2;
    }

    public boolean fling(int i2, int i7) {
        double d = this.mFlingRate;
        return super.fling((int) (((double) i2) * d), (int) (((double) i7) * d));
    }

    public View focusSearch(View view, int i2) {
        try {
            View focusSearch = super.focusSearch(view, i2);
            if (i2 != 130 || focusSearch != null) {
                return focusSearch;
            }
            Log.w(this.TAG, "focusSearch fail in list view. result= null");
            return view;
        } catch (NullPointerException unused) {
            Log.e(this.TAG, "fail focusSearch. keep");
            return view;
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            String str = this.TAG;
            Log.e(str, "fail focusSearch. " + e);
            return null;
        }
    }

    public int[] getActiveColumns() {
        int startDepth = getStartDepth();
        int[] iArr = this.mColumnCount;
        if (startDepth > 0) {
            return Arrays.copyOfRange(iArr, startDepth, iArr.length);
        }
        return iArr;
    }

    public int getCacheViewCount(RecyclerView.LayoutManager layoutManager) {
        int i2;
        if (layoutManager.isItemPrefetchEnabled()) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        return i2 + 2;
    }

    public int getColumnCount() {
        int[] iArr = this.mColumnCount;
        int length = iArr.length;
        int i2 = this.mDepth;
        if (length > i2) {
            return iArr[i2];
        }
        return 1;
    }

    public int[] getColumns() {
        return this.mColumnCount;
    }

    public int getDefaultBGColor() {
        return this.mDefaultBGColor;
    }

    public int getDepth() {
        return this.mDepth;
    }

    public int getDepthFromColumnCount(int i2) {
        int i7 = 0;
        while (true) {
            int[] iArr = this.mColumnCount;
            if (i7 >= iArr.length) {
                return getDepth();
            }
            if (iArr[i7] == i2) {
                return i7;
            }
            i7++;
        }
    }

    public int getEndDepth() {
        return getMaxDepth();
    }

    public FadingEdgeDirection getFadingEdgeDirection() {
        return this.mFadingEdgeDirection;
    }

    public /* bridge */ /* synthetic */ GridSpans getGridSpans() {
        return super.getGridSpans();
    }

    public int getItemCount() {
        GalleryListAdapter adapter = getAdapter();
        if (adapter != null) {
            return adapter.getItemCount();
        }
        return 0;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return super.getLayoutManager();
    }

    public final int getListWidth() {
        int width = getWidth();
        if (width > 0) {
            return width;
        }
        try {
            width = ((Activity) getContext()).getWindow().getDecorView().getWidth();
        } catch (Exception e) {
            a.s(e, new StringBuilder("getRealRatioWidthSpec failed e="), this.TAG);
        }
        if (width <= 0) {
            width = ResourceCompat.getWindowWidth(getContext());
        }
        if (width <= 0) {
            return DeviceInfo.getDisplayWidth(getContext());
        }
        return width;
    }

    public int getMaxColumnCount() {
        return this.mColumnCount[0];
    }

    public int getMaxDepth() {
        return this.mColumnCount.length - 1;
    }

    public int getMaxMonthColumnCount() {
        int[] iArr = this.mColumnCount;
        if (iArr.length > 1) {
            return iArr[1];
        }
        return getMaxColumnCount();
    }

    @Deprecated
    public int[] getRealRatioSpec(int i2, int i7) {
        if (i2 == 0) {
            return getRealRatioWidthSpec(i7);
        }
        if (i2 == 1) {
            return getRealRatioHeightSpec();
        }
        if (i2 == 2) {
            return new int[]{getResources().getInteger(R$integer.real_ratio_max_column_count), getColumnCount(), 0};
        }
        if (i2 == 3) {
            return new int[]{getMaxMonthColumnCount()};
        }
        if (i2 != 4) {
            return new int[]{0, 0, 0};
        }
        return getResources().getIntArray(R$array.timeline_year_column_count);
    }

    public Scroller getScroller() {
        return this.mScroller;
    }

    public SpecProvider getSpecProvider() {
        return new h(0, this);
    }

    public int getStartDepth() {
        return ((Integer) Optional.ofNullable(getAdapter()).map(new i(26)).orElse(0)).intValue();
    }

    public void hideScrollerPopup() {
        Optional.ofNullable(this.mScroller).ifPresent(new F(14));
    }

    public void initAttr(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.GalleryRecyclerView);
            boolean z = obtainStyledAttributes.getBoolean(R$styleable.GalleryRecyclerView_outlineStroke, false);
            this.mIsEnabledLastOutlineStroke = obtainStyledAttributes.getBoolean(R$styleable.GalleryRecyclerView_lastOutlineStroke, z);
            if (obtainStyledAttributes.getBoolean(R$styleable.GalleryRecyclerView_useFrameworkBgColor, false)) {
                this.mDefaultBGColor = getResources().getColor(R$color.default_fw_background, context.getTheme());
            }
            if (obtainStyledAttributes.getBoolean(R$styleable.GalleryRecyclerView_roundedCorner, false)) {
                enableRoundedCorner(z);
            }
            float dimension = obtainStyledAttributes.getDimension(R$styleable.GalleryRecyclerView_contentStartPadding, -1.0f);
            if (dimension > 0.0f) {
                setFirstItemDecoration((int) dimension);
            }
            this.mUseDarkThemeGoToTop = obtainStyledAttributes.getBoolean(R$styleable.GalleryRecyclerView_useDarkThemeGoToTop, false);
            this.mScrollThumbColor = getResources().getColor(obtainStyledAttributes.getResourceId(R$styleable.GalleryRecyclerView_fastScrollThumbColor, R$color.fast_scroll_thumb_default_color), context.getTheme());
            this.mScrollTabColor = getResources().getColor(obtainStyledAttributes.getResourceId(R$styleable.GalleryRecyclerView_fastScrollTabColor, R$color.fast_scroll_thumb_tap_color), context.getTheme());
            this.mEnableGoToTop = obtainStyledAttributes.getBoolean(R$styleable.GalleryRecyclerView_enableGoToTop, true);
            obtainStyledAttributes.recycle();
        }
    }

    public void initializeFastScroll(View view, boolean z, String str) {
        String str2;
        FastScroller fastScroller;
        if (this.mScroller == null) {
            if (z) {
                str2 = str;
                fastScroller = new FastScrollerV2(this, view, str2, this.mScrollThumbColor, this.mScrollTabColor);
            } else {
                String str3 = str;
                FastScroller fastScroller2 = new FastScroller(this, view, str3, -1, this.mScrollThumbColor, this.mScrollTabColor);
                str2 = str3;
                fastScroller = fastScroller2;
            }
            this.mScroller = fastScroller;
            fastScroller.addScrollBarUpdateListener(new f(0));
            this.mGotoTopDelegate.setScreenId(str2);
        }
    }

    public void initializeScroll() {
        if (this.mScroller == null) {
            this.mScroller = new Scroller(this);
        }
    }

    public boolean isData(int i2) {
        GalleryListAdapter adapter = getAdapter();
        if (adapter == null || !adapter.isData(i2)) {
            return false;
        }
        return true;
    }

    public boolean isDivider(int i2) {
        GalleryListAdapter adapter = getAdapter();
        if (adapter == null || !adapter.isDivider(i2)) {
            return false;
        }
        return true;
    }

    public boolean isFadingEdgeExtended() {
        return false;
    }

    public boolean isOngoingPinchAnimation() {
        return false;
    }

    public boolean isQuickScrollRunning() {
        return this.mQuickScrollRunning;
    }

    public boolean isScrollIdle() {
        Scroller scroller = this.mScroller;
        if ((scroller == null || scroller.isHidden()) && getScrollState() == 0 && !this.mQuickScrollRunning) {
            return true;
        }
        return false;
    }

    public boolean isScrolling() {
        if (getScrollState() != 0 || this.mQuickScrollRunning) {
            return true;
        }
        return false;
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter) {
        adapter.registerAdapterDataObserver(this.mAdapterDataObserver);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mGotoTopDelegate.setEnable(this.mEnableGoToTop, this.mUseDarkThemeGoToTop);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            scroller.onConfigurationChanged(configuration);
        }
    }

    public void onConstructor(Context context, AttributeSet attributeSet) {
        this.mDefaultBGColor = getResources().getColor(R$color.default_background, context.getTheme());
        setItemViewCacheSize(2);
        addOnScrollListener(this.mScrollListener);
        setOnTouchListener(new B2.i(9, this));
        seslSetPagingTouchSlopForStylus(true);
        initAttr(context, attributeSet);
        this.mItemAnimator = getItemAnimator();
        setFadingEdge(FadingEdgeDirection.ALL);
    }

    public void onDump(PrintWriter printWriter, String str) {
        StringBuilder t = C0212a.t(str, "column[depth] : {");
        t.append(Arrays.toString(this.mColumnCount));
        t.append("}[");
        t.append(this.mDepth);
        t.append("]");
        Logger.dumpLog(printWriter, t.toString());
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Object, android.view.Choreographer$FrameCallback] */
    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        if (!this.mPreparingItemAnimation) {
            Trace.beginSection("ListOnLayout");
            super.onLayout(z, i2, i7, i8, i10);
            Trace.endSection();
            if (z) {
                Choreographer.getInstance().postFrameCallback(new Object());
            }
            this.mGotoTopDelegate.updateGoToTopPosition();
        }
    }

    public void refreshScrollPosition() {
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            scroller.updateScrollPosition(0, true);
        }
    }

    public void removeAllCachedViews() {
        setItemViewCacheSize(0);
        Optional.ofNullable(getLayoutManager()).ifPresent(new F(16));
        getRecycledViewPool().clear();
    }

    public void removeExtraOnTouchListener() {
        this.mExtraOnTouchListener = null;
    }

    public void removeItemAnimator() {
        setItemAnimator((RecyclerView.ItemAnimator) null);
    }

    public void resetRecyclerViewCache() {
        setItemViewCacheSize(2);
        Optional.ofNullable(getLayoutManager()).ifPresent(new F(15));
    }

    public void resetScroller() {
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            scroller.hide();
            this.mScroller.resetScrollerHeight();
        }
    }

    public void scrollToPosition(int i2, boolean z) {
        super.scrollToPosition(i2);
        if (z) {
            refreshScrollPosition();
        }
    }

    public void scrollToPositionWithAnimation(int i2) {
        Scroller scroller = this.mScroller;
        if (scroller == null || scroller.getScrollOffset() == -1) {
            super.smoothScrollToPosition(i2);
        } else {
            this.mScroller.goToPosition(i2);
        }
    }

    public void scrollToPositionWithOffset(int i2, int i7) {
        if (getLayoutManager() instanceof LinearLayoutManager) {
            ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset(i2, i7);
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) getLayoutManager()).scrollToPositionWithOffset(i2, i7);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        assertGalleryListAdapter(adapter);
        GalleryListAdapter adapter2 = getAdapter();
        if (adapter2 != null) {
            try {
                adapter2.unregisterAdapterDataObserver(this.mAdapterDataObserver);
            } catch (IllegalStateException e) {
                String str = this.TAG;
                Log.e(str, "setAdapter : failed unregisterAdapterDataObserver. e= " + e.getMessage());
            }
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            onAdapterChanged(adapter);
            checkIfEmpty();
        }
    }

    public void setColumnCount(int[] iArr) {
        this.mColumnCount = iArr;
    }

    public void setCustomVI(NoItemVI noItemVI) {
        this.mNoItemVI = noItemVI;
        setEmptyView(noItemVI.getRootView());
    }

    public void setEmptyView(View view) {
        this.mEmptyView = view;
        checkIfEmpty();
    }

    public void setEmptyViewListener(EmptyViewListener emptyViewListener) {
        this.mEmptyViewListener = emptyViewListener;
    }

    public void setEmptyViewWithVI(View view) {
        this.mNoItemVI = new NoItemVI(view);
        setEmptyView(view);
    }

    public void setFadingEdge(FadingEdgeDirection fadingEdgeDirection) {
        boolean z;
        if (!PocFeatures.isEnabled(PocFeatures.GradientProtection)) {
            this.mFadingEdgeDirection = fadingEdgeDirection;
            boolean z3 = false;
            if (fadingEdgeDirection == FadingEdgeDirection.NONE) {
                seslSetFadingEdgeEnabled(false);
                return;
            }
            if (fadingEdgeDirection == FadingEdgeDirection.TOP) {
                z = true;
            } else {
                z = false;
            }
            seslHideBottomFadingEdge(z);
            if (fadingEdgeDirection == FadingEdgeDirection.BOTTOM) {
                z3 = true;
            }
            seslHideTopFadingEdge(z3);
            seslSetFadingEdgeEnabled(true, isFadingEdgeExtended());
        }
    }

    public void setFirstItemDecoration(final int i2) {
        RecyclerView.ItemDecoration itemDecoration = this.mItemDecoration;
        if (itemDecoration != null) {
            removeItemDecoration(itemDecoration);
        }
        AnonymousClass3 r0 = new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                if (recyclerView.getChildAdapterPosition(view) == 0) {
                    rect.left = i2;
                }
            }
        };
        this.mItemDecoration = r0;
        addItemDecoration(r0);
    }

    public void setFlingRate(double d) {
        this.mFlingRate = d;
    }

    public void setGoToTopVisibility(boolean z) {
        this.mGotoTopDelegate.setVisibility(z);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        assertLayoutManager(layoutManager);
        super.setLayoutManager(layoutManager);
        ViewUtils.setLastRoundedCorner(this, this.mIsEnabledLastOutlineStroke);
    }

    public void setPreparingItemAnimation(boolean z) {
        this.mPreparingItemAnimation = z;
    }

    public void setQuickScrollState(boolean z) {
        this.mQuickScrollRunning = z;
    }

    public void setScrollerExtraPadding(int i2) {
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            scroller.setExtraDisplayPadding(i2);
        }
    }

    public void setScrollerListener(Consumer<Integer> consumer) {
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            scroller.setScrollerListener(consumer);
        }
    }

    public void setStartDepth(int i2) {
        this.mDepth = i2;
    }

    public void smoothScrollToPosition(int i2) {
        Scroller scroller;
        if (i2 != 0 || (scroller = this.mScroller) == null || scroller.getScrollOffset() == -1) {
            smoothScrollToPositionRecyclerView(i2);
        } else {
            this.mScroller.goToTop();
        }
    }

    public void smoothScrollToPositionRecyclerView(int i2) {
        super.smoothScrollToPosition(i2);
    }

    public boolean supportSimilarPhoto() {
        if (isScrolling() || isOngoingPinchAnimation()) {
            return false;
        }
        return true;
    }

    public void updateGoToTopBottomPadding(float f) {
        this.mGotoTopDelegate.updateBottomPadding(f);
    }

    public void updateGoToTopPosition() {
        this.mGotoTopDelegate.updateGoToTopPosition();
    }

    public void updateIndexTipPosition() {
        if (seslIsIndexTipEnabled()) {
            Optional.ofNullable(getLayoutManager()).ifPresent(new E9.a(14, this));
        }
    }

    public void updateScrollOffset() {
        postDelayed(new g(this, 1), 100);
    }

    public GalleryListAdapter getAdapter() {
        return (GalleryListAdapter) super.getAdapter();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$initializeFastScroll$3(ValueAnimator valueAnimator) {
    }

    public void setPinchAnimationManager(PinchAnimationManager pinchAnimationManager) {
    }
}
